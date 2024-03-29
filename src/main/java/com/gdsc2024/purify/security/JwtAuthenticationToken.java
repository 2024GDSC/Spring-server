package com.gdsc2024.purify.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

// 사용자 인증에 필요한 토큰 정의 + jwt 에서 가져온 클레임 정보 추가 가능
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private final Claims claims;

    public JwtAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, Claims claims) {
        super(principal, credentials, authorities);
        this.claims = claims;
    }

    public Claims getClaims() {
        return claims;
    }
}
