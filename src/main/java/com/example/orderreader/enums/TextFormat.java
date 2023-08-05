package com.example.orderreader.enums;

public enum TextFormat {
    USER_ID(0, 10),
    NAME(11, 55),
    ORDER_ID(57, 66),
    PRODUCT_ID(67, 75),
    VALUE_PRODUCT(79, 91),
    DATE(92, 100);

    private int start;
    private  int end;

    private TextFormat(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

}
