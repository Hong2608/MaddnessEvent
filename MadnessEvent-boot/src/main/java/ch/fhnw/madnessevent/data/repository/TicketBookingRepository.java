package ch.fhnw.madnessevent.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.madnessevent.data.domain.TicketBooking;

public interface TicketBookingRepository extends JpaRepository<TicketBooking, Long> {
}
