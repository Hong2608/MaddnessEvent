package ch.fhnw.madnessevent.business.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ch.fhnw.madnessevent.business.exception.BadRequestException;
import ch.fhnw.madnessevent.business.exception.ResourceNotFoundException;
import ch.fhnw.madnessevent.controller.dto.EventRequest;
import ch.fhnw.madnessevent.controller.dto.EventResponse;
import ch.fhnw.madnessevent.data.domain.Dj;
import ch.fhnw.madnessevent.data.domain.Event;
import ch.fhnw.madnessevent.data.repository.EventRepository;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final DjService djService;

    public EventService(EventRepository eventRepository, DjService djService) {
        this.eventRepository = eventRepository;
        this.djService = djService;
    }

    public List<EventResponse> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Event getEventEntityById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event with id " + id + " was not found"));
    }

    public EventResponse getEventById(Long id) {
        return toResponse(getEventEntityById(id));
    }

    public EventResponse createEvent(EventRequest request) {
        validate(request);
        Event event = new Event();
        applyRequest(event, request);
        event.setAvailableTickets(request.capacity());
        return toResponse(eventRepository.save(event));
    }

    public EventResponse updateEvent(Long id, EventRequest request) {
        validate(request);
        Event event = getEventEntityById(id);
        applyRequest(event, request);
        if (event.getAvailableTickets() > event.getCapacity()) {
            event.setAvailableTickets(event.getCapacity());
        }
        return toResponse(eventRepository.save(event));
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event with id " + id + " was not found");
        }
        eventRepository.deleteById(id);
    }

    public EventResponse toResponse(Event event) {
        List<String> djNames = event.getDjs().stream()
                .map(Dj::getName)
                .toList();

        return new EventResponse(
                event.getId(),
                event.getName(),
                event.getDate(),
                event.getVenue(),
                djNames,
                event.getPrice(),
                event.getCapacity(),
                event.getAvailableTickets()
        );
    }

    private void applyRequest(Event event, EventRequest request) {
        event.setName(request.name().trim());
        event.setDate(request.date());
        event.setVenue(request.venue().trim());
        event.setPrice(request.price());
        event.setCapacity(request.capacity());
        event.setDjs(resolveDjs(request.djs()));
    }

    private List<Dj> resolveDjs(List<String> djNames) {
        if (djNames == null || djNames.isEmpty()) {
            return new ArrayList<>();
        }
        return djNames.stream()
                .map(djService::findOrCreateByName)
                .toList();
    }

    private void validate(EventRequest request) {
        if (request == null || isBlank(request.name()) || request.date() == null || isBlank(request.venue())) {
            throw new BadRequestException("Event name, date, and venue are required");
        }
        if (request.price() == null || request.price().compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequestException("Event price must be zero or greater");
        }
        if (request.capacity() == null || request.capacity() < 1) {
            throw new BadRequestException("Event capacity must be at least 1");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
