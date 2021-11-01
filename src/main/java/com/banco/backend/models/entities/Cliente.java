package com.banco.backend.models.entities;

import com.banco.backend.models.enums.TipoCliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "persona_id")
public class Cliente extends Persona implements Serializable {
    @Column(nullable = false,length = 100)
    private TipoCliente tipo_cliente;
    @OneToMany(
            mappedBy = "cliente",
            fetch = FetchType.LAZY
    )
    private Set<Cuenta> cuentas;

}
