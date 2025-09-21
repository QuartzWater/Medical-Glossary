/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.v2.search;

import book.bookpicker.Book;
import java.awt.Color;

/**
 *
 * @author BRIN
 */
public record SuggestionElement(String suggestionString, Book book, Color specialColor){
    
    public SuggestionElement(String suggestionString, Book originalBook) {
        this(suggestionString, originalBook, null);
    }
};
