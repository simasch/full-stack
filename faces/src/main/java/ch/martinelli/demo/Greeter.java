package ch.martinelli.demo;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class Greeter {

    public String hello() {
        return "Hello from Quarkus REST";
    }
}
