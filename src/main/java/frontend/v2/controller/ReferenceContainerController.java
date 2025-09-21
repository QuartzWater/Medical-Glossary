/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend.v2.controller;

import backend.eventadapter.DocumentEventAdapter;
import backend.eventadapter.GranularMouseAdapter;
import book.bookpicker.Book;
import frontend.v2.containers.ReferenceContainer;
import frontend.v2.filter.NumericFilter;
import frontend.v2.state.ActiveSearchedTermChangeEvent;
import frontend.v2.state.ActiveSearchedTermChangeListener;
import frontend.v2.state.AppState;
import frontend.v2.state.AppStateChangeEvent;
import frontend.v2.state.AppStateChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.text.PlainDocument;

/**
 *
 * @author BRIN
 */
public class ReferenceContainerController {
    
    private ReferenceContainer rc;
    
    private JTextField pageField;
    private JLabel pageStatus;
    private JLabel multiEverythingLabel;
    private JTextArea superHeadingArea;
    private JTextArea middleHeadingArea;
    private JTextArea subHeadingArea;
    
    private InputMap inputMap;
    private ActionMap actionMap;

    public ReferenceContainerController(ReferenceContainer rc) {
        
        this.rc = rc;
        
        this.pageField = rc.getPageTextBox();
        this.pageStatus = rc.getPageStatus();
        this.multiEverythingLabel = rc.getMultiEverythingIndicator();
        this.superHeadingArea = rc.getSuperHeadingArea();
        this.middleHeadingArea = rc.getMiddleHeadingArea();
        this.subHeadingArea = rc.getSubHeadingArea();
        
        PlainDocument doc = (PlainDocument) pageField.getDocument();
        doc.addDocumentListener(docListen);
        doc.setDocumentFilter(new NumericFilter(4));
        
        pageField.addMouseListener(mouseHoverOnDisabled);
        
        inputMap = pageField.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        actionMap = pageField.getActionMap();
        
        
    }
    
    
    private DocumentEventAdapter docListen = new DocumentEventAdapter(){

        @Override
        public void actOnInsert(DocumentEvent e){
            setEverythingByPage();
        }

        @Override
        public void actOnChange(DocumentEvent e){
            setEverythingByPage();
        }

        @Override
        public void actOnRemove(DocumentEvent e){
            setEverythingByPage();
        }
    };
    
    private GranularMouseAdapter mouseHoverOnDisabled = new GranularMouseAdapter(){
        
        @Override
        public void actOnMouseEntry(MouseEvent e){
            if(!pageField.isEditable()){
                pageStatus.setText("SELECT EDIT ICON TO EDIT");
            }
        }
        
        @Override
        public void actOnMouseExit(MouseEvent e){
            if(!pageField.isEditable()){
                pageStatus.setText("");
            }
        }
    };
    
    private int currentIndex = 0;
    private int size = 0;
    Map<Integer, String[]> multiEverything = new HashMap<>();
    
    private KeyStroke upKey = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
    private KeyStroke downKey = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
    
    private Action upAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentIndex--;
            if(currentIndex < 0){
                currentIndex = size - 1;
            }
            
            setTextAreas(currentIndex);
        }
    };
    
    
    private Action downAction = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentIndex++;
            if(currentIndex == size){
                currentIndex = 0;
            }
            
            setTextAreas(currentIndex);
        }
    };
    
    int testInt = 0;
    
    private void setEverythingByPage(){
        
        currentIndex = 0;
        
        String page = rc.getPageTextBox().getText();
        if(!page.isBlank()){
            multiEverything = AppState.getCurrentBook().getEverythingByPage(Integer.parseInt(page));
        }else {
            runActions();
            multiEverything = new HashMap<>();
        }
        
        size = multiEverything.size();
        
        if(size == 0){
            
            runActions();
            multiEverythingLabel.setVisible(false);
            inputMap.remove(upKey);
            inputMap.remove(downKey);
            actionMap.remove("upKey");
            actionMap.remove("downKey");
            
            if(!page.isBlank()){
                pageStatus.setText("NO CONFIGURATION FOUND");
            }else {
                pageStatus.setText("");
            }
            superHeadingArea.setText(" -");
            middleHeadingArea.setText(" -");
            subHeadingArea.setText(" -");
        }
        else {
            runActions2();
            if(size > 1){
                pageStatus.setText("MULTIPLE (" + Integer.toString(size)+ ") CONFIGURATIONS FOUND");
                multiEverythingLabel.setVisible(true);
                multiEverythingLabel.setText(Integer.toString(currentIndex + 1) +"/" + Integer.toString(size));
                inputMap.put(upKey, "upKey");
                inputMap.put(downKey, "downKey");
                actionMap.put("upKey", upAction);
                actionMap.put("downKey", downAction);
                
            }else {
                pageStatus.setText("SINGLE CONFIGURATION FOUND");
                multiEverythingLabel.setVisible(false);
                inputMap.remove(upKey);
                inputMap.remove(downKey);
                actionMap.remove("upKey");
                actionMap.remove("downKey");
                
            }
            
            
            setTextAreas(0);
        }
    }
    
    private void setTextAreas(int index){
        if(multiEverythingLabel.isVisible()){
            multiEverythingLabel.setText(Integer.toString(currentIndex + 1) +"/" + Integer.toString(size));
        }
        superHeadingArea.setText(multiEverything.get(index)[0]);
        middleHeadingArea.setText(multiEverything.get(index)[1]);
        subHeadingArea.setText(multiEverything.get(index)[2]);
    }
    
    public static void addActionsWhenInvalidPageFound(Runnable runnable){
        actionsWhenInvalidPage.add(runnable);
    }
    
    public static void addActionsWhenValidPageFound(Runnable runnable){
        actionsWhenValidPage.add(runnable);
    }
    private static List<Runnable> actionsWhenInvalidPage = new ArrayList();
    private static List<Runnable> actionsWhenValidPage = new ArrayList();

    
    private static void runActions(){
        for(Runnable runnable : actionsWhenInvalidPage){
            runnable.run();
        }
    }
    
    private static void runActions2(){
        for(Runnable runnable : actionsWhenValidPage){
            runnable.run();
        }
    }
}
