package com.se.authservice.security.jwt;

import com.se.authservice.exceptions.JwtAuthenticationException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class JwtProvider {
    @Qualifier("userDetailsServiceImpl")
    @Lazy
    private final UserDetailsService userDetailsService;

    @Value("${app.jwt.header}")
    private String authorizationHeader;

    @Value("${app.jwt.secret}")
    private String secretKey;

    @Value("${app.jwt.milliseconds}")
    private long validityInMilliseconds;


    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    public String createToken(String username, String role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {

        try {
            System.err.println("token: " + token);
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            throw new JwtAuthenticationException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new JwtAuthenticationException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new JwtAuthenticationException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new JwtAuthenticationException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new JwtAuthenticationException("JWT claims string is empty.");
        }
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsernameByToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsernameByToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String headerAuth = request.getHeader(authorizationHeader);
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring("Bearer ".length());
        }
        return null;
    }
}
