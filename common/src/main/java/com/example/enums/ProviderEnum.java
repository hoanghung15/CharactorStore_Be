package com.example.enums;

public enum ProviderEnum {
    GOOGLE(0), FACEBOOK(1);

    public final int value;

    ProviderEnum(int value) {
        this.value = value;
    }
}
