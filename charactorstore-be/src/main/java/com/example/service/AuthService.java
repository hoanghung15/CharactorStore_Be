package com.example.service;

import com.example.enums.StatusCodeEnum;
import com.example.exception.UserNotFoundException;
import com.example.model.dto.ResponseBuilder;
import com.example.model.dto.ResponseDto;
import com.example.model.dto.request.AuthRequest;
import com.example.model.dto.request.LogoutRequest;
import com.example.model.dto.response.AuthResponse;
import com.example.model.entity.InvalidatedTokenModel;
import com.example.model.entity.UserModel;
import com.example.repository.InvalidateRepository;
import com.example.repository.UserRepository;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.text.ParseException;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthService {

    UserRepository userRepository;

    private final JwtService jwtService;
    private final LanguageService languageService;
    private final InvalidateRepository invalidateRepository;
    private final JedisPool jedisPool;
    private final RedisService redisService;

    public ResponseEntity<ResponseDto<AuthResponse>> authenticate(AuthRequest authRequest) {
        try {
            UserModel userModel = userRepository.findByEmail((authRequest.getEmail()))
                    .orElseThrow(() -> new UserNotFoundException("User not found"));

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
            boolean matches = passwordEncoder.matches(authRequest.getPassword(), userModel.getPassword());

            if (!matches) {
                throw new UserNotFoundException("Invalid password");
            }

            String accessToken = jwtService.generateAccessToken(userModel);
            String refreshToken = jwtService.generateRefreshToken(userModel);

            AuthResponse authResponse = AuthResponse.builder()
                    .authenticated(matches)
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();

            return ResponseBuilder.okResponse(
                    languageService.getMessage("auth.user.account.successfully"),
                    StatusCodeEnum.AUTH0000,
                    authResponse
            );

        } catch (Exception e) {
            return ResponseBuilder.badRequestResponse(
                    languageService.getMessage("auth.user.account.failed"),
                    StatusCodeEnum.AUTH0001
            );
        }
    }

    public ResponseEntity<ResponseDto<Object>> logout(LogoutRequest request) {
        try {
            SignedJWT signToken = jwtService.verifyToken(request.getAccessToken());
            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            SignedJWT signRefreshToken = jwtService.verifyToken(request.getRefreshToken());
            String jitRefresh = signRefreshToken.getJWTClaimsSet().getJWTID();
            Date expiryTimeRefresh = signRefreshToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedTokenModel invalidatedTokenModel = InvalidatedTokenModel.builder()
                    .id(jit)
                    .exprireTime(expiryTime)
                    .build();

            InvalidatedTokenModel invalidatedRefreshToken = InvalidatedTokenModel.builder()
                    .id(jitRefresh)
                    .exprireTime(expiryTimeRefresh)
                    .build();

            invalidateRepository.save(invalidatedTokenModel);
            invalidateRepository.save(invalidatedRefreshToken);

            try (Jedis jedis = jedisPool.getResource()) {
                long ttlAccess = (expiryTime.getTime() - System.currentTimeMillis()) / 1000;
                jedis.setex("revoke_token:" + jit, (int) ttlAccess, request.getAccessToken());

                long ttlRefresh = (expiryTimeRefresh.getTime() - System.currentTimeMillis()) / 1000;
                jedis.setex("revoke_token:" + jitRefresh, (int) ttlRefresh, request.getRefreshToken());
            }

            return ResponseBuilder.okResponse(
                    languageService.getMessage("jwt.logout.successfully"),
                    StatusCodeEnum.AUTH0002
            );

        } catch (JOSEException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            return ResponseBuilder.badRequestResponse(
                    languageService.getMessage("jwt.logout.failed"),
                    StatusCodeEnum.AUTH0003
            );
        }

    }

    public ResponseEntity<ResponseDto<Object>> introspectToke(String token) {
        try {
            jwtService.verifyToken(token);
        } catch (JOSEException | ParseException e) {
            throw new RuntimeException(e);
        }

        return ResponseBuilder.okResponse(
                languageService.getMessage("jwt.verify.successfully"),
                StatusCodeEnum.AUTH0003,
                true
        );
    }

}
