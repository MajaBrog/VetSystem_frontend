package com.kodilla.frontend.ui.Forms;

import com.kodilla.frontend.client.VetSystemClient;
import com.kodilla.frontend.domain.ChronicDisease;
import com.kodilla.frontend.domain.ChronicDisease_Pet;
import com.kodilla.frontend.ui.ChronicDisease_PetView;
import com.kodilla.frontend.ui.Converter.ChronicDiseaseComboBoxConverter;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.Binder;

import java.time.LocalDate;
import java.util.List;

public class ChronicDisease_PetForm extends FormLayout {

    private final ChronicDisease_PetView chronicDisease_petView;
    private VetSystemClient vetSystemClient = new VetSystemClient();

        List<ChronicDisease> chronicDiseaseList = vetSystemClient.getChronicDiseases();
//
    private ComboBox<ChronicDisease> chronicDiseaseId = new ComboBox<>("Chronic Disease");
//    private TextField petId = new TextField("PetId");
//    private TextField chronicDiseaseId = new TextField("Chronic Disease");
    private DatePicker dateOfDiagnosis = new DatePicker("Date of diagnosis", LocalDate.now());

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<ChronicDisease_Pet> binder =
            new Binder<>(ChronicDisease_Pet.class);

    private ChronicDisease_Pet chronicDisease_pet = binder.getBean();


    public ChronicDisease_PetForm(ChronicDisease_PetView chronicDisease_petView) {
        this.chronicDisease_petView = chronicDisease_petView;
        chronicDiseaseId.setItems(chronicDiseaseList);
        chronicDiseaseId.setItemLabelGenerator(ChronicDisease::getName);
        binder.forField(chronicDiseaseId).withConverter(new ChronicDiseaseComboBoxConverter()).bind("chronicDiseaseId");
//        binder.forField(petId).withConverter(new StringToLongConverter("must be long")).bind("petId");
//        binder.forField(chronicDiseaseId).withConverter(new StringToLongConverter("must be long")).bind("chronicDiseaseId");

        binder.bindInstanceFields(this);
        binder.setBean(new ChronicDisease_Pet(chronicDisease_petView.getPetId()));
//        System.out.println(chronicDisease_petView.getPetId());
//        System.out.println(chronicDiseaseId.getValue().getId());
//        System.out.println(dateOfDiagnosis.getValue());
//        ChronicDisease_Pet chronicDisease_Pet_Pet = new ChronicDisease_Pet(5L, 11L, dateOfDiagnosis.getValue());
//        binder.setBean(chronicDisease_Pet_Pet);
        add(chronicDiseaseId,
                dateOfDiagnosis,
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

        chronicDisease_pet = binder.getBean();
        vetSystemClient.addChronicDisease(chronicDisease_pet);
        chronicDisease_petView.filter();
        setChronicDisease_Pet(null);
    }

    private void delete() {
        chronicDisease_pet = binder.getBean();
        vetSystemClient.deletePetChronicDisease(chronicDisease_pet);
        chronicDisease_petView.filter();
    }

    private void close() {
        setChronicDisease_Pet(null);
    }

    public void setChronicDisease_Pet(ChronicDisease_Pet chronicDisease_pet) {

        binder.setBean(chronicDisease_pet);

        if (chronicDisease_pet == null) {
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