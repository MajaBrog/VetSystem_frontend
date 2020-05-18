package com.kodilla.frontend.ui.Form;

import com.kodilla.frontend.client.VetSystemClient;
import com.kodilla.frontend.domain.ChronicDisease;
import com.kodilla.frontend.ui.EntityView.ChronicDiseaseView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class ChronicDiseaseForm extends FormLayout {

    private final ChronicDiseaseView chronicDiseaseView;
    private VetSystemClient vetSystemClient = new VetSystemClient();

    private TextField name = new TextField("name");


    Button save = new Button("Save");
    Button update = new Button("Update");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<ChronicDisease> binder =
            new Binder<>(ChronicDisease.class);

    private ChronicDisease chronicDisease = binder.getBean();

    public ChronicDiseaseForm(ChronicDiseaseView chronicDiseaseView) {
        this.chronicDiseaseView = chronicDiseaseView;
        binder.bindInstanceFields(this);
        binder.setBean(new ChronicDisease());

        add(name,
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
        chronicDisease = binder.getBean();
        vetSystemClient.updateChronicDisease(chronicDisease);
        chronicDiseaseView.refresh();
        setChronicDisease(null);
    }

    private void save() {

        chronicDisease = binder.getBean();
        vetSystemClient.createChronicDisease(chronicDisease);
        chronicDiseaseView.refresh();
        setChronicDisease(null);
    }

    private void delete() {
        chronicDisease = binder.getBean();
        vetSystemClient.deleteChronicDisease(chronicDisease);
        chronicDiseaseView.refresh();
        setChronicDisease(null);
    }

    private void close() {
        setChronicDisease(null);
    }

    public void setChronicDisease(ChronicDisease chronicDisease) {

        binder.setBean(chronicDisease);

        if (chronicDisease == null) {
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

