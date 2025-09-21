/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.v2.data;

import backend.v2.term.AdvancedHyperlink;
import backend.v2.term.AdvancedTerm;
import backend.v2.term.RTFDocumentUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.StyledDocument;

/**
 *
 * @author BRIN
 */
public final class TermComparator {
    // TODO: ADD MORE TYPES
    public enum DIFFERENCE_TYPE{
        BAD_LOCATION,
        SPELLING,
        DEFINITION,
        DEFINITION_LENGTH,
        DEFINITION_TEXT,
        DEFINITION_STYLE,
        DEFINITION_STYLE_BRANCHING,
        DEFINITION_STYLE_ATTRIBUTES,
        DEFINITION_STYLE_DOCUMENT_STRUCTURE_ELEMENT_COUNT,
        KEYWORD,
        REFERENCE,
        REFERENCE_SUPER_CATEGORY,
        REFERENCE_MIDDLE_CATEGORY,
        REFERENCE_SUB_CATEGORY,
        PAGE,
        HYPERLINK,
        
    }
    
    public enum ComparisionStrength{
    
        EXHAUSTIVE,
        CONSERVATIVE
    }
    
    public static Set<DIFFERENCE_TYPE> compare(AdvancedTerm advTerm1, AdvancedTerm advTerm2, ComparisionStrength strength){
        Set<DIFFERENCE_TYPE> difference_types = new HashSet<>();
        
        difference_types.addAll(compareSpellingAndPage(advTerm1, advTerm2));
        
        difference_types.addAll(compareStyledDocument(advTerm1.getStyledDefinition(), advTerm2.getStyledDefinition(), strength));
        
        difference_types.addAll(compareReference(advTerm1, advTerm2));
        
        List<String> differentHyperlinks = compareHyperlinks(advTerm1, advTerm2);
        if(!differentHyperlinks.isEmpty()){
            difference_types.add(DIFFERENCE_TYPE.HYPERLINK);
        }
        
        difference_types.addAll(compareKeywords(advTerm1, advTerm2));
        
        return Collections.unmodifiableSet(difference_types);
    }
    
    public final static Set<DIFFERENCE_TYPE> compareStyledDocument(StyledDocument styledDoc1, StyledDocument styledDoc2, ComparisionStrength strength){
        Set<DIFFERENCE_TYPE> differenceType = new HashSet<>();
        
        if(styledDoc1 == null){
            throw new TermComparatorException("First Argument (styleDoc1): Document type obtained is unexpectedly null.", TermComparatorException.TYPE.NULL_DOCUMENT);
        }
        else if(styledDoc2 == null){
            throw new TermComparatorException("Second Argument (styleDoc2): Document type obtained is unexpectedly null.", TermComparatorException.TYPE.NULL_DOCUMENT);
        }
        
        RTFDocumentUtils.stripNewLines(styledDoc1);
        RTFDocumentUtils.stripNewLines(styledDoc2);
        
        /*
        try {
            String text1 = styledDoc1.getText(0, styledDoc1.getLength());
            String text2 = styledDoc2.getText(0, styledDoc2.getLength());

            if(text1.endsWith("\n")){
                System.out.println("Text1 ends with new line");
                styledDoc1.remove(styledDoc1.getLength() - 1, 1);
            }
            if(text2.endsWith("\n")){
                System.out.println("Text2 ends with new line");
                styledDoc2.remove(styledDoc2.getLength() - 1, 1);
            }

        } catch (BadLocationException ex) {
            System.getLogger(TermComparator.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        */
        
        if(styledDoc1.getLength() != styledDoc2.getLength()){
            
            differenceType.add(DIFFERENCE_TYPE.DEFINITION);
            differenceType.add(DIFFERENCE_TYPE.DEFINITION_LENGTH);
            if(strength == ComparisionStrength.CONSERVATIVE){
                return differenceType;
            }
            
        }
        
        Element element1 = styledDoc1.getDefaultRootElement();
        Element element2 = styledDoc2.getDefaultRootElement();
        
        compareElements(element1, element2, differenceType, strength);
        
        if(differenceType.contains(DIFFERENCE_TYPE.DEFINITION_TEXT) || differenceType.contains(DIFFERENCE_TYPE.DEFINITION_STYLE)){
            differenceType.add(DIFFERENCE_TYPE.DEFINITION);
        }
        
        return Collections.unmodifiableSet(differenceType);
    }

    
    private static void compareElements(Element e1, Element e2, Set<DIFFERENCE_TYPE> differenceType, ComparisionStrength strength){
        if((differenceType.contains(DIFFERENCE_TYPE.DEFINITION_STYLE) || differenceType.contains(DIFFERENCE_TYPE.DEFINITION_TEXT) || differenceType.contains(DIFFERENCE_TYPE.DEFINITION_LENGTH)) && strength == ComparisionStrength.CONSERVATIVE){
            return;
        }
        
        if(e1 == null || e2 == null){
            if(e1 == e2){
                return;
            }else {
                 System.out.println("element null disparity");
                differenceType.add(DIFFERENCE_TYPE.DEFINITION_STYLE);
                return;
            }
        }

        if(e1.isLeaf() != e2.isLeaf()){
            System.out.println("element branching disparity disparity");
            differenceType.add(DIFFERENCE_TYPE.DEFINITION_STYLE);
            if(strength == ComparisionStrength.EXHAUSTIVE){
                differenceType.add(DIFFERENCE_TYPE.DEFINITION_STYLE_BRANCHING);
            }
        }

        if (e1.isLeaf() && e2.isLeaf()) {
            try {
                String text1 = e1.getDocument().getText(e1.getStartOffset(), e1.getEndOffset() - e1.getStartOffset());
                String text2 = e2.getDocument().getText(e2.getStartOffset(), e2.getEndOffset() - e2.getStartOffset());
                if(text1.length() != text2.length()){
                    differenceType.add(DIFFERENCE_TYPE.DEFINITION_LENGTH);
                }
                if(!text1.equals(text2)){
                    differenceType.add(DIFFERENCE_TYPE.DEFINITION_TEXT);
                }
            } catch (BadLocationException e) {
                System.getLogger(AdvancedTermDataManagement.class.getName()).log(System.Logger.Level.ERROR, () -> "Unexpected Bad Location while trying to compare two StyledDocuments. At TermComparator class.", e);
                differenceType.add(DIFFERENCE_TYPE.BAD_LOCATION);
            }
            
            if(strength == ComparisionStrength.EXHAUSTIVE){
                compareLeafAttributes(e1.getAttributes(), e2.getAttributes(), differenceType);
            }
        }

        if (!e1.getAttributes().isEqual(e2.getAttributes())) {
            System.out.println("element attributes disparity");
            differenceType.add(DIFFERENCE_TYPE.DEFINITION_STYLE);
            if(strength == ComparisionStrength.EXHAUSTIVE){
                differenceType.add(DIFFERENCE_TYPE.DEFINITION_STYLE_ATTRIBUTES);
            }
        }
        

        if (e1.getElementCount() != e2.getElementCount()) {
            System.out.println("element count disparity");
            differenceType.add(DIFFERENCE_TYPE.DEFINITION_STYLE);
            if(strength == ComparisionStrength.EXHAUSTIVE){
                differenceType.add(DIFFERENCE_TYPE.DEFINITION_STYLE_DOCUMENT_STRUCTURE_ELEMENT_COUNT);
            }
        }
        
        for (int i = 0; i < Math.min(e1.getElementCount(), e2.getElementCount()); i++) {
            compareElements(e1.getElement(i), e2.getElement(i), differenceType, strength);
        }
        
    }
    
