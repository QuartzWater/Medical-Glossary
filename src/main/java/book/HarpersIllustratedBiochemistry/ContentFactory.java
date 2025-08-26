/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book.HarpersIllustratedBiochemistry;

import backend.SubtopicData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author BRIN
 */
public class ContentFactory {
    
    private static Map<Integer, String[]> getEverythingByPageLOOPER(Map<Integer, String[]> inputMap, Section section, int page){
        
        List<Content> contentList = section.getContent();
        
        Content firstContent = contentList.get(0);
        Content lastContent = contentList.get(contentList.size() - 1);
        
        List<SubtopicData> firstContentSubtopicData = firstContent.getSubtopics();
        List<SubtopicData> lastContentSubtopicData = lastContent.getSubtopics();
        
        int firstContentFirstPage = firstContentSubtopicData.get(0).getStartPage();
        int lastContentLastPage = lastContentSubtopicData.get(lastContentSubtopicData.size() - 1).getEndPage();
        
        if(page < firstContentFirstPage || page > lastContentLastPage){
            return inputMap;
        }
        
        int count = inputMap.size();
        
        for(int contentIndex = 0; contentIndex < contentList.size(); contentIndex++){
            
            Content content = contentList.get(contentIndex);
            List<SubtopicData> contentSubtopics = content.getSubtopics();
            
            for(int subtopicIndex = 0; subtopicIndex < contentSubtopics.size(); subtopicIndex++){
                
                SubtopicData subtopic = contentSubtopics.get(subtopicIndex);
                int startPage = subtopic.getStartPage();
                int endPage = subtopic.getEndPage();
                
                if(page >= startPage && page <= endPage){
                    
                    inputMap.put(count, new String[]{
                        section.getTitle(),                         // index = 0
                        content.getTitle(),                         // index = 1
                        subtopic.getTitle(),                        // index = 2
                        Integer.toString(subtopic.getStartPage()),  // index = 3
                        Integer.toString(subtopic.getEndPage())     // index = 4
                
                    });
                    count++; // Map starts at index 0; since the count is increased AFTER the it's previous value is put in map.
                }
                
            }
            
        }
        
        return inputMap;
        
    }
    
    public static Map<Integer, String[]> getEverythingByPage(int page){
        
        Map<Integer, String[]> returnMap = new HashMap<>();
        
        returnMap = getEverythingByPageLOOPER(returnMap, Section.SECTION_1, page);
        returnMap = getEverythingByPageLOOPER(returnMap, Section.SECTION_2, page);
        returnMap = getEverythingByPageLOOPER(returnMap, Section.SECTION_3, page);
        returnMap = getEverythingByPageLOOPER(returnMap, Section.SECTION_4, page);
        returnMap = getEverythingByPageLOOPER(returnMap, Section.SECTION_5, page);
        returnMap = getEverythingByPageLOOPER(returnMap, Section.SECTION_6, page);
        returnMap = getEverythingByPageLOOPER(returnMap, Section.SECTION_7, page);
        returnMap = getEverythingByPageLOOPER(returnMap, Section.SECTION_8, page);
        returnMap = getEverythingByPageLOOPER(returnMap, Section.SECTION_9, page);
        returnMap = getEverythingByPageLOOPER(returnMap, Section.SECTION_10, page);
        returnMap = getEverythingByPageLOOPER(returnMap, Section.SECTION_11, page);
        
        return returnMap;
    }
}
