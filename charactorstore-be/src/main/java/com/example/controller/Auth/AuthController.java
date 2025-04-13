package com.example.controller.Auth;

import com.example.model.dto.ResponseDto;
import com.example.model.dto.request.AuthRequest;
import com.example.model.dto.request.LogoutRequest;
import com.example.model.dto.response.AuthResponse;
import com.example.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = "Auth")
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Login store with email password", description = "Login store with email password")
    ResponseEntity<ResponseDto<AuthResponse>> authenticate(@ParameterObject @Valid AuthRequest request) {
        return authService.authenticate(request);
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout with JWT", description = "Logout with JWT")
    ResponseEntity<ResponseDto<Object>> logout(@ParameterObject LogoutRequest logoutRequest) {
        return authService.logout(logoutRequest);
    }

    @PostMapping("/introspect")
    ResponseEntity<ResponseDto<Object>> introspect(@RequestParam(name = "token") String token) {
        return authService.introspectToke(token);
    }

}
