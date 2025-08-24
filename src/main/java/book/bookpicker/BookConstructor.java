/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book.bookpicker;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BRIN
 */
public class BookConstructor {
 
    public static List<Book> getBooksByYear(int year){
        
        List<Book> bookList = new ArrayList<>();
        
        switch(year){
            
            case 1 :{
                 bookList.add(Book.GRAYS_ANATOMY);
                 bookList.add(Book.HARPERS_ILLUSTRATED_BIOCHEMISTRY);
                 bookList.add(Book.GUYTON_AND_HALL_TEXTBOOK_OF_MEDICAL_PHYSIOLOGY);
                 // TODO: ADD PHYSIO & BIOCHEM
                 return bookList;
            }
            
            
            
            case 2: {
                // TODO ADD MORE BOOKS
                return bookList;
            }
            
            case 3: {
                // TODO ADD MORE BOOKS
                return bookList;
            }
            
            case 4: {
                // TODO ADD MORE BOOKS
                return bookList;
            }
            
            default: return bookList;
        }
    }
}
