package ch.fhnw.madnessevents.business;

import ch.fhnw.madnessevents.data.domain.Ticket;
import ch.fhnw.madnessevents.data.repository.TicketRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ch.fhnw.madnessevents.data.repository.EventRepository;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;

   public TicketService(TicketRepository ticketRepository, EventRepository eventRepository) {
    this.ticketRepository = ticketRepository;
    this.eventRepository = eventRepository;
}

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket findById(long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found"));
    }
    
    public Ticket save(Ticket ticket) {
    if (ticket.getPrice() < 0) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket price cannot be negative");
    }

    if (ticket.getEvent() == null || ticket.getEvent().getId() == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket must be linked to an event");
    }

    long eventId = ticket.getEvent().getId();
    var event = eventRepository.findById(eventId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event not found"));

    ticket.setEvent(event);

    Ticket savedTicket = ticketRepository.save(ticket);
    return findById(savedTicket.getId());
}
   public Ticket update(long id, Ticket ticket) {
    ticket.setId(id);

    if (ticket.getPrice() < 0) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket price cannot be negative");
    }

    if (ticket.getEvent() == null || ticket.getEvent().getId() == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket must be linked to an event");
    }

    long eventId = ticket.getEvent().getId();
    var event = eventRepository.findById(eventId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event not found"));

    ticket.setEvent(event);

    Ticket updatedTicket = ticketRepository.save(ticket);
    return findById(updatedTicket.getId());
}

    public void deleteById(long id) {
        ticketRepository.deleteById(id);
    }
}