/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import backend.AppConstants;
import backend.Term;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.JLabel;

/**
 *0
 * @author BRIN
 */
public class hyperlinkViewController {
    
    // TODO CREATE A BUTTON THAT INCORPORATES A SVG ICON
    
    private hyperlinkViewFrame hvf;
    private SourceFrame sf;
    private Term existingTerm;
    
    
    // *** GUI VARIABLES DECLARATION *** //
    
    // *** END OF DECLARATION *** //
    
    private hyperlinkViewController(hyperlinkViewFrame hvf, SourceFrame sf, Term existingterm){
        this.hvf = hvf;
        this.sf = sf;
        this.existingTerm = existingterm;
        
        SVGIconPanel[] sVGIconPanels = hvf.getSVGIcons();
        RoundedButton[] rndButton = hvf.getHyperlinkButtons();
        
        List<String> hyperList = existingterm.getHyperlinks();
        
        for(int i = 0; i < hyperList.size(); i++){
            String currentURL = hyperList.get(i);
            try {
                sVGIconPanels[i].set_SVG_URL_String("/images/icons/right-circle-non-filled.svg");
                
            } catch (IOException | NullPointerException ex) {
                System.out.println("SVGIcon could not be loaded!");
                Logger.getLogger(hyperlinkViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            String defaultHyperlink = AppConstants.HYPERLINK_DEFAULT.replaceFirst("%", Integer.toString(i + 1)).replaceFirst("&", existingterm.getSpelling());
            System.out.println(defaultHyperlink);
            System.out.println(currentURL);
            if(currentURL.equals(defaultHyperlink)){
                rndButton[i].setText("");
                
            }
            else{
                rndButton[i].setText(existingterm.getHyperlinkEncapsulation().get(i));
                coupleComponents(rndButton[i], sVGIconPanels[i], currentURL);
            }
            
        }
        
        // *** GUI VARIABLES INITIALIZATION *** //
        
        // *** END OF INITIALIZATION *** //
    }
    
    private void coupleComponents (RoundedButton rnd, SVGIconPanel svgPanel, String hyperlink) {
        rnd.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getDefaultColor());
        
        MouseAdapter mAdapt = new MouseAdapter() {
            
            boolean isInside = false;
            
            @Override
            public void mouseEntered(MouseEvent e){
                isInside = true;
                rnd.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getHoverColor());
                try {
                    svgPanel.set_SVG_URL_String("/images/icons/right-circle-white-half-transparent.svg");
                } catch (IOException | NullPointerException ex) {
                    System.out.println("SVGIcon could not be loaded!");
                    Logger.getLogger(hyperlinkViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                isInside = false;
                rnd.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getDefaultColor());
                try {
                    svgPanel.set_SVG_URL_String("/images/icons/right-circle-non-filled.svg");
                } catch (IOException | NullPointerException ex) {
                    System.out.println("SVGIcon could not be loaded!");
                    Logger.getLogger(hyperlinkViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            @Override
            public void mousePressed(MouseEvent e){
                rnd.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getPressedColor());
                try {
                    svgPanel.set_SVG_URL_String("/images/icons/right-circle-white.svg");
                } catch (IOException | NullPointerException ex) {
                    System.out.println("SVGIcon could not be loaded!");
                    Logger.getLogger(hyperlinkViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            @Override
            public void mouseReleased(MouseEvent e){
                if(isInside){
                    rnd.setCurrentColor(AppConstants.DEFAULT_NEXT_BUTTON_CS_1.getHoverColor());
                    try {
                        svgPanel.set_SVG_URL_String("/images/icons/right-circle-white-half-transparent.svg");
                    } catch (IOException | NullPointerException ex) {
                        System.out.println("SVGIcon could not be loaded!");
                        Logger.getLogger(hyperlinkViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
        };
        
        ActionListener rndActList = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = hyperlink;
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        Desktop.getDesktop().browse(new URI(url));
                    } catch (IOException | URISyntaxException ex) {
                        // Handle exceptions (e.g., show an error dialog)
                        ex.printStackTrace();
                    }
                } else {
                        // Handle case where Desktop is not supported
                    System.out.println("Desktop is not supported, cannot open URL.");
                }
            }
        };
        
        rnd.addMouseListener(mAdapt);
        rnd.addActionListener(rndActList);
    }
    
    public static void initializeController(hyperlinkViewFrame hvf, SourceFrame sf, Term existingterm){
        hyperlinkViewController hvc = new hyperlinkViewController(hvf, sf, existingterm);
    }
}
