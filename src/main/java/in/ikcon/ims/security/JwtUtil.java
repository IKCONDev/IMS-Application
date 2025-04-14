package in.ikcon.ims.security;


import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final Environment environment;
    private static final String SECRET_KEY = "8V4P9L1h1dL5Y7hCvpa8pBooyPMVhETcbP1qkF9DiYA=";
    private static final String TOKEN_EXPIRATION_PROPERTY = "token.expiration_time";

    public String generateToken(String username , String userType) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userType", userType);
        claims.put("email", username);
        return Jwts.builder()
                .setSubject(username)
                .addClaims(claims)
                .setIssuer("jwt-app")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +8640000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return parseToken(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    private Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token);
    }
}

