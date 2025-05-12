package ch.martinelli.demo.qute.controller;

import ch.martinelli.demo.qute.domain.Product;
import ch.martinelli.demo.qute.domain.ProductService;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.util.List;

@Path("/products")
public class Products {

    private final ProductService service;

    public Products(ProductService service) {
        this.service = service;
    }

    @CheckedTemplate
    public static class Templates {

        public static native TemplateInstance products(List<Product> products, Product product, String message);

        public static native TemplateInstance product_rows(List<Product> products, int nextPage, int lastIndex);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance listProducts(@QueryParam("message") String message) {
        return Templates.products(service.findAll(0, 10), new Product(), message);
    }

    @GET
    @Path("/paging")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getProductRows(@QueryParam("page") int page) {
        var products = service.findAll(page * 10, 10);
        return Templates.product_rows(products, page + 1, products.size() - 2);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response saveProduct(@BeanParam Product product) {
        service.save(product);

        var uriBuilder = UriBuilder.fromPath("/products").queryParam("message", "Product saved successfully");

        return Response.status(Response.Status.SEE_OTHER)
                .location(uriBuilder.build())
                .build();
    }

    @GET
    @Path("/edit/{id}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance editProduct(@PathParam("id") Long id) {
        return Templates.products(service.findAll(0, 10), service.findById(id), null);
    }

}
