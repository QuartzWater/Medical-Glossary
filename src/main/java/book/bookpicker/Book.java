/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book.bookpicker;

import backend.ContentConstructor;
import backend.TermDataManagement;
import legacy.AfterEventCode;
import legacy.ButtonActionCode;
import frontend.SourceFrame;
import java.awt.Color;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

/**
 *
 * @author BRIN
 */
public enum Book{
    
    // CONSTANTS DEFINITION START
    
    GRAYS_ANATOMY(
            "Gray's Anatomy", 
            "Gray's",
            "GraysAnatomy",
            new Color[]{
                
                new Color(27,36,45), //Default
                new Color(255,204,51), //Hover
                new Color(233,179,15) //Pressed
            },
            
            new ButtonActionCode(){
                
                @Override
                public void runCode(){
                    
                    System.out.println("THIS RAN");
                    new SourceFrame(Book.GRAYS_ANATOMY).setVisible(true);
                }
            }
            
    );
    
    // CONSTANTS DEFINTION END
    
    
    
    private final String title;
    private final String shortHandTitle;
    private final Path rootBookPath;
    private final ButtonActionCode actionCode;
    
    private  TermDataManagement tdm;
    private final Path rootPath = Paths.get("rep").resolve("MedicalGlossary").resolve("books");;
    
    private final Color[] colorScheme;
    

    private Book(String title, String shortHandTitle, String bookDirectoryName, Color[] colorScheme, ButtonActionCode actionCode){
        
        this.title = title;
        this.colorScheme = colorScheme;
        this.shortHandTitle = shortHandTitle;
        this.actionCode = actionCode;
       // TODO: Remove all fluff
        
        String directory = rootPath.toString();
        String curatedDir = directory.replace("rep", "C:\\"); // TODO Remove this when you achieve cross platform compatibility
        System.out.println(curatedDir);
        
        rootBookPath = Paths.get(curatedDir).resolve(bookDirectoryName);
        directory = rootBookPath.toString();
        System.out.println(directory);
        
        
    }
    
    public String getTitle(){
        
        return this.title;
    }
    
    public String getShortHandTitle(){
        
        return this.shortHandTitle;
    }
    
    public TermDataManagement getTDM(){
        
        this.tdm = new TermDataManagement(rootBookPath);
        return this.tdm;
    }
    
    public Color[] getColorScheme(){
        
        return this.colorScheme;
    }
    
    // Following methods will only be used by bookPickerFrame
    
    public ButtonActionCode getActionCode(){
        
        return this.actionCode;
    }
    
    // End of methods specific to bookPickerFrame
    
    public  Map<Integer, String[]> getEverythingByPage(int page){
        
        return ContentConstructor.getEverythingByPage(page, this);
    }
    
    
}
