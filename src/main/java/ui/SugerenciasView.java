package ui;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.List;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.WindowOwner;

import modelo.Atuendo;
import modelo.Evento;

public class SugerenciasView extends Dialog<SugerenciasViewModel> {
	
	public SugerenciasView(WindowOwner owner,Evento eventoElegido) {
		super(owner, new SugerenciasViewModel(eventoElegido));
		
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		
		new Label(mainPanel).setText("Evento:");
		new Label(mainPanel).bindValueToProperty("evento.nombre");
		new Label(mainPanel).setText("Atuendo Elegido:");
		new Label(mainPanel).bindValueToProperty("evento.sugerenciaElegida");
		new Button(mainPanel)
		.setCaption("Aceptar")
		.onClick(()->getModelObject().AceptarSugerencia());
		new Button(mainPanel)
		.setCaption("Rechazar")
		.onClick(()->getModelObject().RechazarSugerencia());
		List<Atuendo> listaSugerencias=new List<Atuendo>(mainPanel);
		listaSugerencias.bindItemsToProperty("evento.sugerencias");
		listaSugerencias.bindValueToProperty("sugerenciaSeleccionada");
	}

}
