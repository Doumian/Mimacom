package com.example.mimacom.service.impl;

import com.example.mimacom.dtos.EstadoDto;
import com.example.mimacom.dtos.TareaDto;
import com.example.mimacom.model.EstadoEntity;
import com.example.mimacom.model.TareaEntity;
import com.example.mimacom.model.UsuarioEntity;
import com.example.mimacom.repository.EstadoRepository;
import com.example.mimacom.repository.TareaRepository;
import com.example.mimacom.repository.UsuarioRepository;
import com.example.mimacom.service.TareaService;
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
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EstadoRepository estadoRepository;


    private ModelMapper modelMapper = new ModelMapper();

    private final String TAREA_NO_ENCONTRADA = "No hay ninguna Tarea con el Id: ";
    private final String ESTADO_NO_ENCONTRADO = "No hay ningún Estado con el Id: ";
    private final String USUARIO_NO_ENCONTRADO = "No hay ningún Usuario con el Id: ";

    @Override
    public List<TareaDto> findByUsuario(Long idUsuario) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(idUsuario);
        if(usuarioEntity.isPresent()){
            return usuarioEntity.get().getTareas().stream()
                    .map(source -> modelMapper.map(source, TareaDto.class))
                    .collect(Collectors.toList());
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No hay ninguna tarea asociada con el Id de Usuario: ".concat(String.valueOf(idUsuario)));

    }

    @Override
    public List<TareaDto> findByEstado(Long idEstado) {
       List<TareaEntity> tareaDtoList =  tareaRepository.findByEstado_EstadoId(idEstado);
       if(!tareaDtoList.isEmpty()){
           return tareaDtoList.stream()
                   .map(source -> modelMapper.map(source, TareaDto.class))
                   .collect(Collectors.toList());
       } else throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No hay ninguna tarea asociada con el Id de Estado: ".concat(String.valueOf(idEstado)));
    }

    @Override
    public TareaDto cambiarEstado(Long idTarea, Long idEstado) {
        Optional<TareaEntity> tarea = tareaRepository.findById(idTarea);
        Optional<EstadoEntity> nuevoEstado = estadoRepository.findById(idEstado);

        if(tarea.isPresent()){
            if(nuevoEstado.isPresent()) {
                TareaEntity tareaEntity = tarea.get();
                tareaEntity.setEstado(nuevoEstado.get());
                return modelMapper.map(tareaRepository.save(tareaEntity), TareaDto.class);
            } else throw new ResponseStatusException(HttpStatus.NOT_FOUND,ESTADO_NO_ENCONTRADO.concat(String.valueOf(idEstado)));
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND,TAREA_NO_ENCONTRADA.concat(String.valueOf(idTarea)));
    }

    @Override
    public TareaDto cambiarDescripcion(Long idTarea, String descripcion) {
        Optional<TareaEntity> tarea = tareaRepository.findById(idTarea);
        if(tarea.isPresent()){
                TareaEntity tareaEntity = tarea.get();
                tareaEntity.setDescripcion(descripcion);
                return modelMapper.map(tareaRepository.save(tareaEntity), TareaDto.class);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND,TAREA_NO_ENCONTRADA.concat(String.valueOf(idTarea)));
    }

    @Override
    public TareaDto save(TareaDto tareaDto) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(tareaDto.getUsuario().getUsuarioId());
        Optional<EstadoEntity> estadoPendiente = estadoRepository.findById(1L);

        TareaEntity tareaEntity = modelMapper.map(tareaDto, TareaEntity.class);

        if(usuarioEntity.isPresent()) {
            tareaEntity.setEstado(estadoPendiente.get());
            return modelMapper.map(tareaRepository.save(tareaEntity), TareaDto.class);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND,USUARIO_NO_ENCONTRADO.concat(String.valueOf(tareaDto.getUsuario().getUsuarioId())));
    }

    @Override
    public TareaDto find(Long id) {
        Optional<TareaEntity> tareaEntity = tareaRepository.findById(id);
        if(tareaEntity.isPresent()){
            return modelMapper.map(tareaEntity.get(), TareaDto.class);
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND,TAREA_NO_ENCONTRADA.concat(String.valueOf(id)));
    }

    @Override
    public List<TareaDto> findAll() {
        return tareaRepository.findAll()
                .stream()
                .map(source -> modelMapper.map(source, TareaDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        tareaRepository.deleteById(id);
    }

    @Override
    public void delete(TareaDto tareaDto) {
        TareaEntity tareaEntity = modelMapper.map(tareaDto, TareaEntity.class);
        tareaRepository.delete(tareaEntity);
    }

    @Override
    public void deleteAll() {
        tareaRepository.deleteAll();
    }

    @Override
    public long count() {
        return tareaRepository.count();
    }



}