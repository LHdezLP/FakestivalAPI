package com.festival.tickets.entity.service;

import com.festival.tickets.controllers.UsuarioController;
import com.festival.tickets.entity.models.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Collections;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UsuarioControllerUnitTest {

    private MockMvc mockMvc;

    @Mock
    private IUsuarioService usuarioService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    // Prueba para GET /usuario
    @Test
    void testGetAllUsuarios() throws Exception {
        // Configuración del mock
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan");
        when(usuarioService.getAll()).thenReturn(Collections.singletonList(usuario));

        // Ejecución y verificación
        mockMvc.perform(get("/usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Juan"));
    }

    // Prueba para GET /usuario/{id}
    @Test
    void testGetUsuarioById() throws Exception {
        // Configuración del mock
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan");
        when(usuarioService.get(1L)).thenReturn(usuario);

        // Ejecución y verificación
        mockMvc.perform(get("/usuario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    // Prueba para POST /usuario
    @Test
    void testCreateUsuario() throws Exception {
        // Configuración del mock para PasswordEncoder
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        // Configuración del mock para UsuarioService
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan");
        usuario.setEmail("juan@example.com");
        usuario.setContrasena("encodedPassword"); // Contraseña codificada
        when(usuarioService.post(any(Usuario.class))).thenReturn(usuario);

        // Ejecución y verificación
        mockMvc.perform(post("/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"Juan\", \"email\": \"juan@example.com\", \"contrasena\": \"password\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan"));


        verify(passwordEncoder, times(1)).encode("password");
    }

    // Prueba para PUT /usuario/{id}
    @Test
    void testUpdateUsuario() throws Exception {

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan Actualizado");

        // Ejecución y verificación
        mockMvc.perform(put("/usuario/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"Juan Actualizado\"}"))
                .andExpect(status().isOk());

        verify(usuarioService, times(1)).put(any(Usuario.class), eq(1L));
    }

    // Prueba para DELETE /usuario/{id}
    @Test
    void testDeleteUsuario() throws Exception {
        // Configuración del mock
        when(usuarioService.get(1L)).thenReturn(new Usuario());

        // Ejecución y verificación
        mockMvc.perform(delete("/usuario/1"))
                .andExpect(status().isOk());

        verify(usuarioService, times(1)).delete(1L);
    }
}