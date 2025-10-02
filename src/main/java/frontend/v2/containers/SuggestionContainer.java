/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frontend.v2.containers;

import backend.AppConfig;
import backend.eventadapter.GranularMouseAdapter;
import backend.v2.data.AdvancedTermDataManagement;
import backend.v2.search.SearchTermAlgorithm;
import backend.v2.search.SuggestionElement;
import book.bookpicker.Book;
import frontend.RoundedButton;
import frontend.v2.controller.SuggestionContainerController;
import frontend.v2.state.AppState;
import frontend.v2.state.AppStateChangeEvent;
import frontend.v2.state.AppStateChangeListener;
import frontend.v2.state.BookChangeEvent;
import frontend.v2.state.BookChangeListener;
import frontend.v2.state.InputTermChangeEvent;
import java.awt.Color;
import java.awt.FontMetrics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import frontend.v2.state.InputTermChangeListener;
import frontend.v2.state.QuickAccessEvent;
import frontend.v2.state.QuickAccessListener;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;

/**
 *
 * @author BRIN
 */
public class SuggestionContainer extends javax.swing.JPanel {
    
    private static final String NO_SPELLING_ON_CREATE_MODE = "A new term can not have empty spelling!";
    private static final String NO_SUGGESTION_ON_SEARCH_MODE = "No match was found";
    private static final String SPELLING_ALREADY_EXISTS_IN_CREATE_MODE = "Specified spelling already exists";
    
    
    private static final int MAX_STRING_WIDTH_WITHOUT_RESIZE = 140;
    private static final int NORMAL_BUTTON_WIDTH = 200;
    private static final int NORMAL_BUTTON_HEIGHT = 40;
    private final FontMetrics fm;
    
    private SearchTermAlgorithm sta;

    private SuggestionContainerController scc;
    private Book book;
    
    private List<RoundedButton> suggestionBtns = new ArrayList<>();
    /**
     * Creates new form SuggestionContainer
     */
    public SuggestionContainer() {
        initComponents();
        setFocusable(true);
        RoundedButton dummyButton = new RoundedButton();
        fm = dummyButton.getFontMetrics(dummyButton.getFont());
        this.sta = new SearchTermAlgorithm(AppConfig.getSearchDepth(), 45);
        this.scc = new SuggestionContainerController(this);
        this.book = AppState.getCurrentBook();
        
        header.setVisible(false);
        notFoundPanel.setVisible(false);
        
        AppState.addListener(new AppStateChangeListener() {
            @Override
            public void appStateChanged(AppStateChangeEvent e) {
                enterTermPanel.setVisible(false);
                notFoundPanel.setVisible(false);
                header.setVisible(false);
                
                for(RoundedButton rdb : suggestionBtns){
                    getMyself().remove(rdb);
                }
            }
        });
        
        AppState.addBookChangeListener(new BookChangeListener() {
            @Override
            public void bookChanged(BookChangeEvent e) {
                book = e.getNewBook();
            }
        });
        
        AppState.addSearchTermChangeListener(new InputTermChangeListener() {
            @Override
            public void searchTermChanged(InputTermChangeEvent e) {
                enterTermPanel.setVisible(false);
                notFoundPanel.setVisible(false);
                header.setVisible(false);
                for(RoundedButton rdb : suggestionBtns){
                        getMyself().remove(rdb);
                }if(!getMyself().isVisible()){
                        getMyself().setVisible(true);
                }
                if(AppState.getCurrentState() == AppState.Type.SEARCH){
                    System.out.println("CHANGEEE");
                    
                    if(!e.getNewTerm().isBlank()){
                        
                        header.setVisible(true);
                        buildSuggestions(e.getNewTerm());
                    }else{

                        notFoundDesc.setText(NO_SUGGESTION_ON_SEARCH_MODE);
                        enterTermPanel.setVisible(true);
                    }
                    
                }else if(AppState.getCurrentState() == AppState.Type.CREATE){
                    if(e.getNewTerm().isBlank()){
                        notFoundDesc.setText(NO_SPELLING_ON_CREATE_MODE);
                        notFoundPanel.setVisible(true);
                    }else if(AppState.isTermFound()){
                        notFoundDesc.setText(SPELLING_ALREADY_EXISTS_IN_CREATE_MODE);
                        notFoundPanel.setVisible(true);
                    }else{
                        // Why on earth did i waste my 1.5 hours trying to debug why TIC was not showing up when changing spelling 
                        // while in create mode, only to find 
                        // suggestion panel was not getting invisible and it was also opaque WHILE having SAME background color
                        // this was effectively masking the TIC and its visible components entirely
                        // and i thought something else was calling setVisible(false) on TIC....
                        setVisible(false);
                    }
                }
                
                getMyself().revalidate();
                getMyself().repaint();
            }
        });
        
        AppState.addQuickAccessListener(new QuickAccessListener() {
            @Override
            public void menuSelected(QuickAccessEvent e) {
                scc.simulateEnterAction(e.getSelection());
            }
        });
    }
    
