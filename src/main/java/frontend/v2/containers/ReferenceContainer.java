/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frontend.v2.containers;

import backend.HeadingModel;
import backend.v2.data.TermDataIOException;
import backend.v2.term.AdvancedTerm;
import backend.v2.term.IllegalTermStateException;
import book.bookpicker.Book;
import frontend.v2.controller.ReferenceContainerController;
import frontend.v2.state.ActiveSearchedTermChangeEvent;
import frontend.v2.state.ActiveSearchedTermChangeListener;
import frontend.v2.state.AppState;
import frontend.v2.state.AppStateChangeEvent;
import frontend.v2.state.AppStateChangeListener;
import frontend.v2.state.BookChangeEvent;
import frontend.v2.state.BookChangeListener;
import java.awt.Color;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author BRIN
 */
public non-sealed class ReferenceContainer extends javax.swing.JPanel implements ContentProcessor{

    /**
     * Creates new form ReferenceContainer
     */
    private Book book;
    ReferenceContainerController rcc;
    
    private String lastCommitedPage = "";
    private String lastCommitedSuperHeading = " -";
    private String lastCommitedMiddleHeading = " -";
    private String lastCommitedSubHeading = " -";
    
    public ReferenceContainer(Book book) {
        initComponents();
        this.book = book;
        this.rcc = new ReferenceContainerController(this);
        
        updateBookInfo(AppState.getCurrentBook());
        
        AppState.addActiveSearchedTermChangeListener(new ActiveSearchedTermChangeListener() {
            @Override
            public void activeSearchedTermChanged(ActiveSearchedTermChangeEvent e) {
                
                if(e.getNewAdvTerm() == null){
                    reset();
                    setEditable();
                }else {
                    setContent(AppState.getCurrentBook(), e.getNewAdvTerm());
                    setNonEditable();
                }
            }
        });
        
        AppState.addListener(new AppStateChangeListener() {
            @Override
            public void appStateChanged(AppStateChangeEvent e) {
                
                // Always reset regardless of what state the app changed to.
                reset();
            }
        });
        
        AppState.addBookChangeListener(new BookChangeListener() {
            @Override
            public void bookChanged(BookChangeEvent e) {
                updateBookInfo(e.getNewBook());
            }
        });
    }
    
    private void updateBookInfo(Book newBook){
        BookInfoArea.setText(newBook.getTitle());
        BookInfoArea.setForeground(newBook.getColorScheme().getHoverColor());
        BookInfoArea.repaint();
        
        HeadingModel headingModel = newBook.getHeadingModel();
        superHeadingHeader.setText(headingModel.getSuperHeading() + ":");
        middleHeadingHeader.setText(headingModel.getMiddleHeading() + ":");
        subHeadingHeader.setText(headingModel.getSubHeading() + ":");
    }
    
    public void changeBook(Book book){
        this.book = book;
        
    }

    public JTextField getPageTextBox() {
        return pageTextBox;
    }

    public JTextArea getSuperHeadingArea() {
        return superHeadingArea;
    }

    public JTextArea getMiddleHeadingArea() {
        return middleHeadingArea;
    }

    public JTextArea getSubHeadingArea() {
        return subHeadingArea;
    }

    public JLabel getPageStatus() {
        return pageStatus;
    }

    public JLabel getMultiEverythingIndicator() {
        return multiEverythingIndicator;
    }
    
    @Override
    public void reset(){
        pageTextBox.setText("");
        superHeadingArea.setText(" -");
        middleHeadingArea.setText(" -");
        subHeadingArea.setText(" -");
    }

    @Override
    public void setContent(Book book, AdvancedTerm advTerm){
        
        BookInfoArea.setText(book.getTitle());
        BookInfoArea.setForeground(book.getColorScheme().getHoverColor());
        lastCommitedPage = Integer.toString(advTerm.getPage());
        lastCommitedSuperHeading = advTerm.getSuperCateogry();
        lastCommitedMiddleHeading = advTerm.getMiddleCategory();
        lastCommitedSubHeading = advTerm.getSubCategory();
        pageTextBox.setText(lastCommitedPage);
        superHeadingArea.setText(lastCommitedSuperHeading);
        middleHeadingArea.setText(lastCommitedMiddleHeading);
        subHeadingArea.setText(lastCommitedSubHeading);
    }
    
    @Override
    public List<String> extractContent(){
        List<String> toReturn = new LinkedList<>();
        toReturn.add(pageTextBox.getText());
        toReturn.add(superHeadingArea.getText());
        toReturn.add(middleHeadingArea.getText());
        toReturn.add(subHeadingArea.getText());
        
        return Collections.unmodifiableList(toReturn);
    }
    
    @Override
    public boolean wasContentChanged(){
        if(!lastCommitedPage.equals(pageTextBox.getText())){
            return true;
        }else if(!lastCommitedSuperHeading.equals(superHeadingArea.getText())){
            return true;
        }else if(!lastCommitedMiddleHeading.equals(middleHeadingArea.getText())){
            return true;
        }else if(!lastCommitedSubHeading.equals(subHeadingArea.getText())){
            return true;
        }
        
        return false;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header2 = new javax.swing.JLabel();
        pageInfoDecorative = new frontend.RoundedPanel();
        pageTextBox = new javax.swing.JTextField();
        multiEverythingIndicator = new javax.swing.JLabel();
        bookInfoDecorative = new frontend.RoundedPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        BookInfoArea = new javax.swing.JTextArea();
        header1 = new javax.swing.JLabel();
        superHeadingHeader = new javax.swing.JLabel();
        middleHeadingHeader = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        subHeadingArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        superHeadingArea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        middleHeadingArea = new javax.swing.JTextArea();
        subHeadingHeader = new javax.swing.JLabel();
        pageStatusContainer = new javax.swing.JPanel();
        pageStatus = new javax.swing.JLabel();

        setBackground(new java.awt.Color(39, 52, 65));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        header2.setForeground(new java.awt.Color(217, 217, 217));
        header2.setText("PAGE:");
        add(header2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        pageInfoDecorative.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pageTextBox.setEditable(false);
        pageTextBox.setBackground(new java.awt.Color(57, 75, 92));
        pageTextBox.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 36)); // NOI18N
        pageTextBox.setForeground(new java.awt.Color(255, 255, 255));
        pageTextBox.setText("9999");
        pageTextBox.setBorder(null);
        pageTextBox.setCaretColor(new java.awt.Color(255, 255, 255));
        pageInfoDecorative.add(pageTextBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 190, 70));

        multiEverythingIndicator.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 36)); // NOI18N
        multiEverythingIndicator.setForeground(new Color(255,255,255, 90));
        multiEverythingIndicator.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        multiEverythingIndicator.setText("2/2");
        pageInfoDecorative.add(multiEverythingIndicator, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 100, 65));

        add(pageInfoDecorative, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 420, 90));

        bookInfoDecorative.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setBorder(null);
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        BookInfoArea.setEditable(false);
        BookInfoArea.setBackground(new java.awt.Color(57, 75, 92));
        BookInfoArea.setColumns(20);
        BookInfoArea.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        BookInfoArea.setForeground(new java.awt.Color(255, 255, 255));
        BookInfoArea.setLineWrap(true);
        BookInfoArea.setRows(5);
        BookInfoArea.setWrapStyleWord(true);
        BookInfoArea.setBorder(null);
        jScrollPane4.setViewportView(BookInfoArea);

        bookInfoDecorative.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 400, 110));

        add(bookInfoDecorative, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 420, 190));

        header1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        header1.setForeground(new java.awt.Color(217, 217, 217));
        header1.setText("BOOK:");
        add(header1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        superHeadingHeader.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        superHeadingHeader.setForeground(new java.awt.Color(217, 217, 217));
        superHeadingHeader.setText("SUPER HEADING:");
        add(superHeadingHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, -1, -1));

        middleHeadingHeader.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        middleHeadingHeader.setForeground(new java.awt.Color(217, 217, 217));
        middleHeadingHeader.setText("MIDDLE HEADING:");
        add(middleHeadingHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, -1, -1));

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        subHeadingArea.setEditable(false);
        subHeadingArea.setBackground(new java.awt.Color(39, 52, 65));
        subHeadingArea.setColumns(20);
        subHeadingArea.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        subHeadingArea.setForeground(new java.awt.Color(255, 255, 255));
        subHeadingArea.setLineWrap(true);
        subHeadingArea.setRows(5);
        subHeadingArea.setWrapStyleWord(true);
        subHeadingArea.setBorder(null);
        jScrollPane1.setViewportView(subHeadingArea);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 300, 780, 80));

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        superHeadingArea.setEditable(false);
        superHeadingArea.setBackground(new java.awt.Color(39, 52, 65));
        superHeadingArea.setColumns(20);
        superHeadingArea.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        superHeadingArea.setForeground(new java.awt.Color(255, 255, 255));
        superHeadingArea.setLineWrap(true);
        superHeadingArea.setRows(5);
        superHeadingArea.setWrapStyleWord(true);
        superHeadingArea.setBorder(null);
        jScrollPane2.setViewportView(superHeadingArea);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 780, 80));

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        middleHeadingArea.setEditable(false);
        middleHeadingArea.setBackground(new java.awt.Color(39, 52, 65));
        middleHeadingArea.setColumns(20);
        middleHeadingArea.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        middleHeadingArea.setForeground(new java.awt.Color(255, 255, 255));
        middleHeadingArea.setLineWrap(true);
        middleHeadingArea.setRows(5);
        middleHeadingArea.setWrapStyleWord(true);
        middleHeadingArea.setBorder(null);
        jScrollPane3.setViewportView(middleHeadingArea);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 170, 780, 80));

        subHeadingHeader.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        subHeadingHeader.setForeground(new java.awt.Color(217, 217, 217));
        subHeadingHeader.setText("SUB HEADING:");
        add(subHeadingHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 260, -1, -1));

        pageStatusContainer.setOpaque(false);
        pageStatusContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pageStatus.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        pageStatus.setForeground(new java.awt.Color(255, 153, 153));
        pageStatusContainer.add(pageStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 330, 25));

        add(pageStatusContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 350, 60));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea BookInfoArea;
    private frontend.RoundedPanel bookInfoDecorative;
    private javax.swing.JLabel header1;
    private javax.swing.JLabel header2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea middleHeadingArea;
    private javax.swing.JLabel middleHeadingHeader;
    private javax.swing.JLabel multiEverythingIndicator;
    private frontend.RoundedPanel pageInfoDecorative;
    private javax.swing.JLabel pageStatus;
    private javax.swing.JPanel pageStatusContainer;
    private javax.swing.JTextField pageTextBox;
    private javax.swing.JTextArea subHeadingArea;
    private javax.swing.JLabel subHeadingHeader;
    private javax.swing.JTextArea superHeadingArea;
    private javax.swing.JLabel superHeadingHeader;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setEditable() {
        
        pageTextBox.setEditable(true);
        pageStatus.setText("EDIT MODE");
    }

    @Override
    public void setNonEditable() {
        pageTextBox.setEditable(false);
        pageStatus.setText("");
    }

    @Override
    public String getHeaderName() {
        return "REFERENCE:";
    }

    @Override
    public void revertChanges() {
        
        pageTextBox.setText(lastCommitedPage);
        superHeadingArea.setText(lastCommitedSuperHeading);
        middleHeadingArea.setText(lastCommitedMiddleHeading);
        subHeadingArea.setText(lastCommitedSubHeading);
    }

    @Override
    public void saveOnlyThis() {
        AdvancedTerm copy = new AdvancedTerm(AppState.getCurrentActiveSearchedTerm());
        copy.setPage(Integer.parseInt(pageTextBox.getText()));
        copy.setSuperCategory(superHeadingArea.getText());
        copy.setMiddleCategory(middleHeadingArea.getText());
        copy.setSubCategory(subHeadingArea.getText());
        lastCommitedPage = pageTextBox.getText();
        lastCommitedSuperHeading = superHeadingArea.getText();
        lastCommitedMiddleHeading = middleHeadingArea.getText();
        lastCommitedSubHeading = subHeadingArea.getText();
        try {
            AppState.getCurrentBook().getATDM().flush(AppState.getCurrentActiveSearchedTerm(), copy);
        } catch (TermDataIOException ex) {
            System.getLogger(ReferenceContainer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
