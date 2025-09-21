/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package backend.v2.data;

/**
 *
 * @author BRIN
 * @param <T>
 */
@FunctionalInterface
public interface IOCallable<T> {
    
    T call() throws Exception;
}
