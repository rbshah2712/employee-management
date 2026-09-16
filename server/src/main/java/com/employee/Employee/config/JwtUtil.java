package com.employee.Employee.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 2; // 2 Hours

    // Spring will automatically inject the key from application.properties
    public JwtUtil(@Value("${jwt.secret}") String secretString) {
        // This converts your raw configuration string into a secure cryptographic key
        this.secretKey = Keys.hmacShaKeyFor(secretString.getBytes());
    }

    public String generateToken(String email, Long empId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("empId", empId);

        return Jwts.builder()
                .claims(claims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + this.EXPIRATION_TIME))
                .signWith(this.secretKey) // Uses the securely constructed key
                .compact();
    }
}