    private SuggestionContainer getMyself(){
        return this;
    }
    
    
    public void buildSuggestions(String toBuildFor){
        
        
        
        List<SuggestionElement> suggestionElements = new ArrayList<>();
        if(!toBuildFor.isBlank()){
            suggestionElements = sta.get(toBuildFor, book);
        }
        
        if(suggestionElements.size() == 1 && suggestionElements.get(0).specialColor().equals(AppConfig.FINAL_SYSTEM_TERM_CREATION_HIGHLIGHT)){
            notFoundPanel.setVisible(true);
            enterTermPanel.setVisible(false);
        }
        
        
        this.revalidate();
        
        
        int paddingX = 10;
        int paddingY = 40;
        int separation = 10;
        
        int xid = 1;
        int yid = 1;
        
        Map<String, RoundedButton> btnMap = new HashMap<>();
        Map<RoundedButton, SuggestionElement> buttnElementMap = new HashMap<>();
        Map<Integer, Integer> btnNumberPerRow = new HashMap<>();
        suggestionBtns = new ArrayList<>();
        
        for(SuggestionElement suggest : suggestionElements){
            
            RoundedButton rdb = new RoundedButton(suggest.suggestionString());
            rdb.setArcSize(20);
            Color c = suggest.specialColor();
            if(c != null){
                rdb.setCurrentColor(c);
            }
            
            int toSet;
            int stringWidth = fm.stringWidth(suggest.suggestionString());
            if(stringWidth > MAX_STRING_WIDTH_WITHOUT_RESIZE){
                toSet = NORMAL_BUTTON_WIDTH + (stringWidth - MAX_STRING_WIDTH_WITHOUT_RESIZE);
            }
            else {
                toSet = NORMAL_BUTTON_WIDTH;
            }
            
            int potentialPaddingX = paddingX + toSet + separation;
           
            if(potentialPaddingX > this.getPreferredSize().width){
                paddingX = 10;
                paddingY += NORMAL_BUTTON_HEIGHT + separation;
                xid = 1;
                yid++;
            }
            
            rdb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            rdb.addMouseListener(selectionMouseGran);
            
            System.out.println("ACTIVE CURSOR: " + rdb.getCursor());
            btnMap.put(getKey(xid, yid), rdb);
            suggestionBtns.add(rdb);
            buttnElementMap.put(rdb, suggest);
            this.add(rdb, new org.netbeans.lib.awtextra.AbsoluteConstraints(paddingX, paddingY, toSet, rdb.getPreferredSize().height), 0);
            
            paddingX += toSet + separation;
            
            btnNumberPerRow.put(yid, xid);
            xid++;
        }
        
        this.revalidate();
        this.repaint();
        this.setVisible(true);
        
        scc.provideMap(btnMap, buttnElementMap, btnNumberPerRow);
        scc.activateKeyboardNavigation();
    }
    
