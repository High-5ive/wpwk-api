package com.ssafy.wpwk.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JWTUtil {

    private Key key;

    private long tokenValidMilisecond = 1000L * 60 *60;

    public JWTUtil(String key) {
        this.key = Keys.hmacShaKeyFor(key.getBytes());
    }

    /**
     * token 생성 메서드
     */
    public String createToken(Long userId, String email, String nickname) {

        Date now = new Date();
        return Jwts.builder()
                .claim("userId", userId)
                .claim("email", email)
                .claim("nickname", nickname)

                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMilisecond))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
}
