/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfolio.fgc.Controller;

import com.portfolio.fgc.Dto.dtoExperiencia;
import com.portfolio.fgc.Entity.Experiencia;
import com.portfolio.fgc.Security.Controller.Mensaje;
import com.portfolio.fgc.Service.SExperiencia;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("explab")
@CrossOrigin(origins = "http://localhost:4200")
public class CExperiencia {
    @Autowired
    SExperiencia sExperiencia;
    
    @GetMappin("/lista")
    public ResponseEntity<List<Experiencia>> List(){
        List<Experiencia> list = sExperiencia.List();
        return new ResponseEntity(list, HttpStatus.ok);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoExperiencia dtoexp){
if(StringUtils.isBlank(dtoexp.getNombreE()))
    return new ResponseEntity(new Mensaje("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
if(sExperiencia.existsByNombreE(dtoexp.getNombreE()))
    return new ResponseEntity(new Mensaje("Esa experiencia existe"), httpStatus.BAD_REQUEST);

Experiencia experiencia = new Experiencia(dtoexp.getNombreE(), dtoexp.getDescripcionE());
sExperiencia.save(expereincia);

return new ResponseEntity(new Mensaje("Expereiencia agregada"), HttpStatus.OK);

    }
    @PutMapping("/update/(id)")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperiencia dtoexp){
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpSttus.BAD_REQUEST);
        if(sExperiencia.existsByNombreE(dtoexp.getNombreE()) && sExperiencia.getByNombreE(dtoexp.getNombreE()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        if(stringUtils.isBlank(dtoexp.getNombreE()))
            return new ResponseEntity(new Mensaje("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = sExperiencia.get.One(id).get();
        experiencia.setNombreE(dtoexp.getNombreE());
        experiencia.setDescripcionE((dtoexp.getDescripcionE()));
        
        sExpereriencia.save(experiencia);
        return new ResponseEntity(new Mensaje("Expereincia guardada"), HttpStatus.OK);
    }
    public ResponseEntity<?> delete(PathVariable("id") int id){
    if(!sExperiencia.exists.ById(id))
        return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
    sExperiencia.delete(id);
return new ResponseEntity(new Mensaje("Experiencia eliminada"), HttpStatus.BAD_REQUEST);
}
}
