/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

/**
 *
 * @author BRIN
 */
public class SubtopicData {
    
    private final String title;
    private final int startPage;
    private final int endPage;
    
    
    public  SubtopicData(String title, int startpage,int endpage){
        this.title = title;
        this.startPage = startpage;
        this.endPage = endpage;
    }
    
    public String getTitle(){
        
        return title;
    }
    
    public int getStartPage(){
        return startPage;
    }
    
    public int getEndPage(){
        
        return endPage;
    }
}
