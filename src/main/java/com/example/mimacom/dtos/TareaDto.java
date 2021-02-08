package com.example.mimacom.dtos;

import com.example.mimacom.model.EstadoEntity;
import com.example.mimacom.model.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Validated
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
public class TareaDto extends ApplicationDto {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "tareaId")
    private Long tareaId;

    @JsonProperty(value = "descripcion")
    private String descripcion;

    @JsonProperty(value = "usuario")
    private UsuarioEntity usuario;

    @JsonProperty(value = "estado")
    private EstadoEntity estado;

    @JsonProperty(value = "fechaDeCreacion")
    private Date fechaDeCreacion;

}