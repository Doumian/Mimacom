package com.example.mimacom.repository;

import com.example.mimacom.model.TareaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareaRepository extends JpaRepository<TareaEntity, Long> {

    List<TareaEntity> findByEstado_EstadoId(Long idEstado);

}