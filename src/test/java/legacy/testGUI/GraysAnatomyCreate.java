/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package legacy.testGUI;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author BRIN
 */
public class GraysAnatomyCreate extends javax.swing.JFrame {

    /**
     * Creates new form GraysAnatomyCreate
     */
    
    private static GraysAnatomyForm parentFrame;
    private String oldSpelling;
    private String oldDefinition;
    private boolean oldHyperlink_1_STATE;
    private boolean oldHyperlink_2_STATE;
    private boolean oldHyperlink_3_STATE;
    
    private boolean checkboxSTATECHANGED_1 = false;
    private boolean checkboxSTATECHANGED_2 = false;
    private boolean checkboxSTATECHANGED_3 = false;
    
    public void enableEncap_Hyperlink_TextBox(int whatTextBox, boolean shouldEnable){
        if(shouldEnable){
        switch (whatTextBox) {
            case 1:
            {
                hyperlinkCheckBox_1.setSelected(shouldEnable);
                encapsulateText_1.setEditable(shouldEnable);
                hyperlinkText_1.setEditable(shouldEnable);
                break;
            }
            case 2:
            {
                hyperlinkCheckBox_2.setSelected(shouldEnable);
                encapsulateText_2.setEditable(shouldEnable);
                hyperlinkText_2.setEditable(shouldEnable);
                break;
            }
            case 3:
            {
                hyperlinkCheckBox_3.setSelected(shouldEnable);
                encapsulateText_3.setEditable(shouldEnable);
                hyperlinkText_3.setEditable(shouldEnable);
                break;
            }
            default:
                break;
        }
        }
    }
    
