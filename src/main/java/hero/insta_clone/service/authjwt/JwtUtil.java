package hero.insta_clone.service.authjwt;

import hero.insta_clone.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    /**
     * JWT는 Header, Payload, Signature, 생성
     */

    public final static long TOKEN_VALIDATION_SECOND = 1000L * 60 * 30;
    public final static long REFRESH_TOKEN_VALIDATION_SECOND = 1000L * 60 * 24 * 2;

    final static public String ACCESS_TOKEN_NAME = "accessToken";
    final static public String REFRESH_TOKEN_NAME = "refreshToken";

    @Value("${spring.jwt.secret}")
    private String SECRET_KEY;

    private Key getSigningKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException {   //토큰이 유효한 토큰인지 검사한 후, 토큰에 담긴 Payload 값을 가져온다.
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getEmail(String token) {      //추출한 Payload로부터 email을 가져온다.
        return extractAllClaims(token).get("email", String.class);
    }

    public Boolean isTokenExpired(String token) {       //토큰이 만료됐는지 안됐는지 확인.
        final Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public String generateToken(User user) {
        log.info("generateToken 실행");
        return doGenerateToken(user.getEmail(), TOKEN_VALIDATION_SECOND);
    }

    public String generateRefreshToken(User user) {
        log.info("generateRefreshToken 실행");
        return doGenerateToken(user.getEmail(), REFRESH_TOKEN_VALIDATION_SECOND);
    }

    public String doGenerateToken(String email, long expireTime) {
        Claims claims = Jwts.claims();
        claims.put("email", email);

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSigningKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();
        log.info("jwt token 생성 알림 = {}", jwt);
        return jwt;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        log.info("validateToken 실행");
        final String email = getEmail(token);
        log.info("email = {}, token = {}, userDetails = {}", email, token, userDetails);

        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
