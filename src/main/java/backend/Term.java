/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend;

import java.util.ArrayList; // Import ArrayList for mutable list
import java.util.List;
import java.util.Objects; // Used for Objects.requireNonNull
// import java.lang.Math; // Used for Math.min  (implicitly imported by compiler for all Classes in java.lang package

/**
 * Represents a vocabulary term with its definition, contextual information,
 * and associated hyperlinks. This class is designed to be mutable,
 * allowing its state to be changed after creation.
 *
 * @author BRIN (original author)
 */
public class Term {

    // Core attributes of the term, now NOT final to allow mutability.
    private String spelling;
    private String definition;
    private String chapter;
    private String majorTopic;
    private String subtopic;
    private int page;
    
    private String absoluteSpelling;
    

    // A mutable list for hyperlinks, allowing for up to 6 links.
    private List<String> hyperlinks;
    private List<String> hyperlinkEncapsulation;
    
    
    

    // --- Static Constants for Default Messages ---
    private static final String DEFAULT_DEFINITION_PREFIX = "No definition available for '";
    private static final String DEFAULT_CHAPTER_PREFIX = "No parent chapter is decided for '";
    private static final String DEFAULT_MAJOR_TOPIC_PREFIX = "No major topic to which '";
    private static final String DEFAULT_SUBTOPIC_PREFIX = "No sub topic to which '";
    private static final String DEFAULT_SUFFIX = "'";


    /**
     * Constructor for creating a Term with only its spelling,
     * using default values for all other fields.
     *
     * @param spelling The primary spelling of the term. Must not be null.
     */
    public Term(String spelling) {
        
        this.spelling = Objects.requireNonNull(spelling, "Spelling cannot be null");

        this.absoluteSpelling = this.spelling;
        this.spelling = this.spelling.toLowerCase();
        // Initialize other fields with default messages,
        // ensuring 'spelling' is correctly interpolated.
        this.definition = DEFAULT_DEFINITION_PREFIX + spelling + DEFAULT_SUFFIX;
        this.chapter = DEFAULT_CHAPTER_PREFIX + spelling + DEFAULT_SUFFIX;
        this.majorTopic = DEFAULT_MAJOR_TOPIC_PREFIX + spelling + DEFAULT_SUFFIX;
        this.subtopic = DEFAULT_SUBTOPIC_PREFIX + spelling + DEFAULT_SUFFIX;
        this.page = 0; // Default page number

        // Initialize hyperlinks as an empty, mutable list.
        this.hyperlinks = new ArrayList<>();
        this.hyperlinkEncapsulation = new ArrayList<>();
        for (int i = 0; i < 6; i++){
                this.hyperlinks.add(AppConstants.HYPERLINK_DEFAULT.replaceFirst("%", Integer.toString(i + 1)).replaceFirst("&", this.spelling));
                this.hyperlinkEncapsulation.add(AppConstants.ENCAPSULATION_DEFAULT.replaceFirst("%", Integer.toString(i + 1)).replaceFirst("&", this.spelling));
        } // Default Values for hyperlink String.
    }

