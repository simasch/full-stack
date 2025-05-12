package ch.martinelli.demo.faces.ui;

import ch.martinelli.demo.faces.domain.Product;
import ch.martinelli.demo.faces.domain.ProductService;
import jakarta.inject.Singleton;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import java.util.List;
import java.util.Map;

@Singleton
public class LazyProductDataModel extends LazyDataModel<Product> {

    private final ProductService productService;

    public LazyProductDataModel(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        return productService.count();
    }

    @Override
    public List<Product> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        return productService.findAll(first, pageSize).toList();
    }
}
