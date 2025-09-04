/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.v2.data;

import backend.v2.term.AdvancedHyperlink;
import backend.v2.term.AdvancedTerm;
import backend.v2.term.IllegalTermStateException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

/**
 *
 * @author BRIN
 */
public final class AdvancedTermDataManagement {
    
    public static final String ALL_TERMS_FILE_NAME = "ALL_TERMS.xml";
    public static final String TERM_RTF_EXTENSION = ".rtf";
    public static final String TERM_PROPERTIES_EXTENSION = "_properties.xml";
    
    private List<String> allTerms;
    private Map<String, AdvancedTerm> termMap;
    
    private Path globalPropertiesPath;
    
    public void load(Path globalPropPath) throws TermDataIOException{
        
        this.globalPropertiesPath = globalPropPath;
        
        allTerms = new ArrayList<>();
        termMap = new HashMap<>();
        Path parent = this.globalPropertiesPath.getParent();
        Path ALL_TERMS_FILE = this.globalPropertiesPath.resolve(ALL_TERMS_FILE_NAME);
        
        try{
            if(!Files.exists(this.globalPropertiesPath)){
                Files.createDirectories(this.globalPropertiesPath);
            }
            
            if(!Files.exists(ALL_TERMS_FILE)){
                Files.createFile(ALL_TERMS_FILE);
            }
        }
        catch(IOException e){
            throw new TermDataIOException(e, TermDataIOException.TYPE.IO_EXCEPTION);
        }
        
        try(BufferedReader reader = Files.newBufferedReader(ALL_TERMS_FILE)){
            
            String line;
            while((line = reader.readLine()) != null){
                
                allTerms.add(line);
            }
            
        } catch (IOException ex) {
            System.getLogger(AdvancedTermDataManagement.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
        for(String term : allTerms){
            
            try {
                AdvancedTerm advancedTerm = new AdvancedTerm(term);
                
                Path RTF_FILE = parent.resolve(term + TERM_RTF_EXTENSION);
                
                JTextPane jtx = new JTextPane();
                StyledDocument doc = jtx.getStyledDocument();

                try(InputStream IN = Files.newInputStream(RTF_FILE, StandardOpenOption.READ)){

                    RTFEditorKit kit = new RTFEditorKit();
                    kit.read(IN, doc, 0);
                    advancedTerm.setDefinition(doc);

                } catch (IOException ex) {
                    System.getLogger(AdvancedTermDataManagement.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                } catch (BadLocationException ex) {
                    System.getLogger(AdvancedTermDataManagement.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }

                Path PROPERTIES_FILE = parent.resolve(term + TERM_PROPERTIES_EXTENSION);

                try(InputStream IN = Files.newInputStream(PROPERTIES_FILE, StandardOpenOption.READ)){

                    Properties inputProp = new Properties();
                    inputProp.loadFromXML(IN);

                    advancedTerm.setSpelling(inputProp.getProperty(PropertyKey.ABSOLUTE_SPELLING));
                    advancedTerm.setDefinitionKeywords(extract(PropertyKey.DEFINITION_KEYWORD, inputProp));
                    advancedTerm.setSuperCategory(inputProp.getProperty(PropertyKey.SUPER_HEADING));
                    advancedTerm.setMiddleCategory(inputProp.getProperty(PropertyKey.MIDDLE_HEADING));
                    advancedTerm.setSubCategory(inputProp.getProperty(PropertyKey.SUB_HEADING));
                    advancedTerm.setPage(Integer.parseInt(inputProp.getProperty(PropertyKey.PAGE)));
                    advancedTerm.replaceHyperlinks(extractHyperlink(inputProp));
                    
                } catch (IOException ex) {
                    System.getLogger(AdvancedTermDataManagement.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
             
            } catch (IllegalTermStateException ex) {
                System.getLogger(AdvancedTermDataManagement.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        
    }
    private List<String> extract(String propertyKey, Properties prop){
        List<String> toReturn = new ArrayList<>();
        int index = 1;
        String property;
        do{
            property = prop.getProperty(propertyKey + Integer.toString(index));
            if(property != null){
                toReturn.add(property);
            }
            index++;
        }while (property != null);
        
        return toReturn;
    }
    
    private List<AdvancedHyperlink> extractHyperlink(Properties prop) throws IllegalTermStateException{
        List<AdvancedHyperlink> toReturn = new ArrayList<>();
        int index = 1;
        String hyperlink;
        String encapsulation;
        do{
           hyperlink = prop.getProperty(PropertyKey.HYPERLINK + Integer.toString(index));
           encapsulation = prop.getProperty(PropertyKey.ENCAPSULATION + Integer.toString(index));
           if(hyperlink != null && encapsulation != null){
               try {
                   toReturn.add(new AdvancedHyperlink(hyperlink, encapsulation));
               } catch (IllegalTermStateException ex) {
                   System.getLogger(AdvancedTermDataManagement.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                   throw new IllegalTermStateException(ex.getMessage() + ": at hyperlink " + Integer.toString(index), ex, ex.getType());
               }
           }
           index++;
        }while(hyperlink != null && encapsulation != null);
        
        return toReturn;
    }
}
