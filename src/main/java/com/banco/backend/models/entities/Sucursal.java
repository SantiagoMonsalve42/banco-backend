package com.banco.backend.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name ="sucursales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre",length = 50)
    private String nombre;

    @Embedded
    private Direccion direccion;

    @OneToMany(mappedBy = "sucursal",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Empleado> empleados;

    @ManyToOne(
            optional = true,
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            }
    )
    @JoinColumn(name = "ciudad_id")
    private Ciudad ciudad;


}
