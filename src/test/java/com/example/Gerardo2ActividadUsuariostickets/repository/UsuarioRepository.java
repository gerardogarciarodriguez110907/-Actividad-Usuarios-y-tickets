package com.example.Gerardo2ActividadUsuariostickets.repository;

import com.example.Gerardo2ActividadUsuariostickets.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}