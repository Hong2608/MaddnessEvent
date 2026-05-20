package ch.fhnw.madnessevents.data.repository;

import ch.fhnw.madnessevents.data.domain.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {
}