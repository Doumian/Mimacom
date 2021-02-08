package com.example.mimacom.service;

import com.example.mimacom.dtos.EstadoDto;

import java.util.List;


public interface EstadoService {

    EstadoDto save(EstadoDto estadoDto);

    EstadoDto find(Long id) ;

    List<EstadoDto> findAll();

    void delete(Long id);

    void delete(EstadoDto estadoDto);

    void deleteAll();

    long count();

}