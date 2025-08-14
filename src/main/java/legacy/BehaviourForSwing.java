/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package legacy;

import frontend.RoundedButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.nio.file.attribute.UserPrincipal;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import jdk.nio.mapmode.ExtendedMapMode;

/**
 *
 * @author BRIN
 */
public class BehaviourForSwing {
    
    private static final Color DISABLED_TEXT = new Color(102,102,102);
    
    public static void addDefaultJLabelMouseBehaviour(javax.swing.JLabel jLabel, AfterEventCode aec){
        jLabel.setForeground(new Color(204, 204, 204));
        
        jLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel.setForeground(Color.WHITE);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel.setForeground(new Color(204, 204, 204));
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel.setForeground(new Color(204, 204, 204));
                
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel.setForeground(Color.WHITE);
                
                aec.start();
               
            }
        });
        
        
    }
    
    public static void removeJLabelMouseBehaviour(javax.swing.JLabel Jlabel){
        
        Jlabel.setForeground(DISABLED_TEXT);
        
        MouseListener[] msl = Jlabel.getMouseListeners();
        for(int i = 0; i < msl.length; i++){
        Jlabel.removeMouseListener(msl[i]);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="addDefaultJLabelMouseBehaviour_forEACH">
    public static void addDefaultJLabelMouseBehaviour_forEACH(javax.swing.JLabel jLabel, 
            AfterEventCode mouseEnteredAEC,
            AfterEventCode mouseExitedAEC,
            AfterEventCode mousePressedAEC,
            AfterEventCode mouseReleasedAEC){
        jLabel.setForeground(new Color(204, 204, 204));
        
        jLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                
                jLabel.setForeground(Color.WHITE);
                
                mouseEnteredAEC.start();
                System.out.println("Mouse Entered");
                
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel.setForeground(new Color(204, 204, 204));
                
                mouseExitedAEC.start();
                System.out.println("Mouse Exited");
                
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel.setForeground(new Color(204, 204, 204));
                
                mousePressedAEC.start();
                System.out.println("Mouse Pressed");
                
                
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel.setForeground(Color.WHITE);
             
                mouseReleasedAEC.start();
                System.out.println("Mouse Released");
            
            }
        });
        
        
    }// </editor-fold>
    
    private static Color defaultSeparatorColor = new Color(153,153,153);
    
    // <editor-fold defaultstate="collapsed" desc="public static void addDefaultTextBoxBehaviour_Common">
    public static void addDefaultTextBoxBehaviour_Common(javax.swing.JTextField JTextBox, javax.swing.JSeparator JSeparator, AfterEventCode aec){
                
        JTextBox.getParent().requestFocus();
        JSeparator.setForeground(defaultSeparatorColor);
        JSeparator.setBackground(defaultSeparatorColor);
        JTextBox.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                JSeparator.setForeground(Color.WHITE);
                JSeparator.setBackground(Color.WHITE);
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
             JSeparator.setForeground(defaultSeparatorColor);   
             JSeparator.setBackground(defaultSeparatorColor);
            }
        });
        
        Document doc = JTextBox.getDocument();
        
        doc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // This method is called when text is inserted
                aec.start();
                    
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // This method is called when text is removed
                
                aec.start();
                
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // This method is called when attributes change (less common for JTextField)
                
                        aec.start();
            }

            
        });
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="public static void addDefaultTextBoxBehaviour_Different">
    public static void addDefaultTextBoxBehaviour_Different(javax.swing.JTextField JTextBox, 
            javax.swing.JSeparator JSeparator, 
            AfterEventCode insertedAEC,
            AfterEventCode removedAEC,
            AfterEventCode changedAEC){
                
        JSeparator.setForeground(defaultSeparatorColor);
        JSeparator.setBackground(defaultSeparatorColor);
        JTextBox.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                
                JSeparator.setForeground(Color.WHITE);
                JSeparator.setBackground(Color.WHITE);
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
             JSeparator.setForeground(defaultSeparatorColor);
            JSeparator.setBackground(defaultSeparatorColor);   
            }
        });
        
        Document doc = JTextBox.getDocument();
        
        doc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // This method is called when text is inserted
            
                
                insertedAEC.start();
               
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // This method is called when text is removed
                
                removedAEC.start();
         
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // This method is called when attributes change (less common for JTextField)
              
                changedAEC.start();
                 
            }

            
        });
    }// </editor-fold>
    
    public static void addInputFilteredTextBoxBehaviour_Common(javax.swing.JTextField JTextBox, javax.swing.JSeparator JSeparator, int maxLength, AfterEventCode aec){
     
        JTextBox.getParent().requestFocus();
        JSeparator.setForeground(defaultSeparatorColor);
        JSeparator.setBackground(defaultSeparatorColor);
        JTextBox.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                JSeparator.setForeground(Color.WHITE);
                JSeparator.setBackground(Color.WHITE);
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
             JSeparator.setForeground(defaultSeparatorColor);   
             JSeparator.setBackground(defaultSeparatorColor);
            }
        });
        
        AbstractDocument doc = (AbstractDocument) JTextBox.getDocument();
        doc.setDocumentFilter(new NumericAndFirstDigitFilter(maxLength)); // Causes to take into only numbers and first didgit can not be 0
        
        
        doc.addDocumentListener(new DocumentListener() {
            
            private boolean settingDefault = false; // Flag to prevent re-entrancy, because applying changes through listener can cause
                                                    // StackOverflowError due to repeated recursive method calls due to the
                                                    // listener method doing something that itself retriggers the listener.
            @Override
            public void insertUpdate(DocumentEvent e) {
                // This method is called when text is inserted
                
                aec.start();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // This method is called when text is removed
                
                aec.start();
                
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // This method is called when attributes change (less common for JTextField)
                
                aec.start();
            }
            
            private void checkAndAutoFill(javax.swing.text.Document doc) {
                // Prevent recursive calls when we set the text ourselves
                SwingUtilities.invokeLater(() -> {
                if (settingDefault) {
                    return;
                }

                try {
                    if (doc.getLength() == 0) {
                        settingDefault = true; // Set flag
                        doc.insertString(0, "1", null); // Insert "1"
                        settingDefault = false; // Reset flag
                    }
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                    settingDefault = false; // Ensure flag is reset even on error
                }
                });
            }
            
            
        });
    }
    
    public static void addNumericFilter(JTextField JTextBox, int maxLength, AfterEventCode aec){
        
        JTextBox.getParent().requestFocus();
        
        AbstractDocument doc = (AbstractDocument) JTextBox.getDocument();
        doc.setDocumentFilter(new NumericAndFirstDigitFilter(maxLength)); // Causes to take into only numbers and first didgit can not be 0
        
        
        doc.addDocumentListener(new DocumentListener() {
            
            private boolean settingDefault = false; // Flag to prevent re-entrancy, because applying changes through listener can cause
                                                    // StackOverflowError due to repeated recursive method calls due to the
                                                    // listener method doing something that itself retriggers the listener.
            @Override
            public void insertUpdate(DocumentEvent e) {
                // This method is called when text is inserted
                
                aec.start();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // This method is called when text is removed
                
                aec.start();
                
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // This method is called when attributes change (less common for JTextField)
                
                aec.start();
            }
            
            private void checkAndAutoFill(javax.swing.text.Document doc) {
                // Prevent recursive calls when we set the text ourselves
                SwingUtilities.invokeLater(() -> {
                if (settingDefault) {
                    return;
                }

                try {
                    if (doc.getLength() == 0) {
                        settingDefault = true; // Set flag
                        doc.insertString(0, "1", null); // Insert "1"
                        settingDefault = false; // Reset flag
                    }
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                    settingDefault = false; // Ensure flag is reset even on error
                }
                });
            }
            
            
        });
    }
    
    public static void addSpecificHyperlinkFilterBehaviour(javax.swing.JTextField JTextBox, javax.swing.JSeparator JSeparator, AfterEventCode aec){
        
        JTextBox.getParent().requestFocus();
        JSeparator.setForeground(defaultSeparatorColor);
        JSeparator.setBackground(defaultSeparatorColor);
        JTextBox.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                JSeparator.setForeground(Color.WHITE);
                JSeparator.setBackground(Color.WHITE);
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
             JSeparator.setForeground(defaultSeparatorColor);   
             JSeparator.setBackground(defaultSeparatorColor);
            }
        });
        
        AbstractDocument doc = (AbstractDocument) JTextBox.getDocument();
        
        doc.addDocumentListener(new DocumentListener() {
            
            private boolean settingDefault = false; // Flag to prevent re-entrancy, because applying changes through listener can cause
                                                    // StackOverflowError due to repeated recursive method calls due to the
                                                    // listener method doing something that itself retriggers the listener.
            @Override
            public void insertUpdate(DocumentEvent e) {
                // This method is called when text is inserted
                checkAndAutoFill(e.getDocument());
                aec.start();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // This method is called when text is removed
                checkAndAutoFill(e.getDocument());
                aec.start();
                
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // This method is called when attributes change (less common for JTextField)
                checkAndAutoFill(e.getDocument());
                aec.start();
            }
            
            private void checkAndAutoFill(javax.swing.text.Document doc) {
                // Prevent recursive calls when we set the text ourselves
                SwingUtilities.invokeLater(() -> {
                if (settingDefault) {
                    return;
                }

                try {
                    if (doc.getLength() == 0) {
                        settingDefault = true; // Set flag
                        doc.insertString(0, "https://", null); // Insert "1"
                        settingDefault = false; // Reset flag
                    }
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
                    settingDefault = false; // Ensure flag is reset even on error
                }
                });
            }
            
            
        });
    }
    
    
    
    public static void addSpecificRoundedButtonActionBehaviour(RoundedButton rdButton, Color[] colorArray, ButtonActionCode aec){
        
        
        rdButton.setDefaultColor(colorArray[0]);
        rdButton.setHoverColor(colorArray[1]);
        rdButton.setPressedColor(colorArray[2]);
        
        rdButton.callRepaint();
        
        MouseAdapter buttonMouseAdapter = new MouseAdapter() {

            private boolean isInside = false;

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt){
                
                rdButton.setCurrentColor(colorArray[1]);
                isInside = true;
                rdButton.callRepaint();
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt){
                
                rdButton.setCurrentColor(colorArray[0]);
                isInside = false;
                rdButton.callRepaint();
            }
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt){
                
                rdButton.setCurrentColor(colorArray[2]);
                rdButton.callRepaint();
            }
            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt){
                
                if(isInside)
                rdButton.setCurrentColor(colorArray[1]);
                else{
                    rdButton.setCurrentColor(colorArray[0]);
                }
                rdButton.callRepaint();
            }
            
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt){
                
                new Thread(){
                    
                    @Override
                    public void run(){
                        aec.start();
                    }
                }.start();
                
                
            }
        };
        
        rdButton.addMouseListener(buttonMouseAdapter);
        
        
    }
    
    public static void removeRoundedButtonActionBehaviour(RoundedButton rdButton){
        
        rdButton.setDefaultColor(new Color(57,75,92));
        rdButton.callRepaint();
        
        
        MouseListener[] mouseList = rdButton.getMouseListeners();
        for(int i = 0; i < mouseList.length; i++){
            rdButton.removeMouseListener(mouseList[i]);
        }
    }
    
    enum modificationType {
        
        EXTEND,
        REMOVE
    }
    
    
    
    public static void addTypicalJTextBoxBehaviour(JTextField JTextBox, RoundedButton rdB, Color[] colorScheme, AfterEventCode aec){
        
        
        String defaultText = JTextBox.getText();
        
        MouseAdapter extenderAdapter = new MouseAdapter() {
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if(!JTextBox.hasFocus() && JTextBox.getText().equals(defaultText))
                JTextBox.setForeground(colorScheme[1]);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if(!JTextBox.hasFocus() && JTextBox.getText().equals(defaultText))
                JTextBox.setForeground(colorScheme[2]);
            }
        };
        
        JTextBox.addMouseListener(extenderAdapter);
        
        
        
        JTextBox.addFocusListener(new FocusAdapter(){
            
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if(JTextBox.getText().equals(defaultText)){
                    JTextBox.setText("");
                }
                JTextBox.setForeground(colorScheme[0]);
                
            }
            
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                
                if(JTextBox.getText().isBlank() || JTextBox.getText().equals(defaultText)){
                    JTextBox.setText(defaultText);
                
                    rdB.setDefaultColor(rdB.ULTIMATE_DEFAULT);
                    rdB.callRepaint();
                    JTextBox.setForeground(colorScheme[2]);
                    
                }
            }
        });
        
        Document doc = (AbstractDocument) JTextBox.getDocument();
        
        doc.addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                aec.start();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                aec.start();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                aec.start();
            }
            
            
        });
        
        
    }
    
    
    
    
    
    
}
