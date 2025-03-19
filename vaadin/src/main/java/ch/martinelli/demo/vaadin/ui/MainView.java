package ch.martinelli.demo.vaadin.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

@PageTitle("Hello Vaadin")
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

        var productsLink = new RouterLink("Products", ProductView.class);
        productsLink.addClassName(LumoUtility.Margin.Top.XLARGE);
        add(productsLink);
    }
}
