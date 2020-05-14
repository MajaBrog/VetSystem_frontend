
package com.kodilla.frontend.ui.Form;

import com.kodilla.frontend.client.VetSystemClient;
import com.kodilla.frontend.domain.Vaccination;
import com.kodilla.frontend.domain.Unit;
import com.kodilla.frontend.domain.Visit_Vaccination;
import com.kodilla.frontend.ui.Converter.VaccinationComboBoxConverter;
import com.kodilla.frontend.ui.VisitView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.util.List;

public class Visit_VaccinationForm extends FormLayout {

    private final VisitView visitView;
    private VetSystemClient vetSystemClient = new VetSystemClient();

    List<Vaccination> vaccinations = vetSystemClient.getVaccinations();
    private ComboBox<Vaccination> vaccinationId = new ComboBox<>("Vaccination");
    private TextField dose = new TextField("Dose");
    private ComboBox<Unit> unit = new ComboBox<>("Unit");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Visit_Vaccination> binder =
            new Binder<>(Visit_Vaccination.class);

    private Visit_Vaccination visit_vaccination = binder.getBean();


    public Visit_VaccinationForm(VisitView visitView) {
        this.visitView = visitView;
        vaccinationId.setItems(vaccinations);
        vaccinationId.setItemLabelGenerator(Vaccination::getName);
        unit.setItems(Unit.values());
        binder.forField(vaccinationId).withConverter(new VaccinationComboBoxConverter()).bind("vaccinationId");


        binder.bindInstanceFields(this);
        binder.setBean(new Visit_Vaccination());

        add(vaccinationId,
                dose, unit,
                createButtonsLayout());
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
        close.addClickListener(event -> close());

    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addClickShortcut(Key.ENTER);

        close.addClickShortcut(Key.ESCAPE);
        return new HorizontalLayout(save, delete, close);
    }


    private void save() {

        visit_vaccination = binder.getBean();
        vetSystemClient.createVisit_Vaccination(visit_vaccination);
//        visitView.filter();
        setVisit_Vaccination(null);
    }

    private void delete() {
        visit_vaccination = binder.getBean();
        vetSystemClient.deleteVisit_Vaccination(visit_vaccination);
//        visitView.filter();
    }

    private void close() {
        setVisit_Vaccination(null);
    }

    public void setVisit_Vaccination(Visit_Vaccination visit_vaccination) {

        binder.setBean(visit_vaccination);

        if (visit_vaccination == null) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }

    public void hideSaveButton() {
        save.setVisible(false);
        delete.setVisible(true);
    }

    public void hideEditAndDeleteButton() {
        save.setVisible(true);
        delete.setVisible(false);
    }

}