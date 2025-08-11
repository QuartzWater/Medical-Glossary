/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

/**
 *
 * @author BRIN
 */
public enum MajorTopicType {
    CONCEPTUAL_OVERVIEW,
    REGIONAL_ANATOMY,
    SURFACE_ANATOMY;
    
    public static String getCONCEPTUAL_OVERVIEW(){
        
        return "CONCEPTUAL_OVERVIEW";
    }
    
    public static String getREGIONAL_ANATOMY(){
        
        return "REGIONAL_ANATOMY";
    }
    public static String getSURFACE_ANATOMY(){
        
        return "SURFACE_ANATOMY";
    }
    
}
