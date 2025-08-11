/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import backend.ColorScheme;
import backend.State;
import backend.Term;
import backend.TermDataManagement;
import book.bookpicker.Book;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.swing.Action;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 * @author BRIN
 */
public class hyperlinkEditFrameGUIBehaviour {
    
    private Book initialisedBook;
    private TermDataManagement tdm;
    private Term exisitingTerm;
    private Term newTerm;
    private boolean termFound;
    private hyperlinkEditFrame hef;
    private referenceEditFrame ref;
    
    private JTextField[] encapFields;
    private JTextField[] hyperlinkFields;
    private RoundedButton[] adjacentButtons;
    
    private RoundedButton saveButton;
    private RoundedButton backButton;
    private RoundedButton save_skipButton;
    
    private Map<JTextField, State> encapFieldStateMap;
    private Map<JTextField, State> hyperlinkFieldStateMap;
    private Map<RoundedButton, State> adjacentButtonStateMap;
    
    private Map<JTextField, RoundedButton> hyperlinkAdjacentButtonMap;
    private Map<RoundedButton, JTextField> inverseHyperlinkAdjacentButtonMap;
    private Map<JTextField, JTextField> encapHyperlinkFieldsMap;
    
    
    private final String defaultEncapPlaceholder = "<Enter Encapsulating Text>";
    private final String defaultHyperlinkPlaceholder = "<Links to...>";
    
    public hyperlinkEditFrameGUIBehaviour(hyperlinkEditFrame hef){
        this.initialisedBook = hef.getInitialisedBook();
        this.tdm = hef.getTDM();
        this.exisitingTerm = hef.getExistingTerm();
        this.newTerm = hef.getNewTerm();
        this.termFound = hef.getTermFound();
        this.ref = hef.getREFParent();
        this.hef = hef;
        
        this.saveButton = hef.getSaveButton();
        this.backButton = hef.getBackButton();
        this.save_skipButton = hef.getSaveAndSkipButton();
        
        this.encapFields = hef.getEncapBoxes();
        this.hyperlinkFields = hef.getHyperlinkBoxes();
        this.adjacentButtons = hef.getAdjacentButtons();
        
        this.encapFieldStateMap = new LinkedHashMap<>();
        this.hyperlinkFieldStateMap = new LinkedHashMap<>();
        this.adjacentButtonStateMap = new LinkedHashMap<>();
        this.hyperlinkAdjacentButtonMap = new LinkedHashMap<>();
        this.encapHyperlinkFieldsMap = new LinkedHashMap<>();
        this.inverseHyperlinkAdjacentButtonMap = new LinkedHashMap<>();
        
        
        for(JTextField jtf : encapFields){
            encapFieldStateMap.put(jtf, new State(jtf));
            encapHyperlinkFieldsMap.put(jtf, hyperlinkFields[getIndexFromObjectArray_MemCompare(encapFields, jtf)]);
        }
        
        for(JTextField jtf : hyperlinkFields){
            hyperlinkFieldStateMap.put(jtf, new State(jtf));
            hyperlinkAdjacentButtonMap.put(jtf, adjacentButtons[getIndexFromObjectArray_MemCompare(hyperlinkFields, jtf)]);
            
        }
        
        for(RoundedButton rdb : adjacentButtons){
            adjacentButtonStateMap.put(rdb, new State(rdb));
            inverseHyperlinkAdjacentButtonMap.put(rdb, hyperlinkFields[getIndexFromObjectArray_MemCompare(adjacentButtons, rdb)]);
            rdb.setDisabledColor(RoundedButton.ULTIMATE_DEFAULT);
            rdb.setEnabled(false);
        }
        
        
        addEncapFieldsBehaviour();
        addHyperlinkFieldsBehaviour();
        addAdjacentButtonBehaviour();
        addSaveButtonBehaviour();
        addBackButtonBehaviour();
    }
    
