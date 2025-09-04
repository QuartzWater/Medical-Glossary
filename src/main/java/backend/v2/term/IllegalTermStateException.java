/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.v2.term;

/**
 *
 * @author BRIN
 */
public class IllegalTermStateException extends Exception {
    
    public enum TYPE {
        UNDEFINED, EMPTY_SPELLING, KEYWORD_NOTFOUND, INVALID_PAIRING, EMPTY_HYPERLINK, EMPTY_ENCAPSULATION, MALFORMED_PROTOCOL, TOO_MANY_ITEMS, INVALID_PATH_EXCEPTION
    }
    
    private TYPE type = TYPE.UNDEFINED;

    public IllegalTermStateException() {
        super();
    }
    
    public IllegalTermStateException(TYPE type) {
        super();
        this.type = type;
    }
    
    public IllegalTermStateException(String msg){
        super(msg);
    }
    
    public IllegalTermStateException(String msg, TYPE type){
        super(msg);
        this.type = type;
    }
    
    public IllegalTermStateException(Throwable cause){
        super(cause);
    }
    
    public IllegalTermStateException(Throwable cause, TYPE type){
        super(cause);
        this.type = type;
    }
    
    public IllegalTermStateException(String msg, Throwable cause){
        super(msg, cause);
    }
    
    public IllegalTermStateException(String msg, Throwable cause, TYPE type){
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
