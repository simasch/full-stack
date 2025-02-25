package ch.martinelli.demo.faces.ui;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class HelloBean {

    private String name;

    public String sayHello() {
        if (name == null || name.trim().isEmpty()) {
            return "Please enter your name";
        } else {
            return "Hello " + name;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