    private int getIndexFromObjectArray_MemCompare(Object[] targetArray, Object target){
        
        for(int i = 0; i < targetArray.length; i++){
            
            if(targetArray[i] == target){
             return i;
            }
        }
        
        throw new IllegalArgumentException(target.toString() + " not found in array: " + Arrays.toString(targetArray));
    }
    
    private int getIndexFromObjectArray_ContentCompare(Object[] targetArray, Object target){
        
        for(int i = 0; i < targetArray.length; i++){
            
            if(targetArray[i].equals(target)){
             return i;
            }
        }
        
        throw new IllegalArgumentException(target.toString() + " not found in array: " + Arrays.toString(targetArray));
    }
    
    private void updateSaveButton(){
     
        boolean allEncapFieldsValid = true;
        
        for(JTextField jtf : encapFields){
            
            State checkState = encapFieldStateMap.get(jtf);
            if(!checkState.isValid()){
                allEncapFieldsValid = false;
                break;
            }
            
            
        }
        
        boolean allHyperlinkFieldsValid = true;
        
        for(JTextField jtf : hyperlinkFields){
            
            State checkState = hyperlinkFieldStateMap.get(jtf);
            if(!checkState.isValid()){
               allHyperlinkFieldsValid = false;
               break;
            }
            
        }
        
        boolean defaultDiscrepancy = false;
        
        for(JTextField encapField : encapFields){
            
            JTextField hyperlinkField = encapHyperlinkFieldsMap.get(encapField);
            State encapState = encapFieldStateMap.get(encapField);
            State hyperlinkState = hyperlinkFieldStateMap.get(hyperlinkField);
            
            if(encapState.isDefault() != hyperlinkState.isDefault()){
                defaultDiscrepancy = true;
                break;
            }
        }
        
        if(allEncapFieldsValid && allHyperlinkFieldsValid && !defaultDiscrepancy){
            saveButton.setEnabled(true);
        }
        else{
            saveButton.setEnabled(false);
        }
    }
    
    private ColorScheme adjacentButtonColorScheme = new ColorScheme(
            new Color(76, 175, 80),
            new Color(69, 160, 73),
            new Color(62, 142, 65),
            new Color(255,51,51)
    
    );
    
    private void updateAdjacentButton(RoundedButton rdb){
        
        State checkState = adjacentButtonStateMap.get(rdb);
        Objects.requireNonNull(checkState, "rdb Button provided not found in Map Uuexpectedly");
        
        switch(checkState.getState()){
            
            case State.ComponentState.DEFAULT_AND_VALID : {
                rdb.setDisabledColor(RoundedButton.ULTIMATE_DEFAULT);
                rdb.setEnabled(false);
                break;
            } //TODO SET PROPER COLOR
            case State.ComponentState.NON_DEFAULT_AND_VALID : {
                rdb.setCurrentColor(adjacentButtonColorScheme.getDefaultColor());
                rdb.setEnabled(true);
                break;
            }
            case State.ComponentState.NON_DEFAULT_AND_INVALID : {
                rdb.setDisabledColor(adjacentButtonColorScheme.getDisabledColor());
                rdb.setEnabled(false);
                break;
            }
            
            default: throw new IllegalArgumentException("unexpected state found");
        }
        
        rdb.callRepaint();
        
    }
    
    
    private ColorScheme textFieldColorScheme = new ColorScheme(
            new Color(153,153,153), new Color(204,204,204), new Color(255,255,255), new Color(153,153,153)
    );
    
