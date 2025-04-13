package com.example.enums;

public enum StatusCartEnum {
    ADD(0), PAY(1), PENDING(2), CANCEL(3);
    private final int value;

    StatusCartEnum(int value) {
        this.value = value;
    }
}
