package ch.fhnw.madnessevent.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.madnessevent.data.domain.Dj;

@Repository
public interface DjRepository extends JpaRepository<Dj, Long> {

    // Used by DjService.findOrCreateByName() — prevents duplicate DJs
    Optional<Dj> findByNameIgnoreCase(String name);

    // Powers the search bar on the DJs page
    List<Dj> findByNameContainingIgnoreCase(String name);

    // Check existence before creating (used in duplicate validation)
    boolean existsByNameIgnoreCase(String name);
}
