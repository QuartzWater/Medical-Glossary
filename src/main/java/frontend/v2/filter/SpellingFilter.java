/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend.v2.filter;

import frontend.v2.customevent.FilterRejectionEvent;
import frontend.v2.customevent.FilterRejectionListener;
import frontend.v2.customevent.RejectionReason;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author BRIN
 */
public class SpellingFilter extends DocumentFilter{

    private final String invalidCharacters;
    private final List<String> exceptions;
    private final int MAX_CHARACTERS;
    
    public SpellingFilter(String invalidCharacters) {
        
        this(invalidCharacters, Integer.MAX_VALUE, new ArrayList<>());
    }

    public SpellingFilter(String invalidCharacters, int maxChar) {
        this(invalidCharacters, maxChar, new ArrayList<>());
    }
    
    public SpellingFilter(String invalidCharacters, int maxChar, List<String> exceptions) {
        this.invalidCharacters = invalidCharacters;
        this.MAX_CHARACTERS = maxChar;
        this.exceptions = new ArrayList<>();
        this.exceptions.addAll(exceptions);
    }
    
    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        
        if(isValidInput(fb, offset, string)){
            super.insertString(fb, offset, string, attr);
        }
        
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        
        if(isValidInput(fb, length, text)){
            super.replace(fb, offset, length, text, attrs);
        }
        
    }

    @Override
    public void remove(DocumentFilter.FilterBypass fb, int offset, int length) throws BadLocationException {
        
        super.remove(fb, offset, length);
        
    }
    
    private boolean isValidInput(DocumentFilter.FilterBypass fb, int length, String text) throws BadLocationException{
        
        if(text == null || text.isEmpty()){
            return true;
        }
        
        int characters = fb.getDocument().getLength() + text.length() - length;
        if(characters > MAX_CHARACTERS){
            fireRejectionEvent(RejectionReason.MAX_CHARACTERS, text);
            return false;
        }
        
        boolean isInvalid = false;
        for(char invalid : invalidCharacters.toCharArray()){
            
            if(text.indexOf(invalid) != -1){
                
                isInvalid = true;
            }
        }
        
        for(String exception : exceptions){
            if(text.equals(exception)){
                
                isInvalid = false;
            }
        }
        
        if(isInvalid){
            fireRejectionEvent(RejectionReason.INVALID_CHARACTER, text);
        }
        return !isInvalid;
    }
    
    private final List<FilterRejectionListener> listeners = new ArrayList<>();
    
    public void addListener(FilterRejectionListener l){
        listeners.add(l);
    }
    
    public void removeListener(FilterRejectionListener l){
        listeners.remove(l);
    }
    
    public void removeListeners(){
        listeners.clear();
    }
    
    private void fireRejectionEvent(RejectionReason reason, String rejectedTExt){
        FilterRejectionEvent e = new FilterRejectionEvent(this, reason, rejectedTExt);
        for(FilterRejectionListener l : listeners){
            l.rejected(e);
        }
    }
}
