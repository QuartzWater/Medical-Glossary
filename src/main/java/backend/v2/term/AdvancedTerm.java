/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.v2.term;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;

/**
 *
 * @author BRIN
 */
public final class AdvancedTerm {
    
    public enum SpellingAttribute{
        DEFINITION,
        SUPER_CATEGORY,
        MIDDLE_CATEGORY,
        SUB_CATEGORY,
        PAGE
    }
    
    public static final String DEFNITION_EXTENSION = ".rtf";
    public static final int MAX_HYPERLINKS = 6;
    
    // DEFAULTS
    public static final String DEFAULT_PLAIN_DEFINITION = "%%defaultdefintion%%";
    public static final String DEFAULT_SUPER_HEADING = "%%defaultsuperheading%%";
    public static final String DEFAULT_MIDDLE_HEADING = "%%defaultmiddleheading%%";
    public static final String DEFAULT_SUB_HEADING = "%%defaultsubheading%%";
    public static final int DEFAULT_PAGE = 0;
    
    private String spelling;
    private String absoluteSpelling;
    private StyledDocument styledDefinition;
    private String superCategory;
    private String middleCategory;
    private String subCategory;
    private int page;
    
    private List<AdvancedHyperlink> hyperlinks = new ArrayList<>(Arrays.asList(
        new AdvancedHyperlink(),
        new AdvancedHyperlink(),
        new AdvancedHyperlink(),
        new AdvancedHyperlink(),
        new AdvancedHyperlink(),
        new AdvancedHyperlink()
    ));
    
    private List<String> definitionKeywords = new ArrayList<>();
    
