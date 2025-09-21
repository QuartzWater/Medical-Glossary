/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend.v2.controller;

import backend.eventadapter.GranularMouseAdapter;
import frontend.SVGIconPanel;
import frontend.v2.containers.DefinitionContainer;
import frontend.v2.window.ColorPicker;
import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.Utilities;

/**
 *
 * @author BRIN
 */
public class DefinitionContainerController {
    
    private final Color initialColorIconFG;
    private final Color initialColorIconBG;
    
    private static final Color DEFAULT_COLOR1 = new Color(0,0,0);
    private static final Color DEFAULT_COLOR_TRANSPARENT = new Color(0,0,0,0);

    private DefinitionContainer dc;
    
    @Deprecated
    private StyledDocument doc; // no use since all methods now immediately query the definitionPane for its current applied StyledDocument
                                // instead of working on its reference once set (which can potentially get dereferenced when a new term is searched)
    
    SVGIconPanel boldIcon;
    SVGIconPanel italicIcon;
    SVGIconPanel underlineIcon;
    SVGIconPanel colorIcon;
    SVGIconPanel fontfaceIcon;
    
    JTextPane definitionPane;
    
    private Color fgOnCurrentCaret = new Color(255,255,255);
    private Color bgOnCurrentCaret = null;
    
    private int currentCaretDot = 0;
    private int currentCaretMark = 0;
    
    public DefinitionContainerController(DefinitionContainer dc) {
        this.dc = dc;
        
        this.boldIcon = dc.getBoldIcon();
        this.italicIcon = dc.getItalicIcon();
        this.underlineIcon = dc.getUnderlineIcon();
        this.colorIcon = dc.getColorIcon();
        this.fontfaceIcon = dc.getFontFaceIcon();
        
        this.definitionPane = dc.getDefinitionPane();
        
        this.initialColorIconFG = colorIcon.getCurrentFill("foreground");
        this.initialColorIconBG = colorIcon.getCurrentFill("background");
        
        doc = definitionPane.getStyledDocument();
        
        
        
        
        this.boldIcon.addMouseListener(boldGran);
        this.italicIcon.addMouseListener(italicGran);
        this.underlineIcon.addMouseListener(underlineGran);
        
        // the order of adding listeners apparently decides whether the icon freezes or not freezes on hover stage when color picker comes up lmao
        this.colorIcon.addMouseListener(commonEffectsGran);
        this.colorIcon.addMouseListener(specificColorGran);
        
        this.fontfaceIcon.addMouseListener(commonEffectsGran);
        this.fontfaceIcon.addMouseListener(specificFontfaceGran); // fully not implemented yet as of v0.5.0
        
        
        this.definitionPane.addCaretListener(caretListen);
    }
    
    StylesGranularMouseAdapter styleBoldGran = new StylesGranularMouseAdapter(StyleConstants.Bold);
    StylesGranularMouseAdapter styleItalicGran = new StylesGranularMouseAdapter(StyleConstants.Italic);
    StylesGranularMouseAdapter styleUnderlineGran = new StylesGranularMouseAdapter(StyleConstants.Underline);
    
        // --- Inner adapter replacement ---
    private class StylesGranularMouseAdapter extends GranularMouseAdapter {
        private final Object type;

        public StylesGranularMouseAdapter(Object type) {
            this.type = type;
        }

