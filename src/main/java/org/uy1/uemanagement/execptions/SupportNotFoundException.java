package org.uy1.uemanagement.execptions;

public class SupportNotFoundException extends RuntimeException {
    public SupportNotFoundException(String message) {
        super(message);
    }
}
