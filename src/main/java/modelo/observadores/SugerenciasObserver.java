package modelo.observadores;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import modelo.Evento;
import modelo.Usuario;
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Entity
public abstract class SugerenciasObserver {
	@Id@GeneratedValue
	private int id;
	public abstract void notificarSugerenciasNuevas(Usuario usuario,Evento evento);
}
