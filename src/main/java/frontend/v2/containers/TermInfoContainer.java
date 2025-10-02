/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package frontend.v2.containers;

import backend.v2.data.AdvancedTermDataManagement;
import backend.v2.data.TermDataIOException;
import backend.v2.term.AdvancedHyperlink;
import backend.v2.term.AdvancedTerm;
import backend.v2.term.IllegalTermStateException;
import book.bookpicker.Book;
import frontend.RoundedButton;
import frontend.SVGIconPanel;
import frontend.v2.controller.ReferenceContainerController;
import frontend.v2.controller.SpellingContainerController;
import frontend.v2.controller.TermInfoContainerController;
import frontend.v2.state.ActiveSearchedTermChangeEvent;
import frontend.v2.state.ActiveSearchedTermChangeListener;
import frontend.v2.state.AppState;
import frontend.v2.state.AppStateChangeEvent;
import frontend.v2.state.AppStateChangeListener;
import frontend.v2.state.BookChangeEvent;
import frontend.v2.state.BookChangeListener;
import frontend.v2.state.InputTermChangeEvent;
import frontend.v2.state.InputTermChangeListener;
import frontend.v2.window.mainFrame;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.text.StyledDocument;

/**
 *
 * @author BRIN
 */
public class TermInfoContainer extends javax.swing.JPanel {

    /**
     * Creates new form TermInfoContainer
     */
    
    DefinitionContainer dc;
    ReferenceContainer rc;
    HyperlinkContainer hc;
    
    private SpellingContainerController scc;
    
    private AdvancedTermDataManagement atdm;
    private Book book;
    
    private TermInfoContainerController ticControl;
    
    private boolean isReferencePageInvalid = true;
    
