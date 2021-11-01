package com.banco.backend.controllers;

import com.banco.backend.models.dao.CiudadDAO;
import com.banco.backend.models.dao.DepartamentoDAO;
import com.banco.backend.models.dto.CiudadDTO;
import com.banco.backend.models.entities.Ciudad;
import com.banco.backend.models.entities.Departamento;
import com.banco.backend.models.mappers.CiudadMP;
import com.banco.backend.models.mappers.CiudadMPImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/ciudad")
public class CiudadController extends GenericController<Ciudad, CiudadDAO> {

    @Autowired
    private DepartamentoDAO departamentoDAO;
    private Map<String,Object> mensaje;
    @Autowired
    private CiudadMPImpl mapper;

    public CiudadController(CiudadDAO service) {
        super(service);
        nombreEntidad="Ciudad";
    }

    @Override
    public ResponseEntity<?> readAll(){
        mensaje = new HashMap<>();
        List<Ciudad> list = (List<Ciudad>) service.readAll();
        List<CiudadDTO> ciudadDTOS = mapper.mapCiudad(list);
        if(ciudadDTOS.isEmpty()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",200);
        mensaje.put("data",ciudadDTOS);
        return ResponseEntity.ok(mensaje);
    }
    @GetMapping("/departamento/{id}")
    public ResponseEntity<?> getByDepartmentId(@PathVariable Integer id) {
        mensaje = new HashMap<>();
        List<Ciudad> ciudades = (List<Ciudad>) service.obtenerPorIdDepartamento(id);
        List<CiudadDTO> ciudadDTOS = mapper.mapCiudad(ciudades);
        if(ciudadDTOS.isEmpty()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron datos asociados al departamento con id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",200);
        mensaje.put("data",ciudadDTOS);
        return ResponseEntity.ok(mensaje);
    }

    @Override
    public ResponseEntity<?> readById(@PathVariable Integer id){
        mensaje = new HashMap<>();
        Optional<Ciudad> e = service.readById(id);
        CiudadDTO ciudadDTO = mapper.mapCiudad(e.get());
        if(ciudadDTO == null){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad+" con el id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",200);
        mensaje.put("data",ciudadDTO);
        return ResponseEntity.ok(mensaje);
    }
    @Override
    public ResponseEntity<?> save(@RequestBody Ciudad entidad){
        mensaje = new HashMap<>();
        Ciudad e = service.save(entidad);
        CiudadDTO ciudadDTO = mapper.mapCiudad(e);
        if(ciudadDTO == null){
            mensaje.put("status",400);
            mensaje.put("message","No se pudo insertar en la entidad "+nombreEntidad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",201);
        mensaje.put("data",ciudadDTO);
        return ResponseEntity.ok(mensaje);
    }
    @PutMapping("/{idCiudad}/departamento/{idDepartamento}")
    public ResponseEntity<?> asociarDepartamento(@PathVariable Integer idCiudad,@PathVariable Integer idDepartamento){
        mensaje = new HashMap<>();
        Optional<Ciudad> ciudad = service.readById(idCiudad);
        Optional<Departamento> departamento = departamentoDAO.readById(idDepartamento);
        if(!ciudad.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No existe ciudad con id "+idCiudad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        if(!departamento.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No existe departamento con id "+idDepartamento);
            return ResponseEntity.badRequest().body(mensaje);
        }

        Ciudad saver= ciudad.get();
        saver.setDepartamento(departamento.get());
        Ciudad save = service.save(saver);
        CiudadDTO ciudadDTO = mapper.mapCiudad(save);
        mensaje.put("status",200);
        mensaje.put("data",ciudadDTO);
        return ResponseEntity.ok(mensaje);
    }
}
