package ch.fhnw.madnessevent.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.madnessevent.business.service.TicketBookingService;
import ch.fhnw.madnessevent.controller.dto.TicketBookingResponse;
import ch.fhnw.madnessevent.controller.dto.TicketRequest;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketBookingService ticketBookingService;

    public TicketController(TicketBookingService ticketBookingService) {
        this.ticketBookingService = ticketBookingService;
    }

    @GetMapping
    public List<TicketBookingResponse> getAllBookings() {
        return ticketBookingService.getAllBookings();
    }

    @PostMapping
    public ResponseEntity<TicketBookingResponse> bookTickets(@RequestBody TicketRequest request) {
        TicketBookingResponse booking = ticketBookingService.bookTickets(request);
        return ResponseEntity.created(URI.create("/api/tickets/" + booking.bookingId())).body(booking);
    }
}
