/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.v2.data;

/**
 *
 * @author BRIN
 */
public class TermComparatorException extends RuntimeException{
    public enum TYPE {
        UNDEFINED,
        NULL_DOCUMENT
    }
    
    private TYPE type = TYPE.UNDEFINED;

    public TermComparatorException() {
        super();
    }
    
    public TermComparatorException(TYPE type) {
        super();
        this.type = type;
    }
    
    public TermComparatorException(String msg){
        super(msg);
    }
    
    public TermComparatorException(String msg, TYPE type){
        super(msg);
        this.type = type;
    }
    
    public TermComparatorException(Throwable cause){
        
        super(cause);
    }
    
    public TermComparatorException(Throwable cause, TYPE type){
        super(cause);
        this.type = type;
    }
    
    public TermComparatorException(String msg, Throwable cause){
        super(msg, cause);
    }
    
    public TermComparatorException(String msg, Throwable cause, TYPE type){
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