    public TermInfoContainer() {
        this.book = AppState.getCurrentBook();
        this.atdm = book.getATDM();
        initComponents();
        
        dc = new DefinitionContainer();
        rc = new ReferenceContainer(book);
        hc = new HyperlinkContainer();
        
        add(dc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1260, 500));
        add(rc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1260, 400));
        add(hc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1260, 400));
        
        dc.setVisible(false);
        rc.setVisible(false);
        hc.setVisible(false);
        
        
        this.nextBtn.setVisible(false);
        this.backBtn.setVisible(false);
        editIcon.setVisible(false);
        headerLabel.setVisible(false);
        
        definitionIcon.setVisible(false);
        referenceIcon.setVisible(false);
        hyperlinkIcon.setVisible(false);
        
        Map<SVGIconPanel, ContentProcessor> btnMap = new HashMap<>();
        btnMap.put(definitionIcon, dc);
        btnMap.put(referenceIcon, rc);
        btnMap.put(hyperlinkIcon, hc);
        
        this.ticControl = new TermInfoContainerController(this, btnMap, definitionIcon);
        
        AppState.addListener(new AppStateChangeListener() {
            @Override
            public void appStateChanged(AppStateChangeEvent e) {
                if(e.getNewType() == AppState.Type.SEARCH){
                    setVisible(false); // to allow suggestion panel's message to show up (msg = "Start a search")
                    System.out.println("0001 DEBUG: SEARCH CALLED IN TIC");
                }
            }
        });
        
        AppState.addActiveSearchedTermChangeListener(new ActiveSearchedTermChangeListener() {
            @Override
            public void activeSearchedTermChanged(ActiveSearchedTermChangeEvent e) {
                // whenever a selection is made from suggestion panel (exisiting or new) immediately show the term info container.
                setVisible(true);
   
                if(e.getNewAdvTerm() == null){
                    activateCreationProfile(); // enable only creation Profile, since only new term can be null initially.
                    ticControl.activateIconMakeEditable(definitionIcon); // always start at definition container. when searching for a new term.
                    // On create mode this is kind of redundant since the Spelling Box is always set to non-editable once user leaves definition Container.
                    // But still not putting an if condition in case it gives rise to subtle bugs.
                    
                }else {
                    // ie the case for a non-null term (only possible in Search Mode)
                    // since if the suggestion panel finds that selection pointed to a non-existent term,
                    // it immediately switches the AppState to Create Mode.
                    ticControl.activateIcon(definitionIcon);
                    activateSearchProfile();
                }
            }
        });
        
        AppState.addSearchTermChangeListener(new InputTermChangeListener() {
            @Override
            public void searchTermChanged(InputTermChangeEvent e) {
                System.out.println("INITIAL TIC INVISIBLITY -> CHECK");
                setVisible(false);
                
                if(e.getNewTerm().isBlank()){
                    // Do nothing
                    
                    
                }else if(AppState.getCurrentState() == AppState.Type.CREATE){
                    
                    // Override the non - visiblity since we have to allow any non-blank (and valid) term during create mode
                    if(!AppState.isTermFound()){
                        System.out.println("TERM NOT FOUND TIC INVISIBLITY -> CHECK");
                        setVisible(true);
                        
                        System.out.println("COMPONENTS IN TIC: " + getComponentCount() + " AND IS TIC VISIBLE?? " + isVisible());
                        
                    }
                }
                repaint();
            }
        });
        
        ReferenceContainerController.addActionsWhenInvalidPageFound(() -> {
            isReferencePageInvalid = true;
            if(rc.isVisible()){
                nextBtn.setEnabled(false);
            }else {
                nextBtn.setEnabled(true);
            }
        });
        
        ReferenceContainerController.addActionsWhenValidPageFound(() -> {
            isReferencePageInvalid = false;
            if(AppState.getCurrentState() == AppState.Type.CREATE){
                nextBtn.setEnabled(true);
            }
        });
        
        // Future Me (10 mins later btw) : FOLLOWING DOES NOT WORK AND WILL LEAD TO LOADS OF NULL POINTER EXCEPTIONS HAHAHA
        // Following are defined at very last so as to be able to override some TermInfoContainer method calls.
        // (as in case of TermInfoContainer calling activateIcon from its controller). But because initializing the following child components
        // later means that the listeners for AppState associated with them will be later in the List. Which will enable them to override some calls of TermInfoContainer.
        // This becomes important when activateIcon is called whenever any term (regardless of its existence) is selected.
        // activateIcon is called whenever we don't want to enable ability to edit immediately 
        // it internally calls setNonEditable() (method from ContentProcessor interface which all three of them implements)
        // this is desirable when user switches from one panel to another while browsing. (in search mode)
        // however the way the above call works is it immediately switches to defintion panel whenever a new term is searched
        // but in create mode it has side effect of disabling editor panel of Definition Container 
        // and theres no else way of enabling it except through the edit icon (which also becomes invisible during create mode)
        // so initializing the following at very last makes sure that their associated listener (AppStateChangedListener) is in a particular order.
        // --------------------
        // ---- (future me : We are basically forced to not declare them first...
        // a solution I found was to - obviously make a very specific method in the controller which calls activateIcon 
        // and overrides the setNonEditable call by calling setEditable call after it.
        // that method has no use (and should not be used elsewhere except here and only here))
        
        /*
        dc = new DefinitionContainer();
        rc = new ReferenceContainer(book);
        hc = new HyperlinkContainer();
        
        add(dc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1260, 500));
        add(rc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1260, 400));
        add(hc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1260, 400));
        
        dc.setVisible(false);
        rc.setVisible(false);
        hc.setVisible(false);
        */
    }
    
    @Override
    public void setVisible(boolean flag){
        super.setVisible(flag);
        System.out.println("WHO ON EARTH IS CALLING THIS METHOD: " + flag + " WAS SET");
        
    }
    
    public TermInfoContainerController getController(){
        return this.ticControl;
    }
    
    public void registerSpellingContainerController(SpellingContainerController scc){
        this.scc = scc;
        ticControl.registerSCC(scc);
    }
    
    public boolean isReferencePageInvalid(){
        return isReferencePageInvalid;
    }
    
    public SpellingContainerController getSCC(){
        return this.scc;
    }
    
    private mainFrame mainF;
    public void registerMainFrame(mainFrame m){
        this.mainF = m;
    }
    
    public RoundedButton getNextBtn() {
        return nextBtn;
    }

    public RoundedButton getBackBtn() {
        return backBtn;
    }

    public RoundedButton getChangeBookButton() {
        return changeBookButton;
    }
    

    public DefinitionContainer getDC() {
        return dc;
    }

    public ReferenceContainer getRC() {
        return rc;
    }

    public HyperlinkContainer getHC() {
        return hc;
    }

    
    
    public SVGIconPanel getEditIcon() {
        return editIcon;
    }

    public JLabel getHeaderLabel() {
        return headerLabel;
    }
    
    public void activateNoActiveTermProfile(){
        
        this.definitionIcon.setVisible(false);
        this.referenceIcon.setVisible(false);
        this.hyperlinkIcon.setVisible(false);
        this.editIcon.setVisible(false);
        dc.setVisible(false);
        rc.setVisible(false);
        hc.setVisible(false);
    }
    
    public void activateCreationProfile(){
        
        this.definitionIcon.setVisible(false);
        this.referenceIcon.setVisible(false);
        this.hyperlinkIcon.setVisible(false);
        this.editIcon.setVisible(false);
        this.headerLabel.setVisible(true);
        this.nextBtn.setVisible(true);
    }
    
    public void activateSearchProfile(){
        
        this.definitionIcon.setVisible(true);
        this.referenceIcon.setVisible(true);
        this.hyperlinkIcon.setVisible(true);
        this.editIcon.setVisible(true);
        this.headerLabel.setVisible(true);
        this.nextBtn.setVisible(false);
        this.backBtn.setVisible(false);
    }
    
    // Used by TermSearchModule
    
    
    
    public Book getBook(){
        
        return book;
    }

    public AdvancedTermDataManagement getATDM() {
        return this.atdm;
    }
    
    
    public AdvancedTerm extractContent() throws IllegalTermStateException{
        
        AdvancedTerm newAdv = new AdvancedTerm(AppState.getCurrentInputSpelling());
        newAdv.setDefinition(dc.extractContent().get(0));
        List<String> ref = rc.extractContent();
        newAdv.setPage(Integer.parseInt(ref.get(0)));
        newAdv.setSuperCategory(ref.get(1));
        newAdv.setMiddleCategory(ref.get(2));
        newAdv.setSubCategory(ref.get(3));
        
        List<AdvancedHyperlink> hyper = hc.extractContent();
        newAdv.replaceHyperlinks(hyper);
        
        return newAdv;
    }
    
    public void provideContent(AdvancedTerm adv){
        
        dc.setContent(book, adv);
        rc.setContent(book, adv);
        hc.setContent(book, adv);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roundedButton1 = new frontend.RoundedButton();
        changeBookButton = new frontend.RoundedButton();
        jPanel1 = new javax.swing.JPanel();
        headerLabel = new javax.swing.JLabel();
        iconContainer = new javax.swing.JPanel();
        hyperlinkIcon = new frontend.SVGIconPanel();
        referenceIcon = new frontend.SVGIconPanel();
        definitionIcon = new frontend.SVGIconPanel();
        editIcon = new frontend.SVGIconPanel();
        nextBtn = new frontend.RoundedButton();
        backBtn = new frontend.RoundedButton();

        roundedButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundedButton1ActionPerformed(evt);
            }
        });

        changeBookButton.setText("Change Book");
        changeBookButton.setArcSize(15);

        setBackground(new java.awt.Color(255, 153, 153));
        setMinimumSize(new java.awt.Dimension(1260, 530));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1260, 530));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        headerLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        headerLabel.setForeground(new java.awt.Color(255, 255, 255));
        headerLabel.setText("jLabel1");
        jPanel1.add(headerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 2, 130, 40));

        iconContainer.setBackground(new java.awt.Color(153, 153, 153));
        iconContainer.setOpaque(false);
        iconContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        try {
            hyperlinkIcon.set_SVG_URL_String("/icons/tic/hyperlink.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        hyperlinkIcon.setArcSize(15);
        hyperlinkIcon.setIconPosition(frontend.SVGIconPanel.iconPosition.CENTER);
        hyperlinkIcon.setIconSquarePadding(3.0);
        iconContainer.add(hyperlinkIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 40, 40));

        try {
            referenceIcon.set_SVG_URL_String("/icons/tic/reference.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        referenceIcon.setArcSize(15);
        referenceIcon.setIconPosition(frontend.SVGIconPanel.iconPosition.CENTER);
        referenceIcon.setIconSquarePadding(3.0);
        iconContainer.add(referenceIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 40, 40));

        try {
            definitionIcon.set_SVG_URL_String("/icons/tic/definition.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        definitionIcon.setArcSize(15);
        definitionIcon.setIconPosition(frontend.SVGIconPanel.iconPosition.CENTER);
        definitionIcon.setIconSquarePadding(3.0);
        iconContainer.add(definitionIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 40, 40));

        jPanel1.add(iconContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 0, 190, 50));

        try {
            editIcon.set_SVG_URL_String("/icons/tic/edit.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        editIcon.setArcSize(15);
        editIcon.setIconPosition(frontend.SVGIconPanel.iconPosition.CENTER);
        editIcon.setIconSquarePadding(5.0);
        jPanel1.add(editIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 6, 30, 30));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 50));

        nextBtn.setText("Next");
        nextBtn.setArcSize(15);
        nextBtn.setCurrentColor(new java.awt.Color(15, 20, 25));
        add(nextBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 480, -1, -1));

        backBtn.setText("Back");
        backBtn.setArcSize(15);
        backBtn.setCurrentColor(new java.awt.Color(15, 20, 25));
        add(backBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 480, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void roundedButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundedButton1ActionPerformed
        try {
            // TODO add your handling code here:
            StyledDocument doc = dc.extractContent().get(0);
            List<String> content = rc.extractContent();
            
            AdvancedTerm toSet = new AdvancedTerm("TESTv_0_5_0");
            toSet.setDefinition(doc);
            toSet.setPage(Integer.parseInt(content.get(0)));
            toSet.setSuperCategory(content.get(1));
            toSet.setMiddleCategory(content.get(2));
            toSet.setSubCategory(content.get(3));
            atdm.load();
            
            atdm.flush(null, toSet);
            
            System.out.println("MALFORMED TERMS: " +atdm.getMalformedTerms().size());
            System.out.println("FAILEDIO TERMS: " +atdm.getFailedIOTerms().size());
            
        } catch (IllegalTermStateException ex) {
            System.getLogger(TermInfoContainer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            System.out.println("ILLEGALTERMSTATEEXCEPTION");
        } catch (TermDataIOException ex) {
            System.getLogger(TermInfoContainer.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }//GEN-LAST:event_roundedButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private frontend.RoundedButton backBtn;
    private frontend.RoundedButton changeBookButton;
    private frontend.SVGIconPanel definitionIcon;
    private frontend.SVGIconPanel editIcon;
    private javax.swing.JLabel headerLabel;
    private frontend.SVGIconPanel hyperlinkIcon;
    private javax.swing.JPanel iconContainer;
    private javax.swing.JPanel jPanel1;
    private frontend.RoundedButton nextBtn;
    private frontend.SVGIconPanel referenceIcon;
    private frontend.RoundedButton roundedButton1;
    // End of variables declaration//GEN-END:variables
}
