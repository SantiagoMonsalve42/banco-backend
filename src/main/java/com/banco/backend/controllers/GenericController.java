package com.banco.backend.controllers;


import com.banco.backend.models.dao.GenericDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GenericController<E,S extends GenericDAO<E>> {
    private String jwtToken;
    protected final S service;
    @Autowired
    private SessionController session;
    protected String nombreEntidad;
    private Map<String,Object> mensaje;
    public GenericController(S service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> readAll(@RequestHeader String user){
        mensaje = new HashMap<>();
        List<E> list = (List<E>) service.readAll();
        if(list.isEmpty()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",200);
        mensaje.put("data",list);
        return ResponseEntity.ok(mensaje);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> readById(@PathVariable Integer id,@RequestHeader String user){
        mensaje = new HashMap<>();
        Optional<E> e = service.readById(id);
        if(!e.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad+" con el id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",200);
        mensaje.put("data",e);
        return ResponseEntity.ok(mensaje);
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody E entidad,@RequestHeader String user){
        mensaje = new HashMap<>();
        E e = service.save(entidad);
        if(e == null){
            mensaje.put("status",400);
            mensaje.put("message","No se pudo insertar en la entidad "+nombreEntidad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",201);
        mensaje.put("data",e);
        return ResponseEntity.ok(mensaje);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id,@RequestHeader String user){
        mensaje = new HashMap<>();
        Optional<E> e = service.readById(id);
        jwtToken = session.getJWTToken(user);
        mensaje.put("key",jwtToken);
        mensaje.put("user",user);
        if(!e.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad+" con el id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        service.deleteById(id);
        mensaje.put("status",Boolean.TRUE);
        mensaje.put("data",200);
        return ResponseEntity.ok(mensaje);
    }
}
