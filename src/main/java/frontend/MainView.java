package frontend;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.applayout.AppLayout.Section;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinSession;

import p2.launchpool.Usuario;

import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

/**
 * The main view contains a button and a click listener.
 */
@Route
@PWA(name = "My Application", shortName = "My Application")
public class MainView extends AppLayout implements AfterNavigationObserver {
	
	Usuario current=null;

	private final H1 title;
	private final RouterLink proyectostab;
	private final RouterLink candidaturastab;
	private final RouterLink logintab;
	private final RouterLink tusproyectos;
	private final RouterLink tuscandidaturas;
	
    public MainView() {
    	
    	this.title = new H1("Launchpool");
    	this.proyectostab = new RouterLink("Explorar proyectos",ProyectosView.class);
    	this.candidaturastab = new RouterLink("Explorar candidaturas",CandidaturasView.class);
		this.logintab = new RouterLink("Login/Registrate",LoginView.class);
		this.tusproyectos = new RouterLink("Tus proyectos",Tusproyectos.class);
		this.tuscandidaturas = new RouterLink("Tus candidaturas",Tuscandidaturas.class);
		
		final UnorderedList list = new UnorderedList(new ListItem(proyectostab),new ListItem(candidaturastab),new ListItem(logintab),new ListItem(tusproyectos),new ListItem(tuscandidaturas));
		final Nav navigation = new Nav(list);
		addToDrawer(navigation);
		setPrimarySection(Section.DRAWER);
		setDrawerOpened(false);

		// Header
		
		final Header header = new Header(new DrawerToggle(), title);
		addToNavbar(header);
    	
    	current=(Usuario) VaadinSession.getCurrent().getAttribute("user");
    	VaadinSession.getCurrent().setAttribute("user", current);
    }
    
    private RouterLink[] getRouterLinks() {
		return new RouterLink[] {proyectostab,candidaturastab,logintab,tusproyectos,tuscandidaturas};
	}
    
	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		for (final RouterLink routerLink : getRouterLinks()) {
			if (routerLink.getHighlightCondition().shouldHighlight(routerLink, event)) {
				title.setText(routerLink.getText());
			}
		
		}
	}
    

}
