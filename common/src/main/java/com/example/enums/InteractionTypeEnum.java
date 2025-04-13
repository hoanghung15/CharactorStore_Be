package com.example.enums;

public enum InteractionTypeEnum {

    LIKE(0), SHARE(1), DOWNLOAD(2), ADD(3);
    private final int value;

    private InteractionTypeEnum(int value) {
        this.value = value;
    }
}
