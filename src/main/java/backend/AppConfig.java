/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import frontend.bookPickerFrame;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    
    public static final String POM_PROPERTIES_VIA_CLASSLOADER;
    public static final Properties INT_PROJECT_CONFIG_PROPERTIES;
    public static final String VERSION;
    
    public static final String USER_HOME;
    public static final String EXT_APP_CONFIG_HOME;
    public static final String EXT_CONFIG_FILE_NAME;
    public static final Path EXT_CONFIG_PROPERTIES_PATH;
    public static final Properties EXT_CONFIG_PROPERTIES;
    
    static {
        
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

        EXT_APP_CONFIG_HOME = ".medicalglossary";
        EXT_CONFIG_FILE_NAME = "config.properties";
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
        
        System.out.println("Application Version: " + VERSION);
        
        USER_HOME = System.getProperty("user.home");
        EXT_CONFIG_PROPERTIES_PATH = Paths.get(USER_HOME).resolve(EXT_APP_CONFIG_HOME).resolve(EXT_CONFIG_FILE_NAME);
        
        /*
        // REMOVE THIS CODE IN FINAL SHIPMENT
        try {
            Files.delete(AppConfig.EXT_CONFIG_PROPERTIES_PATH);
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

            JFileChooser chooser = new JFileChooser();
            
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            
            int option = chooser.showOpenDialog(null);
            
            if (option == JFileChooser.APPROVE_OPTION) {
                File selectedDir = chooser.getSelectedFile();
                String storagePath = selectedDir.getAbsolutePath();
                
                extProp.setProperty(PropertyKey.STORAGE_PATH, storagePath);
                AppConfig.setExternalConfiguration(extProp);
                
                JOptionPane.showMessageDialog(null, "Welcome to Medical Glossary " + VERSION + "!");
            } else {
                // User cancelled -> exit application
                System.exit(0);
            }
        }

        EXT_CONFIG_PROPERTIES = extProp;
        
    }
    
    /** Gets external configuration stored in predetermined configuration location
     * 
     * @return The loaded Properties Object from external configuration file. returns null if the configuration file was not found (IOException was thrown)
     *
     */
    private static Properties getExternalConfiguration(){
        
        try {
            Properties prop;
            try (InputStream newInputStream = Files.newInputStream(EXT_CONFIG_PROPERTIES_PATH)) {
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
    private static void setExternalConfiguration(Properties extProp){
        try {
            
            Files.createDirectories(EXT_CONFIG_PROPERTIES_PATH.getParent());
            try (OutputStream newOutputStream = Files.newOutputStream(EXT_CONFIG_PROPERTIES_PATH)) {
                extProp.store(newOutputStream, "Application Configuration File");
            }
            
        } catch (IOException ex) {
            System.err.println("Configuration file could not be created: " + ex.getMessage());
            System.exit(1);
        }
    }

    /** The main method of program.
     * 
     * @param args Command-line arguments.
     */
    public static void main(String args[]) {
        
        System.out.println("Welcome to Medical Glossary " + VERSION + "!");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                bookPickerFrame bpf = bookPickerFrame.generateInstance();
                bpf.setVisible(true);
                bpf.requestFocusInWindow();
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
