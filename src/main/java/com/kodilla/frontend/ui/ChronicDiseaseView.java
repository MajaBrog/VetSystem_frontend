package com.kodilla.frontend.ui;

import com.kodilla.frontend.client.VetSystemClient;
import com.kodilla.frontend.domain.ChronicDisease;
import com.kodilla.frontend.ui.Forms.ChronicDiseaseForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;

@Route(value = "chronicDisease", layout = MainView.class)
public class ChronicDiseaseView extends VerticalLayout{

    VetSystemClient vetSystemClient = new VetSystemClient();
    private Grid<ChronicDisease> grid = new Grid<>(ChronicDisease.class);

    private TextField filterText = new TextField();
    ChronicDiseaseForm chronicDiseaseForm = new ChronicDiseaseForm(this);

    public ChronicDiseaseView() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
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

        grid.asSingleSelect().addValueChangeListener(event -> {
                    chronicDiseaseForm.setChronicDisease(grid.asSingleSelect().getValue());
                    chronicDiseaseForm.hideSaveButton();
                }
        );
    }


    private void configureGrid() {
        grid.setSizeFull();
        grid.addColumn(ChronicDisease::getName).setHeader("Chronic Disease");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    public void refresh() {

        grid.setItems(vetSystemClient.getChronicDiseases());
    }



}

