package ch.fhnw.madnessevent.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.madnessevent.data.domain.Dj;

public interface DjRepository extends JpaRepository<Dj, Long> {
    Optional<Dj> findByNameIgnoreCase(String name);
}
