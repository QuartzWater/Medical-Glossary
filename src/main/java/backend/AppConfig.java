/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import backend.v2.search.SearchTermAlgorithm;
import book.bookpicker.Book;
import frontend.bookPickerFrame;
import frontend.v2.state.AppState;
import frontend.v2.window.BookPicker;
import frontend.v2.window.Startup;
import frontend.v2.window.mainFrame;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * This class is responsible for managing the application's configuration,
 * including user-specific settings, version information, and initial setup, etc.
 * It's designed as a utility class with static members and methods to be
 * accessed globally throughout the application.
 * * @author QuartzWater
 */
public class AppConfig {
    
    public static final Color FINAL_SYSTEM_TERM_CREATION_HIGHLIGHT = new Color(0,102,255);
    
    public static final String POM_PROPERTIES_VIA_CLASSLOADER;
    public static final Properties INT_PROJECT_CONFIG_PROPERTIES;
    public static final String VERSION;
    
    public static final String USER_HOME;
    public static final String EXT_APP_CONFIG_HOME;
    public static final String EXT_APP_CONFIG_FILE_NAME;
    public static final Path EXT_APP_CONFIG_PROPERTIES_PATH;
    
    // EXT SETTINGS AND KEY VALUES ----------------------
    private static final Properties EXT_APP_CONFIG_PROPERTIES;
    private static final Properties EXT_APP_CONFIG_PROPERTIES_DUPLICATE;
    public interface ExternalPropertiesKey{
        public static final String STORAGE_PATH = "storage.path";
        public static final String SEARCH_DEPTH = "search.depth";
    }
    
    // END OF EXT PROPERTIES SECTION
    
    // DEFAULT EXT PROPERTIES 
    public static final Path DEFAULT_STORAGE_PATH;
    // END OF DEFAULT EXT PROPERTIES
    
