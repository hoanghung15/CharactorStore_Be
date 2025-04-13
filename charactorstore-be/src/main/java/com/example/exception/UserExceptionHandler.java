package com.example.exception;

import com.example.enums.StatusCodeEnum;
import com.example.model.dto.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class UserExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseDto<Void>> handleUserNotFound(UserNotFoundException ex) {
        final ResponseDto<Void> dto = ResponseDto.<Void>builder()
                .success(false)
                .message(ex.getMessage())
                .statusCode(StatusCodeEnum.USER0002.value)
                .build();
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserExistedException.class)
    public ResponseEntity<ResponseDto<Void>> handleUserExistedException(UserExistedException ex) {
        final ResponseDto<Void>dto = ResponseDto.<Void>builder()
                .success(false)
                .message(ex.getMessage())
                .statusCode(StatusCodeEnum.USER0003.value)
                .build();
        return ResponseEntity.ok(dto);
    }
}
