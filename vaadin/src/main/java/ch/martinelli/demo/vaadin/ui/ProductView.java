package ch.martinelli.demo.vaadin.ui;

import ch.martinelli.demo.vaadin.domain.Product;
import ch.martinelli.demo.vaadin.domain.ProductService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Product Management")
@Route("products")
public class ProductView extends VerticalLayout {

    private final ProductService productService;

    private final Grid<Product> grid = new Grid<>();
    private final Binder<Product> binder = new Binder<>(Product.class);

    private Product product = new Product();

    public ProductView(ProductService productService) {
        this.productService = productService;

        setHeightFull();

        TextField nameField = new TextField("Name");
        binder.forField(nameField).asRequired("Name is required").bind(Product::getName, Product::setName);

        TextArea descriptionField = new TextArea("Description");
        binder.forField(descriptionField).bind(Product::getDescription, Product::setDescription);

        BigDecimalField priceField = new BigDecimalField("Price");
        binder.forField(priceField).asRequired("Price is required").bind(Product::getPrice, Product::setPrice);

        IntegerField stockField = new IntegerField("Stock");
        binder.forField(stockField).asRequired("Stock is required").bind(Product::getStock, Product::setStock);

        FormLayout form = new FormLayout(nameField, descriptionField, priceField, stockField);

        Button saveButton = new Button("Save");
        saveButton.addClickListener(e -> saveProduct());
        Button cancelButton = new Button("Cancel");
        cancelButton.addClickListener(e -> clearForm());

        grid.setHeightFull();
        grid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);

        grid.addColumn(Product::getName).setHeader("Name").setAutoWidth(true).setFlexGrow(0).setSortable(true).setSortProperty("name");
        grid.addColumn(Product::getDescription).setHeader("Description").setFlexGrow(1).setSortProperty("description");
        grid.addColumn(Product::getPrice).setHeader("Price").setFlexGrow(0).setTextAlign(ColumnTextAlign.END).setSortable(true).setSortProperty("price");
        grid.addColumn(Product::getStock).setHeader("Stock").setFlexGrow(0).setTextAlign(ColumnTextAlign.END).setSortable(true).setSortProperty("stock");

        grid.addItemClickListener(e -> editProduct(e.getItem()));

        add(new H2("Products"), form, new HorizontalLayout(saveButton, cancelButton), grid);

        updateList();
    }

    private void saveProduct() {
        if (binder.writeBeanIfValid(product)) {
            productService.save(product);
            clearForm();
            updateList();
            Notification.show("Product saved successfully");
        }
    }

    private void editProduct(Product product) {
        this.product = product;
        binder.readBean(product);
    }

    private void clearForm() {
        product = new Product();
        binder.readBean(product);
    }

    private void updateList() {
        grid.setItems(query -> productService.findAll(query.getOffset(), query.getLimit(), query.getSortOrders()));
    }
}