package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Prenda {

	public Tela tela;
	public TipoDePrenda tipo;
	public Color colorPrimario;
	public Color colorSecundario;
	
	
	public Prenda(TipoDePrenda tipo, Tela tel, Color primario) {
		this.tipo = Objects.requireNonNull(tipo,"Debe especificar la tipo");
		Objects.requireNonNull(tel, "Debe especificar la tela");
		this.verificarTela(tipo, tel);
		this.colorPrimario = Objects.requireNonNull(primario, "El color es obligatorio");
	}
	public void setColorSecundario(Color colour) {
		if(!this.colorPrimario.equals(colour)){
		this.colorSecundario = colour;
		}else{
			throw new RuntimeException("El color Secundario es igual al Color Primario ");
		}
	}
	
	public Categoria getCategortia(){
		
		return this.tipo.getCategoria();
	}
	public String getTipoDePrenda(){
		
		return this.tipo.getNombre();
	}
	public void verificarTela(TipoDePrenda tipo, Tela telita) {
		List<Tela> telasPrendasSuperiores = new ArrayList<Tela>();
		List<Tela> telasPrendasInferiores = new ArrayList<Tela>();
		List<Tela> telasCalzados = new ArrayList<Tela>();
		telasCalzados.add(Tela.ALGODON);
		telasCalzados.add(Tela.CUERO);
		telasPrendasSuperiores.add(Tela.ALGODON);
		telasPrendasSuperiores.add(Tela.SEDA);
		telasPrendasInferiores.add(Tela.ALGODON);
		telasPrendasInferiores.add(Tela.SEDA);
		
		if (tipo.equals(TipoDePrenda.Zapato) && telasCalzados.contains(telita) ) this.tela=telita;
		if (tipo.equals(TipoDePrenda.Ojotas) && telasCalzados.contains(telita)) this.tela=telita;
		if (tipo.equals(TipoDePrenda.Camisa) && telasPrendasSuperiores.contains(telita)) this.tela=telita;
		if (tipo.equals(TipoDePrenda.Remera) &&telasPrendasSuperiores.contains(telita)) this.tela=telita;
		if (tipo.equals(TipoDePrenda.Pantalon)&& telasPrendasInferiores.contains(telita)) this.tela=telita;
		if (tipo.equals(TipoDePrenda.Short) &&telasPrendasInferiores.contains(telita)) this.tela=telita;
		throw new RuntimeException("El tipo de tela no exite para esta prenda");
		
		

	}
}
