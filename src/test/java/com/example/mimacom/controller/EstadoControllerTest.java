package com.example.mimacom.controller;

import com.example.mimacom.dtos.EstadoDto;
import com.example.mimacom.service.EstadoService;
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

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EstadoController.class)
class EstadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EstadoService mockEstadoService;

    @Test
    void testSave() throws Exception {
        // Setup

        // Configure EstadoService.save(...).
        final EstadoDto estadoDto = new EstadoDto();
        estadoDto.setIdEstado(0L);
        estadoDto.setDescripcion("descripcion");
        when(mockEstadoService.save(new EstadoDto())).thenReturn(estadoDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/estado")
                .content("{\"idEstado\":0,\"descripcion\":\"descripcion\"}").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("");
    }

    @Test
    void testGetById() throws Exception {
        // Setup

        // Configure EstadoService.find(...).
        final EstadoDto estadoDto = new EstadoDto();
        estadoDto.setIdEstado(0L);
        estadoDto.setDescripcion("descripcion");
        when(mockEstadoService.find(0L)).thenReturn(estadoDto);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/estado/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("{\"idEstado\":0,\"descripcion\":\"descripcion\"}");
    }

    @Test
    void testGetAll() throws Exception {
        // Setup

        // Configure EstadoService.findAll(...).
        final EstadoDto estadoDto = new EstadoDto();
        estadoDto.setIdEstado(0L);
        estadoDto.setDescripcion("descripcion");
        final List<EstadoDto> estadoDtos = List.of(estadoDto);
        when(mockEstadoService.findAll()).thenReturn(estadoDtos);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/estado")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("[{\"idEstado\":0,\"descripcion\":\"descripcion\"}]");
    }

    @Test
    void testGetAll_EstadoServiceReturnsNoItems() throws Exception {
        // Setup
        when(mockEstadoService.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/estado")
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
        final MockHttpServletResponse response = mockMvc.perform(delete("/estado/{id}", 0)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("");
        verify(mockEstadoService).delete(0L);
    }

    @Test
    void testDeleteAll() throws Exception {
        // Setup

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/estado")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("");
        verify(mockEstadoService).deleteAll();
    }

    @Test
    void testCount() throws Exception {
        // Setup
        when(mockEstadoService.count()).thenReturn(0L);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/estado/count")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("0");
    }
}
