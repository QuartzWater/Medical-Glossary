/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import backend.AppConstants;
import backend.ColorScheme;
import backend.PageContentState;
import backend.State;
import backend.Term;
import backend.TermDataManagement;
import backend.Utils;
import book.bookpicker.Book;
import legacy.NumericAndFirstDigitFilter;
import java.awt.Color;
import java.awt.Font;
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
    private JLabel superHeadingBox;
    private JLabel middleHeadingBox;
    private JTextArea subHeadingBox;
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
    
    private final Font originalSuperHeadingTextBoxFont;
    private final Font originalMiddleHeadingTextBoxFont;
    private final Font originalSubHeadingTextBoxFont;
    
    // -------------------------------------------------------------
    
    // TODO:
    //CRITICAL BUG: When typing number and then pressing backspace so that the field is empty throws a NUMBERFORMATEXCEPTION
    // However, if we type a number like : 3 and THEN press any non numberic key( it'll not accept it, correctly behaving)
    // and then press backspace so that field is empty DONT result in NUMBERFORMATEXCEPTION
    
    
    // ^^^^^^^^^^^^^^^^^^^^^^^ SOLVED ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    
    // LESSON LEARNT: NEVER carelessly update states of objects in a DocumentFilter ever again!!!
    
    private class ExtendedNumericAndFirstDigitFilter extends NumericAndFirstDigitFilter{
        
        public ExtendedNumericAndFirstDigitFilter(int maxSize){
            super(maxSize);
        }
        
        @Override
        protected boolean isValidInput(DocumentFilter.FilterBypass fb, int offset, int length, String text) throws BadLocationException{
            boolean warningEnabled = false;
            if (text == null || text.isBlank() || text.equals(DEFAULT_PAGE_BOX_TEXT)) {
                
                
                return true;
            }
            
            warningEnabled = !super.isValidInput(fb, offset, length, text);
            updateWarningOnStatusLabel(warningEnabled, text);
            return !warningEnabled;
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
        this.superHeadingBox = ref.getSuperHeadingBox();
        this.middleHeadingBox = ref.getMiddleHeadingBox();
        this.subHeadingBox = ref.getSubHeadingBox();
        this.backButton = ref.getBackButton();
        this.nextButton = ref.getNextButton();
        this.statusLabel = ref.getStatusLabel();
        this.HeaderLabel = ref.getHeaderLabel();
        // initialisation ends
        
        this.originalSuperHeadingTextBoxFont = superHeadingBox.getFont();
        this.originalMiddleHeadingTextBoxFont = middleHeadingBox.getFont();
        this.originalSubHeadingTextBoxFont = subHeadingBox.getFont();
        
        this.pageContentState = new PageContentState(pageBox,PageContentState.StateType.NOT_FOUND);
        this.pageValidityState = new State(pageBox, State.ComponentState.DEFAULT_AND_INVALID);
        this.nextButtonValidityState = new State(nextButton, State.ComponentState.DEFAULT_AND_INVALID);
        
        
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
            
            boolean isOutside = true;
            @Override
            public void mouseEntered(MouseEvent event){
                isOutside = false;
                backButton.setCurrentColor(AppConstants.DEFAULT_BACK_BUTTON_CS_1.getHoverColor());
            }
            
            @Override
            public void mouseExited(MouseEvent event){
                isOutside = true;
                backButton.setCurrentColor(AppConstants.DEFAULT_BACK_BUTTON_CS_1.getDefaultColor());
            }
            
            @Override
            public void mousePressed(MouseEvent event){
                backButton.setCurrentColor(AppConstants.DEFAULT_BACK_BUTTON_CS_1.getPressedColor());
            }
            
            @Override
            public void mouseReleased(MouseEvent event){
                if(!isOutside){
                    backButton.setCurrentColor(AppConstants.DEFAULT_BACK_BUTTON_CS_1.getHoverColor());
                }
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
                
                if(searchPageWaitGroup.activeCount() == 0){
                    newTerm.setPage(Integer.parseInt(pageBox.getText()));
                    newTerm.setSuperHeadingContent(superHeadingBox.getText());
                    newTerm.setMiddleHeadingContent(middleHeadingBox.getText());
                    newTerm.setSubHeadingContent(subHeadingBox.getText());
                    ref.dispose();

                    hyperlinkEditFrame hef = new hyperlinkEditFrame(initialisedBook, tdm, existingTerm, newTerm, termFound, ref);
                        hef.setVisible(true);
                        hef.requestFocusInWindow();
                }
                
            }
        };
        
        nextButton.addActionListener(nextActListen);
        
        MouseAdapter nextMAdapt = new MouseAdapter() {
            
            boolean isOutside = true;
            @Override
            public void mouseEntered(MouseEvent event){
                isOutside = false;
                nextButton.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getHoverColor());
            }
            
            @Override
            public void mouseExited(MouseEvent event){
                isOutside = true;
                nextButton.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getDefaultColor());
            }
            
            @Override
            public void mousePressed(MouseEvent event){
                nextButton.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getPressedColor());
            }
            
            @Override
            public void mouseReleased(MouseEvent event){
                if(!isOutside){
                    nextButton.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getHoverColor());
                }
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
        
        DocumentListener pageDocListen = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                waitForStabilization(300);
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                waitForStabilization(300);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                waitForStabilization(300);
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
                updateFields(contentMap, CURRENT_INDEX, superHeadingBox, middleHeadingBox, subHeadingBox);
                HeaderLabel.setText(getHeaderLabelText(pageContentState, pageValidityState, contentMap, CURRENT_INDEX));
            }
        };
        
        Action downAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CURRENT_INDEX = updateCurrentIndex(contentMap, pageContentState, CURRENT_INDEX, updownTYPE.DOWN);
                updateFields(contentMap, CURRENT_INDEX, superHeadingBox, middleHeadingBox, subHeadingBox);
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
    
    private ThreadGroup statusClearThreads = new ThreadGroup("statusClearThreads");
    
    int currentThreadCount = 0;
    
    private void clearStatusLabelAfterInterval(long milliseconds){
        
        if(statusClearThreads.activeCount() > 0){
        statusClearThreads.interrupt();
        }
        //currentThreadCount++;
        
        Thread statusClear  = new Thread(statusClearThreads, new Runnable() {
            @Override
            public void run(){
                try{
                    Thread.sleep(milliseconds);
                    SwingUtilities.invokeLater(new Runnable(){
                        
                        @Override
                        public void run() {
                            if(!statusLabel.getText().equals("")){
                                if(pageBox.getText().equals("")){
                                    statusLabel.setText("You can enter numbers only, and first digit cant be 0.");
                                }else{
                                    statusLabel.setText("");
                                }
                            }
                        }
                        
                    });
                    
                }
                catch(InterruptedException e){
                    System.out.println("ClearThread_referenceEditGUI" + Integer.toString(currentThreadCount) +" Interrupted: process clearing statusLabel cancelled. Restarted timer.");
                    
                }
            }
        }, "StatusClearThread" + Integer.toString(currentThreadCount));
        
        statusClear.start();
        
    }
    
    private void updateWarningOnStatusLabel(boolean toEnable, String invalidInput){
        
        if(toEnable){
            String toSetText;
            if(invalidInput.length() > 1){
                invalidInput = "This particular set of characters";
                toSetText = "Warning: " + invalidInput + " is not allowed in spelling text.";
            }
            else if(invalidInput.equals("0")){
                toSetText = "First digit can't be zero.";
            }
            else{
                invalidInput = "'" + invalidInput + "'";
                toSetText = "Warning: " + invalidInput + " is not allowed in spelling text.";
            }
            statusLabel.setText(toSetText);
            clearStatusLabelAfterInterval(5000);
        }
        else{
            if(statusClearThreads.activeCount() > 0){
                statusClearThreads.interrupt();
            }
            if(!statusLabel.getText().equals("")){
                statusLabel.setText("");
            }
        }
    }
    
    ThreadGroup searchPageWaitGroup = new ThreadGroup("searchSpellingWaitGroup");
    int currentWaitThread = 0;
    
    private void waitForStabilization(long milliseconds){
        
        statusLabel.setText("Loading Configuration...");
        if(statusClearThreads.activeCount() > 0){ // This ensures that update GUI thread is at highest priority and interrupts any status clear thread.
                                                  // because we don't want to clear status when showing message related to term found or not.
            statusClearThreads.interrupt();
            
        }
        if(searchPageWaitGroup.activeCount() > 0){
            searchPageWaitGroup.interrupt();
        }
        Thread searchWaitThread = new Thread(searchPageWaitGroup, new Runnable() {
            @Override
            public void run() {
                
                try {
                    Thread.sleep(milliseconds);
                    
                    SwingUtilities.invokeLater(new Runnable(){
                        @Override
                        public void run() {
                            
                            update(); // maybe in future add a more generic runnable instance instead.
                        }
                        
                        
                    });
                } catch (InterruptedException ex) {
                    System.out.println("WaitThread_referenceEditGUI" + Integer.toString(currentThreadCount) + " was interrupted due to quick user input.");
                }
            }
        }, "WaitThread" + Integer.toString(currentThreadCount));
        
        searchWaitThread.start();
    }
    
    private void update(){
        
        String pageString = pageBox.getText();
        CURRENT_INDEX = 0; // Always updates the current Index to 0 whenever a new number is typed
        
        if(pageString == null || pageString.isBlank() || pageString.equals(DEFAULT_PAGE_BOX_TEXT)){
        
            pageContentState.setCurrentState(PageContentState.StateType.NOT_FOUND);
            pageValidityState.setState(State.ComponentState.DEFAULT_AND_INVALID);
            nextButtonValidityState.setState(State.ComponentState.DEFAULT_AND_INVALID);
            updateButton(nextButtonValidityState, nextButton);
            setFieldsToDefault(superHeadingBox, middleHeadingBox, subHeadingBox);
        }
        else{
            
            pageValidityState.setState(State.ComponentState.NON_DEFAULT_AND_VALID);
            nextButtonValidityState.setState(State.ComponentState.NON_DEFAULT_AND_VALID);
        }
        
        
        
        if(pageValidityState.isValid()){
            int page = Integer.parseInt(pageString);
                updateState(page);
                updateFields(contentMap, 0, superHeadingBox, middleHeadingBox, subHeadingBox); // always update fields at 0th index
                updateButton(nextButtonValidityState, nextButton);
                
        }
        
        statusLabel.setText(getStatusLabelText(pageContentState, pageValidityState, contentMap));
        HeaderLabel.setText(getHeaderLabelText(pageContentState, pageValidityState, contentMap, CURRENT_INDEX));
    }
    
    
    private void updateState(int page){
        
        
        contentMap = initialisedBook.getEverythingByPage(page);
        
        
        if(contentMap == null){
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
            JLabel superHeadingField,
            JLabel middleHeadingField,
            JTextArea subHeadingField)
    {
        if(!pageValidityState.isValid()){
            return;
        }
        
        String[] currentContent = content.get(currentIndex);
        if(currentContent != null){
            
            
            
            new Thread(new Runnable(){
                @Override
                public void run() {
                    String superHeadingSetText;
                    String middleHeadingSetText;
                    String subHeadingSetText;
                    superHeadingSetText = currentContent[0];
                    middleHeadingSetText = currentContent[1];
                    subHeadingSetText = currentContent[2];
                    
                    SwingUtilities.invokeLater(new Runnable(){
                        @Override
                        public void run() {
                            
                            superHeadingField.setText(superHeadingSetText);
                            Utils.dynamicallyChangeFont(superHeadingField, originalSuperHeadingTextBoxFont);
                            middleHeadingField.setText(middleHeadingSetText);
                            Utils.dynamicallyChangeFont(middleHeadingField, originalMiddleHeadingTextBoxFont);
                            subHeadingField.setText(subHeadingSetText);
                            Utils.dynamicallyChangeFont(subHeadingField, originalSubHeadingTextBoxFont);
                        }
                        
                    });
                }
                
            }).start();
            
            /*
            superHeadingBox.setText(currentContent[0]);
            middleHeadingBox.setText(currentContent[1]);
            subHeadingBox.setText(currentContent[2]);
            */
            
         
            nextButtonValidityState.setState(State.ComponentState.NON_DEFAULT_AND_VALID);
        }
        else {
            pageContentState.setCurrentState(PageContentState.StateType.NOT_FOUND);
            setFieldsToDefault(superHeadingField, middleHeadingField, subHeadingField);
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
        String noContentFound = "No configuration found for specified page number.";
        String singleEntryFound = "Single configuration found!";
        String multiEntryFound = "Multiple configurations (&) found! Use 'Up' and 'Down' arrow to navigate.";
        
        
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
       
       
       return defaultString; // todo: remove this - UPDATE : I forgot why though...
    }
    
    
}
