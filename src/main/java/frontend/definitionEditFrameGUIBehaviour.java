/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import backend.ColorScheme;
import backend.SpellingFilter;
import backend.State;
import backend.Term;
import backend.TermDataManagement;
import book.bookpicker.Book;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
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
public class definitionEditFrameGUIBehaviour {
    
    private Term existingTerm;
    private Term newTerm;
    private TermDataManagement tdm;
    private Book initialisedBook;
    private boolean termFound;
    private definitionEditFrame def;
    
    // ** CONTROLLED GUI VARIABLES DECLARATION ** //
    
    private JTextField spellingBox;
    private JTextArea definitionArea;
    private JLabel statusLabel;
    private RoundedButton backButton;
    private RoundedButton nextButton;
    
    // ****** END OF VARIABLES DECLARATION ****** //
    
    private State spellingBoxState;
    private String defaultSpellingString = "<Enter Spelling>";
    
    
    
    public definitionEditFrameGUIBehaviour(definitionEditFrame def){
        
        this.def = def;
        this.initialisedBook = def.getInitialisedBook();
        this.tdm = def.getInitialisedTDM();
        this.existingTerm = def.getExistingTerm();
        this.newTerm = def.getNewTerm();
        this.termFound = def.getTermFound();
        
        // ** GUI VARIABLES INITIALISATION ** //
        
        this.spellingBox = def.getSpellingBox();
        this.definitionArea = def.getDefinitionTextArea();
        this.statusLabel = def.getStatusLabel();
        this.backButton = def.getBackButton();
        this.nextButton = def.getNextButton();
        
        // ***** END OF INITIALISATION ***** //
        
        spellingBox.setText(def.getSpellingDefinitionArray()[0]);
        definitionArea.setText(def.getSpellingDefinitionArray()[1]);
        
        if(!termFound){
            if(def.getSpellingDefinitionArray()[0].equals("")){
                spellingBox.setText(defaultSpellingString);
            }
        }
        
        spellingBoxState = new State(spellingBox, State.ComponentState.DEFAULT_AND_INVALID);
        addSpellingBoxBehaviour();
        addNextButtonFunctionality();
        addBackButtonFunctionality();
        
        //debug code
        Iterator<String> itr = tdm.getAllTerms().iterator();
        while(itr.hasNext()){
            System.out.print(itr.next() +"\n");
        }
        
        
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
                                statusLabel.setText("");
                            }
                        }
                        
                    });
                    
                }
                catch(InterruptedException e){
                    System.out.println("ClearThread_definitionEditGUI" + Integer.toString(currentThreadCount) +" Interrupted: process clearing statusLabel cancelled. Restarted timer.");
                    
                }
            }
        }, "StatusClearThread" + Integer.toString(currentThreadCount));
        
        statusClear.start();
        
    }
    
    private void waitAndInterruptGroup(long milliseconds, ThreadGroup toInterrupt){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(milliseconds);
                    toInterrupt.interrupt();
                } catch (InterruptedException ex) {
                    System.out.println("Interrupting Thread got interrupted");
                }
            }
        }).start();
        
    }
    
    private void waitAndInterrupt(long milliseconds, Thread toInterrupt){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(milliseconds);
                    toInterrupt.interrupt();
                } catch (InterruptedException ex) {
                    System.out.println("Interrupting Thread got interrupted");
                }
            }
        }).start();
        
    }
    
    private class ExtendedSpellingFilter extends SpellingFilter{
        
        public ExtendedSpellingFilter(int maxLength) {
            super(maxLength);
        }
        
        @Override
        public boolean isValidInput(DocumentFilter.FilterBypass fb, int offset, int length, String text) throws BadLocationException{
            
            // TODO : Custom Implementation to allow programatically set Text.
            
            boolean warningEnabled;
            
            if(text == null || text.equals("") || text.equals(defaultSpellingString)){
                
                return true;
            }
            warningEnabled = !super.isValidInput(fb, offset, length, text);
            if(super.maxLengthReached){
                statusLabel.setText("Maximum amount of characters reached!");
            }
            else{
                updateWarningOnStatusLabel(warningEnabled, text);
            }
            
            return !warningEnabled;
        }
    }
    
    private void updateWarningOnStatusLabel(boolean toEnable, String invalidInput){
        
        if(toEnable){
            if(invalidInput.length() > 1){
                invalidInput = "This particular set of characters";
            }
            else{
                invalidInput = "'" + invalidInput + "'";
            }
            statusLabel.setText("Warning: " + invalidInput + " is not allowed in spelling text.");
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
    
    private void addSpellingBoxBehaviour(){
        
        // TODO : ADD LISTENERS AND CONDITIONS
        DocumentListener spellDocListen = new DocumentListener() {
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
        
        Document spellDoc = spellingBox.getDocument();
        if(spellDoc instanceof AbstractDocument spellAbstractDoc){
            spellAbstractDoc.setDocumentFilter(new ExtendedSpellingFilter(45));
            spellAbstractDoc.addDocumentListener(spellDocListen);
        }
        
        FocusAdapter spellFAdapt = new FocusAdapter() {
            
            @Override
            public void focusGained(FocusEvent event){
                if(spellingBox.getText().equals(defaultSpellingString)){
                    spellingBox.setText("");
                }
            }
            
            @Override
            public void focusLost(FocusEvent event){
                if(spellingBox.getText().equals("")){
                    
                    spellingBox.setText(defaultSpellingString);
                }
            }
        };
        
        spellingBox.addFocusListener(spellFAdapt);
    }
    
    ThreadGroup searchSpellingWaitGroup = new ThreadGroup("searchSpellingWaitGroup");
    int currentWaitThread = 0;
    
    private void waitForStabilization(long milliseconds){
        
        statusLabel.setText("Checking...");
        if(statusClearThreads.activeCount() > 0){ // This ensures that update GUI thread is at highest priority and interrupts any status clear thread.
                                                  // because we don't want to clear status when showing message related to term found or not.
            statusClearThreads.interrupt();
            
        }
        if(searchSpellingWaitGroup.activeCount() > 0){
            searchSpellingWaitGroup.interrupt();
        }
        Thread searchWaitThread = new Thread(searchSpellingWaitGroup, new Runnable() {
            @Override
            public void run() {
                
                try {
                    Thread.sleep(milliseconds);
                    
                    SwingUtilities.invokeLater(new Runnable(){
                        @Override
                        public void run() {
                            
                            updateNextButton(); // maybe in future add a more generic runnable instance instead.
                        }
                        
                        
                    });
                } catch (InterruptedException ex) {
                    System.out.println("WaitThread_definitionEditGUI" + Integer.toString(currentThreadCount) + " was interrupted due to quick user input.");
                }
            }
        }, "WaitThread" + Integer.toString(currentThreadCount));
        
        searchWaitThread.start();
    }
    
    private void updateNextButton(){
        boolean isTextDefault = spellingBox.getText().trim().equals("") || spellingBox.getText().trim().equals(defaultSpellingString);
        System.out.println("code ran");
        boolean alrdyEx = alreadyExists(); // chose to do this way because if first condition was becoming false, then alreadyExists() was not even executing
        if(!isTextDefault && !alrdyEx){
        
            if(!termFound)
                def.setTitle("Create new term: '" +spellingBox.getText() + "'");
            else
                def.setTitle("Edit Mode: '" + def.getSpellingDefinitionArray()[0] + "' to '" + spellingBox.getText() + "'");
            if(!nextButton.isEnabled())
            nextButton.setEnabled(true);
        }
        else{
            if(!termFound)
                def.setTitle("Create new term: ");
            else
                def.setTitle("Edit Mode: '" + def.getSpellingDefinitionArray()[0] + "' to ");
            if(nextButton.isEnabled()){
                nextButton.setEnabled(false);
            }
            
            
        }
    }
    
    // TODO : PROBABLY BROKEN LOGIC:: FIXED LOGIC NOW!
    private boolean alreadyExists(){
        
        // TODO: ADD VALIDATION LOGIC TO PREVENT ANY SPELLING CREATION THAT 
        // ALREADY MATCHES THE EXISTING TERM SPELLING UNLESS THE SPECIFIED TERM WAS FIRST
        // SEARCHED IN SOURCE FRAME THEN IT WAS SET TO EDIT.
        String testString = spellingBox.getText().trim().toLowerCase();
        if(tdm.contains(testString)){
            System.out.println("first condition true");
            System.out.println(termFound);
            System.out.println(def.getSpellingDefinitionArray()[0].trim().toLowerCase());
            if(!termFound){
                statusLabel.setText("You are trying to create a term that already exists!");
                clearStatusLabelAfterInterval(8000);
                return true;
            }
            
            if(!testString.equals(def.getSpellingDefinitionArray()[0].trim().toLowerCase())){
                System.out.println("second condition true");
                statusLabel.setText("'" + testString + "' already exists as another spelling!");
                clearStatusLabelAfterInterval(8000);
                return true;
            }
            statusLabel.setText("");
        }
        else{
            statusLabel.setText("");
        }
        return false;
    }
    
    private ColorScheme nextButtonColorScheme = new ColorScheme(
      
        new Color(27,36,45),
        new Color(69,160,73),
        new Color(62,142,65),
        new Color(57,75,92)
            
    );
    
    private ColorScheme backButtonColorScheme = new ColorScheme(
            new Color(27,36,45),
            new Color(255,102,102),
            new Color(255,51,51),
            new Color(57,75,92)
    );
    
    private void addNextButtonFunctionality(){
        
        
        newTerm = new Term(spellingBox.getText());
        newTerm.setDefinition(definitionArea.getText());
              
        ActionListener nextButtonAct = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(searchSpellingWaitGroup.activeCount() == 0){
                    def.dispose();
                    referenceEditFrame ref = referenceEditFrame.generateInstance(initialisedBook, tdm, existingTerm, newTerm, termFound, def);
                    ref.setVisible(true);
                    ref.requestFocusInWindow();
                }
            }
        };
        
        MouseAdapter nextMAdapt = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event){
                nextButton.setCurrentColor(nextButtonColorScheme.getHoverColor());
            }
            
            @Override
            public void mouseExited(MouseEvent event){
                nextButton.setCurrentColor(nextButtonColorScheme.getDefaultColor());
            }
            
            @Override
            public void mousePressed(MouseEvent event){
                nextButton.setCurrentColor(nextButtonColorScheme.getPressedColor());
            }
            
            @Override
            public void mouseReleased(MouseEvent event){
                nextButton.setCurrentColor(nextButtonColorScheme.getHoverColor());
            }
        };
        
        nextButton.addActionListener(nextButtonAct);
        nextButton.addMouseListener(nextMAdapt);
    }
    
    private void addBackButtonFunctionality(){
        
        ActionListener backActListen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                def.dispose();
            }
        };
        
        MouseAdapter backMAdapt = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event){
                backButton.setCurrentColor(nextButtonColorScheme.getHoverColor());
            }
            
            @Override
            public void mouseExited(MouseEvent event){
                backButton.setCurrentColor(nextButtonColorScheme.getDefaultColor());
            }
            
            @Override
            public void mousePressed(MouseEvent event){
                backButton.setCurrentColor(nextButtonColorScheme.getPressedColor());
            }
            
            @Override
            public void mouseReleased(MouseEvent event){
                backButton.setCurrentColor(nextButtonColorScheme.getHoverColor());
            }
        };
        
        backButton.addActionListener(backActListen);
        backButton.addMouseListener(backMAdapt);
    }
}
