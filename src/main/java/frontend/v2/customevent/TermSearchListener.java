/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package frontend.v2.customevent;

import java.util.EventListener;

/**
 *
 * @author BRIN
 */
public interface TermSearchListener extends EventListener {
    
    void termFound(TermSearchEvent e);
    void termNotFound(TermSearchEvent e);
}
