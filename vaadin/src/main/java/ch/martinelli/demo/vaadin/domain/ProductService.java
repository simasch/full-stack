package ch.martinelli.demo.vaadin.domain;

import com.vaadin.flow.data.provider.QuerySortOrder;
import com.vaadin.flow.data.provider.SortDirection;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class ProductService {

    private static final Map<String, Comparator<Product>> FIELD_COMPARATORS = Map.of(
            "name", Comparator.comparing(Product::getName),
            "description", Comparator.comparing(Product::getDescription),
            "price", Comparator.comparing(Product::getPrice),
            "stock", Comparator.comparing(Product::getStock)
    );

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

    public Stream<Product> findAll(int offset, int limit, List<QuerySortOrder> sortOrders) {
        return products.stream()
                .sorted((p1, p2) -> createProductComparator(sortOrders).compare(p1, p2))
                .skip(offset)
                .limit(limit);
    }

    private Comparator<Product> createProductComparator(List<QuerySortOrder> sortOrders) {
        if (sortOrders.isEmpty()) {
            return (_, _) -> 0;
        } else {
            return Optional.ofNullable(FIELD_COMPARATORS.get(sortOrders.getFirst().getSorted()))
                    .map(comparator -> sortOrders.getFirst().getDirection() == SortDirection.DESCENDING
                            ? comparator.reversed()
                            : comparator)
                    .orElse((o1, o2) -> 0);
        }
    }
}
