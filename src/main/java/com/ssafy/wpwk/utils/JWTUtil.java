package com.ssafy.wpwk.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.util.Date;

public class JWTUtil {

    private Logger logger = LoggerFactory.getLogger(JWTUtil.class);

    private Key key;

    private long tokenValidMilisecond = 1000L * 60 * 60 * 24;

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
        if(validateToken(token)) { // 토큰이 유용할 경우
            return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        }
        return null;
    }

    /**
     * 토큰의 유효성 검증 메서드
     * */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token");
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token");
        } catch (SignatureException e) {
            logger.error("Invalid JWT Signature");
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}
