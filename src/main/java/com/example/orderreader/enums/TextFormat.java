package com.example.orderreader.enums;

public enum TextFormat {
    USER_ID(0, 10),
    NAME(11, 55),
    ORDER_ID(57, 66),
    PRODUCT_ID(67, 75),
    VALUE_PRODUCT(76, 87),
    DATE(87, 95);

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
