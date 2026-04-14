package ch.fhnw.madnessevent.controller;

import ch.fhnw.madnessevent.business.service.MenuService;
import ch.fhnw.madnessevent.data.domain.Menu;
import ch.fhnw.madnessevent.data.domain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<Menu> getMenu(@RequestParam(name = "location") String location) {
        if (location == null || location.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Menu menu = menuService.getMenuByLocation(location);
        return ResponseEntity.ok(menu);
    }

    @GetMapping(path = "/pizzas", produces = "application/json")
    public ResponseEntity<List<Pizza>> getPizzaList() {
        List<Pizza> pizzaList = menuService.getAllPizzas();
        return ResponseEntity.ok(pizzaList);
    }

    @GetMapping(path = "/pizzas/{id}", produces = "application/json")
    public ResponseEntity<Pizza> getPizza(@PathVariable Long id) {
        try {
            Pizza pizza = menuService.findPizzaById(id);
            return ResponseEntity.ok(pizza);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping(path = "/pizzas", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addPizza(@RequestBody Pizza pizza) {
        try {
            Pizza created = menuService.addPizza(pizza);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping(path = "/pizzas/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updatePizza(@PathVariable Long id, @RequestBody Pizza pizza) {
        try {
            Pizza updated = menuService.updatePizza(id, pizza);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/pizzas/{id}")
    public ResponseEntity<String> deletePizza(@PathVariable Long id) {
        try {
            menuService.deletePizza(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
