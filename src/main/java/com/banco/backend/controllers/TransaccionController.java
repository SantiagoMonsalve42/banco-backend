package com.banco.backend.controllers;

import com.banco.backend.models.dao.CuentaDAO;
import com.banco.backend.models.dao.TransaccionDAO;
import com.banco.backend.models.dto.PrestamoDTO;
import com.banco.backend.models.dto.TransaccionDTO;
import com.banco.backend.models.entities.Cuenta;
import com.banco.backend.models.entities.Prestamo;
import com.banco.backend.models.entities.Transaccion;
import com.banco.backend.models.mappers.TransaccionMPImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/transaccion")
public class TransaccionController extends GenericController<Transaccion, TransaccionDAO> {

    private String jwtToken;
    @Autowired
    private SessionController session;

    private Map<String,Object> mensaje;
    private CuentaDAO cuentaDAO;
    private TransaccionMPImpl mapper;

    public TransaccionController(TransaccionDAO service, CuentaDAO cuentaDAO , TransaccionMPImpl mapper) {
        super(service);
        this.cuentaDAO = cuentaDAO;
        this.mapper = mapper;
        nombreEntidad="Transaccion";
    }
    @Override
    public ResponseEntity<?> readAll(@RequestHeader String user){
        mensaje = new HashMap<>();
        List<Transaccion> list = (List<Transaccion>) service.readAll();
        List<TransaccionDTO> transaccionDTOS = mapper.mapTransaccion(list);
        jwtToken = session.getJWTToken(user);
        mensaje.put("key",jwtToken);
        mensaje.put("user",user);
        if(list.isEmpty()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",200);
        mensaje.put("data",transaccionDTOS);
        return ResponseEntity.ok(mensaje);
    }
    @Override
    public ResponseEntity<?> readById(@PathVariable Integer id,@RequestHeader String user){
        mensaje = new HashMap<>();
        Optional<Transaccion> e = service.readById(id);
        jwtToken = session.getJWTToken(user);
        mensaje.put("key",jwtToken);
        mensaje.put("user",user);
        if(!e.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad+" con el id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }

        Transaccion transaccion = e.get();
        TransaccionDTO transaccionDTO = mapper.mapTransaccion(transaccion);
        mensaje.put("status",200);
        mensaje.put("data",transaccionDTO);
        return ResponseEntity.ok(mensaje);
    }
    @Override
    public ResponseEntity<?> save(@RequestBody Transaccion entidad,@RequestHeader String user){
        mensaje = new HashMap<>();
        Transaccion e = service.save(entidad);
        jwtToken = session.getJWTToken(user);
        mensaje.put("key",jwtToken);
        mensaje.put("user",user);
        if(e == null){
            mensaje.put("status",400);
            mensaje.put("message","No se pudo insertar en la entidad "+nombreEntidad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        TransaccionDTO transaccionDTO = mapper.mapTransaccion(e);
        mensaje.put("status",201);
        mensaje.put("data",transaccionDTO);
        return ResponseEntity.ok(mensaje);
    }
    @PutMapping("{idTransaccion}/cuenta/{idCuenta}")
    public ResponseEntity<?> asignarCuenta(@PathVariable Integer idCuenta,@PathVariable Integer idTransaccion,@RequestHeader String user){
        mensaje = new HashMap<>();
        Optional<Transaccion> transaccionExiste = service.readById(idTransaccion);
        Optional<Cuenta> cuentaExiste = cuentaDAO.readById(idCuenta);
        jwtToken = session.getJWTToken(user);
        mensaje.put("key",jwtToken);
        mensaje.put("user",user);
        if(!transaccionExiste.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron "+nombreEntidad+" con id "+idTransaccion);
            return ResponseEntity.badRequest().body(mensaje);
        }
        if(!cuentaExiste.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron cuentas con id "+idCuenta);
            return ResponseEntity.badRequest().body(mensaje);
        }
        Transaccion transaccion = transaccionExiste.get();
        Cuenta cuenta = cuentaExiste.get();
        transaccion.setCuenta(cuenta);
        Transaccion save = service.save(transaccion);
        TransaccionDTO transaccionDTO = mapper.mapTransaccion(save);
        mensaje.put("status",200);
        mensaje.put("data",transaccionDTO);
        return ResponseEntity.ok(mensaje);
    }
    @PutMapping("/saldo")
    public ResponseEntity<?> sumarORestar(@RequestBody Transaccion transaccion,@RequestHeader String user){
        mensaje = new HashMap<>();
        Optional<Transaccion> transaccionExiste = service.readById(transaccion.getId());
        jwtToken = session.getJWTToken(user);
        mensaje.put("key",jwtToken);
        mensaje.put("user",user);
        if(!transaccionExiste.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No existe "+nombreEntidad+" con el id cliente "+transaccion.getId());
            return ResponseEntity.badRequest().body(mensaje);
        }

        Transaccion transaccionSaver = transaccionExiste.get();
        BigDecimal saldoReal = new BigDecimal(String.valueOf(transaccionSaver.getSaldo()));
        saldoReal = saldoReal.add(transaccion.getSaldo());
        System.out.println(saldoReal);

        if(saldoReal.compareTo(BigDecimal.ZERO) < 0){
            mensaje.put("status",400);
            mensaje.put("message","Saldo insuficiente");
            return ResponseEntity.badRequest().body(mensaje);
        }else{
            transaccionSaver.setSaldo(saldoReal);
        }
        Transaccion save = service.save(transaccionSaver);
        TransaccionDTO transaccionDTO = mapper.mapTransaccion(save);
        mensaje.put("status",200);
        mensaje.put("data",transaccionDTO);
        return ResponseEntity.ok(mensaje);
    }
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<?> readByIdCliente(@PathVariable Integer idCliente,@RequestHeader String user){
        mensaje = new HashMap<>();
        List<Transaccion> transaccions = (List<Transaccion>) service.readByClienteId(idCliente);
        jwtToken = session.getJWTToken(user);
        mensaje.put("key",jwtToken);
        mensaje.put("user",user);
        if(transaccions.isEmpty()){
            mensaje.put("status",400);
            mensaje.put("message","No hay transacciones asociadas a la persona con id "+idCliente);
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<TransaccionDTO> transaccionDTOS = mapper.mapTransaccion(transaccions);
        mensaje.put("status",200);
        mensaje.put("data",transaccionDTOS);
        return ResponseEntity.ok(mensaje);

    }
}

