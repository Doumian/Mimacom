package com.example.mimacom.service.impl;

import com.example.mimacom.dtos.UsuarioDto;
import com.example.mimacom.model.UsuarioEntity;
import com.example.mimacom.repository.UsuarioRepository;
import com.example.mimacom.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public UsuarioDto save(UsuarioDto usuarioDto) {
        UsuarioEntity usuarioEntity = modelMapper.map(usuarioDto,UsuarioEntity.class);
        return modelMapper.map(usuarioRepository.save(usuarioEntity), UsuarioDto.class);
    }

    @Override
    public UsuarioDto find(Long id) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(id);
        if(usuarioEntity.isPresent()){
            return modelMapper.map(usuarioEntity.get(), UsuarioDto.class);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No hay ningún Usuario con el Id: ".concat(String.valueOf(id)));
    }

    @Override
    public List<UsuarioDto> findAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(source -> modelMapper.map(source, UsuarioDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
    usuarioRepository.deleteById(id);
    }

    @Override
    public void delete(UsuarioDto usuarioDto) {
        UsuarioEntity usuarioEntity = modelMapper.map(usuarioDto,UsuarioEntity.class);
        usuarioRepository.delete(usuarioEntity);
    }

    @Override
    public void deleteAll() {
        usuarioRepository.deleteAll();
    }

    @Override
    public long count() {
        return usuarioRepository.count();
    }

}