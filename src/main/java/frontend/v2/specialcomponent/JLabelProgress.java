/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend.v2.specialcomponent;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import javax.swing.Timer;

/**
 *
 * @author BRIN
 */
public class JLabelProgress {
    
    private JPanel parent;
    private JLabel label;
    private SpecialJProgressBar bar;
    private FontMetrics fm;
    
    public enum MSG_TYPE{
        INVALID,
        VALID,
        DEFAULT
    }
    
    public JLabelProgress(JPanel parent, JLabel label, SpecialJProgressBar bar) {
        refreshReferences(parent, label, bar);
    }
    
    public final void refreshReferences(JPanel parent, JLabel label, SpecialJProgressBar bar){
        this.parent = parent;
        this.label = label;
        this.bar = bar;
        this.fm = label.getFontMetrics(label.getFont());
        
        spa = new SmoothProgressAnimator(label, bar);
    }
    
    private SmoothProgressAnimator spa;
    
    private final Color INVALID_1 = new Color(255,102,102);
    private final Color INVALID_2 = new Color(255,51,51);
    private final Color VALID_1 = new Color(153,255,153);
    private final Color VALID_2 = new Color(0,255,51);
    private final Color DEFAULT_1 = Color.WHITE;
    
    private void decideColors(MSG_TYPE type){
        switch (type) {
            case INVALID -> {
                bar.setGradientColors(INVALID_1, INVALID_2);
                label.setForeground(INVALID_2);
            }
            
            case VALID -> {
                bar.setGradientColors(VALID_1, VALID_2);
                label.setForeground(VALID_2);
            }
            
            case DEFAULT -> {
                bar.setGradientColors(DEFAULT_1, DEFAULT_1);
                label.setForeground(DEFAULT_1);
            }
        }
    }
    
    public void startAnimation(String text, MSG_TYPE type){
        decideColors(type);
        startAnimation(text);
    }
    
    public void startAnimation(String text, Color firstColor, Color secondColor){
        bar.setGradientColors(firstColor, secondColor);
        label.setForeground(secondColor);
        startAnimation(text);
    }
    
    private int prevX = 0;
    private int prevY = 0;
    
    public void startAnimation(String text){
        int stringWidth = fm.stringWidth(text);
        
        int x = label.getX() + (label.getWidth() - stringWidth) - 3;
        int y = label.getY() + label.getHeight()+ 5;
        
        if(prevX != x || prevY != y){
            parent.remove(bar);
            parent.revalidate();
            parent.add(bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, stringWidth, bar.getHeight()));
            parent.revalidate();
            parent.repaint();
            prevX = x;
            prevY = y;
        }
        
        label.setText(text);
        spa.start(2000);
    }
    
    private class SmoothProgressAnimator {

        private final SpecialJProgressBar progressBar;
        private JLabel label;
        private long startTime;

        public SmoothProgressAnimator(JLabel label, SpecialJProgressBar progressBar) {
            this.label = label;
            this.progressBar = progressBar;
        }
        
        Timer timer;

        public void start(long duration) {
            
            if(timer != null && timer.isRunning()){
                timer.stop();
            }
            progressBar.setValue(0);
            startTime = System.currentTimeMillis();

            timer = new Timer(1000 / 60, null); // ~60 FPS
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    long currentTime = System.currentTimeMillis();
                    double t = (double) (currentTime - startTime) / duration;
                    if (t > 1) t = 1;

                    // Sinusoidal easing
                    double eased = 0.5 - 0.5 * Math.cos(Math.PI * t);

                    int value = (int) (eased * 100);
                    progressBar.setValue(value);
                    progressBar.setGradientOffset((float) t);

                    if (t >= 1) {
                        ((Timer) e.getSource()).stop(); // stop timer at the end
                        onTimerStop();
                    }
                }
            });

            timer.start();
            
        }
        
        private void onTimerStop(){
            label.setText("");
            progressBar.setValue(0);
        }
        
        
    }
}
