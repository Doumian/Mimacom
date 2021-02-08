package com.example.mimacom.controller;

import com.example.mimacom.dtos.EstadoDto;
import com.example.mimacom.service.EstadoService;
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

import javax.validation.Valid;
import java.util.List;

@Api(value = "Estado", tags = { "Estado Api" })
@RestController
@RequestMapping(value = "/estado", produces = {"application/json"})
@Validated
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @ApiOperation(value = "Creación de un nuevo Estado", tags = {"Estado Api"}, response = EstadoDto.class, notes="", nickname = "createEstado")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Estado creado", response = EstadoDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @PostMapping
    public ResponseEntity<EstadoDto> save(@Valid @RequestBody EstadoDto estadoDto){
        return new ResponseEntity<>(estadoService.save(estadoDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Búsqueda de estado por Id", tags = {"Estado Api"}, response = EstadoDto.class, notes="", nickname = "getById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Estado", response = EstadoDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping("/{id}")
    public ResponseEntity<EstadoDto> getById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(estadoService.find(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Lista de todos los estados", tags = {"Estado Api"}, response = EstadoDto.class, notes="", nickname = "getAll")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de Estados", response = EstadoDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping
    public ResponseEntity<List<EstadoDto>> getAll(){
        return new ResponseEntity<>(estadoService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Borrar estado por Id", tags = {"Estado Api"}, response = EstadoDto.class, notes="", nickname = "deleteById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Estado borrado", response = EstadoDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(value = "id") Long id){
        estadoService.delete(id);
    }

    @ApiOperation(value = "Borrar todos los estados", tags = {"Estado Api"}, response = EstadoDto.class, notes="", nickname = "deleteAll")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Estados borrados", response = EstadoDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @DeleteMapping("")
    public void deleteAll(){
        estadoService.deleteAll();
    }

    @ApiOperation(value = "Contar estados", tags = {"Estado Api"}, response = EstadoDto.class, notes="", nickname = "count")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cantidad de Estados", response = EstadoDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping("/count")
    public long count(){
        return estadoService.count();
    }
}