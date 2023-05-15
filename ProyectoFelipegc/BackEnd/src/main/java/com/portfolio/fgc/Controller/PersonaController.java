
package com.portfolio.fgc.Controller;

import com.portfolio.fgc.Entity.Persona;
import com.portfolio.fgc.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonaController {
    @Autowired IPersonaService ipersonaService;
    
    @GetMapping("persona/traer")
    public List<Persona> getPersona(){
        return ipersonaService.getPersona();
        }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/persona/crear")
    public String createPersona(@RequestBody Persona persona){
        ipersonaService(persona);
        return "Persona creada correctamente";
    }
    
     @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/persona/borrar/(id)")
    public String deletePersona(@PathVariable Long id){
        ipersonaService.deletePersona (id);
        return "Persona eliminada correctamente";
    }
    //URL:PUERTO/PERSONA/EDITAR/4/nombre & apellido & img//
     @PreAuthorize("hasRole('ADMIN')")
    @PutMappin("/persona/editar/id")
    public Persona editPersona(@PathVariable Long id,
            @RecuestParam("nombre") String nuevoNombre,
            @RecuestParam("apellido") String nuevoApellido,
            @RecuestParam("img") String nuevoImg){
        Persona persona = ipersonaService.findPersona(id);
        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persona.setImg(nuevoImg);
        
        ipersonaService.savePersona(persona);
        return persona;
    }
    
    @GetMapping("persona/traer/perfin")
    public Persona findPersona(){
        return  ipersonaService.findPersona (long)l);
    }
}