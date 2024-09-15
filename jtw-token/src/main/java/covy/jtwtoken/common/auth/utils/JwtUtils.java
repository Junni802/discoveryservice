package covy.jtwtoken.common.auth.utils;

import covy.jtwtoken.common.auth.model.CustomUserInfoDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Date;
import java.time.ZonedDateTime;

@Slf4j
@Component
public class JwtUtils {

    private final Key key;

    private final long accessTokenExpTime;

    public JwtUtils(@Value("${jwt.secret}") String secretKey, @Value("${jwt.expiration_time}") long accessTokenExpTime) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.accessTokenExpTime = accessTokenExpTime;
    }

    /**
     * Access Token 생성
     *
     * @param inDto
     * @return Access Token String
     */
    public String createAccessToken(CustomUserInfoDto inDto) {
        return createToken(inDto, accessTokenExpTime);
    }

    /**
     * JWT 생성
     *
     * @param inDto
     * @param expiresTime
     * @return JWT String
     */
    private String createToken(CustomUserInfoDto inDto, long expiresTime) {
        Claims claims = Jwts.claims();
        claims.put("memberId", inDto.getUserId());
        claims.put("email", inDto.getEmail());
        claims.put("role", inDto.getRole());

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenExpireTime = now.plusSeconds(expiresTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenExpireTime.toInstant()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    /**
     * Token에서 User ID추
     *
     * @param token
     * @return User ID
     */
    public Long getUserId(String token) {
        return parseClaims(token).get("memberId", Long.class);
    }

    /**
     * JWT검증
     *
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(token);
            return true;
        } catch (io.jsonwebtoken.security.SignatureException | MalformedJwtException e) {
            log.info("Invalid JWT token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty", e);
        }
        return false;
    }


    /**
     * JWT Claims 추출
     *
     * @param accessToken
     * @return JWT Claims
     */
    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

}