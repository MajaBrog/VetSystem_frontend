package com.kodilla.frontend.ui.Form;

import com.kodilla.frontend.client.VetSystemClient;
import com.kodilla.frontend.domain.Address;
import com.kodilla.frontend.domain.Client;
import com.kodilla.frontend.ui.EntityView.ClientView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;

public class ClientForm extends FormLayout {
    private final ClientView clientView;
    private VetSystemClient vetSystemClient = new VetSystemClient();

    TextField legalID = new TextField("Legal Id");
    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextField phoneNumber = new TextField("Phone number");

    TextField street = new TextField("street");
    TextField houseNumber = new TextField("houseNumber");
    TextField homeNumber = new TextField("homeNumber");
    TextField city = new TextField("city");
    TextField postcode = new TextField("postcode");
    TextField email = new TextField("Email");

    Button save = new Button("Save");
    Button update = new Button("Update");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Client> binder =
            new Binder<>(Client.class);

    private Client client = binder.getBean();

    public ClientForm(ClientView clientView) {
        this.clientView = clientView;
//        addClassName("client-form");
        binder.bind(legalID, "legalID");
        binder.bind(firstName, "firstName");
        binder.bind(lastName, "lastName");
        binder.bind(phoneNumber, "phoneNumber");
        binder.bind(street, "address.street");
        binder.forField(houseNumber).withConverter(new StringToIntegerConverter("Must enter a number")).bind("address.houseNumber");
        binder.forField(homeNumber).withConverter(new StringToIntegerConverter("Must enter a number")).bind("address.homeNumber");
        binder.bind(city, "address.city");
        binder.bind(postcode, "address.postcode");
        binder.bind(email, "email");
        binder.setBean(new Client(new Address()));

//        binder.bindInstanceFields(this);
        add(legalID,
                firstName,
                lastName,
                phoneNumber,
                street,
                houseNumber,
                homeNumber,
                city,
                postcode,
                email,
                createButtonsLayout());
        save.addClickListener(event -> save());
        update.addClickListener(event -> update());
        delete.addClickListener(event -> delete());
        close.addClickListener(event -> close());

    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        update.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addClickShortcut(Key.ENTER);
        update.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);
        return new HorizontalLayout(save, update, delete, close);
    }

    private void update() {
        client = binder.getBean();
        vetSystemClient.updateClient(client);
        clientView.refresh();
        setClient(null);
    }

    private void save() {
        Client client = binder.getBean();
        vetSystemClient.createClient(client);
        clientView.refresh();
        setClient(null);
    }

    private void delete() {
        client = binder.getBean();
        vetSystemClient.deleteClient(client);
        clientView.refresh();
        setClient(null);
    }

    private void close() {
        setClient(null);
    }

    public void setClient(Client client) {

        binder.setBean(client);

        if (client == null) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }

    public void hideSaveButton() {
        save.setVisible(false);
        update.setVisible(true);
        delete.setVisible(true);
    }

    public void hideEditAndDeleteButton() {
        save.setVisible(true);
        update.setVisible(false);
        delete.setVisible(false);
    }

}