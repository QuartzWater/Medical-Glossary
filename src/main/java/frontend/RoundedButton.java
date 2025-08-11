/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JButton;

/**
 *
 * @author BRIN
 */
public class RoundedButton extends JButton{
    

    public static final Color ULTIMATE_DEFAULT = new Color(27,36,45);
    
    // Define the colors for different states of the button
    private Color DEFAULT_COLOR = new Color(76, 175, 80); // Green
    private Color HOVER_COLOR = new Color(69, 160, 73); // Darker green on hover
    private Color PRESSED_COLOR = new Color(62, 142, 65); // Even darker green on press
    
    private Color DISABLED_COLOR = new Color(57,75,92);

    private Color currentColor;
    private final int arcSize = 45; // Controls the roundness of the corners
    private final float shadowSize = 4f; // Shadow offset
    
    public RoundedButton(){
        
        this("RoundedButton");
    }

    public RoundedButton(String text) {
        super(text);
        this.currentColor = DEFAULT_COLOR;
        setOpaque(false); // Make sure the button is transparent so we can draw our custom shape
        setContentAreaFilled(false); // Do not draw the default background
        setBorderPainted(false); // Do not paint the default border
        setFocusPainted(false); // Do not paint focus border

        // Set the text and font
        setForeground(Color.WHITE);
        setFont(new Font("SansSerif", Font.BOLD, 16));
        setPreferredSize(new Dimension(150, 50));

        
    }
    
    
    public void setDefaultColor(Color defaultColor){
        DEFAULT_COLOR = defaultColor;
        currentColor = defaultColor;
        
       
    }
    
    public void setHoverColor(Color hoverColor){
        HOVER_COLOR = hoverColor;
        
    }
    
    public void setPressedColor(Color pressedColor){
        PRESSED_COLOR = pressedColor;
        
    }
    
    public void setCurrentColor(Color currColor){
        
        this.currentColor = currColor;
    }
    
    public void setDisabledColor(Color disabledColor){
        
        this.DISABLED_COLOR = disabledColor;
    }
    
    public Color getDefaultColor(){
        return DEFAULT_COLOR;
    }
    
    public Color getHoverColor(){
        return HOVER_COLOR;
    }
    
    public Color getPressedColor(){
        return PRESSED_COLOR;
    }
    
    public void callRepaint(){
     super.repaint();
    }
    
   

    
    /**
     * Overrides the paintComponent method to draw a custom rounded rectangle background.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        

        int width = getWidth() - 1;
        int height = getHeight() -1 ;

       
        
        // Draw the main button shape on top of the shadow
        if(isEnabled()){
            g2.setColor(currentColor);
        }
        else{
            g2.setColor(DISABLED_COLOR);
        }
        
        g2.fill(new RoundRectangle2D.Double(0, 0, width, height, arcSize, arcSize));
        
        

        g2.dispose();
        super.paintComponent(g); // Call the super method to draw the text
    }

    /**
     * Overrides getPreferredSize to ensure the button has a consistent size.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 50);
    }
}

