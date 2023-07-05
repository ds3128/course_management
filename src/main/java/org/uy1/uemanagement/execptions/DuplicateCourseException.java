package org.uy1.uemanagement.execptions;

public class DuplicateCourseException extends RuntimeException {
    public DuplicateCourseException(String message) {
        super(message);
    }
}
