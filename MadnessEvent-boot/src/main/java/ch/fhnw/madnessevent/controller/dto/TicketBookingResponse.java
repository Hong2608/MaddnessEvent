package ch.fhnw.madnessevent.controller.dto;

import java.time.LocalDateTime;

public record TicketBookingResponse(
        Long bookingId,
        Long eventId,
        int quantity,
        String purchaserName,
        int remainingTickets,
        LocalDateTime bookedAt
) {
}
