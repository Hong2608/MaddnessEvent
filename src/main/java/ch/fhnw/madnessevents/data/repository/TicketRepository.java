package ch.fhnw.madnessevents.data.repository;

import ch.fhnw.madnessevents.data.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}