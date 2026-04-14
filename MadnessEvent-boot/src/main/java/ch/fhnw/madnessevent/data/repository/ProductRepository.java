package ch.fhnw.madnessevent.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.fhnw.madnessevent.data.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
