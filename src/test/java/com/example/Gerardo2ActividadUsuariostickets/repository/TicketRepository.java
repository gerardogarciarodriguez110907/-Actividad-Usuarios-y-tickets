package com.example.Gerardo2ActividadUsuariostickets.repository;

import com.example.Gerardo2ActividadUsuariostickets.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUsuarioId(Long usuarioId);
}