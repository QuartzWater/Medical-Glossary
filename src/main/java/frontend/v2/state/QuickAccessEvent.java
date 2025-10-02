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
public class QuickAccessEvent extends EventObject{
    
    public enum TYPE {
        SEARCH,
        CREATE
    }
    
    private final String selection;
    private final TYPE type;
    
    public QuickAccessEvent(Object source, String selection, TYPE type) {
        super(source);
        this.selection = selection;
        this.type = type;
    }

    public String getSelection() {
        return selection;
    }

    public TYPE getType() {
        return type;
    }
    
}
