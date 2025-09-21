/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.v2.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author BRIN
 */
public class StandardRetry {
    
    private List<Predicate<Exception>> retryableExceptions;
    private int maxRetries;
    private long initialRetryDelay;
    private int delayExponentialMultiplier;

    public StandardRetry(int maxRetries, long initialRetryDelay, int delayExponentialMultiplier) {
        this.retryableExceptions = new ArrayList<>();
        this.maxRetries = maxRetries;
        this.initialRetryDelay = initialRetryDelay;
        this.delayExponentialMultiplier = delayExponentialMultiplier;
    }

    public StandardRetry(List<Predicate<Exception>> retryableExceptions, int maxRetries, long initialRetryDelay, int delayExponentialMultiplier) {
        this(maxRetries, initialRetryDelay, delayExponentialMultiplier);
        this.retryableExceptions.addAll(retryableExceptions);
    }

    public void setRetryableExceptions(List<Predicate<Exception>> retryableExceptions) {
        this.retryableExceptions .clear();
        this.retryableExceptions.addAll(retryableExceptions);
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    public void setInitialRetryDelay(long initialRetryDelay) {
        this.initialRetryDelay = initialRetryDelay;
    }

    public void setDelayExponentialMultiplier(int delayExponentialMultiplier) {
        this.delayExponentialMultiplier = delayExponentialMultiplier;
    }

    public List<Predicate<Exception>> getRetryableExceptions() {
        return Collections.unmodifiableList(retryableExceptions);
    }

    public int getMaxRetries() {
        return maxRetries;
    }

    public long getInitialRetryDelay() {
        return initialRetryDelay;
    }

    public int getDelayExponentialMultiplier() {
        return delayExponentialMultiplier;
    }
    
    public void execute(IOCallable<?> callable, TermDataIOException fail) throws TermDataIOException{
        
        IORetry.execute(callable, maxRetries, initialRetryDelay, delayExponentialMultiplier, fail, retryableExceptions);
        
    }
    
}