        @Override
        public void actOnMouseRelease(MouseEvent e) {
            StyledDocument doc = dc.getDefinitionPane().getStyledDocument();
            try {
                
                
                int dot = currentCaretDot;
                int mark = currentCaretMark;
                int start = Math.min(dot, mark);
                int end = Math.max(dot, mark);

                // *ALWAYS* CHANGE INPUT ATTRIBUTES 
                MutableAttributeSet inputAttributes = definitionPane.getInputAttributes();
                boolean currentlyOn = attributeIsSet(inputAttributes, type);
                boolean newValue = !currentlyOn;
                if (newValue) {
                    setAttributeValue(inputAttributes, type, true);
                } else {
                    inputAttributes.removeAttribute(type);
                }
                
                
                if (start != end) {
                    // There's a selection -> toggle over the selected range
                    boolean allHave = rangeAllHaveAttribute(doc, start, end, type);
                    if (!allHave) {
                        MutableAttributeSet set = new SimpleAttributeSet();
                        setAttributeValue(set, type, true);
                        // merge so other attributes (italic/underline) remain
                        doc.setCharacterAttributes(start, end - start, set, false);
                    } else {
                        removeAttributeInRange(doc, start, end, type);
                    }
                } else {
                    // Collapsed caret: act on the word or on input attributes
                    int wordStart = Utilities.getWordStart(definitionPane, dot);
                    int wordEnd = Utilities.getWordEnd(definitionPane, dot);

                    if (wordStart == dot || wordEnd == dot) {
                        // caret at word boundary -> toggle input attributes (future typing)
                        
                        // ... //
                        
                        // Myself : This was causing inconsistent input attributes application. 
                        
                    } else {
                        // caret inside a word -> toggle the whole word
                        boolean allHave = rangeAllHaveAttribute(doc, wordStart, wordEnd, type);
                        if (!allHave) {
                            MutableAttributeSet set = new SimpleAttributeSet();
                            setAttributeValue(set, type, true);
                            doc.setCharacterAttributes(wordStart, wordEnd - wordStart, set, false);
                        } else {
                            removeAttributeInRange(doc, wordStart, wordEnd, type);
                        }
                    }
                }
                
                definitionPane.setCaretPosition(end);
                
                
            } catch (BadLocationException ex) {
                System.getLogger(DefinitionContainerController.class.getName())
                      .log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }

    // --- Helpers (put in the same outer class) ---
    private boolean attributeIsSet(AttributeSet as, Object type) {
        if (type == StyleConstants.Bold) return StyleConstants.isBold(as);
        if (type == StyleConstants.Italic) return StyleConstants.isItalic(as);
        if (type == StyleConstants.Underline) return StyleConstants.isUnderline(as);
        return false;
    }

    private void setAttributeValue(MutableAttributeSet mas, Object type, boolean value) {
        if (type == StyleConstants.Bold) StyleConstants.setBold(mas, value);
        else if (type == StyleConstants.Italic) StyleConstants.setItalic(mas, value);
        else if (type == StyleConstants.Underline) StyleConstants.setUnderline(mas, value);
    }

    /**
     * Returns true if every character (by element runs) in [start, end) has the attribute.
     */
    private boolean rangeAllHaveAttribute(StyledDocument doc, int start, int end, Object type) throws BadLocationException {
        int pos = start;
        while (pos < end) {
            Element elem = doc.getCharacterElement(pos);
            AttributeSet as = elem.getAttributes();
            if (!attributeIsSet(as, type)) return false;
            pos = elem.getEndOffset(); // jump by element run
        }
        return true;
    }

    /**
     * Remove an attribute (bold/italic/underline) over [start, end), preserving other attributes.
     * We copy each element run's attributes, remove the key, and replace that run.
     */
    private void removeAttributeInRange(StyledDocument doc, int start, int end, Object type) throws BadLocationException {
        int pos = start;
        while (pos < end) {
            Element elem = doc.getCharacterElement(pos);
            int runStart = Math.max(start, elem.getStartOffset());
            int runEnd = Math.min(end, elem.getEndOffset());
            int runLen = runEnd - runStart;

            MutableAttributeSet copy = new SimpleAttributeSet(elem.getAttributes());
            copy.removeAttribute(type); // remove the key entirely
            doc.setCharacterAttributes(runStart, runLen, copy, true); // replace for this run

            pos = elem.getEndOffset();
        }
    }

    
    private static final Color HOVER_COLOR = new Color(57,75,92);
    private static final Color PRESSED_COLOR = new Color(19,25,31);
    
    private GranularMouseAdapter boldGran = new GranularMouseAdapter(){
        
        @Override
        public void actOnMouseEntry(MouseEvent e){
            
            if(!StyleConstants.isBold(definitionPane.getInputAttributes())){
                boldIcon.setCurrentColor(HOVER_COLOR);
                boldIcon.setBackgroundPainted(true);
                boldIcon.repaint();
            }
        }
        
        @Override
        public void actOnMouseExit(MouseEvent e){

            if(!StyleConstants.isBold(definitionPane.getInputAttributes())){
                boldIcon.setBackgroundPainted(false);
                boldIcon.repaint();
            }
        }
        
        @Override
        public void actOnMousePress(MouseEvent e){
            
            boldIcon.setCurrentColor(PRESSED_COLOR);
            boldIcon.repaint();
        }
        
        @Override
        public void actOnMouseRelease(MouseEvent e){
            boldIcon.setCurrentColor(HOVER_COLOR);
            boldIcon.repaint();
            styleBoldGran.actOnMouseRelease(e);
        }
        
    };
    
    private GranularMouseAdapter italicGran = new GranularMouseAdapter(){
        
        @Override
        public void actOnMouseEntry(MouseEvent e){
            
            if(!StyleConstants.isItalic(definitionPane.getInputAttributes())){
                italicIcon.setCurrentColor(HOVER_COLOR);
                italicIcon.setBackgroundPainted(true);
                italicIcon.repaint();
            }
        }
        
        @Override
        public void actOnMouseExit(MouseEvent e){
            
            if(!StyleConstants.isItalic(definitionPane.getInputAttributes())){
                italicIcon.setBackgroundPainted(false);
                italicIcon.repaint();
            }
        }
        
        @Override
        public void actOnMousePress(MouseEvent e){
            
            italicIcon.setCurrentColor(PRESSED_COLOR);
            italicIcon.repaint();
        }
        
        @Override
        public void actOnMouseRelease(MouseEvent e){
            
            italicIcon.setCurrentColor(HOVER_COLOR);
            italicIcon.repaint();
            styleItalicGran.actOnMouseRelease(e);
            
        }
        
    };
    
    private GranularMouseAdapter underlineGran = new GranularMouseAdapter(){
        
        @Override
        public void actOnMouseEntry(MouseEvent e){
            
            if(!StyleConstants.isUnderline(definitionPane.getInputAttributes())){
                underlineIcon.setCurrentColor(HOVER_COLOR);
                underlineIcon.setBackgroundPainted(true);
                underlineIcon.repaint();
            }
        }
        
        @Override
        public void actOnMouseExit(MouseEvent e){
            
            if(!StyleConstants.isUnderline(definitionPane.getInputAttributes())){
                underlineIcon.setBackgroundPainted(false);
                underlineIcon.repaint();
            }
        }
        
        @Override
        public void actOnMousePress(MouseEvent e){
            underlineIcon.setCurrentColor(PRESSED_COLOR);
            underlineIcon.repaint();
        }
        
        @Override
        public void actOnMouseRelease(MouseEvent e){
            underlineIcon.setCurrentColor(HOVER_COLOR);
            underlineIcon.repaint();
            styleUnderlineGran.actOnMouseRelease(e);
        }
        
    };
    
    private GranularMouseAdapter specificColorGran = new GranularMouseAdapter(){
        
        @Override
        public void actOnMouseRelease(MouseEvent e){
            
            StyledDocument doc = dc.getDefinitionPane().getStyledDocument();
            
            int dot = currentCaretDot;
            int mark = currentCaretMark;
            int start = Math.min(mark, dot);
            int end = Math.max(mark, dot);
            
            Color[] colors = ColorPicker.showDialog(fgOnCurrentCaret, bgOnCurrentCaret);
            
            if(colors == null){
                return;
            }
            
            Color newFG = colors[0];
            Color newBG = colors[1] == null || colors[1].equals(DEFAULT_COLOR_TRANSPARENT) ? null : colors[1];
            
            fgOnCurrentCaret = newFG;
            bgOnCurrentCaret = newBG;
            
            colorIcon.overrideFill("foreground", newFG);
            
            if(newBG == null){
                
                colorIcon.overrideFill("background", initialColorIconBG);
            } else {
                colorIcon.overrideFill("background", newBG);
            }
            
            MutableAttributeSet inputAttrs = definitionPane.getInputAttributes();
            StyleConstants.setForeground(inputAttrs, newFG);
            if (newBG != null) {
                StyleConstants.setBackground(inputAttrs, newBG);
            } else {
                inputAttrs.removeAttribute(StyleConstants.Background);
            }
            
            
            
            if(start != end){
                
                MutableAttributeSet set = new SimpleAttributeSet();
                StyleConstants.setForeground(set, newFG);
                if (newBG != null) StyleConstants.setBackground(set, newBG);
                else set.removeAttribute(StyleConstants.Background);

                doc.setCharacterAttributes(start, end - start, set, false);
            }
            else {
                
                try {
                    // --- Collapsed caret ---
                    int wordStart = Utilities.getWordStart(definitionPane, dot);
                    int wordEnd = Utilities.getWordEnd(definitionPane, dot);
                    
                    if (wordStart == dot || wordEnd == dot) {
                        // caret at boundary -> affect future typing
                        
                    } else {
                        // caret inside word -> color whole word
                        MutableAttributeSet set = new SimpleAttributeSet();
                        StyleConstants.setForeground(set, newFG);
                        if (newBG != null) StyleConstants.setBackground(set, newBG);
                        else set.removeAttribute(StyleConstants.Background);
                        
                        doc.setCharacterAttributes(wordStart, wordEnd - wordStart, set, false);
                    }
                } catch (BadLocationException ex) {
                    System.getLogger(DefinitionContainerController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        }
    };
    
    private GranularMouseAdapter specificFontfaceGran = new GranularMouseAdapter(){
        
        @Override
        public void actOnMouseRelease(MouseEvent e){
            
            StyledDocument doc = dc.getDefinitionPane().getStyledDocument();
            
            System.out.println("NOT AVAILABLE IN V0.5.0");
            
            /* UNCOMMENT THIS CODE BLOCK WHEN FONT CHANGING FEATURE IS FINALIZED!!
            
            int start = definitionPane.getSelectionStart();
            int end = definitionPane.getSelectionEnd();
            int length = end - start;
            AttributeSet currentAttr = doc.getCharacterElement(start).getAttributes();
            MutableAttributeSet newAttr = new SimpleAttributeSet(currentAttr);
            
            // todo own implementation of a FontChooser
            // FONT CHOOSING FEATURE NOT AVAILABLE IN v0.5.0
            
            doc.setCharacterAttributes(start, length, newAttr, true);
            definitionPane.setCaretPosition(end);// So that the selected text is deselected and the changes are immediately visible.
            
            */
        }
    };
    
    private GranularMouseAdapter commonEffectsGran = new GranularMouseAdapter(){
        
        double prevBGSqrPad = 0.0;
        double prevIconSqrPad = 5.0;
        
        @Override
        public void actOnMouseEntry(MouseEvent e){
            SVGIconPanel icon = (SVGIconPanel) e.getSource();
            
            prevIconSqrPad = icon.getIconSquarePadding();
            prevBGSqrPad = icon.getbgSquarePadding();
            
            icon.setBackgroundPainted(true);
            icon.setCurrentColor(HOVER_COLOR);
            icon.repaint();
        }
        
        @Override
        public void actOnMouseExit(MouseEvent e){
            SVGIconPanel icon = (SVGIconPanel) e.getSource();
            
            icon.setBackgroundPainted(false);
            icon.setbgSquarePadding(prevBGSqrPad);
            icon.setIconSquarePadding(prevIconSqrPad);
            icon.repaint();
        }
        
        @Override
        public void actOnMousePress(MouseEvent e){
            SVGIconPanel icon = (SVGIconPanel) e.getSource();
            
            icon.setCurrentColor(PRESSED_COLOR);
            icon.setbgSquarePadding(1.0);
            icon.setIconSquarePadding(7.0);
            icon.repaint();
        }
        
        @Override
        public void actOnMouseRelease(MouseEvent e){
            SVGIconPanel icon = (SVGIconPanel) e.getSource();
            
            icon.setCurrentColor(HOVER_COLOR);
            icon.setbgSquarePadding(prevBGSqrPad);
            icon.setIconSquarePadding(prevIconSqrPad);
            icon.repaint();
        }
        
    };
    
    private final CaretListener caretListen = new CaretListener() {
        @Override
        public void caretUpdate(CaretEvent e) {
            if(!definitionPane.isEditable()){
                return;
            }
            currentCaretDot = e.getDot(); // the place where caret actually is.
            currentCaretMark = e.getMark(); // the place where selection (if any) starts.
            
            System.out.println("CURRENT CARET DOT :" + currentCaretDot);
            
            // if selection is from left to right then currentCaretDot > currentCaretMark.
            // if selection is from right to left then currentCaretDot < currentCaretMark.
            
            if(currentCaretDot == currentCaretMark){
                
                int prevElementPos = 0;
                if(currentCaretDot > 0){
                    prevElementPos = Math.abs(currentCaretDot - 1);
                }
                
                Element element = dc.getDefinitionPane().getStyledDocument().getCharacterElement(prevElementPos);
                
                if(element == null){
                    return;
                }
                AttributeSet currAttr = element.getAttributes();
                
                MutableAttributeSet inputAttr = definitionPane.getInputAttributes();
                StyleConstants.setForeground(inputAttr, StyleConstants.getForeground(currAttr));
                
                
                updateIcons(currAttr);
            }
        }
    };
    
    
    private void updateIcons(AttributeSet currAttr){
        
        boolean boldOnCurrentCaret = StyleConstants.isBold(currAttr);
        boolean italicOnCurrentCaret = StyleConstants.isItalic(currAttr);
        boolean underlineOnCurrentCaret = StyleConstants.isUnderline(currAttr);
        
        System.out.println(boldOnCurrentCaret);
        
        if(boldIcon.isBackgroundPainted() != boldOnCurrentCaret){
            boldIcon.setBackgroundPainted(boldOnCurrentCaret);
            boldIcon.repaint();
        }
        
        if(italicIcon.isBackgroundPainted() != italicOnCurrentCaret){
            italicIcon.setBackgroundPainted(italicOnCurrentCaret);
            italicIcon.repaint();
        }
        
        if(underlineIcon.isBackgroundPainted() != underlineOnCurrentCaret){
            underlineIcon.setBackgroundPainted(underlineOnCurrentCaret);
            underlineIcon.repaint();
        }
        MutableAttributeSet inputSet = definitionPane.getInputAttributes();
        fgOnCurrentCaret = StyleConstants.getForeground(inputSet);
        if(fgOnCurrentCaret == null){
            fgOnCurrentCaret = DEFAULT_COLOR1;
        }
        
        colorIcon.overrideFill("foreground", fgOnCurrentCaret);
        bgOnCurrentCaret = StyleConstants.getBackground(inputSet);
        
        if(bgOnCurrentCaret == null || bgOnCurrentCaret.equals(DEFAULT_COLOR1) || bgOnCurrentCaret.equals(DEFAULT_COLOR_TRANSPARENT)){
            
            colorIcon.overrideFill("background", initialColorIconBG);
            bgOnCurrentCaret = null;// defaults to null since the defaults are NOT beings painted anyways.
        }else{
            colorIcon.overrideFill("background", bgOnCurrentCaret);
        }
        
    }
}
