package com.example.Gerardo2ActividadUsuariostickets.service;

import com.example.GerardoActividadUsuariostickets.dto.UsuarioRequestDTO;
import com.example.GerardoActividadUsuariostickets.exception.ResourceNotFoundException;
import com.example.GerardoActividadUsuariostickets.model.Usuario;
import com.example.GerardoActividadUsuariostickets.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario crearUsuario(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario(dto.getNombre(), true);
        return usuarioRepository.save(usuario);
    }

    public Usuario desactivarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        usuario.setActivo(false);
        return usuarioRepository.save(usuario);
    }
}