/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.awt.Color;

/**
 *
 * @author BRIN
 */
public class AppConstants {
    
    // FOR BOTH OF THEM: Replace '%' with number (if using index while iterating through array, its recommended to use i + 1, instead of just i, '&' with term name.
    public static final String HYPERLINK_DEFAULT = "Hyperlink % for '&' not available yet";
    public static final String ENCAPSULATION_DEFAULT = "Encapsulation for Hyperlink % for '&' not available yet";
    
    public static final ColorScheme DEFAULT_NEXT_BUTTON_CS_1 = new ColorScheme(new Color(15,20,25), new Color(43,181,114), new Color(37,160,100), new Color(57,75,92));
    public static final ColorScheme DEFAULT_NEXT_BUTTON_CS_2 = new ColorScheme(new Color(43,181,114), new Color(37,160,100), new Color(35,146,92), new Color(57,75,92));
    
    public static final ColorScheme DEFAULT_BACK_BUTTON_CS_1 = new ColorScheme(new Color(15,20,25), new Color(255,51,51), new Color(224,51,51), new Color(57,75,92));
    public static final ColorScheme DEFAULT_BACK_BUTTON_CS_2 = new ColorScheme(new Color(255,51,51), new Color(224,51,51), new Color(207, 50, 50), new Color(57,75,92));
    
    public static final ApplicationColorScheme DEFAULT_APPLICATION_CS = new ApplicationColorScheme(new Color(44,62,80), new Color(57,75,92), new Color(255,255,255), new Color(27,36,45));
                                                                                                  // Blue               // Lighter-grayish blue // White            // Dark Blue
}
