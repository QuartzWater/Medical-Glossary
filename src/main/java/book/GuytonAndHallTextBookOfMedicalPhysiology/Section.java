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
    
    SECTION_1("", Content.getContentBySection(1)),
    SECTION_2("", Content.getContentBySection(2)),
    SECTION_3("", Content.getContentBySection(3)),
    SECTION_4("", Content.getContentBySection(4)),
    SECTION_5("", Content.getContentBySection(5)),
    SECTION_6("", Content.getContentBySection(6)),
    SECTION_7("", Content.getContentBySection(7)),
    SECTION_8("", Content.getContentBySection(8)),
    SECTION_9("", Content.getContentBySection(9)),
    SECTION_10_0("", Content.getContentBySection(10).subList(0, 1)),
    SECTION_10_1("", Content.getContentBySection(10).subList(1, 6)),
    SECTION_10_2("", Content.getContentBySection(10).subList(6, 11)),
    SECTION_10_3("", Content.getContentBySection(10).subList(11, 19)),
    SECTION_10_4("", Content.getContentBySection(10).subList(19, 27)),
    SECTION_11("", Content.getContentBySection(11));
    
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
