package com.kodilla.frontend.ui.Form;

import com.kodilla.frontend.client.VetSystemClient;
import com.kodilla.frontend.domain.Medication;
import com.kodilla.frontend.domain.Unit;
import com.kodilla.frontend.ui.MedicationView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class MedicationForm extends FormLayout {

    private final MedicationView medicationView;
    private VetSystemClient vetSystemClient = new VetSystemClient();

    private TextField medicationName = new TextField("Medication");
    private TextField dosePerKg = new TextField("Dose per Kg");
    private ComboBox<Unit> unit = new ComboBox<>("Unit");


    Button save = new Button("Save");
    Button update = new Button("Update");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Medication> binder =
            new Binder<>(Medication.class);

    private Medication medication = binder.getBean();

    public MedicationForm(MedicationView medicationView) {
        this.medicationView = medicationView;
        unit.setItems(Unit.values());
        binder.bindInstanceFields(this);
        binder.setBean(new Medication());

        add(medicationName,
                dosePerKg,
                unit,
                createButtonsLayout());
        save.addClickListener(event -> save());
        update.addClickListener(event -> update());
        delete.addClickListener(event -> delete());
        close.addClickListener(event -> close());

    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        update.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addClickShortcut(Key.ENTER);
        update.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);
        return new HorizontalLayout(save, update, delete, close);
    }

    private void update() {
        medication = binder.getBean();
        vetSystemClient.updateMedication(medication);
        medicationView.refresh();
        setMedication(null);
    }

    private void save() {

        medication = binder.getBean();
        vetSystemClient.createMedication(medication);
        medicationView.refresh();
        setMedication(null);
    }

    private void delete() {
        medication = binder.getBean();
        vetSystemClient.deleteMedication(medication);
        medicationView.refresh();
    }

    private void close() {
        setMedication(null);
    }

    public void setMedication(Medication medication) {

        binder.setBean(medication);

        if (medication == null) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }

    public void hideSaveButton() {
        save.setVisible(false);
        update.setVisible(true);
        delete.setVisible(true);
    }

    public void hideEditAndDeleteButton() {
        save.setVisible(true);
        update.setVisible(false);
        delete.setVisible(false);
    }

}