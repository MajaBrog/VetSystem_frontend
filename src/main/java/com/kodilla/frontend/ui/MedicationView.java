package com.kodilla.frontend.ui;

import com.kodilla.frontend.client.VetSystemClient;
import com.kodilla.frontend.domain.Medication;
import com.kodilla.frontend.ui.Forms.MedicationForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

//@Route(value = "Medication")
//public class MedicationView extends VerticalLayout {
//    RestTemplate restTemplate = new RestTemplate();
//    private String backend = "http://localhost:8089//v1/medication";
//    private Grid<Medication> grid = new Grid<>(Medication.class);
//    MedicationForm medicationForm = new MedicationForm(this);
//    private TextField filterText = new TextField();
//
//
//    public MedicationView() {
//        filterText.setPlaceholder("Filter by name...");
//        filterText.setClearButtonVisible(true);
//        filterText.setValueChangeMode(ValueChangeMode.EAGER);
//        //        filterText.addValueChangeListener(e -> updateList());
//        configureGrid();
//
//        Button addNewClientBtn = new Button("Add new medication");
//        addNewClientBtn.addClickListener(e -> {
//            grid.asSingleSelect().clear();
//            medicationForm.setClientDto(new Medication());
//            medicationForm.hideEditAndDeleteButton();
//        });
//
//        HorizontalLayout toolbar = new HorizontalLayout(filterText,
//                addNewClientBtn);
//        HorizontalLayout mainContent = new HorizontalLayout(grid, medicationForm);
//        mainContent.setSizeFull();
//        grid.setSizeFull();
//
//        add(toolbar, mainContent);
//
//        setSizeFull();
//        refresh();
//
//        medicationForm.setClientDto(null);
//
//        grid.asSingleSelect().addValueChangeListener(event -> {
//                    medicationForm.setClientDto(grid.asSingleSelect().getValue());
//                    medicationForm.hideSaveButton();
//                }
//        );
//    }
//
//    private void configureGrid() {
//        grid.addClassName("client-grid");
//        grid.setSizeFull();
//        grid.setColumns("medicationName", "dosePerKg", "unit");
//        grid.addColumn(Medication::getMedicationName).setHeader("Medication");
//        grid.addColumn(Medication::getDosePerKg).setHeader("Dose per Kg");
//        grid.addColumn(Medication::getUnit).setHeader("Units");
//
////        grid.addColumn(TemplateRenderer.<Medication>of(
////                "<button on-click='handleUpdate'>Update</button>")
////                .withEventHandler(n->{
////                    medicationForm.setClientDto(grid.asSingleSelect().getValue());
////                    medicationForm.hideSaveButton();}));
//        grid.addColumn(
//                new NativeButtonRenderer<>("Edit item",
//                        clickedItem -> {
//                            // remove the item
//                        })
//        );
//        grid.getColumns().forEach(col -> col.setAutoWidth(true));
//    }
//
//    public void refresh() {
//        List<Medication> responseList = new ArrayList<>();
//        try {
//            Medication[] Response = restTemplate.getForObject(backend, Medication[].class);
//            responseList = Arrays.asList(Optional.ofNullable(Response).orElse(new Medication[0]));
//        } catch (RestClientException e) {
//        }
//        grid.setItems(responseList);
//    }
//
//
//}
@Route(value = "medication", layout = MainView.class)
public class MedicationView extends VerticalLayout {
    VetSystemClient vetSystemClient = new VetSystemClient();
        private Grid<Medication> grid = new Grid<>(Medication.class);
    MedicationForm medicationForm = new MedicationForm(this);
    private TextField filterText = new TextField();


    public MedicationView() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> {
            if (e.getValue() == "") {
                refresh();
            } else {
                update();
            }
        });        configureGrid();

        Button addNewClientBtn = new Button("Add new medication");
        addNewClientBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            medicationForm.setMedication(new Medication());
            medicationForm.hideEditAndDeleteButton();
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addNewClientBtn);
        HorizontalLayout mainContent = new HorizontalLayout(grid, medicationForm);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();
        refresh();

        medicationForm.setMedication(null);

        grid.asSingleSelect().addValueChangeListener(event -> {
                    medicationForm.setMedication(grid.asSingleSelect().getValue());
                    medicationForm.hideSaveButton();
                }
        );

       grid.addComponentColumn(medication -> {
            Button edit = new Button("Edit");
            edit.addClickListener(event -> {
                System.out.println(medication);
                        medicationForm.setMedication(medication);
                        medicationForm.hideSaveButton();
                    }
            );
//            edit.setEnabled(!editor.isOpen());
//            editButtons.add(edit);
            return edit;
        });
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.addColumn(Medication::getMedicationName).setHeader("Medication");
        grid.addColumn(Medication::getDosePerKg).setHeader("Dose per Kg");
        grid.addColumn(Medication::getUnit).setHeader("Units");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    public void refresh() {
        grid.setItems(vetSystemClient.getMedications());
    }

    public void update() {
        grid.setItems(vetSystemClient.filterMedications(filterText.getValue()));
    }

}
