package com.kodilla.frontend.ui.Form;

import com.kodilla.frontend.client.VetSystemClient;
import com.kodilla.frontend.domain.Vaccination;
import com.kodilla.frontend.domain.Unit;
import com.kodilla.frontend.ui.EntityView.VaccinationView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class VaccinationForm extends FormLayout {

    private final VaccinationView vaccinationView;
    private VetSystemClient vetSystemClient = new VetSystemClient();

    private TextField name = new TextField("Vaccination");
    private TextField disease = new TextField("Disease");
    private TextField dosePerKg = new TextField("Dose per Kg");
    private ComboBox<Unit> unit = new ComboBox<>("Unit");
    private Checkbox mandatory = new Checkbox("Mandatory",false);


    Button save = new Button("Save");
    Button update = new Button("Update");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Vaccination> binder =
            new Binder<>(Vaccination.class);

    private Vaccination vaccination = binder.getBean();

    public VaccinationForm(VaccinationView vaccinationView) {
        this.vaccinationView = vaccinationView;
        unit.setItems(Unit.values());
        binder.bindInstanceFields(this);
        binder.setBean(new Vaccination());

        add(name,
                disease,
                dosePerKg,
                unit,
                mandatory,
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
        vaccination = binder.getBean();
        vetSystemClient.updateVaccination(vaccination);
        vaccinationView.refresh();
        setVaccination(null);
    }

    private void save() {

        vaccination = binder.getBean();
        vetSystemClient.createVaccination(vaccination);
        vaccinationView.refresh();
        setVaccination(null);
    }

    private void delete() {
        vaccination = binder.getBean();
        vetSystemClient.deleteVaccination(vaccination);
        vaccinationView.refresh();
        setVaccination(null);
    }

    private void close() {
        setVaccination(null);
    }


    public void setVaccination(Vaccination vaccination) {

        binder.setBean(vaccination);

        if (vaccination == null) {
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