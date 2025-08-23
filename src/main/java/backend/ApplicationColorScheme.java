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
public class ApplicationColorScheme{
    
    private final Color[] colorScheme;
    
    public ApplicationColorScheme(Color innermostbg, Color middlebg1, Color middlebg2, Color outermostbg) {
        
        colorScheme = new Color[]{
          
            innermostbg,middlebg1,middlebg2,outermostbg
        };
    }
    
    public Color getInnermostBackground(){
        
        return colorScheme[0];
    }
    
    public Color getFirstMiddleBackground(){
        
        return colorScheme[1];
    }
    
    public Color getSecondMiddleBackground(){
        
        return colorScheme[2];
    }
    
    public Color getOutermostBackground(){
        
        return colorScheme[3];
    }
}
