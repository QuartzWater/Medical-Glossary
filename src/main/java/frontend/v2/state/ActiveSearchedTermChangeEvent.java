/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend.v2.state;

import backend.v2.term.AdvancedTerm;
import java.util.EventObject;

/**
 *
 * @author BRIN
 */
public class ActiveSearchedTermChangeEvent extends EventObject{
    
    private final AdvancedTerm prevAdvTerm;
    private final AdvancedTerm newAdvTerm;

    public ActiveSearchedTermChangeEvent(Object source, AdvancedTerm prevAdvTerm, AdvancedTerm newAdvTerm) {
        super(source);
        this.prevAdvTerm = prevAdvTerm;
        this.newAdvTerm = newAdvTerm;
    }

    public AdvancedTerm getPrevAdvTerm() {
        return prevAdvTerm;
    }

    public AdvancedTerm getNewAdvTerm() {
        return newAdvTerm;
    }
    
}
