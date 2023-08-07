package com.example.order.reader.exception;

public class FileProcessNotFound extends RuntimeException {

    public FileProcessNotFound(final String message, Throwable ex) {
        super(message, ex);
    }
    public FileProcessNotFound(final String message) {
        super(message);
    }

    public  FileProcessNotFound() {
        super();
    }

}
