package com.banco.backend.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Direccion implements Serializable {

    private String calle;
    private String numero;
    private String codigo_postal;
    private String piso;
    private String localidad;

}