    private void addEncapFieldsBehaviour(){
     
        int i = 0;
        for(JTextField encap : encapFields){
            i++;
            State state = encapFieldStateMap.get(encap);
            
            MouseAdapter encapMAdapt = new MouseAdapter() {
              
                @Override
                public void mouseEntered(MouseEvent e){
                    if(!state.hasFocus() && state.isDefault()){
                        encap.setForeground(textFieldColorScheme.getHoverColor());
                    }
                }
                
                @Override
                public void mouseExited(MouseEvent e){
                    
                    if(!state.hasFocus() && state.isDefault()){
                        encap.setForeground(textFieldColorScheme.getDefaultColor());
                    }
                }
            };
            
            FocusAdapter encapFAdapt = new FocusAdapter() {
                
                @Override
                public void focusGained(FocusEvent e){
                    if(encap.getText().equals(defaultEncapPlaceholder)){
                        encap.setText("");
                        encap.setForeground(textFieldColorScheme.getPressedColor());
                    }
                    
                }
                
                @Override
                public void focusLost(FocusEvent e){
                    if(encap.getText().trim().equals("")){
                        encap.setText(defaultEncapPlaceholder);
                        state.setState(State.ComponentState.DEFAULT_AND_VALID);
                        encap.setForeground(textFieldColorScheme.getDefaultColor());
                    }
                }
            };
            
            DocumentListener encapDocListen = new DocumentListener() {
                
                private boolean validate(String checkString){
                    
                    return true;
                }
                
                private void updateBasedOnText(){
                    String checkString = encap.getText();
                    
                    if(checkString.equals(defaultEncapPlaceholder)){
                        state.setState(State.ComponentState.DEFAULT_AND_VALID);
                    }
                    else if(checkString.trim().equals("")){
                        
                        state.setState(State.ComponentState.NON_DEFAULT_AND_INVALID);
                    }
                    else {
                        state.setState(State.ComponentState.NON_DEFAULT_AND_VALID);
                    }
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    updateBasedOnText();
                    updateSaveButton();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    updateBasedOnText();
                    updateSaveButton();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    updateBasedOnText();
                    updateSaveButton();
                }
            };
        
            encap.addMouseListener(encapMAdapt);
            encap.addFocusListener(encapFAdapt);
            encap.getDocument().addDocumentListener(encapDocListen);
        }
        
        System.out.println(i);
    }
    
    
 
    private void addHyperlinkFieldsBehaviour(){
        
        for(JTextField hyperlink : hyperlinkFields){
            
            State state = hyperlinkFieldStateMap.get(hyperlink);
            RoundedButton rdb = hyperlinkAdjacentButtonMap.get(hyperlink);
            State rdbState = adjacentButtonStateMap.get(rdb);
            
            MouseAdapter hyperlinkMAdapt = new MouseAdapter() {
              
                @Override
                public void mouseEntered(MouseEvent e){
                    if(!state.hasFocus() && state.isDefault()){
                        hyperlink.setForeground(textFieldColorScheme.getHoverColor());
                    }
                }
                
                @Override
                public void mouseExited(MouseEvent e){
                    
                    if(!state.hasFocus() && state.isDefault()){
                        hyperlink.setForeground(textFieldColorScheme.getDefaultColor());
                    }
                }
            };
            
            FocusAdapter hyperlinkFAdapt = new FocusAdapter() {
                
                @Override
                public void focusGained(FocusEvent e){
                    if(hyperlink.getText().equals(defaultHyperlinkPlaceholder)){
                        hyperlink.setText("");
                        hyperlink.setForeground(textFieldColorScheme.getPressedColor());
                    }
                    
                }
                
                @Override
                public void focusLost(FocusEvent e){
                    if(hyperlink.getText().trim().equals("")){
                        hyperlink.setText(defaultHyperlinkPlaceholder);
                        state.setState(State.ComponentState.DEFAULT_AND_VALID);
                        hyperlink.setForeground(textFieldColorScheme.getDefaultColor());
                    }
                }
            };
            
            DocumentListener hyperlinkDocListen = new DocumentListener() {
                
                private boolean validate(String checkString){
                    
                    return true;
                }
                
                private void updateBasedOnText(){
                    String checkString = hyperlink.getText();
                    
                    if(checkString.equals(defaultHyperlinkPlaceholder)){
                        state.setState(State.ComponentState.DEFAULT_AND_VALID);
                        
                    }
                    else if(checkString.trim().equals("")){
                        
                        state.setState(State.ComponentState.NON_DEFAULT_AND_INVALID);
                    }
                    else if(!checkString.startsWith("https://") && !checkString.startsWith("http://")){
                     
                        state.setState(State.ComponentState.NON_DEFAULT_AND_INVALID);
                    }
                    else if(checkString.equals("https://") || checkString.equals("http://")){
                        
                        state.setState(State.ComponentState.NON_DEFAULT_AND_INVALID);
                    }
                    else {
                        state.setState(State.ComponentState.NON_DEFAULT_AND_VALID);
                    }
                    rdbState.setState(state.getState());
                }
                
                @Override
                public void insertUpdate(DocumentEvent e) {
                    updateBasedOnText();
                    updateSaveButton();
                    updateAdjacentButton(rdb);
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    updateBasedOnText();
                    updateSaveButton();
                    updateAdjacentButton(rdb);
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    updateBasedOnText();
                    updateSaveButton();
                    updateAdjacentButton(rdb);
                }
            };
            
            hyperlink.addMouseListener(hyperlinkMAdapt);
            hyperlink.addFocusListener(hyperlinkFAdapt);
            hyperlink.getDocument().addDocumentListener(hyperlinkDocListen);
        }
    }
    
