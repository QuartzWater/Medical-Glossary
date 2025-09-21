/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend.v2.controller;

import backend.eventadapter.DocumentEventAdapter;
import backend.eventadapter.GranularMouseAdapter;
import book.bookpicker.Book;
import frontend.RoundedButton;
import frontend.v2.containers.SpellingContainer;
import frontend.v2.containers.TermInfoContainer;
import frontend.v2.customevent.FilterRejectionEvent;
import frontend.v2.customevent.FilterRejectionListener;
import frontend.v2.filter.SpellingFilter;
import frontend.v2.specialcomponent.JLabelProgress;
import frontend.v2.specialcomponent.SpecialJProgressBar;
import frontend.v2.state.ActiveSearchedTermChangeEvent;
import frontend.v2.state.ActiveSearchedTermChangeListener;
import frontend.v2.state.AppState;
import frontend.v2.state.AppStateChangeEvent;
import frontend.v2.state.AppStateChangeListener;
import frontend.v2.window.BookPicker;
import frontend.v2.window.ContentEditWarning;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.text.PlainDocument;

/**
 *
 * @author BRIN
 */
public class SpellingContainerController {

    private final JTextField spellingBox;
    private final JLabel msgLabel;
    private final SpecialJProgressBar msgProgressBar;
    
    private final RoundedButton changeBook;
    
    private boolean TICEditModeActive = false;
    private TermInfoContainer TICReference = null;
    
    private JLabelProgress anim;
    
    private SpellingContainerController(SpellingContainer parent) {
        System.out.println("CONTROLLER CALLED");
        
        
        spellingBox = parent.getSpellingBox();
        msgLabel = parent.getMsgLabel();
        msgProgressBar = parent.getMsgProgressBar();
        changeBook = parent.getChangeBookButton();
        
        anim = new JLabelProgress((JPanel) msgLabel.getParent(), msgLabel, msgProgressBar);
        
        spellingBox.addFocusListener(spellingFocusAdapt);
        spellingBox.addMouseListener(spellingGran);
        
        changeBook.addMouseListener(changeBookGran);
        
        List<String> exceptions = new ArrayList<>();
        exceptions.add(SpellingContainer.DEFAULT_TEXT);
        
        SpellingFilter sf = new SpellingFilter("!@#$%^&*(){}[],.<>/?:;\"\\|'", 45, exceptions);
        sf.addListener(filterListen);
        
        PlainDocument doc = (PlainDocument) spellingBox.getDocument();
        doc.setDocumentFilter(sf);
        doc.addDocumentListener(docListen);
        
        InputMap inputMap = spellingBox.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap actionMap = spellingBox.getActionMap();   
        KeyStroke downStroke = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN,0);
        inputMap.put(downStroke, "downKey");
        
