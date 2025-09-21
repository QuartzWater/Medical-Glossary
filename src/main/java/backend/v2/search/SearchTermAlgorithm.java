/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.v2.search;

import backend.AppConfig;
import backend.Term;
import backend.TermDataManagement;
import backend.v2.data.AdvancedTermDataManagement;
import backend.v2.term.AdvancedTerm;
import book.bookpicker.Book;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author BRIN
 */
public class SearchTermAlgorithm {
    
    public enum SearchType {
        
        SHALLOW_SEARCH,
        BALANCED_SEARCH,
        DEEP_SEARCH,
    }
    
    private SearchType currentType;
    private int maxTerms;
    private boolean maxTermSet;
    
    public SearchTermAlgorithm(SearchType stype){
    
        this.currentType = stype;
        this.maxTerms = 0;
        this.maxTermSet = false;
    }
    
    public SearchTermAlgorithm(SearchType stype, int maxTerms){
        
        this.currentType = stype;
        if(maxTerms > 0){
            this.maxTerms = maxTerms;
            this.maxTermSet = true;
        }
        else{
            this.maxTerms = 0;
            this.maxTermSet = false;
        }
    }
    
    public void setSearchType(SearchType stype){
        
        this.currentType = stype;
    }
    
    public void changeMaxTermsObtainable(int maxTerms){
        
        if(maxTerms > 0){
            this.maxTerms = maxTerms;
            this.maxTermSet = true;
        }
    }
    
    public void disableMaxTermsObtainable(){
        this.maxTermSet = false;
    }
    
    public void enableMaxTermsObtainable(){
        if(maxTerms != 0){
            this.maxTermSet = true;
        }
    }
    
    
    public List<String> get(Term term, TermDataManagement tdm){
        Set<String> returnSet = new LinkedHashSet<>();
        
        List<String> allTermsList = tdm.getAllTerms();
        String termSpelling = term.getSpelling();
        
        List<String> prefixList = new LinkedList<>();
        
        int searchDepth = 1; // Maximum Search Depth
        int termSpellingLength = termSpelling.length();
        
        if(currentType == SearchType.SHALLOW_SEARCH){
            searchDepth = termSpellingLength - 1;
        }
        else if( currentType == SearchType.BALANCED_SEARCH){
            searchDepth = termSpellingLength/2;
            if(termSpellingLength % 2 != 0 ){
                searchDepth++;
            }
            
            if(termSpellingLength == 3){
                searchDepth--;
            }
            
        }else if(currentType == SearchType.DEEP_SEARCH){
            searchDepth = 1;
        }
        
        if(termSpellingLength == 1 || termSpellingLength == 2){
            searchDepth = 1;
        }
        
        for(int i = termSpellingLength; i >= searchDepth;){
            prefixList.add(termSpelling.substring(0, i));
            i--;
        }
        
        
        for(String prefix : prefixList){
           
            for(String  currentTerm : allTermsList){
                
                
                if(currentTerm.startsWith(prefix)){
                    returnSet.add(currentTerm);
                    
                }
                
                if(maxTermSet){
                    if(returnSet.size() >= maxTerms){
                        return  modifiedList(termSpelling, new LinkedList<>(returnSet));
                    }
                }
                
            }
            
            if(maxTermSet){
                if(returnSet.size() >= maxTerms){
                    return  modifiedList(termSpelling, new LinkedList<>(returnSet));
                }
            }
            
        }
        
        
        return  modifiedList(termSpelling, new LinkedList<>(returnSet));
    }
    
