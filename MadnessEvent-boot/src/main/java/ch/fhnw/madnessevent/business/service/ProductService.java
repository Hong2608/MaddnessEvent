package ch.fhnw.madnessevent.business.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import ch.fhnw.madnessevent.business.exception.BadRequestException;
import ch.fhnw.madnessevent.business.exception.ResourceNotFoundException;
import ch.fhnw.madnessevent.controller.dto.ProductRequest;
import ch.fhnw.madnessevent.data.domain.Product;
import ch.fhnw.madnessevent.data.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " was not found"));
    }

    public Product createProduct(ProductRequest request) {
        validate(request);
        Product product = new Product();
        applyRequest(product, request);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductRequest request) {
        validate(request);
        Product product = getProductById(id);
        applyRequest(product, request);
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Product with id " + id + " was not found");
        }
        productRepository.deleteById(id);
    }

    private void applyRequest(Product product, ProductRequest request) {
        product.setName(request.name().trim());
        product.setDescription(request.description().trim());
        product.setPrice(request.price());
        product.setStock(request.stock());
        product.setCategory(request.category().trim());
    }

    private void validate(ProductRequest request) {
        if (request == null || isBlank(request.name()) || isBlank(request.description()) || isBlank(request.category())) {
            throw new BadRequestException("Product name, description, and category are required");
        }
        if (request.price() == null || request.price().compareTo(BigDecimal.ZERO) < 0) {
            throw new BadRequestException("Product price must be zero or greater");
        }
        if (request.stock() == null || request.stock() < 0) {
            throw new BadRequestException("Product stock must be zero or greater");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
