package com.duallab.bus_stop.exception;

public class TextFileTimetableDAOException extends RuntimeException {
    public TextFileTimetableDAOException(Throwable cause) {
        super(cause);
    }

    public TextFileTimetableDAOException(String message) {
        super(message);
    }
}
