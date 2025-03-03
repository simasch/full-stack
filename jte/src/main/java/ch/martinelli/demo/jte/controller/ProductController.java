package ch.martinelli.demo.jte.controller;

import ch.martinelli.demo.jte.domain.Product;
import ch.martinelli.demo.jte.domain.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", service.findAll(PageRequest.of(0, 10)));
        model.addAttribute("product", new Product());
        return "products";
    }

    @GetMapping("/paging")
    public String findAll(Model model, @RequestParam Integer page, @RequestParam Integer size) {
        Page<Product> products = service.findAll(PageRequest.of(page, size));
        model.addAttribute("products", products);

        return "fragments/product-rows";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute Product product, RedirectAttributes ra) {
        service.save(product);
        ra.addFlashAttribute("message", "Product saved successfully");
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        model.addAttribute("products", service.findAll(PageRequest.of(0, 10)));
        model.addAttribute("product", service.findById(id));
        return "products";
    }
}