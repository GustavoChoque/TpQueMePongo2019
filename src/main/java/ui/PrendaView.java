package ui;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.GroupPanel;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.MainWindow;
import org.uqbar.arena.windows.Window;
import org.uqbar.arena.windows.WindowOwner;

import modelo.Color;
import modelo.Guardaropa;
import modelo.Tela;
import modelo.Usuario;



public class PrendaView extends Dialog<PrendaViewModel>{

	public PrendaView(WindowOwner owner,Usuario usuario,Window<QueMePongoViewModel> ventanaPrincipal) {
		super(owner, new PrendaViewModel(usuario,ventanaPrincipal));
		// TODO Auto-generated constructor stub
		
		
	}

	

	@Override
	protected void createFormPanel(Panel mainPanel) {
		this.setTitle("Prenda");
		
		
		mainPanel.setLayout(new ColumnLayout(2));
		
		
		new Label(mainPanel).setText("");
		new Label(mainPanel).setText("Tipo de Prenda:");
		Selector<Color> listaTiposDePrenda=new Selector<Color>(mainPanel).allowNull(false);
		listaTiposDePrenda.bindItemsToProperty("tipos");
		listaTiposDePrenda.bindValueToProperty("tipoDePrenda");
		
		
		new Label(mainPanel).setText("Color Primario:");
		
		Selector<Color> listaColores=new Selector<Color>(mainPanel).allowNull(false);
		listaColores.bindItemsToProperty("colores2");
		listaColores.bindValueToProperty("colorPrimario");
		
		new Label(mainPanel).setText("Color Segundario:");
		Selector<Color> listaColores2=new Selector<Color>(mainPanel).allowNull(false);
		listaColores2.bindItemsToProperty("colores");
		listaColores2.bindValueToProperty("colorSecundario");
		
		new Label(mainPanel).setText("Tipo de Tela:");
		Selector<Tela> listaTelas=new Selector<Tela>(mainPanel).allowNull(false);
		listaTelas.bindItemsToProperty("telas");
		listaTelas.bindValueToProperty("tela");
		
		new Label(mainPanel).setText("Guardar en:");
		Selector<Guardaropa> listaGuardaropas=new Selector<Guardaropa>(mainPanel).allowNull(false);
		listaGuardaropas.bindItemsToProperty("guardaropasPersonales");
		listaGuardaropas.bindValueToProperty("guardaropa");
		
	}
	
	@Override
	protected void addActions(Panel actions) {
		new Button(actions).setCaption("Crear").onClick(()->this.crearPrenda());
		new Button(actions).setCaption("Cancelar").onClick(this::cancel);
	}
	
	protected void crearPrenda() {
		this.getModelObject().crearPrenda();
		this.showInfo("Mensaje creado satisfactoriamente");
		this.close();
	}

	
}
