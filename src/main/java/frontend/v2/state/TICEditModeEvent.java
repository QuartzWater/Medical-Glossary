/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend.v2.state;

import frontend.v2.containers.ContentProcessor;
import frontend.v2.containers.TermInfoContainer;
import java.util.EventObject;

/**
 *
 * @author BRIN
 */
public class TICEditModeEvent extends  EventObject{
    
    private final boolean isEditActive;
    private final TermInfoContainer reference;
    private final ContentProcessor activePanel;

    public TICEditModeEvent(Object source, boolean isEditActive, TermInfoContainer reference, ContentProcessor activePanel) {
        super(source);
        this.isEditActive = isEditActive;
        this.reference = reference;
        this.activePanel = activePanel;
    }

    public ContentProcessor getActivePanel() {
        return activePanel;
    }

    public TermInfoContainer getReference() {
        return reference;
    }

    public boolean isEditActive() {
        return isEditActive;
    }

    
    
    
}
