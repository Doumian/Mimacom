package com.example.mimacom.controller;

import com.example.mimacom.dtos.TareaDto;
import com.example.mimacom.dtos.UsuarioDto;
import com.example.mimacom.model.EstadoEntity;
import com.example.mimacom.model.TareaEntity;
import com.example.mimacom.model.UsuarioEntity;
import com.example.mimacom.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService mockUsuarioService;

    private final String RESULT = "{\"usuarioId\":0,\"nombre\":\"nombre\",\"apellidos\":\"apellidos\",\"tareas\":[{\"tareaId\":0,\"descripcion\":\"descripcion\",\"usuario\":{\"usuarioId\":0,\"nombre\":\"nombre\",\"apellidos\":\"apellidos\"},\"estado\":{\"estadoId\":0,\"descripcion\":\"descripcion\"},\"fechaDeCreacion\":\"2018-12-31T23:00:00.000+00:00\"}]}";

    @Test
    void testSave() throws Exception {
        // Setup

        // Configure UsuarioService.save(...).
        final UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setUsuarioId(0L);
        usuarioDto.setNombre("nombre");
        usuarioDto.setApellidos("apellidos");
        final TareaDto tareaDto = new TareaDto();
        tareaDto.setTareaId(0L);
        tareaDto.setDescripcion("descripcion");
        final UsuarioEntity usuario = new UsuarioEntity();
        usuario.setUsuarioId(0L);
        usuario.setNombre("nombre");
        usuario.setApellidos("apellidos");
        final TareaEntity tareaEntity = new TareaEntity();
        tareaEntity.setTareaId(0L);
        tareaEntity.setDescripcion("descripcion");
        tareaEntity.setUsuario(new UsuarioEntity());
        final EstadoEntity estado = new EstadoEntity();
        estado.setEstadoId(0L);
        estado.setDescripcion("descripcion");
        tareaEntity.setEstado(estado);
        tareaEntity.setFechaDeCreacion(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        usuario.setTareas(List.of(tareaEntity));
        tareaDto.setUsuario(usuario);
        final EstadoEntity estado1 = new EstadoEntity();
        estado1.setEstadoId(0L);
        estado1.setDescripcion("descripcion");
        tareaDto.setEstado(estado1);
        tareaDto.setFechaDeCreacion(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        usuarioDto.setTareas(List.of(tareaDto));
        when(mockUsuarioService.save(new UsuarioDto())).thenReturn(usuarioDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/usuario")
                .content(RESULT).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("");
    }

    @Test
    void testGetById() throws Exception {
        // Setup

        // Configure UsuarioService.find(...).
        final UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setUsuarioId(0L);
        usuarioDto.setNombre("nombre");
        usuarioDto.setApellidos("apellidos");
        final TareaDto tareaDto = new TareaDto();
        tareaDto.setTareaId(0L);
        tareaDto.setDescripcion("descripcion");
        final UsuarioEntity usuario = new UsuarioEntity();
        usuario.setUsuarioId(0L);
        usuario.setNombre("nombre");
        usuario.setApellidos("apellidos");
        final TareaEntity tareaEntity = new TareaEntity();
        tareaEntity.setTareaId(0L);
        tareaEntity.setDescripcion("descripcion");
        tareaEntity.setUsuario(new UsuarioEntity());
        final EstadoEntity estado = new EstadoEntity();
        estado.setEstadoId(0L);
        estado.setDescripcion("descripcion");
        tareaEntity.setEstado(estado);
        tareaEntity.setFechaDeCreacion(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        usuario.setTareas(List.of(tareaEntity));
        tareaDto.setUsuario(usuario);
        final EstadoEntity estado1 = new EstadoEntity();
        estado1.setEstadoId(0L);
        estado1.setDescripcion("descripcion");
        tareaDto.setEstado(estado1);
        tareaDto.setFechaDeCreacion(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        usuarioDto.setTareas(List.of(tareaDto));
        when(mockUsuarioService.find(0L)).thenReturn(usuarioDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/usuario/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"usuarioId\":0,\"nombre\":\"nombre\",\"apellidos\":\"apellidos\",\"tareas\":[{\"tareaId\":0,\"descripcion\":\"descripcion\",\"usuario\":{\"usuarioId\":0,\"nombre\":\"nombre\",\"apellidos\":\"apellidos\"},\"estado\":{\"estadoId\":0,\"descripcion\":\"descripcion\"},\"fechaDeCreacion\":\"2018-12-31T23:00:00.000+00:00\"}]}");
    }

    @Test
    void testGetAll() throws Exception {
        // Setup

        // Configure UsuarioService.findAll(...).
        final UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setUsuarioId(0L);
        usuarioDto.setNombre("nombre");
        usuarioDto.setApellidos("apellidos");
        final TareaDto tareaDto = new TareaDto();
        tareaDto.setTareaId(0L);
        tareaDto.setDescripcion("descripcion");
        final UsuarioEntity usuario = new UsuarioEntity();
        usuario.setUsuarioId(0L);
        usuario.setNombre("nombre");
        usuario.setApellidos("apellidos");
        final TareaEntity tareaEntity = new TareaEntity();
        tareaEntity.setTareaId(0L);
        tareaEntity.setDescripcion("descripcion");
        tareaEntity.setUsuario(new UsuarioEntity());
        final EstadoEntity estado = new EstadoEntity();
        estado.setEstadoId(0L);
        estado.setDescripcion("descripcion");
        tareaEntity.setEstado(estado);
        tareaEntity.setFechaDeCreacion(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        usuario.setTareas(List.of(tareaEntity));
        tareaDto.setUsuario(usuario);
        final EstadoEntity estado1 = new EstadoEntity();
        estado1.setEstadoId(0L);
        estado1.setDescripcion("descripcion");
        tareaDto.setEstado(estado1);
        tareaDto.setFechaDeCreacion(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        usuarioDto.setTareas(List.of(tareaDto));
        final List<UsuarioDto> usuarioDtos = List.of(usuarioDto);
        when(mockUsuarioService.findAll()).thenReturn(usuarioDtos);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/usuario")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[{\"usuarioId\":0,\"nombre\":\"nombre\",\"apellidos\":\"apellidos\",\"tareas\":[{\"tareaId\":0,\"descripcion\":\"descripcion\",\"usuario\":{\"usuarioId\":0,\"nombre\":\"nombre\",\"apellidos\":\"apellidos\"},\"estado\":{\"estadoId\":0,\"descripcion\":\"descripcion\"},\"fechaDeCreacion\":\"2018-12-31T23:00:00.000+00:00\"}]}]");
    }

    @Test
    void testGetAll_UsuarioServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockUsuarioService.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/usuario")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testDeleteById() throws Exception {
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/usuario/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("");
        verify(mockUsuarioService).delete(0L);
    }

    @Test
    void testDeleteAll() throws Exception {
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/usuario")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("");
        verify(mockUsuarioService).deleteAll();
    }

    @Test
    void testCount() throws Exception {
        // Setup
        when(mockUsuarioService.count()).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/usuario/count")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("0");
    }
}
