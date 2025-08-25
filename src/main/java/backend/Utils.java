/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.awt.Font;
import java.awt.FontMetrics;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author BRIN
 */
public class Utils {
    
    public static void dynamicallyChangeFont(JTextField jtx){
        
        FontMetrics fmt = jtx.getFontMetrics(jtx.getFont());
        
        int textWidth = fmt.stringWidth(jtx.getText());
        int fieldWidth = jtx.getWidth();
        
        while(textWidth > fieldWidth){
            
            Font font = jtx.getFont();
            int fontSize = font.getSize();
            fontSize--;
            if(fontSize < 1){
                break;
            }
            font = new Font(font.getName(), font.getStyle(), fontSize);
            jtx.setFont(font);
            fmt = jtx.getFontMetrics(jtx.getFont());
            textWidth = fmt.stringWidth(jtx.getText());
            fieldWidth = jtx.getWidth();
            System.out.println("text Width: "  + textWidth);
            System.out.println("field Width: " +fieldWidth);
        }
        
    }

    public static void dynamicallyChangeFont(JLabel jlb) {
        FontMetrics fmt = jlb.getFontMetrics(jlb.getFont());
        
        int textWidth = fmt.stringWidth(jlb.getText());
        int fieldWidth = jlb.getWidth();
        
        while(textWidth > fieldWidth){
            
            Font font = jlb.getFont();
            int fontSize = font.getSize();
            fontSize--;
            if(fontSize < 1){
                break;
            }
            font = new Font(font.getName(), font.getStyle(), fontSize);
            jlb.setFont(font);
            fmt = jlb.getFontMetrics(jlb.getFont());
            textWidth = fmt.stringWidth(jlb.getText());
            fieldWidth = jlb.getWidth();
            System.out.println("text Width: "  + textWidth);
            System.out.println("field Width: " +fieldWidth);
        }    
    }
    
    public static void dynamicallyChangeFont(JTextArea jta) {
        
        
        int textHeight = jta.getPreferredSize().height;
        int fieldHeight = jta.getWidth();
        
        while(textHeight > fieldHeight){
            
            Font font = jta.getFont();
            int fontSize = font.getSize();
            fontSize--;
            if(fontSize < 1){
                break;
            }
            font = new Font(font.getName(), font.getStyle(), fontSize);
            jta.setFont(font);
            
            textHeight = jta.getPreferredSize().height;
            fieldHeight = jta.getWidth();
            System.out.println("text Height: "  + textHeight);
            System.out.println("field Height: " +fieldHeight);
        }    
    }
}
