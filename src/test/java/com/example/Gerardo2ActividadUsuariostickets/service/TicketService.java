package com.example.Gerardo2ActividadUsuariostickets.service;

import com.example.Gerardo2ActividadUsuariostickets.dto.CambiarPrioridadDTO;
import com.example.Gerardo2ActividadUsuariostickets.dto.CrearTicketDTO;
import com.example.Gerardo2ActividadUsuariostickets.exception.BadRequestException;
import com.example.Gerardo2ActividadUsuariostickets.exception.ResourceNotFoundException;
import com.example.Gerardo2ActividadUsuariostickets.model.EstadoTicket;
import com.example.Gerardo2ActividadUsuariostickets.model.Ticket;
import com.example.Gerardo2ActividadUsuariostickets.model.Usuario;
import com.example.Gerardo2ActividadUsuariostickets.repository.TicketRepository;
import com.example.Gerardo2ActividadUsuariostickets.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;

    public TicketService(TicketRepository ticketRepository, UsuarioRepository usuarioRepository) {
        this.ticketRepository = ticketRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Ticket crearTicket(CrearTicketDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + dto.getUsuarioId()));

        if (!Boolean.TRUE.equals(usuario.getActivo())) {
            throw new BadRequestException("No se puede crear el ticket. El usuario está inactivo.");
        }

        Ticket ticket = new Ticket();
        ticket.setDescripcion(dto.getDescripcion());
        ticket.setEstado(EstadoTicket.ABIERTO);
        ticket.setPrioridad(dto.getPrioridad());
        ticket.setUsuario(usuario);

        return ticketRepository.save(ticket);
    }

    public Ticket actualizarEstado(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket no encontrado con id: " + ticketId));

        if (ticket.getEstado() == EstadoTicket.CERRADO) {
            throw new BadRequestException("El ticket ya está CERRADO y no se puede modificar.");
        }

        if (ticket.getEstado() == EstadoTicket.ABIERTO) {
            ticket.setEstado(EstadoTicket.EN_PROCESO);
        } else if (ticket.getEstado() == EstadoTicket.EN_PROCESO) {
            ticket.setEstado(EstadoTicket.CERRADO);
        }

        return ticketRepository.save(ticket);
    }

    public Ticket reasignarPrioridad(Long ticketId, CambiarPrioridadDTO dto) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket no encontrado con id: " + ticketId));

        if (ticket.getEstado() == EstadoTicket.CERRADO) {
            throw new BadRequestException("No se puede cambiar la prioridad de un ticket CERRADO.");
        }

        ticket.setPrioridad(dto.getNuevaPrioridad());
        return ticketRepository.save(ticket);
    }

    public List<Ticket> obtenerTicketsUsuario(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResourceNotFoundException("Usuario no encontrado con id: " + usuarioId);
        }
        return ticketRepository.findByUsuarioId(usuarioId);
    }
}