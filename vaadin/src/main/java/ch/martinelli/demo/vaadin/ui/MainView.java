package ch.martinelli.demo.vaadin.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    public MainView() {
        var nameField = new TextField("Name");
        var sayHelloButton = new Button("Say hello");
        var text = new Span();

        sayHelloButton.addClickListener(e -> text.setText("Hello " + nameField.getValue()));

        var formLayout = new HorizontalLayout(nameField, sayHelloButton);
        formLayout.setAlignItems(Alignment.BASELINE);
        add(formLayout, text);
    }
}
