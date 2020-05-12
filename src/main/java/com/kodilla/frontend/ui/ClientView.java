package com.kodilla.frontend.ui;

import com.kodilla.frontend.client.VetSystemClient;
import com.kodilla.frontend.domain.Address;
import com.kodilla.frontend.domain.Client;
import com.kodilla.frontend.ui.Forms.ClientForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route(value = "client", layout = MainView.class)
public class ClientView extends VerticalLayout {

    Long clientId;
    VetSystemClient vetSystemClient = new VetSystemClient();
    private Grid<Client> grid = new Grid<>(Client.class);

    private TextField filterText = new TextField();
    ClientForm clientForm = new ClientForm(this);

    public ClientView() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> {
            if (e.getValue() == "") {
                refresh();
            } else {
                update();
            }
        });

        configureGrid();

        Button addNewClientBtn = new Button("Add new client");
        addNewClientBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            clientForm.setClient(new Client(new Address()));
            clientForm.hideEditAndDeleteButton();
        });

//        NativeButton button = new NativeButton(
//                "See Medication List");
//        button.addClickListener(e ->
//                button.getUI().ifPresent(ui ->
//                        ui.navigate("medication"))
//        );

//        HorizontalLayout toolbar = new HorizontalLayout(filterText,
//                addNewClientBtn, button);
        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addNewClientBtn);
        HorizontalLayout mainContent = new HorizontalLayout(grid, clientForm);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();

        refresh();

        clientForm.setClient(null);

        grid.asSingleSelect().addValueChangeListener(event -> {
                    clientId = grid.asSingleSelect().getValue().getId();
                    grid.getUI().ifPresent(ui -> ui.navigate(PetView.class, clientId));
                }
        );
    }


    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("legalID", "firstName", "lastName", "phoneNumber", "email");
        grid.addColumn(client -> {
            Address address = client.getAddress();
            return address == null ? "-" : address.getStreet() + " " + address.getHouseNumber() + "/" + address.getHomeNumber()
                    + "\n," + address.getPostcode() + " " + address.getCity();
        }).setHeader("Address");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    public void refresh() {
        grid.setItems(vetSystemClient.getClients());
    }

    public void update() {
        grid.setItems(vetSystemClient.filterClients(filterText.getValue()));
    }


}