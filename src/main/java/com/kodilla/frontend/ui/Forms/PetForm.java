package com.kodilla.frontend.ui.Forms;

import com.kodilla.frontend.client.VetSystemClient;
import com.kodilla.frontend.domain.Pet;
import com.kodilla.frontend.ui.PetView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class PetForm extends FormLayout {

    private final PetView petView;
    private VetSystemClient vetSystemClient = new VetSystemClient();

    private TextField name = new TextField("Name");
    private TextField chipId = new TextField("Chip ID");
    private TextField kind = new TextField("Kind");

    private DatePicker birthDate = new DatePicker("Date of birth");
    private DatePicker dateOfSterilization = new DatePicker("Date of sterilisation");

    private Checkbox sterilised = new Checkbox("Sterilised",false);
    private Checkbox aggressive = new Checkbox("Aggressive",false);



    Button save = new Button("Save");
    Button update = new Button("Update");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Pet> binder =
            new Binder<>(Pet.class);

    private Pet pet = binder.getBean();

    public PetForm(PetView petView) {
        this.petView = petView;

        binder.bindInstanceFields(this);
        binder.setBean(new Pet());

        add(name,
                chipId,
                kind,
                birthDate,
                sterilised,
                dateOfSterilization,
                aggressive,
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
        pet = binder.getBean();
        vetSystemClient.updatePet(pet);
        petView.refresh();
        setPet(null);
    }

    private void save() {

        pet = binder.getBean();
        vetSystemClient.createPet(pet);
        petView.refresh();
        setPet(null);
    }

    private void delete() {
        pet = binder.getBean();
        vetSystemClient.deletePet(pet);
        petView.refresh();
    }

    private void close() {
        setPet(null);
    }

    public void setPet(Pet pet) {

        binder.setBean(pet);

        if (pet == null) {
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