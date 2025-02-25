package ch.martinelli.demo.faces.ui;

import ch.martinelli.demo.faces.domain.Product;
import ch.martinelli.demo.faces.domain.ProductService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ProductBean implements Serializable {

    @Inject
    private ProductService service;

    private Product product = new Product();
    private boolean editMode = false;
    private List<Product> products;

    @PostConstruct
    public void loadData() {
        products = service.findAll(0, 1000).toList();
    }

    public void saveProduct() {
        service.save(product);
        loadData();
        product = new Product();
        editMode = false;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product saved successfully"));
    }

    public void editProduct(Product product) {
        this.product = product;
        editMode = true;
    }

    public void cancelEdit() {
        product = new Product();
        editMode = false;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct() {
        return product;
    }

    public boolean isEditMode() {
        return editMode;
    }
}
