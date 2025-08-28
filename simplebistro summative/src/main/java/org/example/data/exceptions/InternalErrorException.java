package org.example.data.exceptions;

public class InternalErrorException extends Exception {

    public InternalErrorException() {
        super(new Exception("Database error."));
    }

    public InternalErrorException(String s, Exception e) {
        super(e);
        e.printStackTrace();
    }

    // ✅ Add this constructor to fix your current error
    public InternalErrorException(String message) {
        super(message);
    }
}
