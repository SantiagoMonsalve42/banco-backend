package com.banco.backend.controllers;

import com.banco.backend.models.dao.PersonaDAO;
import com.banco.backend.models.dao.SucursalDAO;
import com.banco.backend.models.dto.EmpleadoDTO;
import com.banco.backend.models.entities.Empleado;
import com.banco.backend.models.entities.Persona;
import com.banco.backend.models.entities.Sucursal;
import com.banco.backend.models.mappers.EmpleadoMPImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController extends PersonaController{
    private Map<String,Object> mensaje;
    @Autowired
    private EmpleadoMPImpl empleadoMP;
    @Autowired
    private SucursalDAO sucursalDAO;
    @Autowired
    public EmpleadoController(@Qualifier("empleadoDAOImpl") PersonaDAO service) {
        super(service);
        nombreEntidad="Empleado";
    }

    @Override
    public ResponseEntity<?> readAll(){
        mensaje = new HashMap<>();
        List<Empleado> personas = (List<Empleado>) service.readAllEmployees();
        List<EmpleadoDTO> empleadoDTOS = empleadoMP.mapEmpleado(personas);
        if(empleadoDTOS.isEmpty()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",200);
        mensaje.put("data",empleadoDTOS);
        return ResponseEntity.ok(mensaje);
    }
    @Override
    public ResponseEntity<?> readById(@PathVariable Integer id){
        mensaje = new HashMap<>();
        Optional<Persona> e = service.readById(id);
        EmpleadoDTO empleadoDTO = empleadoMP.mapEmpleado((Empleado) e.get());
        if(!e.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad+" con el id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",200);
        mensaje.put("data",empleadoDTO);
        return ResponseEntity.ok(mensaje);
    }
    @Override
    public ResponseEntity<?> save(@RequestBody Empleado entidad){
        mensaje = new HashMap<>();
        Empleado e = (Empleado) service.save(entidad);
        EmpleadoDTO empleadoDTO = empleadoMP.mapEmpleado(e);
        if(empleadoDTO == null){
            mensaje.put("status",400);
            mensaje.put("message","No se pudo insertar en la entidad "+nombreEntidad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",201);
        mensaje.put("data",empleadoDTO);
        return ResponseEntity.ok(mensaje);
    }
    @PutMapping("/tipo/{id}")
    public ResponseEntity<?> cambiarTipo(@PathVariable Integer id, @RequestBody Empleado empleado){
        mensaje = new HashMap<>();
        Optional<Persona> persona = service.readById(id);
        if(!persona.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontro el empleado con id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        Empleado employeeSave = (Empleado)persona.get();
        employeeSave.setTipo_empleado(empleado.getTipo_empleado());
        Persona save = service.save(employeeSave);
        EmpleadoDTO empleadoDTO = empleadoMP.mapEmpleado((Empleado) save);
        mensaje.put("status",200);
        mensaje.put("data",empleadoDTO);
        return ResponseEntity.ok(mensaje);

    }
    @PutMapping("/sueldo/{id}")
    public ResponseEntity<?> cambiarSueldo(@PathVariable Integer id, @RequestBody Empleado empleado){
        mensaje = new HashMap<>();
        Optional<Persona> persona = service.readById(id);
        if(!persona.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontro el empleado con id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        Empleado employeeSave = (Empleado)persona.get();
        employeeSave.setSueldo(empleado.getSueldo());
        Persona save = service.save(employeeSave);
        EmpleadoDTO empleadoDTO = empleadoMP.mapEmpleado((Empleado) save);
        mensaje.put("status",200);
        mensaje.put("data",empleadoDTO);
        return ResponseEntity.ok(mensaje);
    }
    @PutMapping("/{idEmpleado}/sucursal/{idSucursal}")
    public ResponseEntity<?> asignarSucursal(@PathVariable Integer idEmpleado,@PathVariable Integer idSucursal){
        mensaje = new HashMap<>();
        Optional<Persona> persona = service.readById(idEmpleado);
        Optional<Sucursal> sucursal = sucursalDAO.readById(idSucursal);
        if(!persona.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontro el empleado con id "+idEmpleado);
            return ResponseEntity.badRequest().body(mensaje);
        }
        if(!sucursal.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontro la sucursal con id "+idSucursal);
            return ResponseEntity.badRequest().body(mensaje);
        }
        Empleado personaSaver =(Empleado) persona.get();
        Sucursal sucursalSaver = sucursal.get();
        personaSaver.setSucursal(sucursalSaver);
        Persona save = service.save(personaSaver);
        EmpleadoDTO empleadoDTO = empleadoMP.mapEmpleado((Empleado) save);
        if(save == null){
            mensaje.put("status",400);
            mensaje.put("message","Error al asignar la sucursal");
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",200);
        mensaje.put("data",empleadoDTO);
        return ResponseEntity.ok(mensaje);
    }


}
