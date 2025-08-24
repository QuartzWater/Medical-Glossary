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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author BRIN
 */
public class AppConfig {
 
    public static final String CONFIG_HOME;
    public static final Path appConfigDir;
    
    public static final Properties configProperties;
    public static final Properties projectConfig;
    
    
    public static final String APP_CONFIG_HOME;
    public static final String CONFIG_FILE_NAME;
    public static final String VERSION;
    public static final String POM_PROPERTIES_VIA_CLASSLOADER;
    
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

        APP_CONFIG_HOME = ".medicalglossary";
        CONFIG_FILE_NAME = "config.properties";
        // ALWAYS CHANGE THIS IF YOU CHANGE <group-id> or <artifact-id> OF THE PROJECT
        POM_PROPERTIES_VIA_CLASSLOADER = "META-INF/maven/quartzwater/Medical-Glossary/pom.properties";
        // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^<group-id>^^^<artifact-id>^^^^^^^^^^^^^^^^^^^
        
        projectConfig = new Properties();
        String pomproperties = POM_PROPERTIES_VIA_CLASSLOADER;
        try (InputStream loadprop = AppConfig.class.getClassLoader().getResourceAsStream(pomproperties)) {
            if (loadprop == null) {
                System.err.println("Critical ResourceInputStream was found to be null.");
                System.out.println("This might be because of malformed JAR file or the project is in development environment. Fallback to default Version.");
            }else{
                projectConfig.load(loadprop);
                System.out.println("Project Configuration loaded successfully!");
            }
            
        } catch (IOException e) {
            System.err.println("Resource not found: " + pomproperties);
            System.err.println("Project Configuration could not be loaded! The application will now close with exit code 1 : " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Critical error during loading of project configuration occured! Application will now close!");
            System.exit(1);
        }
        
        String setVersion = projectConfig.getProperty("version");
        if(setVersion == null){
            VERSION = "v0.2.DEV"; // for development : default version
        }
        else{
            VERSION = "v" + setVersion;
        }
        
        System.out.println("Application Version: " + VERSION);
        
        CONFIG_HOME = System.getProperty("user.home");
        appConfigDir = Paths.get(CONFIG_HOME).resolve(APP_CONFIG_HOME).resolve(CONFIG_FILE_NAME);
        
        // REMOVE THIS CODE IN FINAL SHIPMENT
        try {
            Files.delete(AppConfig.appConfigDir);
        } catch (IOException ex) {
            System.err.println("No such file : TODO REMOVE THIS CODE : AppConfig.java : line 102");
        }
        // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        
        String storagePath = AppConfig.getStoragePath();
        
        if(storagePath == null){
            
            // First time running, prompt the user
            JOptionPane.showMessageDialog(null, "Please select the location where you would like to save the terms.");

            
            JFileChooser chooser = new JFileChooser();
            
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            
            int option = chooser.showOpenDialog(null);
            
            if (option == JFileChooser.APPROVE_OPTION) {
                File selectedDir = chooser.getSelectedFile();
                storagePath = selectedDir.getAbsolutePath();
                
                AppConfig.setStoragePath(storagePath);
                
                JOptionPane.showMessageDialog(null, "Welcome to Medical Glossary " + VERSION + "!");
            } else {
                // User cancelled -> exit application
                System.exit(0);
            }
        }

        configProperties = new Properties();
        
        try {
            configProperties.load(Files.newInputStream(appConfigDir));
            System.out.println("Successfully loaded external configuration file.");
        } catch (IOException ex) {
            String msg = "Could not load Configuration File! The application will now close!";
            System.err.println(msg + ":" + ex.getMessage());
            JOptionPane.showMessageDialog(null, msg);
            System.exit(1);
        }
    }
    
    public static String getStoragePath(){
        
        try {
            Properties prop;
            try (InputStream newInputStream = Files.newInputStream(appConfigDir)) {
                prop = new Properties();
                prop.load(newInputStream);
            }
            return prop.getProperty(PropertyKey.STORAGE_PATH);
            
        } catch (IOException ex) {
            return null;
        }
        
    }
    
    public static void setStoragePath(String path){
        try {
            Properties prop = new Properties();
            prop.setProperty(PropertyKey.STORAGE_PATH, path);
            Files.createDirectories(appConfigDir.getParent());
            try (OutputStream newOutputStream = Files.newOutputStream(appConfigDir)) {
                prop.store(newOutputStream, "Application Configuration File");
            }
            
        } catch (IOException ex) {
            System.err.println("Configuration file could not be created: " + ex.getMessage());
            System.exit(1);
        }
    }

    public static void main(String args[]) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>> TODO <<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println("Add dynamic font size change in case of text overflow in JTextFields and JLabels");
        /* Create and display the form */
        System.out.println("Welcome to Medical Glossary " + VERSION + "!");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                bookPickerFrame bpf = bookPickerFrame.generateInstance();
                bpf.setVisible(true);
                bpf.requestFocusInWindow();
            }
        });
        
    }
    
    public static void printlnClear(String msg){
        System.out.print("\033[2K\r" + msg);
    }
    
}
