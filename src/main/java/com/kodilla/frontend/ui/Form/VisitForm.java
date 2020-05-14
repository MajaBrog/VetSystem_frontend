
package com.kodilla.frontend.ui.Form;

        import com.kodilla.frontend.client.VetSystemClient;
        import com.kodilla.frontend.domain.Visit;
        import com.kodilla.frontend.ui.VisitView;
        import com.vaadin.flow.component.Key;
        import com.vaadin.flow.component.button.Button;
        import com.vaadin.flow.component.button.ButtonVariant;
        import com.vaadin.flow.component.formlayout.FormLayout;
        import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
        import com.vaadin.flow.component.orderedlayout.VerticalLayout;
        import com.vaadin.flow.component.textfield.TextField;
        import com.vaadin.flow.data.binder.Binder;
        import com.vaadin.flow.data.converter.StringToIntegerConverter;

public class VisitForm extends FormLayout {

    private final VisitView visitView;
    private VetSystemClient vetSystemClient = new VetSystemClient();

    private TextField diagnose = new TextField("Diagnose");
    private TextField AdditionalRecommendation = new TextField("Additional recommendation");
    private TextField weight = new TextField("Weight");

    Button addMedication = new Button("Add Medication");
    Button addVaccination = new Button("Add Vaccination");
    Button save = new Button("Save");
    Button update = new Button("Update");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Visit> binder =
            new Binder<>(Visit.class);

    private Visit visit = binder.getBean();

    public VisitForm(VisitView visitView) {
        this.visitView = visitView;
        binder.forField(weight).withConverter(new StringToIntegerConverter("Must enter a number")).bind("weight");
        binder.bindInstanceFields(this);
        binder.setBean(new Visit());

        add(diagnose,
                AdditionalRecommendation,
                weight,
                createButtonsLayout());

        addMedication.addClickListener(event -> {
            saveWithoutClosing();
            save.setVisible(false);
            update.setVisible(true);
            setAddMedication();
        });
        addVaccination.addClickListener(event -> {
            saveWithoutClosing();
            save.setVisible(false);
            update.setVisible(true);
            setAddVaccination();
        });
        save.addClickListener(event -> save());
        update.addClickListener(event -> update());
        delete.addClickListener(event -> delete());
        close.addClickListener(event -> close());

    }

    private void setAddMedication(){
        visit = binder.getBean();
        visitView.addMedication(visit.getId());
        visitView.refresh();
    }

    private void setAddVaccination(){
        visit = binder.getBean();
        visitView.addMedication(visit.getId());
        visitView.refresh();
    }

    private VerticalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        update.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addClickShortcut(Key.ENTER);
        update.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);
        HorizontalLayout mainButtons=new HorizontalLayout(save, update, delete, close);
        HorizontalLayout addButtons=new HorizontalLayout(addMedication, addVaccination);
        return new VerticalLayout(addButtons,mainButtons);
    }

    private void update() {
        visit = binder.getBean();
        visit.setPetId(visitView.getPetId());
        vetSystemClient.updateVisit(visit);
        visitView.refresh();
        setVisit(null);
    }

    private void save() {
        visit = binder.getBean();
        visit.setPetId(visitView.getPetId());
        vetSystemClient.createVisit(visit);
        visitView.refresh();
        setVisit(null);
    }
    private void saveWithoutClosing() {
        visit = binder.getBean();
        visit.setPetId(visitView.getPetId());
        vetSystemClient.createVisit(visit);
        visitView.refresh();
    }

    private void delete() {
        visit = binder.getBean();
        visit.setPetId(visitView.getPetId());
        vetSystemClient.deleteVisit(visit);
        visitView.refresh();
    }

    private void close() {
        setVisit(null);
    }

    public void setVisit(Visit visit) {

        binder.setBean(visit);

        if (visit == null) {
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