    /**
     * Comprehensive constructor for creating a Term with all its details.
     * Provides options for null or empty string inputs to fall back to defaults.
     * Enforces a maximum of 6 hyperlinks.
     *
     * @param spelling The primary spelling of the term. Must not be null.
     * @param definition The definition of the term. If null or empty, a default message is used.
     * @param chapter The chapter the term belongs to. If null or empty, a default message is used.
     * @param majorTopic The major topic the term belongs to. If null or empty, a default message is used.
     * @param subtopic The subtopic the term belongs to. If null or empty, a default message is used.
     * @param page The page number where the term is found.
     * @param hyperlinks A list of hyperlinks associated with the term. If more than 6 are provided,
     * only the first 6 will be used. If null, an empty list is used.
     */
    public Term(String spelling, String definition, String chapter, String majorTopic,
                String subtopic, int page, List<String> hyperlinks, List<String> hyperlinkEncapsulation) {
        this.spelling = Objects.requireNonNull(spelling, "Spelling cannot be null");
        this.absoluteSpelling = this.spelling;
        
        this.spelling = this.spelling.toLowerCase();

        // Use ternary operator to check for null or empty strings and assign defaults (? = ternary operator - short hand way of writing if-else)
        this.definition = (definition != null && !definition.trim().isEmpty()) ? definition : DEFAULT_DEFINITION_PREFIX + spelling + DEFAULT_SUFFIX;
        this.chapter = (chapter != null && !chapter.trim().isEmpty()) ? chapter : DEFAULT_CHAPTER_PREFIX + spelling + DEFAULT_SUFFIX;
        this.majorTopic = (majorTopic != null && !majorTopic.trim().isEmpty()) ? majorTopic : DEFAULT_MAJOR_TOPIC_PREFIX + spelling + DEFAULT_SUFFIX;
        this.subtopic = (subtopic != null && !subtopic.trim().isEmpty()) ? subtopic : DEFAULT_SUBTOPIC_PREFIX + spelling + DEFAULT_SUFFIX;

        this.page = page; // Page is a primitive int, so no null check needed.

        // Create a new mutable ArrayList from the incoming list, enforcing max 6 hyperlinks.
        this.hyperlinks = new ArrayList<>();
        this.hyperlinkEncapsulation = new ArrayList<>();
        
        
        if (hyperlinks != null) {
            // Add only up to the first 6 hyperlinks
            for (int i = 0; i < Math.min(hyperlinks.size(), 6); i++) {
                this.hyperlinks.add(hyperlinks.get(i));
                this.hyperlinkEncapsulation.add(hyperlinkEncapsulation.get(i));
            }
            
            for (int i = hyperlinks.size(); i < 6; i++){
                this.hyperlinks.add("Hyperlink for '" + this.spelling + "' not available yet");
                this.hyperlinkEncapsulation.add(AppConstants.HYPERLINK_DEFAULT.replaceFirst("%", Integer.toString(i + 1)).replaceFirst("&", this.spelling));
            }
        }
        else{
            
            for (int i = 0; i < 6; i++){
                this.hyperlinks.add("Hyperlink " + Integer.toString(i) + " for '" + this.spelling + "' not available yet");
                this.hyperlinkEncapsulation.add(AppConstants.ENCAPSULATION_DEFAULT.replaceFirst("%", Integer.toString(i + 1)).replaceFirst("&", this.spelling));
            }
        }
    }

    // --- Public Getter Methods ---
    // These methods allow external code to access the term's properties.

    public String getAbsoluteSpelling(){
        
        return this.absoluteSpelling;
    }
    
    public String getSpelling() {
        return spelling;
    }

    public String getDefinition() {
        return definition;
    }

    public String getChapter() {
        return chapter;
    }

    public String getMajorTopic() {
        return majorTopic;
    }

    public String getSubtopic() {
        return subtopic;
    }

    public int getPage() {
        return page;
    }

    /**
     * Returns the mutable list of hyperlinks.
     * Modifications to this list will affect the Term object's state.
     *
     * @return A mutable List of Strings representing hyperlinks.
     */
    public List<String> getHyperlinks() {
        return hyperlinks;
    }
    
    public List<String> getHyperlinkEncapsulation(){
        
        return this.hyperlinkEncapsulation;
    }
    
    public String getHyperlinkByIndex(int index){
        if(index < 0 || index > 5 ){
            throw new IllegalArgumentException("Index should be between 0 and 5");
        }
        return this.hyperlinks.get(index);
    }
    
    public  String getHyperlinkEncapsulationByIndex(int index){
        
        if(index < 0 || index > 5 ){
            throw new IllegalArgumentException("Index should be between 0 and 5");
        }
        return this.hyperlinkEncapsulation.get(index);
    }

    // --- Public Setter Methods ---
    // These methods allow external code to modify the term's properties,
    // making the Term object mutable.

    public void setSpelling(String spelling) {
        this.spelling = Objects.requireNonNull(spelling, "Spelling cannot be null");
        this.absoluteSpelling = this.spelling;
        this.spelling = this.spelling.toLowerCase();
        
    }

    public void setDefinition(String definition) {
        this.definition = (definition != null && !definition.trim().isEmpty()) ? definition : DEFAULT_DEFINITION_PREFIX + this.spelling + DEFAULT_SUFFIX;
    }

    public void setChapter(String chapter) {
        this.chapter = (chapter != null && !chapter.trim().isEmpty()) ? chapter : DEFAULT_CHAPTER_PREFIX + this.spelling + DEFAULT_SUFFIX;
    }

    public void setMajorTopic(String majorTopic) {
        this.majorTopic = (majorTopic != null && !majorTopic.trim().isEmpty()) ? majorTopic : DEFAULT_MAJOR_TOPIC_PREFIX + this.spelling + DEFAULT_SUFFIX;
    }

