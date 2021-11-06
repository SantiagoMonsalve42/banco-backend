package com.banco.backend.controllers;

import com.banco.backend.models.dao.DepartamentoDAO;
import com.banco.backend.models.dto.DepartamentoDTO;
import com.banco.backend.models.entities.Departamento;
import com.banco.backend.models.mappers.DepartamentoMP;
import com.banco.backend.models.mappers.DepartamentoMPImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController extends GenericController<Departamento, DepartamentoDAO>{
    private String jwtToken;
    @Autowired
    private SessionController session;

    private Map<String,Object> mensaje;
    @Autowired
    private DepartamentoMPImpl mapper;

    public DepartamentoController(DepartamentoDAO service) {
        super(service);
        nombreEntidad="Departamento";
    }
    @Override
    public ResponseEntity<?> readAll(@RequestHeader String user){
        mensaje = new HashMap<>();
        List<Departamento> list = (List<Departamento>) service.readAll();
        List<DepartamentoDTO> departamentoDTOS = mapper.mapDepartamento(list);
        jwtToken = session.getJWTToken(user);
        mensaje.put("key",jwtToken);
        mensaje.put("user",user);
        if(departamentoDTOS.isEmpty()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",200);
        mensaje.put("data",departamentoDTOS);
        return ResponseEntity.ok(mensaje);
    }
    @Override
    public ResponseEntity<?> readById(@PathVariable Integer id,@RequestHeader String user){
        mensaje = new HashMap<>();
        Optional<Departamento> e = service.readById(id);
        DepartamentoDTO departamentoDTO = mapper.mapDepartamento(e.get());
        jwtToken = session.getJWTToken(user);
        mensaje.put("key",jwtToken);
        mensaje.put("user",user);
        if(!e.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad+" con el id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",200);
        mensaje.put("data",departamentoDTO);
        return ResponseEntity.ok(mensaje);
    }
    @Override
    public ResponseEntity<?> save(@RequestBody Departamento entidad,@RequestHeader String user){
        mensaje = new HashMap<>();
        Departamento e = service.save(entidad);
        DepartamentoDTO departamentoDTO = mapper.mapDepartamento(e);
        jwtToken = session.getJWTToken(user);
        mensaje.put("key",jwtToken);
        mensaje.put("user",user);
        if(e == null){
            mensaje.put("status",400);
            mensaje.put("message","No se pudo insertar en la entidad "+nombreEntidad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",201);
        mensaje.put("data",departamentoDTO);
        return ResponseEntity.ok(mensaje);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,@RequestBody Departamento departamento,@RequestHeader String user){
        Departamento objNuevo = new Departamento();
        mensaje=new HashMap<>();
        Optional<Departamento> departamentoExiste = service.readById(id);
        jwtToken = session.getJWTToken(user);
        mensaje.put("key",jwtToken);
        mensaje.put("user",user);
        if(!departamentoExiste.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No existe dato con id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        objNuevo.setNombre(departamento.getNombre());
        objNuevo.setId(id);
        DepartamentoDTO departamentoDTO = mapper.mapDepartamento(service.save(objNuevo));
        mensaje.put("status",200);
        mensaje.put("data",departamentoDTO);
        return ResponseEntity.ok(mensaje);
    }
}
