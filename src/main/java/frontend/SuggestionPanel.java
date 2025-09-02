/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import backend.AppConfig;
import backend.AppConstants;
import backend.SearchTermAlgorithm;
import backend.Term;
import backend.TermDataManagement;
import backend.eventadapter.GranularMouseAdapter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BRIN
 */
public class SuggestionPanel{
    
    //private and local JComponent declaration
    
    private final int buttonHeight = 40;
    private final int buttonVerticalPadding = 10;
    private final int buttonHorizontalPadding = 20;
    private final int buttonListXOffset = 10;
    private final int buttonListYOffset = 10;
    private final int roundedButtonArcSize = 25;
    
    private final int visibleButtonsAtATime = 4;
    
    private JScrollPane jscrp;
    private RoundedPanel decorativePanel;
    private JPanel parentPanel;
    private JPanel actualContainer;
    private SearchTermAlgorithm sta;
    private JTextField spellJTX;
    
    public SuggestionPanel(JScrollPane jscrp, RoundedPanel initPanel, JPanel parentPanel){
        
       
        this.jscrp = jscrp;
        
        this.decorativePanel = initPanel;
        this.decorativePanel.setPreferredSize(new Dimension(680, 200));
        this.actualContainer = (JPanel) initPanel.getComponent(0);
        this.actualContainer.setPreferredSize(decorativePanel.getPreferredSize());
        this.parentPanel = parentPanel;
        this.decorativePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        this.sta = new SearchTermAlgorithm(AppConfig.getSearchDepth(), 10);
        
        // initialization
    }
    
    public void show(Term term, TermDataManagement tdm, JTextField jtx){
        
        if(spellJTX == null){
            spellJTX = jtx;
        }
        
        clear();
        List<String> suggestions = sta.get(term, tdm);
        List<RoundedButton> rdbSuggestionList = new ArrayList<>();
        
        int termCount = suggestions.size();
        
        int buttonWidth = jtx.getWidth() - buttonHorizontalPadding;
        
        int panelHeight = 0;
        
        for(int i = 0; i < termCount; i++){
            
            panelHeight += buttonHeight + buttonVerticalPadding;
        }
        panelHeight += buttonVerticalPadding;
        
        Dimension dim = new Dimension(jtx.getWidth(), panelHeight);
        decorativePanel.setPreferredSize(dim);
        decorativePanel.setSize(dim);
        actualContainer.setSize(dim);
        
        if(termCount > visibleButtonsAtATime){
            buttonWidth -= 15;
        }
        
        if(termCount > 0){
            
            int yPosition = buttonListYOffset;
            
            for(int i = 1; i <= termCount; i++){
                RoundedButton rdb = new RoundedButton(suggestions.get(i - 1));
                rdb.setArcSize(roundedButtonArcSize);
                invertColor(rdb);
                addSuggestionButtonsBehaviour(rdb, jtx);
                rdbSuggestionList.add(rdb);
                actualContainer.add(rdb, new org.netbeans.lib.awtextra.AbsoluteConstraints(buttonListXOffset, yPosition, buttonWidth, buttonHeight));
                yPosition += buttonHeight + buttonVerticalPadding;
            }
            

            jscrp.getVerticalScrollBar().setValue(0);
            jscrp.setVisible(true);
        }
        
        jscrp.getVerticalScrollBar().setMaximum(panelHeight);
        
        addAdditionalTextBoxBehaviour(jtx, rdbSuggestionList);
    }
    
    public void hide(){
        jscrp.setVisible(false);
        currentButtonIndex = 0;
        firstVisibleIndex = 0;
        currentlyHighlightedTerm ="";
        forFirstTimeActivated = true;
        suggestionButtonNavigationActive = false;
        if(spellJTX != null){
            
            spellJTX.resetKeyboardActions(); // It does not clear ActionPerformed function of the spelling text box (jtx) since its first tier and this function only clears second tier.
            if(actList != null){
                spellJTX.removeActionListener(actList);
            }
        }
    }
    
    public void callRepaintOnParent(){
        parentPanel.repaint();
    }
    
    private void clear(){
        for(Component comp : actualContainer.getComponents()){
            
            actualContainer.remove(comp);
        }
    }
    
