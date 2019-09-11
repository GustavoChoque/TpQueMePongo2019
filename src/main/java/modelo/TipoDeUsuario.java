package modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Entity
public abstract class TipoDeUsuario {
	@Id@GeneratedValue
	private int id;
	public abstract List<Prenda> agregarPrenda(List<Prenda> listaPrendas,Prenda prenda);
}
