package com.muffincrunchy.challenge_wmb_sb_08.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.muffincrunchy.challenge_wmb_sb_08.model.dto.response.JwtClaims;
import com.muffincrunchy.challenge_wmb_sb_08.model.entity.UserAccount;
import com.muffincrunchy.challenge_wmb_sb_08.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Slf4j
@Service
public class JwtServiceImplementation implements JwtService {

    private final String JWT_SECRET;
    private final String ISSUER;
    private final long JWT_EXPIRATION;

    public JwtServiceImplementation(
            @Value("${muffincrunchy.jwt.secret_key}") String jwtSecret,
            @Value("${muffincrunchy.jwt.issuer}") String issuer,
            @Value("${muffincrunchy.jwt.expiration_sec}") long expiration
    ) {
        this.JWT_SECRET = jwtSecret;
        this.ISSUER = issuer;
        this.JWT_EXPIRATION = expiration;
    }

    @Override
    public String generateToken(UserAccount userAccount) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
            return JWT.create()
                    .withSubject(userAccount.getId())
                    .withClaim("roles", userAccount.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plusSeconds(JWT_EXPIRATION))
                    .withIssuer(ISSUER)
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "token generation failed");
        }
    }

    @Override
    public boolean verifyJwtToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            verifier.verify(parseJwt(token));
            return true;
        } catch (JWTVerificationException e) {
            log.error("Invalid JWT Signature/Claims : {}", e.getMessage());
            return false;
        }
    }

    @Override
    public JwtClaims getClaimsByToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            DecodedJWT jwt = verifier.verify(parseJwt(token));
            return JwtClaims.builder()
                    .userAccountId(jwt.getSubject())
                    .roles(jwt.getClaim("roles").asList(String.class))
                    .build();
        } catch (JWTVerificationException e) {
            log.error("Invalid JWT Signature/Claims : {}", e.getMessage());
            return null;
        }
    }

    private String parseJwt(String token) {
        if(token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

}
