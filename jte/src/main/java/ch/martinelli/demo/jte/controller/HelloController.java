package ch.martinelli.demo.jte.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @PostMapping("/hello")
    public String sayHello(Model model, String name) {
        model.addAttribute("greeting", "Hello " + name);
        return "hello";
    }
}
