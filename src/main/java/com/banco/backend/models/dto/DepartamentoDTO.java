package com.banco.backend.models.dto;

import com.banco.backend.models.entities.Ciudad;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoDTO {

    private Integer id;
    private String nombre;

}
