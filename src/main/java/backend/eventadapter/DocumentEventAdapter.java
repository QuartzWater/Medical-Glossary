/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.eventadapter;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author BRIN
 */
public class DocumentEventAdapter implements DocumentListener{
    
    private boolean shouldActOnInsert = true;
    private boolean shouldActOnRemove = true;
    private boolean shouldActOnChange = true;
    
    private boolean shouldAct = true;
    
    
    public void disableInsert(){
        this.shouldActOnInsert = false;
    }
    
    public void enableInsert(){
        this.shouldActOnInsert = true;
    }
    
    public void disableRemove(){
        this.shouldActOnRemove = false;
    }
    
    public void enableRemove(){
        this.shouldActOnRemove = true;
    }
    
    public void disableChange(){
        this.shouldActOnChange = false;
    }
    
    public void enableChange(){
        this.shouldActOnChange = true;
    }
    
    public void switchOFF(){
        this.shouldAct = false;
    }
    
    public void switchON(){
        this.shouldAct = true;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        
        if(shouldActOnInsert && shouldAct){
            actOnInsert(e);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if(shouldActOnRemove && shouldAct){
            actOnRemove(e);
        }
    }
    
    @Override
    public void changedUpdate(DocumentEvent e) {
        if(shouldActOnChange && shouldAct){
            actOnChange(e);
        }
    }
    
    public void actOnInsert(DocumentEvent e){}
    
    public void actOnRemove(DocumentEvent e){}
    
    public void actOnChange(DocumentEvent e){}
    
}
