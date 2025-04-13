package com.example.exception;

import com.example.enums.StatusCodeEnum;
import com.example.model.dto.ResponseDto;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public <T> ResponseEntity<ResponseDto<T>> deniedExecuted(AccessDeniedException e) {
        final ResponseDto<T> dto = ResponseDto.<T>builder()
                .success(false)
                .message(e.getMessage())
                .statusCode(StatusCodeEnum.EXCEPTION0000.value)
                .build();
        return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(JOSEException.class)
    public <T> ResponseEntity<ResponseDto<T>> joseExecuted(JOSEException e) {
        final ResponseDto<T> dto = ResponseDto.<T>builder()
                .success(false)
                .message(e.getMessage())
                .statusCode(StatusCodeEnum.EXCEPTION0001.value)
                .build();
        return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(ParseException.class)
    public <T> ResponseEntity<ResponseDto<T>> cancellationExecuted(ParseCancellationException e) {
        final ResponseDto<T> dto = ResponseDto.<T>builder()
                .success(false)
                .message(e.getMessage())
                .statusCode(StatusCodeEnum.EXCEPTION0001.value)
                .build();
        return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(RuntimeException.class)
    public <T> ResponseEntity<ResponseDto<T>> handleRuntime(RuntimeException e) {
        ResponseDto<T> dto =  ResponseDto.<T>builder()
                .success(false)
                .message(e.getMessage())
                .statusCode(StatusCodeEnum.EXCEPTION0002.value)
                .build();

        return ResponseEntity.ok(dto);
    }

}
