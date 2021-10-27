package com.banco.backend.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "direcciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 10,nullable = false)
    private String calle;
    @Column(length = 10,nullable = false)
    private String numero;
    @Column(length = 10,nullable = false)
    private String codigo_postal;
    @Column(length = 10,nullable = false)
    private String piso;
    @Column(length = 10,nullable = false)
    private String localidad;

    @OneToOne(mappedBy = "direccion")
    private Persona persona;

    @OneToOne(mappedBy = "direccion")
    private Sucursal sucursal;


}
