package com.example.Week1Introduction.Week_1_.Introduction.system.JWTAuth;

import com.example.Week1Introduction.Week_1_.Introduction.system.QC_EGMS_USERS;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Service
public class JwtService {
    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getsecretKey()
    {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }


    public String generateToken(QC_EGMS_USERS user)
    {
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email",user.getEmail())
                .claim("roles", Set.of("ADMIN","USER"))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60))
                .signWith(getsecretKey())
                .compact();
    }

    public Long getUserIdFromToken(String token)
    {
        Claims claims = Jwts.parser()
                .verifyWith(getsecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return Long.valueOf(claims.getSubject());
    }
}
