package com.kodilla.frontend.ui.EntityView;

import com.kodilla.frontend.client.VetSystemClient;
import com.kodilla.frontend.domain.Address;
import com.kodilla.frontend.domain.Client;
import com.kodilla.frontend.ui.Form.ClientForm;
import com.kodilla.frontend.ui.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
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
        filterText.setPrefixComponent(VaadinIcon.SEARCH.create());
        filterText.setPlaceholder("Filter by last name...");
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

        Button addNewClientBtn = new Button("Add new client", VaadinIcon.PLUS_CIRCLE.create());
        addNewClientBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            clientForm.setClient(new Client(new Address()));
            clientForm.hideEditAndDeleteButton();
        });

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

        grid.addComponentColumn(client -> {
            Button edit =new Button("Edit",VaadinIcon.EDIT.create());
            edit.addClickListener(event -> {
                        clientForm.setClient(client);
                        clientForm.hideSaveButton();
                    }
            );
            return edit;
        });
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    public void refresh() {
        grid.setItems(vetSystemClient.getClients());
    }

    public void update() {
        grid.setItems(vetSystemClient.filterClients(filterText.getValue()));
    }


}