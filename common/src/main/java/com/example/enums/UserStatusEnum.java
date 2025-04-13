package com.example.enums;

public enum UserStatusEnum {
    LOCKED(0), ACTIVE(1), PENDING(2);
    private final int value;

    UserStatusEnum(int value) {
        this.value = value;
    }
}
