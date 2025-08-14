/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import backend.ColorScheme;
import backend.PageContentState;
import backend.State;
import backend.Term;
import backend.TermDataManagement;
import book.bookpicker.Book;
import legacy.NumericAndFirstDigitFilter;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.Objects;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author BRIN
 */
public class referenceEditFrameGUIBehaviour {
    
    private Book initialisedBook;
    private TermDataManagement tdm;
    private Term existingTerm;
    private Term newTerm;
    private boolean termFound;
    
    private definitionEditFrame parentFrame;
    private referenceEditFrame ref;
    
    // Variables to be controlled :::::::::::::
    
    private JTextField pageBox;
    private JLabel bookField;
    private JLabel chapterField;
    private JLabel majorTopicField;
    private JTextArea subtopicField;
    private RoundedButton backButton;
    private RoundedButton nextButton;
    private JLabel statusLabel;
    private JLabel HeaderLabel;
    
    // Variable decalreation ends :::::::::::::
    
    private Map<Integer, String[]> contentMap;
    
    private PageContentState pageContentState;
    private State pageValidityState;
    private State nextButtonValidityState;
    
    private int CURRENT_INDEX = 0;
    private final String DEFAULT_PAGE_BOX_TEXT = "<Enter Page Number>";
    
    // -------------------------------------------------------------
    
    // TODO:
    //CRITICAL BUG: When typing number and then pressing backspace so that the field is empty throws a NUMBERFORMATEXCEPTION
    // However, if we type a number like : 3 and THEN press any non numberic key( it'll not accept it, correctly behaving)
    // and then press backspace so that field is empty DONT result in NUMBERFORMATEXCEPTION
    
    
    // ^^^^^^^^^^^^^^^^^^^^^^^ SOLVED ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    
    // LESSON LEARNT: NEVER update states of objects in a DocumentFilter ever again!!!
    
    private class ExtendedNumericAndFirstDigitFilter extends NumericAndFirstDigitFilter{
        
        public ExtendedNumericAndFirstDigitFilter(int maxSize){
            super(maxSize);
        }
        
        @Override
        protected boolean isValidInput(DocumentFilter.FilterBypass fb, int offset, int length, String text) throws BadLocationException{
            if (text == null || text.isBlank() || text.equals(DEFAULT_PAGE_BOX_TEXT)) {
                
                
                return true;
            }
            return super.isValidInput(fb, offset, length, text);
        }
    }
    
    
    public referenceEditFrameGUIBehaviour(referenceEditFrame ref){
        
        initialisedBook = ref.getInitialisedBook();
        tdm = ref.getTDM();
        existingTerm = ref.getExistingTerm();
        newTerm = ref.getNewTerm();
        termFound = ref.getTermFound();
        
        parentFrame = ref.getDEFParent();
        
        this.ref = ref;
        
        // Controllable JComponents reference initialisation:
        this.pageBox = ref.getPageBox();
        this.bookField = ref.getBookField();
        this.chapterField = ref.getChapterField();
        this.majorTopicField = ref.getMajorTopicField();
        this.subtopicField = ref.getSubtopicField();
        this.backButton = ref.getBackButton();
        this.nextButton = ref.getNextButton();
        this.statusLabel = ref.getStatusLabel();
        this.HeaderLabel = ref.getHeaderLabel();
        // initialisation ends
        
        this.pageContentState = new PageContentState(pageBox,PageContentState.StateType.NOT_FOUND);
        this.pageValidityState = new State(pageBox, State.ComponentState.DEFAULT_AND_INVALID);
        this.nextButtonValidityState = new State(nextButton, State.ComponentState.DEFAULT_AND_INVALID);
        this.pageBox.setText(DEFAULT_PAGE_BOX_TEXT);
        addPageBoxBehaviour();
        addBackButtonbehaviour();
        addNextButtonBehaviour();
    }
    
    private class CS {
    
        private CS(){
            
        }
        
