package com.banco.backend.controllers;

import com.banco.backend.models.dao.PersonaDAO;
import com.banco.backend.models.dto.ClienteDTO;
import com.banco.backend.models.dto.EmpleadoDTO;
import com.banco.backend.models.entities.Cliente;
import com.banco.backend.models.entities.Empleado;
import com.banco.backend.models.entities.Persona;
import com.banco.backend.models.mappers.ClienteMPImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController extends PersonaController{

    private Map<String,Object> mensaje;
    @Autowired
    private ClienteMPImpl clienteMP;

    @Autowired
    public ClienteController(@Qualifier("clienteDAOImpl")PersonaDAO service) {
        super(service);
        nombreEntidad="Cliente";
    }


    @Override
    public ResponseEntity<?> readAll(){
        mensaje = new HashMap<>();
        List<Cliente> personas = (List<Cliente>) service.readAllCostumers();
        List<ClienteDTO> clienteDTOS = clienteMP.mapCliente(personas);
        if(clienteDTOS.isEmpty()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",200);
        mensaje.put("data",clienteDTOS);
        return ResponseEntity.ok(mensaje);
    }

    @Override
    public ResponseEntity<?> readById(@PathVariable Integer id){
        mensaje = new HashMap<>();
        Optional<Persona> e = service.readById(id);
        ClienteDTO clienteDTO = clienteMP.mapCliente((Cliente) e.get());
        if(!e.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad+" con el id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",200);
        mensaje.put("data",clienteDTO);
        return ResponseEntity.ok(mensaje);
    }
    @Override
    public ResponseEntity<?> save(@RequestBody Empleado entidad){
        mensaje = new HashMap<>();
        Cliente e = (Cliente) service.save(entidad);
        ClienteDTO clienteDTO = clienteMP.mapCliente(e);
        if(clienteDTO == null){
            mensaje.put("status",400);
            mensaje.put("message","No se pudo insertar en la entidad "+nombreEntidad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",201);
        mensaje.put("data",clienteDTO);
        return ResponseEntity.ok(mensaje);
    }
}
