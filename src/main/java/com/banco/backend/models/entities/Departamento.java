package com.banco.backend.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "departamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre",nullable = false,length = 70)
    private String nombre;

    @OneToMany(
            mappedBy = "departamento",
            fetch = FetchType.LAZY
    )
    private Set<Ciudad> ciudades;
}
