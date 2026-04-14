package ch.fhnw.madnessevent;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ch.fhnw.madnessevent.data.domain.Dj;
import ch.fhnw.madnessevent.data.domain.Event;
import ch.fhnw.madnessevent.data.domain.Product;
import ch.fhnw.madnessevent.data.repository.DjRepository;
import ch.fhnw.madnessevent.data.repository.EventRepository;
import ch.fhnw.madnessevent.data.repository.ProductRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DjRepository djRepository;
    private final EventRepository eventRepository;
    private final ProductRepository productRepository;

    public DataInitializer(DjRepository djRepository, EventRepository eventRepository, ProductRepository productRepository) {
        this.djRepository = djRepository;
        this.eventRepository = eventRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        if (djRepository.count() == 0) {
            seedDjs();
        }
        if (eventRepository.count() == 0) {
            seedEvents();
        }
        if (productRepository.count() == 0) {
            seedProducts();
        }
    }

    private void seedDjs() {
        djRepository.save(createDj("DJ Nyx", "Techno", "Dark techno performer for late-night rave events."));
        djRepository.save(createDj("Luna Bass", "Hard Techno", "High-energy DJ focused on fast club sets."));
        djRepository.save(createDj("Echo Pulse", "Melodic Techno", "Melodic techno artist with atmospheric sets."));
    }

    private void seedEvents() {
        Dj djNyx = djRepository.findByNameIgnoreCase("DJ Nyx").orElseThrow();
        Dj lunaBass = djRepository.findByNameIgnoreCase("Luna Bass").orElseThrow();
        Dj echoPulse = djRepository.findByNameIgnoreCase("Echo Pulse").orElseThrow();

        eventRepository.save(createEvent(
                "Basel Warehouse Night",
                LocalDate.now().plusDays(30),
                "Basel",
                List.of(djNyx, lunaBass),
                new BigDecimal("35.00"),
                250
        ));

        eventRepository.save(createEvent(
                "Zurich Underground Session",
                LocalDate.now().plusDays(45),
                "Zurich",
                List.of(echoPulse),
                new BigDecimal("42.00"),
                180
        ));
    }

    private void seedProducts() {
        productRepository.save(createProduct(
                "MadnessEvent T-Shirt",
                "Black T-shirt with MadnessEvent front print.",
                new BigDecimal("29.90"),
                80,
                "Apparel"
        ));

        productRepository.save(createProduct(
                "MadnessEvent Wristband",
                "Branded festival wristband for event supporters.",
                new BigDecimal("9.90"),
                150,
                "Accessories"
        ));
    }

    private Dj createDj(String name, String genre, String description) {
        Dj dj = new Dj();
        dj.setName(name);
        dj.setGenre(genre);
        dj.setDescription(description);
        return dj;
    }

    private Event createEvent(String name, LocalDate date, String venue, List<Dj> djs, BigDecimal price, int capacity) {
        Event event = new Event();
        event.setName(name);
        event.setDate(date);
        event.setVenue(venue);
        event.setDjs(djs);
        event.setPrice(price);
        event.setCapacity(capacity);
        event.setAvailableTickets(capacity);
        return event;
    }

    private Product createProduct(String name, String description, BigDecimal price, int stock, String category) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        product.setCategory(category);
        return product;
    }
}
