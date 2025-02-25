package ch.martinelli.demo.faces.ui;

import ch.martinelli.demo.faces.domain.Product;
import ch.martinelli.demo.faces.domain.ProductService;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class ProductBean implements Serializable {

    @Inject
    ProductService service;
    @Inject
    LazyProductDataModel lazyProductDataModel;

    private Product product = new Product();
    private boolean editMode = false;

    public void saveProduct() {
        service.save(product);
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

    public LazyProductDataModel getLazyProductDataModel() {
        return lazyProductDataModel;
    }

    public Product getProduct() {
        return product;
    }

    public boolean isEditMode() {
        return editMode;
    }
}
