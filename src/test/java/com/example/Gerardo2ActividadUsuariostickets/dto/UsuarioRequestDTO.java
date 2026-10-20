package com.example.Gerardo2ActividadUsuariostickets.dto;

import jakarta.validation.constraints.NotBlank;

public class UsuarioRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}