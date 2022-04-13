package frontend;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.WeakHashMap;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ReadOnlyHasValue;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import p2.launchpool.Candidatura;
import p2.launchpool.Candidaturascontroller;
import p2.launchpool.Usuario;


@Route(value = "tuscandidaturas",layout=MainView.class)
public class Tuscandidaturas extends VerticalLayout{
	public Tuscandidaturas() throws Exception{
		
		Usuario current=(Usuario)VaadinSession.getCurrent().getAttribute("user");
		Candidaturascontroller Candidaturas=Candidaturascontroller.controller();
		Candidaturascontroller usuarios=Candidaturascontroller.controller();
		Grid <Candidatura> Candidaturasgrid=new Grid<>();
		Binder<Candidatura> binderCandidatura=new Binder<>(Candidatura.class,false);
    	Editor<Candidatura> editor = Candidaturasgrid.getEditor();
    	editor.setBinder(binderCandidatura);
    	editor.setBuffered(true);
		
    	Candidaturasgrid.addSelectionListener(selection -> {
		    Optional<Candidatura> optionalPerson = selection.getFirstSelectedItem();
		    if (optionalPerson.isPresent()) {
		    	
		       
		        
		        
		    }
		});
    	Grid.Column<Candidatura> idcolumna=Candidaturasgrid.addColumn(Candidatura::getId).setHeader("Id");
    	Grid.Column<Candidatura> nombrecolumna=Candidaturasgrid.addColumn(Candidatura::getNombre).setHeader("Nombre");
    	Grid.Column<Candidatura> tlfcolumna=Candidaturasgrid.addColumn(Candidatura::getTlf).setHeader("Telefono");
    	Grid.Column<Candidatura> emailcolumna=Candidaturasgrid.addColumn(Candidatura::getEmail).setHeader("E-mail");
    	Grid.Column<Candidatura> sobremicolumna=Candidaturasgrid.addColumn(Candidatura::getSobremi).setHeader("Sobre mi");
    	Grid.Column<Candidatura> especialidadescolumna=Candidaturasgrid.addColumn(Candidatura::getEspecialidades).setHeader("Especialidades");
    	
    	if(current!=null) {
    		try {
				Candidaturasgrid.setItems(Candidaturas.buscar(current.getUsername()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	}
    	
    	Label idLabel = new Label();
    	ReadOnlyHasValue<Long> idlistener =
		        new ReadOnlyHasValue<>(
		            text -> idLabel.setText(text.toString()));
		binderCandidatura.forField(idlistener).bind(Candidatura::getId, null);
    	
    	TextField nombreedit = new TextField();
		binderCandidatura.forField(nombreedit).bind(Candidatura::getNombre, Candidatura::setNombre);
		nombrecolumna.setEditorComponent(nombreedit);
		
		TextField tlfedit = new TextField();
		binderCandidatura.forField(tlfedit).bind(Candidatura::getTlf, Candidatura::setTlf);
		tlfcolumna.setEditorComponent(tlfedit);
		
		TextField emailedit = new TextField();
		binderCandidatura.forField(emailedit).bind(Candidatura::getEmail, Candidatura::setEmail);
		emailcolumna.setEditorComponent(emailedit);
		
		TextField sobremiedit = new TextField();
		binderCandidatura.forField(sobremiedit).bind(Candidatura::getSobremi, Candidatura::setSobremi);
		sobremicolumna.setEditorComponent(sobremiedit);
		
		TextField especialidadesedit = new TextField();
		binderCandidatura.forField(especialidadesedit).bind(Candidatura::getEspecialidades, Candidatura::setEspecialidades);
		especialidadescolumna.setEditorComponent(especialidadesedit);
		
		
		Collection<Button> editButtons = Collections
		        .newSetFromMap(new WeakHashMap<>());
		Grid.Column<Candidatura> editorColumn = Candidaturasgrid.addComponentColumn(Candidatura -> {
		    Button edit = new Button("Editar");
		    edit.addClassName("editar");
		    edit.addClickListener(e -> {
		        editor.editItem(Candidatura);
		        
		        nombreedit.focus();
		    });
		    edit.setEnabled(!editor.isOpen());
		    editButtons.add(edit);
		    return edit;
		});
		editor.addOpenListener(e -> editButtons.stream()
		        .forEach(button -> button.setEnabled(!editor.isOpen())));
		editor.addCloseListener(e -> editButtons.stream()
		        .forEach(button -> button.setEnabled(!editor.isOpen())));

		Button save = new Button("Save", 
			e ->{
				editor.save();
		
				try {
					Candidaturas.updatejson();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			});
		save.addClassName("Save");
		
		Button cancel = new Button(VaadinIcon.CLOSE.create(), e -> editor.cancel());
		cancel.addClassName("cancel");
		
		Button delete = new Button(VaadinIcon.TRASH.create(), 
				e ->{
					
					Long id=idlistener.getValue();
					try {
						
						
							Candidaturas.borrar(id);
							Notification.show("Prestamo borrado");
						
							
						
					} catch (IOException e1) {
						Notification.show("No se pudo eliminar el prestamo");
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						Candidaturasgrid.setItems(Candidaturas.buscar(current.getUsername()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				});
		cancel.addClassName("cancel");

		// Add a keypress listener that listens for an escape key up event.
		// Note! some browsers return key as Escape and some as Esc
		Candidaturasgrid.getElement().addEventListener("keyup", event -> editor.cancel())
		        .setFilter("event.key === 'Escape' || event.key === 'Esc'");

		Div buttons = new Div(save, cancel,delete);
		editorColumn.setEditorComponent(buttons);
		
		
		
		
		FormLayout columnLayout = new FormLayout();
		
		
		
		
		
		TextField nombrein = new TextField();
		nombrein.setPlaceholder("Nombre y apellidos:");
		TextField tlfin = new TextField();
		tlfin.setPlaceholder("Telefono");
		TextField emailin = new TextField();
		emailin.setPlaceholder("E-mail:");
		TextField sobremiin = new TextField();
		sobremiin.setPlaceholder("Escribe algo sobre ti:");
		TextField especialidadesin = new TextField();
		especialidadesin.setPlaceholder("Perfiles profesionales buscados:");
		
        Button button = new Button("Crear Candidatura",
                event ->{ 
                	
                	
                	
                	try {
                		
                			Candidaturas.crear(nombrein.getValue(),current.getUsername(),tlfin.getValue(),emailin.getValue(),sobremiin.getValue(),especialidadesin.getValue());
        					
                			Notification.show("Candidatura añadido");
                		
                		
						
                	} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                	try {
						Candidaturasgrid.setItems(Candidaturas.buscar( current.getUsername() ));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                	
                });
        
 
        
        VerticalLayout Candidaturaslayout=new VerticalLayout();
        
        columnLayout.add(nombrein,tlfin,emailin,sobremiin,especialidadesin,button);
        
        Candidaturaslayout.add(Candidaturasgrid,columnLayout);
        if((current)==null) {
        	H3 notinit=new H3("Inicia sesión para acceder a tus Candidaturas");
        	add(notinit);
        }else {
        	add(Candidaturaslayout);
        }
	}

}
