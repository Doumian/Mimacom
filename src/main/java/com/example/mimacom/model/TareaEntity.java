package com.example.mimacom.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name="Tarea")
public class TareaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tareaId;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name="usuarioId", nullable=false)
    private UsuarioEntity usuario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "estadoId")
    private EstadoEntity estado;

    @CreationTimestamp
    private Date fechaDeCreacion;


}
