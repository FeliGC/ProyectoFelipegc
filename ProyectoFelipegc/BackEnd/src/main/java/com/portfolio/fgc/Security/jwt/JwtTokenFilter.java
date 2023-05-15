/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.fgc.Security.jwt;
import com.portfolio.fgc.Security.Service.UserDetailsImpl
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.LoggerFactory;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;
 
public class JwtTokenFilter extends OncePerRequestFilter{
  private final static Looger logger = LoggerFactory.getLogger(JwtProvider.class); 
  @Autowired
  JwtProvider jwtProvider;
  @Autowired
  UserDetailsImpl userDetailsServiceImpl;
  
  protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws IOException{
      try {
          String token = getToken(request);
          if (token!=null && jwtProvider.getNombreUsuarioFromToken(token);{
          UserDetails userDetails = userDetailsServiceImpk.loadUserByUsername(nombreUsuario);
          UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
          SecurityContexHolder.getContext().setAuthentication(auth);
      }
  }catch(Exception e){
    logger.error("Falló metodo FilterInterna");
}
  filterChain.doFilter(request, response);

private String getTpken(HttpServletRequest request){
String header = request.getHeader("Authentication");
if(header != null && header.startsWith("Bearer"))
    return header.replace("Bearer", "");
return null;
    
    }
}