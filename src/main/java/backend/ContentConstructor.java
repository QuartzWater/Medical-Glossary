/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import book.bookpicker.Book;
import java.util.Map;

/**
 *
 * @author BRIN
 */
public class ContentConstructor {
    
    public static Map<Integer, String[]> getEverythingByPage(int page, Book bookName){
        
        switch (bookName) {
            case Book.GRAYS_ANATOMY: {
                
                return book.GraysAnatomy.Content.THE_BODY.getEverythingByPage(page);
            }
            
            case Book.HARPERS_ILLUSTRATED_BIOCHEMISTRY :{
                
                return book.HarpersIllustratedBiochemistry.ContentFactory.getEverythingByPage(page);
            }
            
            case Book.GUYTON_AND_HALL_TEXTBOOK_OF_MEDICAL_PHYSIOLOGY : {
                
                return book.GuytonAndHallTextBookOfMedicalPhysiology.ContentFactory.getEverythingByPage(page);
            }
                
            default:
                throw new AssertionError("Invalid Book Name");
        }
    }
}
