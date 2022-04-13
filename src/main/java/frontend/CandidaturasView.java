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

import p2.launchpool.Candidatura;
import p2.launchpool.Candidaturascontroller;


@Route(value = "candidaturasview",layout=MainView.class)
public class CandidaturasView extends VerticalLayout{
	public CandidaturasView() throws Exception{

		
		Candidaturascontroller Candidaturas=Candidaturascontroller.controller();
		Grid<Candidatura> gridCandidaturas=new Grid();
		
		Grid.Column<Candidatura> nombrecolumna=gridCandidaturas.addColumn(Candidatura::getNombre).setHeader("Nombre");
		Grid.Column<Candidatura> especialidadescolumna=gridCandidaturas.addColumn(Candidatura::getEspecialidades).setHeader("Titulos");
		
		Binder<Candidatura> binderCandidaturas=new Binder<>(Candidatura.class,false);
		gridCandidaturas.setSelectionMode(SelectionMode.SINGLE);
		
		
		gridCandidaturas.addSelectionListener(selection -> {
		    Optional<Candidatura> optional = selection.getFirstSelectedItem();
		    if (optional.isPresent()) {
		    	
		    	Dialog dialog = new Dialog();
    			add(dialog);
    			dialog.open();
    			dialog.add(new H2("Detalles de la Candidatura "+Long.toString(optional.get().getId() )+":"));
    			dialog.add("Nombre completo:");
    			dialog.add(new H4(optional.get().getNombre() ));
    			dialog.add("Telefono:");
    			dialog.add(new H4(optional.get().getTlf()));
    			dialog.add("E-mail:");
    			dialog.add(new H4(optional.get().getEmail()));
    			dialog.add("Sobre mi:");
    			dialog.add(new H4(optional.get().getSobremi()));
    			dialog.add("Titulaciones:");
    			dialog.add(new H4(optional.get().getEspecialidades()));
    			
    			
    			
    		 
		    }
		});
		
		Label idLabel = new Label();
		ReadOnlyHasValue<Long> idlistener =
		        new ReadOnlyHasValue<>(
		            text -> idLabel.setText(text.toString()));
		binderCandidaturas.forField(idlistener).bind(Candidatura::getId, null);
		

		

		gridCandidaturas.setItems(Candidaturas.getLista());
		HorizontalLayout layout=new HorizontalLayout();
		gridCandidaturas.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
		add(gridCandidaturas);
		
		
		
	
		
		
	}

}
