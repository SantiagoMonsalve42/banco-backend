package com.banco.backend.controllers;


import com.banco.backend.models.dao.GenericDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GenericController<E,S extends GenericDAO<E>> {
    protected final S service;
    protected String nombreEntidad;
    private Map<String,Object> mensaje;
    public GenericController(S service,String nombreEntidad) {
        this.service = service;
        this.nombreEntidad = nombreEntidad;
    }

    @GetMapping
    public ResponseEntity<?> readAll(){
        mensaje = new HashMap<>();
        List<E> list = (List<E>) service.readAll();
        if(list.isEmpty()){
            mensaje.put("status",Boolean.FALSE);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",Boolean.TRUE);
        mensaje.put("message",list);
        return ResponseEntity.ok(mensaje);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Integer id){
        mensaje = new HashMap<>();
        Optional<E> e = service.readById(id);
        if(!e.isPresent()){
            mensaje.put("status",Boolean.FALSE);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad+" con el id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",Boolean.TRUE);
        mensaje.put("message",e);
        return ResponseEntity.ok(mensaje);
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody E entidad){
        mensaje = new HashMap<>();
        Optional<E> e = service.create(entidad);
        if(!e.isPresent()){
            mensaje.put("status",Boolean.FALSE);
            mensaje.put("message","No se pudo insertar en la entidad "+nombreEntidad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",Boolean.TRUE);
        mensaje.put("message",e);
        return ResponseEntity.ok(mensaje);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(Integer id){
        mensaje = new HashMap<>();
        Optional<E> e = service.readById(id);
        if(!e.isPresent()){
            mensaje.put("status",Boolean.FALSE);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad+" con el id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        service.deleteById(id);
        mensaje.put("status",Boolean.TRUE);
        mensaje.put("message","Dato eliminado correctamente");
        return ResponseEntity.ok(mensaje);
    }
}
