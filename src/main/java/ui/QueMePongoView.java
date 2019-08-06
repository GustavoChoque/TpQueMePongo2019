package ui;

import org.uqbar.arena.bindings.ObservableProperty;
import org.uqbar.arena.layout.HorizontalLayout;
import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.GroupPanel;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.RadioSelector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.MainWindow;

import com.mchange.v2.cfg.PropertiesConfigSource.Parse;

import modelo.Color;
import modelo.Evento;
import modelo.Gratuito;
import modelo.Guardaropa;
import modelo.Usuario;
import quartz.QuartzSchedulerJobs;



public class QueMePongoView extends MainWindow<QueMePongoViewModel>{
	private Usuario usuario;
	public QueMePongoView(Usuario usuario) {
		super(new QueMePongoViewModel(usuario));
		this.usuario=usuario;
	}

	@Override
	public void createContents(Panel mainPanel) {
		this.setTitle("Esto es Que me Pongo");
		this.setMinHeight(400);
		this.setMinWidth(600);
		
		mainPanel.setLayout(new HorizontalLayout());
		
		GroupPanel subPanel1=new GroupPanel(mainPanel);
		subPanel1.setTitle("Mi Panel");
		subPanel1.setLayout(new VerticalLayout());
		
		GroupPanel subPanel2=new GroupPanel(mainPanel);
		subPanel2.setTitle("Principal");
		subPanel2.setLayout(new VerticalLayout());
		
		new Button(subPanel1)
		.setCaption("Nueva Prenda")
		.onClick(this:: prendaNueva);
		
		
		
		new Button(subPanel1)
		.setCaption("Nuevo Evento")
		.onClick(this:: eventoNuevo);
		new Label(subPanel1).setText("Nº de Guardaropas:");
		new Label(subPanel1).setText(""+getModelObject().cantidaDeGuardaropas());
		new Label(subPanel1).setText("Nº de Prendas:");
		new Label(subPanel1).bindValueToProperty("cantPrendasTotales");
		
		
		//----------------esto hay que mejorarlo para que funcines como notificaciones
		/*for (int x=0;x<10;x++){
			GroupPanel subPanel3=new GroupPanel(subPanel2);
			subPanel3.setTitle("-----"+x);
			subPanel3.setLayout(new VerticalLayout());
			new Label(subPanel3).setText("Evento"+getModelObject().cantidaDeGuardaropas());
			*/
							/*Table tabla=new Table<Evento>(subPanel2,Evento.class);
							tabla.bindItems(new ObservableProperty<>(getModelObject().getUsuario(),"notificaciones"));
						
							new Column<Evento>(tabla)
							.setTitle("Prenda")
							.setFixedSize(400)
							.bindContentsToProperty("sugerencias");
							
							
							new RadioSelector<>(subPanel2).onSelection(this::eventoNuevo)
							.bindItems(new ObservableProperty<>(getModelObject().getUsuario(),"notificaciones"));
							*/
			/*new Column<QueMePongoViewModel>(tabla)
			.setTitle("ACEPTADA");
			new Column<QueMePongoViewModel>(tabla)
			.setTitle("RECHAZADA");*/
		//}
		
		
		
		//new List<Color>(subPanel1).bindItems(new ObservableProperty<>("colores"));
		
		new Button(subPanel2).setCaption("Gestionar").onClick(()->this.gestionarSugerencias());
		new Label(subPanel2).bindValueToProperty("notificacionElegida");
		
		new Label(subPanel2).setText("Eventos con Sugerencias Listas");
		List<Evento>unaLista=new List<Evento>(subPanel2);
		unaLista.setWidth(500);
		unaLista.bindItems(new ObservableProperty<>(getModelObject().getUsuario(),"notificaciones"));
		unaLista.bindValueToProperty("notificacionElegida");
		//unaLista.onSelection(()->this.gestionarSugerencias(getModelObject().getNotificacionElegida()));
		//unaLista.onSelection(this::gestionarSugerencias);
		//-------------
		
	}
	
	public static void main(String[] args) {
		Usuario usuario=new Usuario(new Gratuito());
		usuario.agregarGuardaropa(new Guardaropa());
		
		QuartzSchedulerJobs sche=new QuartzSchedulerJobs();
		
	    new QueMePongoView(usuario).startApplication();
	  }
	
	
	public void prendaNueva(){
		Dialog<?> dialog = new PrendaView(this,this.usuario,this);
		dialog.open();
		//dialog.onAccept(()->{});
		
	}
	
	public void eventoNuevo(){
		Dialog<?> dialog = new EventoView(this,this.usuario);
		dialog.open();
		
		
	}
	public void gestionarSugerencias(){
		Dialog<?> dialog = new SugerenciasView(this,getModelObject().getNotificacionElegida());
		dialog.open();
		
	}
	
}
