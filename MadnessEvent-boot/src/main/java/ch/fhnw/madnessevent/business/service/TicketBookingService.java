package ch.fhnw.madnessevent.business.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.fhnw.madnessevent.business.exception.BadRequestException;
import ch.fhnw.madnessevent.controller.dto.TicketBookingResponse;
import ch.fhnw.madnessevent.controller.dto.TicketRequest;
import ch.fhnw.madnessevent.data.domain.Event;
import ch.fhnw.madnessevent.data.domain.TicketBooking;
import ch.fhnw.madnessevent.data.repository.TicketBookingRepository;

@Service
public class TicketBookingService {

    private final TicketBookingRepository ticketBookingRepository;
    private final EventService eventService;

    public TicketBookingService(TicketBookingRepository ticketBookingRepository, EventService eventService) {
        this.ticketBookingRepository = ticketBookingRepository;
        this.eventService = eventService;
    }

    public List<TicketBookingResponse> getAllBookings() {
        return ticketBookingRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public TicketBookingResponse bookTickets(TicketRequest request) {
        validate(request);
        Event event = eventService.getEventEntityById(request.eventId());

        if (event.getAvailableTickets() < request.quantity()) {
            throw new BadRequestException("Only " + event.getAvailableTickets() + " tickets are available");
        }

        event.setAvailableTickets(event.getAvailableTickets() - request.quantity());
        eventService.saveEvent(event);

        TicketBooking booking = new TicketBooking();
        booking.setEvent(event);
        booking.setQuantity(request.quantity());
        booking.setPurchaserName(request.purchaserName().trim());
        booking.setBookedAt(LocalDateTime.now());

        return toResponse(ticketBookingRepository.save(booking));
    }

    private TicketBookingResponse toResponse(TicketBooking booking) {
        return new TicketBookingResponse(
                booking.getId(),
                booking.getEvent().getId(),
                booking.getQuantity(),
                booking.getPurchaserName(),
                booking.getEvent().getAvailableTickets(),
                booking.getBookedAt()
        );
    }

    private void validate(TicketRequest request) {
        if (request == null || request.eventId() == null || isBlank(request.purchaserName())) {
            throw new BadRequestException("Event id and purchaser name are required");
        }
        if (request.quantity() == null || request.quantity() < 1) {
            throw new BadRequestException("Ticket quantity must be at least 1");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
