package ch.fhnw.madnessevents.data.repository;

import ch.fhnw.madnessevents.data.domain.Dj;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DjRepository extends JpaRepository<Dj, Long> {
}