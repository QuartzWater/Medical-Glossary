/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.quartzwater.medicalglossary;

import backend.TermDataManagement;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 * @author BRIN
 */
public class HyperlinkEditWindow extends javax.swing.JFrame {

    private TermDataManagement tdm;
    private String inputTerm;
    private boolean isEditModeEnabled;
    private boolean initialisedVIACreate;
    
    /**
     * Creates new form HyperlinkEditWindow
     */
    
    
    
    private void initialiseHyperlinkTextBoxBehaviour(){
        
        TextBoxContentEventCode hyperlinkTextBoxCode = new TextBoxContentEventCode(){
            
            @Override
            public void runCode(){
                
                Document  HLdoc = hyperlinkTextBox.getDocument();
                Document Edoc = encapsulationTextBox.getDocument();
                
                try{
                    String HLtext = HLdoc.getText(0, HLdoc.getLength());
                    String Etext = Edoc.getText(0, Edoc.getLength());
                    
                    if((!HLtext.startsWith("https://") && !HLtext.startsWith("http://"))){
                        
                        BehaviourForSwing.removeJLabelMouseBehaviour(visitLabel);
                    }
                    else{
                        
                        
                        initialiseVisitLabelBehaviour();
                    }
                    if(Etext.isBlank() || (!HLtext.startsWith("https://") && !HLtext.startsWith("http://"))){
                            BehaviourForSwing.removeJLabelMouseBehaviour(saveLabel);
                    }
                    else{
                        initialiseSaveLabelBehaviour();
                    }
                    
                }catch(BadLocationException e){
                    
                    System.err.println("Hyperlink/Encapsulation TextBox couldnt get the text required for checking the validity of hyperlink!");
                }
                
                
                
            }
        };
        
        BehaviourForSwing.addSpecificHyperlinkFilterBehaviour(hyperlinkTextBox, hyperlinkSep, hyperlinkTextBoxCode);
    }
    
    private void initialiseEncapsulationTextBoxBehaviour(){
     
        TextBoxContentEventCode encapsulationTextBoxCode = new TextBoxContentEventCode(){
            
            @Override
            public void runCode(){
                
                Document  HLdoc = hyperlinkTextBox.getDocument();
                Document Edoc = encapsulationTextBox.getDocument();
                
                try{
                    String HLtext = HLdoc.getText(0, HLdoc.getLength());
                    String Etext = Edoc.getText(0, Edoc.getLength());
                    
                    if((!HLtext.startsWith("https://") && !HLtext.startsWith("http://")) || Etext.isBlank()){
                        BehaviourForSwing.removeJLabelMouseBehaviour(saveLabel);
                        
                    }
                    else{
                        
                        initialiseSaveLabelBehaviour();
                        
                    }
                    
                }catch(BadLocationException e){
                    
                    System.err.println("Encapsulation/Hyperlink TextBox couldnt get the text required for checking the validity of hyperlink!");
                }
            }
        };
        
        BehaviourForSwing.addDefaultTextBoxBehaviour_Common(encapsulationTextBox, encapSep, encapsulationTextBoxCode);
        
    }
    
    private void initialiseSaveLabelBehaviour(){
        
        MouseReleaseCode saveLabelCode = new MouseReleaseCode(){
            
            @Override
            public void runCode(){
                
                
            }
        };
        
        BehaviourForSwing.addDefaultJLabelMouseBehaviour(saveLabel, saveLabelCode);
    }
    
    private void initialiseBackLabelBehaviour(){
        
        MouseReleaseCode backLabelCode = new MouseReleaseCode(){
            
            @Override
            public void runCode(){
                
                
            }
        };
        
        BehaviourForSwing.addDefaultJLabelMouseBehaviour(backLabel, backLabelCode);
    }
    
    private void initialiseNextLabelBehaviour(){
        
        MouseReleaseCode nextLabelCode = new MouseReleaseCode(){
            
            @Override
            public void runCode(){
                
                
            }
        };
        
        BehaviourForSwing.addDefaultJLabelMouseBehaviour(nextLabel, nextLabelCode);
    }
    
    private void initialiseVisitLabelBehaviour(){
        
        MouseReleaseCode visitLabelCode = new MouseReleaseCode(){
            
            @Override
            public void runCode(){
                
                
            }
        };
        
        BehaviourForSwing.addDefaultJLabelMouseBehaviour(visitLabel, visitLabelCode);
    }
    
    

