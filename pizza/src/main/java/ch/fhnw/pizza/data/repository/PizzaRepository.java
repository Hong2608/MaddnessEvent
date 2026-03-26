package ch.fhnw.madnessevents.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.madnessevents.data.domain.Pizza;

@Repository
//JpaRepository should be typed to the domain class and an ID type
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    Pizza findByPizzaName(String pizzaName);
    List<Pizza> findAllByPizzaToppingsContainsIgnoreCase(String topping);
}
