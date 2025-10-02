/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend.v2.state;

import backend.v2.term.AdvancedTerm;
import book.bookpicker.Book;
import frontend.v2.containers.ContentProcessor;
import frontend.v2.containers.TermInfoContainer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BRIN
 */
public class AppState {
    
    // THE ORDER IN WHICH LIST IS ITERATED TO CALL ALL REGISTERED LISTENERS IS VERY IMPORTANT TO AVOID SUBTLE BUGS
    // DO NOT CHANGE THE ITERATION ORDER IN FUTURE UNLESS YOU HAVE REVIEWED ALL REGISTERING CLASSES.
    
    public static void fireQuickAccessEvent(Object source, String selection, QuickAccessEvent.TYPE type){
        for(QuickAccessListener l : quickAccessListeners){
            l.menuSelected(new QuickAccessEvent(source, selection, type));
        }
    }
    
    public static void fireEditModeStatusChanged(boolean isEditActive, TermInfoContainer reference, ContentProcessor activePanel){
        for(TICEditModeListener l :TICEventListener){
            l.editModeStatusChanged(new TICEditModeEvent(new AppState(), isEditActive, reference, activePanel));
        }
    }
    
    public static void changeActiveSearchedTerm(AdvancedTerm newAdvTerm){
        
        fireActiveSearchTermChange(currentActiveSearchedTerm, newAdvTerm);
        currentActiveSearchedTerm = newAdvTerm;
        
    }
    
    public static AdvancedTerm getCurrentActiveSearchedTerm(){
        return currentActiveSearchedTerm;
    }
    
    public static void changeInputSpelling(String newTerm){
            fireSearchTermChange(currentInputSpelling, newTerm);
            currentInputSpelling = newTerm;
    }
    
    public static String getCurrentInputSpelling(){
        
        return currentInputSpelling;
    }
    
    public static void changeBook(Book newBook){
        if(currentlyActiveBook != newBook){
            fireBookChange(currentlyActiveBook, newBook);
            currentlyActiveBook = newBook;
        }
    }
    
    public static Book getCurrentBook(){
        return currentlyActiveBook;
    }
    
    public static void changeState(Type toChange) {
        if(currentType != toChange){
            fireChange(currentType, toChange);
            currentType = toChange;
        }
    }
    
    public static Type getCurrentState(){
        return currentType;
    }
    
    public static void setTermNotFound(){
        termFound = false;
    }
    
    public static void setTermFound(){
        termFound = true;
    }
    
    public static boolean isTermFound(){
        return termFound;
    }
    
    public enum Type {
        SEARCH,
        CREATE
    }
    
    public static void addQuickAccessListener(QuickAccessListener l){
        quickAccessListeners.add(l);
    }
    
    public static void removeQuickAccessListener(QuickAccessListener l){
        quickAccessListeners.remove(l);
    }
    
    public static void addTICEventListener(TICEditModeListener l){
        TICEventListener.add(l);
    }
    
    public static void removeTICEventListener(TICEditModeListener l){
        TICEventListener.remove(l);
    }
    
    public static void addActiveSearchedTermChangeListener(ActiveSearchedTermChangeListener l){
        activeSearchedTermChangeListeners.add(l);
    }
    
    public static void removeActiveSearchedTermChangeListener(ActiveSearchedTermChangeListener l){
        activeSearchedTermChangeListeners.remove(l);
    }
    
    public static void addSearchTermChangeListener(InputTermChangeListener l){
        inputTermChangeListeners.add(l);
    }
    
    public static void removeSearchTermChangeListener(InputTermChangeListener l){
        inputTermChangeListeners.remove(l);
    }
    
    public static void addBookChangeListener(BookChangeListener l){
        bookChangeListeners.add(l);
    }
    
    public static void removeBookChangeListener(BookChangeListener l){
        bookChangeListeners.remove(l);
    }
    
    public static void addListener(AppStateChangeListener l){
        
        listeners.add(l);
    }
    
    public static void removeListener(AppStateChangeListener l){
        
        listeners.remove(l);
    }
    
    private static void fireChange(AppState.Type prevType, AppState.Type newType){
        
        for(AppStateChangeListener l : listeners){
            l.appStateChanged(new AppStateChangeEvent(new AppState(), prevType, newType));
        }
    }
    
    private static void fireBookChange(Book prevBook, Book newBook){
        for(BookChangeListener l : bookChangeListeners){
            l.bookChanged(new BookChangeEvent(new AppState(), prevBook, newBook));
        }
    }
    
    private static void fireSearchTermChange(String prevTerm, String newTerm){
        for(InputTermChangeListener l : inputTermChangeListeners){
            l.searchTermChanged(new InputTermChangeEvent(new AppState(), prevTerm, newTerm));
        }
    }
    
    private static void fireActiveSearchTermChange(AdvancedTerm prevAdvTerm, AdvancedTerm newAdvTerm){
        for(ActiveSearchedTermChangeListener l : activeSearchedTermChangeListeners){
            l.activeSearchedTermChanged(new ActiveSearchedTermChangeEvent(new AppState(), prevAdvTerm, newAdvTerm));
        }
    }
    
    private static final List<AppStateChangeListener> listeners = new ArrayList<>();
    private static final List<BookChangeListener> bookChangeListeners = new ArrayList<>();
    private static final List<InputTermChangeListener> inputTermChangeListeners = new ArrayList<>();
    private static final List<ActiveSearchedTermChangeListener> activeSearchedTermChangeListeners = new ArrayList<>();
    private static final List<TICEditModeListener> TICEventListener = new ArrayList<>();
    private static final List<QuickAccessListener> quickAccessListeners = new ArrayList<>();
    
    private AppState(){}
    
    private static Type currentType = Type.SEARCH;
    private static Book currentlyActiveBook = Book.GRAYS_ANATOMY;
    private static String currentInputSpelling = "";
    private static AdvancedTerm currentActiveSearchedTerm = null;
    private static boolean termFound = false;
    
}
