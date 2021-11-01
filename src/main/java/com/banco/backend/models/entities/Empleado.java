package com.banco.backend.models.entities;

import com.banco.backend.models.enums.TipoCliente;
import com.banco.backend.models.enums.TipoEmpleado;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "empleado")
@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "persona_id")
public class Empleado extends Persona implements Serializable {
    @Column(nullable = false,length = 20)
    private BigDecimal sueldo;
    @Column(nullable = false,length = 100)
    private TipoEmpleado tipo_empleado;

    @ManyToOne(
          optional = true,
          fetch = FetchType.LAZY,
          cascade = {
                  CascadeType.MERGE,
                  CascadeType.PERSIST
          }
    )
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

}
