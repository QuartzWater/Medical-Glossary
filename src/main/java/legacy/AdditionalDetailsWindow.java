/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package legacy;

import book.GraysAnatomy.Content;
import backend.TermDataManagement;
import java.awt.Color;
import java.nio.file.Paths;
import java.util.Map;
import javax.swing.text.AbstractDocument;

/**
 *
 * @author BRIN
 */
public class AdditionalDetailsWindow extends javax.swing.JFrame {

    /**
     * Creates new form AdditionalDetailsWindow
     */
    private final String EDIT_LABEL_DEFAULT = "   Edit";
    private final String EDIT_LABEL_EDITMODE = "   Toggle Edit";
    private boolean isEditModeEnabled  = false;
    private boolean initialisedVIACreate = false;
    
    private TermDataManagement tdm;
    private GASearch parentGASearch;
    private String inputTerm;
    
    
    private Map<Integer, String[]> entryMap;
    private String[] globalArrayNextEntry;
    
    private final String STATUS_CREATE_MODE = "In create mode, can not toggle Edit.";
    private final String STATUS_EDIT_MODE = "Status: " + "You can edit only page field. Use Next Entry to help in choosing.";
    private final String STATUS_NON_EDIT_MODE = "Status: " + "Read Only mode, click Edit to toggle between Edit and Read Only.";
    
    private int entryMapSize;
    private int currentMapIndex = 0;
    
    private AdditionalDetailsWindow getMyself(){
        
        return this;
    }
    
    private void initialiseBackLabelBehaviour(){
        
        MouseReleaseCode backLabelCode = new MouseReleaseCode(){
            
          @Override
          public void runCode(){
              
              
              
              parentGASearch.setVisible(true);
              getMyself().dispose();
          }
        };
        
        BehaviourForSwing.addDefaultJLabelMouseBehaviour(backLabel, backLabelCode);
    }
    
    private void initialiseNextEntryLabelBehaviour(){
        
        MouseReleaseCode nextEntryLabelCode  = new MouseReleaseCode(){
            
            @Override
            public void runCode(){
                
                if(currentMapIndex + 1 == entryMapSize){
                 
                    currentMapIndex = 0;
                }
                else{
                    currentMapIndex++;
                }
                
                nextEntryLabel.setText("   Next Entry {" + Integer.toString(currentMapIndex + 1) + "/" + Integer.toString(entryMapSize) + ")");
                
                globalArrayNextEntry = entryMap.get(currentMapIndex);
                
                chapterField.setText(globalArrayNextEntry[0]);
                majorTopicField.setText(globalArrayNextEntry[1]);
                subTopicArea.setText(globalArrayNextEntry[2]);
            }
        };
        
        BehaviourForSwing.addDefaultJLabelMouseBehaviour(nextEntryLabel, nextEntryLabelCode);
        
    }
    
    private void initialiseSaveLabelBehaviour(){
        
        MouseReleaseCode saveLabelCode = new MouseReleaseCode(){
            
            @Override
            public void runCode(){
                
                new HyperlinkEditWindow(tdm, inputTerm ,isEditModeEnabled, initialisedVIACreate).setVisible(true);
            }
        };
        
        
        BehaviourForSwing.addDefaultJLabelMouseBehaviour(saveLabel, saveLabelCode);
    }
    
