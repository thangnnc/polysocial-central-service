package com.polysocial.config.jwt;

import com.polysocial.config.security.CustomUserDetails;
import com.polysocial.utils.Logger;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import java.util.Date;

@Component
public class JwtTokenProvider {
    // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
    private final String JWT_SECRET = "PolySocial";

    //Thời gian có hiệu lực của chuỗi jwt
    private final long JWT_EXPIRATION = 604800000L;

    // Tạo ra jwt từ thông tin user
    public String generateToken(CustomUserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        // Tạo chuỗi json web token từ email của user.
        return Jwts.builder()
                .setSubject(userDetails.getUser().getRoleId().toString())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    // Lấy thông tin user từ jwt
    public Long getIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            Logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            Logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            Logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            Logger.error("JWT claims string is empty.");
        }
        return false;
    }
}