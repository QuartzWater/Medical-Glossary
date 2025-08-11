/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import frontend.RoundedButton;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author BRIN
 */
public class State {
    
    public static enum ComponentState {
        DEFAULT_AND_VALID,
        NON_DEFAULT_AND_INVALID,
        NON_DEFAULT_AND_VALID,
        DEFAULT_AND_INVALID
    }
    
    private ComponentState currentState;
    private final JComponent jcomp;
    
    public State(JComponent jcomp){
        
        this.jcomp = jcomp;
        this.currentState = ComponentState.DEFAULT_AND_VALID;
    }
    
    public void setState(ComponentState state){
        
        this.currentState = state;
    }
    
    public boolean isDefault(){
        
        return (currentState == ComponentState.DEFAULT_AND_INVALID || currentState == ComponentState.DEFAULT_AND_VALID);
    }
    
    public boolean isValid(){
        
        return (currentState == ComponentState.DEFAULT_AND_VALID || currentState == ComponentState.NON_DEFAULT_AND_VALID);
    }
    
    public ComponentState getState(){
        
        return this.currentState;
    }
    
    public boolean hasFocus(){
        
        return jcomp.hasFocus();
    }
    
    public boolean isBlank(){
        
        if(jcomp instanceof JTextField || jcomp instanceof JTextArea){
            
            JTextComponent textComp = (JTextComponent) jcomp;
            return textComp.getText().trim().isEmpty();
            
        }
        
        return false;
    }
    
    
    
}
