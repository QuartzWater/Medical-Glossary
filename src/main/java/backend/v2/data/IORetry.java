/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.v2.data;


import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author BRIN
 */
public class IORetry {
    
        
    public static <T> T execute(IOCallable<T> callable, int maxRetries, long retryDelay, int exponent, TermDataIOException completeIOFail, List<Predicate<Exception>> toTest) throws TermDataIOException{
        boolean failedIO;
        int retry = 1;
        IOException currentException = null;
        do {
            failedIO = false;

            try {
                return callable.call();
            }
            catch(IOException e){
                System.getLogger(AdvancedTermDataManagement.class.getName()).log(System.Logger.Level.ERROR, "I/O operation failed. Retrying...", e);
                
                boolean match = false;
                
                for (Predicate<Exception> exception : toTest) {
                    match = exception.test(e);
                    if(match){
                        currentException = e;
                        break;
                    }
                }
                
                if(toTest.isEmpty()){
                    match = true;
                }
                
                if(match){
                    failedIO = true;
                    
                }
                else {
                    throw new TermDataIOException("Non-Retriable IOException encountered!: " + e.getClass().getName(), e, TermDataIOException.TYPE.NON_RETRIABLE_IO_FAIL);
                }
                
                if(retry <= maxRetries){
                    try {
                        Thread.sleep(retryDelay);
                    } catch (InterruptedException ex) {
                        System.getLogger(IORetry.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        throw new TermDataIOException("Unexpected Thread Interruption while retrying to recover from Failed IO operation.", TermDataIOException.TYPE.RETRY_THREAD_INTERRUPTED);
                    }
                    retryDelay *= exponent;
                    retry++;
                }
            }
            catch(Exception e){
                throw new TermDataIOException("Unexpected Exception: " + e.getMessage(), e, TermDataIOException.TYPE.UNDEFINED);
            }
            
            if(retry > maxRetries && failedIO){
                throw new TermDataIOException(completeIOFail.getMessage(), currentException, TermDataIOException.TYPE.UNDEFINED_BAD_LOCATION);
                
            }

        }while(retry <= maxRetries && failedIO);

        return null;
    }
    
}

