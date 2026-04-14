package ch.fhnw.madnessevent.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ch.fhnw.madnessevent.data.domain.TicketBooking;

@Repository
public interface TicketBookingRepository extends JpaRepository<TicketBooking, Long> {

    // Get all bookings for a specific event (admin view / availability check)
    List<TicketBooking> findByEventId(Long eventId);

    // Look up a user's own bookings by name (shown after login on Tickets page)
    List<TicketBooking> findByPurchaserNameIgnoreCase(String purchaserName);

    // Sum of tickets already booked for an event (used in availability business rule)
    @Query("SELECT COALESCE(SUM(tb.quantity), 0) FROM TicketBooking tb WHERE tb.event.id = :eventId")
    int sumQuantityByEventId(@Param("eventId") Long eventId);
}