    private static void compareLeafAttributes(AttributeSet attributes1, AttributeSet attributes2, Set<DIFFERENCE_TYPE> differenceType){
        
        if(attributes1 == null || attributes2 == null){
            if(attributes1 == attributes2){
                return;
            }
            else{
                differenceType.add(DIFFERENCE_TYPE.DEFINITION_STYLE_ATTRIBUTES);
                return;
            }
        }
        
        // TODO
    }
    
    public final static Set<DIFFERENCE_TYPE> compareSpellingAndPage(AdvancedTerm advTerm1, AdvancedTerm advTerm2){
        Set<DIFFERENCE_TYPE> differenceType = new HashSet<>();
        
        if(!advTerm1.getSpelling().equals(advTerm2.getSpelling())){
            differenceType.add(DIFFERENCE_TYPE.SPELLING);
        }
        
        if(advTerm1.getPage() != advTerm2.getPage()){
            differenceType.add(DIFFERENCE_TYPE.PAGE);
        }
        return Collections.unmodifiableSet(differenceType);
    }
    
    public final static Set<DIFFERENCE_TYPE> compareReference(AdvancedTerm advTerm1, AdvancedTerm advTerm2){
        
        Set<DIFFERENCE_TYPE> differenceType = new HashSet<>();
        if(!advTerm1.getSuperCateogry().equals(advTerm2.getSuperCateogry())){
            
            differenceType.add(DIFFERENCE_TYPE.REFERENCE_SUPER_CATEGORY);
        }
        
        if(!advTerm1.getMiddleCategory().equals(advTerm2.getMiddleCategory())){
            
            differenceType.add(DIFFERENCE_TYPE.REFERENCE_MIDDLE_CATEGORY);
        }
        
        if(!advTerm1.getSubCategory().equals(advTerm2.getSubCategory())){
            
            differenceType.add(DIFFERENCE_TYPE.REFERENCE_SUB_CATEGORY);
        }
        
        if(!differenceType.isEmpty()){
            differenceType.add(DIFFERENCE_TYPE.REFERENCE);
        }
        
        return Collections.unmodifiableSet(differenceType);
        
    }
    
    public static List<String> compareHyperlinks(AdvancedTerm advTerm1, AdvancedTerm advTerm2){
        advTerm1.arrangeHyperlinks();
        advTerm2.arrangeHyperlinks();
        List<String> differenceList = new ArrayList<>();
        
        List<AdvancedHyperlink> advHyper1List = advTerm1.getHyperlinks();
        List<AdvancedHyperlink> advHyper2List = advTerm2.getHyperlinks();
        
        for(int i = 0; i < AdvancedTerm.MAX_HYPERLINKS; i++){
            
            AdvancedHyperlink advHyper1 = advHyper1List.get(i);
            AdvancedHyperlink advHyper2 = advHyper2List.get(i);
            
            if(!advHyper1.getHyperlink().equals(advHyper2.getHyperlink())){
                differenceList.add("HYPERLINK" + Integer.toString(i));
            }else if(!advHyper1.getEncapsulation().equals(advHyper2.getEncapsulation())){
                differenceList.add("ENCAPSULATION" + Integer.toString(i));
            }
        }
        
        return Collections.unmodifiableList(differenceList);
    }
    
    public static Set<DIFFERENCE_TYPE> compareKeywords(AdvancedTerm advTerm1, AdvancedTerm advTerm2){
        Set<DIFFERENCE_TYPE> differenceType = new HashSet<>();
        if(!advTerm1.getDefinitionKeywords().equals(advTerm2.getDefinitionKeywords())){
            differenceType.add(DIFFERENCE_TYPE.KEYWORD);
        }
        
        return Collections.unmodifiableSet(differenceType);
    }
    
    // compareKeywordsExhaustive method??
}
