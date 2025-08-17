/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend;

import backend.Term;
import backend.TermDataManagement;
import book.bookpicker.Book;
import legacy.AfterEventCode;
import legacy.BehaviourForSwing;
import legacy.ButtonActionCode;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

/**
 *
 * @author BRIN
 */
public class definitionEditFrame extends javax.swing.JFrame {

    /**
     * Creates new form definitionEditFrame
     */
    
    private final Term existingTerm;
    private Term newTerm;
    private final TermDataManagement tdm;
    private final Book initialisedBook;
    
    private JTextComponent[] textComponents;
    
    private final boolean termFound;
    
    public definitionEditFrame(Book initialisedBook, TermDataManagement tdm, Term existingTerm, boolean  termFound, JTextComponent[] textComponents){
        
        
        this.tdm = tdm;
        this.existingTerm = existingTerm;
        this.initialisedBook = initialisedBook;
        this.termFound = termFound;
        this.textComponents = textComponents;
        
        initComponents();
        
        nextButton.setEnabled(true);
        
        if(termFound){
            
            titleLabel.setText("Edit Mode");
            spellingTextBox.setText(textComponents[0].getText());
            definitionTextArea.setText(textComponents[1].getText());
            this.setTitle("Edit Mode: '" + textComponents[0].getText() + "'");
            
        }
        else{
            
            titleLabel.setText("Create new term");
            spellingTextBox.setText(textComponents[0].getText());
            this.setTitle("Create new term: '" + textComponents[0].getText() + "'");
        }
        
        new definitionEditFrameGUIBehaviour(this);
        
        
        // ********* LEGACY CODE *********
        /*
        Document doc = spellingTextBox.getDocument();
        AfterEventCode anonymousAEC = new AfterEventCode() {
            @Override
            public void runCode() {
                if(termFound)
                setTitle("Edit Mode: '" + textComponents[0].getText() + "' to '" + spellingTextBox.getText() + "'");
                else
                setTitle("Create New Term: '" + spellingTextBox.getText() + "'");
            }
        };
        
        anonymousAEC.start();
        
        doc.addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                // This method is called when text is inserted
                validateSpelling();
                anonymousAEC.start();
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // This method is called when text is removed
                
                validateSpelling();
                anonymousAEC.start();
                
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // This method is called when attributes change (less common for JTextField)
                
                validateSpelling();
                anonymousAEC.start();
            }
        
        }
        );
        validateSpelling();
        */
        
        // ******* LEGACY CODE ENDS ********
    }
    
    public static definitionEditFrame generateInstance(Book initialisedBook, TermDataManagement tdm, Term existingTerm, boolean  termFound, JTextComponent[] textComponents){
        return new definitionEditFrame(initialisedBook, tdm, existingTerm, termFound, textComponents);
    }
    
    
    public JTextField getSpellingBox(){
        
        return this.spellingTextBox;
    }
    
    public JTextArea getDefinitionTextArea(){
        
        return this.definitionTextArea;
    }
    
    public Book getInitialisedBook(){
        
        return this.initialisedBook;
    }
    
    public TermDataManagement getInitialisedTDM(){
        
        return this.tdm;
    }
    
    public Term getExistingTerm(){
        
        return this.existingTerm;
    }
    
    public Term getNewTerm(){
        
        return newTerm;
    }
    
    public boolean getTermFound(){
        
        return this.termFound;
    }
    
    public RoundedButton getNextButton(){
        
        return this.nextButton;
    }
    
    public RoundedButton getBackButton(){
        
        return backButton;
    }
    
    public JLabel getStatusLabel(){
        
        return statusLabel;
    }
    
    public JTextComponent[] getPassedInTextComponent(){
        
        return this.textComponents;
    }
    
    public String[] getSpellingDefinitionArray(){ // of parent fram and not this one
        
        return new String[]{
                
            getPassedInTextComponent()[0].getText(), // 0th index is Spelling Box text
            getPassedInTextComponent()[1].getText() // 1st index is Definition text
       };
    }
    
    private definitionEditFrame getMyself(){
        
        return this;
    }
    private final ButtonActionCode nextButton_actionCode = new ButtonActionCode(){
        
      @Override
      public void runCode(){
          

          Term newTerm = new Term(getSpellingBox().getText());
          newTerm.setDefinition(getDefinitionTextArea().getText());
          referenceEditFrame ref = new referenceEditFrame(initialisedBook, tdm, existingTerm, newTerm, termFound, getMyself());
          new Thread(){
              
              @Override
              public void run(){
                  
                  ref.setVisible(true);
                    
                  getMyself().dispose();
              }
          }.start();
          
              
          
      }
          
    };
    
    private Color[] nextButtonColorScheme = {
      
        new Color(27,36,45),
        new Color(69,160,73),
        new Color(62,142,65)
    };
    
