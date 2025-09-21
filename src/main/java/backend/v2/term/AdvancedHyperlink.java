/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.v2.term;

import java.util.Objects;

/**
 *
 * @author BRIN
 */
public final class AdvancedHyperlink {
    
    public static final String DEFAULT_HYPERLINK_TEXT = "%%defaulthttps%%";
    public static final String DEFAULT_HYPERLINK_ENCAPSULATION = "%%defaultencap%%";
    
    private String hyperlink;
    private String encapsulation;

    public AdvancedHyperlink() {
        this.hyperlink = DEFAULT_HYPERLINK_TEXT;
        this.encapsulation = DEFAULT_HYPERLINK_ENCAPSULATION;
    }

    public AdvancedHyperlink(String hyperlink, String encapsulation) throws IllegalTermStateException {
        setHyperlink(hyperlink, encapsulation);
    }

    public AdvancedHyperlink(AdvancedHyperlink advHyper){
        this.hyperlink = advHyper.hyperlink;
        this.encapsulation = advHyper.encapsulation;
    }
    
    public String getHyperlink() {
        return hyperlink;
    }

    public String getEncapsulation() {
        return encapsulation;
    }

    public void setHyperlink(String hyperlink, String encapsulation) throws IllegalTermStateException {
        checkHyperlink(hyperlink);
        checkEncapsulation(encapsulation);
        checkPairing(hyperlink, encapsulation);
        this.hyperlink = hyperlink;
        this.encapsulation = encapsulation;
    }
    
    public boolean isDefault(){
        return hyperlink.equals(DEFAULT_HYPERLINK_TEXT) && encapsulation.equals(DEFAULT_HYPERLINK_ENCAPSULATION);
    }
    
    private void checkHyperlink(String hyperlink) throws  IllegalTermStateException{
        hyperlink = hyperlink.strip();
        if(hyperlink.equals(DEFAULT_HYPERLINK_TEXT)){
            return;
        }
        
        if(hyperlink.isEmpty()){
            throw new IllegalTermStateException("Hyperlink can not be empty", IllegalTermStateException.TYPE.EMPTY_HYPERLINK);
        }
        
        if(!hyperlink.startsWith("https://") && !hyperlink.startsWith("http://")){
            throw new IllegalTermStateException("Hyperlink (%) does not begin with standard protocol: https:// or http://".replace("%", hyperlink), IllegalTermStateException.TYPE.MALFORMED_PROTOCOL);
        }
        else if(hyperlink.equals("https://") || hyperlink.equals("http://")){
            throw new IllegalTermStateException("Hyperlink should not be equal to standard protocol only: https:// or http://", IllegalTermStateException.TYPE.MALFORMED_PROTOCOL);
        }
    }
    
    private void checkEncapsulation(String encapsulation) throws IllegalTermStateException {
        encapsulation = encapsulation.strip();
        if(encapsulation.equals(DEFAULT_HYPERLINK_ENCAPSULATION)){
            return;
        }
        
        if(encapsulation.isEmpty()){
            throw new IllegalTermStateException("Hyperlink Encapsulation can not be empty", IllegalTermStateException.TYPE.EMPTY_ENCAPSULATION);
        }
    }
    
    private void checkPairing(String hyperlink, String hyperlinkEncapsulation) throws IllegalTermStateException{
        if(hyperlink.equals(DEFAULT_HYPERLINK_TEXT) && !hyperlinkEncapsulation.equals(DEFAULT_HYPERLINK_ENCAPSULATION)){
            throw new IllegalTermStateException("Hyperlink is dafault but its encapsulation is not default", IllegalTermStateException.TYPE.INVALID_PAIRING);
        }
        else if(!hyperlink.equals(DEFAULT_HYPERLINK_TEXT) && hyperlinkEncapsulation.equals(DEFAULT_HYPERLINK_ENCAPSULATION)){
            throw new IllegalTermStateException("Hyperlink is not dafault but its encapsulation is default", IllegalTermStateException.TYPE.INVALID_PAIRING);
        }
    }
    
    @Override
    public String toString() {
        return "AdvancedHyperlink{" +
               "hyperlink='" + hyperlink + '\'' +
               ", encapsulation='" + encapsulation + '\'' +
               '}';
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdvancedHyperlink other = (AdvancedHyperlink) o;
        return Objects.equals(hyperlink, other.hyperlink) &&
               Objects.equals(encapsulation, other.encapsulation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hyperlink, encapsulation);
    }
}
