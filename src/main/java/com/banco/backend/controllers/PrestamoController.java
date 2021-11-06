package com.banco.backend.controllers;

import com.banco.backend.models.dao.CuentaDAO;
import com.banco.backend.models.dao.PrestamoDAO;
import com.banco.backend.models.dto.CuentaDTO;
import com.banco.backend.models.dto.PrestamoDTO;
import com.banco.backend.models.entities.Cuenta;
import com.banco.backend.models.entities.Prestamo;
import com.banco.backend.models.mappers.PrestamoMPImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/prestamo")
public class PrestamoController extends GenericController<Prestamo, PrestamoDAO> {
    private Map<String,Object> mensaje;
    private PrestamoMPImpl mapper;
    private CuentaDAO cuentaDAO;

    public PrestamoController(PrestamoDAO service, PrestamoMPImpl mapper, CuentaDAO cuentaDAO) {
        super(service);
        this.mapper = mapper;
        this.cuentaDAO = cuentaDAO;
        nombreEntidad = "Prestamo";
    }
    @Override
    public ResponseEntity<?> readAll(){
        mensaje = new HashMap<>();
        List<Prestamo> list = (List<Prestamo>) service.readAll();
        List<PrestamoDTO> prestamoDTOS = mapper.mapPrestamo(list);
        if(list.isEmpty()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",200);
        mensaje.put("data",prestamoDTOS);
        return ResponseEntity.ok(mensaje);
    }
    @Override
    public ResponseEntity<?> readById(@PathVariable Integer id){
        mensaje = new HashMap<>();
        Optional<Prestamo> e = service.readById(id);
        if(!e.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron datos asociados a la entidad "+nombreEntidad+" con el id "+id);
            return ResponseEntity.badRequest().body(mensaje);
        }

        Prestamo prestamo = e.get();
        PrestamoDTO prestamoDTO = mapper.mapPrestamo(prestamo);
        mensaje.put("status",200);
        mensaje.put("data",prestamoDTO);
        return ResponseEntity.ok(mensaje);
    }
    @Override
    public ResponseEntity<?> save(@RequestBody Prestamo entidad){
        mensaje = new HashMap<>();
        Prestamo e = service.save(entidad);
        if(e == null){
            mensaje.put("status",400);
            mensaje.put("message","No se pudo insertar en la entidad "+nombreEntidad);
            return ResponseEntity.badRequest().body(mensaje);
        }
        PrestamoDTO prestamoDTO = mapper.mapPrestamo(e);
        mensaje.put("status",201);
        mensaje.put("data",prestamoDTO);
        return ResponseEntity.ok(mensaje);
    }
    @PutMapping("{idPrestamo}/cuenta/{idCuenta}")
    public ResponseEntity<?> asignarCuenta(@PathVariable Integer idCuenta,@PathVariable Integer idPrestamo){
        mensaje = new HashMap<>();
        Optional<Prestamo> prestamoExiste = service.readById(idPrestamo);
        Optional<Cuenta> cuentaExiste = cuentaDAO.readById(idCuenta);
        if(!prestamoExiste.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron "+nombreEntidad+" con id "+idPrestamo);
            return ResponseEntity.badRequest().body(mensaje);
        }
        if(!cuentaExiste.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No se encontraron cuentas con id "+idCuenta);
            return ResponseEntity.badRequest().body(mensaje);
        }
        Prestamo prestamo = prestamoExiste.get();
        Cuenta cuenta = cuentaExiste.get();
        prestamo.setCuenta(cuenta);
        Prestamo save = service.save(prestamo);
        PrestamoDTO prestamoDTO = mapper.mapPrestamo(save);
        mensaje.put("status",200);
        mensaje.put("data",prestamoDTO);
        return ResponseEntity.ok(mensaje);
    }
    @PutMapping("/saldo")
    public ResponseEntity<?> sumarORestar(@RequestBody Prestamo prestamo){
        mensaje = new HashMap<>();
        Optional<Prestamo> prestamoExiste = service.readById(prestamo.getId());
        if(!prestamoExiste.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","No existe "+nombreEntidad+" con el id cliente "+prestamo.getId());
            return ResponseEntity.badRequest().body(mensaje);
        }

        Prestamo prestamoSaver = prestamoExiste.get();
        BigDecimal saldoReal = new BigDecimal(String.valueOf(prestamoSaver.getSaldo()));
        saldoReal = saldoReal.add(prestamo.getSaldo());
        System.out.println(saldoReal);

        if(saldoReal.compareTo(BigDecimal.ZERO) < 0){
            mensaje.put("status",400);
            mensaje.put("message","Saldo insuficiente");
            return ResponseEntity.badRequest().body(mensaje);
        }else{
            prestamoSaver.setSaldo(saldoReal);
        }
        Prestamo save = service.save(prestamoSaver);
        PrestamoDTO prestamoDTO = mapper.mapPrestamo(save);
        mensaje.put("status",200);
        mensaje.put("data",prestamoDTO);
        return ResponseEntity.ok(mensaje);
    }
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<?> readByClientId(@PathVariable Integer idCliente){
        mensaje = new HashMap<>();
        List<Prestamo> prestamos = (List<Prestamo>)service.readByClientId(idCliente);
        if(prestamos.isEmpty()){
            mensaje.put("status",400);
            mensaje.put("message","No hay prestamos asociados al cliente con id "+idCliente);
            return ResponseEntity.badRequest().body(mensaje);
        }
        List<PrestamoDTO> prestamoDTOS = mapper.mapPrestamo(prestamos);
        mensaje.put("status",200);
        mensaje.put("data",prestamoDTOS);
        return ResponseEntity.ok(mensaje);
    }
}
