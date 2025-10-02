/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frontend.v2.containers;

import backend.v2.data.TermDataIOException;
import backend.v2.term.AdvancedTerm;
import backend.v2.term.IllegalTermStateException;
import backend.v2.term.RTFDocumentUtils;
import book.bookpicker.Book;
import frontend.SVGIconPanel;
import frontend.v2.controller.DefinitionContainerController;
import frontend.v2.state.ActiveSearchedTermChangeEvent;
import frontend.v2.state.ActiveSearchedTermChangeListener;
import frontend.v2.state.AppState;
import frontend.v2.state.AppStateChangeEvent;
import frontend.v2.state.AppStateChangeListener;
import java.awt.Color;
import java.awt.Cursor;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.IconView;
import javax.swing.text.LabelView;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.ParagraphView;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.rtf.RTFEditorKit;

/**
 *
 * @author BRIN
 */
public non-sealed class DefinitionContainer extends javax.swing.JPanel implements ContentProcessor{

    private final Color initialColorIconFG;
    private final Color initialColorIconBG;
    
    private StyledDocument lastCommitedDefinition;
    
    /**
     * Creates new form DefinitionContainer
     */
    public DefinitionContainer() {
        initComponents();
        lastCommitedDefinition = (StyledDocument) new RTFEditorKit().createDefaultDocument();
        // ENSURE THE PATH NAMES ON ANY FUTURE ICONS HAS PATH ID SAME AS MENTIONED HERE IN DOUBLE QUOTATIONS.
        this.initialColorIconFG = colorIcon.getCurrentFill("foreground");
        this.initialColorIconBG = colorIcon.getCurrentFill("background");
        
        System.out.println(initialColorIconBG);
        System.out.println(initialColorIconFG);
        
        definitionScroll = new JScrollPane();
        definitionPane2 = new WarpHorizontalStylePane();
        definitionScroll.setBorder(null);
        definitionScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        definitionPane2.setBackground(new java.awt.Color(39, 52, 65));
        definitionPane2.setBorder(null);
        definitionPane2.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        definitionPane2.setForeground(new java.awt.Color(255, 255, 255));
        definitionPane2.setText("TEST");
        definitionPane2.setCaretColor(new java.awt.Color(255, 255, 255));
        definitionPane2.setSelectionColor(new Color(255,255,255,70));
        definitionPane2.setSelectedTextColor(null);
        definitionScroll.setViewportView(definitionPane2);
        

        add(definitionScroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 410));
        
        boldIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        italicIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        underlineIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        colorIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        fontFaceIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        
        
        definitionScroll.setVisible(false);
        editorPanel.setVisible(false);
        DefinitionContainerController dcControl = new DefinitionContainerController(this);
        
        AppState.addListener(new AppStateChangeListener() {
            @Override
            public void appStateChanged(AppStateChangeEvent e) {
                // Regardless of what AppState changed to always Reset the fields.
                reset();
            }
        });
        
        AppState.addActiveSearchedTermChangeListener(new ActiveSearchedTermChangeListener() {
            @Override
            public void activeSearchedTermChanged(ActiveSearchedTermChangeEvent e) {
                
                reset();
                if(e.getNewAdvTerm() == null){
                    setEditable(); // since edit icon on Term Info Container will be not visible.
                    reset();
                    
                }else {
                    setContent(AppState.getCurrentBook(), e.getNewAdvTerm());
                    setNonEditable(); // do not let edit by default.
                }
                
                setVisible(true);
                definitionScroll.setVisible(true);
            }
        });
    }
    
    @Override
    public void reset(){
        
        MutableAttributeSet attrs = definitionPane2.getInputAttributes();
        attrs.removeAttribute(StyleConstants.Foreground);
        attrs.removeAttribute(StyleConstants.Background);
        attrs.removeAttribute(StyleConstants.FontFamily);
        attrs.removeAttribute(StyleConstants.FontSize);
        attrs.removeAttribute(StyleConstants.Bold);
        attrs.removeAttribute(StyleConstants.Italic);
        attrs.removeAttribute(StyleConstants.Underline);
        StyleConstants.setForeground(attrs, new java.awt.Color(255, 255, 255));
        StyleConstants.setFontFamily(attrs, "Microsoft YaHei UI");
        StyleConstants.setFontSize(attrs, 18);
        definitionPane2.setStyledDocument(new DefaultStyledDocument());
        fontFaceIcon.overrideFill("foreground", Color.BLUE);
        fontFaceIcon.overrideFill("background", initialColorIconBG);
        /*
        SimpleAttributeSet simpleAttributeSet = new SimpleAttributeSet();
        StyledDocument doc = definitionPane2.getStyledDocument();
        try {
            doc.remove(0, doc.getLength());
        } catch (BadLocationException ex) {
            System.getLogger(DefinitionContainer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        StyleConstants.setForeground(simpleAttributeSet, Color.WHITE);
        doc.setCharacterAttributes(0, doc.getLength(), simpleAttributeSet, true);
        */
    }
    
    @Override
    public void setContent(Book book, AdvancedTerm advTerm){
        
        lastCommitedDefinition = RTFDocumentUtils.clone(advTerm.getStyledDefinition());
        RTFDocumentUtils.stripNewLines(lastCommitedDefinition);
        definitionPane2.setStyledDocument(RTFDocumentUtils.clone(lastCommitedDefinition));
        definitionPane2.setCaretPosition(definitionPane2.getStyledDocument().getLength() - 1);
        
        /* for internal testing only (UNCOMMENT FOR TESTING)
        StyledDocument live = definitionPane2.getStyledDocument();

        System.out.println("live length: " + live.getLength());
        System.out.println("last committed length: " + lastCommitedDefinition.getLength());

        try {
            System.out.println("live text: [" + live.getText(0, live.getLength()) + "]");
            System.out.println("last text: [" + lastCommitedDefinition.getText(0, lastCommitedDefinition.getLength()) + "]");
        } catch (BadLocationException ex) {
            System.getLogger(DefinitionContainer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);

        }

        System.out.println("live root elements: " + live.getDefaultRootElement().getElementCount());
        System.out.println("last root elements: " + lastCommitedDefinition.getDefaultRootElement().getElementCount());

        */
    }
    
    @Override
    public List<StyledDocument> extractContent(){
        List<StyledDocument> docList = new LinkedList<>();
        docList.add(definitionPane2.getStyledDocument());
        return docList;
    }
    
    @Override
    public boolean wasContentChanged(){
        
        boolean isDifferent = RTFDocumentUtils.isDifferent(lastCommitedDefinition, definitionPane2.getStyledDocument());
        
        /* for internal testing only (UNCOMMENT IN CASE OF TESTING)
        StyledDocument live = definitionPane2.getStyledDocument();

        System.out.println("live length: " + live.getLength());
        System.out.println("last committed length: " + lastCommitedDefinition.getLength());

        try {
            System.out.println("live text: [" + live.getText(0, live.getLength()) + "]");
            System.out.println("last text: [" + lastCommitedDefinition.getText(0, lastCommitedDefinition.getLength()) + "]");
        } catch (BadLocationException ex) {
            System.getLogger(DefinitionContainer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);

        }

        System.out.println("live root elements: " + live.getDefaultRootElement().getElementCount());
        System.out.println("last root elements: " + lastCommitedDefinition.getDefaultRootElement().getElementCount());
        */
        
        return isDifferent;
    }

    public SVGIconPanel getBoldIcon() {
        return boldIcon;
    }

    public SVGIconPanel getItalicIcon() {
        return italicIcon;
    }

    public SVGIconPanel getUnderlineIcon() {
        return underlineIcon;
    }

    public SVGIconPanel getColorIcon() {
        return colorIcon;
    }

    public SVGIconPanel getFontFaceIcon() {
        return fontFaceIcon;
    }

    public JTextPane getDefinitionPane() {
        return definitionPane2;
    }

    public JPanel getEditorPanel() {
        return editorPanel;
    }

    public JScrollPane getDefinitionScroll() {
        return definitionScroll;
    }

    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editorPanel = new javax.swing.JPanel();
        fontFaceIcon = new frontend.SVGIconPanel();
        boldIcon = new frontend.SVGIconPanel();
        italicIcon = new frontend.SVGIconPanel();
        underlineIcon = new frontend.SVGIconPanel();
        colorIcon = new frontend.SVGIconPanel();

        setMinimumSize(new java.awt.Dimension(1260, 500));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1260, 500));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editorPanel.setOpaque(false);
        editorPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        try {
            fontFaceIcon.set_SVG_URL_String("/icons/defedit/fontface2-white.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        fontFaceIcon.setArcSize(15);
        fontFaceIcon.setCurrentColor(new java.awt.Color(57, 75, 92));
        fontFaceIcon.setIconPosition(frontend.SVGIconPanel.iconPosition.CENTER);
        fontFaceIcon.setIconSquarePadding(5.0);
        fontFaceIcon.setFocusable(false);
        editorPanel.add(fontFaceIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 2, 30, 30));

        try {
            boldIcon.set_SVG_URL_String("/icons/defedit/bold-white.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        boldIcon.setArcSize(15);
        boldIcon.setCurrentColor(new java.awt.Color(57, 75, 92));
        boldIcon.setIconPosition(frontend.SVGIconPanel.iconPosition.CENTER);
        boldIcon.setIconSquarePadding(5.0);
        boldIcon.setFocusable(false);
        editorPanel.add(boldIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2, 30, 30));

        try {
            italicIcon.set_SVG_URL_String("/icons/defedit/italic-white.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        italicIcon.setArcSize(15);
        italicIcon.setCurrentColor(new java.awt.Color(57, 75, 92));
        italicIcon.setIconPosition(frontend.SVGIconPanel.iconPosition.CENTER);
        italicIcon.setIconSquarePadding(5.0);
        italicIcon.setFocusable(false);
        editorPanel.add(italicIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 2, 30, 30));

        try {
            underlineIcon.set_SVG_URL_String("/icons/defedit/underline-white.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        underlineIcon.setArcSize(15);
        underlineIcon.setCurrentColor(new java.awt.Color(57, 75, 92));
        underlineIcon.setIconPosition(frontend.SVGIconPanel.iconPosition.CENTER);
        underlineIcon.setIconSquarePadding(5.0);
        underlineIcon.setFocusable(false);
        editorPanel.add(underlineIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 2, 30, 30));

        try {
            colorIcon.set_SVG_URL_String("/icons/defedit/color.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        colorIcon.setArcSize(15);
        colorIcon.setCurrentColor(new java.awt.Color(57, 75, 92));
        colorIcon.setIconPosition(frontend.SVGIconPanel.iconPosition.CENTER);
        colorIcon.setIconSquarePadding(4.0);
        colorIcon.setFocusable(false);
        editorPanel.add(colorIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 2, 30, 30));

        add(editorPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 1260, 35));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private frontend.SVGIconPanel boldIcon;
    private frontend.SVGIconPanel colorIcon;
    private javax.swing.JPanel editorPanel;
    private frontend.SVGIconPanel fontFaceIcon;
    private frontend.SVGIconPanel italicIcon;
    private frontend.SVGIconPanel underlineIcon;
    // End of variables declaration//GEN-END:variables
    
    private final JScrollPane definitionScroll;
    private WarpHorizontalStylePane definitionPane2;
    
    
    private class WarpHorizontalStylePane extends JTextPane {

        public WarpHorizontalStylePane() {
            super();
        // Set the editor kit to a custom one that handles wrapping
            setEditorKit(new RTFEditorKit() {
                @Override
                public ViewFactory getViewFactory() {
                    return new RTFViewFactory();
                }
            });
        }

        // A custom ViewFactory to create our wrapping ParagraphView
        private class RTFViewFactory implements ViewFactory {
            @Override
            public View create(Element elem) {
                String kind = elem.getName();
                if (kind != null) {
                    if (kind.equals(AbstractDocument.ContentElementName)) {
                        return new LabelView(elem);
                    } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                        // Return our custom wrapping ParagraphView
                        return new WrappingParagraphView(elem);
                    } else if (kind.equals(AbstractDocument.SectionElementName)) {
                        return new BoxView(elem, View.Y_AXIS);
                    } else if (kind.equals(StyleConstants.ComponentElementName)) {
                        return new ComponentView(elem);
                    } else if (kind.equals(StyleConstants.IconElementName)) {
                        return new IconView(elem);
                    }
                }
                // Default to the base class behavior
                return new LabelView(elem);
            }
        }

        // The custom ParagraphView that forces the text to wrap
        private class WrappingParagraphView extends ParagraphView {
            public WrappingParagraphView(Element elem) {
                super(elem);
            }

            // This is the key method for wrapping
            @Override
            public int getFlowSpan(int index) {
                return super.getContainer().getWidth();
            }
        }
        
        @Override
        public void setText(String toSet){
            
            try {
                setText(toSet, 0, this.getStyledDocument().getDefaultRootElement().getAttributes());
            } catch (BadLocationException ex) {
                System.getLogger(DefinitionContainer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                System.err.println(ex.getMessage());
            }
        }
        
        public void setText(String toSet, int offset) throws BadLocationException{
            
            setText(toSet, offset, this.getStyledDocument().getDefaultRootElement().getAttributes());
        }
        
        public void setTxt(String toSet, AttributeSet attr){
            
            try {
                setText(toSet, 0, attr);
            } catch (BadLocationException ex) {
                System.getLogger(DefinitionContainer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                System.err.println(ex.getMessage());
            }
        }
        
        public void setText(String toSet, int offset, AttributeSet attr) throws BadLocationException{
            
            this.getDocument().remove(0, this.getDocument().getLength());
            this.getDocument().insertString(offset, toSet, attr);
        }
        
        
        @Override
        public boolean getScrollableTracksViewportWidth() {
            
            return true;
        }
    }
    
    @Override
    public void setEditable() {
        
        System.out.println("SET EDITABLE CALLED IN DEFINITION CONTAINER");
        this.editorPanel.setVisible(true);
        this.definitionPane2.setEditable(true);
        this.repaint();
    }

    @Override
    public void setNonEditable() {
        this.editorPanel.setVisible(false);
        this.definitionPane2.setEditable(false);
    }

    @Override
    public String getHeaderName() {
        return "DEFINITION:";
    }
    
    @Override
    public void revertChanges() {
        
        definitionPane2.setDocument(RTFDocumentUtils.clone(lastCommitedDefinition));
    }

    @Override
    public void saveOnlyThis() {
        
        AdvancedTerm currentTerm = AppState.getCurrentActiveSearchedTerm();
        AdvancedTerm copy = new AdvancedTerm(currentTerm);
        try {
            copy.setDefinition(definitionPane2.getStyledDocument());
            setContent(AppState.getCurrentBook(), copy);
            lastCommitedDefinition = RTFDocumentUtils.clone(definitionPane2.getStyledDocument());
        } catch (IllegalTermStateException ex) {
            System.getLogger(DefinitionContainer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        // v0.5.0 don't support keywords yet! // TODO : add in future versions!
        try {
            AppState.getCurrentBook().getATDM().flush(currentTerm, copy);
        } catch (TermDataIOException ex) {
            System.getLogger(DefinitionContainer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
