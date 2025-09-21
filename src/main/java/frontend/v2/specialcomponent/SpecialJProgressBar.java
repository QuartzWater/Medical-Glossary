/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend.v2.specialcomponent;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JProgressBar;

/**
 *
 * @author BRIN
 */
public class SpecialJProgressBar extends JProgressBar {
    
    private float gradientOffset = 0f;
    private Color firstColor;
    private Color secondColor;

    public SpecialJProgressBar() {
        super();
        setBorderPainted(false);
        setOpaque(false);
        setValue(50);
        
        firstColor =  new Color(0, 200, 255);
        secondColor = new Color(0, 100, 255);
    }
    
    public void setGradientOffset(float offset) {
        this.gradientOffset = offset % 1f; // keep it in [0,1]
        repaint();
    }
    
    public void setGradientColors(Color firstColor, Color secondColor){
        this.firstColor = firstColor;
        this.secondColor = secondColor;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        
        
        int width = getWidth();
        int height = getHeight();

        // Transparent background
        g2.setColor(new Color(0, 0, 0, 0));
        g2.fillRect(0, 0, width, height);

        // Progress width
        int progressWidth = (int) (width * getPercentComplete());

        if (progressWidth > 0) {
            // Animated gradient
            GradientPaint gradient = new GradientPaint(
                    0, 0, firstColor,
                    width, height, secondColor
            );
            g2.setPaint(gradient);
            g2.fillRoundRect(0, 0, progressWidth, height, height, height);
        }

        g2.dispose();
    }
}
