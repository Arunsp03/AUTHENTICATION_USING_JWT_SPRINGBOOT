package com.example.jwt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTGenerator {
public String generatetoken(Authentication authentication){
    String username=authentication.getName();
    Date currentdate=new Date();
    Date expiredate=new Date(currentdate.getTime()+SecurityConstant.JWT_EXPIRATION);
    String token= Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(expiredate).signWith(SignatureAlgorithm.HS384,SecurityConstant.JWT_SECRET).compact();
    return token;
}
public String getusernamefromjwt(String token){
    Claims claims=Jwts.parser().setSigningKey(SecurityConstant.JWT_SECRET).parseClaimsJws(token).getBody();
    return claims.getSubject();
}
public boolean validatetoken(String token){
   try{
       Jwts.parser().setSigningKey(SecurityConstant.JWT_SECRET).parseClaimsJwt(token);
       return true;
   }
   catch (Exception e){
       throw new AuthenticationCredentialsNotFoundException("JWT WAS EXPIRED OR INCORRECT");
   }
}
}
