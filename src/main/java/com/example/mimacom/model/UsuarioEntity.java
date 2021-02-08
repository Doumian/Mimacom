package com.example.mimacom.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="Usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;

    private String nombre;
    private String apellidos;

    @JsonIgnore
    @OneToMany(targetEntity=TareaEntity.class, mappedBy="usuario",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TareaEntity> tareas = new ArrayList<>();


}