    public AdditionalDetailsWindow(TermDataManagement tdm, String inputTerm, boolean isEditModeEnabled, boolean initialisedVIACreate, GASearch parentGASearch) {
        initComponents();
        initialiseSaveLabelBehaviour();
        initialiseBackLabelBehaviour();
        
        this.parentGASearch = parentGASearch;
        
        
        
        this.isEditModeEnabled = isEditModeEnabled;
        this.initialisedVIACreate = initialisedVIACreate;
        
        this.tdm = tdm;
        this.inputTerm = inputTerm;
        
        if(isEditModeEnabled && initialisedVIACreate){
            
            editLabel.setText(EDIT_LABEL_EDITMODE);
            chapterField.setText("<Chapter Name>");
            majorTopicField.setText("<Major Topic Name>");
            subTopicArea.setText("<Subtopic Name> \n" +
"<Hint: You can enter page number only, and program will decide what all details you might want (Next Entry button will activate accordingly)>");
            statusLabel.setText(STATUS_CREATE_MODE);
            
            BehaviourForSwing.removeJLabelMouseBehaviour(editLabel);
            BehaviourForSwing.removeJLabelMouseBehaviour(nextEntryLabel);
            BehaviourForSwing.removeJLabelMouseBehaviour(viewHyperlinkLabel);
            
        }
        else if(isEditModeEnabled){
            
            
            editLabel.setText(EDIT_LABEL_EDITMODE);
            chapterField.setText(tdm.getTerm(inputTerm).getChapter());
            majorTopicField.setText(tdm.getTerm(inputTerm).getMajorTopic());
            subTopicArea.setText(tdm.getTerm(inputTerm).getSubtopic());
            pageTextBox.setText(Integer.toString(tdm.getTerm(inputTerm).getPage()));
            pageTextBox.setEditable(true);
            statusLabel.setText(STATUS_EDIT_MODE);
        }
        else{ // ie ReadOnlyMode
            editLabel.setText(EDIT_LABEL_DEFAULT);
            chapterField.setText(tdm.getTerm(inputTerm).getChapter());
            majorTopicField.setText(tdm.getTerm(inputTerm).getMajorTopic());
            subTopicArea.setText(tdm.getTerm(inputTerm).getSubtopic());
            pageTextBox.setText(Integer.toString(tdm.getTerm(inputTerm).getPage()));
            pageTextBox.setEditable(false);
            statusLabel.setText(STATUS_NON_EDIT_MODE); 
        }
        
        
        
        TextBoxContentEventCode filteredContentCode = new TextBoxContentEventCode(){
            
            @Override
            public void runCode(){
                
                String page = pageTextBox.getText();
                if(!page.isBlank()){
                    statusLabel.setText(STATUS_CREATE_MODE);
                    System.out.println("Not blank");
                    Content content = Content.THE_BODY; // Any variable initialisation can work since we only need the static method for now
                    
                    entryMap = content.getEverythingByPage(Integer.parseInt(page));
                    entryMapSize = entryMap.size();
                    globalArrayNextEntry = entryMap.get(0); // default entry
                    ;
                    if(entryMap.size() > 1){
                        
                        statusLabel.setText("Status: " + Integer.toString(entryMapSize) + " entries found! Use Next Entry to cycle through them!");
                        nextEntryLabel.setText(nextEntryLabel.getText() + " (1/" + Integer.toString(entryMapSize) + ")");
                        initialiseNextEntryLabelBehaviour();
                        
                    }
                    else{
                        nextEntryLabel.setText("   Next Entry");
                        BehaviourForSwing.removeJLabelMouseBehaviour(nextEntryLabel);
                        
                        if(initialisedVIACreate){
                            statusLabel.setText(STATUS_CREATE_MODE);
                        }
                        else{
                
                            statusLabel.setText(STATUS_EDIT_MODE);
                        }
                    }
                    
                    if(globalArrayNextEntry != null){
                        
                    chapterField.setText(globalArrayNextEntry[0]);
                    majorTopicField.setText(globalArrayNextEntry[1]);
                    subTopicArea.setText(globalArrayNextEntry[2]); 
                    
                    
                    }
                    else{
                        
                        statusLabel.setText("No entry found for page - " + page);
                        BehaviourForSwing.removeJLabelMouseBehaviour(saveLabel);
                    }
                }
                else{
                    System.out.println("blank");
                }
            }
            
        };
        
        BehaviourForSwing.addInputFilteredTextBoxBehaviour_Common(pageTextBox, pageSeparator,4, filteredContentCode);
        
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
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        nextEntryLabel = new javax.swing.JLabel();
        pageTextBox = new javax.swing.JTextField();
        pageSeparator = new javax.swing.JSeparator();
        editLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        backLabel = new javax.swing.JLabel();
        viewHyperlinkLabel = new javax.swing.JLabel();
        saveLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        majorTopicField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        subTopicArea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        chapterField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(34, 40, 44));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(21, 25, 28));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 500));

        jPanel3.setBackground(new java.awt.Color(21, 25, 28));
        jPanel3.setMinimumSize(new java.awt.Dimension(190, 500));
        jPanel3.setPreferredSize(new java.awt.Dimension(190, 500));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setBackground(new java.awt.Color(34, 40, 44));
        jLabel5.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Page:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        nextEntryLabel.setBackground(new java.awt.Color(21, 25, 28));
        nextEntryLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        nextEntryLabel.setForeground(new java.awt.Color(204, 204, 204));
        nextEntryLabel.setText("   Next Entry");
        nextEntryLabel.setOpaque(true);
        jPanel3.add(nextEntryLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 150, 40));

        pageTextBox.setBackground(new java.awt.Color(34, 40, 44));
        pageTextBox.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        pageTextBox.setForeground(new java.awt.Color(255, 255, 255));
        pageTextBox.setText("6");
        pageTextBox.setBorder(null);
        pageTextBox.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel3.add(pageTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 150, -1));
        jPanel3.add(pageSeparator, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 150, 10));

        editLabel.setBackground(new java.awt.Color(21, 25, 28));
        editLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        editLabel.setForeground(new java.awt.Color(204, 204, 204));
        editLabel.setText("   Edit");
        editLabel.setOpaque(true);
        jPanel3.add(editLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 150, 40));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 150, 10));

        backLabel.setBackground(new java.awt.Color(21, 25, 28));
        backLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        backLabel.setForeground(new java.awt.Color(204, 204, 204));
        backLabel.setText("   Back");
        backLabel.setOpaque(true);
        jPanel3.add(backLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 150, 40));

        viewHyperlinkLabel.setBackground(new java.awt.Color(21, 25, 28));
        viewHyperlinkLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        viewHyperlinkLabel.setForeground(new java.awt.Color(204, 204, 204));
        viewHyperlinkLabel.setText("   View Hyperlinks");
        viewHyperlinkLabel.setOpaque(true);
        jPanel3.add(viewHyperlinkLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 150, 40));

        saveLabel.setBackground(new java.awt.Color(21, 25, 28));
        saveLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        saveLabel.setForeground(new java.awt.Color(204, 204, 204));
        saveLabel.setText("   Save");
        saveLabel.setOpaque(true);
        jPanel3.add(saveLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 150, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 0, -1, -1));

        jPanel4.setBackground(new java.awt.Color(34, 40, 44));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        majorTopicField.setBackground(new java.awt.Color(34, 40, 44));
        majorTopicField.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        majorTopicField.setForeground(new java.awt.Color(255, 255, 255));
        majorTopicField.setText("<Major Topic Name>");
        majorTopicField.setToolTipText("Enter Search Term Here");
        majorTopicField.setBorder(null);
        majorTopicField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel4.add(majorTopicField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 520, -1));

        jScrollPane1.setBorder(null);

        subTopicArea.setEditable(false);
        subTopicArea.setBackground(new java.awt.Color(34, 40, 44));
        subTopicArea.setColumns(20);
        subTopicArea.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        subTopicArea.setForeground(new java.awt.Color(255, 255, 255));
        subTopicArea.setLineWrap(true);
        subTopicArea.setRows(5);
        subTopicArea.setText("<Subtopic Name> \n<Hint: You can enter page number only, and program will decide what all details you might want (Next Entry button will activate accordingly)>");
        subTopicArea.setWrapStyleWord(true);
        subTopicArea.setBorder(null);
        jScrollPane1.setViewportView(subTopicArea);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 520, 120));

        jLabel3.setBackground(new java.awt.Color(34, 40, 44));
        jLabel3.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Major Topic:");
        jLabel3.setOpaque(true);
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 130, -1));

        chapterField.setBackground(new java.awt.Color(34, 40, 44));
        chapterField.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        chapterField.setForeground(new java.awt.Color(255, 255, 255));
        chapterField.setText("<Chapter Name>");
        chapterField.setToolTipText("Enter Search Term Here");
        chapterField.setBorder(null);
        chapterField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel4.add(chapterField, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 520, -1));

        jLabel7.setBackground(new java.awt.Color(34, 40, 44));
        jLabel7.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("Subtopic:");
        jLabel7.setOpaque(true);
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 110, -1));

        statusLabel.setBackground(new java.awt.Color(34, 40, 44));
        statusLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(204, 204, 204));
        statusLabel.setText("Status:");
        statusLabel.setOpaque(true);
        jPanel4.add(statusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 520, -1));

        jLabel2.setBackground(new java.awt.Color(34, 40, 44));
        jLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Chapter:");
        jLabel2.setOpaque(true);
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 70, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 560, 500));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

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
            java.util.logging.Logger.getLogger(AdditionalDetailsWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdditionalDetailsWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdditionalDetailsWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdditionalDetailsWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdditionalDetailsWindow(new TermDataManagement(Paths.get("main method of additional Details window directly called")), null, false, false, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backLabel;
    private javax.swing.JTextField chapterField;
    private javax.swing.JLabel editLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField majorTopicField;
    private javax.swing.JLabel nextEntryLabel;
    private javax.swing.JSeparator pageSeparator;
    private javax.swing.JTextField pageTextBox;
    private javax.swing.JLabel saveLabel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JTextArea subTopicArea;
    private javax.swing.JLabel viewHyperlinkLabel;
    // End of variables declaration//GEN-END:variables
}
