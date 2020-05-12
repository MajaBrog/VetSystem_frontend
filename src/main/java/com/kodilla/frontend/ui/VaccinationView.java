package com.kodilla.frontend.ui;

import com.kodilla.frontend.client.VetSystemClient;
import com.kodilla.frontend.domain.Vaccination;
import com.kodilla.frontend.ui.Forms.VaccinationForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route(value = "vaccination", layout = MainView.class)
public class VaccinationView extends VerticalLayout {
    VetSystemClient vetSystemClient = new VetSystemClient();
    private Grid<Vaccination> grid = new Grid<>(Vaccination.class);
    VaccinationForm vaccinationForm = new VaccinationForm(this);
    private TextField filterText = new TextField();


    public VaccinationView() {
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

        Button addNewClientBtn = new Button("Add new vaccination");
        addNewClientBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            vaccinationForm.setClient(new Vaccination());
            vaccinationForm.hideEditAndDeleteButton();
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addNewClientBtn);
        HorizontalLayout mainContent = new HorizontalLayout(grid, vaccinationForm);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();
        refresh();

        vaccinationForm.setClient(null);

        grid.asSingleSelect().addValueChangeListener(event -> {
                    vaccinationForm.setClient(grid.asSingleSelect().getValue());
                    vaccinationForm.hideSaveButton();
                }
        );
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setColumns("name", "disease", "dosePerKg", "unit", "mandatory");
        grid.addColumn(Vaccination::getName).setHeader("Vaccination");
        grid.addColumn(Vaccination::getDisease).setHeader("Disease");
        grid.addColumn(Vaccination::getDosePerKg).setHeader("Dose per Kg");
        grid.addColumn(Vaccination::getUnit).setHeader("Units");
        grid.addColumn(Vaccination::isMandatory).setHeader("Mandatory");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        Grid.Column<Vaccination> editorColumn = grid.addComponentColumn(person -> {
            Button edit = new Button("Edit");
            edit.addClickListener(event -> {
                        vaccinationForm.setClient(grid.asSingleSelect().getValue());
                        vaccinationForm.hideSaveButton();
                    }
            );
//            edit.setEnabled(!editor.isOpen());
//            editButtons.add(edit);
            return edit;
        });
    }

    public void refresh() {
        grid.setItems(vetSystemClient.getVaccinations());
    }
    public void update() {
        grid.setItems(vetSystemClient.filterVaccinations(filterText.getValue()));
    }

}