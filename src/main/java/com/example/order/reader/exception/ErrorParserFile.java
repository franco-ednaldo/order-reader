package com.example.order.reader.exception;

public class ErrorParserFile extends RuntimeException {

    public ErrorParserFile(final String message, Throwable ex) {
        super(message, ex);
    }
    public ErrorParserFile(final String message) {
        super(message);
    }

    public ErrorParserFile() {
        super();
    }

}