    public AdvancedTerm(String spelling) throws IllegalTermStateException{
        
        spelling = spelling.strip();
        checkSpelling(spelling);
        this.absoluteSpelling = spelling;
        this.spelling = spelling.toLowerCase();
        
        try {
            StyledDocument DEFAULT_STYLED_DEFINITION;
            DEFAULT_STYLED_DEFINITION = new DefaultStyledDocument();
            DEFAULT_STYLED_DEFINITION.insertString(0, DEFAULT_PLAIN_DEFINITION, null);
            this.styledDefinition = DEFAULT_STYLED_DEFINITION;
            
        } catch (BadLocationException ex) {
            System.getLogger(AdvancedTerm.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw new IllegalTermStateException("Bad Location Exception in the single argument constructor", ex, IllegalTermStateException.TYPE.BAD_LOCATION);
        }
        this.superCategory = DEFAULT_SUPER_HEADING;
        this.middleCategory = DEFAULT_MIDDLE_HEADING;
        this.subCategory = DEFAULT_SUB_HEADING;
        this.page = DEFAULT_PAGE;
    }
    
    public AdvancedTerm(
            String spelling,
            StyledDocument styledDefinition,
            List<String> keywords,
            String superCategory,
            String middleCategory,
            String subCategory,
            int page,
            List<AdvancedHyperlink> hyperlinks
    ) throws IllegalTermStateException
    {
        
        spelling = spelling.strip();
        checkSpelling(spelling);
        this.absoluteSpelling = spelling;
        this.spelling = spelling.toLowerCase();
        this.styledDefinition = styledDefinition;
        try {
            String plainDefinition = styledDefinition.getText(0, styledDefinition.getLength());
            checkKeywords(keywords, plainDefinition);
        } catch (BadLocationException ex) {
            System.getLogger(AdvancedTerm.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw new IllegalTermStateException("Provided definition caused a Bad Location Exception.", ex, IllegalTermStateException.TYPE.BAD_LOCATION);
        }
        this.definitionKeywords.addAll(keywords);
        this.superCategory = superCategory;
        this.middleCategory = middleCategory;
        this.subCategory = subCategory;
        this.page = page;
        
        replaceHyperlinks(hyperlinks);
    }

    public AdvancedTerm(AdvancedTerm original) {
        
        this.spelling = original.spelling;
        this.absoluteSpelling = original.absoluteSpelling;
        this.page = original.page;
        this.superCategory = original.superCategory;
        this.middleCategory = original.middleCategory;
        this.subCategory = original.subCategory;
        this.hyperlinks = new ArrayList<>();
        
        for(AdvancedHyperlink advHyper : original.hyperlinks){
            this.hyperlinks.add(new AdvancedHyperlink(advHyper));
        }
        
        this.definitionKeywords = new ArrayList<>();
        this.definitionKeywords.addAll(original.definitionKeywords);
        this.styledDefinition = original.styledDefinition; // Reference copy (TODO: MAKE IT A FULL COPY)
    }
    
    public boolean isDefault(SpellingAttribute atr){
        switch (atr) {
            
            case DEFINITION ->{
                
                boolean content = true;
                try {
                    content = styledDefinition.getText(0, styledDefinition.getLength()).equals(DEFAULT_PLAIN_DEFINITION);
                } catch (BadLocationException ex) {
                    System.getLogger(AdvancedTerm.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
                return content;
            }
                
            case SUPER_CATEGORY -> {
                return superCategory.equals(DEFAULT_SUPER_HEADING);
            }
            
            case MIDDLE_CATEGORY -> {
                return middleCategory.equals(DEFAULT_MIDDLE_HEADING);
            }
            
            case SUB_CATEGORY -> {
                return subCategory.equals(DEFAULT_SUB_HEADING);
            }
            
            case PAGE -> {
                return page == DEFAULT_PAGE;
            }
        }
        
        return false;
    }
    
    public List<Integer> hyperlinkDefaultAt() throws IllegalTermStateException{
        List<Integer> toReturn = new ArrayList<>();
        for(int i = 0; i < MAX_HYPERLINKS; i++){
            AdvancedHyperlink advHyper = hyperlinks.get(i);
            String hyperlink = advHyper.getHyperlink();
            String hyperlinkEncapsulation = advHyper.getEncapsulation();
            
            if(hyperlink.equals(AdvancedHyperlink.DEFAULT_HYPERLINK_TEXT) && hyperlinkEncapsulation.equals(AdvancedHyperlink.DEFAULT_HYPERLINK_ENCAPSULATION)){
                toReturn.add(i);
            }
        }
        return toReturn;
    }
    
    public String getAbsoluteSpelling(){
        return this.absoluteSpelling;
    }
    
    public String getSpelling(){
        return this.spelling;
    }
    
    public String getPlainDefinition(){
        try {
            return this.styledDefinition.getText(0, this.styledDefinition.getLength());
        } catch (BadLocationException ex) {
            System.getLogger(AdvancedTerm.class.getName()).log(System.Logger.Level.ERROR, ex);
            return "";
        }
    }
    
    public StyledDocument getStyledDefinition(){
        return this.styledDefinition;
    }
    
    public List<String> getDefinitionKeywords(){
        List<String> keywords = new ArrayList<>();
        keywords.addAll(definitionKeywords);
        return keywords;
    }
    
    public String getSuperCateogry(){
        return this.superCategory;
    }
    
    public String getMiddleCategory(){
        return this.middleCategory;
    }
    
    public String getSubCategory(){
        return this.subCategory;
    }
    
    public int getPage(){
        return this.page;
    }
    
    public List<AdvancedHyperlink> getHyperlinks(){
        List<AdvancedHyperlink> toReturn = new ArrayList<>();
        for(AdvancedHyperlink advHyper : hyperlinks){
            AdvancedHyperlink advCOPY = new AdvancedHyperlink(advHyper);
            toReturn.add(advCOPY);
        }
        return toReturn;
    }
    
    public String[] getBothAsPair(int index){
        AdvancedHyperlink advHyper = hyperlinks.get(index);
        String[] toReturn = {
            
            advHyper.getHyperlink(),
            advHyper.getEncapsulation()
        };
        
        return toReturn;
    }
    
    public void setSpelling(String spelling) throws IllegalTermStateException{
        spelling = spelling.strip();
        checkSpelling(spelling);
        this.absoluteSpelling = spelling;
        this.spelling = spelling.toLowerCase();
    }
    
    public void setDefinition(StyledDocument styledDefinition) throws IllegalTermStateException{
        
        try {
            String definition = styledDefinition.getText(0, styledDefinition.getLength());
            checkKeywords(this.definitionKeywords, definition);
        } catch (BadLocationException ex) {
            System.getLogger(AdvancedTerm.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw new IllegalTermStateException("Could not get text from styled document associated with this term.", ex, IllegalTermStateException.TYPE.BAD_LOCATION);
        }
        this.styledDefinition = styledDefinition;
    }
    
    public void setNewDefinition(StyledDocument styledDefinition) throws IllegalTermStateException{
        this.definitionKeywords.clear();
        setDefinition(styledDefinition);
    }
    
    public void setDefinitionKeywords(List<String> keywords) throws IllegalTermStateException{
        try {
            String definition = styledDefinition.getText(0, styledDefinition.getLength());
            checkKeywords(keywords, definition);
        } catch (BadLocationException ex) {
            System.getLogger(AdvancedTerm.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            throw new IllegalTermStateException("Could not get text from styled document associated with this term.", ex, IllegalTermStateException.TYPE.BAD_LOCATION);
        }
        
        this.definitionKeywords.clear();
        this.definitionKeywords.addAll(keywords);
        Collections.sort(this.definitionKeywords);
    }
    
    public void setSuperCategory(String superCategory){
        this.superCategory = superCategory;
    }
    
    public void setMiddleCategory(String middleCategory){
        this.middleCategory = middleCategory;
    }
    
    public void setSubCategory(String subCategory){
        this.subCategory = subCategory;
    }
    
    public void setPage(int page){
        this.page = page;
    }
    
    public void replaceHyperlinks(List<AdvancedHyperlink> advHyper) throws IllegalTermStateException{
        
        if (advHyper.size() > MAX_HYPERLINKS) {
            throw new IllegalTermStateException("Number of hyperlinks exceeds the maximum allowed.", IllegalTermStateException.TYPE.TOO_MANY_ITEMS);
        }
        
        for (int i = 0; i < advHyper.size(); i++) {
            AdvancedHyperlink adv = advHyper.get(i);
            if(adv == null){
                throw new IllegalTermStateException("Hyperlink at index (" + Integer.toString(i)+") of provided List oject was found to be null.", IllegalTermStateException.TYPE.EMPTY_HYPERLINK);
            }
        }
        hyperlinks.clear();
        hyperlinks.addAll(advHyper);
        
        while (this.hyperlinks.size() < MAX_HYPERLINKS) {
            this.hyperlinks.add(new AdvancedHyperlink());
        }
    }
    
    public void replaceHyperlink(String hyperlink, String encapsulation, int index) throws IllegalTermStateException {
        if (index < 0 || index >= MAX_HYPERLINKS) {
            throw new IllegalArgumentException("Index is out of bounds: " + index);
        }
        AdvancedHyperlink advHyper = hyperlinks.get(index);
        try{
            advHyper.setHyperlink(hyperlink, encapsulation);
        }
        catch(IllegalTermStateException e){
            
            throw new IllegalTermStateException(e.getMessage() + ": (replacement attempted at index " + Integer.toString(index) + ")", e, e.getType());
        }
    }
    
    public void arrangeHyperlinks(){
        List<AdvancedHyperlink> toSet = new ArrayList<>();
        for(AdvancedHyperlink adv : this.hyperlinks){
            if(!adv.isDefault()){
                toSet.add(adv);
            }
        }
        
        this.hyperlinks.clear();
        this.hyperlinks.addAll(toSet);
        while (this.hyperlinks.size() < MAX_HYPERLINKS) {
            this.hyperlinks.add(new AdvancedHyperlink());
        }
    }
    
    
    private void checkSpelling(String spelling) throws IllegalTermStateException{
        if(spelling == null || spelling.isEmpty()){
            throw new IllegalTermStateException("Spelling can not be empty or null", IllegalTermStateException.TYPE.EMPTY_SPELLING);
        }
        else {
            try{
                Paths.get(spelling); // Automcatically handles all invalid characters.
            }catch(InvalidPathException e){
                throw  new IllegalTermStateException("Illegal character found: " + e.getReason(), e, IllegalTermStateException.TYPE.INVALID_PATH_EXCEPTION);
            }
        }
    }
    
    private void checkKeywords(List<String> keywords, String definition) throws IllegalTermStateException{
        int index = 0;
        for(String keyword : keywords){
            if(keyword == null){
                throw new IllegalTermStateException("One of the keyword was found to be null. (at index: " + Integer.toString(index) + ")", IllegalTermStateException.TYPE.KEYWORD_NOTFOUND);
            }
            
            if(!definition.toLowerCase().contains(keyword.toLowerCase())){
                throw new IllegalTermStateException("Definition contained in the styled Document associated with this term does not contain: '" + keyword + "' keyword. (found at index: " + Integer.toString(index) + ")", IllegalTermStateException.TYPE.KEYWORD_NOTFOUND);
            }
            index++;
        }
    }
    
    
    @Override
    public String toString() {
        String definitionText;
        try {
            definitionText = this.styledDefinition.getText(0, this.styledDefinition.getLength());
        } catch (BadLocationException e) {
            definitionText = "Error retrieving styled definition text.";
        }

        String hyperlinksString = this.hyperlinks.stream()
                .map(h -> h.toString())
                .collect(Collectors.joining(", ", "[", "]"));

        String keywordsString = this.definitionKeywords.stream()
                .collect(Collectors.joining(", ", "[", "]"));

        return "AdvancedTerm{" +
                "spelling='" + spelling + '\'' +
                ", absoluteSpelling='" + absoluteSpelling + '\'' +
                ", styledDefinition='" + definitionText + '\'' +
                ", superCategory='" + superCategory + '\'' +
                ", middleCategory='" + middleCategory + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", page=" + page +
                ", hyperlinks=" + hyperlinksString +
                ", definitionKeywords=" + keywordsString +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdvancedTerm other = (AdvancedTerm) obj;
        
        try {
        return  Objects.equals(this.spelling, other.spelling) && 
                Objects.equals(this.absoluteSpelling, other.absoluteSpelling) &&
                Objects.equals(this.superCategory, other.superCategory) &&
                Objects.equals(this.middleCategory, other.middleCategory) &&
                Objects.equals(this.subCategory, other.subCategory) &&
                Objects.equals(this.definitionKeywords, other.definitionKeywords) &&
                Objects.equals(this.hyperlinks, other.hyperlinks) &&
                this.page == other.page &&
                this.styledDefinition.getText(0, this.styledDefinition.getLength()).equals(other.styledDefinition.getText(0, other.styledDefinition.getLength()));
        
        } catch (BadLocationException e) {
            System.getLogger(AdvancedTerm.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);
            return false;
        }
    }

    @Override
    public int hashCode() {
        // You must also consider the content of the StyledDocument
        String definitionText;
        try {
            definitionText = this.styledDefinition.getText(0, this.styledDefinition.getLength());
        } catch (BadLocationException e) {
            definitionText = ""; // Default to an empty string on error
        }

        return Objects.hash(spelling, absoluteSpelling, definitionText, superCategory, middleCategory, subCategory, page, hyperlinks, definitionKeywords);
    }
}
