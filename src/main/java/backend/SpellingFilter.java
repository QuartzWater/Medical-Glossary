/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author BRIN
 */
public class SpellingFilter extends DocumentFilter{
    
    private int maxLength;
    protected boolean maxLengthReached = false;
    
    public SpellingFilter(int maxLength) {
        if (maxLength <= 0) {
            throw new IllegalArgumentException("Max length must be positive.");
        }
        this.maxLength = maxLength;
    }
    
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        // Validate the incoming string
        if(isValidInput(fb, offset, 0, string)) {
            super.insertString(fb, offset, string, attr);
        }
        
        //System.out.println("inserted String: " + string);
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        // Validate the replacement text
        if (isValidInput(fb, offset, length, text)) {
            super.replace(fb, offset, length, text, attrs);
        }
        //System.out.println("replaced String: " + text);
        //System.out.println(length);
    }

    @Override
    public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
        // Allow removal of characters
        super.remove(fb, offset, length);
        //System.out.println("Something removed");
    }
    
    private class validSpellCheck{
        
        private static String[] invalidChar = 
        {
          
            "\\", "/", "|", "?", "*", "$", "!", "@", "#", "%", "^", "<", ">", ":", ";", "\""
        };
     
        
        public static boolean containsInvalidChar(String text){
            for(int i = 0; i < invalidChar.length; i++){
                
                if(text.contains(invalidChar[i])){
                    return true;
                }
            }
            
            return false;
        }
        
       
    }
    
    protected boolean isValidInput(DocumentFilter.FilterBypass fb, int offset, int length, String text) throws BadLocationException {
        if (text == null || text.isEmpty()) {
            return true; // Allow empty input (e.g., when deleting)
        }
        
        
        

        //String builder
        
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
       
        String split1 = currentText.substring(0, offset);
        String split2 = currentText.substring(offset, currentText.length());
        
        if(length > split2.length()){
            
            length = split2.length();
            System.out.println("Length registered was greater than String length on which operation was to be performed, defaulted to length of original String");
        }
        
        String proposedText;
        
        if(length > 0){ // for Replace action
            String secondarySplit1 = split2.substring(0, length);
            String secondarySplit2 = split2.substring(length, split2.length());
            
            proposedText = split1 + text + secondarySplit2;
        }else{ // for insert action
            
            proposedText = split1 + text + split2;
        }
        
        if(proposedText.length() > maxLength){
            maxLengthReached = true;
            return false;
        }
        else{
            maxLengthReached = false;
        }
        //String builder ends
        
        // TODO : ACTUAL SPELLCHECK STARTS HERE ->
        
        if(validSpellCheck.containsInvalidChar(proposedText)){
            return false;
        }
        
        
        return true;
    }
}