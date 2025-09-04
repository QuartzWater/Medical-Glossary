/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.v2.data;

import java.io.IOException;

/**
 *
 * @author BRIN
 */
public class TermDataIOException extends IOException{
    
    public enum TYPE {
        UNDEFINED, INVALID_TERM_DATA, MALFORMED_PROPERTIES, IO_EXCEPTION
    }
    
    private TYPE type = TYPE.UNDEFINED;

    public TermDataIOException() {
        super();
    }
    
    public TermDataIOException(TYPE type) {
        super();
        this.type = type;
    }
    
    public TermDataIOException(String msg){
        super(msg);
    }
    
    public TermDataIOException(String msg, TYPE type){
        super(msg);
        this.type = type;
    }
    
    public TermDataIOException(Throwable cause){
        
        super(cause);
    }
    
    public TermDataIOException(Throwable cause, TYPE type){
        super(cause);
        this.type = type;
    }
    
    public TermDataIOException(String msg, Throwable cause){
        super(msg, cause);
    }
    
    public TermDataIOException(String msg, Throwable cause, TYPE type){
        super(msg, cause);
        this.type = type;
    }
    
    public TYPE getType(){
        return this.type;
    }
    
    @Override
    public String getMessage(){
        String msg = super.getMessage() + ": " + type.toString();
        return msg;
    }
}