    private GranularMouseAdapter selectionMouseGran = new GranularMouseAdapter(){
            
        Color prevColor;
      
        @Override
        public void actOnMouseEntry(MouseEvent e){
            RoundedButton rdb = (RoundedButton) e.getSource();
            prevColor = rdb.getCurrentColor();
            rdb.setCurrentColor(SuggestionContainerController.SELECTION_COLOR);
            rdb.repaint();
        }
        
        @Override
        public void actOnMouseExit(MouseEvent e){
            RoundedButton rdb = (RoundedButton) e.getSource();
            rdb.setCurrentColor(prevColor);
            rdb.repaint();
        }
        
        @Override
        public void actOnMousePress(MouseEvent e){
            RoundedButton rdb = (RoundedButton) e.getSource();
            rdb.setCurrentColor(SuggestionContainerController.SELECTION_COLOR_PRESSED);
            rdb.repaint();
        }
        
        @Override
        public void actOnMouseRelease(MouseEvent e){
            scc.setCurrentlyHighlighted((RoundedButton) e.getSource());
            scc.enterActionRunnable();
        }
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        optionsPanel = new javax.swing.JPanel();
        header = new javax.swing.JLabel();
        notFoundPanel = new javax.swing.JPanel();
        sVGIconPanel1 = new frontend.SVGIconPanel();
        headerLabel2 = new javax.swing.JLabel();
        notFoundDesc = new javax.swing.JLabel();
        enterTermPanel = new javax.swing.JPanel();
        sVGIconPanel2 = new frontend.SVGIconPanel();
        headerLabel3 = new javax.swing.JLabel();
        headerLabel4 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1260, 530));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1260, 530));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        optionsPanel.setOpaque(false);
        optionsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        header.setForeground(new java.awt.Color(255, 255, 255));
        header.setText("SUGGESTIONS:");
        optionsPanel.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 240, 20));

        notFoundPanel.setOpaque(false);
        notFoundPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        try {
            sVGIconPanel1.set_SVG_URL_String("/icons/defedit/no-results-white.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        notFoundPanel.add(sVGIconPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 120, 120));

        headerLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        headerLabel2.setForeground(new Color(255,255,255, 153));
        headerLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLabel2.setText("Oops...");
        notFoundPanel.add(headerLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 130, 150, 40));

        notFoundDesc.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        notFoundDesc.setForeground(new Color(255,255,255, 153));
        notFoundDesc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        notFoundDesc.setText("No matches were found");
        notFoundPanel.add(notFoundDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 160, 830, 40));

        optionsPanel.add(notFoundPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 160, 840, 205));

        enterTermPanel.setOpaque(false);
        enterTermPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        try {
            sVGIconPanel2.set_SVG_URL_String("/icons/defedit/research-white.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        enterTermPanel.add(sVGIconPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 0, 120, 120));

        headerLabel3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        headerLabel3.setForeground(new Color(255,255,255, 153));
        headerLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLabel3.setText("Start a search");
        enterTermPanel.add(headerLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 250, 40));

        headerLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        headerLabel4.setForeground(new Color(255,255,255, 153));
        headerLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLabel4.setText("Find that term!");
        enterTermPanel.add(headerLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 370, 40));

        optionsPanel.add(enterTermPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 160, 370, 205));

        add(optionsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 530));
    }// </editor-fold>//GEN-END:initComponents


    public String getKey(int xid, int yid){
        return Integer.toString(yid) + "&" + Integer.toString(xid);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel enterTermPanel;
    private javax.swing.JLabel header;
    private javax.swing.JLabel headerLabel2;
    private javax.swing.JLabel headerLabel3;
    private javax.swing.JLabel headerLabel4;
    private javax.swing.JLabel notFoundDesc;
    private javax.swing.JPanel notFoundPanel;
    private javax.swing.JPanel optionsPanel;
    private frontend.SVGIconPanel sVGIconPanel1;
    private frontend.SVGIconPanel sVGIconPanel2;
    // End of variables declaration//GEN-END:variables
}