    public HyperlinkEditWindow(TermDataManagement tdm, String inputTerm, boolean isEditModeEnabled, boolean initialisedVIACreate) {
        initComponents();
        this.tdm = tdm;
        this.inputTerm = inputTerm;
        this.isEditModeEnabled = isEditModeEnabled;
        this.initialisedVIACreate = initialisedVIACreate;
        
        initialiseHyperlinkTextBoxBehaviour();
        initialiseEncapsulationTextBoxBehaviour();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        editModeLabel = new javax.swing.JLabel();
        backLabel = new javax.swing.JLabel();
        saveLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        encapsulationTextBox = new javax.swing.JTextField();
        encapSep = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        hyperlinkTextBox = new javax.swing.JTextField();
        hyperlinkSep = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        visitLabel = new javax.swing.JLabel();
        nextLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        statusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(810, 400));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(21, 25, 28));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setBackground(new java.awt.Color(58, 63, 66));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("1");
        jLabel12.setOpaque(true);
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 40, 40));

        jLabel13.setBackground(new java.awt.Color(58, 63, 66));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("2");
        jLabel13.setOpaque(true);
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 40, 40));

        jLabel14.setBackground(new java.awt.Color(58, 63, 66));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("3");
        jLabel14.setOpaque(true);
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 40, 40));

        jLabel15.setBackground(new java.awt.Color(58, 63, 66));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("4");
        jLabel15.setOpaque(true);
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 40, 40));

        jLabel16.setBackground(new java.awt.Color(58, 63, 66));
        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("5");
        jLabel16.setOpaque(true);
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 40, 40));

        jLabel7.setBackground(new java.awt.Color(58, 63, 66));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("6");
        jLabel7.setOpaque(true);
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 40, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 400));

        jPanel3.setBackground(new java.awt.Color(21, 25, 28));
        jPanel3.setPreferredSize(new java.awt.Dimension(190, 400));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editModeLabel.setBackground(new java.awt.Color(21, 25, 28));
        editModeLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        editModeLabel.setForeground(new java.awt.Color(204, 204, 204));
        editModeLabel.setText("   Toggle Edit ");
        editModeLabel.setOpaque(true);
        jPanel3.add(editModeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, 40));

        backLabel.setBackground(new java.awt.Color(21, 25, 28));
        backLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        backLabel.setForeground(new java.awt.Color(204, 204, 204));
        backLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        backLabel.setText("   Back");
        backLabel.setOpaque(true);
        jPanel3.add(backLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 150, 40));

        saveLabel.setBackground(new java.awt.Color(21, 25, 28));
        saveLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        saveLabel.setForeground(new java.awt.Color(204, 204, 204));
        saveLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        saveLabel.setText("   Save");
        saveLabel.setOpaque(true);
        jPanel3.add(saveLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 150, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 190, 400));

        jPanel4.setBackground(new java.awt.Color(34, 40, 44));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(34, 40, 44));
        jLabel3.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Hyperlink");
        jLabel3.setOpaque(true);
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, 30));

        encapsulationTextBox.setBackground(new java.awt.Color(34, 40, 44));
        encapsulationTextBox.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        encapsulationTextBox.setForeground(new java.awt.Color(255, 255, 255));
        encapsulationTextBox.setText("DGFSfjfsgig");
        encapsulationTextBox.setBorder(null);
        encapsulationTextBox.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel4.add(encapsulationTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 520, -1));
        jPanel4.add(encapSep, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 520, 10));

        jLabel4.setBackground(new java.awt.Color(34, 40, 44));
        jLabel4.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Links to:");
        jLabel4.setOpaque(true);
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 130, -1));

        hyperlinkTextBox.setBackground(new java.awt.Color(34, 40, 44));
        hyperlinkTextBox.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        hyperlinkTextBox.setForeground(new java.awt.Color(255, 255, 255));
        hyperlinkTextBox.setText("DGFSfjfsgig");
        hyperlinkTextBox.setBorder(null);
        hyperlinkTextBox.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel4.add(hyperlinkTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 520, -1));
        jPanel4.add(hyperlinkSep, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 520, 10));

        jLabel5.setBackground(new java.awt.Color(34, 40, 44));
        jLabel5.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Encapsultation:");
        jLabel5.setOpaque(true);
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 130, -1));

        visitLabel.setBackground(new java.awt.Color(34, 40, 44));
        visitLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        visitLabel.setForeground(new java.awt.Color(204, 204, 204));
        visitLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        visitLabel.setText("Visit >>");
        visitLabel.setOpaque(true);
        jPanel4.add(visitLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, 70, 40));

        nextLabel.setBackground(new java.awt.Color(34, 40, 44));
        nextLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        nextLabel.setForeground(new java.awt.Color(204, 204, 204));
        nextLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nextLabel.setText("Next");
        nextLabel.setOpaque(true);
        jPanel4.add(nextLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 90, 40));
        jPanel4.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 520, 10));

        statusLabel.setBackground(new java.awt.Color(34, 40, 44));
        statusLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(204, 204, 204));
        statusLabel.setText("Status:");
        statusLabel.setOpaque(true);
        jPanel4.add(statusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 520, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 560, 400));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HyperlinkEditWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HyperlinkEditWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HyperlinkEditWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HyperlinkEditWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HyperlinkEditWindow(null, null, false, false).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backLabel;
    private javax.swing.JLabel editModeLabel;
    private javax.swing.JSeparator encapSep;
    private javax.swing.JTextField encapsulationTextBox;
    private javax.swing.JSeparator hyperlinkSep;
    private javax.swing.JTextField hyperlinkTextBox;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel nextLabel;
    private javax.swing.JLabel saveLabel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel visitLabel;
    // End of variables declaration//GEN-END:variables
}
