/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

/**
 *
 * @author BRIN
 */
public class HeadingModel {
    
    private final String superHeading;
    private final String middleHeading;
    private final String subHeading;

    public HeadingModel(String superHeading, String middleHeading, String subHeading) {
        this.superHeading = superHeading.toUpperCase();
        this.middleHeading = middleHeading.toUpperCase();
        this.subHeading = subHeading.toUpperCase();
    }

    public String getSuperHeading() {
        return superHeading;
    }

    public String getMiddleHeading() {
        return middleHeading;
    }

    public String getSubHeading() {
        return subHeading;
    }
    
}
