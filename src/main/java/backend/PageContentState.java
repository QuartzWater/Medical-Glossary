/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import javax.swing.JComboBox;
import javax.swing.JComponent;

/**
 *
 * @author BRIN
 */
public final class PageContentState { // This class is not extensible
    
    public static enum StateType {
        NOT_FOUND,
        SINGULAR_ENTRY,
        MULTIPLE_ENTRIES
    }
    
    private final JComponent jc;
    private StateType currentState;
    
    public PageContentState(JComponent jc){
        this.jc = jc;
        this.currentState = StateType.NOT_FOUND;
    }
    
    public PageContentState(JComponent jc, StateType sType){
        
        this(jc);
        this.currentState = sType;
    }
    
    public void setCurrentState(StateType stype){
        
        this.currentState = stype;
    }
    
    public boolean isFound(){
        
        return currentState != StateType.NOT_FOUND;
    }
    
    public boolean isMultiFound(){
        
        return currentState == StateType.MULTIPLE_ENTRIES;
    }
    
    public StateType getCurrentState(){
        
        return currentState;
    }
    
    public JComponent getJComponent(){
        
        return this.jc;
    }
}
