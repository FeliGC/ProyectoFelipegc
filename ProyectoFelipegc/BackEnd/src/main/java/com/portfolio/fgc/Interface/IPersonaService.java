package com.portfolio.fgc.Interface;

import com.portfolio.fgc.Entity.Persona;
import java.util.List;

public interface IPersonaService {
   //traer lista de personas
    public List<Persona> getPersona();
   
    //guardar objeto de tipo Persona
    public void savePersona(Persona persona);
    
    //eliminar un objeto buscado por ID

    /**
     *
     * @param id
     */
    public void deletePersona(Long id);
    
    //buscar por ID
    public Persona findPersona(Long id);
}
