package ch.fhnw.madnessevents.business;

import ch.fhnw.madnessevents.data.domain.Event;
import ch.fhnw.madnessevents.data.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event findById(long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
    }

    public Event save(@org.springframework.lang.NonNull Event event) {
        return eventRepository.save(event);
    }

    public Event update(long id, Event event) {
        event.setId(id);
        return eventRepository.save(event);
    }

    public void deleteById(long id) {
        eventRepository.deleteById(id);
    }
}