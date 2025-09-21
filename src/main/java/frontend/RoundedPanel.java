/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.beans.BeanProperty;

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
    
    private double bgPaddingX;
    private double bgPaddingY;
    private double bgSquarepadding;
    
    private boolean linearGradientPaint = false;
    
    private float[] positions;
    private Color[] gradientColors;
    
    private float lgpStartX = 0.0f;
    private float lgpStartY = 0.0f;
    private float lgpEndX = 0.0f;
    private float lgpEndY = 0.0f;
    
    public RoundedPanel() {
        super();
        this.currentColor = new Color(57,75,92);
        bgPaddingX = 0;
        bgPaddingY = 0;
        bgSquarepadding = 0;
        setOpaque(false); // Make sure the button is transparent so we can draw our custom shape
        
        setPreferredSize(new Dimension(50, 50));
        
        useDeafultLGPPositions();
    }
    
    public void setArcSize(int size){
        this.arcSize = size;
    }
    
    public void setCurrentColor(Color currentColor){
        
        this.currentColor = currentColor;
    }
    
    public void setSquarePadding(double sqrPad){
        
        this.bgSquarepadding = sqrPad;
        this.bgPaddingX = sqrPad;
        this.bgPaddingY = sqrPad;
    }
    
    public void setRectangularPadding(double [] pad){
        
        if(pad.length > 2 || pad.length < 2){

            throw new IllegalArgumentException("Padding array size can not be greater than 2 or less than 2.");
        }
        
        this.bgPaddingX = pad[0];
        this.bgPaddingY = pad[1];
    }
    
    @BeanProperty(preferred = true, visualUpdate = true, description
            = "Defines the degree of roundness for background.")
    public int getArcSize(){
        return arcSize;
    }
    
    @BeanProperty(preferred = true, visualUpdate = true, description
            = "Background color of the component.")
    public Color getCurrentColor(){
        
        return this.currentColor;
    }
    
    @BeanProperty(preferred = true, visualUpdate = true, description
            = "Defines same amount of background padding for both x and y directions.")
    public double getSquarePadding(){
        
        return this.bgSquarepadding;
    }
    
    @BeanProperty(preferred = true, visualUpdate = true, description
            = "Defines different background padding for x and y directions.")
    public double[] getRectangularPadding(){
        
        return new double[]{bgPaddingX, bgPaddingY};
    }
    
    public void callRepaint(){
     super.repaint();
    }
    
    public final void definePositionsForLGP(float startX, float startY, float endX, float endY){
        this.lgpStartX = startX;
        this.lgpStartY = startY;
        this.lgpEndX = endX;
        this.lgpEndY = endY;
    }
    
    public final void useDeafultLGPPositions(){
        this.lgpStartX = 0.0f;
        this.lgpStartY = 0.0f;
        this.lgpEndX = (float) getPreferredSize().getWidth();
        this.lgpEndY = (float) getPreferredSize().getHeight();
        
    }
    
    public void enableLinearGradientPaint(float[] positions, Color[] gradientColors){
        this.linearGradientPaint = true;
        this.positions = positions;
        this.gradientColors = gradientColors;
    }
    
    public void disableLinearGradientPaint(){
        this.linearGradientPaint = false;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        
        g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        

        double width = getWidth() - (2 * bgPaddingX);
        double height = getHeight() - (2 * bgPaddingY) ;

        // Draw the main button shape on top of the shadow
        g2d.setColor(currentColor);
        
        if(linearGradientPaint){
            LinearGradientPaint lgp = new LinearGradientPaint(lgpStartX, lgpStartY, lgpEndX, lgpEndY, positions, gradientColors, MultipleGradientPaint.CycleMethod.NO_CYCLE);
            g2d.setPaint(lgp);
        }
        
        g2d.fill(new RoundRectangle2D.Double(bgPaddingX, bgPaddingY, width, height, arcSize, arcSize));
        
        g2d.dispose();
        super.paintComponent(g2d); // Call the super method to draw the text
    }
    
    // *********************** RESETTING ANNOTATIONS ************************* //
    
    
    @BeanProperty(hidden = true, description
            = "Use setCurrentColor to change the background. There is no use of changing Foreground for this component.")
    @Override
    public void setForeground(Color setColor){
        
        super.setForeground(setColor);
    }
    
    @BeanProperty(hidden =  true,  description
            = "The foreground of this component.")
    @Override
    public Color getForeground(){
        
        return super.getForeground();
    };
    
    @BeanProperty(hidden = true, description
            = "Not useful method for this class. Use setCurrentColor to set Background of this component.")
    @Override
    public void setBackground(Color toSet){
        super.setBackground(toSet);
    }
    
    @BeanProperty(hidden = true, description
            = "The background of this component.")
    
    @Override
    public Color getBackground(){
        
        return super.getBackground();
    }
    
    @BeanProperty(hidden = true, description
            = "Calls setBorder method of superclass. Not particularly useful for this component.")
    @Override
    public void setBorder(javax.swing.border.Border border){
        super.setBorder(border);
    }
    
    @BeanProperty(hidden = true, description
            = "Calls getBorder method of superclass. Not particularly useful for this component.")
    @Override
    public javax.swing.border.Border getBorder(){
        return super.getBorder();
    }
    
    
    @BeanProperty(hidden = true, description
            = "Calls setToolTipText method of superclass. Not particularly useful for this component.")
    @Override
    public void setToolTipText(String toSet){
        super.setToolTipText(toSet);
    }
    
    @BeanProperty(hidden = true, description
            = "Calls getToolTipText method of superclass. Not particularly useful for this component.")
    @Override
    public String getToolTipText(){
        return super.getToolTipText();
    }
}
