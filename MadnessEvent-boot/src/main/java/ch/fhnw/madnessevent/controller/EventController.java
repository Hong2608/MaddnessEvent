package ch.fhnw.madnessevent.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.madnessevent.business.service.EventService;
import ch.fhnw.madnessevent.controller.dto.EventRequest;
import ch.fhnw.madnessevent.controller.dto.EventResponse;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventResponse> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public EventResponse getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @PostMapping
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventRequest request) {
        EventResponse event = eventService.createEvent(request);
        return ResponseEntity.created(URI.create("/api/events/" + event.id())).body(event);
    }

    @PutMapping("/{id}")
    public EventResponse updateEvent(@PathVariable Long id, @RequestBody EventRequest request) {
        return eventService.updateEvent(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
