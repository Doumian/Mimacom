package com.example.mimacom.service;

import com.example.mimacom.dtos.UsuarioDto;
import com.example.mimacom.model.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;


public interface UsuarioService {

    UsuarioDto save(UsuarioDto usuarioDto);

    UsuarioDto find(Long id);

    List<UsuarioDto> findAll();

    void delete(Long id);

    void delete(UsuarioDto usuarioDto);

    void deleteAll();

    long count();

}