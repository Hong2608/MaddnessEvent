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
            // ---------- DJs ----------
            if (djRepository.count() == 0) {
                djRepository.save(new Dj(null, "Amelie Lens", "Techno", "Belgium",
                        "https://images.unsplash.com/photo-1493225457124-a3eb161ffa5f?w=800&q=80",
                        "International techno DJ known for dark, driving sets and a residency at Berlin's Berghain. Founder of the Lenske label."));
                djRepository.save(new Dj(null, "DJ Nyx", "Dark Techno", "Switzerland",
                        "https://images.unsplash.com/photo-1571266028243-e4733b1f4b1d?w=800&q=80",
                        "Basel-based dark techno performer known for relentless kick patterns, hypnotic loops, and unfiltered industrial energy."));
                djRepository.save(new Dj(null, "Luna Bass", "Hard Techno", "Switzerland",
                        "https://images.unsplash.com/photo-1516280440614-37939bbacd81?w=800&q=80",
                        "High-energy hard techno DJ built for peak-time mayhem. Known across the Zurich underground for fast, fearless transitions."));
                djRepository.save(new Dj(null, "Echo Pulse", "Melodic Techno", "Germany",
                        "https://images.unsplash.com/photo-1574391884720-bbc049ec09ad?w=800&q=80",
                        "Crafts melodic, atmospheric techno journeys. Drawing on Berlin's deeper club tradition, his sets balance emotion and rhythm."));
            }

            // ---------- Events + tickets ----------
            if (eventRepository.count() == 0) {
                Event e1 = eventRepository.save(new Event(null,
                        "Basel Techno Night", "2026-05-20", "Vault 404, Basel",
                        "Deep in the subterranean levels of the city, we return to our roots. Raw, unfiltered techno in a converted industrial warehouse."));
                Event e2 = eventRepository.save(new Event(null,
                        "Zurich Neon Pulse", "2026-06-12", "Hangar 9, Zurich",
                        "A night of melodic techno under neon lasers. Hangar 9 transforms into an atmospheric playground where sound, light, and motion become one."));
                Event e3 = eventRepository.save(new Event(null,
                        "Geneva Afterdark Session", "2026-07-04", "Le Bunker, Geneva",
                        "From midnight to sunrise, Le Bunker becomes Geneva's loudest secret. Acid lines, pounding kicks, and a community that doesn't sleep until the lights come up."));
                Event e4 = eventRepository.save(new Event(null,
                        "Lausanne Industrial Rave", "2026-07-26", "Usine Sud, Lausanne",
                        "Hard techno meets industrial architecture. A massive open warehouse, four DJs, and the kind of sound system that rearranges your insides."));

                ticketRepository.save(new Ticket(null, "Standard", 35.00, true, e1));
                ticketRepository.save(new Ticket(null, "VIP", 79.90, true, e1));
                ticketRepository.save(new Ticket(null, "Standard", 42.00, true, e2));
                ticketRepository.save(new Ticket(null, "Standard", 38.00, true, e3));
                ticketRepository.save(new Ticket(null, "Early Bird", 30.00, true, e4));
            }

            // ---------- Merchandise ----------
            if (merchandiseRepository.count() == 0) {
                merchandiseRepository.save(new Merchandise(null, "Madness Black Tee", 29.90, 80, "Apparel", "M"));
                merchandiseRepository.save(new Merchandise(null, "Neon Pulse Hoodie", 69.00, 40, "Apparel", "L"));
                merchandiseRepository.save(new Merchandise(null, "Basel Rave Cap", 24.90, 120, "Apparel", "M"));
                merchandiseRepository.save(new Merchandise(null, "MadnessEvent Wristband", 9.90, 300, "Accessories", "M"));
                merchandiseRepository.save(new Merchandise(null, "Techno Sticker Pack", 7.50, 500, "Accessories", "M"));
                merchandiseRepository.save(new Merchandise(null, "Neon Lighter", 12.00, 150, "Accessories", "M"));
            }
        };
    }
}
