package com.banco.backend.controllers;

import com.banco.backend.models.dao.PersonaDAO;
import com.banco.backend.models.dto.PersonaDTO;
import com.banco.backend.models.entities.Cliente;
import com.banco.backend.models.entities.Empleado;
import com.banco.backend.models.entities.Persona;
import com.banco.backend.models.entities.Telefono;
import com.banco.backend.models.mappers.ClienteMPImpl;
import com.banco.backend.models.mappers.EmpleadoMPImpl;
import com.banco.backend.utils.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class PersonaController extends GenericController<Persona, PersonaDAO> {
    private Map<String,Object> mensaje;
    private Session session;
    @Autowired
    private EmpleadoMPImpl empleadoMP;
    @Autowired
    private ClienteMPImpl clienteMP;
    public PersonaController(PersonaDAO service) {
        super(service);
    }
    @Override
    public ResponseEntity<?> save(@RequestBody Persona persona){
        PersonaDTO dto = null;
        mensaje = new HashMap<>();
        session = new Session();

        persona.setPassword(session.getSHA512(persona.getPassword()));
        Persona save = service.save(persona);
        if(save instanceof Empleado){
            dto=empleadoMP.mapEmpleado((Empleado) save);
        }
        if(save instanceof Cliente){
            dto=clienteMP.mapCliente((Cliente) save);
        }
        if(save == null){
            mensaje.put("status",400);
            mensaje.put("message","No se pudo insertar en la entidad "+nombreEntidad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",201);
        mensaje.put("data",dto);
        return ResponseEntity.ok(mensaje);
    }
    
    @PutMapping("/pass/{id}")
    public ResponseEntity<?> changePassword(@RequestBody Persona persona,@PathVariable Integer id){
        PersonaDTO dto = null;
        mensaje = new HashMap<>();
        session = new Session();
        Optional<Persona> personaRead = service.readById(id);
        if(!personaRead.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontro "+nombreEntidad+" con el id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        Persona personaSaver = personaRead.get();
        personaSaver.setPassword(session.getSHA512(persona.getPassword()));
        Persona save = service.save(personaSaver);
        if(personaRead.get() instanceof Empleado){
            dto=empleadoMP.mapEmpleado((Empleado) save);
        }
        if(personaRead.get() instanceof Cliente){
            dto=clienteMP.mapCliente((Cliente) save);
        }
        mensaje.put("status",200);
        mensaje.put("data", dto);
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/phones/{id}")
    public ResponseEntity<?> changePhones(@RequestBody Persona persona,@PathVariable Integer id){
        PersonaDTO dto = null;
        mensaje = new HashMap<>();
        session = new Session();
        Optional<Persona> personaRead = service.readById(id);
        if(!personaRead.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontro "+nombreEntidad+" con el id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        Persona personaSaver = personaRead.get();
        Telefono telefono = personaSaver.getTelefono();
        telefono.setTelefono_fijo(persona.getTelefono().getTelefono_fijo());
        telefono.setTelefono_movil(persona.getTelefono().getTelefono_movil());
        personaSaver.setTelefono(telefono);
        Persona save = service.save(personaSaver);
        if(personaRead.get() instanceof Empleado){
            dto=empleadoMP.mapEmpleado((Empleado) save);
        }
        if(personaRead.get() instanceof Cliente){
            dto=clienteMP.mapCliente((Cliente) save);
        }
        mensaje.put("status",200);
        mensaje.put("data", dto);
        return ResponseEntity.ok(mensaje);
    }

    @PutMapping("/names/{id}")
    public ResponseEntity<?> changeNames(@RequestBody Persona persona,@PathVariable Integer id){
        PersonaDTO dto = null;
        mensaje = new HashMap<>();
        session = new Session();
        Optional<Persona> personaRead = service.readById(id);
        if(!personaRead.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontro "+nombreEntidad+" con el id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        Persona personaSaver = personaRead.get();
        personaSaver.setPrimer_nombre(persona.getPrimer_nombre());
        personaSaver.setSegundo_nombre(persona.getSegundo_nombre());
        personaSaver.setPrimer_apellido(persona.getPrimer_apellido());
        personaSaver.setSegundo_apellido(persona.getSegundo_apellido());
        Persona status = service.save(personaSaver);
        if(status == null){
            mensaje.put("status",400);
            mensaje.put("message","Error al editar al usuario con id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        if(personaRead.get() instanceof Empleado){
            dto=empleadoMP.mapEmpleado((Empleado) status);
        }
        if(personaRead.get() instanceof Cliente){
            dto=clienteMP.mapCliente((Cliente) status);
        }
        mensaje.put("status",200);
        mensaje.put("data",dto );
        return ResponseEntity.ok(mensaje);
    }

    public abstract ResponseEntity<?> save(@RequestBody Empleado entidad);
}
