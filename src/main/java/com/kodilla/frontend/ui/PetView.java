package com.kodilla.frontend.ui;

import com.kodilla.frontend.client.VetSystemClient;
import com.kodilla.frontend.domain.ChronicDisease;
import com.kodilla.frontend.domain.ChronicDisease_Pet;
import com.kodilla.frontend.domain.Pet;
import com.kodilla.frontend.domain.Visit;
import com.kodilla.frontend.ui.Forms.PetForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;

import java.util.stream.Collectors;

@Route(value = "pet", layout = MainView.class)
public class PetView extends VerticalLayout implements AfterNavigationObserver, HasUrlParameter<Long> {
    private Long clientId;

    private Long petId;

    @Override
    public void setParameter(BeforeEvent event,
                             Long parameter) {
        this.clientId = parameter;
    }

    //
    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        event.getLocation().getSubLocation().ifPresent(location ->
                {clientId = Long.parseLong(location.getSegments().get(0));
                refresh();}
        );
    }

    VetSystemClient vetSystemClient = new VetSystemClient();
    private Grid<Pet> grid = new Grid<>(Pet.class);

    private TextField filterText = new TextField();
    PetForm petForm = new PetForm(this);

    public PetView() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        configureGrid();

        Button addNewPetBtn = new Button("Add new pet");
        addNewPetBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            petForm.setPet(new Pet());
            petForm.hideEditAndDeleteButton();
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addNewPetBtn);
        HorizontalLayout mainContent = new HorizontalLayout(grid, petForm);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();

        petForm.setPet(null);

        grid.addComponentColumn(pet -> {
            Button edit = new Button("Edit");
            edit.addClickListener(event -> {
                        petForm.setPet(pet);
                        petForm.hideSaveButton();
                    }
            );
            return edit;
        });

        grid.addComponentColumn(pet -> {
            Button button = new Button("Visits history");
            button.addClickListener(event -> {
                petId =  pet.getId();
                grid.getUI().ifPresent(ui -> ui.navigate(VisitView.class, petId));
                    }
            );
            return button;
        });

        grid.addComponentColumn(pet -> {
            Button button = new Button("Chronic diseases");
            button.addClickListener(event -> {
                petId = pet.getId();
                grid.getUI().ifPresent(ui -> ui.navigate(ChronicDisease_PetView.class, petId));
                    }
            );
//            button.setEnabled(!editor.isOpen());
//            editButtons.add(button);
            return button;
        });
    }


    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("name", "kind", "sterilised", "aggressive");
        grid.addColumn(Pet::getChipId).setHeader("Chip ID");
        grid.addColumn(Pet::getBirthDate).setHeader("Date of birth");
        grid.addColumn(Pet::getDateOfSterilization).setHeader("Date of sterilization");
//        grid.addColumn(pet -> pet.getChronicDisease_pets().stream()
//                .map(n->vetSystemClient.getChronicDisease(n.getChronicDiseaseId()).getName())
//                .collect(Collectors.joining( "," ))).setHeader("Chronic diseases");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }



    public void refresh() {

        grid.setItems(vetSystemClient.getClientPets(clientId));
    }


}

