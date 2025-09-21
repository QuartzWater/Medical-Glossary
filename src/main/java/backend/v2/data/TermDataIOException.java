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
        UNDEFINED,
        UNDEFINED_BAD_LOCATION,
        
        NON_RETRIABLE_IO_FAIL,
        RETRY_THREAD_INTERRUPTED,
        
        RTF_FILE_DELETE_FAIL,
        PROPERTIES_FILE_DELETE_FAIL,
        
        RTF_FILE_WRITE_FAIL,
        RTF_FILE_BAD_LOCATION,
        PROPERTIES_FILE_WRITE_FAIL,
        TEMP_RTF_FILE_WRITE_FAIL,
        TEMP_RTF_FILE_BAD_LOCATION,
        TEMP_RTF_FILE_ATOMICMOVE_UNSUPPORTED,
        TEMP_RTF_FILE_ATOMICMOVE_FAIL,
        TEMP_ALLTERMS_FILE_WRITE_FAIL,
        TEMP_ALLTERMS_FILE_ATOMICMOVE_UNSUPPORTED,
        TEMP_ALLTERMS_FILE_ATOMICMOVE_FAIL,
        TEMP_PROPERTIES_FILE_WRITE_FAIL,
        TEMP_PROPERTIES_FILE_ATOMICMOVE_UNSUPPORTED,
        TEMP_PROPERTIES_FILE_ATOMICMOVE_FAIL,
        
        INVALID_TERM_DATA,
        MALFORMED_DATA,
        INITIAL_LOAD_FAILED,
        IO_EXCEPTION
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
