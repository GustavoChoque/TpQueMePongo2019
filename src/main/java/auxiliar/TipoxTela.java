package auxiliar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import modelo.Tela;
import modelo.TipoDePrenda;
import repositorios.RepositorioTiposDePrenda;
@Entity
public class TipoxTela {
	@Id
	@GeneratedValue
	private int id;
	
	private String tipodeprenda;
	
	@Enumerated(EnumType.STRING)
	private Tela tela;
	
	public TipoxTela(){}
	
	public TipoxTela(String tipo,Tela tela){
		this.tipodeprenda=tipo;
		this.tela=tela;
	}

	public int getId() {
		return id;
	}

	public String getTipodeprenda() {
		return tipodeprenda;
	}

	public Tela getTela() {
		return tela;
	}
	
	/*
	TipoDePrenda tipoPrenda;
	Tela tela;
	public void setTipoPrenda(TipoDePrenda tipoPrenda) {
		this.tipoPrenda = tipoPrenda;
	}
	
	public void setTipoPrenda(String tipoPrenda) {
		//Programa pr=new Programa();
		//pr.cargarTiposDePrendas("src/main/resources/tipos");
		if(RepositorioTiposDePrenda.instance().getTiposPrenda().stream().anyMatch(p->p.getNombre().equals(tipoPrenda))){
			TipoDePrenda t1=RepositorioTiposDePrenda.instance().getTiposPrenda().stream().filter(p->p.getNombre().equals(tipoPrenda)).findFirst().get();
			this.tipoPrenda = t1;
		}else{
			throw new RuntimeException("no existe ese tipo de prenda");
		}
	}
	
	public void setTela(Tela tela) {
		this.tela = tela;
	}
	public void setTela(String tela) {
		this.tela = Tela.valueOf(tela);
	}

	public TipoDePrenda getTipoPrenda() {
		return tipoPrenda;
	}

	public Tela getTela() {
		return tela;
	}
	//los sobre escribi para poder usar el equals, para compara objetos de este tipo
	@Override
	public int hashCode() {
	return tela.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
	TipoxTela txt= (TipoxTela)obj;
	return txt.getTela().equals(this.getTela()) && txt.getTipoPrenda().equals(this.getTipoPrenda());
	}
	
	*/
}