        Action downAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SuggestionContainerController.requestKeyboardNavigation();
            }
        };
        actionMap.put("downKey", downAction);
        
        AppState.addActiveSearchedTermChangeListener(new ActiveSearchedTermChangeListener() {
            @Override
            public void activeSearchedTermChanged(ActiveSearchedTermChangeEvent e) {
                if(e.getNewAdvTerm() != null){
                    setTextQuietly(e.getNewAdvTerm().getAbsoluteSpelling());
                }
            }
        });
        
        AppState.addListener(new AppStateChangeListener() {
            @Override
            public void appStateChanged(AppStateChangeEvent e) {
                if(e.getNewType() == AppState.Type.SEARCH){
                    anim.startAnimation("SEARCH MODE", JLabelProgress.MSG_TYPE.DEFAULT);
                }else if(e.getNewType() == AppState.Type.CREATE){
                    anim.startAnimation("CREATE MODE", JLabelProgress.MSG_TYPE.DEFAULT);
                }
            }
        });
        
        AppState.addTICEventListener((e) -> {
           
            TICEditModeActive = e.isEditActive();
            TICReference = e.getReference();
        });
        
    }
    
    
    public static SpellingContainerController intialize(SpellingContainer parent){
        return new SpellingContainerController(parent);
    }
    
    private GranularMouseAdapter changeBookGran = new GranularMouseAdapter(){
        
        Color prevBG;
        
        @Override
        public void actOnMouseEntry(MouseEvent e){
            prevBG = changeBook.getCurrentColor();
            changeBook.setCurrentColor(AppState.getCurrentBook().getColorScheme().getHoverColor());
            changeBook.getParent().getParent().repaint(); // without this theres subtle yellow ring on the button.
            // is this because SpellingContainer paint does not span its entire width and only the "initially painted" part is properly repainted...?
        }
        
        @Override
        public void actOnMouseExit(MouseEvent e){
            changeBook.setCurrentColor(prevBG);
            changeBook.getParent().getParent().repaint();
        }
        
        @Override
        public void actOnMousePress(MouseEvent e){
            changeBook.setCurrentColor(AppState.getCurrentBook().getColorScheme().getPressedColor());
            changeBook.getParent().getParent().repaint();
        }
        
        @Override
        public void actOnMouseRelease(MouseEvent e){
            changeBook.setCurrentColor(AppState.getCurrentBook().getColorScheme().getHoverColor());
            changeBook.getParent().getParent().repaint();
            promptForBookChange();
        }
    };
    
    private void promptForBookChange(){
        
        Book choosenBook = BookPicker.showBookPicker();
        if(choosenBook != null && choosenBook != AppState.getCurrentBook()){
            AppState.changeBook(choosenBook);
            act();
            anim.startAnimation("BOOK CHANGE", choosenBook.getColorScheme().getHoverColor(), choosenBook.getColorScheme().getPressedColor());
        }
        
        spellingBox.requestFocusInWindow();
    }
    
    private final FocusAdapter spellingFocusAdapt = new FocusAdapter() {
        
        @Override
        public void focusGained(FocusEvent e){
            
            if(TICEditModeActive){
                TICReference.getController().toggleEditIcon();
                if(TICReference.getController().getUserResponse() == ContentEditWarning.Response.ESCAPE && e.getOppositeComponent() != null){
                    e.getOppositeComponent().requestFocusInWindow();
                    return;
                }else{
                    spellingBox.requestFocusInWindow();
                }
                
            }
            
            if(spellingBox.getText().equals(SpellingContainer.DEFAULT_TEXT)){
                setTextQuietly("");
            }
        }
        
        @Override
        public void focusLost(FocusEvent e){
            if(spellingBox.getText().equals("")){
                if(spellingBox.getParent().getParent().isVisible()){
                    setTextQuietly(SpellingContainer.DEFAULT_TEXT);
                }
            }
        }
    };
    
    private final GranularMouseAdapter spellingGran = new GranularMouseAdapter(){
        
        @Override
        public void actOnMouseEntry(MouseEvent e){
            
        }
        
        @Override
        public void actOnMouseExit(MouseEvent e){
            
        }
    };
    
    private final DocumentEventAdapter docListen = new DocumentEventAdapter() {
        @Override
        public void actOnInsert(DocumentEvent e) {
            
            act();
        }

        @Override
        public void actOnRemove(DocumentEvent e) {
            act();
        }

        @Override
        public void actOnChange(DocumentEvent e) {
            
            act();
        }
    };
    
    private void act(){
        
        String spelling = spellingBox.getText();
        
        if(spelling.equals(SpellingContainer.DEFAULT_TEXT)){
            return;
        }
        
        if(AppState.getCurrentBook().getATDM().contains(spelling)){
            System.out.println("SPELLING FOUND????");
            AppState.setTermFound();
        }else {
            System.out.println("SPELLING NOT FOUND????");
            AppState.setTermNotFound();
        }
        
        AppState.changeInputSpelling(spelling);
        
    }
    
    public static final Color INVALID_MSG = new Color(255,102,102);
    public static final Color GREEN_MSG = new Color(51,255,51);
    public static final Color DEFAULT_MSG = new Color(255,255,255);
    
    public enum MSG_TYPE{
        INVALID,
        GREEN,
        DEFAULT
    }
    
    public String getCurrentSpelling(){
        return spellingBox.getText();
    }
    
    public void disableSpellingBox(){
        this.spellingBox.setEditable(false);
    }
    
    public void enableSpellingBox(){
        this.spellingBox.setEditable(true);
    }
    
    public void displayMsg(String msg, MSG_TYPE type){
        
        switch (type) {
            case INVALID -> {
                msgLabel.setForeground(INVALID_MSG);
            }
                
            case GREEN -> {
                msgLabel.setForeground(GREEN_MSG);
            }
            
            case DEFAULT -> {
                msgLabel.setForeground(DEFAULT_MSG);
            }
        }
        
        msgLabel.setText(msg);
    }
    
    public void clearMsg(){
        msgLabel.setText("");
    }
    
    private final FilterRejectionListener filterListen = new FilterRejectionListener() {
        @Override
        public void rejected(FilterRejectionEvent e) {
            anim.startAnimation(e.getReasonString().replace("_", " "), JLabelProgress.MSG_TYPE.INVALID);
        }
    };
    
    public void setTextQuietly(String toSet){
        docListen.switchOFF();
        spellingBox.setText(toSet);
        docListen.switchON();
    }
}
