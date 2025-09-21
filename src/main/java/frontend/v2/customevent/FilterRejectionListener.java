/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend.v2.customevent;

import java.util.EventListener;

/**
 *
 * @author BRIN
 */
@FunctionalInterface
public interface FilterRejectionListener extends EventListener{
    
    void rejected(FilterRejectionEvent e);
}
