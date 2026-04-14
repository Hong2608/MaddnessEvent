package ch.fhnw.madnessevent.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.madnessevent.data.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