    public GraysAnatomyCreate(GraysAnatomyForm parent) {
        initComponents();
        
        //TODO: add condition for supporting future new books
        chapterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        
        
        //The following code prevents auto scroll of text area to bottom when
        //Application programmatically sets the text of termDefiningTextArea (jTextArea) 
        //in case of vertical text overflow
        
        //Avoid this mistake: ALWAYS ADD THIS CODE **BEFORE** ANY CODE THAT SETS THE TEXT OF TEXT AREA
        //Otherwise this code would be useless
        DefaultCaret caret = (DefaultCaret)termDefiningTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        /**-------------------------------------------------------------------**/

        parentFrame = parent;
        this.termSpellingTextBox.setText(parentFrame.getSearchBoxText());
        oldSpelling = parentFrame.getSearchBoxText();
        this.termDefiningTextArea.setText(parentFrame.getDefiningText());
        oldDefinition = parentFrame.getDefiningText();
        
        //Following code restore the state of J Text Area to default. The above code may cause
        //unexpected behaviour while trying to edit the J text Area (where required) if not followed by following code
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        enableEncap_Hyperlink_TextBox(1, parentFrame.shouldHyperlinkCheckMarkBeEnabled(1));
        enableEncap_Hyperlink_TextBox(2, parentFrame.shouldHyperlinkCheckMarkBeEnabled(2));
        enableEncap_Hyperlink_TextBox(3, parentFrame.shouldHyperlinkCheckMarkBeEnabled(3));
        
        oldHyperlink_1_STATE = parentFrame.shouldHyperlinkCheckMarkBeEnabled(1);
        oldHyperlink_2_STATE = parentFrame.shouldHyperlinkCheckMarkBeEnabled(2);
        oldHyperlink_3_STATE = parentFrame.shouldHyperlinkCheckMarkBeEnabled(3);
        
        if(!parentFrame.getEncapsulated_1().equals("<Hyperlink 1 for given term is not available>")){
            encapsulateText_1.setText(parentFrame.getEncapsulated_1());
            hyperlinkText_1.setText(parentFrame.getHyperlink_1());
        }
        if(!parentFrame.getEncapsulated_2().equals("<Hyperlink 2 for given term is not available>")){
        encapsulateText_2.setText(parentFrame.getEncapsulated_2());
        hyperlinkText_2.setText(parentFrame.getHyperlink_2());
        }
        if(!parentFrame.getEncapsulated_3().equals("<Hyperlink 3 for given term is not available>")){
        encapsulateText_3.setText(parentFrame.getEncapsulated_3());
        hyperlinkText_3.setText(parentFrame.getHyperlink_3());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        termSpellingTextBox = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        termDefiningTextArea = new javax.swing.JTextArea();
        hyperlinkCheckBox_1 = new javax.swing.JCheckBox();
        hyperlinkCheckBox_2 = new javax.swing.JCheckBox();
        hyperlinkCheckBox_3 = new javax.swing.JCheckBox();
        saveButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        encapsulateText_1 = new javax.swing.JTextField();
        hyperlinkText_1 = new javax.swing.JTextField();
        encapsulateText_2 = new javax.swing.JTextField();
        hyperlinkText_2 = new javax.swing.JTextField();
        encapsulateText_3 = new javax.swing.JTextField();
        hyperlinkText_3 = new javax.swing.JTextField();
        hyperlinkTestButton_1 = new javax.swing.JButton();
        hyperlinkTestButton_2 = new javax.swing.JButton();
        hyperlinkTestButton_3 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        chapterComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        majorTopicComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        subtopicComboBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        pageField = new javax.swing.JTextField();
        pageRangeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create a new term : Gray's Anatomy");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        termSpellingTextBox.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        termSpellingTextBox.setText("<The Term>");

        jLabel1.setText("<--- The spelling of the new term");

        termDefiningTextArea.setColumns(20);
        termDefiningTextArea.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        termDefiningTextArea.setLineWrap(true);
        termDefiningTextArea.setRows(5);
        termDefiningTextArea.setText("<The definition of new term>");
        termDefiningTextArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(termDefiningTextArea);

        hyperlinkCheckBox_1.setText("Add a 1st Hyperlink");
        hyperlinkCheckBox_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hyperlinkCheckBox_1ActionPerformed(evt);
            }
        });

        hyperlinkCheckBox_2.setText("Add a 2nd Hyperlink");
        hyperlinkCheckBox_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hyperlinkCheckBox_2ActionPerformed(evt);
            }
        });

        hyperlinkCheckBox_3.setText("Add a 3rd Hyperlink");
        hyperlinkCheckBox_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hyperlinkCheckBox_3ActionPerformed(evt);
            }
        });

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Status:");
        jLabel2.setMaximumSize(new java.awt.Dimension(37, 20));
        jLabel2.setMinimumSize(new java.awt.Dimension(37, 20));

        encapsulateText_1.setEditable(false);
        encapsulateText_1.setText("<Type here the text that would encapsulate the hyperlink. Try to keep it short>");
        encapsulateText_1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                encapsulateText_1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                encapsulateText_1FocusLost(evt);
            }
        });
        encapsulateText_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encapsulateText_1ActionPerformed(evt);
            }
        });

        hyperlinkText_1.setEditable(false);
        hyperlinkText_1.setText("<Actual Hyperlink>");
        hyperlinkText_1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hyperlinkText_1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hyperlinkText_1FocusLost(evt);
            }
        });
        hyperlinkText_1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hyperlinkText_1KeyReleased(evt);
            }
        });

        encapsulateText_2.setEditable(false);
        encapsulateText_2.setText("<Type here the text that would encapsulate the hyperlink. Try to keep it short>");
        encapsulateText_2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                encapsulateText_2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                encapsulateText_2FocusLost(evt);
            }
        });
        encapsulateText_2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                encapsulateText_2KeyReleased(evt);
            }
        });

        hyperlinkText_2.setEditable(false);
        hyperlinkText_2.setText("<Actual Hyperlink>");
        hyperlinkText_2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hyperlinkText_2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hyperlinkText_2FocusLost(evt);
            }
        });
        hyperlinkText_2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hyperlinkText_2KeyReleased(evt);
            }
        });

        encapsulateText_3.setEditable(false);
        encapsulateText_3.setText("<Type here the text that would encapsulate the hyperlink. Try to keep it short>");
        encapsulateText_3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                encapsulateText_3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                encapsulateText_3FocusLost(evt);
            }
        });

        hyperlinkText_3.setEditable(false);
        hyperlinkText_3.setText("<Actual Hyperlink>");
        hyperlinkText_3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hyperlinkText_3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hyperlinkText_3FocusLost(evt);
            }
        });
        hyperlinkText_3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                hyperlinkText_3KeyReleased(evt);
            }
        });

        hyperlinkTestButton_1.setText("Test");
        hyperlinkTestButton_1.setEnabled(false);
        hyperlinkTestButton_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hyperlinkTestButton_1ActionPerformed(evt);
            }
        });

        hyperlinkTestButton_2.setText("Test");
        hyperlinkTestButton_2.setEnabled(false);
        hyperlinkTestButton_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hyperlinkTestButton_2ActionPerformed(evt);
            }
        });

        hyperlinkTestButton_3.setText("Test");
        hyperlinkTestButton_3.setEnabled(false);
        hyperlinkTestButton_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hyperlinkTestButton_3ActionPerformed(evt);
            }
        });

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Chapter:");

        chapterComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chapterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        chapterComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chapterComboBoxItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Major Topic:");

        majorTopicComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        majorTopicComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        majorTopicComboBox.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Subtopic:");

        subtopicComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        subtopicComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        subtopicComboBox.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Page:");

        pageField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pageField.setText("page");
        pageField.setEnabled(false);
        pageField.setMinimumSize(new java.awt.Dimension(79, 24));

        pageRangeLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pageRangeLabel.setText("Page number range:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(termSpellingTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(hyperlinkCheckBox_3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(hyperlinkTestButton_3))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(hyperlinkCheckBox_2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(hyperlinkTestButton_2))
                            .addComponent(hyperlinkText_3)
                            .addComponent(encapsulateText_3, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                            .addComponent(hyperlinkText_2)
                            .addComponent(encapsulateText_2)
                            .addComponent(hyperlinkText_1)
                            .addComponent(encapsulateText_1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(hyperlinkCheckBox_1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(hyperlinkTestButton_1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(chapterComboBox, 0, 290, Short.MAX_VALUE)
                                .addComponent(majorTopicComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(subtopicComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pageField, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pageRangeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(termSpellingTextBox)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(hyperlinkCheckBox_1)
                                    .addComponent(hyperlinkTestButton_1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(encapsulateText_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hyperlinkText_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(hyperlinkCheckBox_2)
                                    .addComponent(hyperlinkTestButton_2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(encapsulateText_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hyperlinkText_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(hyperlinkCheckBox_3)
                                    .addComponent(hyperlinkTestButton_3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(encapsulateText_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hyperlinkText_3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chapterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(majorTopicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subtopicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(pageField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pageRangeLabel))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(statusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void simulateKeyRelease(Component comp){
        
        KeyEvent evt = new KeyEvent(
                comp, 
                KeyEvent.KEY_RELEASED, 
                System.currentTimeMillis(), 
                0, 
                KeyEvent.VK_0, 
                '0');
        hyperlinkText_1KeyReleased(evt);
        hyperlinkText_2KeyReleased(evt);
        hyperlinkText_3KeyReleased(evt);
    }
    private void hyperlinkCheckBox_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hyperlinkCheckBox_3ActionPerformed
        // TODO add your handling code here:
        if(hyperlinkCheckBox_3.isSelected()){
            encapsulateText_3.setEditable(true);
            hyperlinkText_3.setEditable(true);
            if(!hyperlinkTestButton_3.isEnabled())
            saveButton.setEnabled(false);
        }
        else{
            encapsulateText_3.setEditable(false);
            hyperlinkText_3.setEditable(false);
            
            if(!hyperlinkCheckBox_1.isSelected() && !hyperlinkCheckBox_2.isSelected())
            saveButton.setEnabled(true);
            
        }
        
        boolean checkSTATE = hyperlinkCheckBox_3.isSelected();
        
        //following code may seem redundant especially the if statement
        //howvever this is more readable to me.
        if(checkSTATE == oldHyperlink_3_STATE){
            checkboxSTATECHANGED_3 = false;
        }
        else{
            checkboxSTATECHANGED_3 = true;
        }
    }//GEN-LAST:event_hyperlinkCheckBox_3ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.setVisible(false);
        parentFrame.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        
        String outputFilePath = new mainFrame().getRootFolder() + new GraysAnatomyForm().getFilePath() + "\\";
        File outputFile = new File(outputFilePath + termSpellingTextBox.getText() + ".txt");
        
        try {
            if(!outputFile.exists()){
        outputFile.createNewFile();
            }
            
            
            
            boolean checkEdit = oldSpelling.equals(termSpellingTextBox.getText());
            boolean checkEdit_definition = oldDefinition.equals(termDefiningTextArea.getText());
            
            if(!checkEdit || !checkEdit_definition || checkboxSTATECHANGED_1 || checkboxSTATECHANGED_2 || checkboxSTATECHANGED_3){
                System.out.println(oldSpelling);
                System.out.println(new File(outputFilePath + oldSpelling +".txt").delete());
                System.out.println(new File(outputFilePath + oldSpelling + "_properties.properties").delete());
            }
            
            //Following code ensure the values to be compared are updated to latest edit
            oldSpelling = termSpellingTextBox.getText();
            oldDefinition = termDefiningTextArea.getText();
            oldHyperlink_1_STATE = hyperlinkCheckBox_1.isSelected();
            oldHyperlink_2_STATE = hyperlinkCheckBox_2.isSelected();
            oldHyperlink_3_STATE = hyperlinkCheckBox_3.isSelected();
            //--------------------------------------------------------------------------
            
            parentFrame.setSearchBoxText(oldSpelling);
            parentFrame.setDefiningText(termDefiningTextArea.getText());
            
            
            
            FileWriter fileOutStream = new FileWriter(outputFile);
        fileOutStream.write(termDefiningTextArea.getText());
        fileOutStream.flush();
        fileOutStream.close();
        
        Properties contentProperties = new Properties();
        if(hyperlinkCheckBox_1.isSelected()){
            contentProperties.setProperty("encapsulated_1", encapsulateText_1.getText());
            contentProperties.setProperty("hyperlink_1", hyperlinkText_1.getText());
        }
        if(hyperlinkCheckBox_2.isSelected()){
            contentProperties.setProperty("encapsulated_2", encapsulateText_2.getText());
            contentProperties.setProperty("hyperlink_2", hyperlinkText_2.getText());
            
        }
        if(hyperlinkCheckBox_3.isSelected()){
            contentProperties.setProperty("encapsulated_3", encapsulateText_3.getText());
            contentProperties.setProperty("hyperlink_3", hyperlinkText_3.getText());
        }
        
        FileOutputStream propOut = new FileOutputStream(outputFilePath + termSpellingTextBox.getText() + "_properties.properties");
        contentProperties.store(propOut, null);
        propOut.close();
        
        KeyEvent keyReleaseEvent = new KeyEvent(
                    this.saveButton, 
                    KeyEvent.KEY_RELEASED, 
                    System.currentTimeMillis(), 
                    0, 
                    KeyEvent.VK_0, 
                    '0');
            
            parentFrame.callTermSpellingTextBoxKeyReleased(keyReleaseEvent);
        }
        catch(IOException e){
            e.getStackTrace();
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void encapsulateText_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encapsulateText_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_encapsulateText_1ActionPerformed

    private void hyperlinkCheckBox_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hyperlinkCheckBox_1ActionPerformed
        // TODO add your handling code here:
        if(hyperlinkCheckBox_1.isSelected()){
            encapsulateText_1.setEditable(true);
            hyperlinkText_1.setEditable(true);
            if(!hyperlinkTestButton_1.isEnabled())
            saveButton.setEnabled(false);
        }
        else{
            
            encapsulateText_1.setEditable(false);
            hyperlinkText_1.setEditable(false);
            if(!hyperlinkCheckBox_2.isSelected() && !hyperlinkCheckBox_3.isSelected())
            saveButton.setEnabled(true);
        }
        
        boolean checkSTATE = hyperlinkCheckBox_1.isSelected();
        //following code may seem redundant especially the if statement
        //howvever this is more readable to me.
        if(checkSTATE == oldHyperlink_1_STATE){
            checkboxSTATECHANGED_1 = false;
        }
        else{
            checkboxSTATECHANGED_1 = true;
        }
    }//GEN-LAST:event_hyperlinkCheckBox_1ActionPerformed

    private void hyperlinkCheckBox_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hyperlinkCheckBox_2ActionPerformed
        // TODO add your handling code here:
        if(hyperlinkCheckBox_2.isSelected()){
            encapsulateText_2.setEditable(true);
            hyperlinkText_2.setEditable(true);
            if(!hyperlinkTestButton_2.isEnabled())
            saveButton.setEnabled(false);
        }
        else{
            encapsulateText_2.setEditable(false);
            hyperlinkText_2.setEditable(false);
            if(!hyperlinkCheckBox_1.isSelected() && !hyperlinkCheckBox_3.isSelected())
            saveButton.setEnabled(true);
            
        }
        boolean checkSTATE = hyperlinkCheckBox_2.isSelected();
        //following code may seem redundant especially the if statement
        //howvever this is more readable to me.
        if(checkSTATE == oldHyperlink_2_STATE){
            checkboxSTATECHANGED_2 = false;
        }
        else{
            checkboxSTATECHANGED_2 = true;
        }
    }//GEN-LAST:event_hyperlinkCheckBox_2ActionPerformed

    //Mode = 1 if focus gained
    //Mode = 0 if focus lost
    //Type = 1 if encapsulated Text Box
    //Type = 2 if hyperlink Text Box
    private void textBoxBehaviour (JTextField jtb, int mode, int type){
        
        if(mode == 1){
            jtb.setText("");
        }
        else if(mode == 0 && type == 1){
            if(jtb.getText().equals("")){
                jtb.setText("<Type here the text that would encapsulate the hyperlink. Try to keep it short>");
            }
        }
        else if(mode == 0 && type == 2){
            if(jtb.getText().equals("")){
                jtb.setText("<Actual Hyperlink>");
            }
        }
        else{
            System.err.println("Whatever the F happened here. THIS REQUIRES IMMEDIATE ATTENTION : private void textBoxBehaviour");
        }
    }
    
    private String encapDefault = "<Type here the text that would encapsulate the hyperlink. Try to keep it short>";
    private String hyperDefault = "<Actual Hyperlink>";
    
    private void encapsulateText_1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_encapsulateText_1FocusGained
        // TODO add your handling code here:
        if(hyperlinkCheckBox_1.isSelected() && encapsulateText_1.getText().equals(encapDefault))
        textBoxBehaviour(encapsulateText_1, 1, 1);
    }//GEN-LAST:event_encapsulateText_1FocusGained

    private void encapsulateText_1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_encapsulateText_1FocusLost
        // TODO add your handling code here:
        if(hyperlinkCheckBox_1.isSelected() && encapsulateText_1.getText().isEmpty())
        textBoxBehaviour(encapsulateText_1, 0, 1);
    }//GEN-LAST:event_encapsulateText_1FocusLost

    private void hyperlinkText_1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hyperlinkText_1FocusGained
        // TODO add your handling code here:
        if(hyperlinkCheckBox_1.isSelected() && hyperlinkText_1.getText().equals(hyperDefault))
        textBoxBehaviour(hyperlinkText_1, 1, 2);
    }//GEN-LAST:event_hyperlinkText_1FocusGained

    private void hyperlinkText_1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hyperlinkText_1FocusLost
        // TODO add your handling code here:
        if(hyperlinkCheckBox_1.isSelected() && hyperlinkText_1.getText().isEmpty())
        textBoxBehaviour(hyperlinkText_1, 0, 2);
    }//GEN-LAST:event_hyperlinkText_1FocusLost

    private void encapsulateText_2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_encapsulateText_2FocusGained
        // TODO add your handling code here:
        if(hyperlinkCheckBox_2.isSelected() && encapsulateText_2.getText().equals(encapDefault))
        textBoxBehaviour(encapsulateText_2, 1, 1);
    }//GEN-LAST:event_encapsulateText_2FocusGained

    private void encapsulateText_2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_encapsulateText_2FocusLost
        // TODO add your handling code here:
        if(hyperlinkCheckBox_2.isSelected() && encapsulateText_2.getText().isEmpty())
        textBoxBehaviour(encapsulateText_2, 0, 1);
    }//GEN-LAST:event_encapsulateText_2FocusLost

    private void hyperlinkText_2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hyperlinkText_2FocusGained
        // TODO add your handling code here:
        if(hyperlinkCheckBox_2.isSelected() && hyperlinkText_2.getText().equals(hyperDefault))
        textBoxBehaviour(hyperlinkText_2, 1, 2);
    }//GEN-LAST:event_hyperlinkText_2FocusGained

    private void hyperlinkText_2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hyperlinkText_2FocusLost
        // TODO add your handling code here:
        if(hyperlinkCheckBox_2.isSelected() && hyperlinkText_2.getText().isEmpty())
        textBoxBehaviour(hyperlinkText_2, 0, 2);
    }//GEN-LAST:event_hyperlinkText_2FocusLost

    private void encapsulateText_3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_encapsulateText_3FocusGained
        // TODO add your handling code here:
        if(hyperlinkCheckBox_3.isSelected() && encapsulateText_2.getText().equals(encapDefault))
        textBoxBehaviour(encapsulateText_3, 1, 1);
    }//GEN-LAST:event_encapsulateText_3FocusGained

    private void encapsulateText_3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_encapsulateText_3FocusLost
        // TODO add your handling code here:
        if(hyperlinkCheckBox_3.isSelected() && encapsulateText_3.getText().isEmpty())
        textBoxBehaviour(encapsulateText_3, 0, 1);
    }//GEN-LAST:event_encapsulateText_3FocusLost

    private void hyperlinkText_3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hyperlinkText_3FocusGained
        // TODO add your handling code here:
        if(hyperlinkCheckBox_3.isSelected() && hyperlinkText_3.getText().equals(hyperDefault))
        textBoxBehaviour(hyperlinkText_3, 1, 2);
    }//GEN-LAST:event_hyperlinkText_3FocusGained

    private void hyperlinkText_3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hyperlinkText_3FocusLost
        // TODO add your handling code here:
        if(hyperlinkCheckBox_3.isSelected() && hyperlinkText_3.getText().isEmpty())
        textBoxBehaviour(hyperlinkText_3, 0, 2);
    }//GEN-LAST:event_hyperlinkText_3FocusLost

    
    private void internetBrowse(String url){
        
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    URI uri = new URI(url);
                    desktop.browse(uri);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
        
    }
    private void hyperlinkTestButton_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hyperlinkTestButton_1ActionPerformed
        // TODO add your handling code here:
        internetBrowse(hyperlinkText_1.getText());
    }//GEN-LAST:event_hyperlinkTestButton_1ActionPerformed

    private void hyperlinkTestButton_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hyperlinkTestButton_2ActionPerformed
        // TODO add your handling code here:
        internetBrowse(hyperlinkText_2.getText());
    }//GEN-LAST:event_hyperlinkTestButton_2ActionPerformed

    private void hyperlinkTestButton_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hyperlinkTestButton_3ActionPerformed
        // TODO add your handling code here:
        internetBrowse(hyperlinkText_3.getText());
    }//GEN-LAST:event_hyperlinkTestButton_3ActionPerformed

    private boolean checkCorrectSyntax(String checkString){
        if((checkString.startsWith("https://") || checkString.startsWith("http://"))){
            if (!checkString.equals("https://") && !checkString.equals("http://")) {
                saveButton.setEnabled(true);
                return true;
                
            }
            saveButton.setEnabled(false);
            return false;
        }
        else {
            saveButton.setEnabled(false);
            return false;
        }
    }
    private void hyperlinkText_1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hyperlinkText_1KeyReleased
        // TODO add your handling code here:
        if(checkCorrectSyntax(hyperlinkText_1.getText())){
            hyperlinkTestButton_1.setEnabled(true);
            
        }
        else{
            hyperlinkTestButton_1.setEnabled(false);
        }
    }//GEN-LAST:event_hyperlinkText_1KeyReleased

    private void encapsulateText_2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_encapsulateText_2KeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_encapsulateText_2KeyReleased

    private void hyperlinkText_2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hyperlinkText_2KeyReleased
        // TODO add your handling code here:
        if(checkCorrectSyntax(hyperlinkText_2.getText())){
            hyperlinkTestButton_2.setEnabled(true);
        }
        else{
            hyperlinkTestButton_2.setEnabled(false);
        }
    }//GEN-LAST:event_hyperlinkText_2KeyReleased

    private void hyperlinkText_3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hyperlinkText_3KeyReleased
        // TODO add your handling code here:
        if(checkCorrectSyntax(hyperlinkText_3.getText())){
            hyperlinkTestButton_3.setEnabled(true);
        }
        else{
            hyperlinkTestButton_3.setEnabled(false);
        }
    }//GEN-LAST:event_hyperlinkText_3KeyReleased

    private void chapterComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chapterComboBoxItemStateChanged
        // TODO add your handling code here:
        int selectedItemIndex = chapterComboBox.getSelectedIndex();
        
    }//GEN-LAST:event_chapterComboBoxItemStateChanged

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
            java.util.logging.Logger.getLogger(GraysAnatomyCreate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraysAnatomyCreate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraysAnatomyCreate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraysAnatomyCreate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraysAnatomyCreate(parentFrame).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> chapterComboBox;
    private javax.swing.JTextField encapsulateText_1;
    private javax.swing.JTextField encapsulateText_2;
    private javax.swing.JTextField encapsulateText_3;
    private javax.swing.JCheckBox hyperlinkCheckBox_1;
    private javax.swing.JCheckBox hyperlinkCheckBox_2;
    private javax.swing.JCheckBox hyperlinkCheckBox_3;
    private javax.swing.JButton hyperlinkTestButton_1;
    private javax.swing.JButton hyperlinkTestButton_2;
    private javax.swing.JButton hyperlinkTestButton_3;
    private javax.swing.JTextField hyperlinkText_1;
    private javax.swing.JTextField hyperlinkText_2;
    private javax.swing.JTextField hyperlinkText_3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JComboBox<String> majorTopicComboBox;
    private javax.swing.JTextField pageField;
    private javax.swing.JLabel pageRangeLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JComboBox<String> subtopicComboBox;
    private javax.swing.JTextArea termDefiningTextArea;
    private javax.swing.JTextField termSpellingTextBox;
    // End of variables declaration//GEN-END:variables
}
