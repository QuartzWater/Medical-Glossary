package backend.v2.term;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import backend.v2.data.TermComparator;
import java.awt.font.TextAttribute;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import javax.swing.text.*;
import javax.swing.text.rtf.RTFEditorKit;

public class RTFDocumentUtils {

    public static StyledDocument clone(StyledDocument original) {
        
        try {
            RTFEditorKit editorKit = new RTFEditorKit();
            
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            editorKit.write(out, original, 0, original.getLength());
            
            StyledDocument copy = (StyledDocument) editorKit.createDefaultDocument();
            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
            editorKit.read(in, copy, 0);
            
            return  copy;
        } catch (IOException | BadLocationException e) {
            throw new RuntimeException("Error while copying RTF document!", e);
        } 
    }
    
    
    public static boolean isDifferent(StyledDocument styleDoc1, StyledDocument styleDoc2){
        
        Set<TermComparator.DIFFERENCE_TYPE> differences = TermComparator.compareStyledDocument(styleDoc1, styleDoc2, TermComparator.ComparisionStrength.CONSERVATIVE);
        Iterator<TermComparator.DIFFERENCE_TYPE> it = differences.iterator();
                while(it.hasNext()){
                    System.out.println(it.next().toString());
                }
        return !differences.isEmpty();
    }
    
    public static void sanitize(StyledDocument doc){
        
        Element root = doc.getDefaultRootElement();

        // Iterate through all paragraphs
        for (int i = 0; i < root.getElementCount(); i++) {
            Element paragraph = root.getElement(i);
            AttributeSet attrs = paragraph.getAttributes();

            // Remove run-direction if present (LTR/RTL)
            if (attrs.isDefined(TextAttribute.RUN_DIRECTION)) {
                System.out.println("RUN DIRECTION DEFINED");
                MutableAttributeSet mutable = new SimpleAttributeSet(attrs);
                mutable.removeAttribute(TextAttribute.RUN_DIRECTION);
                ((StyledDocument) doc).setParagraphAttributes(
                        paragraph.getStartOffset(),
                        paragraph.getEndOffset() - paragraph.getStartOffset(),
                        mutable,
                        true
                );
            }

            // Add more filters here if needed
        }

        // Normalize document properties as well
        Object runDir = doc.getProperty(TextAttribute.RUN_DIRECTION);
        if (runDir != null) {
            doc.putProperty(TextAttribute.RUN_DIRECTION, null);
        }
    }
    
    public static void stripNewLines(StyledDocument doc){
        
        try {
            while (doc.getLength() > 0) {
                String text = doc.getText(doc.getLength() - 1, 1);
                if ("\n".equals(text)) {
                    System.out.println("new line detected");
                    doc.remove(doc.getLength() - 1, 1);
                } else {
                    break;
                }
            }
        } catch (BadLocationException ex) {
            System.getLogger(TermComparator.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
