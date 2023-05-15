/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.fgc.Security;

import com.portfolio.fgc.Security.Service.UserDetailsImpl;
import com.portfolio.fgc.Security.jwt.JwtEntryPoint;
import com.portfolio.fgc.Security.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecuritt(prePostEnabled = true)
public class MainSecurity extends WebSecurityConfigurerAdapter{
  @Autowired
UserDetailsImpl userDetailServiceImpl;
   @Autowired
   JwtEntryPoint jwtEntryPoint;
   
   @Bean
   public JwtTokenFilter jwtTokenFilter(){
       return new JwtTokenFilter();
   }
   @Bean
   public passwordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }
   
   protected void configure(HttpSecurity http) throws Exception{
       http.cors().and().csrf().disable()
  .authorizeRequest()
               .andMatchers("/auth/**").permitAll()
               .anyRequest().authenticated
               .and()
               .execptionHandling().authenticationEntryPoint(jwtEntryPoint)
               .and()
               .sessionManagment().sessionCreationPolicy(sessionCreationPolicy.STATELESS);
               http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
   }
   
   protected AuthenticationManager authenticationManager()throws Exception{
       return super.authenticationManager ();
   }
         public AuthenticationManager authenticationManagerBean() throws Exception{
             return super.authenticationManagerBean();
         }
         
         protected void configure(AuthenticationManagerBuilder auth) throws Exception{
            auth.userDetailsService(userDetailsServicesImpl).passwordEncoder(passwordEncoder()); 
         }
}
