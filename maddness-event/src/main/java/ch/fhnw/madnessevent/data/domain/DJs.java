
package ch.fhnw.madnessevent.data.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;    
   import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "djs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DJ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String genre;

    @Column(length = 1000)
    private String description;

    // Displayed on the DJs page
    @Column(name = "photo_url")
    private String photoUrl;

    @ManyToMany(mappedBy = "djs")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private List<Event> events = new ArrayList<>();
}


