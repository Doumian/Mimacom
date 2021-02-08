package com.example.mimacom.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
public class EstadoDto extends ApplicationDto {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "idEstado")
    private Long idEstado;

    @JsonProperty(value = "descripcion")
    private String descripcion;
}