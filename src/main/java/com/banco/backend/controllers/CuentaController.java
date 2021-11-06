package com.banco.backend.controllers;

import com.banco.backend.models.dao.ClienteDAO;
import com.banco.backend.models.dao.CuentaDAO;
import com.banco.backend.models.dto.CuentaDTO;
import com.banco.backend.models.entities.Cliente;
import com.banco.backend.models.entities.Cuenta;
import com.banco.backend.models.entities.Persona;
import com.banco.backend.models.mappers.CuentaMPImpl;
import com.banco.backend.services.CuentaDAOImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cuenta")
public class CuentaController extends GenericController<Cuenta, CuentaDAO> {
    private Map<String,Object> mensaje;
    private CuentaMPImpl mapper;
    private ClienteDAO clienteDAO;

    public CuentaController(CuentaDAO service, CuentaMPImpl mapper, ClienteDAO clienteDAO) {
        super(service);
        this.mapper = mapper;
        this.clienteDAO = clienteDAO;

        nombreEntidad="Cuenta";
    }

    @Override
    public ResponseEntity<?> readAll(){
        mensaje = new HashMap<>();
        List<Cuenta> list = (List<Cuenta>) service.readAll();
        List<CuentaDTO> cuentaDTOS = mapper.mapCuenta(list);
        if(list.isEmpty()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",200);
        mensaje.put("data",cuentaDTOS);
        return ResponseEntity.ok(mensaje);
    }
    @Override
    public ResponseEntity<?> readById(@PathVariable Integer id){
        mensaje = new HashMap<>();
        Optional<Cuenta> e = service.readById(id);
        if(!e.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad+" con el id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }
        CuentaDTO cuentaDTO = mapper.mapCuenta(e.get());
        mensaje.put("status",200);
        mensaje.put("data",cuentaDTO);
        return ResponseEntity.ok(mensaje);
    }
    @Override
    public ResponseEntity<?> save(@RequestBody Cuenta entidad){
        mensaje = new HashMap<>();
        Cuenta e = service.save(entidad);
        if(e == null){
            mensaje.put("status",400);
            mensaje.put("message","No se pudo insertar en la entidad "+nombreEntidad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        CuentaDTO cuentaDTO = mapper.mapCuenta(e);
        mensaje.put("status",201);
        mensaje.put("data",cuentaDTO);
        return ResponseEntity.ok(mensaje);
    }
    @PutMapping("{idCuenta}/cliente/{idCliente}")
    public ResponseEntity<?> asignarCliente(@PathVariable Integer idCuenta,@PathVariable Integer idCliente){
        mensaje = new HashMap<>();
        Optional<Cuenta> cuentaExiste = service.readById(idCuenta);
        Optional<Persona> personaExiste = clienteDAO.readById(idCliente);
        if(!cuentaExiste.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No existe "+nombreEntidad+" con id "+idCuenta);
            return ResponseEntity.badRequest().body(mensaje);
        }
        if(!cuentaExiste.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No existe cliente con id "+idCliente);
            return ResponseEntity.badRequest().body(mensaje);
        }
        Cuenta cuenta = cuentaExiste.get();
        Persona persona = personaExiste.get();
        cuenta.setCliente((Cliente) persona);
        Cuenta response = service.save(cuenta);
        CuentaDTO cuentaDTO = mapper.mapCuenta(response);
        mensaje.put("status",200);
        mensaje.put("data",cuentaDTO);
        return ResponseEntity.ok(mensaje);
    }
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<?> readByIdCliente(@PathVariable Integer idCliente){
        mensaje = new HashMap<>();
        List<Cuenta> list = (List<Cuenta>) service.getCuentasByIdPersona(idCliente);
        if(list.isEmpty()){
            mensaje.put("status",400);
            mensaje.put("message","No existe "+nombreEntidad+" con el id cliente "+idCliente);
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<CuentaDTO> cuentaDTOS = mapper.mapCuenta(list);
        mensaje.put("status",200);
        mensaje.put("data",cuentaDTOS);
        return ResponseEntity.ok(mensaje);
    }
    @PutMapping("/saldo")
    public ResponseEntity<?> sumarORestar(@RequestBody Cuenta cuenta){
        mensaje = new HashMap<>();
        Optional<Cuenta> cuentaExiste = service.readById(cuenta.getId());
        if(!cuentaExiste.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No existe "+nombreEntidad+" con el id cliente "+cuenta.getId());
            return ResponseEntity.badRequest().body(mensaje);
        }

        Cuenta cuentaSaver = cuentaExiste.get();
        BigDecimal saldoReal = new BigDecimal(String.valueOf(cuentaSaver.getSaldo()));
        saldoReal = saldoReal.add(cuenta.getSaldo());
        System.out.println(saldoReal);

        if(saldoReal.compareTo(BigDecimal.ZERO) < 0){
            mensaje.put("status",400);
            mensaje.put("message","Saldo insuficiente");
            return ResponseEntity.badRequest().body(mensaje);
        }else{
            cuentaSaver.setSaldo(saldoReal);
        }
        Cuenta save = service.save(cuentaSaver);
        CuentaDTO cuentaDTO = mapper.mapCuenta(save);
        mensaje.put("status",200);
        mensaje.put("data",cuentaDTO);
        return ResponseEntity.ok(mensaje);
    }
}
