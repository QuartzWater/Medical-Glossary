/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book.bookpicker;

import backend.AppConfig;
import backend.ColorScheme;
import backend.ContentConstructor;
import backend.HeadingModel;
import backend.PropertyKey;
import backend.TermDataManagement;
import backend.v2.data.AdvancedTermDataManagement;
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
            new HeadingModel("Chapter", "Major Topic", "Subtopic"),
            new ColorScheme(new Color(27,36,45), new Color(255,204,51), new Color(233,179,15), new Color(57,75,92)),
            new AfterEventCode(){
                
                @Override
                public void runCode(){
                    
                    System.out.println("Initialized Gray's Anatomy");
                    SourceFrame sf = SourceFrame.generateInstance(GRAYS_ANATOMY);
                    sf.setVisible(true);
                    sf.requestFocusInWindow();
                    
                }
            }
            
    ),
    
    HARPERS_ILLUSTRATED_BIOCHEMISTRY(
            "Harper's Illustrated Biochemistry", 
            "Harper's",
            "HarpersIllustratedBiochemistry",
            new HeadingModel("Section", "Chapter", "Subtopic"),
            new ColorScheme(new Color(27,36,45), new Color(25, 162, 224), new Color(22, 144, 199), new Color(57,75,92)),
            new AfterEventCode(){
                
                @Override
                public void runCode(){
                    
                    System.out.println("Initialized Harper's Illustrated Biochemistry");
                    SourceFrame sf = SourceFrame.generateInstance(HARPERS_ILLUSTRATED_BIOCHEMISTRY);
                    sf.setVisible(true);
                    sf.requestFocusInWindow();
                    
                }
            }
    ),
    
    GUYTON_AND_HALL_TEXTBOOK_OF_MEDICAL_PHYSIOLOGY(
            "Guyton and Hall - Textbook of Medical Physiology",
            "G&H",
            "TextbookOfMedicalPhysiology",
            new HeadingModel("Section", "Chapter", "Subtopic"),
            new ColorScheme(new Color(27,36,45), new Color(24, 219, 164), new Color(21, 189, 141), new Color(57,75,92)),
            new AfterEventCode(){
                
                @Override
                public void runCode(){
                    
                    System.out.println("Initialized Guyton and Hall - Textbook of Medical Physiology");
                    SourceFrame sf = SourceFrame.generateInstance(GUYTON_AND_HALL_TEXTBOOK_OF_MEDICAL_PHYSIOLOGY);
                    sf.setVisible(true);
                    sf.requestFocusInWindow();
                    
                }
            }
            
    );
    
    // CONSTANTS DEFINTION END
    
    
    
    private final String title;
    private final String shortHandTitle;
    private final Path rootBookPath;
    private final AfterEventCode actionCode;
    
    private  TermDataManagement tdm;
    private final Path rootPath = Paths.get("rep").resolve("Medical-Glossary").resolve("books");;
    
    private final ColorScheme colorScheme;
    private final HeadingModel headingModel;
    
    private final AdvancedTermDataManagement atdm;

    private Book(String title, String shortHandTitle, String bookDirectoryName, HeadingModel headingModel, ColorScheme colorScheme, AfterEventCode actionCode){
        
        this.title = title;
        this.colorScheme = colorScheme;
        this.shortHandTitle = shortHandTitle;
        this.actionCode = actionCode;
        this.headingModel = headingModel;
       // TODO: Remove all fluff
       
        String directory = rootPath.toString();
        String curatedDir = directory.replace("rep", AppConfig.getStorageLocation().toString()); 
        rootBookPath = Paths.get(curatedDir).resolve(bookDirectoryName);
        atdm = new AdvancedTermDataManagement(rootBookPath.resolve("globalproperties"));
    }
    
    public String getTitle(){
        
        return this.title;
    }
    
    public String getShortHandTitle(){
        
        return this.shortHandTitle;
    }
    
    public TermDataManagement getTDM(){
        
        this.tdm = new TermDataManagement(this, rootBookPath);
        return this.tdm;
    }
    
    public AdvancedTermDataManagement getATDM(){
        
        return this.atdm;
    }
    
    public ColorScheme getColorScheme(){
        
        return this.colorScheme;
    }
    
    public HeadingModel getHeadingModel(){
        
        return this.headingModel;
    }
    
    // Following methods will only be used by bookPickerFrame
    
    public AfterEventCode getActionCode(){
        
        return this.actionCode;
    }
    
    // End of methods specific to bookPickerFrame
    
    public  Map<Integer, String[]> getEverythingByPage(int page){
        
        return ContentConstructor.getEverythingByPage(page, this);
    }
    
    
}