    private void browse(RoundedButton rdb){
        JTextField hyperlinkField = inverseHyperlinkAdjacentButtonMap.get(rdb);
        String url = hyperlinkField.getText();
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException ex) {
                // Handle exceptions (e.g., show an error dialog)
                ex.printStackTrace();
            }
        } else {
                // Handle case where Desktop is not supported
            System.out.println("Desktop is not supported, cannot open URL.");
        }
    }
    
    
    
    private void addAdjacentButtonBehaviour(){
        
        for(RoundedButton rdb : adjacentButtons){
        
            MouseAdapter adjacentMAdapt = new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent event){

                    if(rdb.isEnabled())
                    rdb.setCurrentColor(adjacentButtonColorScheme.getHoverColor());
                }

                @Override
                public void mouseExited(MouseEvent event){

                    if(rdb.isEnabled())
                    rdb.setCurrentColor(adjacentButtonColorScheme.getDefaultColor());
                }
                
                @Override
                public void mousePressed(MouseEvent event){
                    
                    if(rdb.isEnabled())
                    rdb.setCurrentColor(adjacentButtonColorScheme.getPressedColor());
                }
                
                @Override
                public void mouseReleased(MouseEvent event){
                    
                    if(rdb.isEnabled())
                    rdb.setCurrentColor(adjacentButtonColorScheme.getHoverColor());
                }
            };

            ActionListener adjacentActListen = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    System.out.println("CLICKED!");
                    browse(rdb);
                }
            };
            
            rdb.addMouseListener(adjacentMAdapt);
            rdb.addActionListener(adjacentActListen);
        
        }
    }
    
    private void addSaveButtonBehaviour(){
        
        MouseAdapter saveMAdapt = new MouseAdapter() {
            
            @Override
                public void mouseEntered(MouseEvent event){

                    if(event.getSource() == saveButton)
                    {
                        if(saveButton.isEnabled())
                        saveButton.setCurrentColor(adjacentButtonColorScheme.getDefaultColor());
                    }
                    else if(event.getSource() == save_skipButton){
                        save_skipButton.setCurrentColor(adjacentButtonColorScheme.getDefaultColor());
                    }
                }

                @Override
                public void mouseExited(MouseEvent event){

                    if(event.getSource() == saveButton)
                    {
                        if(saveButton.isEnabled())
                        saveButton.setCurrentColor(RoundedButton.ULTIMATE_DEFAULT);
                    }
                    else if(event.getSource() == save_skipButton){
                        save_skipButton.setCurrentColor(RoundedButton.ULTIMATE_DEFAULT);
                    }
                }
                
                @Override
                public void mousePressed(MouseEvent event){
                    
                    if(event.getSource() == saveButton)
                    {
                        if(saveButton.isEnabled())
                        saveButton.setCurrentColor(adjacentButtonColorScheme.getHoverColor());
                    }
                    else if(event.getSource() == save_skipButton){
                        save_skipButton.setCurrentColor(adjacentButtonColorScheme.getHoverColor());
                    }
                }
                
                @Override
                public void mouseReleased(MouseEvent event){
                    
                    if(event.getSource() == saveButton)
                    {
                        if(saveButton.isEnabled())
                        saveButton.setCurrentColor(adjacentButtonColorScheme.getDefaultColor());
                    }
                    else if(event.getSource() == save_skipButton){
                        save_skipButton.setCurrentColor(adjacentButtonColorScheme.getDefaultColor());
                    }
                }
                
        };
     
        ActionListener saveActListen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(e.getSource() == saveButton){
                    saveAction(saveType.SIMPLE_SAVE);
                    System.out.println("SAVE");
                }
                else if(e.getSource() == save_skipButton){
                    saveAction(saveType.SKIP_AND_SAVE);
                    System.out.println("SAVE-SKIP");
                }
                
            }
        };
        
        saveButton.addActionListener(saveActListen);
        saveButton.addMouseListener(saveMAdapt);
        save_skipButton.addMouseListener(saveMAdapt);
        save_skipButton.addActionListener(saveActListen);
    }
    
    private enum saveType{
        
        SIMPLE_SAVE,
        SKIP_AND_SAVE
    }
    
    private void saveAction(saveType stype){
        
        List<String> hyperlinkEncapsulation = new ArrayList<>();
        List<String> hyperlinkURL = new ArrayList<>();
        
        
        
        if(stype == saveType.SIMPLE_SAVE){
            for(JTextField encap : encapFields){
            
            JTextField hyperlink = encapHyperlinkFieldsMap.get(encap);
            State encapState = encapFieldStateMap.get(encap);
            State hyperlinkState = hyperlinkFieldStateMap.get(hyperlink);
            
                if(!encapState.isDefault() && !hyperlinkState.isDefault()){

                    hyperlinkEncapsulation.add(encap.getText());
                    hyperlinkURL.add(hyperlink.getText());
                }
            }

            newTerm.setHyperlinks(hyperlinkURL);
            newTerm.setHyperlinksEncapsulation(hyperlinkEncapsulation);
            System.out.println(newTerm.toString());
            if(termFound)
            tdm.flush(exisitingTerm, newTerm);
            else
                tdm.flush(null, newTerm);
        }
        else if(stype == saveType.SKIP_AND_SAVE){
            if(termFound)
            tdm.flush(exisitingTerm, newTerm);
            else
                tdm.flush(null, newTerm);
        }
    }
    
    private ColorScheme backButtonColorScheme = new ColorScheme(new Color(27,36,45), new Color(255,51,51), new Color(221,51,51), new Color(27,36,45));
    
    private void addBackButtonBehaviour(){
        //TODO - Incomplete
        MouseAdapter backMAdapt = new MouseAdapter() {
            
            @Override
            public void mouseEntered(MouseEvent e){
                
                backButton.setCurrentColor(backButtonColorScheme.getHoverColor());
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                backButton.setCurrentColor(backButtonColorScheme.getDefaultColor());
            }
            
            @Override
            public void mousePressed(MouseEvent e){
                backButton.setCurrentColor(backButtonColorScheme.getPressedColor());
            }
            
            @Override
            public void mouseReleased(MouseEvent e){
                backButton.setCurrentColor(backButtonColorScheme.getHoverColor());
            }
            
            
                
        };
        
        ActionListener backActListen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hef.dispose();
                ref.setVisible(true);
            }
        };
        
        backButton.addMouseListener(backMAdapt);
        backButton.addActionListener(backActListen);
    }
}
