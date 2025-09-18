package xyz.genzsage.genzsagebackend.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtService {

    private final SecretKey key;
    public JwtService() {

        final String secret="6y9Qv+6N0J1fW7x1qS2u5J6h2r8v9y4X6h7j5g0d3tY=";

        byte[] decodedKey = Base64.getDecoder().decode(secret);
        this.key = Keys.hmacShaKeyFor(decodedKey);
    }



    public String generateToken(String identity, String type) {
        long refreshTokenExpiration = 1000L * 60 * 60 * 24 * 60;
        long loginTokenExpiration = 1000L * 60 * 60 * 10;
        long time = type.equals("login") ? loginTokenExpiration : refreshTokenExpiration;
        return Jwts.builder()

                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + time))
                .subject(identity)
                .signWith(key)
                .compact();
    }

    public String extractUserIdentity(String jwt) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwt)
                .getPayload()
                .getSubject();
    }

    public boolean isJwtValid(String jwt) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }




    }


}
