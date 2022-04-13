package frontend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.combobox.ComboBox;
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

import p2.launchpool.Proyecto;
import p2.launchpool.Proyectoscontroller;
import p2.launchpool.Usuario;











@Route(value = "tusproyectos",layout=MainView.class)
public class Tusproyectos extends VerticalLayout{
	public Tusproyectos() throws Exception {
		Usuario current=(Usuario)VaadinSession.getCurrent().getAttribute("user");
		Proyectoscontroller proyectos=Proyectoscontroller.controller();
		Proyectoscontroller usuarios=Proyectoscontroller.controller();
		Grid <Proyecto> Proyectosgrid=new Grid<>();
		Binder<Proyecto> binderProyecto=new Binder<>(Proyecto.class,false);
    	Editor<Proyecto> editor = Proyectosgrid.getEditor();
    	editor.setBinder(binderProyecto);
    	editor.setBuffered(true);
		
    	Proyectosgrid.addSelectionListener(selection -> {
		    Optional<Proyecto> optionalPerson = selection.getFirstSelectedItem();
		    if (optionalPerson.isPresent()) {
		    	
		       
		        
		        
		    }
		});
    	Grid.Column<Proyecto> idcolumna=Proyectosgrid.addColumn(Proyecto::getId).setHeader("Id");
    	Grid.Column<Proyecto> nombrecolumna=Proyectosgrid.addColumn(Proyecto::getNombre).setHeader("Nombre");
    	Grid.Column<Proyecto> descripcioncolumna=Proyectosgrid.addColumn(Proyecto::getDescripcion).setHeader("Descripcion");
    	Grid.Column<Proyecto> recaudacioncolumna=Proyectosgrid.addColumn(Proyecto::getRecaudacion).setHeader("Recaudacion");
    	Grid.Column<Proyecto> especialidadescolumna=Proyectosgrid.addColumn(Proyecto::getEspecialidades).setHeader("Especialidades");
    	
    	if(current!=null) {
    		try {
				Proyectosgrid.setItems(proyectos.buscar(current.getUsername()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	}
    	
    	Label idLabel = new Label();
    	ReadOnlyHasValue<Long> idlistener =
		        new ReadOnlyHasValue<>(
		            text -> idLabel.setText(text.toString()));
		binderProyecto.forField(idlistener).bind(Proyecto::getId, null);
    	
    	TextField nombreedit = new TextField();
		binderProyecto.forField(nombreedit).bind(Proyecto::getNombre, Proyecto::setNombre);
		nombrecolumna.setEditorComponent(nombreedit);
		
		TextField descripcionedit = new TextField();
		binderProyecto.forField(descripcionedit).bind(Proyecto::getDescripcion, Proyecto::setDescripcion);
		descripcioncolumna.setEditorComponent(descripcionedit);
		
		TextField recaudacionedit = new TextField();
		binderProyecto.forField(recaudacionedit).bind(Proyecto::getRecaudacion, Proyecto::setRecaudacion);
		recaudacioncolumna.setEditorComponent(recaudacionedit);
		
		TextField especialidadesedit = new TextField();
		binderProyecto.forField(especialidadesedit).bind(Proyecto::getEspecialidades, Proyecto::setEspecialidades);
		especialidadescolumna.setEditorComponent(especialidadesedit);
		
		
		
		Collection<Button> editButtons = Collections
		        .newSetFromMap(new WeakHashMap<>());
		Grid.Column<Proyecto> editorColumn = Proyectosgrid.addComponentColumn(proyecto -> {
		    Button edit = new Button("Editar");
		    edit.addClassName("editar");
		    edit.addClickListener(e -> {
		        editor.editItem(proyecto);
		        
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
					proyectos.updatejson();
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
						
						
							proyectos.borrar(id);
							Notification.show("Prestamo borrado");
						
							
						
					} catch (IOException e1) {
						Notification.show("No se pudo eliminar el prestamo");
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						Proyectosgrid.setItems(proyectos.buscar(current.getUsername()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				});
		cancel.addClassName("cancel");

		// Add a keypress listener that listens for an escape key up event.
		// Note! some browsers return key as Escape and some as Esc
		Proyectosgrid.getElement().addEventListener("keyup", event -> editor.cancel())
		        .setFilter("event.key === 'Escape' || event.key === 'Esc'");

		Div buttons = new Div(save, cancel,delete);
		editorColumn.setEditorComponent(buttons);
		
		
		
		
		FormLayout columnLayout = new FormLayout();
		
		
		
		
		
		TextField nombrein = new TextField();
		nombrein.setPlaceholder("Nombre del proyecto:");
		TextField descripcionin = new TextField();
		descripcionin.setPlaceholder("Descripcion");
		TextField recaudacionin = new TextField();
		recaudacionin.setPlaceholder("Objetivo de recuadacion:");
		TextField especialidadesin = new TextField();
		especialidadesin.setPlaceholder("Perfiles profesionales buscados:");
		
        Button button = new Button("Crear proyecto",
                event ->{ 
                	
                	
                	
                	try {
                		
                			proyectos.crear((current).getUsername(),nombrein.getValue(), descripcionin.getValue(),recaudacionin.getValue(),especialidadesin.getValue());
        					
                			Notification.show("Proyecto añadido");
                		
                		
						
                	} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                	try {
						Proyectosgrid.setItems(proyectos.buscar( current.getUsername() ));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                	
                });
        
        Button prueba = new Button("prueba",
                event ->{ 
                	
                	
                	
                	try {
						Notification.show( Integer.toString(proyectos.buscar("petunio").size()) );
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                	
                });
        
        VerticalLayout proyectoslayout=new VerticalLayout();
        
        columnLayout.add(nombrein,descripcionin,recaudacionin,especialidadesin,button,prueba);
        
        proyectoslayout.add(Proyectosgrid,columnLayout);
        if((current)==null) {
        	H3 notinit=new H3("Inicia sesión para acceder a tus proyectos");
        	add(notinit);
        }else {
        	add(proyectoslayout);
        }
	}

}