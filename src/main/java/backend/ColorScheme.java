/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.awt.Color;

/**
 *
 * @author BRIN
 */
public class ColorScheme{
    
    private final Color DEFAULT_COLOR;
    private final Color HOVER_COLOR;
    private final Color PRESSED_COLOR;
    private final Color DISABLED_COLOR;
    
    public ColorScheme(Color defaultColor, Color hoverColor, Color pressedColor, Color disabledColor){
        
        this.DEFAULT_COLOR = defaultColor;
        this.HOVER_COLOR = hoverColor;
        this.PRESSED_COLOR = pressedColor;
        this.DISABLED_COLOR = disabledColor;
    }
    
    public Color getDefaultColor(){
        
        return this.DEFAULT_COLOR;
    }
    
    public Color getHoverColor(){
        
        return this.HOVER_COLOR;
    }
    
    public Color getPressedColor(){
        
        return this.PRESSED_COLOR;
    }
    
    public Color getDisabledColor(){
        
        return this.DISABLED_COLOR;
    }
    
}
