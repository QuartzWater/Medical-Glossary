/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.eventadapter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 *
 * @author BRIN
 */
public class GranularMouseAdapter extends MouseAdapter{
    
    private boolean enableMouseEntry = true;
    private boolean enableMouseExit = true;
    private boolean enableMousePress = true;
    private boolean enableMouseRelease = true;
    private boolean enableMouseClick = true;
    private boolean enableMouseDrag = true;
    private boolean enableMouseSimpleMovement = true;
    private boolean enableMouseWheelMotion = true;
    
    public GranularMouseAdapter(){
        
    }
    
    public void setCanRespondToHover(boolean flag){
        enableMouseEntry = flag;
        enableMouseExit = flag;
    }
    
    public void setCanRespondToMouseEntry(boolean flag){
        enableMouseEntry = flag;
    }
    
    public void setCanRespondToMouseExit(boolean flag){
        enableMouseExit = flag;
    }
    
    public void setCanRespondToClick(boolean flag){
        enableMouseClick = flag;
    }
    
    public void setCanRespondToPress_Release(boolean flag){
        enableMousePress = flag;
        enableMouseRelease = flag;
    }
    
    public void setCanRespondToPress(boolean flag){
        enableMousePress = flag;
        
    }
    
    public void setCanRespondToRelease(boolean flag){
        enableMouseRelease = flag;
    }
        
    public void setCanRespondToDrag(boolean flag){
        enableMouseDrag = flag;
    }
    
    public void setCanRespondToSimpleMovement(boolean flag){
        enableMouseSimpleMovement = flag;
    }
    
    public void setCanRespondToWheelMotion(boolean flag){
        enableMouseWheelMotion = flag;
    }
    
    
    @Override
    public void mouseEntered(MouseEvent e){
        if(enableMouseEntry)
            actOnMouseEntry(e);
    }
    
    @Override
    public void mouseExited(MouseEvent e){
        if(enableMouseExit)
            actOnMouseExit(e);
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        if(enableMousePress)
            actOnMousePress(e);
    }
    
    @Override
    public void mouseReleased(MouseEvent e){
        if(enableMouseRelease)
            actOnMouseRelease(e);
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
        if(enableMouseClick)
            actOnMouseClick(e);
    }
    
    @Override
    public void mouseDragged(MouseEvent e){
        if(enableMouseDrag)
            actOnMouseDrag(e);
    }
    
    @Override
    public void mouseMoved(MouseEvent e){
        if(enableMouseSimpleMovement)
            actOnMouseMovement(e);
    }
    
    @Override 
    public void mouseWheelMoved(MouseWheelEvent e){
        if(enableMouseWheelMotion)
            actOnMouseWheelMovement(e);
    }
    
    // It is recommended to Override these methods.
    
    public void actOnMouseEntry(MouseEvent e){
        
    }
    
    public void actOnMouseExit(MouseEvent e){
        
    }
    
    public void actOnMousePress(MouseEvent e){
        
    }
    
    public void actOnMouseRelease(MouseEvent e){
        
    }
    
    public void actOnMouseClick(MouseEvent e){
        
    }
    
    public void actOnMouseDrag(MouseEvent e){
        
    }
    
    public void actOnMouseMovement(MouseEvent e){
        
    }
    
    public void actOnMouseWheelMovement(MouseWheelEvent e){
        
    }
}
