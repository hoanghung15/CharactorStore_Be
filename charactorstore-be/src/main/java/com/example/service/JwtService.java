package com.example.service;

import com.example.enums.StatusCodeEnum;
import com.example.exception.UserNotFoundException;
import com.example.model.dto.ResponseBuilder;
import com.example.model.dto.ResponseDto;
import com.example.model.dto.request.RefreshTokenRequest;
import com.example.model.dto.response.AuthResponse;
import com.example.model.entity.UserModel;
import com.example.repository.InvalidateRepository;
import com.example.repository.UserRepository;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtService {
    private final InvalidateRepository invalidateRepository;
    private final UserRepository userRepository;
    private final LanguageService languageService;

    @NonFinal
    @org.springframework.beans.factory.annotation.Value("${jwt.signerKey}")
    private String SIGNER_KEY;

    @NonFinal
    @org.springframework.beans.factory.annotation.Value("${jwt.exp-accessToken}")
    private long ACCESS_TOKEN_DURATION;

    @NonFinal
    @Value("${jwt.exp-refreshToken}")
    private long REFRESH_TOKEN_DURATION;

    public String generateAccessToken(UserModel user) {
        return generateToken(user, ACCESS_TOKEN_DURATION, false);
    }

    public String generateRefreshToken(UserModel user) {
        return generateToken(user, REFRESH_TOKEN_DURATION, true);
    }

    public String generateToken(UserModel userModel, long expiryTime, Boolean isRefreshToken) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet.Builder claimsSetBuilder = new JWTClaimsSet.Builder()
                .subject(userModel.getEmail())
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(expiryTime, ChronoUnit.SECONDS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString());

        if (!isRefreshToken) {
            claimsSetBuilder.claim("scope", userModel.getRole());
        }

        JWTClaimsSet jwtClaimsSet = claimsSetBuilder.build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var checkVerifyToken = signedJWT.verify(verifier);

        if (expiryTime.before(new Date())) {
            throw new JOSEException("Expired JWT token");
        }

        if (!checkVerifyToken) {
            throw new JOSEException("Invalid JWT token");
        }

        boolean checkJwtLogout = invalidateRepository.existsById(signedJWT.
                getJWTClaimsSet().getJWTID());

        if (checkJwtLogout) {
            throw new JOSEException("Expired JWT token");
        }
        return signedJWT;
    }

    public ResponseEntity<ResponseDto<AuthResponse>> refreshToken(String refreshToken) {
        try {
            SignedJWT signedJWT = verifyToken(refreshToken);
            String userEmail = signedJWT.getJWTClaimsSet().getSubject();

            UserModel userModel = userRepository.findByEmail(userEmail).orElseThrow(() -> new UserNotFoundException("Người dùng không tồn tại"));
            String newAccToken = generateAccessToken(userModel);
            String newRefreshToken = generateRefreshToken(userModel);

            AuthResponse authResponse = AuthResponse.builder()
                    .refreshToken(newRefreshToken)
                    .accessToken(newAccToken)
                    .authenticated(true)
                    .build();

            return ResponseBuilder.okResponse(
                    languageService.getMessage("jwt.refresh.token.successfully"),
                    StatusCodeEnum.AUTH0000,
                    authResponse
            );

        } catch (JOSEException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

}