    public void setSubtopic(String subtopic) {
        this.subtopic = (subtopic != null && !subtopic.trim().isEmpty()) ? subtopic : DEFAULT_SUBTOPIC_PREFIX + this.spelling + DEFAULT_SUFFIX;
    }

    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Sets the list of hyperlinks for the term, enforcing a maximum of 6.
     * If the provided list contains more than 6 elements, only the first 6 will be used.
     * If the provided list is null, the hyperlinks list will be cleared.
     *
     * @param hyperlinks The new list of hyperlinks.
     */
    public void setHyperlinks(List<String> hyperlinks) {
        this.hyperlinks.clear(); // Clear existing links
        if (hyperlinks != null) {
            // Add only up to the first 6 hyperlinks
            // Math.min cleverly decided the limit of the loop, if its lower than 6 say 3 then i will increase only upto 3
            // This prevents any index out of bound exception.
            // If the provided hyperlink array is more than 6 only first 6 will be copied.
            for (int i = 0; i < Math.min(hyperlinks.size(), 6); i++) {
                this.hyperlinks.add(hyperlinks.get(i));
            }
            
            for (int i = hyperlinks.size(); i < 6; i++){
                this.hyperlinks.add(AppConstants.HYPERLINK_DEFAULT.replaceFirst("%", Integer.toString(i + 1)).replaceFirst("&", this.spelling));
            }
        }
        else{
            
            for (int i = 0; i < 6; i++){
                this.hyperlinks.add(AppConstants.HYPERLINK_DEFAULT.replaceFirst("%", Integer.toString(i + 1)).replaceFirst("&", this.spelling));
            }
        } 
    }
    
    
    public void setHyperlinksEncapsulation(List<String> hyperlinksEncapsulation) {
        this.hyperlinkEncapsulation.clear(); // Clear existing links
        if (hyperlinksEncapsulation != null) {
            // Add only up to the first 6 hyperlinks
            // Math.min cleverly decided the limit of the loop, if its lower than 6 say 3 then i will increase only upto 3
            // This prevents any index out of bound exception.
            // If the provided hyperlink array is more than 6 only first 6 will be copied.
            for (int i = 0; i < Math.min(hyperlinksEncapsulation.size(), 6); i++) {
                this.hyperlinkEncapsulation.add(hyperlinksEncapsulation.get(i));
            }
            
            for (int i = hyperlinksEncapsulation.size(); i < 6; i++){
                this.hyperlinkEncapsulation.add(AppConstants.ENCAPSULATION_DEFAULT.replaceFirst("%", Integer.toString(i + 1)).replaceFirst("&", this.spelling));
            }
        }
        else{
            
            for (int i = 0; i < 6; i++){
                this.hyperlinkEncapsulation.add(AppConstants.ENCAPSULATION_DEFAULT.replaceFirst("%", Integer.toString(i + 1)).replaceFirst("&", this.spelling));
            }
        } 
    }
    
    

    /**
     * Adds a single hyperlink to the term, if the maximum number of hyperlinks (6) has not been reached.
     * The hyperlink must not be null or empty.
     *
     * @param hyperlink The hyperlink to add.
     * @return true if the hyperlink was successfully added, false otherwise (e.g., if max limit reached or invalid hyperlink).
     */
    public boolean addHyperlink(String hyperlink, String hyperlinkEncapsulation) {
        if (hyperlink != null && hyperlinkEncapsulation != null 
                && !hyperlink.trim().isEmpty() && !hyperlinkEncapsulation.trim().isBlank()
                && (this.hyperlinks.size() < 6 || this.hyperlinkEncapsulation.size() < 6)) {
            return (this.hyperlinks.add(hyperlink) || this.hyperlinkEncapsulation.add(hyperlinkEncapsulation));
        }
        return false;
    }
    
    

    /**
     * Provides a string representation of the Term object, useful for debugging and logging.
     *
     * @return A formatted string containing the Term's details.
     */
    @Override
    public String toString() {
        return "Term{" +
               "spelling='" + spelling + '\'' +
               ", definition='" + definition + '\'' +
               ", chapter='" + chapter + '\'' +
               ", majorTopic='" + majorTopic + '\'' +
               ", subtopic='" + subtopic + '\'' +
               ", page=" + page +
               ", hyperlinks=" + hyperlinks +
               ", encapsulating text=" + hyperlinkEncapsulation +
               '}';
    }

    // --- hashCode() and equals() ---
    // These methods are still important for mutable objects if they are used in collections.
    // However, be aware that changing an object's state (e.g., a field used in hashCode)
    // while it's in a hash-based collection (like HashMap or HashSet) can lead to unexpected behavior.
    // It's generally safer to use immutable objects as keys in hash collections.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return page == term.page &&
               Objects.equals(spelling, term.spelling) &&
               Objects.equals(definition, term.definition) &&
               Objects.equals(chapter, term.chapter) &&
               Objects.equals(majorTopic, term.majorTopic) &&
               Objects.equals(subtopic, term.subtopic) &&
               Objects.equals(hyperlinks, term.hyperlinks) &&
               Objects.equals(hyperlinkEncapsulation, term.hyperlinkEncapsulation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spelling, definition, chapter, majorTopic, subtopic, page, hyperlinks, hyperlinkEncapsulation);
    }
}
