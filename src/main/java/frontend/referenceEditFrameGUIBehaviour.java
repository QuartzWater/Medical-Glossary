/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import backend.Term;
import backend.TermDataManagement;
import book.bookpicker.Book;

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
    
    public referenceEditFrameGUIBehaviour(referenceEditFrame ref){
        
        initialisedBook = ref.getInitialisedBook();
        tdm = ref.getTDM();
        existingTerm = ref.getExistingTerm();
        newTerm = ref.getNewTerm();
        termFound = ref.getTermFound();
        
        parentFrame = ref.getDEFParent();
        this.ref = ref;
    }
    
    
}
