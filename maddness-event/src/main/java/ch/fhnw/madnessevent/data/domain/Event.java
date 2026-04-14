package ch.fhnw.madnessevent.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // Shown on Homepage (newest event) and Tickets page
    @Column(name = "photo_url")
    private String photoUrl;

    // Shown on Tickets page: "date"
    @Column(nullable = false)
    private LocalDate date;

    // Shown on Tickets page: "location"
    @Column(nullable = false)
    private String location;

    // Shown on Tickets page: "DJ"
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "event_djs",
        joinColumns    = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "dj_id")
    )
    @Builder.Default
    private List<DJ> djs = new ArrayList<>();

    // Shown on Tickets page: "price"
    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private Integer capacity;

    // Shown on Tickets page: "number of tickets available"
    @Column(nullable = false)
    private Integer availableTickets;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private List<TicketBooking> bookings = new ArrayList<>();
}

