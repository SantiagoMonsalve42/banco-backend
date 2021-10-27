package com.banco.backend.controllers;

import com.banco.backend.models.dao.DepartamentoDAO;
import com.banco.backend.models.entities.Departamento;
import com.banco.backend.models.mappers.DepartamentoMP;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController extends GenericController<Departamento, DepartamentoDAO>{
    private Map<String,Object> mensaje;
    private DepartamentoMP mapper;
    public DepartamentoController(DepartamentoDAO service) {
        super(service);
        nombreEntidad="Departamento";
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,@RequestBody Departamento departamento){
        Departamento objNuevo = new Departamento();
        mensaje=new HashMap<>();
        Optional<Departamento> departamentoExiste = service.readById(id);
        if(!departamentoExiste.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No existe dato con id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        objNuevo.setNombre(departamento.getNombre());
        objNuevo.setId(id);
        service.save(objNuevo);
        mensaje.put("status",200);
        mensaje.put("data",objNuevo);
        return ResponseEntity.ok(mensaje);
    }
}
