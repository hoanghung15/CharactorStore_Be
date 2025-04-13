package com.example.enums;

public enum GenderEnum {
    MALE(0),
    FEMALE(1),
    UNKNOWN(2);

    private final int value;

    private GenderEnum(int value) {
        this.value = value;
    }
}
