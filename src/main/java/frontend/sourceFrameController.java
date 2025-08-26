/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import backend.SpellingFilter;
import backend.Term;
import backend.TermDataManagement;
import backend.Utils;
import backend.eventadapter.GranularMouseAdapter;
import book.bookpicker.Book;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
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
import javax.swing.text.JTextComponent;

/**
 *
 * @author BRIN
 */
public class sourceFrameController {
    
    
    private Book initialisedBook;
    private boolean termFound;
    private TermDataManagement tdm;
    
    
    private SourceFrame sf;
    
    private final String DEFAULT_SPELLING = "_PLACE_HOLDER_";
    private Term currentTerm = new Term(DEFAULT_SPELLING);
    
    private final String DEFAULT_SPELLINGBOX_TEXT = "<Enter Term>";
    private final String DEFAULT_STATUS_TEXT = "";
    private final String TERM_FOUND_STATUS_TEXT = "Term found! Press enter now to edit this term.";
    private final String TERM_NOT_FOUND_STATUS_TEXT = "Specified term was not found! Press enter now to create a new term";
    
    private final String DEFAULT_DEFINITION = "---";
    private final String DEFAULT_REFERENCE = " -"; // space for stylistic choice
    
    // ** CONTROLLABLE GUI VARIABLES DECLARATION ** //
    
    private JTextField spellingBox;
    private JTextArea definitionArea;
    private JLabel pageBox;
    private JLabel superHeadingBox;
    private JLabel middleHeadingBox;
    private JTextArea subHeadingBox;
    private JLabel statusLabel;
    private JLabel bookLabel;
    private JLabel hyperlinkInfoLabel;
    private SVGIconPanel SVGBrowserIconPanel;
    // ADD HERE IN FUTURE
    
    // ******* END OF VARIABLES DECLARATION ******* //
    
    private GranularMouseAdapter granMAdapt_svgPanel = new GranularMouseAdapter(){
        
        
        // TODO THERE WILL BE DIFFERENT ICONS.
        @Override
        public void actOnMouseEntry(MouseEvent e){
            
            try {
                SVGBrowserIconPanel.setCurrentColor(Color.white);
                SVGBrowserIconPanel.setBackgroundPainted(true);
                SVGBrowserIconPanel.set_SVG_URL_String("/images/icons/captive-portal-44-62-80.svg");
            } catch (IOException ex) {
                
                System.out.println("SVG icon couldn't be loaded...");
            }
        }
        
        @Override
        public void actOnMouseExit(MouseEvent e){
            try {
                SVGBrowserIconPanel.setBackgroundPainted(false);
                SVGBrowserIconPanel.set_SVG_URL_String("/images/icons/captive-portal-white.svg");
            } catch (IOException ex) {
                
                System.out.println("SVG icon couldn't be loaded...");
            }
        }
        
        @Override
        public void actOnMousePress(MouseEvent e){
            
            SVGBrowserIconPanel.setCurrentColor(new Color(237,237,237));
            SVGBrowserIconPanel.repaint();
               
            
        }
        
        @Override
        public void actOnMouseRelease(MouseEvent e){
            
            SVGBrowserIconPanel.setCurrentColor(Color.white);
            SVGBrowserIconPanel.repaint();
        }
        
        @Override
        public void actOnMouseClick(MouseEvent e){
            hyperlinkViewFrame hvf = hyperlinkViewFrame.createInstance(sf, currentTerm);
            hvf.setVisible(true);
            hvf.requestFocusInWindow();
        }
    };
    
    
    private final Font originalSuperHeadingTextBoxFont;
    private final Font originalMiddleHeadingTextBoxFont;
    private final Font originalSubHeadingTextBoxFont;

    
    private sourceFrameController(Book initialisedBook, TermDataManagement tdm, Term currentTerm, boolean termFound, SourceFrame sf){
     
        this.initialisedBook = initialisedBook;
        this.tdm = tdm;
        this.currentTerm = currentTerm;
        this.termFound = termFound;
        this.sf = sf;
        
        // **** GUI VARIABLES INITIALIZATION ***** //
        
        this.spellingBox = sf.getSpellingBox();
        this.definitionArea = sf.getDefinitionArea();
        this.pageBox = sf.getPageBox();
        this.superHeadingBox = sf.getSuperHeadingTextBox();
        this.middleHeadingBox = sf.getMiddleHeadingTextBox();
        this.subHeadingBox = sf.getSubHeadingTextBox();
        this.statusLabel = sf.getStatusLabel();
        this.bookLabel = sf.getBookLabel();
        this.hyperlinkInfoLabel = sf.getHyperlinkInfoLabel();
        this.SVGBrowserIconPanel = sf.getSVGBrowserIconPanel();
        
        // ADD HERE IN FUTURE
        
        // *** END OF VARIABLES INITIALIZATION *** //
        
        originalSuperHeadingTextBoxFont = superHeadingBox.getFont();
        originalMiddleHeadingTextBoxFont = middleHeadingBox.getFont();
        originalSubHeadingTextBoxFont = subHeadingBox.getFont();
        
        
        SVGBrowserIconPanel.addMouseListener(granMAdapt_svgPanel);
        addSpellingBoxFunctionality();
        granMAdapt_svgPanel.setCanRespondToClick(false);
        granMAdapt_svgPanel.setCanRespondToHover(false);
        granMAdapt_svgPanel.setCanRespondToPress_Release(false);
    }
    
    public static void initializeController(Book initialisedBook, TermDataManagement tdm, Term currentTerm, boolean termFound, SourceFrame sf){
        sourceFrameController sourceFrameController = new sourceFrameController(initialisedBook, tdm, currentTerm, termFound, sf);
    }
    
