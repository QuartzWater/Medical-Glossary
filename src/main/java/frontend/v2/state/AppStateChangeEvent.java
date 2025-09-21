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
public class AppStateChangeEvent extends EventObject{
    
    private final AppState.Type prevType;
    private final AppState.Type newType;
    
    public AppStateChangeEvent(Object source, AppState.Type prevType, AppState.Type newType) {
        super(source);
        this.prevType = prevType;
        this.newType = newType;
    }

    public AppState.Type getNewType() {
        return newType;
    }

    public AppState.Type getPrevType() {
        return prevType;
    }
}
