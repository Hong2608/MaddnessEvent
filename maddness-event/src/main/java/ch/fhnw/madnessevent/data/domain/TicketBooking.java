package ch.fhnw.madnessevent.data.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ticket_bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The event being booked (from the Tickets page)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Event event;

    @Column(nullable = false)
    private Integer quantity;

    // Entered by user at booking (Login required on Tickets page)
    @Column(nullable = false)
    private String purchaserName;

    @Column(nullable = false, updatable = false)
    private LocalDateTime bookedAt;

    @PrePersist
    protected void onCreate() {
        this.bookedAt = LocalDateTime.now();
    }
}