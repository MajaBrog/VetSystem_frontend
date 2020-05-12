package com.kodilla.frontend.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinServlet;

import java.util.Optional;


@Route("")
@PageTitle("Contacts | Vaadin CRM")
//@CssImport("./styles/shared-styles.css")
public class MainView extends AppLayout {
    private final Tabs menu;

    public MainView() {
        this.setDrawerOpened(false);
        Span appName = new Span("VetSystem");

        menu = createMenuTabs();
        FlexLayout centeredLayout = new FlexLayout();
        centeredLayout.setSizeFull();
        centeredLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        centeredLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        centeredLayout.add(menu);

        this.addToNavbar(appName);
        this.addToNavbar(true, centeredLayout);
    }

    private static Tabs createMenuTabs() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);

        tabs.add(createTab(VaadinIcon.USER, "Clients", ClientView.class));
        tabs.add(createTab(VaadinIcon.ARCHIVES, "Visits", VisitView.class));
        tabs.add(createTab(VaadinIcon.PILLS, "Medication", MedicationView.class));
        tabs.add(createTab(VaadinIcon.PILL, "Vaccination", VaccinationView.class));
//        tabs.add(createTab(VaadinIcon.PILL, "Chronic Diseases", Chro.class));
        final String contextPath = VaadinServlet.getCurrent().getServletContext().getContextPath();
        final Tab logoutTab = createTab(createLogoutLink(contextPath));
        tabs.add(logoutTab);
        return tabs;
    }

    private static Tab createTab(VaadinIcon icon, String title, Class<? extends Component> viewClass) {
        return createTab(populateLink(new RouterLink(null, viewClass), icon, title));
    }

    private static Tab createTab(Component content) {
        final Tab tab = new Tab();
        tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        tab.add(content);
        return tab;
    }

    private static Anchor createLogoutLink(String contextPath) {
        final Anchor a = populateLink(new Anchor(), VaadinIcon.ARROW_RIGHT, "logout");
        a.setHref(contextPath + "/logout");
        return a;
    }

    private static <T extends HasComponents> T populateLink(T a, VaadinIcon icon, String title) {
        a.add(icon.create());
        a.add(title);
        return a;
    }


}

