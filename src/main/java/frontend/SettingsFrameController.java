/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import backend.AppConfig;
import static backend.AppConfig.VERSION;
import backend.AppConstants;
import backend.ColorScheme;
import backend.SearchTermAlgorithm;
import backend.eventadapter.GranularMouseAdapter;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author BRIN
 */
public class SettingsFrameController {
  
    // GUI VARIABLES DECLARATION
    private final SettingsFrame settings;
    private final RoundedButton storageLocation;
    private final RoundedButton changeButton;
    private final RoundedButton deepButton;
    private final RoundedButton balancedButton;
    private final RoundedButton shallowButton;
    private final RoundedButton saveButton;
    private final RoundedButton cancelButton;
    
    // END OF DECLARATION
    
    private final ColorScheme changeButtonColorScheme = new ColorScheme(new Color(57,75,92), new Color(44,62,80), new Color(40,56,71), Color.WHITE);
    private final ColorScheme searchDepthCS = new ColorScheme(Color.WHITE, AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getHoverColor(), AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getPressedColor(), new Color(102,102,102));
    
    private SettingsFrameController(SettingsFrame settings){
        this.settings = settings;
        this.storageLocation = settings.getStorageLocation();
        this.changeButton = settings.getChangeButton();
        this.deepButton = settings.getDeepButton();
        this.balancedButton = settings.getBalancedButton();
        this.shallowButton = settings.getShallowButton();
        this.saveButton = settings.getSaveButton();
        this.cancelButton = settings.getCancelButton();
        
        changeButton.setCurrentColor(changeButtonColorScheme.getDefaultColor());
        changeButton.setForeground(changeButtonColorScheme.getDefaultColor());
       
        Path oldPath = AppConfig.getStorageLocation().resolve("Medical-Glossary").resolve("books");
        
        storageLocation.setText(oldPath.toString());
        SearchTermAlgorithm.SearchType stype = AppConfig.getSearchDepth();
        
        RoundedButton rdbToSet = new RoundedButton();
        switch (stype) {
            case DEEP_SEARCH -> {
                rdbToSet = deepButton;
            }
            
            case BALANCED_SEARCH -> {
                rdbToSet = balancedButton;
            }
            
            case SHALLOW_SEARCH -> {
                rdbToSet = shallowButton;
            }

                 
        }
        
        rdbToSet.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_2.getDefaultColor());
        rdbToSet.setForeground(Color.WHITE);
        
