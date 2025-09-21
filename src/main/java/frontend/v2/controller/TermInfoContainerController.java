/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend.v2.controller;

import backend.AppConstants;
import backend.eventadapter.GranularMouseAdapter;
import backend.v2.data.TermDataIOException;
import backend.v2.term.IllegalTermStateException;
import frontend.RoundedButton;
import frontend.SVGIconPanel;
import frontend.v2.containers.ContentProcessor;
import frontend.v2.containers.ReferenceContainer;
import frontend.v2.containers.TermInfoContainer;
import frontend.v2.state.AppState;
import frontend.v2.state.AppStateChangeEvent;
import frontend.v2.state.AppStateChangeListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import frontend.v2.window.ContentEditWarning;
import javax.swing.JComponent;
import javax.swing.text.DefaultStyledDocument;

/**
 *
 * @author BRIN
 */
public class TermInfoContainerController {
    
    private final TermInfoContainer tc;
    private final ContentProcessorManager contentProcessorManager;
    private final SVGIconPanel editIcon;
    
    private SpellingContainerController scc;

    public TermInfoContainerController(TermInfoContainer tc,Map<SVGIconPanel, ContentProcessor> btnMap, SVGIconPanel firstActive) {
        this.tc = tc;
        this.editIcon = tc.getEditIcon();
        contentProcessorManager = new ContentProcessorManager(btnMap, firstActive);
        CreateTermPanelManager ctpm = new CreateTermPanelManager();
    }
    
    public void activateIcon(SVGIconPanel toActivate){
        contentProcessorManager.activateIcon(toActivate);
    }
    
    public void activateIconMakeEditable(SVGIconPanel toActivate){
        contentProcessorManager.activateIconMakeEditable(toActivate);
    }
    
    public void toggleEditIcon(){
        contentProcessorManager.calledFromOutside = true;
        contentProcessorManager.toggleEditIcon();
        contentProcessorManager.calledFromOutside = false;
    }
    
    public ContentEditWarning.Response getUserResponse(){
        ContentEditWarning.Response toReturn =  contentProcessorManager.userResponse;
        contentProcessorManager.userResponse = null;
        return toReturn;
    }
    
    public void registerSCC(SpellingContainerController scc){
        this.scc = scc;
    }
    
    private class CreateTermPanelManager{
        
        RoundedButton nextButton = tc.getNextBtn();
        RoundedButton backButton = tc.getBackBtn();

        ContentProcessor[] allContainers;
        JPanel[] allPanels;
        
        public CreateTermPanelManager() {
            
            allContainers = new ContentProcessor[]{
                tc.getDC(), tc.getRC(), tc.getHC()
            };
            
            allPanels = new JPanel[]{
                tc.getDC(), tc.getRC(), tc.getHC()
            };
            
            activateSaveBtn();
            activateBackBtn();
            
            // VERY SPECIAL HACK to effectively provide clean slate whenever mode is changed in reference panel or hyperlink panel
            AppState.addListener(new AppStateChangeListener() {
                @Override
                public void appStateChanged(AppStateChangeEvent e) {
                    allPanels[currentIndex].setVisible(false);
                    currentIndex = 0;
                    backButton.setVisible(false);
                    nextButton.setEnabled(true);
                    scc.enableSpellingBox();
                }
            });
        }
        
        int currentIndex = 0;
        
        public void reset(){
            currentIndex = 0;
        }
        
