package ch.fhnw.madnessevent.data.repository;

import ch.fhnw.madnessevent.data.domain.DJ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DJRepository extends JpaRepository<DJ, Long> {

    // Used by DjService.findOrCreateByName() — prevents duplicate DJs
    Optional<DJ> findByNameIgnoreCase(String name);

    // Powers the search bar on the DJs page
    List<DJ> findByNameContainingIgnoreCase(String name);

    // Check existence before creating (used in duplicate validation)
    boolean existsByNameIgnoreCase(String name);
}