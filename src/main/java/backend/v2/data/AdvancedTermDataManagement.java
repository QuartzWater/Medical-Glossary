/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.v2.data;

import backend.AppConfig;
import backend.v2.term.AdvancedHyperlink;
import backend.v2.term.AdvancedTerm;
import backend.v2.term.IllegalTermStateException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.function.Predicate;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;
import javax.swing.text.rtf.RTFEditorKit;

/**
 *
 * @author BRIN
 */
public final class AdvancedTermDataManagement {
    
    public static final String ALL_TERMS_FILE_NAME = "ALL_TERMS.termarray";
    public static final String TERM_RTF_EXTENSION = ".rtf";
    public static final String TERM_PROPERTIES_EXTENSION = "_properties.xml";
    
    private StandardRetry standardRetry;
    
    public static final int MAXIMUM_RETRY = 5;
    public static final long LEAST_RETRY_DELAY = 100L;
    public static final int RETRY_EXPONENT_INCREMENT = 2;
    private Set<String> allTerms;
    private Map<String, AdvancedTerm> termMap;
    
    private Set<String> malformedTerms;
    private Set<String> failedIOTerms;
    
    private Path globalPropertiesPath;

    public AdvancedTermDataManagement(Path globalPropPath) {
        this.globalPropertiesPath = globalPropPath;
        allTerms = new HashSet<>();
        termMap = new HashMap<>();
        malformedTerms = new HashSet<>();
        failedIOTerms = new HashSet<>();
        
        List<Predicate<Exception>> retryableExceptions = new ArrayList<>();
        retryableExceptions.add(
            e -> e instanceof java.nio.file.FileSystemException // For issues like file locking
        );
        
        standardRetry = new StandardRetry(retryableExceptions, MAXIMUM_RETRY, LEAST_RETRY_DELAY, RETRY_EXPONENT_INCREMENT);
    }

    public void setGlobalPropertiesPath(Path globalPropertiesPath) {
        this.globalPropertiesPath = globalPropertiesPath;
        
    }

    public Map<String, AdvancedTerm> getTermMap() {
        Map<String, AdvancedTerm> toReturn = new HashMap<>();
        toReturn.putAll(termMap);
        return toReturn;
    }
    
    public boolean contains(String termSpelling){
        return this.termMap.containsKey(termSpelling) || this.termMap.containsKey(termSpelling.toLowerCase());
    }
    
    public AdvancedTerm get(String termSpelling) throws IllegalTermStateException{
        if(this.termMap.containsKey(termSpelling)){
            return this.termMap.get(termSpelling);
        }
        else if (this.termMap.containsKey(termSpelling.toLowerCase())){
            return this.termMap.get(termSpelling.toLowerCase());
        }
        
        return null;
    }

    public List<String> getAllTerms() {
        List<String> toReturn = new ArrayList<>();
        toReturn.addAll(allTerms);
        return toReturn;
    }

    public Set<String> getFailedIOTerms() {
        Set<String> toReturn = new HashSet<>();
        toReturn.addAll(failedIOTerms);
        return toReturn;
    }

    public Set<String> getMalformedTerms() {
        Set<String> toReturn = new HashSet<>();
        toReturn.addAll(malformedTerms);
        return toReturn;
    }
    
    public void load() throws TermDataIOException{
        
        allTerms = new HashSet<>();
        termMap = new HashMap<>();
        malformedTerms = new HashSet<>();
        failedIOTerms = new HashSet<>();
        
        Path parent = this.globalPropertiesPath.getParent();
        Path ALL_TERMS_FILE = globalPropertiesPath.resolve(ALL_TERMS_FILE_NAME);
        
        allTerms = loadAllTermsFile(ALL_TERMS_FILE);
        termMap = initializeAdvancedTerms(parent);
        
    }
    
    private enum LoadStatus {
        SUCCESS,
        FAILED_IO,
        MALFORMED_DATA,
        FAILED_IO_AND_MALFORMED
    }
    
    private Set<String> loadAllTermsFile(Path allTermsFilePath) throws TermDataIOException{
        Set toReturn = new HashSet<>();
        
        standardRetry.execute(
                () -> {
                    
                    if(!Files.exists(this.globalPropertiesPath)){
                    Files.createDirectories(this.globalPropertiesPath);
                    }
                    if(!Files.exists(allTermsFilePath)){
                        Files.createFile(allTermsFilePath);
                    }
                    
                    try(BufferedReader reader = Files.newBufferedReader(allTermsFilePath)){

                        String line;
                        while((line = reader.readLine()) != null){

                            toReturn.add(line);
                        }

                    }
                    return null;
                },
                new TermDataIOException("Could not load AllTermsFile even after multiple retries!", TermDataIOException.TYPE.INITIAL_LOAD_FAILED)
        );
        
        return toReturn;
    }
    
