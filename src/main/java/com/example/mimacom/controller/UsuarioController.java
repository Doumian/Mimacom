package com.example.mimacom.controller;

import com.example.mimacom.dtos.UsuarioDto;
import com.example.mimacom.service.UsuarioService;
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

@Api(value = "Usuario", tags = { "Usuario Api" })
@RestController
@RequestMapping(value = "/usuario", produces = {"application/json"})
@Validated
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @ApiOperation(value = "Creación de un nuevo Usuario", tags = {"Usuario Api"}, response = UsuarioDto.class, notes="", nickname = "createUsuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario creado", response = UsuarioDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @PostMapping
    public ResponseEntity<UsuarioDto> save(@RequestBody UsuarioDto usuarioDto){
        return new ResponseEntity<>(usuarioService.save(usuarioDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Búsqueda de Usuario por Id", tags = {"Usuario Api"}, response = UsuarioDto.class, notes="", nickname = "createUsuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario", response = UsuarioDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> getById(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(usuarioService.find(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Lista de todos los Usuarios", tags = {"Usuario Api"}, response = UsuarioDto.class, notes="", nickname = "getAll")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista de Usuarios", response = UsuarioDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> getAll(){
        return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Borrar Usuario por Id", tags = {"Usuario Api"}, response = UsuarioDto.class, notes="", nickname = "deleteById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuario borrado", response = UsuarioDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(value = "id") Long id){
        usuarioService.delete(id);
    }

    @ApiOperation(value = "Borrar todos los Usuarios", tags = {"Usuario Api"}, response = UsuarioDto.class, notes="", nickname = "deleteAll")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Usuarios borrados", response = UsuarioDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @DeleteMapping
    public void deleteAll(){
        usuarioService.deleteAll();
    }

    @ApiOperation(value = "Contar Usuarios", tags = {"Usuario Api"}, response = UsuarioDto.class, notes="", nickname = "count")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cantidad de Usuarios", response = UsuarioDto.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found")})
    @GetMapping("/count")
    public long count(){
        return usuarioService.count();
    }
}