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
public class bookConstructor {
 
    public static List<Book> getBooksByYear(int year){
        
        List<Book> bookList = new ArrayList<>();
        
        switch(year){
            
            case 1 :{
                 bookList.add(Book.GRAYS_ANATOMY);
                 // TODO: ADD PHYSIO & BIOCHEM
                 return bookList;
            }
            
            
            
            default: {
                return null;
            }
        }
    }
}