    private class ExtendedSpellingFilter extends SpellingFilter {
        
        public ExtendedSpellingFilter(int maxLength){
            
            super(maxLength);
        }
        
        @Override
        protected boolean isValidInput(DocumentFilter.FilterBypass fb, int offset, int length, String text) throws BadLocationException{
            
            // TODO : Custom Implementation to allow programatically set Text. : DONE
            boolean warningEnabled;
            
            if(text == null || text.equals("") || text.equals(DEFAULT_SPELLINGBOX_TEXT)){
                
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
                    System.out.println(e.getMessage());
                    System.out.println("StatusThread_sourceFrame" + Integer.toString(currentThreadCount) +" Interrupted: process clearing statusLabel cancelled. Restarted timer.");
                    
                }
            }
        }, "StatusClearThread" + Integer.toString(currentThreadCount));
        
        statusClear.start();
        
    }
    
    ThreadGroup searchSpellingWaitGroup = new ThreadGroup("searchSpellingWaitGroup");
    int currentWaitThread = 0;
    
    private void waitForStabilization(long milliseconds){
        
        statusLabel.setText("Please wait...");
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
                            updateGUI(); // maybe in future add a more generic runnable instance instead.
                        }
                        
                        
                    });
                } catch (InterruptedException ex) {
                    System.out.println("WaitThread_sourceFrame" + Integer.toString(currentThreadCount) + " was interrupted due to quick user input.");
                }
            }
        }, "WaitThread" + Integer.toString(currentThreadCount));
        
        searchWaitThread.start();
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
    
    private void addSpellingBoxFunctionality(){
        
        Document spellingDoc = spellingBox.getDocument();
        
        if(spellingDoc instanceof AbstractDocument abstractSpellingDoc){
            
            abstractSpellingDoc.setDocumentFilter(new ExtendedSpellingFilter(45));
            abstractSpellingDoc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // This method is called when text is inserted
                checkTerm();
                waitForStabilization(300);
                    
                
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // This method is called when text is removed
                checkTerm();
                waitForStabilization(300);
                
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // This method is called when attributes change (less common for JTextField)
                checkTerm();
                waitForStabilization(300);
            }

            
        });
        }
        
        FocusAdapter SpellingFAdapt = new FocusAdapter() {
            
            @Override
            public void focusGained(FocusEvent event){
                if(spellingBox.getText().equals(DEFAULT_SPELLINGBOX_TEXT)){
                    spellingBox.setText("");
                }
            }
            
            @Override
            public void focusLost(FocusEvent event){
                if(spellingBox.getText().equals("")){
                    spellingBox.setText(DEFAULT_SPELLINGBOX_TEXT);
                }
            }
        };
        
        spellingBox.addFocusListener(SpellingFAdapt);
        
        ActionListener spellingActListen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(searchSpellingWaitGroup.activeCount() == 0){
                    JTextComponent[] textComponents = {
                        spellingBox, definitionArea  
                    };

                    definitionEditFrame def = definitionEditFrame.generateInstance(initialisedBook, tdm, currentTerm, termFound, sf);
                
                    def.setVisible(true);
                    def.requestFocusInWindow();
                }
            }
        };
        
        spellingBox.addActionListener(spellingActListen);
        
        
        
    }
    
        
    private void checkTerm(){
        
        String currentSpelling = spellingBox.getText().trim().toLowerCase(); // BUG FIXED

        termFound = tdm.contains(currentSpelling);
        if(termFound){
            
            currentTerm = tdm.getTerm(currentSpelling);
        }
        else{
            
            currentTerm = new Term(DEFAULT_SPELLING);
        }        
    }
    
    private void updateGUI(){
        
        String currentSpelling = spellingBox.getText().trim().toLowerCase();
        granMAdapt_svgPanel.setCanRespondToClick(termFound);
        granMAdapt_svgPanel.setCanRespondToHover(termFound);
        granMAdapt_svgPanel.setCanRespondToPress_Release(termFound);
        if(termFound){
            try {
                SVGBrowserIconPanel.set_SVG_URL_String("/images/icons/captive-portal-white.svg");
            } catch (IOException ex) {
                System.getLogger(sourceFrameController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            statusLabel.setText(TERM_FOUND_STATUS_TEXT);
            pageBox.setText(Integer.toString(currentTerm.getPage()));
            superHeadingBox.setText(currentTerm.getSuperHeadingContent());
            Utils.dynamicallyChangeFont(superHeadingBox, originalSuperHeadingTextBoxFont);
            middleHeadingBox.setText(currentTerm.getMiddleHeadingContent());
            Utils.dynamicallyChangeFont(middleHeadingBox, originalMiddleHeadingTextBoxFont);
            subHeadingBox.setText(currentTerm.getSubHeadingContent());
            Utils.dynamicallyChangeFont(subHeadingBox, originalSubHeadingTextBoxFont);
            definitionArea.setText(currentTerm.getDefinition());
            
        }
        else
        {
            try {
                SVGBrowserIconPanel.set_SVG_URL_String("/images/icons/captive-portal-white-NA-55-percent-opaque.svg");
            } catch (IOException ex) {
                System.getLogger(sourceFrameController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            
            if(!currentSpelling.isBlank()){
                statusLabel.setText(TERM_NOT_FOUND_STATUS_TEXT);
            }
            else{
                statusLabel.setText(DEFAULT_STATUS_TEXT);
            }
            
            pageBox.setText(DEFAULT_REFERENCE);
            superHeadingBox.setText(DEFAULT_REFERENCE);
            middleHeadingBox.setText(DEFAULT_REFERENCE);
            subHeadingBox.setText(DEFAULT_REFERENCE);
            definitionArea.setText(DEFAULT_DEFINITION);
        }
    }
    
    
    
}
