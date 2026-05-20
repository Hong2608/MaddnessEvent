package ch.fhnw.madnessevents;

import ch.fhnw.madnessevents.data.domain.Dj;
import ch.fhnw.madnessevents.data.domain.Event;
import ch.fhnw.madnessevents.data.domain.Merchandise;
import ch.fhnw.madnessevents.data.domain.Ticket;
import ch.fhnw.madnessevents.data.repository.DjRepository;
import ch.fhnw.madnessevents.data.repository.EventRepository;
import ch.fhnw.madnessevents.data.repository.MerchandiseRepository;
import ch.fhnw.madnessevents.data.repository.TicketRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MadnessEventsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MadnessEventsApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(
            DjRepository djRepository,
            EventRepository eventRepository,
            TicketRepository ticketRepository,
            MerchandiseRepository merchandiseRepository
    ) {
        return args -> {
            djRepository.save(new Dj(null, "Amelie Lens", "Techno", "Belgium",
                    "https://example.com/amelie.jpg", "International techno DJ"));

            Event event1 = eventRepository.save(
                 new Event(null, "Basel Techno Night", "2026-05-20", "Basel", "A techno event with international DJs")
);
        
        ticketRepository.save(new Ticket(null, "VIP", 79.90, true, event1));

            merchandiseRepository.save(new Merchandise(null, "Madness T-Shirt", 39.90,
                    25, "Clothing"));
        };
    }
}