package com.example.mimacom.dtos;

import com.example.mimacom.model.EstadoEntity;
import com.example.mimacom.model.UsuarioEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

@Validated
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Data
public class UsuarioDto extends ApplicationDto {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "usuarioId")
    private Long usuarioId;

    @JsonProperty(value = "nombre")
    private String nombre;

    @JsonProperty(value = "apellidos")
    private String apellidos;

    @JsonProperty(value = "tareas")
    private List<TareaDto> tareas;


}