/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.fgc.Service;

import com.portfolio.fgc.Entity.Experiencia;
import com.portfolio.fgc.Repository.RExperiencia;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SExperiencia {
    @Autowired
    RExperiencia rExperiencia;
    
    public List<Experiencia> List(){
        
        return rExperiencia.findAll();
        
           
    }
    public Optional<Experiencia> getOne(int id){
          return rExperiencia.findById(id);
        }
    public Optional<Experiencia> getByNombreE(Strig nombreE){
        return rExperiencia.findbyNombreE(nombreE);
    }
    public void save(Experiencia expre){
        rExperiencia.save(expe);
    }
    public void delete(int id){
        return rExperiencia.deleteById(id);
    }
    public boolean existsById(int id){
        return rExperiencia.existsById(id);
    }
    
    public boolean existsByNombreE(String nombreE){
        return rExperiencia.existsByNombreE(nombreE);
    }
}
