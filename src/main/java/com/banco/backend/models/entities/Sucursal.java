package com.banco.backend.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name ="sucursales")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre",length = 50)
    private String nombre;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_id",referencedColumnName = "id")
    private Direccion direccion;

    @OneToMany(mappedBy = "sucursal",fetch = FetchType.LAZY)
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
