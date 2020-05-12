package com.kodilla.frontend.ui;

import com.kodilla.frontend.client.VetSystemClient;
import com.kodilla.frontend.domain.Client;
import com.kodilla.frontend.domain.Pet;
import com.kodilla.frontend.domain.Visit;
import com.kodilla.frontend.ui.Forms.VisitForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;

@Route(value = "visit", layout = MainView.class)
public class VisitView extends VerticalLayout implements AfterNavigationObserver, HasUrlParameter<Long> {
    private Long petId;

    @Override
    public void setParameter(BeforeEvent event,
                             @OptionalParameter Long parameter) {
        this.petId = parameter;

    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        event.getLocation().getSubLocation().ifPresent(location ->
                {
                    petId = Long.parseLong(location.getSegments().get(0));
                    refresh();
                }
        );
    }

    VetSystemClient vetSystemClient = new VetSystemClient();
    private Grid<Visit> grid = new Grid<>(Visit.class);

    private TextField filterText = new TextField();
    VisitForm visitForm = new VisitForm(this);

    public VisitView() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        configureGrid();

        Button addNewVisitBtn = new Button("Add new visit");
        addNewVisitBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            visitForm.setVisit(new Visit());
            visitForm.hideEditAndDeleteButton();
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addNewVisitBtn);
        HorizontalLayout mainContent = new HorizontalLayout(grid, visitForm);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();

        refresh();

        visitForm.setVisit(null);

        grid.addComponentColumn(visit -> {
            Button edit = new Button("Edit");
            edit.addClickListener(event -> {
                        visitForm.setVisit(visit);
                        visitForm.hideSaveButton();
                    }
            );
            return edit;
        });

    }

    public void adjustView(Button button,){

    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("dateOfVisit","diagnose", "weight","additionalRecommendation");
        grid.addColumn(visit -> {
            Pet pet=vetSystemClient.getPet(visit.getPetId());
            Client client=vetSystemClient.getClient(pet.getClientId());
            return client.getFirstName()+" "+client.getLastName();
        }).setHeader("Client");
        grid.addColumn(visit -> vetSystemClient.getPet(visit.getPetId()).getName()).setHeader("Pet");
//        grid.addColumn(Visit::getDateOfVisit).setHeader("Date of visit");
//        grid.addColumn(Visit::getAdditionalRecommendation).setHeader("Additional recommendation");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    public void refresh() {
        if (petId == null) grid.setItems(vetSystemClient.getVisits());
        else grid.setItems(vetSystemClient.getPetVisits(petId));
    }

}

