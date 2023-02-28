package com.dawes.security.jwt;

import com.dawes.security.entity.UsuarioWeb;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {


    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    public String generateToken(Authentication authentication){
        UsuarioWeb usuarioWeb = (UsuarioWeb) authentication.getPrincipal();
        return Jwts.builder().setSubject(usuarioWeb.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1200))
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    public String getEmailFromToken (String token) {
        return Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException e){
            logger.error("Expired token");
        }catch (IllegalArgumentException e){
            logger.error("Empty token");
        }catch (MalformedJwtException e){
            logger.error("Malformed token");
        }catch (UnsupportedJwtException e) {
            logger.error("Unsupported token");
        }catch (SignatureException e){
            logger.error("Sign error");
        }
        return false;
    }
}
