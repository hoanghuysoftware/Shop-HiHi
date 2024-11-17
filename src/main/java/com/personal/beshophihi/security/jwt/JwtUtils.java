package com.personal.beshophihi.security.jwt;

import com.personal.beshophihi.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final Long EXPIRATION = 86400L * 1000;

    @Value("${jwt.secret}")
    private String SECRET_KEY ;

    public String generateToken(Authentication authentication) {
        User user  = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean validateToken (String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            Date expiration = claims.getExpiration();
            log.info("Token expiration time: " + expiration);
            return !expiration.before(new Date());
        }catch (ExpiredJwtException e) {
            log.error("Expired JWT token");
        }catch (UnsupportedJwtException e){
            log.error("Unsupported JWT token");
        }catch (MalformedJwtException e){
            log.error("Malformed JWT token");
        }catch (SignatureException e){
            log.error("Signature JWT token");
        }catch (IllegalArgumentException e){
            log.error("IllegalArgument JWT token");
        }
        return false;
    }

    public String getUsernameFromToken (String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
