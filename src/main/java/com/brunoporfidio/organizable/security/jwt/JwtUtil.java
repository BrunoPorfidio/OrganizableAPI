package com.brunoporfidio.organizable.security.jwt;

import com.brunoporfidio.organizable.security.model.MainUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    
    private final static Logger logger = LoggerFactory.getLogger(JwtUtil.class);
            
    @Value("${jwt.secret}")
    private String secret;
    
    private SecretKey getSigningKey() {
        byte[] keyBytes = this.secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }
    
    public Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }
    
    public <T> T extractClaims(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }
    
    public Claims extractAllClaims(String token){
        return Jwts
                .parser()
//              .setSigningKey(getSigningKey())
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    
    private String createToken(Authentication authentication){
        MainUser mainUser = (MainUser) authentication.getPrincipal();
        return Jwts.builder().setSubject(mainUser.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 100 *60 *60 *10))
                .signWith(getSigningKey())
                .compact();
    }
    
    
//    public Boolean validateToken(String token, UserDetails userDetails){
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
    
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(secret).build().parseSignedClaims(token);
            return true;
        }catch (MalformedJwtException e){
            logger.error("Token mal formado");
        }catch (UnsupportedJwtException e){
            logger.error("Token no soportado");
        }catch (ExpiredJwtException e){
            logger.error("Token expirado");
        }catch (IllegalArgumentException e){
            logger.error("Token vacio");
        }catch (SignatureException e){
            logger.error("Firma no válida");
        }
        return false;
    }
}