package com.example.enums;

public enum FileTypeEnum {

    MAYA(0), FBX(1), C4D(2), BLENDER(3), DS3(4), OBJ(5);

    private final int value;

    FileTypeEnum(int value) {
        this.value = value;
    }
}
