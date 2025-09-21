/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package frontend.v2.state;

import java.util.EventListener;

/**
 *
 * @author BRIN
 */
public interface ActiveSearchedTermChangeListener extends EventListener{
    
    void activeSearchedTermChanged(ActiveSearchedTermChangeEvent e);
}
