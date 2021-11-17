package com.tssco.portalLogin.Utils;

import java.util.*;

import javax.security.auth.message.AuthException;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")

public class JwtUtils {

    // private static final String CLAIMS_KEY_USER_ROLES = "userRoles";

    private long expire;
    private String SECRET;


    /**
     * 產生 JWT Token
     */
    public String generateToken(String username){

        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime()+1000 * expire);


        Map<String, Object> claims = new HashMap<>();
        claims.put( "userid","12312343"); 
        claims.put( "userName",username); 


        return Jwts.builder()
                // .setHeaderParam("type", "JWT")
                // .setSubject(username)
                // .setIssuedAt(nowDate)
                .setSubject(username)
                .setClaims(claims)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }


    /**
     * 驗證JWT
     * @return 
     * @return 
     * @throws AuthException
     */
    public Claims validateToken(String token) throws AuthException {

        try {
            Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
            return claims;

        } catch (SignatureException e) {
            throw new AuthException("Invalid JWT signature.");
        }
        catch (MalformedJwtException e) {
            throw new AuthException("Invalid JWT token.");
        }
        catch (ExpiredJwtException e) {
            throw new AuthException("Expired JWT token");
        }
        catch (UnsupportedJwtException e) {
            throw new AuthException("Unsupported JWT token");
        }
        catch (IllegalArgumentException e) {
            throw new AuthException("JWT token compact of handler are invalid");
        }
    }

     /**
     * 取得payload
     */
    public Claims getTokenCliams(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(claims.toString());
        return claims;
    }
}
