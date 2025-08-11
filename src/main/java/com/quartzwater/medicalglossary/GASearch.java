/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.quartzwater.medicalglossary;

import backend.Term;
import backend.TermDataManagement;
import java.awt.Color;
import java.awt.Component;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author BRIN
 */
public class GASearch extends javax.swing.JFrame {

    /**
     * Creates new form GASearch
     */
    
    Path rootPath = Paths.get("C:\\MedicalGlossary\\books\\GA");
    private final String CREATE_EDIT_LABEL_TEXT_CREATE = "   Create New";
    private final String CREATE_EDIT_LABEL_TEXT_EDIT = "   Edit Existing";
    private final String CREATE_EDIT_LABEL_TEXT_TOGGLE = "   Toggle Edit";
    
    private boolean isEditModeActive = false;
    private boolean termExistsInMap = false;
    
    private String searchBoxTextRevert = "";
    private String definitionAreaTextRevert = "";
    
    private TermDataManagement tdm;
    
    private Component[] componentArray;
    
    // AfterEventCode declarations
    
    private MouseReleaseCode addInfoMRC;
    private MouseReleaseCode hyperlinkMRC;
    private TextBoxContentEventCode textContentEvent;
    private MouseReleaseCode createEditLabelMouseEntered;
    private MouseReleaseCode createEditLabelMouseExited;
    private MouseReleaseCode createEditLabelMousePressed;
    private MouseReleaseCode createEditLabelMouseReleased;
    
    // End of AfterEventCode declarations
    
    private GASearch getMyself(){
        
        return this;
    }
    
    public Component[] getMyComponents(){
        
        return componentArray;
    }
    
    //Following methods should only be used by the window that follows GASearch Window
    
    public String getSearchBoxText(){
        
        return this.searchBox.getText();
    }
    
    
    
    private void setForeground(javax.swing.JLabel jLabel, java.awt.Color color){
                jLabel.setForeground(color);

    }
    
    private void initialise_addInfoMRC(){
        
        addInfoMRC = new MouseReleaseCode(){
          
            @Override
            public void runCode(){
                new AdditionalDetailsWindow(tdm, searchBox.getText(), isEditModeActive, false, getMyself()).setVisible(true);
            }
        };
        
        BehaviourForSwing.addDefaultJLabelMouseBehaviour(addInfoLabel, addInfoMRC);
    }
    
    private void initialise_hyperlinkMRC(){
        
        hyperlinkMRC = new MouseReleaseCode(){
            
            @Override
            public void runCode(){
                new HyperlinkEditWindow(tdm, searchBox.getText(), isEditModeActive, false).setVisible(true);
            }
        };
        
        BehaviourForSwing.addDefaultJLabelMouseBehaviour(hyperlinkLabel, hyperlinkMRC);
    }
    
