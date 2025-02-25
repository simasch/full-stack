package ch.martinelli.demo.jte.controller;

import ch.martinelli.demo.jte.domain.Product;
import ch.martinelli.demo.jte.domain.ProductService;
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
        model.addAttribute("products", service.findAll(0, 1000).toList());
        model.addAttribute("product", new Product());
        return "products";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute Product product, RedirectAttributes ra) {
        service.save(product);
        ra.addFlashAttribute("message", "Product saved successfully");
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        model.addAttribute("products", service.findAll(0, 1000).toList());
        model.addAttribute("product", service.findById(id));
        return "products";
    }
}