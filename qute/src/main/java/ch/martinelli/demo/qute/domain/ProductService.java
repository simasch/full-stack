package ch.martinelli.demo.qute.domain;

import jakarta.inject.Singleton;

import java.util.List;
import java.util.Objects;

@Singleton
public class ProductService {

    private static final List<Product> products = ProductData.create500Products();

    public void save(Product product) {
        products.stream().filter(p -> Objects.equals(p.getId(), product.getId())).findFirst()
                .ifPresent(p -> {
                    p.setName(product.getName());
                    p.setDescription(product.getDescription());
                    p.setPrice(product.getPrice());
                    p.setStock(product.getStock());
                });
    }

    public int count() {
        return products.size();
    }

    public List<Product> findAll(int offset, int limit) {
        return products.stream()
                .skip(offset)
                .limit(limit)
                .toList();
    }

    public Product findById(Long id) {
        return products.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst().orElse(null);
    }
}
