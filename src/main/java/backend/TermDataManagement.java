/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BRIN
 */
public class TermDataManagement {
    
    private final Path rootFolder;
    private final Path propertiesFolder;
    private final Path allTermsFile;
    private final Path temp_allTermsFile;
    
    private Map<String, Term> termMap;
    private List<String> allTermsList = new ArrayList<>();
    
    public TermDataManagement(Path root){
        
        System.out.println("Loading...");
        this.rootFolder = root;
        this.propertiesFolder = root.resolve("globalproperties");
        this.termMap = new HashMap<>();
        
        try{
            
            if(!Files.exists(propertiesFolder)){
                Files.createDirectories(propertiesFolder);
            }
            
            this.allTermsFile = propertiesFolder.resolve("ALLTERMS.properties");
            this.temp_allTermsFile = propertiesFolder.resolve("ALLTERMS_temp.properties");
            
            if(!Files.exists(allTermsFile)){
                Files.createFile(allTermsFile);
            }
            
            try(BufferedReader reader = Files.newBufferedReader(allTermsFile)){
            
            
            String readLine;
            while((readLine = reader.readLine()) != null){
                
                allTermsList.add(readLine);
            }
        } catch (IOException ex) {
            Logger.getLogger(TermDataManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            try(BufferedReader reader = Files.newBufferedReader(allTermsFile)){
                
                String readLine = "";
                String spelling = "";
                String definition = "";
                
                while((readLine = reader.readLine()) != null){
                    
                    
                    Path targetPath = root.resolve(readLine + ".term");
                    spelling = readLine;
                    try(BufferedReader readerDefinition = Files.newBufferedReader(targetPath)){
                        String readDefinitionLine;
                        while((readDefinitionLine = readerDefinition.readLine()) != null){
                            
                            definition = definition.concat(readDefinitionLine + "\n");
                        }
                        
                    }
                    
                    Path targetPropertiesPath = root.resolve(readLine + "_properties.properties");
                    Properties loadProp = new Properties();
                    try(BufferedInputStream inputProp = new BufferedInputStream(new FileInputStream(targetPropertiesPath.toFile()))){
                        
                        loadProp.load(inputProp);
                        
                        String chapter = loadProp.getProperty(PropertyKey.CHAPTER);
                        String majorTopic = loadProp.getProperty(PropertyKey.MAJOR_TOPIC);
                        String subtopic = loadProp.getProperty(PropertyKey.SUB_TOPIC);
                        String page = loadProp.getProperty(PropertyKey.PAGE);
                        
                        String hyperlink_1 = loadProp.getProperty(PropertyKey.HYPERLINK_1);
                        String hyperlink_2 = loadProp.getProperty(PropertyKey.HYPERLINK_2);
                        String hyperlink_3 = loadProp.getProperty(PropertyKey.HYPERLINK_3);
                        String hyperlink_4 = loadProp.getProperty(PropertyKey.HYPERLINK_4);
                        String hyperlink_5 = loadProp.getProperty(PropertyKey.HYPERLINK_5);
                        String hyperlink_6 = loadProp.getProperty(PropertyKey.HYPERLINK_6);
                        
                        String hyperlink_1_encap = loadProp.getProperty(PropertyKey.HYPERLINK_1_ENCAP);
                        String hyperlink_2_encap = loadProp.getProperty(PropertyKey.HYPERLINK_2_ENCAP);
                        String hyperlink_3_encap = loadProp.getProperty(PropertyKey.HYPERLINK_3_ENCAP);
                        String hyperlink_4_encap = loadProp.getProperty(PropertyKey.HYPERLINK_4_ENCAP);
                        String hyperlink_5_encap = loadProp.getProperty(PropertyKey.HYPERLINK_5_ENCAP);
                        String hyperlink_6_encap = loadProp.getProperty(PropertyKey.HYPERLINK_6_ECNAP);
                        
                       
                        
                        List<String> hyperlinkArray = new ArrayList<String>();
                        hyperlinkArray.add(hyperlink_1);
                        hyperlinkArray.add(hyperlink_2);
                        hyperlinkArray.add(hyperlink_3);
                        hyperlinkArray.add(hyperlink_4);
                        hyperlinkArray.add(hyperlink_5);
                        hyperlinkArray.add(hyperlink_6);
                        
                        List<String> hyperlinkEncapsulationArray = new ArrayList<String>();
                        hyperlinkEncapsulationArray.add(hyperlink_1_encap);
                        hyperlinkEncapsulationArray.add(hyperlink_2_encap);
                        hyperlinkEncapsulationArray.add(hyperlink_3_encap);
                        hyperlinkEncapsulationArray.add(hyperlink_4_encap);
                        hyperlinkEncapsulationArray.add(hyperlink_5_encap);
                        hyperlinkEncapsulationArray.add(hyperlink_6_encap);
                        
                        
                        
                        Term loadedTerm = new Term(spelling, definition, chapter, majorTopic, subtopic, Integer.parseInt(page), hyperlinkArray, hyperlinkEncapsulationArray);
                        
                        termMap.put(spelling, loadedTerm);
                    }
                }
            }
            
        }
        catch(IOException e){
            //TODO exception handling
            throw new UncheckedIOException("Failed to initialize TermDataManagement: Could not create properties folder or ALLTERMS file.", e);
        }
        
        System.out.println("Total terms loaded: " + Integer.toString(termMap.size()));
    }
    
    private void flushNEW(Term newTerm){
        
        boolean operationFailure = false;
        
        String spelling = newTerm.getSpelling();
        String definition = newTerm.getDefinition();
        
        
        
        Path definitionFile = rootFolder.resolve(spelling + ".term");
        
        try(BufferedWriter writer = Files.newBufferedWriter(temp_allTermsFile, StandardOpenOption.CREATE_NEW,
                                                              StandardOpenOption.APPEND)){
            
            for (int i = 0; i < allTermsList.size(); i++) {
                writer.write(allTermsList.get(i));
                writer.newLine();
            }
                writer.write(spelling);
                writer.newLine();
            
            }catch(IOException e){
                e.printStackTrace();
                operationFailure = true;
            }
        
            try(BufferedWriter writer = Files.newBufferedWriter(definitionFile, StandardOpenOption.CREATE,
                                                              StandardOpenOption.TRUNCATE_EXISTING)){
            
                writer.write(definition);
                // writer.flush(); is not needed because it automatically calls this method when closing the buffer stream.
            }
            catch(IOException e){
            
                e.printStackTrace();
                operationFailure = true;
            }    
        
            Path propertiesFile = rootFolder.resolve(spelling + "_properties.properties");
        
        
            Properties prop = new Properties();
            prop.setProperty(PropertyKey.CHAPTER, newTerm.getChapter());
            prop.setProperty(PropertyKey.MAJOR_TOPIC, newTerm.getMajorTopic());
            prop.setProperty(PropertyKey.SUB_TOPIC, newTerm.getSubtopic());
            prop.setProperty(PropertyKey.PAGE, Integer.toString(newTerm.getPage()));
            
            prop.setProperty(PropertyKey.HYPERLINK_1, newTerm.getHyperlinkByIndex(0) );
            prop.setProperty(PropertyKey.HYPERLINK_2, newTerm.getHyperlinkByIndex(1) );
            prop.setProperty(PropertyKey.HYPERLINK_3, newTerm.getHyperlinkByIndex(2) );
            prop.setProperty(PropertyKey.HYPERLINK_4, newTerm.getHyperlinkByIndex(3) );
            prop.setProperty(PropertyKey.HYPERLINK_5, newTerm.getHyperlinkByIndex(4) );
            prop.setProperty(PropertyKey.HYPERLINK_6, newTerm.getHyperlinkByIndex(5) );
            
            prop.setProperty(PropertyKey.HYPERLINK_1_ENCAP, newTerm.getHyperlinkEncapsulationByIndex(0) );
            prop.setProperty(PropertyKey.HYPERLINK_2_ENCAP, newTerm.getHyperlinkEncapsulationByIndex(1) );
            prop.setProperty(PropertyKey.HYPERLINK_3_ENCAP, newTerm.getHyperlinkEncapsulationByIndex(2) );
            prop.setProperty(PropertyKey.HYPERLINK_4_ENCAP, newTerm.getHyperlinkEncapsulationByIndex(3) );
            prop.setProperty(PropertyKey.HYPERLINK_5_ENCAP, newTerm.getHyperlinkEncapsulationByIndex(4) );
            prop.setProperty(PropertyKey.HYPERLINK_6_ECNAP, newTerm.getHyperlinkEncapsulationByIndex(5) );
            
            
        
            try(BufferedWriter writer = Files.newBufferedWriter(propertiesFile, StandardOpenOption.CREATE,
                                                              StandardOpenOption.TRUNCATE_EXISTING)){
            
                prop.store(writer, null);
            }
            catch(IOException e){
                e.printStackTrace();
                operationFailure = true;
            }   
            
            try{
                Files.move(temp_allTermsFile, allTermsFile, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
                
            }catch(AtomicMoveNotSupportedException e){
                e.printStackTrace();
                operationFailure = true;
            }catch(IOException e){
                e.printStackTrace();
                operationFailure = true;
            }finally{
                if(!operationFailure){
                    termMap.put(spelling, newTerm);
                    System.out.println("Successfully saved '" + newTerm.getSpelling() + "'");
                }
                else{
                    System.err.println("'" + newTerm.getSpelling() + "' couldn't be saved!");
                }
            }
    }
    
    private void flushUPDATE(Term oldTerm, Term newTerm){
        
        boolean operationFailure = false;
        
        String oldSpelling = oldTerm.getSpelling();
        String oldDefinition = oldTerm.getDefinition();
        
        String newSpelling = newTerm.getSpelling();
        String newDefinition = newTerm.getDefinition();
        
        Path oldDefinitionFile = rootFolder.resolve(oldSpelling + ".term");
        Path oldPropertiesFile = rootFolder.resolve(oldSpelling + "_properties.properties");
        Path newDefinitionFile = rootFolder.resolve(newSpelling + ".term");
        Path newPropertiesFile = rootFolder.resolve(newSpelling + "_properties.properties");
        Path temp_newDefinitionFile = rootFolder.resolve(newSpelling +"_temp.term");
        Path temp_newPropertiesFile = rootFolder.resolve(newSpelling +"_properties_temp.properties");
        
        
        
        
        
        int indexOF = allTermsList.indexOf(oldSpelling);
        allTermsList.set(indexOF, newSpelling);
        
        try(BufferedWriter writer = Files.newBufferedWriter(temp_allTermsFile)){
            
            for(int i = 0; i < allTermsList.size(); i++){
                
                writer.write(allTermsList.get(i));
                writer.newLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(TermDataManagement.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("ALLTERMS.properties couldn't be updated! ");
            operationFailure = true;
        }
        
        try(BufferedWriter writer = Files.newBufferedWriter(temp_newDefinitionFile)){
            
            Files.delete(oldDefinitionFile);
            Files.delete(oldPropertiesFile);
            writer.write(newDefinition);
        } catch (IOException ex) {
            Logger.getLogger(TermDataManagement.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Definition couldn't be updated! ");
            operationFailure = true;
        }
        
        
        Properties prop = new Properties();
        prop.setProperty(PropertyKey.CHAPTER, newTerm.getChapter());
        prop.setProperty(PropertyKey.MAJOR_TOPIC, newTerm.getMajorTopic());
        prop.setProperty(PropertyKey.SUB_TOPIC, newTerm.getSubtopic());
        prop.setProperty(PropertyKey.PAGE, Integer.toString(newTerm.getPage()));
        
        prop.setProperty(PropertyKey.HYPERLINK_1, newTerm.getHyperlinkByIndex(0) );
        prop.setProperty(PropertyKey.HYPERLINK_2, newTerm.getHyperlinkByIndex(1) );
        prop.setProperty(PropertyKey.HYPERLINK_3, newTerm.getHyperlinkByIndex(2) );
        prop.setProperty(PropertyKey.HYPERLINK_4, newTerm.getHyperlinkByIndex(3) );
        prop.setProperty(PropertyKey.HYPERLINK_5, newTerm.getHyperlinkByIndex(4) );
        prop.setProperty(PropertyKey.HYPERLINK_6, newTerm.getHyperlinkByIndex(5) );
        
        prop.setProperty(PropertyKey.HYPERLINK_1_ENCAP, newTerm.getHyperlinkEncapsulationByIndex(0) );
        prop.setProperty(PropertyKey.HYPERLINK_2_ENCAP, newTerm.getHyperlinkEncapsulationByIndex(1) );
        prop.setProperty(PropertyKey.HYPERLINK_3_ENCAP, newTerm.getHyperlinkEncapsulationByIndex(2) );
        prop.setProperty(PropertyKey.HYPERLINK_4_ENCAP, newTerm.getHyperlinkEncapsulationByIndex(3) );
        prop.setProperty(PropertyKey.HYPERLINK_5_ENCAP, newTerm.getHyperlinkEncapsulationByIndex(4) );
        prop.setProperty(PropertyKey.HYPERLINK_6_ECNAP, newTerm.getHyperlinkEncapsulationByIndex(5) );
       
        try(BufferedWriter writer = Files.newBufferedWriter(temp_newPropertiesFile)){
            
            prop.store(writer, null);
        } catch (IOException ex) {
            Logger.getLogger(TermDataManagement.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Term specific properties couldn't be updated! ");
            operationFailure = true;
        }
        
        try{
        Files.move(temp_newDefinitionFile, newDefinitionFile, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        Files.move(temp_newPropertiesFile, newPropertiesFile, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        Files.move(temp_allTermsFile, allTermsFile, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        }
        catch(AtomicMoveNotSupportedException e){
            
            e.printStackTrace();
            operationFailure = true;
        }
        catch(IOException e){
            
            e.printStackTrace();
            operationFailure = true;
        }
        
        if (!oldSpelling.equals(newSpelling) && !operationFailure) {
            termMap.remove(oldSpelling); // Remove old entry if spelling changed
        }
        if(!operationFailure){
            termMap.put(newSpelling, newTerm); // Add/Update with new term object
            System.out.println("Successfully updated from '" + oldSpelling + "' to '" + newTerm.getSpelling() + "'");
        }else{
            System.err.println("'" + oldSpelling + "' to '" + newTerm.getSpelling() + "' change was not executed properly!");
        }
        
    }
    
    public void flush(Term oldTerm, Term newTerm){
        Objects.requireNonNull(newTerm, "New term can never be null");
        
        
        if(oldTerm == null){
            
            
            flushNEW(newTerm);
        }
        else if(!oldTerm.equals(newTerm)){
            
            flushUPDATE(oldTerm, newTerm);
        }
        
    }
    
    public boolean contains(String inputString){
        
        return termMap.containsKey(inputString);
    }
    
    public Term getTerm(String inputString){
        
        return termMap.get(inputString);
    }
   
}

   
