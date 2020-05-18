
package com.kodilla.frontend.ui.Form;

import com.kodilla.frontend.client.VetSystemClient;
import com.kodilla.frontend.domain.Medication;
import com.kodilla.frontend.domain.Unit;
import com.kodilla.frontend.domain.Visit_Medication;
import com.kodilla.frontend.ui.Converter.MedicationComboBoxConverter;
import com.kodilla.frontend.ui.EntityView.VisitView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.util.List;

public class Visit_MedicationForm extends FormLayout {

    private final VisitView visitView;
    private VetSystemClient vetSystemClient = new VetSystemClient();
    List<Medication> medications = vetSystemClient.getMedications();
    private ComboBox<Medication> medicationId = new ComboBox<>("Medication");
    private TextField dose = new TextField("Dose");
    private ComboBox<Unit> unit = new ComboBox<>("Unit");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Visit_Medication> binder =
            new Binder<>(Visit_Medication.class);

    private Visit_Medication visit_medication = binder.getBean();

    private Long visitId;

    public Visit_MedicationForm(VisitView visitView) {
        this.visitView = visitView;
        medicationId.setItems(medications);
        medicationId.setItemLabelGenerator(Medication::getMedicationName);
        unit.setItems(Unit.values());
        binder.forField(medicationId).withConverter(new MedicationComboBoxConverter()).bind("medicationId");


        binder.bindInstanceFields(this);
        binder.setBean(new Visit_Medication());

        add(medicationId,
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
        visit_medication = binder.getBean();
        visit_medication.setVisitId(visitId);
        vetSystemClient.createVisit_Medication(visit_medication);
        visitView.refresh();
        setVisit_Medication(null);
    }

    private void delete() {
        visit_medication = binder.getBean();
        visit_medication.setVisitId(visitId);
        vetSystemClient.deleteVisit_Medication(visit_medication);
        visitView.refresh();
        setVisit_Medication(null);
    }

    private void close() {
        setVisit_Medication(null);
    }

    public void setVisit_Medication(Visit_Medication visit_medication) {

        binder.setBean(visit_medication);
        if (visit_medication == null) {
            setVisible(false);
        } else {
        visitId=visit_medication.getVisitId();
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