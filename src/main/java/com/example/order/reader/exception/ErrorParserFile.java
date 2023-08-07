package com.example.order.reader.exception;

public class ErrorParserFile extends BusinessException {

    public ErrorParserFile(String message) {
        super(message);
    }
    public ErrorParserFile(String message, Exception ex) {
        super(message, ex);
    }
}