    private void addSuggestionButtonsBehaviour(RoundedButton rdb, JTextField jtx){
        
        rdb.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                jtx.setText(rdb.getText());
                jtx.requestFocus();
                hide();
            }
            
        });
        
        GranularMouseAdapter rdbMAdapt = new GranularMouseAdapter() {
            
            @Override
            public void actOnMouseEntry(MouseEvent e){
                rdb.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getDefaultColor());
                rdb.setForeground(Color.white);
            }
            
            @Override
            public void actOnMouseExit(MouseEvent e){
                rdb.setCurrentColor(Color.white);
                rdb.setForeground(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getDefaultColor());
            }
            
            @Override
            public void actOnMousePress(MouseEvent e){
                rdb.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getHoverColor());
            }
            
            @Override
            public void actOnMouseRelease(MouseEvent e){
                rdb.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getDefaultColor());                
            }
        };
        
        MouseAdapter additionalMAdapt = new MouseAdapter() {
            
            @Override
            public void mouseReleased(MouseEvent e){
                
                jtx.requestFocus();
            }
        };
        
        rdb.addMouseListener(rdbMAdapt);
        rdb.addMouseListener(additionalMAdapt);
    }
    
    private void invertColor(RoundedButton rdb){
        
        Color bgColor = rdb.getCurrentColor();
        Color fgColor = rdb.getForeground();
        
        rdb.setForeground(bgColor);
        rdb.setCurrentColor(fgColor);
    }
    
    private String currentlyHighlightedTerm ="";
    private int currentButtonIndex = 0;
    private boolean forFirstTimeActivated = true;
    private boolean suggestionButtonNavigationActive = false;
    private ActionListener actList;
    
    private void addAdditionalTextBoxBehaviour(JTextField jtx, List<RoundedButton> rdbSuggestionList){
        
        if(rdbSuggestionList.isEmpty()){
            return;
        }
        
        KeyStroke upKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
        KeyStroke downKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        
        InputMap inputMap = jtx.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = jtx.getActionMap();
        
        String upActionName = "upAction";
        String downActionName = "downAction";
        String escapeActionName = "escapeAction";
        
        Action upAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeButtonInFocus(actionType.UP, rdbSuggestionList);
                forFirstTimeActivated = false;
                suggestionButtonNavigationActive = true;
            }
        };
        
        Action downAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeButtonInFocus(actionType.DOWN, rdbSuggestionList);
                forFirstTimeActivated = false;
                suggestionButtonNavigationActive = true;
            }
        };
        
        Action escapeAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
            }
        };
        
        actList = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(suggestionButtonNavigationActive){
                    jtx.setText(currentlyHighlightedTerm);
                }
            }
        };
        
        jtx.addActionListener(actList);
        
        inputMap.put(upKeyStroke, upActionName);
        actionMap.put(upActionName, upAction);
        
        inputMap.put(downKeyStroke, downActionName);
        actionMap.put(downActionName, downAction);
        
        inputMap.put(escapeKeyStroke, escapeActionName);
        actionMap.put(escapeActionName, escapeAction);
        
    }
    
    
    private enum actionType {
        UP, DOWN
    }
    
    int firstVisibleIndex = 0;
    
    private void changeButtonInFocus(actionType atype, List<RoundedButton> rdbList){
        int previousCurrentButtonIndex = currentButtonIndex;
        int changeValue = 0;
        
        switch (atype) {
            case UP -> {
                
                currentButtonIndex--;
                if(currentButtonIndex < 0){
                    currentButtonIndex = rdbList.size() - 1;
                    firstVisibleIndex = rdbList.size() - visibleButtonsAtATime;
                    changeValue = jscrp.getVerticalScrollBar().getMaximum();
                }
                else if(currentButtonIndex < firstVisibleIndex){
                    changeValue = -(buttonHeight + buttonVerticalPadding);
                    firstVisibleIndex--;
                    
                }
            }
            
            case DOWN -> {
                if(!forFirstTimeActivated){
                    currentButtonIndex++;
                }
                
                if(currentButtonIndex > rdbList.size() - 1){
                    currentButtonIndex = 0;
                    firstVisibleIndex = 0;
                    changeValue = -jscrp.getVerticalScrollBar().getValue();
                }
                else if(currentButtonIndex > firstVisibleIndex + (visibleButtonsAtATime - 1)){
                    firstVisibleIndex++;
                    changeValue = buttonHeight + buttonVerticalPadding;
                    
                }
            }
            
        }
        
        jscrp.getVerticalScrollBar().setValue(jscrp.getVerticalScrollBar().getValue() + changeValue);
        
        RoundedButton rdbNew = rdbList.get(currentButtonIndex);
        rdbNew.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getDefaultColor());
        rdbNew.setForeground(Color.white);
        GranularMouseAdapter granMAdaptNew = (GranularMouseAdapter) rdbNew.getMouseListeners()[1];
        granMAdaptNew.setCanRespondToHover(false);
        
        if(!forFirstTimeActivated && rdbList.size() != 1){
            RoundedButton rdbOld = rdbList.get(previousCurrentButtonIndex);
            GranularMouseAdapter granMAdaptOld = (GranularMouseAdapter) rdbOld.getMouseListeners()[1];
            
            if(!granMAdaptOld.isInside()){
                rdbOld.setCurrentColor(Color.white);
                rdbOld.setForeground(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getDefaultColor());
            }
            
            granMAdaptOld.setCanRespondToHover(true);
        }
        
        currentlyHighlightedTerm = rdbNew.getText();
    }
    
    
    public boolean isSuggestionNavigationActive(){
        
        return suggestionButtonNavigationActive;
    }
}
