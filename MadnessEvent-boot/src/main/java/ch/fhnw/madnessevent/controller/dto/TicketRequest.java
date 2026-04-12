package ch.fhnw.madnessevent.controller.dto;

public record TicketRequest(
        Long eventId,
        Integer quantity,
        String purchaserName
) {
}
