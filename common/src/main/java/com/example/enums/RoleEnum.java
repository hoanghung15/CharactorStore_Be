package com.example.enums;

public enum RoleEnum {
    ADMIN(0),
    AUTHOR(1),
    END_USER(2);
    private final int value;

    RoleEnum(int value) {
        this.value = value;
    }
}
