/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.fgc.Security.jwt;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.security.sasl.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component

public class JwtEntryPoint  implements AuthenticationEntryPoint {
  private final static Logger logger =  LoggerFactory.getLogger(JwtEntryPoint.class);
  
  public void commence (httpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException{
      logger.error("Fallo metodo commence");
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
  }
}
