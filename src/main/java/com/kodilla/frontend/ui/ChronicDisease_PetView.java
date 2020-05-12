package com.kodilla.frontend.ui;

import com.kodilla.frontend.client.VetSystemClient;
import com.kodilla.frontend.domain.ChronicDisease_Pet;
import com.kodilla.frontend.ui.Forms.ChronicDisease_PetForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;

@Route(value = "chronicDisease_Pet", layout = MainView.class)
public class ChronicDisease_PetView extends VerticalLayout implements AfterNavigationObserver, HasUrlParameter<Long> {
    private Long petId;

    @Override
    public void setParameter(BeforeEvent event,
                             Long parameter) {
        this.petId = parameter;
    }

    //
    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        event.getLocation().getSubLocation().ifPresent(location ->
                {
                    petId = Long.parseLong(location.getSegments().get(0));
                    filter();
                }
        );
    }

    private VetSystemClient vetSystemClient = new VetSystemClient();
    private Grid<ChronicDisease_Pet> grid = new Grid<>(ChronicDisease_Pet.class);

    private TextField filterText = new TextField();
    private ChronicDisease_PetForm chronicDisease_petForm = new ChronicDisease_PetForm(this);

    public ChronicDisease_PetView() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        configureGrid();

        Button addNewChronicDiseaseBtn = new Button("Add new chronicDisease");
        addNewChronicDiseaseBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            chronicDisease_petForm.setChronicDisease_Pet(new ChronicDisease_Pet());
            chronicDisease_petForm.hideEditAndDeleteButton();
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addNewChronicDiseaseBtn);
        HorizontalLayout mainContent = new HorizontalLayout(grid, chronicDisease_petForm);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();


        chronicDisease_petForm.setChronicDisease_Pet(null);

        grid.asSingleSelect().addValueChangeListener(event -> {
                    chronicDisease_petForm.setChronicDisease_Pet(grid.asSingleSelect().getValue());
                    chronicDisease_petForm.hideSaveButton();
                }
        );
    }


    private void configureGrid() {
        grid.setSizeFull();
        grid.addColumn(n -> vetSystemClient.getChronicDisease(n.getChronicDiseaseId()).getName()).setHeader("Chronic diseases");
        grid.addColumn(ChronicDisease_Pet::getDateOfDiagnosis).setHeader("Date of diagnosis");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }


    public void filter() {

        grid.setItems(vetSystemClient.getPetChronicDiseases(petId));
    }

    public Long getPetId() {
        return petId;
    }
}

