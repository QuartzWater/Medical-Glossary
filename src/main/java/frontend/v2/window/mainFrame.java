/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frontend.v2.window;

import backend.AppConfig;
import backend.eventadapter.GranularMouseAdapter;
import book.bookpicker.Book;
import frontend.RoundedPanel;
import frontend.v2.containers.DefinitionContainer;
import frontend.v2.containers.SettingsContainer;
import frontend.v2.containers.SpellingContainer;
import frontend.v2.containers.SuggestionContainer;
import frontend.v2.containers.TermInfoContainer;
import frontend.v2.state.AppState;
import frontend.v2.state.AppStateChangeEvent;
import frontend.v2.state.AppStateChangeListener;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JFrame;
/**
 *
 * @author BRIN
 */
public class mainFrame extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(mainFrame.class.getName());

    
    SuggestionContainer sugC;
    
    private boolean isEditEnabled = false;
    private TermInfoContainer TICEventReference;
    SpellingContainer sc;
    SettingsContainer settings;
    
    private Color redCrossColor = new Color(218, 68, 83);
    private Color redCrossColorDarker = new Color(195,57,71);
    private Color minimizeColor = new Color(27,36,45);
    private Color minimizeColorDarker = new Color(15,20,25);
    private Color transparent = new Color(0,0,0,0);
    /**
     * Creates new form mainFrame
     */
    public mainFrame() {
        initComponents();
        versionLabel.setText(AppConfig.VERSION);
        
        redCrossIcon.overrideFill("back", transparent);
        redCrossIcon.overrideFill("cross", redCrossColor);
        minimizeIcon.overrideFill("back", transparent);
        
        /* Experimental:::
        decor1.definePositionsForLGP(0, 0, 0, decor1.getPreferredSize().height);
        float[] fractions = {0.0f, 0.5f, 1.0f};
        Color[] colors = {
            new Color(0x2C, 0x53, 0x64),  // top: deep cyan-slate
            new Color(0x20, 0x3A, 0x43),  // middle: dark blue-gray
            new Color(0x0F, 0x20, 0x27)   // bottom: near black
        };

        decor1.enableLinearGradientPaint(fractions, colors);
        */
        
        
        this.setBackground(new Color(0,0,0,0));
        sugC = new SuggestionContainer();
        TermInfoContainer tic = new TermInfoContainer();
        
        sc = new SpellingContainer(tic);
        settings = new SettingsContainer();
        
        decor2Contain.add(sc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, sc.getPreferredSize().width, sc.getPreferredSize().height));
        decor2Contain.add(sugC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, sugC.getPreferredSize().width, sugC.getPreferredSize().height));
        decor2Contain.add(tic, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, tic.getPreferredSize().width, tic.getPreferredSize().height));
        decor2.add(settings, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, settings.getPreferredSize().width, settings.getPreferredSize().height));
        
        settings.setVisible(false);
        
        tic.setVisible(false);
        
        searchOptionsGran = new OptionsGRAN(searchOption);
        createOptionsGRAN = new OptionsGRAN(createOption);
        settingsOptionsGRAN = new OptionsGRAN(settingsOption);
        
        searchOption.addMouseListener(searchOptionsGran);
        createOption.addMouseListener(createOptionsGRAN);
        settingsOption.addMouseListener(settingsOptionsGRAN);
        
        redCrossIcon.addMouseListener(redCrossGran);
        minimizeIcon.addMouseListener(minimizeGran);
        
        searchOptionsGran.provideRunnable(() -> {
            createOptionsGRAN.switchON();
            settingsOptionsGRAN.switchON();
            createOption.setCurrentColor(defaultColor);
            settingsOption.setCurrentColor(defaultColor);
        });
        
        createOptionsGRAN.provideRunnable(() -> {
            searchOptionsGran.switchON();
            settingsOptionsGRAN.switchON();
            searchOption.setCurrentColor(defaultColor);
            settingsOption.setCurrentColor(defaultColor);
        });
        
        settingsOptionsGRAN.provideRunnable(() -> {
            createOptionsGRAN.switchON();
            searchOptionsGran.switchON();
            createOption.setCurrentColor(defaultColor);
            searchOption.setCurrentColor(defaultColor);
        });
        
        final Runnable runnable = () -> {
            
            createOptionsGRAN.switchOff();
            searchOptionsGran.switchON();
            createOption.setCurrentColor(hoverColor);
            searchOption.setCurrentColor(defaultColor);
            repaint();
        };
        
        AppState.addListener(new AppStateChangeListener() {
            @Override
            public void appStateChanged(AppStateChangeEvent e) {
                if(e.getNewType() == AppState.Type.CREATE){
                    runnable.run();
                }
            }
        });
        
        AppState.addTICEventListener((e) -> {
            
            isEditEnabled = e.isEditActive();
            TICEventReference = e.getReference();
        });
        
        
        
        searchOption.addMouseListener(new GranularMouseAdapter(){
            
            @Override
            public void actOnMouseRelease(MouseEvent e){
                
                
                if(!decor2Contain.isVisible()){
                    decor2Contain.setVisible(true);
                    sc.getSpellingBox().requestFocusInWindow();
                    settings.setVisible(false);
                    
                }
                
                if(AppState.getCurrentState() == AppState.Type.SEARCH){
                    return;
                }
                
                boolean approved = true;
                if(AppState.getCurrentState() == AppState.Type.CREATE){
                    
                    if(tic.isVisible()){
                        approved = StateChangeWarning.showWarningDialog();
                    }
                }
                
                if(approved){
                    
                    
                    AppState.changeState(AppState.Type.SEARCH);
                    if(tic.isVisible()){
                        if(sc.getSpellingBox().getText().isBlank()){
                            AppState.changeInputSpelling("");
                        }
                        else{
                            sc.getSpellingBox().setText("");
                        }
                    }else{
                        AppState.changeInputSpelling(sc.getSpellingBox().getText());
                    }
                }else {
                    runnable.run();
                }
            }
        });
        
        createOption.addMouseListener(new GranularMouseAdapter(){
            
            @Override
            public void actOnMouseRelease(MouseEvent e){
                
                if(!decor2Contain.isVisible()){
                    decor2Contain.setVisible(true);
                    sc.getSpellingBox().requestFocusInWindow();
                    settings.setVisible(false);
                }
                
                if(AppState.getCurrentState() == AppState.Type.CREATE){
                    return;
                }
                
                if(isEditEnabled){
                    TICEventReference.getController().toggleEditIcon();
                    if(TICEventReference.getController().getUserResponse() == ContentEditWarning.Response.ESCAPE){
                        return;
                    }
                }
                
                AppState.changeState(AppState.Type.CREATE);
                AppState.changeActiveSearchedTerm(null);
                AppState.changeInputSpelling(sc.getSpellingBox().getText());
                
            }
        });
        
        settingsOption.addMouseListener(new GranularMouseAdapter(){
            
            @Override
            public void actOnMouseRelease(MouseEvent e){
                
                decor2Contain.setVisible(false);
                settings.setVisible(true);
                createOption.setCurrentColor(defaultColor);
                searchOption.setCurrentColor(defaultColor);
            }
        });
        
        
        
        searchOptionsGran.switchOff();
        tic.registerMainFrame(this);
    }
    
    private OptionsGRAN searchOptionsGran;
    private OptionsGRAN createOptionsGRAN;
    private OptionsGRAN settingsOptionsGRAN;
    
    private static Color defaultColor = new Color(57,75,92);
    private static Color hoverColor = new Color(76,94,111);
    private static Color pressedColor = new Color(85,105,123);

    private class OptionsGRAN extends  GranularMouseAdapter {

        private final RoundedPanel toControl;
        private Runnable runnable;
        
        public OptionsGRAN(RoundedPanel toControl) {
            this.toControl = toControl;
        }
        
        public void provideRunnable(Runnable runnable){
            this.runnable = runnable;
        }
        
        
        @Override
        public void actOnMouseEntry(MouseEvent e){
            
            toControl.setCurrentColor(hoverColor);
            toControl.repaint();
        }
        
        @Override
        public void actOnMouseExit(MouseEvent e){
            toControl.setCurrentColor(defaultColor);
            toControl.repaint();
        }
        
        @Override
        public void actOnMousePress(MouseEvent e){
            toControl.setCurrentColor(pressedColor);
            toControl.repaint();
        }
        
        @Override
        public void actOnMouseRelease(MouseEvent e){
            toControl.setCurrentColor(hoverColor);
            
            if(runnable != null){
                runnable.run();
            }
            toControl.getParent().repaint();
            this.switchOff();
        }
    }
    
    private GranularMouseAdapter redCrossGran = new GranularMouseAdapter(){
            
        @Override
        public void actOnMouseEntry(MouseEvent e){
            redCrossIcon.overrideFill("back", redCrossColor);
            redCrossIcon.overrideFill("cross", Color.WHITE);
        }
        
        @Override
        public void actOnMouseExit(MouseEvent e){
            redCrossIcon.overrideFill("back", transparent);
            redCrossIcon.overrideFill("cross", redCrossColor);
        }
        
        @Override
        public void actOnMousePress(MouseEvent e){
            redCrossIcon.overrideFill("back", redCrossColorDarker);
        
        }
        
        @Override
        public void actOnMouseRelease(MouseEvent e){
            redCrossIcon.overrideFill("back", redCrossColor);
            System.exit(0);
        }
    };
    
    private GranularMouseAdapter minimizeGran = new GranularMouseAdapter(){
            
        @Override
        public void actOnMouseEntry(MouseEvent e){
            
            minimizeIcon.overrideFill("back", minimizeColor);
        }
        
        @Override
        public void actOnMouseExit(MouseEvent e){
            
            minimizeIcon.overrideFill("back", transparent);
        }
        
        @Override
        public void actOnMousePress(MouseEvent e){
            
            minimizeIcon.overrideFill("back", minimizeColorDarker);
            
        }
        
        @Override
        public void actOnMouseRelease(MouseEvent e){
            
            minimizeIcon.overrideFill("back", minimizeColor);
            setState(JFrame.ICONIFIED);
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

        decor1 = new frontend.RoundedPanel();
        decor2 = new frontend.RoundedPanel();
        decor2Contain = new javax.swing.JPanel();
        versionLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        settingsOption = new frontend.RoundedPanel();
        settingsOptionLabel = new javax.swing.JLabel();
        sVGIconPanel3 = new frontend.SVGIconPanel();
        searchOption = new frontend.RoundedPanel();
        searchOptionLabel = new javax.swing.JLabel();
        sVGIconPanel1 = new frontend.SVGIconPanel();
        createOption = new frontend.RoundedPanel();
        createOptionLabel = new javax.swing.JLabel();
        sVGIconPanel2 = new frontend.SVGIconPanel();
        redCrossIcon = new frontend.SVGIconPanel();
        minimizeIcon = new frontend.SVGIconPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Medical Glossary " + AppConfig.VERSION);
        setUndecorated(true);
        setSize(new java.awt.Dimension(1400, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        decor1.setPreferredSize(new java.awt.Dimension(1350, 690));
        decor1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        decor2.setCurrentColor(new java.awt.Color(39, 52, 65));
        decor2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        decor2Contain.setOpaque(false);
        decor2Contain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        decor2.add(decor2Contain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 1300, 610));

        decor1.add(decor2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 1300, 620));

        versionLabel.setFont(new java.awt.Font("Agency FB", 1, 14)); // NOI18N
        versionLabel.setForeground(new java.awt.Color(255, 255, 255));
        versionLabel.setText("v0.5.0");
        decor1.add(versionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 665, 230, -1));

        titleLabel.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setText("MEDICAL GLOSSARY");
        decor1.add(titleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 625, 230, -1));

        settingsOption.setArcSize(15);
        settingsOption.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        settingsOptionLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        settingsOptionLabel.setForeground(new java.awt.Color(255, 255, 255));
        settingsOptionLabel.setText("Settings");
        settingsOption.add(settingsOptionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 17, 80, -1));

        try {
            sVGIconPanel3.set_SVG_URL_String("/icons/settings-white.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        sVGIconPanel3.setIconPosition(frontend.SVGIconPanel.iconPosition.CENTER);
        sVGIconPanel3.setIconSquarePadding(5.0);
        settingsOption.add(sVGIconPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, -1, -1));

        decor1.add(settingsOption, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 630, 160, 60));

        searchOption.setArcSize(15);
        searchOption.setCurrentColor(new java.awt.Color(76, 94, 111));
        searchOption.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        searchOptionLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        searchOptionLabel.setForeground(new java.awt.Color(255, 255, 255));
        searchOptionLabel.setText("Search");
        searchOption.add(searchOptionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 17, 70, -1));

        try {
            sVGIconPanel1.set_SVG_URL_String("/icons/search-white.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        sVGIconPanel1.setIconPosition(frontend.SVGIconPanel.iconPosition.CENTER);
        sVGIconPanel1.setIconSquarePadding(5.0);
        searchOption.add(sVGIconPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, -1, -1));

        decor1.add(searchOption, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 630, 160, 60));

        createOption.setArcSize(15);
        createOption.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        createOptionLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        createOptionLabel.setForeground(new java.awt.Color(255, 255, 255));
        createOptionLabel.setText("Create");
        createOption.add(createOptionLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 17, 70, -1));

        try {
            sVGIconPanel2.set_SVG_URL_String("/icons/create-white.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        sVGIconPanel2.setIconPosition(frontend.SVGIconPanel.iconPosition.CENTER);
        sVGIconPanel2.setIconSquarePadding(5.0);
        createOption.add(sVGIconPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, -1, -1));

        decor1.add(createOption, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 630, 160, 60));

        try {
            redCrossIcon.set_SVG_URL_String("/icons/red-cross-active.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        decor1.add(redCrossIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1355, 15, 40, 40));

        try {
            minimizeIcon.set_SVG_URL_String("/icons/minimize-active.svg");
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        decor1.add(minimizeIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1355, 60, 40, 40));

        getContentPane().add(decor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private frontend.RoundedPanel createOption;
    private javax.swing.JLabel createOptionLabel;
    private frontend.RoundedPanel decor1;
    private frontend.RoundedPanel decor2;
    private javax.swing.JPanel decor2Contain;
    private frontend.SVGIconPanel minimizeIcon;
    private frontend.SVGIconPanel redCrossIcon;
    private frontend.SVGIconPanel sVGIconPanel1;
    private frontend.SVGIconPanel sVGIconPanel2;
    private frontend.SVGIconPanel sVGIconPanel3;
    private frontend.RoundedPanel searchOption;
    private javax.swing.JLabel searchOptionLabel;
    private frontend.RoundedPanel settingsOption;
    private javax.swing.JLabel settingsOptionLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel versionLabel;
    // End of variables declaration//GEN-END:variables
}
