package com.sharpness.user_service.application.service;

import com.sharpness.user_service.domain.user.CustomUser;
import com.sharpness.user_service.infrastructure.repository.CustomUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService{

    @Value("${jwt.secret}")
    private String secretKey;

    @Autowired
    private CustomUserRepository customUserRepository;

    @Autowired
    private CustomUserDetailService customUserDetailService;


    public String generateAccessToken(String username, String email, UUID uuid) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("email", email);
        claims.put("uuid", uuid);
        claims.put("type", "accessToken");
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 20 * 60 * 1000*15))
                .and()
                .signWith(getKey())
                .compact();

    }

    public String generateRefreshToken(String username, String email, UUID uuid) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("email", email);
        claims.put("uuid", uuid);
        claims.put("type", "refreshToken");
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000))
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    public String extractTokenType(String token) {
        return extractClaim(token, claims -> claims.get("type", String.class));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateAccessToken(String token, UserDetails userDetails) {
        final String userName = extractUsername(token);
        final String tokenType = extractTokenType(token);
        return (userName.equals(userDetails.getUsername()) && tokenType.equals("accessToken")&& !isTokenExpired(token));
    }

    public boolean validateRefreshToken(String token, UserDetails userDetails) {
        final String userName = extractUsername(token);
        final String tokenType = extractTokenType(token);
        return (userName.equals(userDetails.getUsername()) && tokenType.equals("refreshToken")&& !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public ResponseEntity<?> refreshAccessToken(String token) {
        String refreshToken = null;

        if (token != null && token.startsWith("Bearer ")) {
            refreshToken = token.substring(7);
        }

        Claims claims = extractAllClaims(refreshToken);

        String username = claims.get("username", String.class);
        String uuid = claims.get("uuid", String.class);
        String email = claims.get("email", String.class);

        if (username == null && uuid == null && email == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token is not valid");
        }

        Optional<CustomUser> user = customUserRepository.findByUsername(username);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token is not valid");
        }
        UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
        if(validateRefreshToken(refreshToken, userDetails)) {
            String newAccessToken = generateAccessToken(username, email, UUID.fromString(uuid));
            return ResponseEntity.ok(new RefreshAccessTokenResponseDto(newAccessToken));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token is not valid");

    };

}

