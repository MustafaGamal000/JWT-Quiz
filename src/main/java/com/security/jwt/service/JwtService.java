package com.security.jwt.service;

import com.security.jwt.model.Quiz;
import com.security.jwt.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private final String SECRET_KEY = "fdfcf3c970f1a4a7e8ed96ff84db4a425f3f3c54dda11ee2e54a1ef6bee7cfcf";
    // WORM TANK URGE SOLD HEY BATE TELL CROW MUCK BUY LURK JUT BRIM WELT FUN MILK EST DEAN SUNK BAIL MOLE FANG FADE TANK


    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }

    public boolean isValid(String token, UserDetails userDetails){
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return exprirationDate(token).before(new Date());
    }

    private Date exprirationDate(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    public <T> T extractClaims(String token, Function<Claims, T> resolver){
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    public String generateToken(User user){
        String token = Jwts
                .builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 30*60*1000))
                .signWith(getSigningKey())
                .compact();
        return token;
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
