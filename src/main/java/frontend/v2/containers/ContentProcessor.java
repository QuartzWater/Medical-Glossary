/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package frontend.v2.containers;

import backend.v2.term.AdvancedTerm;
import book.bookpicker.Book;
import java.util.List;

/**
 *
 * @author BRIN
 */
public sealed interface ContentProcessor permits DefinitionContainer, ReferenceContainer, HyperlinkContainer {
    
    void setEditable();
    void setNonEditable();
    void reset();
    void setContent(Book book, AdvancedTerm advTerm);
    boolean wasContentChanged();
    void revertChanges();
    void saveOnlyThis();
    List<?> extractContent();
    String getHeaderName();
}
