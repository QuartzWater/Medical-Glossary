/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend.v2.state;

import java.util.EventObject;

/**
 *
 * @author BRIN
 */
public class InputTermChangeEvent extends EventObject{
    
    private final String prevTerm;
    private final String newTerm;

    public InputTermChangeEvent(Object source, String prevTerm, String newTerm) {
        super(source);
        this.prevTerm = prevTerm;
        this.newTerm = newTerm;
    }

    public String getPrevTerm() {
        return prevTerm;
    }

    public String getNewTerm() {
        return newTerm;
    }
    
}
