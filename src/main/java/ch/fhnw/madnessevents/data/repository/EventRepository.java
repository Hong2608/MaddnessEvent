package ch.fhnw.madnessevents.data.repository;

import ch.fhnw.madnessevents.data.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}