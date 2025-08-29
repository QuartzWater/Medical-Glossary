/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend;

import backend.HeadingModel;
import backend.Term;
import backend.TermDataManagement;
import backend.Utils;
import book.bookpicker.Book;
import legacy.BehaviourForSwing;
import legacy.ButtonActionCode;
import legacy.TextBoxContentEventCode;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 *
 * @author BRIN
 */
public class referenceEditFrame extends javax.swing.JFrame {

    /**
     * Creates new form definitionEditFrame
     */
    
    private Book initialisedBook;
    private TermDataManagement tdm;
    private Term existingTerm;
    private Term newTerm;
    private boolean termFound;
    
    private definitionEditFrame parentFrame;
    private final String DEFAULT_PAGE_BOX_TEXT = "<Enter Page Number>";
    
    private final Font originalBookTextBoxFont;
    
    public referenceEditFrame(Book initialisedBook, TermDataManagement tdm, Term existingTerm, Term newTerm, boolean  termFound, definitionEditFrame def) {
        
        
        this.initialisedBook = initialisedBook;
        this.tdm = tdm;
        this.existingTerm = existingTerm;
        this.newTerm = newTerm;
        this.termFound = termFound;
        this.parentFrame = def;
        HeadingModel hm = initialisedBook.getHeadingModel();
        
        
        initComponents();
        this.superHeadingLabel.setText(hm.getSuperHeading() + ":");
        this.middleHeadingLabel.setText(hm.getMiddleHeading() + ":");
        this.subHeadingLabel.setText(hm.getSubHeading() + ":");
        
        originalBookTextBoxFont = bookTextBox.getFont();
        
        bookTextBox.setText(initialisedBook.getTitle());
        bookTextBox.setForeground(initialisedBook.getColorScheme().getHoverColor());

        Utils.dynamicallyChangeFont(bookTextBox);
        
        if(termFound){
            pageBox.setText(Integer.toString(existingTerm.getPage()));
            superHeadingTextBox.setText(existingTerm.getSuperHeadingContent());
            middleHeadingTextBox.setText(existingTerm.getMiddleHeadingContent());
            subHeadingTextArea.setText(existingTerm.getSubHeadingContent());
            
            if(!existingTerm.getSpelling().equals(newTerm.getSpelling())){
                this.setTitle("Editing Reference for: '" + newTerm.getSpelling() +"' (Previously: '" + existingTerm.getSpelling() + "')");
            }
            else{
                this.setTitle("Editing Reference for: '" + newTerm.getSpelling() + "'");
            }
            
            titleLabel.setText("Edit Reference");
            statusLabel.setText("Edit page number to load a new configuration.");
            nextButton.setEnabled(true);
        }
        else{
            pageBox.setText(DEFAULT_PAGE_BOX_TEXT);
            superHeadingTextBox.setText(" -");
            middleHeadingTextBox.setText(" -");
            subHeadingTextArea.setText(" -");
            this.setTitle("Creating Reference for: '" + newTerm.getSpelling() +"'");
            titleLabel.setText("Create Reference");
            statusLabel.setText("Enter page number to load a configuration.");
            nextButton.setEnabled(false);
        }
        
        new referenceEditFrameGUIBehaviour(this);
    }
    
    public static referenceEditFrame generateInstance(Book initialisedBook, TermDataManagement tdm, Term existingTerm, Term newTerm, boolean  termFound, definitionEditFrame def){
        
        referenceEditFrame ref = new referenceEditFrame(initialisedBook, tdm, existingTerm, newTerm, termFound, def);
        return ref;
    }
    
    public Book getInitialisedBook(){
        
        return initialisedBook;
    }
    
    public TermDataManagement getTDM(){
        
        return tdm;
    }
    
    public Term getExistingTerm(){
        
        return existingTerm;
    }
    
    public Term getNewTerm(){
        
        return newTerm;
    }
    
    public boolean getTermFound(){
        
        return termFound;
    }
    
    public definitionEditFrame getDEFParent(){
        
        return parentFrame;
    }
    
    public JTextField getPageBox(){
        
        return pageBox;
    }
    
    public JLabel getBookField(){
        
        return bookTextBox;
    }
    
    public JLabel getSuperHeadingBox(){
        
        return superHeadingTextBox;
    }
    
    public JLabel getMiddleHeadingBox(){
        
        return middleHeadingTextBox;
    }
    
    public JTextArea getSubHeadingBox(){
        
        return subHeadingTextArea;
    }
    
    public RoundedButton getBackButton(){
        
        return backButton;
    }
    
    public RoundedButton getNextButton(){
        
        return nextButton;
    }
    
    public JLabel getStatusLabel(){
        
        return statusLabel;
    }
    
