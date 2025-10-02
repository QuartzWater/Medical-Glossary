/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend.v2.state;

import java.util.EventListener;

/**
 *
 * @author BRIN
 */
@FunctionalInterface
public interface QuickAccessListener extends  EventListener{
    
    void menuSelected(QuickAccessEvent e);
}
