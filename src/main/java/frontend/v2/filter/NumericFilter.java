/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend.v2.filter;

/**
 *
 * @author BRIN
 */
import frontend.v2.customevent.FilterRejectionEvent;
import frontend.v2.customevent.FilterRejectionListener;
import frontend.v2.customevent.RejectionReason;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumericFilter extends DocumentFilter {

    private List<FilterRejectionListener> listeners = new ArrayList<>();
    
    private int maxLength;
    public NumericFilter(int maxLength) {
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

    protected boolean isValidInput(DocumentFilter.FilterBypass fb, int offset, int length, String text) throws BadLocationException {
        if (text == null || text.isBlank()) {
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
        
        //String builder ends
        
        /*
        String proposedText = currentText.substring(0, offset) + text + currentText.substring(offset + (text.length() - 1 < 0 ? 0 : text.length() - 1)); // Simplified for insert, will be replaced for replace

        // For 'replace' operation, 'length' determines how many characters are being replaced
        // This is a more accurate way to get the proposed text for both insert and replace
        StringBuilder sb = new StringBuilder(fb.getDocument().getLength() + text.length() - (offset + text.length() > fb.getDocument().getLength() ? 0 : text.length()));
        sb.append(fb.getDocument().getText(0, offset));
        sb.append(text);
        if (offset + length < fb.getDocument().getLength()) {
            sb.append(fb.getDocument().getText(offset + length, fb.getDocument().getLength() - (offset + length)));
        }
        proposedText = sb.toString();
        
*/

        // Check if all characters are digits
        for (char c : text.toCharArray()) {
            if (!Character.isDigit(c)) {
                fireRejection(RejectionReason.NON_NUMBER, "ONLY DIGITS ALLOWED", text);
                return false; // Not a digit
            }
        }

        // Check for '0' as the first character
        if (offset == 0 && text.startsWith("0") && proposedText.length() == 1) {
            // If it's the very first character and it's '0', disallow it
            fireRejection(RejectionReason.FIRST_DIGIT_IS_ZERO, "FIRST DIGIT CAN NOT BE ZERO", text);
            return false;
        }
        
        if(proposedText.length() > maxLength){
            fireRejection(RejectionReason.MAX_CHARACTERS, "MAXIMUM " + Integer.toString(maxLength) + " DIGITS ALLOWED", text);
            return false;
        }
        
        

        // If the text becomes empty after deletion and retyping, ensure '0' isn't the first char.
        // This handles cases where a user deletes all content and then tries to type '0'
        if (proposedText.length() > 0 && proposedText.startsWith("0")) {
            // If the *entire* proposed text starts with '0' AND it's not the only character
            // (i.e., someone pasted "0123" when the field was empty), dont allow it.
            // But if they typed '0' as the first char for an empty field, disallow.
            if (offset == 0 && proposedText.length() == 1) { // Means they typed '0' into an empty field
                fireRejection(RejectionReason.FIRST_DIGIT_IS_ZERO, "FIRST DIGIT CAN NOT BE ZERO", text);
                return false;
            }
        }


        return true;
    }
    
    public void addListener(FilterRejectionListener l){
        listeners.add(l);
    }
    
    public void removeListener(FilterRejectionListener l){
        listeners.remove(l);
    }
    
    public void removeListeners(){
        listeners.clear();
    }
    
    private void fireRejection(RejectionReason reason, String rejectedText){
        FilterRejectionEvent e = new FilterRejectionEvent(this, reason, rejectedText);
        for(FilterRejectionListener l : listeners){
            l.rejected(e);
        }
    }
    
    private void fireRejection(RejectionReason reason, String reasonString, String rejectedText){
        FilterRejectionEvent e = new FilterRejectionEvent(this, reason, reasonString, rejectedText);
        for(FilterRejectionListener l : listeners){
            l.rejected(e);
        }
    }
}