    private Map<String, AdvancedTerm> initializeAdvancedTerms(Path parentPath){
        
        Map<String, AdvancedTerm> toReturnMap = new HashMap<>();
        
        for(String term : allTerms){
            
            LoadStatus loadStatus = initializeSingleAdvancedTerm(parentPath, term, toReturnMap);
            switch(loadStatus){
                
                case FAILED_IO_AND_MALFORMED -> {
                    
                    malformedTerms.add(term);
                    failedIOTerms.add(term);
                }
                
                case FAILED_IO -> failedIOTerms.add(term);
                
                case MALFORMED_DATA -> malformedTerms.add(term);
            }
        }
        
        int retry = 1;
        
        while(!failedIOTerms.isEmpty() && retry <= MAXIMUM_RETRY){
        
            Iterator<String> iterate = failedIOTerms.iterator();
            while(iterate.hasNext()){
                
                String failedIOTerm = iterate.next();
                LoadStatus loadStatus = initializeSingleAdvancedTerm(parentPath, failedIOTerm, toReturnMap);
                
                switch(loadStatus){
                 
                    case SUCCESS -> {
                        iterate.remove();
                        malformedTerms.remove(failedIOTerm);
                    }
                    
                    case FAILED_IO_AND_MALFORMED -> {
                        malformedTerms.add(failedIOTerm);
                    }
                    
                    case MALFORMED_DATA -> {
                        iterate.remove();
                        malformedTerms.add(failedIOTerm);
                    }
                    
                    case FAILED_IO -> {
                        
                    }
                }
            }
            retry++;
        }
        
        return toReturnMap;
    }
    
