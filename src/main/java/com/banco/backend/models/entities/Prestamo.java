package com.banco.backend.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name ="prestamos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "saldo")
    private BigDecimal saldo;
    @Column(name = "estado",length = 1)
    private Byte estado;
    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
    @PrePersist
    private void prePersist(){
        this.fechaAlta = LocalDateTime.now();
    }
    @PreUpdate
    private void preUpdate(){
        this.fechaModificacion=LocalDateTime.now();
    }
    @ManyToOne(
            optional = true,
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            }
    )
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;
}

