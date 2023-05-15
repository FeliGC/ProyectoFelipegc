/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.fgc.Security.Controller;

import com.portfolio.fgc.Security.Entity.Rol;
import com.portfolio.fgc.Security.Entity.Usuario;
import com.portfolio.fgc.Security.Enums.RolNombre;
import com.portfolio.fgc.Security.Service.RolService;
import com.portfolio.fgc.Security.Service.UsuarioService;
import com.portfolio.fgc.Security.jwt.JwtProvider;
import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
      @Autowired
      AuthenticationManager authenticationManager;
      @Autowired
      UsuarioService usuarioService;
      @Autowired
      RolService rolService;
      @Autowired
      JwtProvider jwtProvider;
      @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResultt){
        if (bindigResult.hasErrors())
            return new ResponseEntity(new Mensaje("email invalido"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("Ese nombde de usuario ya existe"), HttpStatus.BAD_REQUEST);
        
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("Ese email ya existe"), HttpStatus.BAD_REQUEST);
        
        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
   
    Set<Rol> roles = new HashSet<>();
    roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
    
    if(nuevoUsuario.getRoles().contains("admin"))
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
    
    usuario.setRoles(roles);
    usuarioService.save(usuario);
    return new ResponseEntity(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
    } 
    @PostMapping("/login")
   public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
       if(bindingResult.hasErrors())
           return new ResponseEntity(new Mensaje("Campo mal puesto"), HttpStatus.BAD_REQUEST);
       Authentication  authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginusuario.getNombreUsuario(), loginUsuario.getPassword()));
  
   SecurityContexHolder.getContex().setAuthentication(authentication);
   
   String jwt = jwtProvider,generateToken(authentication);
   UserDetails userDetails = (UserDetails) authentication.getPrincipal();
   
   JwtDto jwtDto = new JwtDto (jwt, userDetails,getUsername(), userDetails.getAuthorities());
   
   return new ResponseEntity(jwtDto, HttpStatus.OK);
   }
}
