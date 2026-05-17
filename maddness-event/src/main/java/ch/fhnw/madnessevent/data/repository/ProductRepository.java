package ch.fhnw.madnessevent.data.repository;

import ch.fhnw.madnessevent.data.domain.Product;
import ch.fhnw.madnessevent.data.domain.ProductCategory;
import ch.fhnw.madnessevent.data.domain.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Shop page: load the Apparel section
    List<Product> findByCategory(ProductCategory category);

    // Shop search bar: search by product name within a category
    List<Product> findByCategoryAndNameContainingIgnoreCase(
            ProductCategory category, String name);

    // Shop filter: filter Apparel by size (e.g. only show M items)
    List<Product> findByCategoryAndSize(ProductCategory category, Size size);

    // Shop filter: filter Apparel by color
    List<Product> findByCategoryAndColorIgnoreCase(ProductCategory category, String color);

    // Shop filter: filter Apparel by both size and color
    List<Product> findByCategoryAndSizeAndColorIgnoreCase(
            ProductCategory category, Size size, String color);

    // Stock check: only show items still in stock
    List<Product> findByCategoryAndStockGreaterThan(ProductCategory category, int minStock);
}

