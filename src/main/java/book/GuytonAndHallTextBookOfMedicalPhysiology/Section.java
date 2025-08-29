/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book.GuytonAndHallTextBookOfMedicalPhysiology;

import java.util.List;

/**
 *
 * @author BRIN
 */
public enum Section {
    
    SECTION_1("General Physiology", Content.getContentBySection(1)),
    SECTION_2("Nerve and Muscle Physiology", Content.getContentBySection(2)),
    SECTION_3("Blood and Its Constituents", Content.getContentBySection(3)),
    SECTION_4("Cardiovascular Physiology", Content.getContentBySection(4)),
    SECTION_5("Respiratory Physiology", Content.getContentBySection(5)),
    SECTION_6("Gastrointestinal Physiology", Content.getContentBySection(6)),
    SECTION_7("Renal Physiology", Content.getContentBySection(7)),
    SECTION_8("The Endocrine System", Content.getContentBySection(8)),
    SECTION_9("Reproductive Physiology", Content.getContentBySection(9)),
    SECTION_10_0("Central Nervous System - Introduction", Content.getContentBySection(10).subList(0, 1)),
    SECTION_10_1("Central Nervous System - Sensory System", Content.getContentBySection(10).subList(1, 6)),
    SECTION_10_2("Central Nervous System - Special Senses", Content.getContentBySection(10).subList(6, 11)),
    SECTION_10_3("Central Nervous System - Motor System", Content.getContentBySection(10).subList(11, 19)),
    SECTION_10_4("Central Nervous System - Other Functions and Activities of the Brain", Content.getContentBySection(10).subList(19, 27)),
    SECTION_11("Integrative Physiology", Content.getContentBySection(11));
    
    private final String title;
    private final List<Content> content;

    private Section(String title, List<Content> content) {
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
