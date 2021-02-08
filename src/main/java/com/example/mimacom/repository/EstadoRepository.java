package com.example.mimacom.repository;

import com.example.mimacom.model.EstadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<EstadoEntity, Long> {
}