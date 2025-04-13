package com.example.model.dto;

import com.example.enums.StatusCodeEnum;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseEntity;

@UtilityClass
public class ResponseBuilder {
    public static <T> ResponseEntity<ResponseDto<T>> okResponse(String message, StatusCodeEnum statusCodeEnum) {
        final ResponseDto<T> dto = ResponseDto.<T>
                        builder()
                .success(true)
                .message(message)
                .statusCode(statusCodeEnum.value)
                .build();
        return ResponseEntity.ok(dto);
    }

    public static <T> ResponseEntity<ResponseDto<T>> okResponse(String message, StatusCodeEnum statusCodeEnum, @NonNull T body) {
        final ResponseDto<T> dto = ResponseDto.<T>
                        builder()
                .success(true)
                .statusCode(statusCodeEnum.value)
                .message(message)
                .data(body)
                .build();

        return ResponseEntity.ok(dto);
    }

    public static <T> ResponseEntity<ResponseDto<T>> badRequestResponse(String message, StatusCodeEnum statusCodeEnum, T body) {
        final ResponseDto<T> dto = ResponseDto.<T>
                        builder()
                .success(false)
                .message(message)
                .statusCode(statusCodeEnum.value)
                .data(body)
                .build();
        return ResponseEntity.ok(dto);
    }

    public static <T> ResponseEntity<ResponseDto<T>> badRequestResponse(String message, StatusCodeEnum statusCodeEnum) {
        final ResponseDto<T> dto = ResponseDto.<T>builder()
                .success(false)
                .message(message)
                .statusCode(statusCodeEnum.value)
                .build();
        return ResponseEntity.ok(dto);
    }
}
