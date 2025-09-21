/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend.v2.controller;

import backend.v2.search.SuggestionElement;
import backend.v2.term.AdvancedTerm;
import backend.v2.term.IllegalTermStateException;
import frontend.RoundedButton;
import frontend.v2.containers.SuggestionContainer;
import frontend.v2.state.AppState;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EventListener;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author BRIN
 */
public class SuggestionContainerController {
    
    
    private static final Color EXACT_MATCH_COLOR = Color.BLUE;
    private static final Color SELECTION_COLOR = Color.GREEN;
    
    private final FontMetrics fm;
    
    private Map<String, RoundedButton> btnMap;
    private Map<RoundedButton, SuggestionElement> btnElementMap;
    private Map<Integer, Integer> btnNumberPerRow;

    // NAVIGATION RELEVANT VARIABLES :::
    private RoundedButton currentlyHighlighted;
    private Color previousColor;
    
    private int MAXIMUM_YID;
    private int currentXID = 0;
    private int currentYID = 0;
    
    private int prevXID = 0;
    private int prevYID = 0;
    
    private InputMap inputMap;
    private ActionMap actionMap;
    

    private final KeyStroke upKey = KeyStroke.getKeyStroke(KeyEvent.VK_UP,0);
    private final KeyStroke downKey = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0);
    private final KeyStroke rightKey = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT,0);
    private final KeyStroke leftKey = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT,0);
    private final KeyStroke enterKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0);
    
    private final Action upAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(currentXID == 0){
                currentXID = 1;
            }
            currentYID--;
            if(currentYID < 1){
                currentYID = MAXIMUM_YID;
            }
            
            if(!exists()){
                currentXID = btnNumberPerRow.get(currentYID);
            }
            
            update();
        }
    };
    
    private Action downAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("DOWN");
            if(currentXID == 0){
                currentXID = 1;
            }
            currentYID++;
            if(currentYID > MAXIMUM_YID){
                currentYID = 1;
            }
            
            if(!exists()){
                currentXID = btnNumberPerRow.get(currentYID);
            }
            
            update();
        }
    };
    
    private Action rightAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentXID++;
            if(currentXID > btnNumberPerRow.get(currentYID)){
                currentXID = 1;
                currentYID++;
                if(currentYID > MAXIMUM_YID){
                    currentYID = 1;
                    
                }
            }
            
            
            update();
        }
    };
    
    private Action leftAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentXID--;
            if(currentXID < 1){
                
                currentYID--;
                if(currentYID < 1){
                    currentYID = MAXIMUM_YID;
                }
                currentXID = btnNumberPerRow.get(currentYID);
            }
            
            
            update();
        }
    };
    
    private Action enterAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("BUTTON ELEMENT MAP SIZE: " + btnElementMap.size());
            try {
                AdvancedTerm newTerm = AppState.getCurrentBook().getATDM().get(btnElementMap.get(currentlyHighlighted).suggestionString());
                if(newTerm == null){
                    AppState.changeState(AppState.Type.CREATE);
                }
                AppState.changeActiveSearchedTerm(newTerm);
            } catch (IllegalTermStateException ex) {
                System.getLogger(SuggestionContainerController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                System.out.println("AN ERROR OCCURED WHEN TRYING TO FIRE ADVANCED TERM CHANGE EVENT IN SuggestionContainerController.");
            }
            sc.setVisible(false);
        }
    };

    // ---------------------------------
            
    private final SuggestionContainer sc;
    
    public SuggestionContainerController(SuggestionContainer sc) {
        this.sc = sc;
        inputMap = sc.getInputMap(JComponent.WHEN_FOCUSED);
        actionMap = sc.getActionMap();
        RoundedButton dummyButton = new RoundedButton();
        fm = dummyButton.getFontMetrics(dummyButton.getFont());
        
        addEventRunnable(() -> {
            currentXID = 1;
            currentYID = 1;
            update();
            sc.requestFocusInWindow();
            activateKeyboardNavigation();
        });
    }
    
    public void provideMap(Map<String,RoundedButton> btnMap, Map<RoundedButton, SuggestionElement> btnElementMap, Map<Integer, Integer> btnNumberPerRow){
        this.btnMap = btnMap;
        this.btnElementMap = btnElementMap;
        this.btnNumberPerRow = btnNumberPerRow;
        
        int max = 0;
        Iterator<Integer> iterator = btnNumberPerRow.keySet().iterator();
        while(iterator.hasNext()){
            max = Math.max(max, iterator.next());
        }
        
        MAXIMUM_YID = max;
    }
    
    public void activateKeyboardNavigation(){
        
        inputMap.put(downKey, "downKey");
        actionMap.put("downKey", downAction);
        inputMap.put(upKey, "upKey");
        actionMap.put("upKey", upAction);
        inputMap.put(rightKey, "rightKey");
        actionMap.put("rightKey", rightAction);
        inputMap.put(leftKey, "leftKey");
        actionMap.put("leftKey", leftAction);
        inputMap.put(enterKey, "enterKey");
        actionMap.put("enterKey", enterAction);
    }
    
    private boolean exists(){
        RoundedButton toActivate = btnMap.get(sc.getKey(currentXID, currentYID));
        if(toActivate == null){
            System.out.println("NULL FOR SOME REASON: -> exists() method in SuggestionContainerController returned false. "
                    + "This is expected if the previously highlighted suggestion button was at end of row and next row has lesser buttons overall.");
            return false;
        }
        return true;
    }
    
    private void update(){
        
        RoundedButton toActivate = btnMap.get(sc.getKey(currentXID, currentYID));
        System.out.println(currentYID + "<--- YID & " + currentXID + "<--- XID");
        if(currentlyHighlighted != null){
            currentlyHighlighted.setCurrentColor(previousColor);
        }
        
        previousColor = toActivate.getCurrentColor();
        toActivate.setCurrentColor(SELECTION_COLOR);
        currentlyHighlighted = toActivate;
        sc.repaint();
    }
    
    private void snapshot(){
        prevXID = currentXID;
        prevYID = currentYID;
    }
    
    public static void requestKeyboardNavigation(){
        
        listener.get(0).keyboardNavigationActivated(null);
    }
    
    public static void addEventRunnable(Runnable runnable){
        listener.clear();
        listener.add(new SuggestionKeyboardNavigationActivationListener() {
            @Override
            public void keyboardNavigationActivated(SuggestionKeyboardNavigationActivationEvent e) {
                runnable.run();
            }
        });
    }
    
    public static List<SuggestionKeyboardNavigationActivationListener> getListener(){
        
        return  Collections.unmodifiableList(listener);
    }
    
    private static final List<SuggestionKeyboardNavigationActivationListener> listener = new ArrayList<>();
    
    private class SuggestionKeyboardNavigationActivationEvent extends EventObject{
        
        public SuggestionKeyboardNavigationActivationEvent(Object source) {
            super(source);
        }
        
    }
    
    @FunctionalInterface
    private interface SuggestionKeyboardNavigationActivationListener extends EventListener {
        
        void keyboardNavigationActivated(SuggestionKeyboardNavigationActivationEvent e);
    }
}
