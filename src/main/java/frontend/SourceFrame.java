/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend;

import backend.HeadingModel;
import backend.Term;
import backend.TermDataManagement;
import book.bookpicker.Book;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

/**
 *
 * @author BRIN
 */
public class SourceFrame extends javax.swing.JFrame {

    /**
     * Creates new form SourceFrame
     */
    
    private final Book initialisedBook;
    private boolean termFound = false;
    
    private final TermDataManagement tdm;
    private final String DEFAULT_SPELLING = "_PLACE_HOLDER_";
    private Term currentTerm = new Term(DEFAULT_SPELLING);
    
    
    private final String DEFAULT_STATUS_TEXT = "";
    private final String TERM_FOUND_STATUS_TEXT = "Term found! Press enter now to edit this term.";
    private final String TERM_NOT_FOUND_STATUS_TEXT = "Specified term was not found! Press enter now to create a new term";
    
    private final String DEFAULT_DEFINITION = "-";
    private final String DEFAULT_REFERENCE = " -"; // space for stylistic choice
    
    // LEGACY >>>>>>>>>>>>>>>>>>>>>>
    private void checkTerm(){
        
        
        String currentSpelling = spellingTextBox.getText().trim().toLowerCase();
        
        termFound= tdm.contains(currentSpelling);
        
        
        
        if(termFound){
            
            currentTerm = tdm.getTerm(currentSpelling);
            statusLabel.setText(TERM_FOUND_STATUS_TEXT);
            definitionTextArea.setText(currentTerm.getDefinition());
            pageTextBox.setText(Integer.toString(currentTerm.getPage()));
            superHeadingTextBox.setText(currentTerm.getSuperHeadingContent());
            middleHeadingTextBox.setText(currentTerm.getMiddleHeadingContent());
            subHeadingTextArea.setText(currentTerm.getSubHeadingContent());
            
        }
        else{
            
            currentTerm = new Term(DEFAULT_SPELLING);
            if(!currentSpelling.isBlank()){
                statusLabel.setText(TERM_NOT_FOUND_STATUS_TEXT);
            }
            else{
                statusLabel.setText(DEFAULT_STATUS_TEXT);
            }
            definitionTextArea.setText(DEFAULT_REFERENCE);
            pageTextBox.setText(DEFAULT_REFERENCE);
            superHeadingTextBox.setText(DEFAULT_REFERENCE);
            middleHeadingTextBox.setText(DEFAULT_REFERENCE);
            subHeadingTextArea.setText(DEFAULT_REFERENCE);
        }
        
        
        
    }
    
