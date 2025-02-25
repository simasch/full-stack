package ch.martinelli.demo.faces.domain;

import jakarta.inject.Singleton;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Singleton
public class ProductService implements Serializable {

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

    public Stream<Product> findAll(int offset, int limit) {
        return products.stream()
                .skip(offset)
                .limit(limit);
    }

}
