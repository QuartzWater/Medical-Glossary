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
import java.awt.image.BufferedImage;

/**
 *
 * @author BRIN
 */
public class RoundedPanel extends javax.swing.JPanel{
    
    private Color DEFAULT_COLOR = new Color(76, 175, 80); // Green
    private Color HOVER_COLOR = new Color(69, 160, 73); // Darker green on hover
    private Color PRESSED_COLOR = new Color(62, 142, 65); // Even darker green on press
    
    private Color currentColor;
    private int arcSize = 35; // Controls the roundness of the corners
    private final float shadowSize = 4f; // Shadow offset4
    
    private Graphics2D g2d;
    
    public RoundedPanel() {
        super();
        this.currentColor = new Color(57,75,92);
        setOpaque(false); // Make sure the button is transparent so we can draw our custom shape
        
        setPreferredSize(new Dimension(50, 50));
        
    }
    
        
    public void setArcSize(int size){
        this.arcSize = size;
    }
    
    public int getArcSize(){
        return arcSize;
    }
    
    public void setCurrentColor(Color currentColor){
        
        this.currentColor = currentColor;
    }
    
    public Color getCurrentColor(){
        
        return this.currentColor;
    }
    
    public void callRepaint(){
     super.repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        

        int width = getWidth() - 1;
        int height = getHeight() -1 ;

        // Draw the main button shape on top of the shadow
        g2d.setColor(currentColor);
        g2d.fill(new RoundRectangle2D.Double(0, 0, width, height, arcSize, arcSize));
        
        

        g2d.dispose();
        super.paintComponent(g2d); // Call the super method to draw the text
    }
}