    // don't ask why is everything in the static block. I know its somewhat problematic and can cause subtle problems
    // but i can NOT be bothered to refactor this rn.....
    // It works right now as it is supposed to work...
    static {     
        Runtime rt = Runtime.getRuntime();
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("MEMORY ALLOCATION *BEFORE* EVEN MAIN CLASS IS LOADED: ");
        System.out.println("Max heap: " + rt.maxMemory() / (1024*1024) + " MB (Its ~25% of your maximum System Memory)");
        System.out.println("Total heap: " + rt.totalMemory() / (1024*1024) + " MB");
        System.out.println("Free heap: " + rt.freeMemory() / (1024*1024) + " MB");
        System.out.println("---------------------------------------------------------------------------------------");
        
        /* Set the Windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(bookPickerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bookPickerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bookPickerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bookPickerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        Startup startup = new Startup(null, false);
        System.out.println("HERES A STARTUP SCREEN THAT ACTUALLY DOES NOT LOAD ANYTHING");
        startup.setVisible(true);

        EXT_APP_CONFIG_HOME = ".medicalglossary";
        EXT_APP_CONFIG_FILE_NAME = "config.properties";
        // ALWAYS CHANGE THIS IF YOU CHANGE <group-id> or <artifact-id> OF THE PROJECT
        POM_PROPERTIES_VIA_CLASSLOADER = "META-INF/maven/quartzwater/Medical-Glossary/pom.properties";
        // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^<group-id>^^^<artifact-id>^^^^^^^^^^^^^^^^^^^
        
        INT_PROJECT_CONFIG_PROPERTIES = new Properties();
        try (InputStream loadprop = AppConfig.class.getClassLoader().getResourceAsStream(POM_PROPERTIES_VIA_CLASSLOADER)) {
            if (loadprop == null) {
                System.err.println("Critical ResourceInputStream was found to be null.");
                System.out.println("This might be because of malformed JAR file or the project is in development environment. Fallback to default Version.");
            }else{
                INT_PROJECT_CONFIG_PROPERTIES.load(loadprop);
                System.out.println("Project Configuration loaded successfully!");
            }
            
        } catch (IOException e) {
            System.err.println("Resource not found: " + POM_PROPERTIES_VIA_CLASSLOADER);
            System.err.println("Project Configuration could not be loaded! The application will now close with exit code 1 : " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Critical error during loading of project configuration occured! Application will now close!");
            System.exit(1);
        }
        
        String setVersion = INT_PROJECT_CONFIG_PROPERTIES.getProperty("version");
        if(setVersion == null){
            VERSION = "v0.2.DEV"; // for development : default version
        }
        else{
            VERSION = "v" + setVersion;
        }
        
        startup.setVersion(VERSION);
        
        System.out.println("Application Version: " + VERSION);
        
        // EXTERNAL CONFIGURATION INITILZATION::::::::::::::::::::::::::::::::::
        
        USER_HOME = System.getProperty("user.home");
        EXT_APP_CONFIG_PROPERTIES_PATH = Paths.get(USER_HOME).resolve(EXT_APP_CONFIG_HOME).resolve(EXT_APP_CONFIG_FILE_NAME);
        DEFAULT_STORAGE_PATH = Paths.get(USER_HOME).resolve("Medical-Glossary");
        
        /*
        // REMOVE THIS CODE IN FINAL SHIPMENT
        try {
            Files.delete(AppConfig.EXT_APP_CONFIG_PROPERTIES_PATH);
        } catch (IOException ex) {
            System.err.println("No such file : TODO REMOVE THIS CODE : AppConfig.java : line 104");
        }
        // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        */
        
        Properties extProp = AppConfig.getExternalConfiguration();
        
        if(extProp == null){
            
            extProp = new Properties();
            // First time running, prompt the user
            JOptionPane.showMessageDialog(null, "Please select the location where you would like to save the terms.");
            
            boolean isARoot = true;
            File[] roots = File.listRoots();

            while (isARoot) {                
                isARoot = false;
                JFileChooser chooser = new JFileChooser();
            
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int option = chooser.showOpenDialog(null);

                if (option == JFileChooser.APPROVE_OPTION) {
                    File selectedDir = chooser.getSelectedFile();
                    
                    for(File f : roots){
                        if(selectedDir.equals(f)){
                            isARoot = true;
                        }
                    }
                    String storagePath = selectedDir.getAbsolutePath();
                    

                    if(!isARoot){
                        extProp.setProperty(AppConfig.ExternalPropertiesKey.STORAGE_PATH, storagePath);
                        extProp.setProperty(AppConfig.ExternalPropertiesKey.SEARCH_DEPTH, "DEEP_SEARCH");
                        try {
                            AppConfig.setExternalConfiguration(extProp);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(
                                null, 
                                "Error encountered while trying to create the configuration file: \njava.io.IOException: " + ex.getMessage(), 
                                "Error", 
                                JOptionPane.ERROR_MESSAGE);
                            System.getLogger(AppConfig.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        }

                        JOptionPane.showMessageDialog(null, "Welcome to Medical Glossary " + VERSION + "!");
                    }
                    else{
                        
                        JOptionPane.showMessageDialog(null, "Selected directory was found to be root directory! This may cause permission issues. Please select another directory");
                    }
                } else {
                    // User cancelled -> exit application
                    System.exit(0);
                }
            }
            
        }

        EXT_APP_CONFIG_PROPERTIES = extProp;
        EXT_APP_CONFIG_PROPERTIES_DUPLICATE = new Properties();
        EXT_APP_CONFIG_PROPERTIES_DUPLICATE.putAll(EXT_APP_CONFIG_PROPERTIES);
        
        
        System.out.println("ALL PROPERTIES ARE LOADED AND NOW THREAD WILL SLEEP FOR 2 SECONDS");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            System.getLogger(AppConfig.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        System.out.println("NOW THE STARTUP SCREEN WILL BECOME INVISIBLE");
        startup.setVisible(false);
        
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("MEMORY ALLOCATION *AFTER* MAIN CLASS AND RELEVANT PROPERTIES ARE COMPLETELY LOADED: ");
        System.out.println("Max heap: " + rt.maxMemory() / (1024*1024) + " MB (Its ~25% of your maximum System Memory)");
        System.out.println("Total heap: " + rt.totalMemory() / (1024*1024) + " MB");
        System.out.println("Free heap: " + rt.freeMemory() / (1024*1024) + " MB");
        System.out.println("---------------------------------------------------------------------------------------");
    }
    
    /** Gets external configuration stored in predetermined configuration location
     * 
     * @return The loaded Properties Object from external configuration file. returns null if the configuration file was not found (IOException was thrown)
     *
     */
    private static Properties getExternalConfiguration(){
        
        try {
            Properties prop;
            try (InputStream newInputStream = Files.newInputStream(EXT_APP_CONFIG_PROPERTIES_PATH)) {
                prop = new Properties();
                prop.load(newInputStream);
            }
            return prop;
            
        } catch (IOException ex) {
            return null;
        }
        
    }
    
    /** Saves the configuration specified by run-time of application as a .properties file  to an external and predetermined configuration location.
     * 
     * @param extProp The final configuration property to be saved.
     */
    private static void setExternalConfiguration(Properties extProp) throws IOException{
        try {
            
            Files.createDirectories(EXT_APP_CONFIG_PROPERTIES_PATH.getParent());
            try (OutputStream newOutputStream = Files.newOutputStream(EXT_APP_CONFIG_PROPERTIES_PATH)) {
                extProp.store(newOutputStream, "Application Configuration File");
            }
            
        } catch (IOException ex) {
            System.err.println("Configuration file could not be created: " + ex.getMessage());
            throw new IOException("Configuration file could not be created: " + ex.getMessage());
        }
    }
    
    /**
     * Gets the storage location for the application's data.
     * <p>
     * This method retrieves the path from the in-memory configuration. It checks if the
     * path exists and, if not, prints an error and falls back to a default location.
     * The default location is based on "user.home" property of system properties.
     * </p>
     * @return The Path object representing the application's data storage location.
     */
    public static final Path getStorageLocation(){
        String storage = EXT_APP_CONFIG_PROPERTIES.getProperty(ExternalPropertiesKey.STORAGE_PATH);
        Path storage_path = Path.of(storage);
        if(storage_path.toFile().exists()){
            return storage_path;
        }
        else {
            System.err.println("Critical Storage Path was found to not exist! Fallback to default path");
            return DEFAULT_STORAGE_PATH;
        }
    }
    
    
    /**
     * Sets the storage location for the application's data.
     * <p>
     * This method updates the storage path in the in-memory configuration properties.
     * The changes will not be saved to the file until {@link #storeUserPropertiesToFile()} is called.
     * </p>
     * @param location The new Path object representing the desired storage location.
     */
    public static final void setStorageLocation(Path location){
     
        EXT_APP_CONFIG_PROPERTIES_DUPLICATE.setProperty(ExternalPropertiesKey.STORAGE_PATH, location.toString());
    }
    
    /**
     * Checks if Storage Path Property was changed.
     *
     * @return false if the original Property Object and duplicate Property Object have same key-value binding for (@link #ExternalPropertiesKey.STORAGE_PATH) otherwise true.
     */
    public static boolean storageLocationPropertyChanged(){
        
        if(EXT_APP_CONFIG_PROPERTIES.getProperty(ExternalPropertiesKey.STORAGE_PATH).equals(EXT_APP_CONFIG_PROPERTIES_DUPLICATE.getProperty(ExternalPropertiesKey.STORAGE_PATH))){
            return false;
        }
        else{
            return true;
        }
    }
    
    // This method performs the move of all files from old directory to new directory.
    private static void transferStorage() throws IOException{
        
        Path oldPath = Paths.get(EXT_APP_CONFIG_PROPERTIES.getProperty(ExternalPropertiesKey.STORAGE_PATH)).resolve("Medical-Glossary");
        Path newPath = Paths.get(EXT_APP_CONFIG_PROPERTIES_DUPLICATE.getProperty(ExternalPropertiesKey.STORAGE_PATH)).resolve("Medical-Glossary");
        
        
        // Check if the source directory exists
        if (Files.exists(oldPath)) {
            // Perform the atomic move
            Files.move(oldPath, newPath, StandardCopyOption.ATOMIC_MOVE);
            System.out.println("Directory moved successfully.");
        } else {
            System.out.println("Source directory does not exist.");
        }
        
    }
    
    /**
     * Gets the "search depth" setting.
     * <p>
     * The method retrieves the search depth from the in-memory configuration properties.
     * It handles cases where the property is missing, empty, or an invalid value,
     * in which case it defaults to {@code SearchTermAlgorithm.SearchType.DEEP_SEARCH}.
     * </p>
     * @return The {@code SearchTermAlgorithm.SearchType} representing the default search depth.
     */
    public static final SearchTermAlgorithm.SearchType getSearchDepth(){
        String searchType = EXT_APP_CONFIG_PROPERTIES.getProperty(ExternalPropertiesKey.SEARCH_DEPTH);
        
        if(searchType == null || searchType.isEmpty()){
            System.err.println("Empty Search Depth Type found. \nFallback to default value: DEEP_SEARCH");
            return SearchTermAlgorithm.SearchType.DEEP_SEARCH;
        }
        
        try{
            SearchTermAlgorithm.SearchType stype = SearchTermAlgorithm.SearchType.valueOf(searchType);
            return stype;
            
        }catch (IllegalArgumentException e){
            System.err.println("Inavlid Search Depth Type: '" + searchType + "' \nFallback to default value: DEEP_SEARCH");
            return SearchTermAlgorithm.SearchType.DEEP_SEARCH;
        }
        
    }
    
    /**
     * Sets the "search depth" for the term search.
     * <p>
     * This method updates the search depth in the in-memory configuration properties.
     * The changes will not be saved to the file until {@link #storeUserPropertiesToFile()} is called.
     * </p>
     * @param searchDepth The {@code SearchTermAlgorithm.SearchType} to set as the default search depth.
     */

    public static final void setSearchDepth(SearchTermAlgorithm.SearchType searchDepth){
        String toSet = "undefined";
        switch (searchDepth) {
            case DEEP_SEARCH -> {
                toSet = searchDepth.name();
            }
            
            case BALANCED_SEARCH -> {
                toSet = searchDepth.name();
            }
            
            case SHALLOW_SEARCH -> {
                toSet = searchDepth.name();
            }
        }
        
        EXT_APP_CONFIG_PROPERTIES_DUPLICATE.setProperty(ExternalPropertiesKey.SEARCH_DEPTH, toSet);
    }
    
    /** Checks if the Search Depth properties stored was changed.
     *
     * @return true if it was changed or false otherwise.
     */
    public static boolean searchDepthPropertyChanged(){
        
        if(EXT_APP_CONFIG_PROPERTIES.getProperty(ExternalPropertiesKey.SEARCH_DEPTH).equals(EXT_APP_CONFIG_PROPERTIES_DUPLICATE.getProperty(ExternalPropertiesKey.SEARCH_DEPTH))){
            return false;
        }
        else{
            return true;
        }
    }
    
    
    /**
     * Resets the in-memory user properties to the original state loaded from the configuration file.
     * <p>
     * This discards any unsaved changes made during the application's runtime.
     * </p>
     */
    public static void resetUserProperties(){
        EXT_APP_CONFIG_PROPERTIES_DUPLICATE.putAll(EXT_APP_CONFIG_PROPERTIES);
    }
    
    /**
     * Saves the in-memory user properties to the external configuration file and moves any files contained in old directory to new directory if the changes to that property were made.
     * <p>
     * This method saves all changes made to the configuration during the application's runtime.
     * </p>
     */
    public static void storeUserPropertiesToFile() throws IOException{
        if(storageLocationPropertyChanged()){
            transferStorage();
        }
        
        setExternalConfiguration(EXT_APP_CONFIG_PROPERTIES_DUPLICATE);
        
        
        EXT_APP_CONFIG_PROPERTIES.putAll(EXT_APP_CONFIG_PROPERTIES_DUPLICATE);
    }
    
    /** 
     * Returns copy of Original Properties loaded from external file.
     *
     * @param prop Input Properties (Preferably Empty) which will be returned after copying the Original properties.
     * @return
     */
    public static Properties getOriginal(Properties prop){
        
        prop.putAll(EXT_APP_CONFIG_PROPERTIES);
        return prop;
    }
    
    /**
     * Returns copy of duplicate of Original Properties loaded from external file (whose state might have changed).
     * The duplicate of original properties is used to register any changes (via setters) while original Properties is read-only (via getters)
     *
     * @param prop
     * @return
     */
    public static Properties getDuplicate(Properties prop){
        
        prop.putAll(EXT_APP_CONFIG_PROPERTIES_DUPLICATE);
        return prop;
    }
    /** The main method of program.
     * 
     * @param args Command-line arguments.
     */
    public static void main(String args[]) {
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                // bookPickerFrame is from v0.4.0 and prior.
                //bookPickerFrame bpf = bookPickerFrame.generateInstance();
                
                Runtime rt = Runtime.getRuntime();

                System.out.println("Welcome to Medical Glossary " + VERSION + "!");
                
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("MEMORY ALLOCATION *BEFORE* BOOK PICKER FRAME IS CONSTURCTED: ");
                System.out.println("Max heap: " + rt.maxMemory() / (1024*1024) + " MB (Its ~25% of your maximum System Memory)");
                System.out.println("Total heap: " + rt.totalMemory() / (1024*1024) + " MB");
                System.out.println("Free heap: " + rt.freeMemory() / (1024*1024) + " MB");
                System.out.println("---------------------------------------------------------------------------------------");
                
                // following v0.5.0 BookPicker will be shown first.
                Book toSetBook = BookPicker.showBookPicker();
                
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("MEMORY ALLOCATION *AFTER* BOOK PICKER FRAME IS CONSTURCTED, SET VISIBLE AND ALL TERMS FROM SELECTED BOOK ARE LOADED: ");
                System.out.println("Max heap: " + rt.maxMemory() / (1024*1024) + " MB (Its ~25% of your maximum System Memory)");
                System.out.println("Total heap: " + rt.totalMemory() / (1024*1024) + " MB");
                System.out.println("Free heap: " + rt.freeMemory() / (1024*1024) + " MB");
                System.out.println("---------------------------------------------------------------------------------------");
                
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("MEMORY ALLOCATION *BEFORE* MAIN FRAME IS CONSTURCTED: ");
                System.out.println("Max heap: " + rt.maxMemory() / (1024*1024) + " MB (Its ~25% of your maximum System Memory)");
                System.out.println("Total heap: " + rt.totalMemory() / (1024*1024) + " MB");
                System.out.println("Free heap: " + rt.freeMemory() / (1024*1024) + " MB");
                System.out.println("---------------------------------------------------------------------------------------");
                
                boolean toContinue = true;
                mainFrame mf = new mainFrame();

                if(toSetBook == null){
                    System.out.println("Book was null");
                    toContinue = false;
                }
                else{
                    AppState.changeBook(toSetBook);
                }
                
                if
                    (!toContinue) System.exit(0);
                else 
                    mf.setVisible(true);
                
                System.out.println("---------------------------------------------------------------------------------------");
                System.out.println("MEMORY ALLOCATION *AFTER* MAIN FRAME IS CONSTURCTED AND SET VISIBLE:");
                System.out.println("Max heap: " + rt.maxMemory() / (1024*1024) + " MB (Its ~25% of your maximum System Memory)");
                System.out.println("Total heap: " + rt.totalMemory() / (1024*1024) + " MB");
                System.out.println("Free heap: " + rt.freeMemory() / (1024*1024) + " MB");
                System.out.println("---------------------------------------------------------------------------------------");

                //bpf.setVisible(true);
                //bpf.requestFocusInWindow();

                
                
            }
        });
        
        
    }
    
    /** Basically useful for printing messages onto console while also clearing the previous output on same line.
     *  This might not work with output window of IDEs.
     * 
     * @param msg 
     */
    public static void printlnClear(String msg){
        System.out.print("\033[2K\r" + msg);
    }
    
}
