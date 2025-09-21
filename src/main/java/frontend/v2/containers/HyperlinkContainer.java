/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frontend.v2.containers;

import backend.eventadapter.GranularMouseAdapter;
import backend.v2.data.TermComparator;
import backend.v2.data.TermDataIOException;
import backend.v2.term.AdvancedHyperlink;
import backend.v2.term.AdvancedTerm;
import backend.v2.term.IllegalTermStateException;
import book.bookpicker.Book;
import frontend.RoundedButton;
import frontend.RoundedPanel;
import frontend.SVGIconPanel;
import frontend.v2.state.ActiveSearchedTermChangeEvent;
import frontend.v2.state.ActiveSearchedTermChangeListener;
import frontend.v2.state.AppState;
import frontend.v2.state.AppStateChangeEvent;
import frontend.v2.state.AppStateChangeListener;
import frontend.v2.window.HyperlinkDialog;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JLabel;

/**
 *
 * @author BRIN
 */
public non-sealed class HyperlinkContainer extends javax.swing.JPanel implements ContentProcessor{
    
    private static final Color DEFAULT_BTN_BG = new Color(39,52,65);
    private static final Color HOVER_BTN_BG = new Color(57,75,92);
    private static final Color PRESSED_BTN_BG = new Color(51,67,82);
    
    private static final Color DEFAULT_PNL_BG = new Color(57,75,92);
    private static final Color ACTIVE_PNL_BG = new Color(15,20,25);
    private static final Color HOVER_PNL_BG = new Color(43,181,114);
    private static final Color PRESSED_PNL_BG = new Color(37,160,100);
    
    private static final Color TRASH_DEFAULT_BG = new Color(57,75,92);
    private static final Color TRASH_PRESSED_BG = new Color(51,67,82);
    private static final Color TRASH_QUESTIONABLE_CONFIRM_BG = new Color(255,153,153);
    private static final Color TRASH_HARD_CONFIRM_BG = new Color(251,122,122);
    
    private static final Color WARNING_RED = new Color(255,51,51);
    private static final Color WARNING_GREEN = new Color(51,255,0);
    
    List<AdvancedHyperlink> hyperlinkList;
    List<AdvancedHyperlink> lastCommitedHyperlinkList;
    JLabel[] encapFields;
    RoundedButton[] editBtns;
    RoundedPanel[] encapContainers;
    RoundedPanel[] indicators;
    SVGIconPanel[] trashIcons;
    JLabel[] deleteWarns;
    
    Map<SVGIconPanel, JLabel> trashDeleteMap;
    
    List<GranularMouseAdapter> containerGrans;
    Set<SVGIconPanel> activatedTrashIcons;
    Set<RoundedButton> activatedEditButtons;
    

    /**
     * Creates new form HyperlinkContainer
     */
    public HyperlinkContainer() {
        initComponents();
        
        hyperlinkList = new ArrayList<>();
        lastCommitedHyperlinkList = new ArrayList<>();
        
        
        hyperlinkList.add(new AdvancedHyperlink());
        hyperlinkList.add(new AdvancedHyperlink());
        hyperlinkList.add(new AdvancedHyperlink());
        hyperlinkList.add(new AdvancedHyperlink());
        hyperlinkList.add(new AdvancedHyperlink());
        hyperlinkList.add(new AdvancedHyperlink());
        
        containerGrans = new ArrayList<>();
        activatedTrashIcons = new HashSet<>(); // do not use .get(int index) since it is very highly likely that
                                                 // this list is going to be populated randomly and not serially.
        activatedEditButtons = new HashSet<>();
        
        trashDeleteMap = new HashMap<>();
        
        encapFields = new JLabel[]{
            encap1, encap2, encap3, encap4, encap5, encap6
        };
        
        editBtns = new RoundedButton[]{
            edit1, edit2, edit3, edit4, edit5, edit6
        };
        
        encapContainers = new RoundedPanel[]{
            encap1Container, encap2Container, encap3Container, encap4Container, encap5Container, encap6Container
        };
        
        indicators = new RoundedPanel[]{
            indicator1, indicator2, indicator3, indicator4, indicator5, indicator6
        };
        
        trashIcons = new SVGIconPanel[]{
            trash1, trash2, trash3, trash4, trash5, trash6
        };
        
        deleteWarns = new JLabel[]{
            delete1, delete2, delete3, delete4, delete5, delete6
        };
        
        for(RoundedButton edit : editBtns){
            edit.setVisible(false);
        }
        
        for(SVGIconPanel trash : trashIcons){
            trash.setVisible(false);
        }
        
        for(JLabel warning : deleteWarns){
            warning.setVisible(false);
        }
        // uncomment the following for testing purpose only!
        // edit1.setText("ADD");
        // activatedEditButtons.add(edit1);
        
        populateMap();
        initializeEditButton();
        initializeEncapContainers();
        initializeTrashIcons();
        
        AppState.addActiveSearchedTermChangeListener(new ActiveSearchedTermChangeListener() {
            @Override
            public void activeSearchedTermChanged(ActiveSearchedTermChangeEvent e) {
                
                if(e.getNewAdvTerm() == null){
                    reset();
                    setEditable();
                }else {
                    setContent(AppState.getCurrentBook(), e.getNewAdvTerm()); // might make one or more edit icons visible
                    setNonEditable(); // completely overrides the previous method call to make edit button visible.
                }
            }
        });
        
        AppState.addListener(new AppStateChangeListener() {
            @Override
            public void appStateChanged(AppStateChangeEvent e) {
                // Always reset regardless of what the state app changed to.
                reset();
            }
        });
    }

    @Override
    public void reset(){
        
        hyperlinkList = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            hyperlinkList.add(new AdvancedHyperlink());
        }
    }
    
    @Override
    public void setContent(Book book, AdvancedTerm advTerm){
        
        hyperlinkList.clear();
        lastCommitedHyperlinkList.clear();
        activatedEditButtons.clear();
        activatedTrashIcons.clear();
        
        for(AdvancedHyperlink adVHyper : advTerm.getHyperlinks()){
            hyperlinkList.add(new AdvancedHyperlink(adVHyper));
            lastCommitedHyperlinkList.add(new AdvancedHyperlink(adVHyper));
        }
        
        updateInBulk();
        
        for(RoundedButton editButton : editBtns){
            editButton.setVisible(false);
        }
        
        for(SVGIconPanel trashIcon : trashIcons){
            trashIcon.setVisible(false);
        }
    }
    
    @Override
    public List<AdvancedHyperlink> extractContent(){
        
       return Collections.unmodifiableList(hyperlinkList);
    }
    
    @Override
    public boolean wasContentChanged(){
        
        for(int i = 0 ;i < lastCommitedHyperlinkList.size() && i < hyperlinkList.size(); i++){
            if(!lastCommitedHyperlinkList.get(i).equals(hyperlinkList.get(i))){
                return true;
            }
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

        indicator5 = new frontend.RoundedPanel();
        five = new javax.swing.JLabel();
        indicator1 = new frontend.RoundedPanel();
        one = new javax.swing.JLabel();
        indicator2 = new frontend.RoundedPanel();
        two = new javax.swing.JLabel();
        indicator3 = new frontend.RoundedPanel();
        three = new javax.swing.JLabel();
        indicator4 = new frontend.RoundedPanel();
        four = new javax.swing.JLabel();
        indicator6 = new frontend.RoundedPanel();
        six = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        encap6Container = new frontend.RoundedPanel();
        encap6 = new javax.swing.JLabel();
        edit6 = new frontend.RoundedButton();
        trash6 = new frontend.SVGIconPanel();
        jPanel7 = new javax.swing.JPanel();
        encap1Container = new frontend.RoundedPanel();
        encap1 = new javax.swing.JLabel();
        edit1 = new frontend.RoundedButton();
        trash1 = new frontend.SVGIconPanel();
        jPanel8 = new javax.swing.JPanel();
        encap2Container = new frontend.RoundedPanel();
        encap2 = new javax.swing.JLabel();
        edit2 = new frontend.RoundedButton();
        trash2 = new frontend.SVGIconPanel();
        jPanel9 = new javax.swing.JPanel();
        encap3Container = new frontend.RoundedPanel();
        encap3 = new javax.swing.JLabel();
        edit3 = new frontend.RoundedButton();
        trash3 = new frontend.SVGIconPanel();
        jPanel10 = new javax.swing.JPanel();
        encap4Container = new frontend.RoundedPanel();
        encap4 = new javax.swing.JLabel();
        edit4 = new frontend.RoundedButton();
        trash4 = new frontend.SVGIconPanel();
        jPanel11 = new javax.swing.JPanel();
        encap5Container = new frontend.RoundedPanel();
        encap5 = new javax.swing.JLabel();
        edit5 = new frontend.RoundedButton();
        trash5 = new frontend.SVGIconPanel();
        delete6 = new javax.swing.JLabel();
        delete1 = new javax.swing.JLabel();
        delete2 = new javax.swing.JLabel();
        delete3 = new javax.swing.JLabel();
        delete4 = new javax.swing.JLabel();
        delete5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(39, 52, 65));
        setMinimumSize(new java.awt.Dimension(1260, 400));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        indicator5.setArcSize(15);
        indicator5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        five.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        five.setForeground(new java.awt.Color(255, 255, 255));
        five.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        five.setText("5");
        indicator5.add(five, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        add(indicator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 40, 40));

        indicator1.setArcSize(15);
        indicator1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        one.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        one.setForeground(new java.awt.Color(255, 255, 255));
        one.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        one.setText("1");
        indicator1.add(one, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        add(indicator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 40, 40));

        indicator2.setArcSize(15);
        indicator2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        two.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        two.setForeground(new java.awt.Color(255, 255, 255));
        two.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        two.setText("2");
        indicator2.add(two, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        add(indicator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 40, 40));

        indicator3.setArcSize(15);
        indicator3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        three.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        three.setForeground(new java.awt.Color(255, 255, 255));
        three.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        three.setText("3");
        indicator3.add(three, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        add(indicator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 40, 40));

        indicator4.setArcSize(15);
        indicator4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        four.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        four.setForeground(new java.awt.Color(255, 255, 255));
        four.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        four.setText("4");
        indicator4.add(four, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        add(indicator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 40, 40));

        indicator6.setArcSize(15);
        indicator6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        six.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        six.setForeground(new java.awt.Color(255, 255, 255));
        six.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        six.setText("6");
        indicator6.add(six, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 40, 40));

        add(indicator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 40, 40));

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        encap6Container.setArcSize(15);
        encap6Container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        encap6.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 16)); // NOI18N
        encap6.setForeground(new java.awt.Color(255, 255, 255));
        encap6Container.add(encap6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, 660, 25));

        jPanel2.add(encap6Container, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 40));

        edit6.setText("ADD");
        edit6.setArcSize(15);
        edit6.setCurrentColor(new java.awt.Color(39, 52, 65));
        jPanel2.add(edit6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 70, -1));

        try {
            trash6.set_SVG_URL_String("/icons/trash.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        trash6.setIconPosition(frontend.SVGIconPanel.iconPosition.CENTER);
        trash6.setIconSquarePadding(3.0);
        jPanel2.add(trash6, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 40, 40));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 330, 840, 40));

        jPanel7.setOpaque(false);
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        encap1Container.setArcSize(15);
        encap1Container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        encap1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 16)); // NOI18N
        encap1.setForeground(new java.awt.Color(255, 255, 255));
        encap1Container.add(encap1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, 660, 25));

        jPanel7.add(encap1Container, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 40));

        edit1.setText("ADD");
        edit1.setArcSize(15);
        edit1.setCurrentColor(new java.awt.Color(39, 52, 65));
        jPanel7.add(edit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 70, -1));

        try {
            trash1.set_SVG_URL_String("/icons/trash.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        trash1.setArcSize(15);
        trash1.setCurrentColor(new java.awt.Color(255, 153, 153));
        trash1.setIconPosition(frontend.SVGIconPanel.iconPosition.CENTER);
        trash1.setIconSquarePadding(3.0);
        jPanel7.add(trash1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 40, 40));

        add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 840, 40));

        jPanel8.setOpaque(false);
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        encap2Container.setArcSize(15);
        encap2Container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        encap2.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 16)); // NOI18N
        encap2.setForeground(new java.awt.Color(255, 255, 255));
        encap2Container.add(encap2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, 660, 25));

        jPanel8.add(encap2Container, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 40));

        edit2.setText("ADD");
        edit2.setArcSize(15);
        edit2.setCurrentColor(new java.awt.Color(39, 52, 65));
        jPanel8.add(edit2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 70, -1));

        try {
            trash2.set_SVG_URL_String("/icons/trash.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        trash2.setIconPosition(frontend.SVGIconPanel.iconPosition.CENTER);
        trash2.setIconSquarePadding(3.0);
        jPanel8.add(trash2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 40, 40));

        add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 840, 40));

        jPanel9.setOpaque(false);
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        encap3Container.setArcSize(15);
        encap3Container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        encap3.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 16)); // NOI18N
        encap3.setForeground(new java.awt.Color(255, 255, 255));
        encap3Container.add(encap3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, 660, 25));

        jPanel9.add(encap3Container, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 40));

        edit3.setText("ADD");
        edit3.setArcSize(15);
        edit3.setCurrentColor(new java.awt.Color(39, 52, 65));
        jPanel9.add(edit3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 70, -1));

        try {
            trash3.set_SVG_URL_String("/icons/trash.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        trash3.setIconPosition(frontend.SVGIconPanel.iconPosition.CENTER);
        trash3.setIconSquarePadding(3.0);
        jPanel9.add(trash3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 40, 40));

        add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 840, 40));

        jPanel10.setOpaque(false);
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        encap4Container.setArcSize(15);
        encap4Container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        encap4.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 16)); // NOI18N
        encap4.setForeground(new java.awt.Color(255, 255, 255));
        encap4Container.add(encap4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, 660, 25));

        jPanel10.add(encap4Container, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 40));

        edit4.setText("ADD");
        edit4.setArcSize(15);
        edit4.setCurrentColor(new java.awt.Color(39, 52, 65));
        jPanel10.add(edit4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 70, -1));

        try {
            trash4.set_SVG_URL_String("/icons/trash.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        trash4.setIconPosition(frontend.SVGIconPanel.iconPosition.CENTER);
        trash4.setIconSquarePadding(3.0);
        jPanel10.add(trash4, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 40, 40));

        add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 840, 40));

        jPanel11.setOpaque(false);
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        encap5Container.setArcSize(15);
        encap5Container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        encap5.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 16)); // NOI18N
        encap5.setForeground(new java.awt.Color(255, 255, 255));
        encap5Container.add(encap5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, 660, 25));

        jPanel11.add(encap5Container, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 40));

        edit5.setText("ADD");
        edit5.setArcSize(15);
        edit5.setCurrentColor(new java.awt.Color(39, 52, 65));
        jPanel11.add(edit5, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 70, -1));

        try {
            trash5.set_SVG_URL_String("/icons/trash.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        trash5.setIconPosition(frontend.SVGIconPanel.iconPosition.CENTER);
        trash5.setIconSquarePadding(3.0);
        jPanel11.add(trash5, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 0, 40, 40));

        add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, 840, 40));

        delete6.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        delete6.setForeground(new java.awt.Color(255, 51, 51));
        delete6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        delete6.setText("CLICK AGAIN TO CONFIRM");
        add(delete6, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 330, 290, 40));

        delete1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        delete1.setForeground(new java.awt.Color(51, 255, 0));
        delete1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        delete1.setText("CLICK AGAIN TO CONFIRM");
        add(delete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 30, 290, 40));

        delete2.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        delete2.setForeground(new java.awt.Color(255, 51, 51));
        delete2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        delete2.setText("CLICK AGAIN TO CONFIRM");
        add(delete2, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 90, 290, 40));

        delete3.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        delete3.setForeground(new java.awt.Color(255, 51, 51));
        delete3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        delete3.setText("CLICK AGAIN TO CONFIRM");
        add(delete3, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 150, 290, 40));

        delete4.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        delete4.setForeground(new java.awt.Color(255, 51, 51));
        delete4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        delete4.setText("CLICK AGAIN TO CONFIRM");
        add(delete4, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 210, 290, 40));

        delete5.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 12)); // NOI18N
        delete5.setForeground(new java.awt.Color(255, 51, 51));
        delete5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        delete5.setText("CLICK AGAIN TO CONFIRM");
        add(delete5, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 270, 290, 40));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel delete1;
    private javax.swing.JLabel delete2;
    private javax.swing.JLabel delete3;
    private javax.swing.JLabel delete4;
    private javax.swing.JLabel delete5;
    private javax.swing.JLabel delete6;
    private frontend.RoundedButton edit1;
    private frontend.RoundedButton edit2;
    private frontend.RoundedButton edit3;
    private frontend.RoundedButton edit4;
    private frontend.RoundedButton edit5;
    private frontend.RoundedButton edit6;
    private javax.swing.JLabel encap1;
    private frontend.RoundedPanel encap1Container;
    private javax.swing.JLabel encap2;
    private frontend.RoundedPanel encap2Container;
    private javax.swing.JLabel encap3;
    private frontend.RoundedPanel encap3Container;
    private javax.swing.JLabel encap4;
    private frontend.RoundedPanel encap4Container;
    private javax.swing.JLabel encap5;
    private frontend.RoundedPanel encap5Container;
    private javax.swing.JLabel encap6;
    private frontend.RoundedPanel encap6Container;
    private javax.swing.JLabel five;
    private javax.swing.JLabel four;
    private frontend.RoundedPanel indicator1;
    private frontend.RoundedPanel indicator2;
    private frontend.RoundedPanel indicator3;
    private frontend.RoundedPanel indicator4;
    private frontend.RoundedPanel indicator5;
    private frontend.RoundedPanel indicator6;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel one;
    private javax.swing.JLabel six;
    private javax.swing.JLabel three;
    private frontend.SVGIconPanel trash1;
    private frontend.SVGIconPanel trash2;
    private frontend.SVGIconPanel trash3;
    private frontend.SVGIconPanel trash4;
    private frontend.SVGIconPanel trash5;
    private frontend.SVGIconPanel trash6;
    private javax.swing.JLabel two;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setEditable() {
        setEditVisible(true);
    }

    @Override
    public void setNonEditable() {
        setEditVisible(false);
    }
    
    private void setEditVisible(boolean toVisible){
        for(RoundedButton edit : activatedEditButtons){ 
            edit.setVisible(toVisible);
        }
        for(SVGIconPanel toShowTrash : activatedTrashIcons){
            toShowTrash.setVisible(toVisible);
        }
    }

    @Override
    public String getHeaderName() {
        return "HYPERLINK:";
    }
    
    @Override
    public void revertChanges() {
        setContent(AppState.getCurrentBook(), AppState.getCurrentActiveSearchedTerm());
    }

    @Override
    public void saveOnlyThis() {
        AdvancedTerm copy = new AdvancedTerm(AppState.getCurrentActiveSearchedTerm());
        try {
            copy.replaceHyperlinks(extractContent());
        } catch (IllegalTermStateException ex) {
            System.getLogger(HyperlinkContainer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        lastCommitedHyperlinkList.clear();
        lastCommitedHyperlinkList.addAll(extractContent());
        copy.arrangeHyperlinks();
        try {
            AppState.getCurrentBook().getATDM().flush(AppState.getCurrentActiveSearchedTerm(), copy);
        } catch (TermDataIOException ex) {
            System.getLogger(HyperlinkContainer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    private void populateMap(){
        
        for(int i = 0; i < trashIcons.length; i++){
            trashDeleteMap.put(trashIcons[i], deleteWarns[i]);
        }
    }
    
    private void initializeEditButton(){
        
        for(int i = 0; i < editBtns.length; i++){
            final int index = i;
            RoundedButton rdb = editBtns[index];
            
            
            
            ActionListener btnActListen = (ActionEvent e) -> {
                HyperlinkDialog dialog = new HyperlinkDialog(null);
                AdvancedHyperlink newadv = dialog.showDialog(hyperlinkList.get(index));
                if(newadv == null){
                    return;
                }
                
                hyperlinkList.set(index, newadv);
                updateEncapContainers_Indicators(index, newadv);
                trashIcons[index].setVisible(true);
                activatedTrashIcons.add(trashIcons[index]);
                
            };
            
            rdb.addActionListener(btnActListen);
            
            
            GranularMouseAdapter gran = new GranularMouseAdapter(){
              
                @Override
                public void actOnMouseEntry(MouseEvent e){
                    rdb.setCurrentColor(HOVER_BTN_BG);
                }
                
                @Override
                public void actOnMouseExit(MouseEvent e){
                    rdb.setCurrentColor(DEFAULT_BTN_BG);
                }
                
                @Override
                public void actOnMousePress(MouseEvent e){
                    rdb.setCurrentColor(PRESSED_BTN_BG);
                }
                
            };
            
            rdb.addMouseListener(gran);
        }
    }
    
    private void initializeEncapContainers(){
        
        for(int i = 0; i < encapContainers.length; i++){
            
            final RoundedPanel rdpnl = encapContainers[i];
            final int index = i;
            GranularMouseAdapter gran = new GranularMouseAdapter(){
                Color prevColor;
                
                @Override
                public void actOnMouseEntry(MouseEvent e){
                    prevColor = rdpnl.getCurrentColor();
                    rdpnl.setCurrentColor(HOVER_PNL_BG);
                    rdpnl.repaint();
                }
                
                @Override
                public void actOnMouseExit(MouseEvent e){
                    rdpnl.setCurrentColor(prevColor);
                    rdpnl.repaint();
                }
                
                @Override
                public void actOnMousePress(MouseEvent e){
                    rdpnl.setCurrentColor(PRESSED_PNL_BG);
                    rdpnl.repaint();
                }
                
                @Override
                public void actOnMouseRelease(MouseEvent e){
                    rdpnl.setCurrentColor(HOVER_PNL_BG);
                    rdpnl.repaint();
                    browse(hyperlinkList.get(index).getHyperlink());
                }
            };
            
            rdpnl.addMouseListener(gran);
            containerGrans.add(gran);
            gran.switchOff(); // initially switch off all Granular Mouse Adapters.
        }
    }
    
    private void initializeTrashIcons(){
        
        for(int i = 0; i < trashIcons.length; i++){
            
            final SVGIconPanel trashIcon = trashIcons[i];
            final int indexToRemove = i;
            final JLabel warning = trashDeleteMap.get(trashIcon);
            
            GranularMouseAdapter trashGran = new GranularMouseAdapter(){
                
                int confirm = 0;
                
                
                @Override
                public void actOnMouseEntry(MouseEvent e){
                    trashIcon.setCurrentColor(TRASH_DEFAULT_BG);
                    trashIcon.setBackgroundPainted(true);
                    trashIcon.repaint();
                }
                
                @Override
                public void actOnMouseExit(MouseEvent e){
                    confirm = 0;
                    trashIcon.setBackgroundPainted(false);
                    trashIcon.setCurrentColor(TRASH_DEFAULT_BG);
                    warning.setVisible(false);
                    trashIcon.repaint();
                }
                
                @Override
                public void actOnMousePress(MouseEvent e){
                    if(confirm == 0){
                        trashIcon.setCurrentColor(TRASH_PRESSED_BG);
                        warning.setText("CLICK AGAIN TO CONFIRM");
                        warning.setForeground(WARNING_RED);
                        warning.setVisible(true);
                    }else if(confirm == 1){
                        trashIcon.setCurrentColor(TRASH_HARD_CONFIRM_BG);
                    }
                    confirm++;
                    trashIcon.repaint();
                }
                
                @Override
                public void actOnMouseRelease(MouseEvent e){
                    
                    if(confirm == 1){
                        trashIcon.setCurrentColor(TRASH_QUESTIONABLE_CONFIRM_BG);
                    }
                    if(confirm == 2){
                        trashIcon.setCurrentColor(TRASH_DEFAULT_BG);
                        changeList(indexToRemove);
                        warning.setForeground(WARNING_GREEN);
                        warning.setText("REMOVED SUCESSFULLY");
                        confirm = 0; // so that the user do not have to exit the mouse and reenter the mouse to make confirm = 0 again.
                    }
                    trashIcon.repaint();
                }
            };
            
            trashIcon.addMouseListener(trashGran);
        }
    }
    
  
    private void changeList(int index){
        hyperlinkList.remove(index);
        hyperlinkList.add(new AdvancedHyperlink());
        activatedTrashIcons.clear();
        updateInBulk();
        
        if(editBtns[editBtns.length - 1].getText().equals("EDIT")){
            editBtns[editBtns.length - 1].setText("ADD");
        }else {
            for(int i = editBtns.length - 1; i >= 0; i--){
                
                if(editBtns[i].isVisible() && i != 0){
                    
                    editBtns[i].setVisible(false);
                    activatedEditButtons.remove(editBtns[i]);
                    editBtns[i - 1].setText("ADD");
                    break;
                }
                else if(i == 0){
                    editBtns[i].setText("ADD");
                }
            }
        }
        
        if(activatedTrashIcons.isEmpty()){
            trashIcons[index].setVisible(false);
            return; // no need to proceed further...
        } 
        
        for(int i = trashIcons.length - 1; i >= 0; i--){
            if(trashIcons[i].isVisible()){
                trashIcons[i].setVisible(false);
                activatedTrashIcons.remove(trashIcons[i]);
                break;
            }
        }
        
        
        
    }
    
    private void browse(String link){
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(link));
            } catch (IOException | URISyntaxException ex) {
                // Handle exceptions (e.g., show an error dialog)
                ex.printStackTrace();
            }
        } else {
                // Handle case where Desktop is not supported
            System.out.println("Desktop is not supported, cannot open URL.");
        }
    }
    
    private void updateInBulk(){
        for(int i = 0; i < hyperlinkList.size(); i++){
            updateEncapContainers_Indicators(i, hyperlinkList.get(i));
        }
        
        if(activatedEditButtons.isEmpty()){
            editBtns[0].setText("ADD");
            editBtns[0].setVisible(true);
            activatedEditButtons.add(editBtns[0]);
        }
    }
    
    private void updateEncapContainers_Indicators(int index, AdvancedHyperlink link){
        
        if(!link.isDefault()){
            encapContainers[index].setCurrentColor(ACTIVE_PNL_BG);
            encapFields[index].setText(link.getEncapsulation());
            indicators[index].setCurrentColor(ACTIVE_PNL_BG);
            containerGrans.get(index).switchON();
            activatedTrashIcons.add(trashIcons[index]);
            editBtns[index].setText("EDIT");
            activatedEditButtons.add(editBtns[index]);
            if(activatedEditButtons.size() < 6){
                editBtns[index + 1].setText("ADD");
                editBtns[index + 1].setVisible(true);
                activatedEditButtons.add(editBtns[index + 1]);
            }
        }
        else {
            encapContainers[index].setCurrentColor(DEFAULT_PNL_BG);
            encapFields[index].setText("");
            indicators[index].setCurrentColor(DEFAULT_PNL_BG);
            containerGrans.get(index).switchOff();
        }
        
        this.repaint();
    }

    
}