    private Color[] backButtonColorScheme = {
            
            new Color(27,36,45),
            new Color(255,102,102),
            new Color(255,51,51)
                
    };
    
    private void validateSpelling(){
        
        String currentText = spellingTextBox.getText().trim();
        
        if(!currentText.isBlank()){
            
            BehaviourForSwing.addSpecificRoundedButtonActionBehaviour(nextButton, nextButtonColorScheme, nextButton_actionCode);
        }
        else{
            BehaviourForSwing.removeRoundedButtonActionBehaviour(nextButton);
        }
        
    }
    
    public void resetColor(){
        
        backButton.setDefaultColor(backButtonColorScheme[0]);
        nextButton.setDefaultColor(nextButtonColorScheme[0]);
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        parentPanel = new javax.swing.JPanel();
        spellingTextBox = new javax.swing.JTextField();
        titleLabel = new javax.swing.JLabel();
        definitionTextArea = new javax.swing.JTextArea();
        decorativePanel = new frontend.RoundedPanel();
        headerLabel = new javax.swing.JLabel();
        backButton = new frontend.RoundedButton();
        statusLabel = new javax.swing.JLabel();
        nextButton = new frontend.RoundedButton();
        statusTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(590, 720));
        setResizable(false);
        setSize(new java.awt.Dimension(590, 720));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        parentPanel.setBackground(new java.awt.Color(44, 62, 80));
        parentPanel.setMaximumSize(new java.awt.Dimension(590, 720));
        parentPanel.setMinimumSize(new java.awt.Dimension(590, 720));
        parentPanel.setPreferredSize(new java.awt.Dimension(590, 720));
        parentPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        spellingTextBox.setBackground(new java.awt.Color(68, 96, 122));
        spellingTextBox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        spellingTextBox.setForeground(new java.awt.Color(255, 255, 255));
        spellingTextBox.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        spellingTextBox.setText("jTextField1");
        spellingTextBox.setCaretColor(new java.awt.Color(255, 255, 255));
        spellingTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spellingTextBoxActionPerformed(evt);
            }
        });
        parentPanel.add(spellingTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 470, 40));

        titleLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Edit Mode");
        parentPanel.add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 280, -1));

        definitionTextArea.setColumns(20);
        definitionTextArea.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        definitionTextArea.setForeground(new java.awt.Color(255, 255, 255));
        definitionTextArea.setLineWrap(true);
        definitionTextArea.setRows(5);
        definitionTextArea.setText("---");
        definitionTextArea.setWrapStyleWord(true);
        definitionTextArea.setCaretColor(new java.awt.Color(255, 255, 255));
        definitionTextArea.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        definitionTextArea.setOpaque(false);
        parentPanel.add(definitionTextArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 510, 390));

        decorativePanel.setDefaultColor(new java.awt.Color(57, 75, 92));

        javax.swing.GroupLayout decorativePanelLayout = new javax.swing.GroupLayout(decorativePanel);
        decorativePanel.setLayout(decorativePanelLayout);
        decorativePanelLayout.setHorizontalGroup(
            decorativePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );
        decorativePanelLayout.setVerticalGroup(
            decorativePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        parentPanel.add(decorativePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 530, 410));

        headerLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        headerLabel.setForeground(new java.awt.Color(255, 255, 255));
        headerLabel.setText("Definition:");
        parentPanel.add(headerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 120, -1));

        backButton.setText("Back");
        backButton.setDefaultColor(new java.awt.Color(57, 75, 92));
        parentPanel.add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 650, -1, -1));

        statusLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(255, 255, 255));
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        parentPanel.add(statusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 470, 20));

        nextButton.setText("Next");
        nextButton.setDefaultColor(new java.awt.Color(27, 36, 45));
        parentPanel.add(nextButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 650, -1, -1));

        statusTextArea.setColumns(20);
        statusTextArea.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        statusTextArea.setForeground(new java.awt.Color(255, 255, 255));
        statusTextArea.setLineWrap(true);
        statusTextArea.setRows(5);
        statusTextArea.setWrapStyleWord(true);
        statusTextArea.setCaretColor(new java.awt.Color(255, 255, 255));
        statusTextArea.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        statusTextArea.setOpaque(false);
        parentPanel.add(statusTextArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 640, 200, 70));

        getContentPane().add(parentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void spellingTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spellingTextBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_spellingTextBoxActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private frontend.RoundedButton backButton;
    private frontend.RoundedPanel decorativePanel;
    private javax.swing.JTextArea definitionTextArea;
    private javax.swing.JLabel headerLabel;
    private frontend.RoundedButton nextButton;
    private javax.swing.JPanel parentPanel;
    private javax.swing.JTextField spellingTextBox;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JTextArea statusTextArea;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
