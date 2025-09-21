/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend.v2.customevent;

import java.util.EventObject;

/**
 *
 * @author BRIN
 */
public class FilterRejectionEvent extends EventObject {
    
    private final RejectionReason reason;
    private final String rejectionReasonAsString;
    private final String rejectedText;
    
    public FilterRejectionEvent(Object source, RejectionReason reason, String rejectedText) {
        this(source, reason, reason.toString(), rejectedText);
    }

    public FilterRejectionEvent(Object source, RejectionReason reason, String rejectionReasonAsString, String rejectedText) {
        super(source);
        this.reason = reason;
        this.rejectionReasonAsString = rejectionReasonAsString;
        this.rejectedText = rejectedText;
    }
    
    public RejectionReason getReason() {
        return reason;
    }

    public String getReasonString() {
        return rejectionReasonAsString;
    }

    
    public String getRejectedText() {
        return rejectedText;
    }
    
}
