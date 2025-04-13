package com.example.controller.Auth;

import com.example.model.dto.ResponseDto;
import com.example.model.dto.request.RefreshTokenRequest;
import com.example.model.dto.response.AuthResponse;
import com.example.service.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "JWT")
@RequestMapping("/jwt")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtController {
    JwtService jwtService;

    @PostMapping("/refresh")
    @Operation(description = "Get new Acctoken and RefreshToken",
            summary = "Generate new Access and Refresh Token")
    public ResponseEntity<ResponseDto<AuthResponse>> refresh(@RequestParam(name = "refreshToken")
                                                             String requestToken) {
        return jwtService.refreshToken(requestToken);
    }
}