        addChangeButtonBehaviour();
        addSaveButtonBehaviour();
        addCancelButtonBehaviour();
        SearchDepthButtonManager sdbm = new SearchDepthButtonManager(deepButton, balancedButton, shallowButton, rdbToSet);
        
    }
    
    public static void initializeController(SettingsFrame settings){
        SettingsFrameController settingsController = new SettingsFrameController(settings);
    }
    
    private class OriginalDirectoryFilter extends FileFilter{
        
         private final String directoryToHide;
         
         public OriginalDirectoryFilter(Path toHide){
             directoryToHide = toHide.toString();
         }

        @Override
        public boolean accept(File f) {
            // Always show files.
            if (f.isFile()) {
                return false;
            }

            // Hide a directory if its absolute path matches the one we want to hide.
            if (f.isDirectory() && f.getAbsolutePath().equals(directoryToHide)) {
                return false;
            }

            // Otherwise, show the directory.
            return true;
        }

        @Override
        public String getDescription() {
            return "Show All Directories (except '" + directoryToHide + "\' - Its the original path.)";
        }
    }
    
    private void addChangeButtonBehaviour(){
        
        ActionListener changeButtonActListen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser(AppConfig.getStorageLocation().toFile());
            
                Path originalPath = AppConfig.getStorageLocation().resolve("Medical-Glossary");
                chooser.setFileFilter(new OriginalDirectoryFilter(originalPath));
                chooser.setDialogTitle("Select the directory where terms will be stored:");
                chooser.setApproveButtonText("Confirm");
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                boolean isARoot = true;
                File[] roots = File.listRoots();
                while(isARoot){
                    isARoot = false;
                    
                    int option = chooser.showOpenDialog(null);

                    if (option == JFileChooser.APPROVE_OPTION) {
                        File selectedDir = chooser.getSelectedFile();
                        for(File f : roots){
                            if(selectedDir.equals(f)){
                                isARoot = true;
                            }
                        }
                        
                        
                        String storagePath = selectedDir.getAbsolutePath();
                        Path newPath = Paths.get(storagePath).resolve("Medical-Glossary").resolve("books");
                        
                        if(!isARoot){
                            storageLocation.setText(newPath.toString());
                            AppConfig.setStorageLocation(Paths.get(storagePath));
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Selected directory was found to be root directory! This may cause permission issues. Please select another directory");
                        }

                    }
                }
            }
        };
        
        changeButton.addActionListener(changeButtonActListen);
        
        GranularMouseAdapter changeButtonMAdapt = new GranularMouseAdapter(){
            @Override
            public void actOnMouseEntry(MouseEvent e){
                changeButton.setCurrentColor(changeButtonColorScheme.getHoverColor());
                changeButton.setForeground(changeButtonColorScheme.getDisabledColor());
            }
            
            @Override
            public void actOnMouseExit(MouseEvent e){
                changeButton.setCurrentColor(changeButtonColorScheme.getDefaultColor());
                changeButton.setForeground(changeButtonColorScheme.getDefaultColor());
            }
            
            @Override
            public void actOnMousePress(MouseEvent e){
                changeButton.setCurrentColor(changeButtonColorScheme.getPressedColor());
            }
            
            @Override
            public void actOnMouseRelease(MouseEvent e){
                changeButton.setCurrentColor(changeButtonColorScheme.getHoverColor());
            }
        };
        
        changeButton.addMouseListener(changeButtonMAdapt);
    }
    
    private class SearchDepthButtonManager{
        
        private RoundedButton deep;
        private RoundedButton balanced;
        private RoundedButton shallow;
        
        public SearchDepthButtonManager(RoundedButton deep, RoundedButton balanced, RoundedButton shallow, RoundedButton alreadyActivated){
            
            this.deep = deep;
            this.balanced = balanced;
            this.shallow = shallow;
            
            addListeners(deep);
            addListeners(balanced);
            addListeners(shallow);
            
            GranularMouseAdapter name = (GranularMouseAdapter) alreadyActivated.getMouseListeners()[1];
            name.setCanRespondToHover(false);
            name.setCanRespondToPress_Release(false);
            
        }
        
        private void addListeners(RoundedButton rdb){
            
            GranularMouseAdapter gran = new GranularMouseAdapter(){
                
                @Override
                public void actOnMouseEntry(MouseEvent e){
                    rdb.setCurrentColor(searchDepthCS.getHoverColor());
                    rdb.setForeground(searchDepthCS.getDefaultColor());
                }

                @Override
                public void actOnMouseExit(MouseEvent e){
                    rdb.setCurrentColor(searchDepthCS.getDefaultColor());
                    rdb.setForeground(searchDepthCS.getDisabledColor());
                }

                @Override
                public void actOnMousePress(MouseEvent e){
                    rdb.setCurrentColor(searchDepthCS.getPressedColor());
                }

                @Override
                public void actOnMouseRelease(MouseEvent e){
                    rdb.setCurrentColor(searchDepthCS.getHoverColor());
                    this.setCanRespondToHover(false);
                    this.setCanRespondToPress_Release(false);
                    
                    RoundedButton[] others = getOtherButtonsAndSetSetting(rdb);
                    
                    GranularMouseAdapter other1 = (GranularMouseAdapter) others[0].getMouseListeners()[1];
                    GranularMouseAdapter other2 = (GranularMouseAdapter) others[1].getMouseListeners()[1];
                    
                    other1.setCanRespondToHover(true);
                    other1.setCanRespondToPress_Release(true);
                    other2.setCanRespondToHover(true);
                    other2.setCanRespondToPress_Release(true);
                    
                    others[0].setCurrentColor(searchDepthCS.getDefaultColor());
                    others[0].setForeground(searchDepthCS.getDisabledColor());
                    others[1].setCurrentColor(searchDepthCS.getDefaultColor());
                    others[1].setForeground(searchDepthCS.getDisabledColor());
                }
            };
            
            rdb.addMouseListener(gran);
            
        }
        
        private RoundedButton[] getOtherButtonsAndSetSetting(RoundedButton rdb){
            
            if(rdb == deep) {
                AppConfig.setSearchDepth(SearchTermAlgorithm.SearchType.DEEP_SEARCH);
                return new RoundedButton[]{balanced, shallow};
            }
            else if(rdb == balanced) {
                AppConfig.setSearchDepth(SearchTermAlgorithm.SearchType.BALANCED_SEARCH);
                return new RoundedButton[]{shallow, deep};
            }
            else {
                AppConfig.setSearchDepth(SearchTermAlgorithm.SearchType.SHALLOW_SEARCH);
                return new RoundedButton[]{deep,balanced};
            }
        }
        
    }
    
    private void addSaveButtonBehaviour(){
        ActionListener saveActListen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = 999;
                if(AppConfig.storageLocationPropertyChanged()){
                    
                    String[] options = {"Confirm", "Cancel"};
                    response = JOptionPane.showOptionDialog(
                        settings, // Parent component
                        "Storage Location was changed. The application will exit now. This is required to correctly apply new settings.", // Message
                        "Storage location changed", // Title
                        JOptionPane.YES_NO_OPTION, // Option type (custom buttons)
                        JOptionPane.WARNING_MESSAGE, // Message type (icon)
                        null, // Custom icon (null uses default)
                        options, // Custom button labels
                        options[0] // Default selected option
                    );
                }
                
                if(response == 0 || response == 999){
                    
                    try {
                        AppConfig.storeUserPropertiesToFile();
                        settings.dispose();
                        if(response == 0){
                            System.exit(0);
                        }
                    } catch (AccessDeniedException ex){
                        
                        JOptionPane.showMessageDialog(
                                settings, 
                                "You either don't have enough administrative priviledges or the destination directory is not currently accessible: \njava.nio.file.AccessDeniedException", 
                                "Error moving to destination directory", 
                                JOptionPane.ERROR_MESSAGE);
                    } catch (IOException ex) {
                        
                        JOptionPane.showMessageDialog(
                                settings, 
                                "Error encountered while trying to move to destination directory: \njava.io.IOException: " + ex.getMessage(), 
                                "Error moving to destination directory", 
                                JOptionPane.ERROR_MESSAGE);
                        System.getLogger(SettingsFrameController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    }
                    
                }
                else if(response == 1 || response == -1){ // FIX for v0.5.0: fixed issue where pressing cross upon getting confirmation message for changing storage location would not reset the storage location field.
                    storageLocation.setText(AppConfig.getStorageLocation().toString());
                    AppConfig.setStorageLocation(AppConfig.getStorageLocation());
                }
            }
        };
        
        GranularMouseAdapter gran = new GranularMouseAdapter(){
            
            @Override
            public void actOnMouseEntry(MouseEvent e){

                saveButton.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getHoverColor());
            }

            @Override
            public void actOnMouseExit(MouseEvent e){

                saveButton.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getDefaultColor());
            }

            @Override
            public void actOnMousePress(MouseEvent e){

                saveButton.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getPressedColor());
            }

            @Override
            public void actOnMouseRelease(MouseEvent e){

                saveButton.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getHoverColor());
            }
        };
        
        saveButton.addMouseListener(gran);
        saveButton.addActionListener(saveActListen);
    }
    
    private void addCancelButtonBehaviour(){
        ActionListener cancelActListen = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppConfig.resetUserProperties();
                settings.dispose();
            }
        };
        
        cancelButton.addActionListener(cancelActListen);
        
        GranularMouseAdapter gran = new GranularMouseAdapter(){
            @Override
            public void actOnMouseEntry(MouseEvent e){

                cancelButton.setCurrentColor(AppConstants.DEFAULT_BACK_BUTTON_CS_1.getHoverColor());
            }

            @Override
            public void actOnMouseExit(MouseEvent e){

                cancelButton.setCurrentColor(AppConstants.DEFAULT_BACK_BUTTON_CS_1.getDefaultColor());
            }

            @Override
            public void actOnMousePress(MouseEvent e){

                cancelButton.setCurrentColor(AppConstants.DEFAULT_BACK_BUTTON_CS_1.getPressedColor());
            }

            @Override
            public void actOnMouseRelease(MouseEvent e){

                cancelButton.setCurrentColor(AppConstants.DEFAULT_BACK_BUTTON_CS_1.getHoverColor());
            }
        };
        
        cancelButton.addMouseListener(gran);
    }
}
