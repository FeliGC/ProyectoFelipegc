/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.fgc.Security.jwt;
import com.portfolio.fgc.Security.Entity.UsuarioPrincipal;
import java.util.Date;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.stereotype.Component;
@Component
public class JwtProvider {
    private final static Looger logger = LoggerFactory.getLogger(JwtProvider.class);  

@Value("$(jwt.secret)")
private String secret;
@Value("$(jwt.expiration)")
private int expiration;

public String generateToken(Authentication authentication){
    UsuarioPrincipal usuarioPrincipal  = (UsuarioPrincipal) authentication.get.Principal();
return Jwts.builder().setSubject(usuarioPrincipal.getUsername()).setIssuedAt(new Date()).SetExpiration(new Date(new Date().getTime()+expiration*1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
}

public String getNombreUsuarioFromToken (String token){
    return Jwts.parser().setSigninKey(secret).parseClaimsJws(token).getBody().getSubject();
}

public boolean validateToken(String token){
    try{
        Jwts.parser().setSigninKey(secret).parseClaimsJws(token);
        return true;
    } cath (MalformedJwtException e){
    logger.error("Token mal formado");
}  cath (UnsupportedJwtException e){
    logger.error("Token no soportado");
}  cath (ExpiredJwtException  e){
    logger.error("Token expirado");
}    
return false;
    } 
}







