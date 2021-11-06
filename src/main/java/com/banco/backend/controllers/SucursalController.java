package com.banco.backend.controllers;

import com.banco.backend.models.dao.CiudadDAO;
import com.banco.backend.models.dao.SucursalDAO;
import com.banco.backend.models.dto.EmpleadoDTO;
import com.banco.backend.models.dto.SucursalDTO;
import com.banco.backend.models.entities.Ciudad;
import com.banco.backend.models.entities.Empleado;
import com.banco.backend.models.entities.Persona;
import com.banco.backend.models.entities.Sucursal;
import com.banco.backend.models.mappers.EmpleadoMPImpl;
import com.banco.backend.models.mappers.SucursalMPImpl;
import com.banco.backend.services.PersonaDAOImpl;
import com.banco.backend.services.SucursalDAOImpl;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/sucursal")
public class SucursalController extends GenericController<Sucursal, SucursalDAO> {
    private String jwtToken;
    @Autowired
    private SessionController session;

    private Map<String,Object> mensaje;
    private CiudadDAO ciudadDAO;
    @Autowired
    private SucursalMPImpl mapper;
    @Autowired
    private EmpleadoMPImpl empleadoMP;
    @Autowired
    public SucursalController(SucursalDAO service, CiudadDAO ciudadDAO) {
        super(service);
        this.ciudadDAO = ciudadDAO;
        nombreEntidad="Sucursal";

    }
    @Override
    public ResponseEntity<?> readAll(@RequestHeader String user){
        mensaje = new HashMap<>();
        List<Sucursal> list = (List<Sucursal>) service.readAll();
        List<SucursalDTO> sucursalDTOS = mapper.mapSucursal(list);
        jwtToken = session.getJWTToken(user);
        mensaje.put("key",jwtToken);
        mensaje.put("user",user);
        if(sucursalDTOS.isEmpty()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",200);
        mensaje.put("data",sucursalDTOS);
        return ResponseEntity.ok(mensaje);
    }
    @Override
    public ResponseEntity<?> readById(@PathVariable Integer id,@RequestHeader String user){
        mensaje = new HashMap<>();
        Optional<Sucursal> e = service.readById(id);
        SucursalDTO sucursalDTO = mapper.mapSucursal(e.get());
        jwtToken = session.getJWTToken(user);
        mensaje.put("key",jwtToken);
        mensaje.put("user",user);
        if(!e.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad+" con el id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",200);
        mensaje.put("data",sucursalDTO);
        return ResponseEntity.ok(mensaje);
    }
    @Override
    public ResponseEntity<?> save(@RequestBody Sucursal entidad,@RequestHeader String user){
        mensaje = new HashMap<>();
        Sucursal e = service.save(entidad);
        SucursalDTO sucursalDTO = mapper.mapSucursal(e);
        jwtToken = session.getJWTToken(user);
        mensaje.put("key",jwtToken);
        mensaje.put("user",user);
        if(e == null){
            mensaje.put("status",400);
            mensaje.put("message","No se pudo insertar en la entidad "+nombreEntidad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",201);
        mensaje.put("data",sucursalDTO);
        return ResponseEntity.ok(mensaje);
    }
    @GetMapping("/empleados/{id}")
    public ResponseEntity<?> readAllBySucursal(@PathVariable Integer id,@RequestHeader String user){
        mensaje= new HashMap<>();
        List<Empleado> personas = (List<Empleado>) service.readAllByIdSucursal(id);
        List<EmpleadoDTO> empleadoDTOS = empleadoMP.mapEmpleado(personas);
        jwtToken = session.getJWTToken(user);
        mensaje.put("key",jwtToken);
        mensaje.put("user",user);
        if(personas.isEmpty()){
            mensaje.put("status",400);
            mensaje.put("message","No hay empleados en la sucursal con id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",200);
        mensaje.put("data",empleadoDTOS);
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Sucursal sucursal, @PathVariable Integer id,@RequestHeader String user){
        mensaje= new HashMap<>();
        Optional<Sucursal> sucursalExiste = service.readById(id);
        jwtToken = session.getJWTToken(user);
        mensaje.put("key",jwtToken);
        mensaje.put("user",user);
        if(!sucursalExiste.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No existe sucursal con id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        Sucursal sucursalEditable = sucursalExiste.get();
        sucursalEditable.setNombre(sucursal.getNombre());
        mensaje.put("status",200);
        mensaje.put("data",mapper.mapSucursal(service.save(sucursalEditable)));
        return ResponseEntity.ok(mensaje);

    }
    @PutMapping("/{idSucursal}/ciudad/{idCiudad}")
    public ResponseEntity<?> asignarCiudad(@PathVariable Integer idSucursal,@PathVariable Integer idCiudad,@RequestHeader String user){
        mensaje= new HashMap<>();
        Optional<Sucursal> sucursal = service.readById(idSucursal);
        Optional<Ciudad> ciudad = ciudadDAO.readById(idCiudad);
        jwtToken = session.getJWTToken(user);
        mensaje.put("key",jwtToken);
        mensaje.put("user",user);
        if(!sucursal.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No existe sucursal con id "+idSucursal);
            return ResponseEntity.badRequest().body(mensaje);
        }
        if(!ciudad.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No existe ciudad con el id"+idCiudad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        Sucursal sucursalSave = sucursal.get();
        Ciudad ciudadSave = ciudad.get();
        sucursalSave.setCiudad(ciudadSave);
        mensaje.put("status",200);
        mensaje.put("data",mapper.mapSucursal(service.save(sucursalSave)));
        return ResponseEntity.ok(mensaje);
    }

}
