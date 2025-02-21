# Return of the Full-Stack Developer

This project demonstrates different approaches to full-stack development using modern Java frameworks and templating engines. 
It serves as example code for my talk "Return of the Full-Stack Developer".

## Project Overview

The project showcases four different approaches to building full-stack web applications:

1. **Quarkus with Jakarta Faces (aka JSF)**
   - Uses PrimeFaces component library
   - Demonstrates server-side component-based UI
   - CDI integration with JSF beans

2. **Quarkus with Qute Templates**
   - Modern templating engine for Quarkus
   - REST endpoint integration
   - Type-safe template usage

3. **Spring Boot with JTE Templates**
   - Modern Java Template Engine (JTE)
   - Spring MVC integration

4. **Spring Boot with Vaadin**
   - Pure Java UI development
   - Component-based approach
   - Spring Boot integration

## Prerequisites

- Java 21
- Maven 3.8+
- Docker (optional, for container builds)

## Module Details

### Faces Module (Quarkus + JSF)
```bash
cd faces
mvn quarkus:dev
```
Access the application at `http://localhost:8080`

### Qute Module (Quarkus + Qute)
```bash
cd qute
mvn quarkus:dev
```
Access the application at `http://localhost:8081`

### JTE Module (Spring Boot + JTE)
```bash
cd jte
mvn spring-boot:run
```
Access the application at `http://localhost:8082`

### Vaadin Module (Spring Boot + Vaadin)
```bash
cd vaadin
mvn spring-boot:run
```
Access the application at `http://localhost:8083`

## Building for Production

Each module can be built for production:

### Quarkus Modules Faces and Qute, Spring Boot with JTE
```bash
mvn clean package
```
### Vaadin
```bash
mvn clean package -Pproduction
```

## Additional Resources

- [Quarkus Guide](https://quarkus.io/guides/)
- [PrimeFaces Documentation](https://primefaces.org/documentation)
- [Qute Reference Guide](https://quarkus.io/guides/qute-reference)
- [JTE Documentation](https://jte.gg/)
- [Vaadin Documentation](https://vaadin.com/docs)

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.
