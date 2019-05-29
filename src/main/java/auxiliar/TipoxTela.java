package auxiliar;


import modelo.Tela;
import modelo.TipoDePrenda;

import repositorios.RepositorioTiposDePrenda;

public class TipoxTela {
	TipoDePrenda tipoPrenda;
	Tela tela;
	
	public void setTipoPrenda(TipoDePrenda tipoPrenda) {
		this.tipoPrenda = tipoPrenda;
	}
	
	public void setTipoPrenda(String tipoPrenda) {
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
	
}