    private LoadStatus initializeSingleAdvancedTerm(Path parent, String spelling, Map<String, AdvancedTerm> termMap){
        boolean failedIO = false;
        boolean malformed = false;
        try {
            AdvancedTerm advancedTerm = new AdvancedTerm(spelling);

            Path RTF_FILE = parent.resolve(spelling + TERM_RTF_EXTENSION);

            StyledDocument doc = new DefaultStyledDocument();

            try(InputStream IN = Files.newInputStream(RTF_FILE, StandardOpenOption.READ)){

                RTFEditorKit kit = new RTFEditorKit();
                kit.read(IN, doc, 0);
                advancedTerm.setDefinition(doc);

            } catch (IOException ex) {
                System.getLogger(AdvancedTermDataManagement.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                failedIO = true;
            } catch (BadLocationException ex) {
                System.getLogger(AdvancedTermDataManagement.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }

            Path PROPERTIES_FILE = parent.resolve(spelling + TERM_PROPERTIES_EXTENSION);

            try(InputStream IN = Files.newInputStream(PROPERTIES_FILE, StandardOpenOption.READ)){

                Properties inputProp = new Properties();
                inputProp.loadFromXML(IN);

                advancedTerm.setSpelling(inputProp.getProperty(PropertyKey.ABSOLUTE_SPELLING));
                advancedTerm.setDefinitionKeywords(extract(PropertyKey.DEFINITION_KEYWORD, inputProp));
                advancedTerm.setSuperCategory(inputProp.getProperty(PropertyKey.SUPER_HEADING));
                advancedTerm.setMiddleCategory(inputProp.getProperty(PropertyKey.MIDDLE_HEADING));
                advancedTerm.setSubCategory(inputProp.getProperty(PropertyKey.SUB_HEADING));

                try{
                    advancedTerm.setPage(Integer.parseInt(inputProp.getProperty(PropertyKey.PAGE)));
                }
                catch(NumberFormatException e){
                    advancedTerm.setPage(1); //defaults to one.
                    System.out.println("ERROR IN ATDM");
                }

                advancedTerm.replaceHyperlinks(extractHyperlink(inputProp));
                termMap.put(spelling, advancedTerm);

            } catch (IOException ex) {
                System.getLogger(AdvancedTermDataManagement.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                failedIO = true;
            }

        } catch (IllegalTermStateException ex) {
            System.getLogger(AdvancedTermDataManagement.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            malformed = true;
        }
        
        if(failedIO && malformed){
            return LoadStatus.FAILED_IO_AND_MALFORMED;
        }
        else if(failedIO){
            return LoadStatus.FAILED_IO;
        }
        else if(malformed){
            return LoadStatus.MALFORMED_DATA;
        }
        else{
            return LoadStatus.SUCCESS;
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
    
    public void remove(AdvancedTerm advancedTerm) throws TermDataIOException{
        Path parent = this.globalPropertiesPath.getParent();
        
        removeFromMemory(advancedTerm, allTerms, termMap);
        flushAllTermsFile(globalPropertiesPath, allTerms);
        removeRTF(parent, advancedTerm);
        removeProperties(parent, advancedTerm);
    }
    
    private void removeRTF(Path parent, AdvancedTerm advancedTerm) throws TermDataIOException{
        Path rtfPath = parent.resolve(advancedTerm.getSpelling() + TERM_RTF_EXTENSION);
        
        standardRetry.execute(
                () -> {
                    
                    Files.delete(rtfPath);
                    return null;
                },
                new TermDataIOException("Old RTF File could not be deleted!", TermDataIOException.TYPE.RTF_FILE_DELETE_FAIL)
        );
        
    }
    
    private void removeProperties(Path parent, AdvancedTerm advancedTerm) throws TermDataIOException{
        Path propPath = parent.resolve(advancedTerm.getSpelling() + TERM_PROPERTIES_EXTENSION);
        
        standardRetry.execute(
                () -> {
                    
                    Files.delete(propPath);
                    return null;
                },
                new TermDataIOException("Old Properties File could not be deleted!", TermDataIOException.TYPE.PROPERTIES_FILE_DELETE_FAIL)
        );
    }
    
    private void removeFromMemory(AdvancedTerm advancedTerm, Set<String> allTermsList, Map<String, AdvancedTerm> advancedTermMap){
        allTermsList.remove(advancedTerm.getAbsoluteSpelling());
        advancedTermMap.remove(advancedTerm.getAbsoluteSpelling());
    }
    
    public void flush(AdvancedTerm oldAdvancedTerm, AdvancedTerm newAdvancedTerm) throws TermDataIOException{
        
        if(newAdvancedTerm == null){
            throw new IllegalArgumentException("New term state or the final term state that will be considered the updated state of old term can not be null");
        }
        
        if(oldAdvancedTerm == null){
            flushNEW(newAdvancedTerm);
        }
        else{
            flushUPDATE(oldAdvancedTerm, newAdvancedTerm);
        }
    }
    
    private void flushUPDATE(AdvancedTerm oldAdvancedTerm, AdvancedTerm newAdvancedTerm) throws TermDataIOException{
        
        Set<TermComparator.DIFFERENCE_TYPE> differenceType = TermComparator.compare(oldAdvancedTerm, newAdvancedTerm, TermComparator.ComparisionStrength.CONSERVATIVE);
        
        if(differenceType.contains(TermComparator.DIFFERENCE_TYPE.SPELLING)){
            flushRTF(globalPropertiesPath.getParent(), newAdvancedTerm);
            flushProperties(globalPropertiesPath.getParent(), newAdvancedTerm);
            removeFromMemory(oldAdvancedTerm, allTerms, termMap);
            flushInMemory(newAdvancedTerm, allTerms, termMap);
            try {
                flushAllTermsFile(globalPropertiesPath, allTerms);
            } catch (TermDataIOException ex) {
                removeFromMemory(newAdvancedTerm, allTerms, termMap);
                flushInMemory(oldAdvancedTerm, allTerms, termMap);
                System.getLogger(AdvancedTermDataManagement.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                throw new TermDataIOException(ex);
            }
            removeRTF(globalPropertiesPath.getParent(), oldAdvancedTerm);
            removeProperties(globalPropertiesPath.getParent(), oldAdvancedTerm);
        }
        else if(!differenceType.isEmpty()){
            
            if(differenceType.contains(TermComparator.DIFFERENCE_TYPE.DEFINITION_LENGTH) || differenceType.contains(TermComparator.DIFFERENCE_TYPE.DEFINITION_TEXT)){
                flushRTF(globalPropertiesPath.getParent(), newAdvancedTerm);
                flushProperties(globalPropertiesPath.getParent(), newAdvancedTerm);
            }
            else if(differenceType.contains(TermComparator.DIFFERENCE_TYPE.DEFINITION_STYLE)){
                flushRTF(globalPropertiesPath.getParent(), newAdvancedTerm);
            }
            else if(differenceType.contains(TermComparator.DIFFERENCE_TYPE.REFERENCE) 
                    || differenceType.contains(TermComparator.DIFFERENCE_TYPE.KEYWORD)
                    || differenceType.contains(TermComparator.DIFFERENCE_TYPE.HYPERLINK)
                    || differenceType.contains(TermComparator.DIFFERENCE_TYPE.PAGE)){
                flushProperties(globalPropertiesPath.getParent(), newAdvancedTerm);
            }
            
            flushInMemory(newAdvancedTerm, allTerms, termMap); // Will be successful ONLY when above block proceeds without any exception.
            
        }else{
            System.out.println("flushUpdate() method in AdvancedTermDataManagement found No Major differences... what are we working with lmao");
        }
        
    }
    
    private void flushNEW(AdvancedTerm newAdvancedTerm) throws TermDataIOException{
        Path parent = this.globalPropertiesPath.getParent();
        
        flushRTF(parent, newAdvancedTerm);
        flushProperties(parent, newAdvancedTerm);
        flushInMemory(newAdvancedTerm, allTerms, termMap);
        flushAllTermsFile(globalPropertiesPath, allTerms);
    }
    
    private void flushRTF(Path parent, AdvancedTerm advancedTerm) throws TermDataIOException{
        Path rtfPath = parent.resolve(advancedTerm.getSpelling() + TERM_RTF_EXTENSION);
        Path rtfPathTemp = parent.resolve("temp_" + advancedTerm.getSpelling() + TERM_RTF_EXTENSION);
        
        
        standardRetry.execute(
                () -> {
                    
                    try(OutputStream rtfOUT = Files.newOutputStream(rtfPathTemp, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)){
                        StyledDocument doc = advancedTerm.getStyledDefinition();
                        
                        RTFEditorKit kit = new RTFEditorKit();
                        try{
                            kit.write(rtfOUT, doc, 0, doc.getLength());
                            
                        }
                        catch(BadLocationException e){
                            System.getLogger(AdvancedTermDataManagement.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);
                            throw new TermDataIOException("Unexpected BadLocationException!: " + e.getMessage(), e, TermDataIOException.TYPE.RTF_FILE_BAD_LOCATION);
                        }

                    }
                    return null;
                },
                new TermDataIOException("Temporary RTF File could not be saved!", TermDataIOException.TYPE.RTF_FILE_WRITE_FAIL)
        );
        
        standardRetry.execute(
                () -> {
                    
                    try{
                        Files.move(rtfPathTemp, rtfPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);

                    }catch(AtomicMoveNotSupportedException ex){
                        System.getLogger(AdvancedTermDataManagement.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        throw new TermDataIOException("Atomic move is unsupported!", TermDataIOException.TYPE.TEMP_RTF_FILE_ATOMICMOVE_UNSUPPORTED);
                    } 
                    return null;
                },
                new TermDataIOException("RTF File could not be moved atomically!", TermDataIOException.TYPE.TEMP_RTF_FILE_ATOMICMOVE_FAIL)
        );
    }
    
    private void flushProperties(Path parent, AdvancedTerm newAdvancedTerm) throws TermDataIOException{
        
        Properties toFlushProperties = new Properties();
        toFlushProperties.setProperty(PropertyKey.SPELLING, newAdvancedTerm.getSpelling());
        toFlushProperties.setProperty(PropertyKey.ABSOLUTE_SPELLING, newAdvancedTerm.getAbsoluteSpelling());
        toFlushProperties.setProperty(PropertyKey.DEFINITION_TEXT, newAdvancedTerm.getPlainDefinition());
        toFlushProperties.setProperty(PropertyKey.SUPER_HEADING, newAdvancedTerm.getSuperCateogry());
        toFlushProperties.setProperty(PropertyKey.MIDDLE_HEADING, newAdvancedTerm.getMiddleCategory());
        toFlushProperties.setProperty(PropertyKey.SUB_HEADING, newAdvancedTerm.getSubCategory());
        toFlushProperties.setProperty(PropertyKey.PAGE, Integer.toString(newAdvancedTerm.getPage()));
        extract(toFlushProperties, PropertyKey.DEFINITION_KEYWORD, newAdvancedTerm.getDefinitionKeywords());
        extractHyperlink(toFlushProperties, newAdvancedTerm.getHyperlinks());
        
        Path propPath = parent.resolve(toFlushProperties.getProperty(PropertyKey.SPELLING) + TERM_PROPERTIES_EXTENSION);
        Path propPathTemp = parent.resolve("temp_" +toFlushProperties.getProperty(PropertyKey.SPELLING) + TERM_PROPERTIES_EXTENSION);
        
        standardRetry.execute(
                () -> {
                    
                    try(OutputStream propOUT = Files.newOutputStream(propPathTemp, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)){
                        // ENSURE COMPATILITY WITH OLDER BACKEND VERSION. IF AppConfig WERE TO CHANGE.
                        toFlushProperties.storeToXML(propOUT, "XML PROPERTIES FILE FOR MEDICAL GLOSSARY" + AppConfig.VERSION);

                    }
                    return null;
                },
                new TermDataIOException("Temporary Properties File could not be saved!", TermDataIOException.TYPE.PROPERTIES_FILE_WRITE_FAIL)
        );
        
        standardRetry.execute(
                () -> {
                    
                    try{
                        Files.move(propPathTemp, propPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);

                    }catch(AtomicMoveNotSupportedException ex){
                        System.getLogger(AdvancedTermDataManagement.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        throw new TermDataIOException("Atomic Move is Unsupported!: ", ex, TermDataIOException.TYPE.TEMP_PROPERTIES_FILE_ATOMICMOVE_UNSUPPORTED);
                    }
                    return null;
                },
                new TermDataIOException("Temporary Properties File could not be moved atomically!", TermDataIOException.TYPE.TEMP_PROPERTIES_FILE_ATOMICMOVE_FAIL)
        );
    }
    
    private void flushInMemory(AdvancedTerm advancedTerm, Set<String> allTermsList, Map<String, AdvancedTerm> advancedTermMap){
        allTermsList.add(advancedTerm.getAbsoluteSpelling());
        advancedTermMap.put(advancedTerm.getAbsoluteSpelling(), advancedTerm);
    }
    
    private void flushAllTermsFile(Path globalPropertiesPath, Set<String> allTermsList) throws TermDataIOException{
        Path temporaryAllTermsPath = globalPropertiesPath.resolve("temp_" +ALL_TERMS_FILE_NAME);
        Path allTermsPath = globalPropertiesPath.resolve(ALL_TERMS_FILE_NAME);
        
        
        standardRetry.execute(
                () -> {
                    
                    try(BufferedWriter allTermsWriter = Files.newBufferedWriter(temporaryAllTermsPath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)){

                        Iterator<String> iterate = allTermsList.iterator();
                        while(iterate.hasNext()){
                            allTermsWriter.write(iterate.next() + "\n");
                        }
                    } 
                    return null;
                },
                new TermDataIOException("Temporary All Terms File could not be saved!", TermDataIOException.TYPE.TEMP_ALLTERMS_FILE_WRITE_FAIL)
        );
        
        standardRetry.execute(
                () -> {
                    
                    try{
                        Files.move(temporaryAllTermsPath, allTermsPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);

                    }catch(AtomicMoveNotSupportedException ex){
                        System.getLogger(AdvancedTermDataManagement.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        throw new TermDataIOException("Atomic Move is Unsupported!", ex, TermDataIOException.TYPE.TEMP_ALLTERMS_FILE_ATOMICMOVE_UNSUPPORTED);
                    } 
                    return null;
                },
                new TermDataIOException("Temporary All Terms File could not be moved atomically!", TermDataIOException.TYPE.TEMP_ALLTERMS_FILE_ATOMICMOVE_FAIL)
        );
    }
    
    private void extract(Properties propToPut, String propertyKey, List<String> toTranslate){
        
        int index = 1;
        for(String property : toTranslate){
            propToPut.setProperty(propertyKey + Integer.toString(index), property);
            index++;
        }
    }
    
    private void extractHyperlink(Properties propToPut, List<AdvancedHyperlink> toTranslate){
        
        int index = 1;
        for(AdvancedHyperlink hyperlink : toTranslate){
            propToPut.setProperty(PropertyKey.HYPERLINK + Integer.toString(index), hyperlink.getHyperlink());
            propToPut.setProperty(PropertyKey.ENCAPSULATION + Integer.toString(index), hyperlink.getEncapsulation());
            index++;
        }
    }
}
