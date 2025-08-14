/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package legacy;

/**
 *
 * @author BRIN
 */
public interface AfterEventCode {
    
    public void runCode();
    
    default void start(){
        runCode();
    }
}