        private void activateSaveBtn(){
            
            GranularMouseAdapter saveGran = new GranularMouseAdapter(){
                
                Color prevBack;
                Color prevFore;
                
                @Override
                public void actOnMouseEntry(MouseEvent e){
                    prevBack = nextButton.getCurrentColor();
                    prevFore = nextButton.getForeground();
                    
                    if(nextButton.getText().equals("Next")){
                        nextButton.setCurrentColor(AppState.getCurrentBook().getColorScheme().getHoverColor());
                        nextButton.setForeground(AppState.getCurrentBook().getColorScheme().getDisabledColor());
                    }else {
                        nextButton.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getHoverColor());
                    }
                    
                    nextButton.repaint();
                }
                
                @Override
                public void actOnMouseExit(MouseEvent e){
                    
                    nextButton.setCurrentColor(prevBack);
                    nextButton.setForeground(prevFore);
                    nextButton.repaint();
                }
                
                @Override
                public void actOnMousePress(MouseEvent e){
                    
                    if(nextButton.getText().equals("Next")){
                        nextButton.setCurrentColor(AppState.getCurrentBook().getColorScheme().getPressedColor());
                    }else{
                        nextButton.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getPressedColor());
                    }
                    nextButton.repaint();
                }
                
                @Override
                public void actOnMouseRelease(MouseEvent e){
                    
                    if(nextButton.getText().equals("Next")){
                    nextButton.setCurrentColor(AppState.getCurrentBook().getColorScheme().getHoverColor());
                    }else {
                        nextButton.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getHoverColor());
                    }
                    nextButton.repaint();
                }
                
                
            };
            
            nextButton.addMouseListener(saveGran);
            
            ActionListener saveActListen = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    if(AppState.getCurrentState() == AppState.Type.CREATE){
                        if(currentIndex < allContainers.length - 1){

                            if(currentIndex == allContainers.length - 2){
                                nextButton.setText("Save");
                                nextButton.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getHoverColor());
                                nextButton.setForeground(Color.white);
                            }
                            currentIndex++;
                            if(currentIndex == 1){
                                backButton.setVisible(true);
                                scc.disableSpellingBox();
                                nextButton.setEnabled(!tc.isReferencePageInvalid()); // initial setup for next button
                            }
                            allPanels[currentIndex].setVisible(true);
                            allContainers[currentIndex].setEditable();
                            if(currentIndex > 0){
                                allPanels[currentIndex - 1].setVisible(false);
                                allContainers[currentIndex - 1].setNonEditable();
                            }

                            tc.getHeaderLabel().setText(allContainers[currentIndex].getHeaderName());

                        }
                        else {

                            try {
                                tc.getATDM().flush(null, tc.extractContent());
                                allPanels[currentIndex].setVisible(false);
                                allContainers[currentIndex].setNonEditable();
                                currentIndex = 0;
                                allPanels[currentIndex].setVisible(true);
                                allContainers[currentIndex].setEditable();
                                backButton.setVisible(false);
                                nextButton.setText("Next");
                                scc.setTextQuietly("");
                                scc.enableSpellingBox();
                                tc.getDC().getDefinitionPane().setStyledDocument(new DefaultStyledDocument());
                                // DO NOT IN THE GOD DAMN HELL USE .setText() HERE!! IT'LL CLEAR EVERYTHING STORED IN-MEMORY

                            } catch (IllegalTermStateException ex) {
                                System.getLogger(TermInfoContainerController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                            } catch (TermDataIOException ex) {
                                System.getLogger(TermInfoContainerController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                            }
                        }
                    }else if(AppState.getCurrentState() == AppState.Type.SEARCH){
                        // todo
                    }
                }
            };
            
            nextButton.addActionListener(saveActListen);
        }
        
        private void activateBackBtn(){
            
            
            
            GranularMouseAdapter backGran = new GranularMouseAdapter(){
                
                Color prevBack;
              
                @Override
                public void actOnMouseEntry(MouseEvent e){
                    prevBack = backButton.getCurrentColor();
                    
                    backButton.setCurrentColor(AppConstants.DEFAULT_BACK_BUTTON_CS_1.getHoverColor());
                    backButton.repaint();
                }
                
                @Override
                public void actOnMouseExit(MouseEvent e){
                    backButton.setCurrentColor(prevBack);
                    backButton.repaint();
                }
                
                @Override
                public void actOnMousePress(MouseEvent e){
                    backButton.setCurrentColor(AppConstants.DEFAULT_BACK_BUTTON_CS_1.getPressedColor());
                    backButton.repaint();
                }
                
                @Override
                public void actOnMouseRelease(MouseEvent e){
                    backButton.setCurrentColor(AppConstants.DEFAULT_BACK_BUTTON_CS_1.getHoverColor());
                    backButton.repaint();
                }
                
            };
            
            backButton.addMouseListener(backGran);
            
            ActionListener backActListen = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    if(AppState.getCurrentState() == AppState.Type.CREATE){
                        if(currentIndex > 0){
                            if(currentIndex == allContainers.length - 1){
                                nextButton.setText("Next");
                            }
                            currentIndex--;
                            if(currentIndex == 0){
                                backButton.setVisible(false);
                                nextButton.setEnabled(true);
                                scc.enableSpellingBox();
                            }
                            else if(currentIndex == 1 && tc.isReferencePageInvalid()){
                                nextButton.setEnabled(false);
                            }

                            allPanels[currentIndex].setVisible(true);
                            allContainers[currentIndex].setEditable();
                            if(currentIndex < allContainers.length - 1){
                                allPanels[currentIndex + 1].setVisible(false);
                                allContainers[currentIndex + 1].setNonEditable();
                            }


                        }
                        tc.getHeaderLabel().setText(allContainers[currentIndex].getHeaderName());

                    }
                }
            };
            
            backButton.addActionListener(backActListen);
            
        }
    }
    
    private class ContentProcessorManager {
        
        private static final Color WHITE_BG = new Color(255,255,255,255);
        //private static final Color WHITE_BG_128ALPHA = new Color(255,255,255,200);
        
        private SVGIconPanel currentlyActiveIcon;
        private final Map<SVGIconPanel, ContentProcessor> btnMap;
        private final Map<SVGIconPanel, GranularMouseAdapter> btn_granMap;
        
        public ContentProcessorManager(Map<SVGIconPanel, ContentProcessor> btnMap, SVGIconPanel firstActive) {
            this.btnMap = btnMap;
            this.btn_granMap = new HashMap<>();
            initializeContainerIcons();
            initializeEditIcon();
            activateIcon(firstActive);
        }
        
        public final void activateIcon(SVGIconPanel toActivate){
            
            editIcon.setBackgroundPainted(false);
            editGran.setCanRespondToHover(true);
            if(activated){
                toggleEditIcon();
            }
            
            if(currentlyActiveIcon != null){
                currentlyActiveIcon.setBackgroundPainted(false);
                btn_granMap.get(currentlyActiveIcon).switchON();
                ContentProcessor proc = btnMap.get(currentlyActiveIcon);
                
                proc.setNonEditable();
                JPanel container = (JPanel) proc;
                container.setVisible(false);
            }
            ContentProcessor proc = btnMap.get(toActivate);
            
            toActivate.setCurrentColor(WHITE_BG);
            toActivate.setBackgroundPainted(true);
            btn_granMap.get(toActivate).switchOff();
            JPanel container = (JPanel) proc;
            container.setVisible(true);
            tc.getHeaderLabel().setText(proc.getHeaderName());
            currentlyActiveIcon = toActivate;
            toActivate.getParent().repaint();
        }
        
        // method specifically made for listener of TermInfoContainer
        public final void activateIconMakeEditable(SVGIconPanel toActivate){
            activateIcon(toActivate);
            ContentProcessor contentProcessor = btnMap.get(toActivate);
            contentProcessor.setEditable();
        }
        
        public final void setEditable(boolean toSet){
            ContentProcessor proc = btnMap.get(currentlyActiveIcon);
            if(toSet){
                proc.setEditable();
            }else{
                proc.setNonEditable();
            }
        }
        
        private void initializeContainerIcons(){
            for (SVGIconPanel icon : btnMap.keySet()) {
                GranularMouseAdapter gran = new GranularMouseAdapter(){
                    
                    double prevIconSqrPad = 0;
                    @Override
                    public void actOnMouseEntry(MouseEvent e){
                        icon.setCurrentColor(WHITE_BG);
                        icon.setBackgroundPainted(true);
                        icon.repaint();
                    }
                    
                    @Override
                    public void actOnMouseExit(MouseEvent e){
                        icon.setBackgroundPainted(false);
                        icon.repaint();
                    }
                    
                    @Override
                    public void actOnMousePress(MouseEvent e){
                        icon.setCurrentColor(WHITE_BG);
                        prevIconSqrPad = icon.getIconSquarePadding();
                        icon.setIconSquarePadding(prevIconSqrPad + 3.0);
                        icon.repaint();
                    }
                    
                    @Override
                    public void actOnMouseRelease(MouseEvent e){
                        icon.setIconSquarePadding(prevIconSqrPad);
                        actOnMouseExit(e);
                        activateIcon((SVGIconPanel) e.getSource());
                        
                    }
                };
                
                icon.addMouseListener(gran);
                btn_granMap.put(icon, gran);
            }
        }
        
        private GranularMouseAdapter editGran;
        private boolean activated = false;
        
        private boolean calledFromOutside= true;
        private ContentEditWarning.Response userResponse;
        
        private void toggleEditIcon(){
            ContentProcessor proc = btnMap.get(currentlyActiveIcon);
            
            if(!activated){
                activated = true;
                editGran.setCanRespondToHover(false);
                proc.setEditable();
                
            }
            else {
                
                if(proc.wasContentChanged()){
                    ((JComponent) proc).requestFocusInWindow();
                    if(proc instanceof ReferenceContainer && tc.isReferencePageInvalid()){ // well i can ask Referencecontainer directly regarding this but TIC already has the same info so who the heck cares
                        userResponse = ContentEditWarning.showDialogWithInvalid();
                    }else{
                        userResponse = ContentEditWarning.showDialog();
                    }
                    if(null != userResponse){
                        switch (userResponse) {
                        case YES -> {
                            proc.saveOnlyThis();
                        }
                        
                        case NO -> {
                            proc.revertChanges();
                        }
                        
                        case ESCAPE -> {
                            return;
                        }
                        }
                    }
                }
                activated = false;
                editGran.setCanRespondToHover(true);
                proc.setNonEditable();
            }
            if(!activated && calledFromOutside){
                editIcon.setBackgroundPainted(false);
                editIcon.repaint();
            }
            
            AppState.fireEditModeStatusChanged(activated, tc, proc);
        }
        
        private void initializeEditIcon(){
            
            editGran = new GranularMouseAdapter(){
                
                double prevIconSqrPad = 0;
                
                @Override
                public void actOnMouseEntry(MouseEvent e){
                    editIcon.setCurrentColor(WHITE_BG);
                    editIcon.setBackgroundPainted(true);
                    editIcon.repaint();
                }
                
                @Override
                public void actOnMouseExit(MouseEvent e){
                    editIcon.setBackgroundPainted(false);
                    editIcon.repaint();
                }
                
                @Override
                public void actOnMousePress(MouseEvent e){
                    editIcon.setCurrentColor(WHITE_BG);
                    prevIconSqrPad = editIcon.getIconSquarePadding();
                    editIcon.setIconSquarePadding(7.0);
                    editIcon.repaint();
                }
                
                @Override
                public void actOnMouseRelease(MouseEvent e){
                    
                    editIcon.setIconSquarePadding(prevIconSqrPad);
                    if(activated){
                        editIcon.setCurrentColor(WHITE_BG);
                    }
                    editIcon.repaint();
                    calledFromOutside = false;
                    toggleEditIcon();
                    calledFromOutside = true;
                }
            };
            
            editIcon.addMouseListener(editGran);
        }
    }
}