    private void initialiseSpellingTextBoxBehaviour(){
        
        
        
        Document doc = spellingTextBox.getDocument();
        
        doc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // This method is called when text is inserted
                checkTerm();
                    
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // This method is called when text is removed
                
                checkTerm();
                
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // This method is called when attributes change (less common for JTextField)
                
                checkTerm();
            }

            
        });
    }
    
    // LEGACY ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    
    public SourceFrame(Book book) {
        this.initialisedBook = book;
        this.tdm = book.getTDM();
        HeadingModel hm = book.getHeadingModel();
        
        initComponents();
        this.superHeadingLabel.setText(hm.getSuperHeading() + ":");
        this.middleHeadingLabel.setText(hm.getMiddleHeading() + ":");
        this.subHeadingLabel.setText(hm.getSubHeading() + ":");
        // ****** LEGACY ******
        //initialiseSpellingTextBoxBehaviour();
        //roundedPanel.setDefaultColor(Color.white);
        
        // ****** LEGACY ******
        
        bookLabel.setText(book.getTitle());
        bookLabel.setForeground(book.getColorScheme().getHoverColor());
        containerPane.getViewport().setOpaque(false);
        containerPane.setVisible(false);
        
    }
    
    public static SourceFrame generateInstance(Book book){
        SourceFrame sf = new SourceFrame(book);
        sourceFrameController.initializeController(sf.initialisedBook, sf.tdm, sf.currentTerm, true, sf);
        return sf;
    }
    
    public static SourceFrame generateInstanceWithNoController(Book book){
        
        return new SourceFrame(book);
    }
    
    
    //TODO: add getters : DONE
    
    protected JTextField getSpellingBox(){
        
        return spellingTextBox;
    }
    
    protected JTextArea getDefinitionArea(){
        
        return definitionTextArea;
    }
    
    protected JLabel getPageBox(){
        
        return pageTextBox;
    }
    
    protected JLabel getSuperHeadingTextBox(){
        
        return superHeadingTextBox;
    }
    
    protected JLabel getMiddleHeadingTextBox(){
        
        return middleHeadingTextBox;
    }
    
    protected JTextArea getSubHeadingTextBox(){
        
        return subHeadingTextArea;
    }
    
    protected JLabel getStatusLabel(){
        
        return statusLabel;
    }
    
    protected JLabel getBookLabel(){
        
        return bookLabel;
    }
    
    protected JLabel getHyperlinkInfoLabel(){
        
        return hyperlinkInfoLabel;
    }
    
    protected JTextComponent[] getTextComponents(){
        
        return new JTextComponent[]{
                
            spellingTextBox,
            definitionTextArea
        };
    }
    
    protected SVGIconPanel getSVGBrowserIconPanel(){
        
        return svgBrowserIcon;
    }
    
    protected JPanel getParentPanel(){
        
        return this.parentPanel;
    }
    
    protected JScrollPane getContainerPane(){
        
        return this.containerPane;
    }

    // ADD FUTURE GETTERS HERE
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        parentPanel = new javax.swing.JPanel();
        containerPane = new javax.swing.JScrollPane();
        roundedPanel1 = new frontend.RoundedPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        spellingTextBox = new javax.swing.JTextField();
        roundedButton2 = new frontend.RoundedButton();
        titleLabel = new javax.swing.JLabel();
        definitionTextArea = new javax.swing.JTextArea();
        referenceContainerPanel = new javax.swing.JPanel();
        subHeadingTextArea = new javax.swing.JTextArea();
        superHeadingTextBox = new javax.swing.JLabel();
        superHeadingLabel = new javax.swing.JLabel();
        middleHeadingLabel = new javax.swing.JLabel();
        middleHeadingTextBox = new javax.swing.JLabel();
        subHeadingLabel = new javax.swing.JLabel();
        pageLabel = new javax.swing.JLabel();
        pageTextBox = new javax.swing.JLabel();
        decorativeDefinitionPanel = new frontend.RoundedPanel();
        otherInfoContainerPanel = new javax.swing.JPanel();
        hyperlinkInfoLabel = new javax.swing.JLabel();
        svgBrowserIcon = new frontend.SVGIconPanel();
        decorativeReferencePanel = new frontend.RoundedPanel();
        decorativeOtherInfoPanel = new frontend.RoundedPanel();
        otherInfoHeaderLabel = new javax.swing.JLabel();
        definitionHeaderLabel = new javax.swing.JLabel();
        bookLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        referenceHeaderLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Medical Glossary " + backend.AppConfig.VERSION);
        setBackground(new java.awt.Color(34, 40, 44));
        setResizable(false);
        setSize(new java.awt.Dimension(1200, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        parentPanel.setBackground(new java.awt.Color(44, 62, 80));
        parentPanel.setMaximumSize(new java.awt.Dimension(1200, 700));
        parentPanel.setMinimumSize(new java.awt.Dimension(1200, 700));
        parentPanel.setPreferredSize(new java.awt.Dimension(1200, 700));
        parentPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        containerPane.setBorder(null);
        containerPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        containerPane.setOpaque(false);

        roundedPanel1.setCurrentColor(new java.awt.Color(204, 204, 204));
        roundedPanel1.setOpaque(true);
        roundedPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 660, 30));

        roundedPanel1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 60));

        containerPane.setViewportView(roundedPanel1);

        parentPanel.add(containerPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 182, 680, 210));

        spellingTextBox.setBackground(new java.awt.Color(68, 96, 122));
        spellingTextBox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        spellingTextBox.setForeground(new java.awt.Color(255, 255, 255));
        spellingTextBox.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        spellingTextBox.setText("<Enter Term>");
        spellingTextBox.setCaretColor(new java.awt.Color(255, 255, 255));
        parentPanel.add(spellingTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 680, 40));

        roundedButton2.setForeground(new java.awt.Color(44, 62, 80));
        roundedButton2.setText("PRE RELEASE");
        roundedButton2.setArcSize(20);
        roundedButton2.setCurrentColor(new java.awt.Color(255, 255, 255));
        roundedButton2.setFont(new java.awt.Font("Gill Sans MT Condensed", 1, 14)); // NOI18N
        parentPanel.add(roundedButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 100, 20));

        titleLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Medical Glossary " + backend.AppConfig.VERSION);
        parentPanel.add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 680, -1));

        definitionTextArea.setEditable(false);
        definitionTextArea.setBackground(new java.awt.Color(57, 75, 92));
        definitionTextArea.setColumns(20);
        definitionTextArea.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        definitionTextArea.setForeground(new java.awt.Color(255, 255, 255));
        definitionTextArea.setLineWrap(true);
        definitionTextArea.setRows(5);
        definitionTextArea.setText("---");
        definitionTextArea.setWrapStyleWord(true);
        definitionTextArea.setBorder(null);
        definitionTextArea.setCaretColor(new java.awt.Color(255, 255, 255));
        definitionTextArea.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        definitionTextArea.setFocusable(false);
        parentPanel.add(definitionTextArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 510, 390));

        referenceContainerPanel.setOpaque(false);
        referenceContainerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        subHeadingTextArea.setEditable(false);
        subHeadingTextArea.setBackground(new java.awt.Color(57, 75, 92));
        subHeadingTextArea.setColumns(20);
        subHeadingTextArea.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        subHeadingTextArea.setForeground(new java.awt.Color(255, 255, 255));
        subHeadingTextArea.setLineWrap(true);
        subHeadingTextArea.setRows(5);
        subHeadingTextArea.setText(" -");
        subHeadingTextArea.setWrapStyleWord(true);
        subHeadingTextArea.setBorder(null);
        subHeadingTextArea.setCaretColor(new java.awt.Color(255, 255, 255));
        subHeadingTextArea.setFocusable(false);
        referenceContainerPanel.add(subHeadingTextArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 560, 50));

        superHeadingTextBox.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        superHeadingTextBox.setForeground(new java.awt.Color(255, 255, 255));
        superHeadingTextBox.setText(" -");
        referenceContainerPanel.add(superHeadingTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 560, -1));

        superHeadingLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        superHeadingLabel.setForeground(new java.awt.Color(204, 204, 204));
        superHeadingLabel.setText(" Chapter:");
        referenceContainerPanel.add(superHeadingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 70, -1));

        middleHeadingLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        middleHeadingLabel.setForeground(new java.awt.Color(204, 204, 204));
        middleHeadingLabel.setText(" Major Topic:");
        referenceContainerPanel.add(middleHeadingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 90, -1));

        middleHeadingTextBox.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        middleHeadingTextBox.setForeground(new java.awt.Color(255, 255, 255));
        middleHeadingTextBox.setText(" -");
        referenceContainerPanel.add(middleHeadingTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 560, -1));

        subHeadingLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        subHeadingLabel.setForeground(new java.awt.Color(204, 204, 204));
        subHeadingLabel.setText(" Subtopic:");
        referenceContainerPanel.add(subHeadingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 70, -1));

        pageLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        pageLabel.setForeground(new java.awt.Color(204, 204, 204));
        pageLabel.setText(" Page:");
        referenceContainerPanel.add(pageLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 70, -1));

        pageTextBox.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        pageTextBox.setForeground(new java.awt.Color(255, 255, 255));
        pageTextBox.setText(" -");
        referenceContainerPanel.add(pageTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 220, -1));

        parentPanel.add(referenceContainerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 270, 590, 240));

        javax.swing.GroupLayout decorativeDefinitionPanelLayout = new javax.swing.GroupLayout(decorativeDefinitionPanel);
        decorativeDefinitionPanel.setLayout(decorativeDefinitionPanelLayout);
        decorativeDefinitionPanelLayout.setHorizontalGroup(
            decorativeDefinitionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        decorativeDefinitionPanelLayout.setVerticalGroup(
            decorativeDefinitionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        parentPanel.add(decorativeDefinitionPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 530, 410));

        otherInfoContainerPanel.setOpaque(false);
        otherInfoContainerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        hyperlinkInfoLabel.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        hyperlinkInfoLabel.setForeground(new java.awt.Color(255, 255, 255));
        hyperlinkInfoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hyperlinkInfoLabel.setText("Hyperlinks");
        hyperlinkInfoLabel.setToolTipText("");
        otherInfoContainerPanel.add(hyperlinkInfoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 80, -1));

        try {
            svgBrowserIcon.set_SVG_URL_String("/images/icons/captive-portal-white-NA-55-percent-opaque.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        svgBrowserIcon.setbgSquarePadding(4.0);
        svgBrowserIcon.setIconPosition(frontend.SVGIconPanel.iconPosition.CENTER);
        svgBrowserIcon.setIconSquarePadding(6.0);

        javax.swing.GroupLayout svgBrowserIconLayout = new javax.swing.GroupLayout(svgBrowserIcon);
        svgBrowserIcon.setLayout(svgBrowserIconLayout);
        svgBrowserIconLayout.setHorizontalGroup(
            svgBrowserIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        svgBrowserIconLayout.setVerticalGroup(
            svgBrowserIconLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        otherInfoContainerPanel.add(svgBrowserIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 60, 60));

        parentPanel.add(otherInfoContainerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 550, 590, 130));

        javax.swing.GroupLayout decorativeReferencePanelLayout = new javax.swing.GroupLayout(decorativeReferencePanel);
        decorativeReferencePanel.setLayout(decorativeReferencePanelLayout);
        decorativeReferencePanelLayout.setHorizontalGroup(
            decorativeReferencePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        decorativeReferencePanelLayout.setVerticalGroup(
            decorativeReferencePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        parentPanel.add(decorativeReferencePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 270, 590, 240));

        javax.swing.GroupLayout decorativeOtherInfoPanelLayout = new javax.swing.GroupLayout(decorativeOtherInfoPanel);
        decorativeOtherInfoPanel.setLayout(decorativeOtherInfoPanelLayout);
        decorativeOtherInfoPanelLayout.setHorizontalGroup(
            decorativeOtherInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        decorativeOtherInfoPanelLayout.setVerticalGroup(
            decorativeOtherInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        parentPanel.add(decorativeOtherInfoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 550, 590, 130));

        otherInfoHeaderLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        otherInfoHeaderLabel.setForeground(new java.awt.Color(255, 255, 255));
        otherInfoHeaderLabel.setText("Other Info:");
        parentPanel.add(otherInfoHeaderLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 520, 120, -1));

        definitionHeaderLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        definitionHeaderLabel.setForeground(new java.awt.Color(255, 255, 255));
        definitionHeaderLabel.setText("Definition:");
        parentPanel.add(definitionHeaderLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 120, -1));

        bookLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 16)); // NOI18N
        bookLabel.setForeground(new java.awt.Color(233, 179, 15));
        bookLabel.setText("<Book Name>");
        parentPanel.add(bookLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 240, 480, 20));

        statusLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(255, 255, 255));
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusLabel.setText("Term spelling can not contain some special characters.");
        parentPanel.add(statusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 175, 680, 20));

        referenceHeaderLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        referenceHeaderLabel.setForeground(new java.awt.Color(255, 255, 255));
        referenceHeaderLabel.setText("Reference:");
        parentPanel.add(referenceHeaderLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 90, -1));

        getContentPane().add(parentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bookLabel;
    private javax.swing.JScrollPane containerPane;
    private frontend.RoundedPanel decorativeDefinitionPanel;
    private frontend.RoundedPanel decorativeOtherInfoPanel;
    private frontend.RoundedPanel decorativeReferencePanel;
    private javax.swing.JLabel definitionHeaderLabel;
    private javax.swing.JTextArea definitionTextArea;
    private javax.swing.JLabel hyperlinkInfoLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel middleHeadingLabel;
    private javax.swing.JLabel middleHeadingTextBox;
    private javax.swing.JPanel otherInfoContainerPanel;
    private javax.swing.JLabel otherInfoHeaderLabel;
    private javax.swing.JLabel pageLabel;
    private javax.swing.JLabel pageTextBox;
    private javax.swing.JPanel parentPanel;
    private javax.swing.JPanel referenceContainerPanel;
    private javax.swing.JLabel referenceHeaderLabel;
    private frontend.RoundedButton roundedButton2;
    private frontend.RoundedPanel roundedPanel1;
    private javax.swing.JTextField spellingTextBox;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel subHeadingLabel;
    private javax.swing.JTextArea subHeadingTextArea;
    private javax.swing.JLabel superHeadingLabel;
    private javax.swing.JLabel superHeadingTextBox;
    private frontend.SVGIconPanel svgBrowserIcon;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
