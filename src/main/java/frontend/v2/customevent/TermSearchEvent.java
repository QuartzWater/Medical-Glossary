/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend.v2.customevent;

import backend.v2.term.AdvancedTerm;
import java.util.EventObject;

/**
 *
 * @author BRIN
 */
public class TermSearchEvent extends EventObject {
    
    private final AdvancedTerm foundTerm;
    
    public TermSearchEvent(Object source, AdvancedTerm term) {
        super(source);
        this.foundTerm = term;
        
    }
    
    public AdvancedTerm getTerm(){
        return foundTerm;
    }
    
}
