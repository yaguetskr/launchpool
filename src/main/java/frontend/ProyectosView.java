package frontend;

import java.util.Optional;

import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ReadOnlyHasValue;
import com.vaadin.flow.router.Route;

import p2.launchpool.Proyecto;
import p2.launchpool.Proyectoscontroller;

@Route(value = "proyectosview",layout=MainView.class)
public class ProyectosView extends VerticalLayout{
	public ProyectosView() throws Exception{

		Proyectoscontroller Proyectos=Proyectoscontroller.controller();
		Grid<Proyecto> gridProyectos=new Grid();
		
		Grid.Column<Proyecto> nombrecolumna=gridProyectos.addColumn(Proyecto::getNombre).setHeader("Nombre");
		Grid.Column<Proyecto> recaudacioncolumna=gridProyectos.addColumn(Proyecto::getRecaudacion).setHeader("Objetivo de recaudacion");
		
		Binder<Proyecto> binderProyectos=new Binder<>(Proyecto.class,false);
		gridProyectos.setSelectionMode(SelectionMode.SINGLE);
		
		
		gridProyectos.addSelectionListener(selection -> {
		    Optional<Proyecto> optional = selection.getFirstSelectedItem();
		    if (optional.isPresent()) {
		    	
		    	Dialog dialog = new Dialog();
    			add(dialog);
    			dialog.open();
    			dialog.add(new H2("Detalles del Proyecto "+Long.toString(optional.get().getId() )+":"));
    			dialog.add("Nombre:");
    			dialog.add(new H4(optional.get().getNombre() ));
    			dialog.add("Descripcion:");
    			dialog.add(new H4(optional.get().getDescripcion()));
    			dialog.add("Recaudacion objetivo:");
    			dialog.add(new H4(optional.get().getRecaudacion()));
    			dialog.add("Se buscan:");
    			dialog.add(new H4(optional.get().getEspecialidades()));
    			
    			
    			
    		 
		    }
		});
		
		Label idLabel = new Label();
		ReadOnlyHasValue<Long> idlistener =
		        new ReadOnlyHasValue<>(
		            text -> idLabel.setText(text.toString()));
		binderProyectos.forField(idlistener).bind(Proyecto::getId, null);
		

		

		gridProyectos.setItems(Proyectos.getLista());
		HorizontalLayout layout=new HorizontalLayout();
		gridProyectos.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
		add(gridProyectos);
		
		
	}

}
