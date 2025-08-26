/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book.HarpersIllustratedBiochemistry;

import java.util.List;

/**
 *
 * @author BRIN
 */
public enum Section {
    SECTION_1("Structures & Functions of Proteins & Enzymes", Content.getContentBySection(1)),
    SECTION_2("Enzymes: Kinetics, Mechanism, Regulation, & Role of Transition Metals", Content.getContentBySection(2)),
    SECTION_3("Bioenergetics", Content.getContentBySection(3)),
    SECTION_4("Metabolism of Carbohydrates", Content.getContentBySection(4)),
    SECTION_5("Metabolism of Lipids", Content.getContentBySection(5)),
    SECTION_6("Metabolism of Proteins & Amino Acids", Content.getContentBySection(6)),
    SECTION_7("Structure, Function, & Replication of Informational Molecules", Content.getContentBySection(7)),
    SECTION_8("Biochemistry of Extracellular & Intracellular Communication", Content.getContentBySection(8)),
    SECTION_9("Special Topics (A)", Content.getContentBySection(9)),
    SECTION_10("Special Topics (B)", Content.getContentBySection(10)),
    SECTION_11("Special Topics (C)", Content.getContentBySection(11));

        
    private final String title;
    private final List<Content> content;
    
    private Section(java.lang.String title, java.util.List<book.HarpersIllustratedBiochemistry.Content> content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public List<Content> getContent() {
        return content;
    }
    
    
}