        public static Color get(int r, int g, int b){
            
            return new Color(r,g,b);
        }
    }

    
    private ColorScheme backCS = new ColorScheme(CS.get(27,36,45), CS.get(255,51,51), CS.get(224,51,51), CS.get(57,75,92));
    
    private void addBackButtonbehaviour(){
        
        backButton.setCurrentColor(backCS.getDefaultColor());
        
        ActionListener backActListen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ref.dispose();
        
                parentFrame.pack();
                parentFrame.setVisible(true);

                
               
                
            }
        };
        
        backButton.addActionListener(backActListen);
        
        MouseAdapter backMAdapt = new MouseAdapter() {
            
            @Override
            public void mouseEntered(MouseEvent event){
                backButton.setCurrentColor(backCS.getHoverColor());
            }
            
            @Override
            public void mouseExited(MouseEvent event){
                 backButton.setCurrentColor(backCS.getDefaultColor());

            }
            
            @Override
            public void mousePressed(MouseEvent event){
                backButton.setCurrentColor(backCS.getPressedColor());
            }
            
            @Override
            public void mouseReleased(MouseEvent event){
                 backButton.setCurrentColor(backCS.getHoverColor());

            }
            
            
        };
        
        backButton.addMouseListener(backMAdapt);
    }
    
    private ColorScheme nextCS = new ColorScheme(backCS.getDefaultColor(), CS.get(76,175,80), CS.get(66,149,69), backCS.getDisabledColor());
    
    private void addNextButtonBehaviour(){
        
        nextButton.setCurrentColor(nextCS.getDefaultColor());
        
        ActionListener nextActListen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ref.dispose();
                
                hyperlinkEditFrame hef = new hyperlinkEditFrame(initialisedBook, tdm, existingTerm, newTerm, termFound, ref);
                    hef.setVisible(true);
                
            }
        };
        
        nextButton.addActionListener(nextActListen);
        
        MouseAdapter nextMAdapt = new MouseAdapter() {
            
            @Override
            public void mouseEntered(MouseEvent event){
                nextButton.setCurrentColor(nextCS.getHoverColor());
            }
            
            @Override
            public void mouseExited(MouseEvent event){
                 nextButton.setCurrentColor(nextCS.getDefaultColor());

            }
            
            @Override
            public void mousePressed(MouseEvent event){
                nextButton.setCurrentColor(nextCS.getPressedColor());
            }
            
            @Override
            public void mouseReleased(MouseEvent event){
                 nextButton.setCurrentColor(nextCS.getHoverColor());

            }
        };
        
        nextButton.addMouseListener(nextMAdapt);
    }
    
    private void addPageBoxBehaviour(){
        
        
        Document pageDoc = pageBox.getDocument();
        
        if(pageDoc instanceof AbstractDocument){
            AbstractDocument abstractPageDoc = (AbstractDocument) pageDoc;
            abstractPageDoc.setDocumentFilter(new ExtendedNumericAndFirstDigitFilter(4));
            //pageBox.setDocument(abstractPageDoc);
        }
        else{
            
            System.err.println("WARNING: DOCUMENT FILTER COULDN'T BE SET BECAUSE "
                    + "THE DOCUMENT TYPE RECIEVED WAS NOT AN INSTANCE OF ABSTRACT DOCUMENT");
        }
        
        bookField.setText(initialisedBook.getTitle());
        bookField.setForeground(initialisedBook.getColorScheme()[1]);
        
        DocumentListener pageDocListen = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }
        };
        
        FocusAdapter pageFAdapt = new FocusAdapter() {
            
            @Override
            public void focusGained(FocusEvent e){
                if(pageBox.getText().trim().equals(DEFAULT_PAGE_BOX_TEXT)){
                    pageBox.setText("");
                    statusLabel.setText("You can enter numbers only, and first digit cant be 0.");
                }
            }
            
            @Override
            public void focusLost(FocusEvent e){
                if(pageBox.getText().isBlank()){
                    pageBox.setText(DEFAULT_PAGE_BOX_TEXT);
                    statusLabel.setText("Click on this text box to enter page number!");
                }
            }
        };
        
        pageBox.addFocusListener(pageFAdapt);
        pageDoc.addDocumentListener(pageDocListen);
        
        Action upAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CURRENT_INDEX = updateCurrentIndex(contentMap, pageContentState, CURRENT_INDEX, updownTYPE.UP);
                updateFields(contentMap, CURRENT_INDEX, chapterField, majorTopicField, subtopicField);
                HeaderLabel.setText(getHeaderLabelText(pageContentState, pageValidityState, contentMap, CURRENT_INDEX));
            }
        };
        
        Action downAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CURRENT_INDEX = updateCurrentIndex(contentMap, pageContentState, CURRENT_INDEX, updownTYPE.DOWN);
                updateFields(contentMap, CURRENT_INDEX, chapterField, majorTopicField, subtopicField);
                HeaderLabel.setText(getHeaderLabelText(pageContentState, pageValidityState, contentMap, CURRENT_INDEX));
            }
        };

        KeyStroke upKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
        KeyStroke downKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
        
        InputMap inputMap = pageBox.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = pageBox.getActionMap();
        
        String upActionName = "upAction";
        String downActionName = "downAction";
        
        inputMap.put(upKeyStroke, upActionName);
        actionMap.put(upActionName, upAction);
        
        inputMap.put(downKeyStroke, downActionName);
        actionMap.put(downActionName, downAction);
               
                
        
    }
    
    private void update(){
        
        String pageString = pageBox.getText();
        CURRENT_INDEX = 0; // Always updates the current Index to 0 whenever a new number is typed
        
        if(pageString == null || pageString.isBlank() || pageString.equals(DEFAULT_PAGE_BOX_TEXT)){
        
            pageContentState.setCurrentState(PageContentState.StateType.NOT_FOUND);
            pageValidityState.setState(State.ComponentState.DEFAULT_AND_INVALID);
            nextButtonValidityState.setState(State.ComponentState.DEFAULT_AND_INVALID);
            updateButton(nextButtonValidityState, nextButton);
            setFieldsToDefault(chapterField, majorTopicField, subtopicField);
        }
        else{
            
            pageValidityState.setState(State.ComponentState.NON_DEFAULT_AND_VALID);
            nextButtonValidityState.setState(State.ComponentState.NON_DEFAULT_AND_VALID);
        }
        
        
        
        if(pageValidityState.isValid()){
            System.out.println("inside RAN");
            int page = Integer.parseInt(pageString);
                updateState(page);
                updateFields(contentMap, 0, chapterField, majorTopicField, subtopicField); // always update fields at 0th index
                updateButton(nextButtonValidityState, nextButton);
                
        }
        
        statusLabel.setText(getStatusLabelText(pageContentState, pageValidityState, contentMap));
        HeaderLabel.setText(getHeaderLabelText(pageContentState, pageValidityState, contentMap, CURRENT_INDEX));
    }
    
    
    private void updateState(int page){
        
        
        contentMap = initialisedBook.getEverythingByPage(page);
        
        
        if(contentMap == null){
            System.out.println("currently null");
            pageContentState.setCurrentState(PageContentState.StateType.NOT_FOUND);
            nextButtonValidityState.setState(State.ComponentState.NON_DEFAULT_AND_INVALID);
        }
        else if(contentMap.size() == 1){
            pageContentState.setCurrentState(PageContentState.StateType.SINGULAR_ENTRY);
            nextButtonValidityState.setState(State.ComponentState.NON_DEFAULT_AND_VALID);
        }
        else if(contentMap.size() > 1){
            pageContentState.setCurrentState(PageContentState.StateType.MULTIPLE_ENTRIES);
            nextButtonValidityState.setState(State.ComponentState.NON_DEFAULT_AND_VALID);
        }
    }
    
    private enum updownTYPE{

        UP,
        DOWN
    }
    
    
    private int updateCurrentIndex(Map<Integer, String[]> content, PageContentState pcs, int currentIndex, updownTYPE type){
        
        if(!pcs.isMultiFound()){
            return 0;
        }
        
        switch (type) {
            case UP:{
                
                currentIndex++;
                if(currentIndex == content.size()){
                    currentIndex = 0;
                }
                break;
            }
                
            case DOWN:{
                
                currentIndex--;
                if(currentIndex == -1){
                    currentIndex = content.size() - 1;
                }
                break;
            }
        }
        
        
        return currentIndex;
        
    }
    
    private void updateFields(
            Map<Integer, String[]> content, 
            int currentIndex,
            JLabel chapterField,
            JLabel majorTopicField,
            JTextArea subtopicField)
    {
        if(!pageValidityState.isValid()){
            return;
        }
        
        String[] currentContent = content.get(currentIndex);
        if(currentContent != null){
            
            
            
            new Thread(new Runnable(){
                @Override
                public void run() {
                    String chapterSetText;
                    String majorTopicSetText;
                    String subtopicSetText;
                    chapterSetText = currentContent[0];
                    majorTopicSetText = currentContent[1];
                    subtopicSetText = currentContent[2];
                    
                    SwingUtilities.invokeLater(new Runnable(){
                        @Override
                        public void run() {
                            
                            chapterField.setText(chapterSetText);
                            majorTopicField.setText(majorTopicSetText);
                            subtopicField.setText(subtopicSetText);
                        }
                        
                    });
                }
                
            }).start();
            
            /*
            chapterField.setText(currentContent[0]);
            majorTopicField.setText(currentContent[1]);
            subtopicField.setText(currentContent[2]);
            */
            
         
            nextButtonValidityState.setState(State.ComponentState.NON_DEFAULT_AND_VALID);
        }
        else {
            System.out.println("WARNING: input Map was found null");
            pageContentState.setCurrentState(PageContentState.StateType.NOT_FOUND);
            setFieldsToDefault(chapterField, majorTopicField, subtopicField);
            nextButtonValidityState.setState(State.ComponentState.NON_DEFAULT_AND_INVALID);
        }
    }
    
    private void setFieldsToDefault(JLabel chapterField,
            JLabel majorTopicField,
            JTextArea subtopicField){
        
        chapterField.setText(" -");
        majorTopicField.setText(" -");
        subtopicField.setText(" -");
    }
    
    private void updateButton(State state, JButton button){
        if(state.isValid()){
            button.setEnabled(true);
        }
        else{
            button.setEnabled(false);
        }
    }
    
    private String getStatusLabelText(PageContentState pcs, State st, Map<Integer, String[]> content){
        
        String defaultString = "You can enter numbers only, and first digit cant be 0.";
        String noContentFound = "No Entry found for specified page number.";
        String singleEntryFound = "Single entry found!";
        String multiEntryFound = "Multiple entries (&) found! Use 'Up' and 'Down' arrow to navigate.";
        
        
        if(!st.isValid()){
            
            return defaultString;
        }
        
        if(pcs.isFound()){
            
            if(pcs.isMultiFound()){
                return multiEntryFound.replaceFirst("&", Integer.toString(content.size()));
            }
            return singleEntryFound;
        }
        
        return noContentFound;
        
    }
    
    private String getHeaderLabelText(PageContentState pcs, State st, Map<Integer, String[]> content, int currentIndex){
        
        String defaultString = "Reference: ";
        String multiFound = defaultString + "(%/&)";
        
        if(!st.isValid()){
            return defaultString;
        }
        
        if(pcs.isMultiFound()){
            return multiFound.replaceFirst("%", Integer.toString(currentIndex + 1)).replaceFirst("&", Integer.toString(content.size()));
        }
       //
       
       
       return defaultString; // todo: remove this
    }
    
    
}
