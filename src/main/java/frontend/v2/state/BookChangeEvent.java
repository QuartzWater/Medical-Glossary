/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend.v2.state;

import book.bookpicker.Book;
import java.util.EventObject;

/**
 *
 * @author BRIN
 */
public class BookChangeEvent extends EventObject{
    
    private final Book prevBook;
    private final Book newBook;

    public BookChangeEvent(Object source, Book prevBook, Book newBook) {
        super(source);
        this.prevBook = prevBook;
        this.newBook = newBook;
    }

    public Book getPrevBook() {
        return prevBook;
    }

    public Book getNewBook() {
        return newBook;
    }
    
    
    
}
