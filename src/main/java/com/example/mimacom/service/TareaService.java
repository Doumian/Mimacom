package com.example.mimacom.service;

import com.example.mimacom.dtos.TareaDto;
import com.example.mimacom.model.TareaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;


public interface TareaService {

    List<TareaDto> findByUsuario(Long idUsuario);

    List<TareaDto> findByEstado(Long idEstado);

    TareaDto save(TareaDto tareaDto);

    TareaDto find(Long id);

    List<TareaDto> findAll();

    void delete(Long id);

    void delete(TareaDto tareaDto);

    void deleteAll();

    long count();

    TareaDto cambiarEstado(Long idTarea, Long idEstado);

    TareaDto cambiarDescripcion(Long idTarea, String descripcion);
}