package com.example.Gerardo2ActividadUsuariostickets.dto;

import com.example.Gerardo2ActividadUsuariostickets.model.Prioridad;
import jakarta.validation.constraints.NotNull;

public class CambiarPrioridadDTO {

    @NotNull(message = "La nueva prioridad es obligatoria")
    private Prioridad nuevaPrioridad;

    public Prioridad getNuevaPrioridad() { return nuevaPrioridad; }
    public void setNuevaPrioridad(Prioridad nuevaPrioridad) { this.nuevaPrioridad = nuevaPrioridad; }
}