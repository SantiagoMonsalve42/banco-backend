package com.banco.backend.controllers;

import com.banco.backend.models.dao.ClienteDAO;
import com.banco.backend.models.dao.EmpleadoDAO;
import com.banco.backend.models.dao.PersonaDAO;
import com.banco.backend.models.dto.PersonaCreacionDTO;
import com.banco.backend.models.dto.PersonaDTO;
import com.banco.backend.models.dto.SessionDTO;
import com.banco.backend.models.entities.Cliente;
import com.banco.backend.models.entities.Empleado;
import com.banco.backend.models.entities.Persona;
import com.banco.backend.models.mappers.ClienteMPImpl;
import com.banco.backend.utils.Session;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/session")
public class SessionController {
    private Map<String,Object> mensaje;
    private EmpleadoDAO empleadoDAO;
    private ClienteDAO clienteDAO;
    private Session session;
    private ClienteMPImpl mapper;
    private PersonaDAO personaDAO;
    public SessionController(EmpleadoDAO empleadoDAO, ClienteDAO clienteDAO, ClienteMPImpl mapper, @Qualifier("clienteDAOImpl") PersonaDAO personaDAO) {
        this.empleadoDAO = empleadoDAO;
        this.clienteDAO = clienteDAO;
        this.mapper = mapper;
        this.personaDAO = personaDAO;
    }
    //tipo 1 empleado; tipo 2 cliente
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SessionDTO sessionDTO){
        mensaje = new HashMap<>();
        String token,id64;
        Session generator = new Session();
        String pass = generator.getSHA512(sessionDTO.getPass());
        Optional<Empleado> empleado = empleadoDAO.readByMailEmpleado(sessionDTO.getEmail());
        Optional<Cliente> cliente = clienteDAO.readByMailCliente(sessionDTO.getEmail());
        if(empleado.isPresent()){
            if(pass.equals(empleado.get().getPassword())){
                id64 = Base64.getEncoder().encodeToString(empleado.get().getId().toString().getBytes());
                token = getJWTToken(empleado.get().getEmail());
                mensaje.put("key",token);
                mensaje.put("user",id64);
                mensaje.put("status",200);
                mensaje.put("email",empleado.get().getEmail());
                mensaje.put("tipo",1);
                return ResponseEntity.ok(mensaje);
            }
        }
        if(cliente.isPresent()){
            if(pass.equals(cliente.get().getPassword())){
                id64 = Base64.getEncoder().encodeToString(cliente.get().getId().toString().getBytes());
                token = getJWTToken(cliente.get().getEmail());
                mensaje.put("key",token);
                mensaje.put("user",id64);
                mensaje.put("status",200);
                mensaje.put("email",cliente.get().getEmail());
                mensaje.put("tipo",2);
                return ResponseEntity.ok(mensaje);
            }
        }
        mensaje.put("status",401);
        mensaje.put("message","unauthorized: bad login");
        return ResponseEntity.badRequest().body(mensaje);
    }
    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody Persona persona){
        PersonaDTO dto = null;
        mensaje = new HashMap<>();
        session = new Session();
        Optional<Cliente> CorreoClienteExiste = clienteDAO.readByMailCliente(persona.getEmail());
        Optional<Empleado> CorreoEmpleadoExiste = empleadoDAO.readByMailEmpleado(persona.getEmail());
        if(CorreoEmpleadoExiste.isPresent() || CorreoClienteExiste.isPresent()){
            mensaje.put("status",400);
            mensaje.put("message","El correo ya existe en el sistema");
            return ResponseEntity.badRequest().body(mensaje);
        }
        persona.setPassword(session.getSHA512(persona.getPassword()));
        if(persona instanceof Empleado){
            mensaje.put("status",400);
            mensaje.put("message","Error en el tipo de usuario ingresado");
            return ResponseEntity.badRequest().body(mensaje);
        }
        Persona save = personaDAO.save(persona);
        if(save instanceof Cliente){
            dto=mapper.mapCliente((Cliente) save);
        }else{
            mensaje.put("status",400);
            mensaje.put("message","Error en el tipo de usuario ingresado");
            return ResponseEntity.badRequest().body(mensaje);
        }
        if(save == null){
            mensaje.put("status",400);
            mensaje.put("message","No se pudo crear el usuario");
            return ResponseEntity.badRequest().body(mensaje);
        }
        mensaje.put("status",201);
        mensaje.put("data",dto);
        return ResponseEntity.ok(mensaje);
    }

    public String getJWTToken(String username) {
        String secretKey = "Dh4wuM+iyLnKYCt8";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("bancoJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
