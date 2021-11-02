package com.banco.backend.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name ="personas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Cliente.class,name = "cliente"),
        @JsonSubTypes.Type(value = Empleado.class,name = "empleado"),
})
public abstract class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="primer_nombre",nullable = false,length = 40)
    private String primer_nombre;
    @Column(name="segundo_nombre",nullable = true,length = 40)
    private String segundo_nombre;
    @Column(name="primer_apellido",nullable = false,length = 40)
    private String primer_apellido;
    @Column(name="segundo_apellido",nullable = true,length = 40)
    private String segundo_apellido;
    @Column(name="email",nullable = false,length = 60,unique = true)
    private String email;
    @Column(name="password",nullable = false,length = 150)
    private String password;
    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;
    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;


    @Embedded
    private Direccion direccion;

    @Embedded
    private Telefono telefono;


    @PrePersist
    private void prePersist(){
        this.fechaAlta = LocalDateTime.now();
    }
    @PreUpdate
    private void preUpdate(){
        this.fechaModificacion=LocalDateTime.now();
    }
}