    public List<String> get(String termSpelling, List<String> allTerms){
        List<String> returnSet = new ArrayList<>();
        
        List<String> allTermsList = new ArrayList<>();
        allTermsList.addAll(allTerms);
        
        List<String> prefixList = new LinkedList<>();
        
        int searchDepth = 1; // Maximum Search Depth
        int termSpellingLength = termSpelling.length();
        
        if(currentType == SearchType.SHALLOW_SEARCH){
            searchDepth = termSpellingLength - 1;
        }
        else if( currentType == SearchType.BALANCED_SEARCH){
            searchDepth = termSpellingLength/2;
            if(termSpellingLength % 2 != 0 ){
                searchDepth++;
            }
            
            if(termSpellingLength == 3){
                searchDepth--;
            }
            
        }else if(currentType == SearchType.DEEP_SEARCH){
            searchDepth = 1;
        }
        
        if(termSpellingLength == 1 || termSpellingLength == 2){
            searchDepth = 1;
        }
        
        for(int i = termSpellingLength; i >= searchDepth;){
            prefixList.add(termSpelling.substring(0, i));
            i--;
        }
        
        
        for(String prefix : prefixList){
           
            for(String  currentTerm : allTermsList){
                
                
                if(currentTerm.startsWith(prefix)){
                    returnSet.add(currentTerm);
                    
                }
                
                if(maxTermSet){
                    if(returnSet.size() >= maxTerms){
                        return  modifiedList(termSpelling, new LinkedList<>(returnSet));
                    }
                }
                
            }
            
            if(maxTermSet){
                if(returnSet.size() >= maxTerms){
                    return  modifiedList(termSpelling, new LinkedList<>(returnSet));
                }
            }
            
        }
        
        
        return  modifiedList(termSpelling, new LinkedList<>(returnSet));
    }
    
    private LinkedList<String> modifiedList(String term, LinkedList<String> stringList){
        
        int currentIndex = stringList.indexOf(term);
        if(currentIndex == -1){
            
            return stringList;
        }
        
        String replacedString = stringList.get(0);
        
        stringList.set(0, term);
        stringList.set(currentIndex, replacedString);
        
        
        return stringList;
    }
    
    public List<SuggestionElement> get(String toSearchFor, Book book){
        List<SuggestionElement> toReturn = new ArrayList<>();
        
        List<String> allTermsList = new ArrayList<>();
        allTermsList.addAll(book.getATDM().getAllTerms());
        
        List<String> prefixList = new LinkedList<>();
        
        int searchDepth = 1; // Maximum Search Depth
        int termSpellingLength = toSearchFor.length();
        
        if(currentType == SearchType.SHALLOW_SEARCH){
            searchDepth = termSpellingLength - 1;
        }
        else if( currentType == SearchType.BALANCED_SEARCH){
            searchDepth = termSpellingLength/2;
            if(termSpellingLength % 2 != 0 ){
                searchDepth++;
            }
            
            if(termSpellingLength == 3){
                searchDepth--;
            }
            
        }else if(currentType == SearchType.DEEP_SEARCH){
            searchDepth = 1;
        }
        
        if(termSpellingLength == 1 || termSpellingLength == 2){
            searchDepth = 1;
        }
        
        for(int i = termSpellingLength; i >= searchDepth;){
            prefixList.add(toSearchFor.toLowerCase().substring(0, i));
            i--;
        }
        
        for(String prefix : prefixList){
           
            for(String  currentTerm : allTermsList){
                
                
                if(currentTerm.toLowerCase().startsWith(prefix)){
                    SuggestionElement toadd = new SuggestionElement(currentTerm, book, book.getColorScheme().getDefaultColor());
                    if(!toReturn.contains(toadd)){
                        toReturn.add(toadd);
                    }
                    
                }
                
                if(maxTermSet){
                    if(toReturn.size() >= maxTerms){
                        return  modifiedList(toSearchFor, book, toReturn);
                    }
                }
                
            }
            
            if(maxTermSet){
                if(toReturn.size() >= maxTerms){
                    return  modifiedList(toSearchFor, book, toReturn);
                }
            }
            
        }
        
        return  modifiedList(toSearchFor, book, toReturn);
    }
    
    private List<SuggestionElement> modifiedList(String toSearchFor, Book book, List<SuggestionElement> toReturn){
        
        SuggestionElement newTerm = new SuggestionElement(toSearchFor, book, book.getColorScheme().getDefaultColor());
        SuggestionElement specialNewTerm = new SuggestionElement(toSearchFor, book, AppConfig.FINAL_SYSTEM_TERM_CREATION_HIGHLIGHT);
        if(!toReturn.contains(newTerm)){
            toReturn.addFirst(specialNewTerm);
        }else{
            
            int index = toReturn.indexOf(newTerm);
            SuggestionElement toShift = toReturn.remove(index);
            toReturn.addFirst(toShift);
        }
        
        return toReturn;
    }
}
