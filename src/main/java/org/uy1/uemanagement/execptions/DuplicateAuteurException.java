package org.uy1.uemanagement.execptions;

public class DuplicateAuteurException extends RuntimeException {
    public DuplicateAuteurException(String message) {
        super(message);
    }
}
