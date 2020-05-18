package com.kodilla.frontend.ui.EntityView;

import com.kodilla.frontend.client.VetSystemClient;
import com.kodilla.frontend.domain.*;
import com.kodilla.frontend.ui.Form.VisitForm;
import com.kodilla.frontend.ui.Form.Visit_MedicationForm;
import com.kodilla.frontend.ui.Form.Visit_VaccinationForm;
import com.kodilla.frontend.ui.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;

import java.util.stream.Collectors;

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
                    configureGrid();
                    showAddButtonVisible(addNewVisitBtn, true);
                }
        );
    }

    VetSystemClient vetSystemClient = new VetSystemClient();
    private Grid<Visit> grid = new Grid<>(Visit.class);
    private TextField filterText = new TextField();
    VisitForm visitForm = new VisitForm(this);
    Visit_MedicationForm visit_medicationForm = new Visit_MedicationForm(this);
    Visit_VaccinationForm visit_vaccinationForm = new Visit_VaccinationForm(this);

    Button addNewVisitBtn = new Button("Add new visit", VaadinIcon.PLUS_CIRCLE.create());

    public VisitView() {
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

        addNewVisitBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            visitForm.setVisit(new Visit());
            visitForm.hideEditAndDeleteButton();
        });

        showAddButtonVisible(addNewVisitBtn, false);

        HorizontalLayout toolbar = new HorizontalLayout(filterText,
                addNewVisitBtn);
        HorizontalLayout mainContent = new HorizontalLayout(grid, visitForm, visit_medicationForm,visit_vaccinationForm);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);

        setSizeFull();

        refresh();

        visitForm.setVisit(null);
        visit_medicationForm.setVisit_Medication(null);
        visit_vaccinationForm.setVisit_Vaccination(null);
    }

    public void addMedication(Long visitId) {
        visit_medicationForm.setVisit_Medication(new Visit_Medication(visitId));
    }

    public void addVaccination(Long visitId) {
        visit_vaccinationForm.setVisit_Vaccination(new Visit_Vaccination(visitId));
    }

    private void showAddButtonVisible(Button button, boolean show) {
        button.setVisible(show);
    }

    private void configureGrid() {
        grid.setSizeFull();

        grid.setColumns("dateOfVisit", "diagnose", "weight", "additionalRecommendation");
        grid.addColumn(visit -> {
            Pet pet = vetSystemClient.getPet(visit.getPetId());
            Client client = vetSystemClient.getClient(pet.getClientId());
            return client.getFirstName() + " " + client.getLastName();
        }).setHeader("Client");

        grid.addColumn(visit -> vetSystemClient.getPet(visit.getPetId()).getName()).setHeader("Pet");

        grid.addColumn(visit -> visit.getVisit_medicationsDtoList().stream()
                .map(this::getMedicationDetails)
                .collect(Collectors.joining(", "))).setHeader("Medication");

        grid.addColumn(visit -> visit.getVisit_vaccinationsDtoList().stream()
                .map(this::getVaccinationDetails)
                .collect(Collectors.joining(", "))).setHeader("Vaccination");

        if (petId != null) {
            grid.addComponentColumn(visit -> {
                Button edit = new Button("Edit",VaadinIcon.EDIT.create());
                edit.addClickListener(event -> {
                            visitForm.setVisit(visit);
                            visitForm.hideSaveButton();
                        }
                );
                return edit;
            });
        }

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private String getMedicationDetails(Visit_Medication visit_medication) {
        return vetSystemClient.getMedication(visit_medication.getMedicationId()).getMedicationName() + "(" +
                visit_medication.getDose() + " " + visit_medication.getUnit().name() + ")";
    }

    private String getVaccinationDetails(Visit_Vaccination visit_vaccination) {
        return vetSystemClient.getVaccination(visit_vaccination.getVaccinationId()).getName() + "(" +
                visit_vaccination.getDose() + " " + visit_vaccination.getUnit().name() + ")";
    }

    public void refresh() {
        if (petId == null) grid.setItems(vetSystemClient.getVisits());
        else grid.setItems(vetSystemClient.getPetVisits(petId));
    }

    public Long getPetId() {
        return petId;
    }

    public void update() {
        grid.setItems(vetSystemClient.filterVisit(filterText.getValue()));
    }

}

