package com.example.enums;

public enum StatusCodeEnum {

    //User
    USER0000(1000), //create new user account successfully
    USER0001(1001), //create new user account failed
    USER0002(1002), //get user failed
    USER0003(1003), //user existed

    //Auth
    AUTH0000(1100), //auth user account successfully
    AUTH0001(1101), // auth user account failed
    AUTH0002(1102), // Save token when log-out successfully
    AUTH0003(1103), // Save token when log-out failed

    //EXCEPTION
    EXCEPTION0000(1200), //Access denied
    EXCEPTION0001(1201), //JWT invalid
    EXCEPTION0002(1202), //Runtime Exception
    ;

    public final int value;

    StatusCodeEnum(int value) {
        this.value = value;
    }
}
