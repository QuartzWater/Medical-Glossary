/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frontend.v2.containers;

import frontend.RoundedButton;
import frontend.v2.controller.SpellingContainerController;
import frontend.v2.specialcomponent.SpecialJProgressBar;
import frontend.v2.state.AppState;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

/**
 *
 * @author BRIN
 */
public class SpellingContainer extends javax.swing.JPanel {
    
    public static final String  DEFAULT_TEXT = "<Enter spelling here>";
    
    private Color[] gradientColor = {};
    private float[] gradientFractions = {};
    
    private Graphics2D g2d;
    SpellingContainerController initialize;
    

    /**
     * Creates new form SpellingContainer
     */
    public SpellingContainer(TermInfoContainer tic) {
        initComponents();
        setPreferredSize(new java.awt.Dimension(1260, 50));
        spellingBox.setText(DEFAULT_TEXT);
        
        initialize = SpellingContainerController.intialize(this);
        tic.registerSpellingContainerController(initialize);
        
        
    }
    
    public SpellingContainerController getController(){
        return initialize;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spellingBox = new javax.swing.JTextField();
        msgLabel = new javax.swing.JLabel();
        changeBookButton = new frontend.RoundedButton();
        progressBar = new frontend.v2.specialcomponent.SpecialJProgressBar();

        setBackground(new java.awt.Color(15, 20, 25));
        setMinimumSize(new java.awt.Dimension(1260, 50));
        setPreferredSize(new java.awt.Dimension(1260, 50));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spellingBox.setBackground(new java.awt.Color(15, 20, 25));
        spellingBox.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        spellingBox.setForeground(new java.awt.Color(255, 255, 255));
        spellingBox.setText("jTextField1");
        spellingBox.setBorder(null);
        spellingBox.setCaretColor(new java.awt.Color(255, 255, 255));
        add(spellingBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 770, 40));

        msgLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        msgLabel.setForeground(new java.awt.Color(153, 255, 153));
        msgLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        add(msgLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 200, 20));

        changeBookButton.setText("Change Book");
        changeBookButton.setArcSize(15);
        add(changeBookButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 1, 170, 48));

        progressBar.setForeground(new java.awt.Color(255, 204, 204));
        progressBar.setValue(0);
        progressBar.setPreferredSize(new java.awt.Dimension(200, 4));
        add(progressBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 35, 200, 5));
    }// </editor-fold>//GEN-END:initComponents

    public JTextField getSpellingBox() {
        return spellingBox;
    }

    public JLabel getMsgLabel() {
        return msgLabel;
    }

    public SpecialJProgressBar getMsgProgressBar() {
        return progressBar;
    }

    public RoundedButton getChangeBookButton() {
        return changeBookButton;
    }
    
    
    
    private int arcSize = 20;

    @Override
    protected void paintComponent(Graphics g) {
        g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        

        int width = getWidth() - 1;
        int height = getHeight() -1 ;

        // Draw the main button shape on top of the shadow
        g2d.setColor(this.getBackground());
        
        
        g2d.fill(new RoundRectangle2D.Double(0, 0, width - 180 , height, arcSize, arcSize));
        
        

        g2d.dispose();
        super.paintComponent(g2d); // Call the super method to draw the text
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private frontend.RoundedButton changeBookButton;
    private javax.swing.JLabel msgLabel;
    private frontend.v2.specialcomponent.SpecialJProgressBar progressBar;
    private javax.swing.JTextField spellingBox;
    // End of variables declaration//GEN-END:variables
}