    private void initialise_textInfoContent(){
        
        textContentEvent = new TextBoxContentEventCode(){
            
          @Override
          public void runCode(){
                String inputText = searchBox.getText();
                Term checkTerm;
                
                if(inputText.equals("")){
                    
                    statusLabel.setText("Status:");
                }
                else if(!tdm.contains(inputText) && !isEditModeActive){
                    statusLabel.setText("Status: '" + inputText + "' was not found!");
                    createEditLabel.setText(CREATE_EDIT_LABEL_TEXT_CREATE);
                    
                    BehaviourForSwing.removeJLabelMouseBehaviour(addInfoLabel);
                    BehaviourForSwing.removeJLabelMouseBehaviour(hyperlinkLabel);
                }
                else if(tdm.contains(inputText) && !isEditModeActive){
                    checkTerm = tdm.getTerm(inputText);
                    definitionArea.setText(checkTerm.getDefinition());
                    createEditLabel.setText(CREATE_EDIT_LABEL_TEXT_EDIT);
                    initialise_addInfoMRC();
                    initialise_hyperlinkMRC();
                }
                
          }
        };
        
        BehaviourForSwing.addDefaultTextBoxBehaviour_Common(searchBox, searchBoxSeparator, textContentEvent);
        
    }
    
    
    private void initialise_createEditLabelMouseEvents(){
        
        createEditLabelMouseEntered = new MouseReleaseCode(){
            
          @Override
          public void runCode(){
              System.out.println("mouse ENTERED?");
            if(createEditLabel.getText().equals(CREATE_EDIT_LABEL_TEXT_EDIT)){
                    
                
                setForeground(searchEditStatus, definitionHeaderLabel.getForeground());
                setForeground(definitionEditStatus, definitionHeaderLabel.getForeground());
                
            }else if(createEditLabel.getText().equals(CREATE_EDIT_LABEL_TEXT_TOGGLE)){
                    
                setForeground(searchEditStatus, definitionHeaderLabel.getForeground());
                setForeground(definitionEditStatus, definitionHeaderLabel.getForeground());
            }
          }
        };
        
        createEditLabelMouseExited = new MouseReleaseCode(){
            
          @Override
          public void runCode(){
              if(createEditLabel.getText().equals(CREATE_EDIT_LABEL_TEXT_EDIT)){
                    
                    setForeground(searchEditStatus, editStatusPanel.getBackground());
                    setForeground(definitionEditStatus, editStatusPanel.getBackground());
            }else if(createEditLabel.getText().equals(CREATE_EDIT_LABEL_TEXT_TOGGLE)){
                    
                    setForeground(searchEditStatus, Color.WHITE);
                    setForeground(definitionEditStatus, Color.WHITE);
            }
          }
        };
        
        createEditLabelMousePressed = new MouseReleaseCode(){

            @Override
            public void runCode(){
                if(!isEditModeActive){
                    
                   
                }
            }
        };
        
        createEditLabelMouseReleased = new MouseReleaseCode(){
            
            @Override
            public void runCode(){
                
                if(createEditLabel.getText().equals(CREATE_EDIT_LABEL_TEXT_CREATE)){
                    isEditModeActive = true;
                    componentArray = getMyself().getComponents();
                    new AdditionalDetailsWindow(tdm , searchBox.getText(), isEditModeActive, true, getMyself()).setVisible(true);
                    
                    
                }
                else if(createEditLabel.getText().equals(CREATE_EDIT_LABEL_TEXT_EDIT)){
                    isEditModeActive = true;
                    searchBoxTextRevert = searchBox.getText();
                    definitionAreaTextRevert = definitionArea.getText();
                    setForeground(searchEditStatus, Color.WHITE);
                    setForeground(definitionEditStatus, Color.WHITE);
                    createEditLabel.setText(CREATE_EDIT_LABEL_TEXT_TOGGLE);
                    
                    definitionArea.setEditable(true);
                    
                }else if(createEditLabel.getText().equals(CREATE_EDIT_LABEL_TEXT_TOGGLE)){
                    isEditModeActive = false;
                    searchBox.setText(searchBoxTextRevert);
                    definitionArea.setText(definitionAreaTextRevert);
                    setForeground(searchEditStatus, editStatusPanel.getBackground());
                    setForeground(definitionEditStatus, editStatusPanel.getBackground());
                    createEditLabel.setText(CREATE_EDIT_LABEL_TEXT_EDIT);
                    
                    definitionArea.setEditable(false);
                }
                
            }
            
        };
        
        BehaviourForSwing.addDefaultJLabelMouseBehaviour_forEACH(createEditLabel,
                createEditLabelMouseEntered,
                createEditLabelMouseExited,
                createEditLabelMousePressed,
                createEditLabelMouseReleased);
    }
    
