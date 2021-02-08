package com.example.mimacom.service.impl;

import com.example.mimacom.dtos.EstadoDto;
import com.example.mimacom.model.EstadoEntity;
import com.example.mimacom.repository.EstadoRepository;
import com.example.mimacom.service.EstadoService;
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
public class EstadoServiceImpl implements EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public EstadoDto save(EstadoDto estadoDto) {
        EstadoEntity estadoentity = modelMapper.map(estadoDto, EstadoEntity.class);
        return modelMapper.map(estadoRepository.save(estadoentity), EstadoDto.class);
    }

    @Override
    public EstadoDto find(Long id)  {
        Optional<EstadoEntity> estadoEntity = estadoRepository.findById(id);
        if(estadoEntity.isPresent()){
            return modelMapper.map(estadoEntity.get(), EstadoDto.class);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No hay ningún Estado con el Id: ".concat(String.valueOf(id)));

    }

    @Override
    public List<EstadoDto> findAll() {
        return estadoRepository.findAll()
                        .stream()
                        .map(source -> modelMapper.map(source, EstadoDto.class))
                        .collect(Collectors.toList());


    }

    @Override
    public void delete(Long id) {
        estadoRepository.deleteById(id);
    }

    @Override
    public void delete(EstadoDto estadoDto) {
        EstadoEntity estadoEntity = modelMapper.map(estadoDto,EstadoEntity.class);
        estadoRepository.delete(estadoEntity);
    }

    @Override
    public void deleteAll() {
        estadoRepository.deleteAll();
    }

    @Override
    public long count() {
        return estadoRepository.count();
    }

}