    public JLabel getHeaderLabel(){
        
        return headerLabel;
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
        pageBox = new javax.swing.JTextField();
        titleLabel = new javax.swing.JLabel();
        statusTextArea = new javax.swing.JTextArea();
        containerPanel = new javax.swing.JPanel();
        subHeadingTextArea = new javax.swing.JTextArea();
        superHeadingTextBox = new javax.swing.JLabel();
        superHeadingLabel = new javax.swing.JLabel();
        middleHeadingLabel = new javax.swing.JLabel();
        middleHeadingTextBox = new javax.swing.JLabel();
        subHeadingLabel = new javax.swing.JLabel();
        bookLabel = new javax.swing.JLabel();
        bookTextBox = new javax.swing.JLabel();
        decorativePanel = new frontend.RoundedPanel();
        headerLabel = new javax.swing.JLabel();
        backButton = new frontend.RoundedButton();
        statusLabel = new javax.swing.JLabel();
        nextButton = new frontend.RoundedButton();

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

        pageBox.setBackground(new java.awt.Color(68, 96, 122));
        pageBox.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        pageBox.setForeground(new java.awt.Color(255, 255, 255));
        pageBox.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pageBox.setText("<Page Number>");
        pageBox.setCaretColor(new java.awt.Color(255, 255, 255));
        pageBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pageBoxActionPerformed(evt);
            }
        });
        parentPanel.add(pageBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 470, 40));

        titleLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Edit Reference");
        parentPanel.add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 280, -1));

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

        containerPanel.setOpaque(false);
        containerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        subHeadingTextArea.setEditable(false);
        subHeadingTextArea.setColumns(20);
        subHeadingTextArea.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        subHeadingTextArea.setForeground(new java.awt.Color(255, 255, 255));
        subHeadingTextArea.setLineWrap(true);
        subHeadingTextArea.setRows(5);
        subHeadingTextArea.setText(" -");
        subHeadingTextArea.setWrapStyleWord(true);
        subHeadingTextArea.setBorder(null);
        subHeadingTextArea.setCaretColor(new java.awt.Color(255, 255, 255));
        subHeadingTextArea.setFocusable(false);
        subHeadingTextArea.setOpaque(false);
        subHeadingTextArea.setPreferredSize(new java.awt.Dimension(450, 160));
        containerPanel.add(subHeadingTextArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 490, 150));

        superHeadingTextBox.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        superHeadingTextBox.setForeground(new java.awt.Color(255, 255, 255));
        superHeadingTextBox.setText(" -");
        containerPanel.add(superHeadingTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 490, -1));

        superHeadingLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        superHeadingLabel.setForeground(new java.awt.Color(204, 204, 204));
        superHeadingLabel.setText(" Chapter:");
        containerPanel.add(superHeadingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 110, -1));

        middleHeadingLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        middleHeadingLabel.setForeground(new java.awt.Color(204, 204, 204));
        middleHeadingLabel.setText(" Major Topic:");
        containerPanel.add(middleHeadingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 120, -1));

        middleHeadingTextBox.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        middleHeadingTextBox.setForeground(new java.awt.Color(255, 255, 255));
        middleHeadingTextBox.setText(" -");
        containerPanel.add(middleHeadingTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 490, -1));

        subHeadingLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        subHeadingLabel.setForeground(new java.awt.Color(204, 204, 204));
        subHeadingLabel.setText(" Subtopic:");
        containerPanel.add(subHeadingLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 100, -1));

        bookLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bookLabel.setForeground(new java.awt.Color(204, 204, 204));
        bookLabel.setText(" Book:");
        containerPanel.add(bookLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, -1));

        bookTextBox.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        bookTextBox.setForeground(new java.awt.Color(255, 255, 255));
        bookTextBox.setText(" -");
        containerPanel.add(bookTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 490, -1));

        parentPanel.add(containerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 530, 420));

        javax.swing.GroupLayout decorativePanelLayout = new javax.swing.GroupLayout(decorativePanel);
        decorativePanel.setLayout(decorativePanelLayout);
        decorativePanelLayout.setHorizontalGroup(
            decorativePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );
        decorativePanelLayout.setVerticalGroup(
            decorativePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );

        parentPanel.add(decorativePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 530, 420));

        headerLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        headerLabel.setForeground(new java.awt.Color(255, 255, 255));
        headerLabel.setText("Reference:");
        parentPanel.add(headerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 120, -1));

        backButton.setText("Back");
        backButton.setArcSize(35);
        backButton.setCurrentColor(new java.awt.Color(27, 36, 45));
        parentPanel.add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 660, 130, 40));

        statusLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(255, 255, 255));
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusLabel.setText("Use up/down keys to cycle through various entries ()");
        parentPanel.add(statusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 470, 20));

        nextButton.setText("Next");
        nextButton.setArcSize(35);
        nextButton.setCurrentColor(new java.awt.Color(27, 36, 45));
        parentPanel.add(nextButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 660, 130, 40));

        getContentPane().add(parentPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 590, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void pageBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pageBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pageBoxActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private frontend.RoundedButton backButton;
    private javax.swing.JLabel bookLabel;
    private javax.swing.JLabel bookTextBox;
    private javax.swing.JPanel containerPanel;
    private frontend.RoundedPanel decorativePanel;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JLabel middleHeadingLabel;
    private javax.swing.JLabel middleHeadingTextBox;
    private frontend.RoundedButton nextButton;
    private javax.swing.JTextField pageBox;
    private javax.swing.JPanel parentPanel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JTextArea statusTextArea;
    private javax.swing.JLabel subHeadingLabel;
    private javax.swing.JTextArea subHeadingTextArea;
    private javax.swing.JLabel superHeadingLabel;
    private javax.swing.JLabel superHeadingTextBox;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
