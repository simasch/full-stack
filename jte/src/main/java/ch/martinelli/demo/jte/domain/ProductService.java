package ch.martinelli.demo.jte.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Service
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

    public Page<Product> findAll(Pageable pageable) {
        return new PageImpl<>(products.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize()).toList(), Pageable.ofSize(pageable.getPageSize()), products.size());
    }

    public Product findById(Long id) {
        return products.stream().filter(p -> Objects.equals(p.getId(), id)).findFirst().orElse(null);
    }
}
