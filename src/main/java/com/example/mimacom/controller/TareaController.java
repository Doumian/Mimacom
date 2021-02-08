package com.example.mimacom.controller;

import com.example.mimacom.dtos.TareaDto;
import com.example.mimacom.service.TareaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Tarea", tags = { "Tarea Api" })
@RestController
@RequestMapping(value = "/tarea", produces = {"application/json"})
@Validated
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @ApiOperation(value = "Búsqueda de Tarea por Usuario", tags = {"Tarea Api"}, response = TareaDto.class, notes="", nickname = "getByUsuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tarea por Usuario", response = TareaDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<TareaDto>> getByUsuario(@PathVariable(value = "id") Long idUsuario){
        return new ResponseEntity<>(tareaService.findByUsuario(idUsuario), HttpStatus.OK);
    }

    @ApiOperation(value = "Búsqueda de Tarea por Estado", tags = {"Tarea Api"}, response = TareaDto.class, notes="", nickname = "getByEstado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tarea por Estado", response = TareaDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping("/estado/{id}")
    public ResponseEntity<List<TareaDto>> getByEstado(@PathVariable(value = "id") Long idEstado){
        return new ResponseEntity<>(tareaService.findByEstado(idEstado), HttpStatus.OK);
    }

    @ApiOperation(value = "Modificar Estado de tarea", tags = {"Tarea Api"}, response = TareaDto.class, notes="", nickname = "cambiarEstado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Estado cambiado", response = TareaDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @PutMapping("/{idTarea}/estado/{idEstado}")
    public ResponseEntity<TareaDto> cambiarEstado(@PathVariable(value = "idTarea") Long idTarea, @PathVariable(value = "idEstado") Long idEstado){
        return new ResponseEntity<>(tareaService.cambiarEstado(idTarea, idEstado), HttpStatus.OK);
    }

    @ApiOperation(value = "Modificar Descripcion de tarea", tags = {"Tarea Api"}, response = TareaDto.class, notes="", nickname = "cambiarDescripcion")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Estado cambiado", response = TareaDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @PutMapping("/{idTarea}/descripcion/{descripcion}")
    public ResponseEntity<TareaDto> cambiarDescripcion(@PathVariable(value = "idTarea") Long idTarea, @PathVariable(value = "descripcion") String descripcion){
        return new ResponseEntity<>(tareaService.cambiarDescripcion(idTarea, descripcion), HttpStatus.OK);
    }


    @ApiOperation(value = "Creación de un nueva Tarea", tags = {"Tarea Api"}, response = TareaDto.class, notes="", nickname = "createTarea")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tarea creada", response = TareaDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @PostMapping
    public ResponseEntity<TareaDto> save(@RequestBody TareaDto tareaDto){
        return new ResponseEntity<>(tareaService.save(tareaDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Búsqueda de Tarea por Id", tags = {"Tarea Api"}, response = TareaDto.class, notes="", nickname = "getById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tarea", response = TareaDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping("/{id}")
    public ResponseEntity<TareaDto> getById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(tareaService.find(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Lista de todas las Tareas", tags = {"Tarea Api"}, response = TareaDto.class, notes="", nickname = "getAll")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de Tareas", response = TareaDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping
    public ResponseEntity<List<TareaDto>> getAll(){
        return new ResponseEntity<>(tareaService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Borrar lista por Id", tags = {"Tarea Api"}, response = TareaDto.class, notes="", nickname = "deleteById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tarea borrada", response = TareaDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(value = "id") Long id){
        tareaService.delete(id);
    }

    @ApiOperation(value = "Borrar todas las tareas", tags = {"Tarea Api"}, response = TareaDto.class, notes="", nickname = "deleteAll")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tareas borradas", response = TareaDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @DeleteMapping
    public void deleteAll(){
        tareaService.deleteAll();
    }

    @ApiOperation(value = "Contar Tareas", tags = {"Tarea Api"}, response = TareaDto.class, notes="", nickname = "count")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cantidad de Tareas", response = TareaDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping("/count")
    public long count(){
        return tareaService.count();
    }
}