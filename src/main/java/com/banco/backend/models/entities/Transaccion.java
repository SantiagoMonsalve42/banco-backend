package com.banco.backend.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name ="transaccion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "destino")
    private String destino;
    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;
    @Column(name = "estado")
    private Byte estado;
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
