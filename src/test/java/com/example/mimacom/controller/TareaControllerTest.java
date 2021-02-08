package com.example.mimacom.controller;

import com.example.mimacom.dtos.TareaDto;
import com.example.mimacom.model.EstadoEntity;
import com.example.mimacom.model.TareaEntity;
import com.example.mimacom.model.UsuarioEntity;
import com.example.mimacom.service.TareaService;
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
@WebMvcTest(TareaController.class)
class TareaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TareaService mockTareaService;

    private String RESULT = "[{\"tareaId\":0,\"descripcion\":\"descripcion\",\"usuario\":{\"usuarioId\":0,\"nombre\":\"nombre\",\"apellidos\":\"apellidos\"},\"estado\":{\"estadoId\":0,\"descripcion\":\"descripcion\"},\"fechaDeCreacion\":\"2018-12-31T23:00:00.000+00:00\"}]";
    private String RESULT_NO_BRACKET = "{\"tareaId\":0,\"descripcion\":\"descripcion\",\"usuario\":{\"usuarioId\":0,\"nombre\":\"nombre\",\"apellidos\":\"apellidos\"},\"estado\":{\"estadoId\":0,\"descripcion\":\"descripcion\"},\"fechaDeCreacion\":\"2018-12-31T23:00:00.000+00:00\"}";


    @Test
    void testGetByUsuario() throws Exception {
        // Setup

        // Configure TareaService.findByUsuario(...).
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
        final List<TareaDto> tareaDtoList = List.of(tareaDto);
        when(mockTareaService.findByUsuario(0L)).thenReturn(tareaDtoList);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/tarea/usuario/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(RESULT);
    }

    @Test
    void testGetByUsuario_TareaServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockTareaService.findByUsuario(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/tarea/usuario/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetByEstado() throws Exception {
        // Setup

        // Configure TareaService.findByEstado(...).
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
        final List<TareaDto> tareaDtoList = List.of(tareaDto);
        when(mockTareaService.findByEstado(0L)).thenReturn(tareaDtoList);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/tarea/estado/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(RESULT);
    }

    @Test
    void testGetByEstado_TareaServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockTareaService.findByEstado(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/tarea/estado/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testCambiarEstado() throws Exception {
        // Setup

        // Configure TareaService.cambiarEstado(...).
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
        when(mockTareaService.cambiarEstado(0L, 0L)).thenReturn(tareaDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/tarea/{idTarea}/estado/{idEstado}", 0, 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(RESULT_NO_BRACKET);
    }

    @Test
    void testCambiarDescripcion() throws Exception {
        // Setup

        // Configure TareaService.cambiarDescripcion(...).
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

        tareaDto.setFechaDeCreacion(new GregorianCalendar(2019, Calendar.JANUARY, 1).getTime());
        when(mockTareaService.cambiarDescripcion(0L, "descripcion")).thenReturn(tareaDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/tarea/{idTarea}/descripcion/{descripcion}", 0, "descripcion2")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("");
    }

    @Test
    void testSave() throws Exception {
        // Setup

        // Configure TareaService.save(...).
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
        when(mockTareaService.save(new TareaDto())).thenReturn(tareaDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/tarea")
                .content(RESULT_NO_BRACKET).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("");
    }

    @Test
    void testGetById() throws Exception {
        // Setup

        // Configure TareaService.find(...).
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
        when(mockTareaService.find(0L)).thenReturn(tareaDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/tarea/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(RESULT_NO_BRACKET);
    }

    @Test
    void testGetAll() throws Exception {
        // Setup

        // Configure TareaService.findAll(...).
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
        final List<TareaDto> tareaDtoList = List.of(tareaDto);
        when(mockTareaService.findAll()).thenReturn(tareaDtoList);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/tarea")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(RESULT);
    }

    @Test
    void testGetAll_TareaServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockTareaService.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/tarea")
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
        final MockHttpServletResponse response = mockMvc.perform(delete("/tarea/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("");
        verify(mockTareaService).delete(0L);
    }

    @Test
    void testDeleteAll() throws Exception {
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/tarea")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("");
        verify(mockTareaService).deleteAll();
    }

    @Test
    void testCount() throws Exception {
        // Setup
        when(mockTareaService.count()).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/tarea/count")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("0");
    }
}
