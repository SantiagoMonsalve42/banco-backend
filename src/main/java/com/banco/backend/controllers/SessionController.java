package com.banco.backend.controllers;

import com.banco.backend.models.dao.ClienteDAO;
import com.banco.backend.models.dao.EmpleadoDAO;
import com.banco.backend.models.dto.SessionDTO;
import com.banco.backend.models.entities.Cliente;
import com.banco.backend.models.entities.Empleado;
import com.banco.backend.utils.Session;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/session")
public class SessionController {
    private Map<String,Object> mensaje;
    private EmpleadoDAO empleadoDAO;
    private ClienteDAO clienteDAO;

    public SessionController(EmpleadoDAO empleadoDAO, ClienteDAO clienteDAO) {
        this.empleadoDAO = empleadoDAO;
        this.clienteDAO = clienteDAO;
    }
    //tipo 1 empleado; tipo 2 cliente
    @PostMapping
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
                mensaje.put("email",cliente.get().getEmail());
                mensaje.put("tipo",2);
                return ResponseEntity.ok(mensaje);
            }
        }
        mensaje.put("status",401);
        mensaje.put("message","unauthorized: bad login");
        return ResponseEntity.badRequest().body(mensaje);
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
