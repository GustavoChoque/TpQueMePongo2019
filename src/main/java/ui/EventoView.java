package ui;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.Widget;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import auxiliar.Frecuencia;
import modelo.Guardaropa;
import modelo.Usuario;

public class EventoView extends Dialog<EventoViewModel>  {


	public EventoView(WindowOwner owner,Usuario usuario) {
		super(owner, new EventoViewModel(usuario));
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Evento");
		mainPanel.setLayout(new ColumnLayout(2));
		new Label(mainPanel).setText("");
		
		new Label(mainPanel).setText("Nombre:");
		new TextBox(mainPanel).bindValueToProperty("nombre");
		
		new Label(mainPanel).setText("Fecha(dd/mm/aaaa):");
		new TextBox(mainPanel).bindValueToProperty("fecha");
		
		new Label(mainPanel).setText("Guardaropa:");
		Selector<Guardaropa> listaGuardaropas=new Selector<Guardaropa>(mainPanel).allowNull(false);
		listaGuardaropas.bindItemsToProperty("guardaropasPersonales");
		listaGuardaropas.bindValueToProperty("guardaropa");
		
		new Label(mainPanel).setText("Frecuencia:");
		Selector<Frecuencia> listaFrecuencias=new Selector<Frecuencia>(mainPanel).allowNull(false);
		listaFrecuencias.bindItemsToProperty("frecuencias");
		listaFrecuencias.bindValueToProperty("frecuencia");
		
	}
	
	@Override
	protected void addActions(Panel actions) {
		new Button(actions).setCaption("Crear").onClick(()->this.getModelObject().crearEvento());
		new Button(actions).setCaption("Cancelar").onClick(this::cancel);
	}
	
	
}
