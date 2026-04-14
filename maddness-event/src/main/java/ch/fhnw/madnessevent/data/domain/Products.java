package ch.fhnw.madnessevent.data.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // e.g. "T-shirt", "Hoodie", "Wristband", "Sticker"
    @Column(nullable = false)
    private String name;

    // Shown in Shop: "description"
    @Column(nullable = false, length = 500)
    private String description;

    // Shown in Shop: "photo"
    @Column(name = "photo_url")
    private String photoUrl;

    // Shown in Shop: "price"
    @Column(nullable = false)
    private Float price;

    @Column(nullable = false)
    private Integer stock;

    // APPAREL or ACCESSORIES — drives which section it appears in
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductCategory category;

    // Only for APPAREL items (T-shirt, hoodie, hat, tank-top)
    // Shown in Shop: "color"
    @Column
    private String color;

    // Only for APPAREL items — null for accessories
    // Shown in Shop: "size"
    @Enumerated(EnumType.STRING)
    @Column
    private Size size;
}
