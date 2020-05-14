package com.kodilla.frontend.ui;

import com.kodilla.frontend.client.VetSystemClient;
import com.kodilla.frontend.domain.ChronicDisease;
import com.kodilla.frontend.ui.Form.ChronicDiseaseForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route(value = "chronicDisease", layout = MainView.class)
public class ChronicDiseaseView extends VerticalLayout {

    VetSystemClient vetSystemClient = new VetSystemClient();
    private Grid<ChronicDisease> grid = new Grid<>(ChronicDisease.class);

    private TextField filterText = new TextField();
    ChronicDiseaseForm chronicDiseaseForm = new ChronicDiseaseForm(this);

    public ChronicDiseaseView() {
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

        Button addNewChronicDiseaseBtn = new Button("Add new chronicDisease");
        addNewChronicDiseaseBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            chronicDiseaseForm.setChronicDisease(new ChronicDisease());
            chronicDiseaseForm.hideEditAndDeleteButton();
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addNewChronicDiseaseBtn);
        HorizontalLayout mainContent = new HorizontalLayout(grid, chronicDiseaseForm);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();

        refresh();

        chronicDiseaseForm.setChronicDisease(null);

    }


    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns();
        grid.addColumn(ChronicDisease::getName).setHeader("Chronic Disease");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.addComponentColumn(chronicDisease -> {
            Button edit = new Button("Edit");
            edit.addClickListener(event -> {
                        chronicDiseaseForm.setChronicDisease(chronicDisease);
                        chronicDiseaseForm.hideSaveButton();
                    }
            );
            return edit;
        });
    }

    public void refresh() {

        grid.setItems(vetSystemClient.getChronicDiseases());
    }

    public void update() {
        grid.setItems(vetSystemClient.filterChronicDiseases(filterText.getValue()));
    }


}

