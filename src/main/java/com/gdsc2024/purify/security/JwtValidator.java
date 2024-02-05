package com.gdsc2024.purify.security;

import io.jsonwebtoken.*;
import jakarta.servlet.ServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtValidator { // 토큰 유효성 검사
    @Value("${jwt.access.key}")
    private String accessKey;

    public boolean validateToken(ServletRequest request, String jwtToken) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(accessKey.getBytes()).parseClaimsJws(jwtToken);
            if (claimsJws.getBody().getExpiration() != null && claimsJws.getBody().getExpiration().before(new Date())) {
                throw new ExpiredJwtException(null, claimsJws.getBody(), "JWT Token expired at " + claimsJws.getBody().getExpiration());
            }
            return true;
        } catch (SignatureException e) {
            request.setAttribute("exception", "ForbiddenException");
        } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            request.setAttribute("exception", e.getClass().getSimpleName());
        }
        return false; // invalid
    }
}