package ch.martinelli.demo.qute.controller;

import ch.martinelli.demo.qute.domain.Product;
import ch.martinelli.demo.qute.domain.ProductService;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.util.List;

@Path("/products")
public class Products {

    @Inject
    ProductService service;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance products(List<Product> products, Product product, String message);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance listProducts(@QueryParam("message") String message) {
        return Templates.products(service.findAll(0, 1000).toList(), new Product(), message);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response saveProduct(@BeanParam Product product) {
        service.save(product);

        UriBuilder uriBuilder = UriBuilder.fromPath("/products").queryParam("message", "Product saved successfully");

        return Response.status(Response.Status.SEE_OTHER)
                .location(uriBuilder.build())
                .build();
    }

    @GET
    @Path("/edit/{id}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance editProduct(@PathParam("id") Long id) {
        return Templates.products(service.findAll(0, 1000).toList(), service.findById(id), null);
    }

}