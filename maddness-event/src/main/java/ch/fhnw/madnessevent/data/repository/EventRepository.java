package ch.fhnw.madnessevent.data.repository;

import ch.fhnw.madnessevent.data.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    // Homepage: "Newest upcoming event" — first event from today onwards
    Optional<Event> findTopByDateAfterOrderByDateAsc(LocalDate today);

    // Tickets page: all future events ordered by date (soonest first)
    List<Event> findByDateAfterOrderByDateAsc(LocalDate today);

    // Tickets page: "Events in March" — filter by month/year
    List<Event> findByDateBetweenOrderByDateAsc(LocalDate from, LocalDate to);

    // Tickets page search bar: search by event name
    List<Event> findByNameContainingIgnoreCaseOrderByDateAsc(String name);

    // Tickets page filter: search by location
    List<Event> findByLocationContainingIgnoreCaseOrderByDateAsc(String location);
}

