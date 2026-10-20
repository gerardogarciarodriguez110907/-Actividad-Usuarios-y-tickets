package com.example.Gerardo2ActividadUsuariostickets.controller;

import com.example.Gerardo2ActividadUsuariostickets.dto.CambiarPrioridadDTO;
import com.example.Gerardo2ActividadUsuariostickets.dto.CrearTicketDTO;
import com.example.Gerardo2ActividadUsuariostickets.model.Ticket;
import com.example.Gerardo2ActividadUsuariostickets.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> crearTicket(@Valid @RequestBody CrearTicketDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.crearTicket(dto));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Ticket> actualizarEstado(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.actualizarEstado(id));
    }

    @PatchMapping("/{id}/prioridad")
    public ResponseEntity<Ticket> reasignarPrioridad(@PathVariable Long id, @Valid @RequestBody CambiarPrioridadDTO dto) {
        return ResponseEntity.ok(ticketService.reasignarPrioridad(id, dto));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Ticket>> obtenerTicketsUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(ticketService.obtenerTicketsUsuario(usuarioId));
    }
}