    public GASearch(TermDataManagement tdm) {
        
        
        initComponents();
        
        componentArray = this.getComponents();
        
        this.tdm = tdm;
        this.requestFocus();
        
        searchEditStatus.setForeground(editStatusPanel.getBackground());
        definitionEditStatus.setForeground(editStatusPanel.getBackground());
        
        BehaviourForSwing.removeJLabelMouseBehaviour(addInfoLabel);
        BehaviourForSwing.removeJLabelMouseBehaviour(hyperlinkLabel);
        
        //initialise_addInfoMRC();
        //initialise_hyperlinkMRC();
        initialise_textInfoContent();
        initialise_createEditLabelMouseEvents();
        
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
        jPanel3 = new javax.swing.JPanel();
        searchBox = new javax.swing.JTextField();
        searchBoxSeparator = new javax.swing.JSeparator();
        definitionHeaderLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        statusLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        definitionArea = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        hyperlinkLabel = new javax.swing.JLabel();
        addInfoLabel = new javax.swing.JLabel();
        createEditLabel = new javax.swing.JLabel();
        editStatusPanel = new javax.swing.JPanel();
        definitionEditStatus = new javax.swing.JLabel();
        searchEditStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(34, 40, 44));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        searchBox.setBackground(new java.awt.Color(34, 40, 44));
        searchBox.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        searchBox.setForeground(new java.awt.Color(255, 255, 255));
        searchBox.setText("jTextField1");
        searchBox.setToolTipText("Enter Search Term Here");
        searchBox.setBorder(null);
        searchBox.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        searchBox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchBoxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchBoxFocusLost(evt);
            }
        });
        jPanel3.add(searchBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 720, -1));

        searchBoxSeparator.setBackground(new java.awt.Color(153, 153, 153));
        searchBoxSeparator.setForeground(new java.awt.Color(153, 153, 153));
        jPanel3.add(searchBoxSeparator, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 720, 10));

        definitionHeaderLabel.setBackground(new java.awt.Color(34, 40, 44));
        definitionHeaderLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        definitionHeaderLabel.setForeground(new java.awt.Color(204, 204, 204));
        definitionHeaderLabel.setText("Definition");
        definitionHeaderLabel.setOpaque(true);
        jPanel3.add(definitionHeaderLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 70, -1));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 550, 720, 20));

        statusLabel.setBackground(new java.awt.Color(34, 40, 44));
        statusLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(204, 204, 204));
        statusLabel.setText("Status: Specified Term was not found!");
        statusLabel.setOpaque(true);
        statusLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                statusLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                statusLabelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                statusLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                statusLabelMouseReleased(evt);
            }
        });
        jPanel3.add(statusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, 720, 30));

        jScrollPane1.setBorder(null);

        definitionArea.setEditable(false);
        definitionArea.setBackground(new java.awt.Color(34, 40, 44));
        definitionArea.setColumns(20);
        definitionArea.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        definitionArea.setForeground(new java.awt.Color(255, 255, 255));
        definitionArea.setRows(5);
        definitionArea.setBorder(null);
        jScrollPane1.setViewportView(definitionArea);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 720, 450));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 760, 600));

        jPanel4.setBackground(new java.awt.Color(21, 25, 28));
        jPanel4.setMinimumSize(new java.awt.Dimension(190, 600));
        jPanel4.setPreferredSize(new java.awt.Dimension(190, 600));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        hyperlinkLabel.setBackground(new java.awt.Color(21, 25, 28));
        hyperlinkLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        hyperlinkLabel.setForeground(new java.awt.Color(204, 204, 204));
        hyperlinkLabel.setText("   View Hyperlinks");
        hyperlinkLabel.setOpaque(true);
        hyperlinkLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hyperlinkLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hyperlinkLabelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                hyperlinkLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                hyperlinkLabelMouseReleased(evt);
            }
        });
        jPanel4.add(hyperlinkLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 150, 40));

        addInfoLabel.setBackground(new java.awt.Color(21, 25, 28));
        addInfoLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        addInfoLabel.setForeground(new java.awt.Color(204, 204, 204));
        addInfoLabel.setText("   Additional Info");
        addInfoLabel.setOpaque(true);
        jPanel4.add(addInfoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 150, 40));

        createEditLabel.setBackground(new java.awt.Color(21, 25, 28));
        createEditLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        createEditLabel.setForeground(new java.awt.Color(204, 204, 204));
        createEditLabel.setText("   Create New");
        createEditLabel.setOpaque(true);
        createEditLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                createEditLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                createEditLabelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                createEditLabelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                createEditLabelMouseReleased(evt);
            }
        });
        jPanel4.add(createEditLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 150, 40));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, 190, 600));

        editStatusPanel.setBackground(new java.awt.Color(21, 25, 28));
        editStatusPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        definitionEditStatus.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        definitionEditStatus.setForeground(new java.awt.Color(255, 255, 255));
        definitionEditStatus.setText(">>");
        editStatusPanel.add(definitionEditStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 30, 30));

        searchEditStatus.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        searchEditStatus.setForeground(new java.awt.Color(255, 255, 255));
        searchEditStatus.setText(">>");
        editStatusPanel.add(searchEditStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 30, 30));

        jPanel1.add(editStatusPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void hyperlinkLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hyperlinkLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_hyperlinkLabelMouseEntered

    private void hyperlinkLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hyperlinkLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_hyperlinkLabelMouseExited

    private void hyperlinkLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hyperlinkLabelMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_hyperlinkLabelMousePressed

    private void hyperlinkLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hyperlinkLabelMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_hyperlinkLabelMouseReleased

    private void statusLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statusLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_statusLabelMouseEntered

    private void statusLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statusLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_statusLabelMouseExited

    private void statusLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statusLabelMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusLabelMousePressed

    private void statusLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statusLabelMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_statusLabelMouseReleased

    private void createEditLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createEditLabelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_createEditLabelMouseEntered

    private void createEditLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createEditLabelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_createEditLabelMouseExited

    private void createEditLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createEditLabelMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_createEditLabelMousePressed

    private void createEditLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createEditLabelMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_createEditLabelMouseReleased

    private void searchBoxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBoxFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBoxFocusGained

    private void searchBoxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBoxFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBoxFocusLost

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
            java.util.logging.Logger.getLogger(GASearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GASearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GASearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GASearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GASearch(new TermDataManagement(Paths.get("main method called on GASearch"))).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addInfoLabel;
    private javax.swing.JLabel createEditLabel;
    private javax.swing.JTextArea definitionArea;
    private javax.swing.JLabel definitionEditStatus;
    private javax.swing.JLabel definitionHeaderLabel;
    private javax.swing.JPanel editStatusPanel;
    private javax.swing.JLabel hyperlinkLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField searchBox;
    private javax.swing.JSeparator searchBoxSeparator;
    private javax.swing.JLabel searchEditStatus;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables
}
