package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import exceptions.TelaInvalidaException;
import repositorios.RepositorioTipoDePrendaTela;

public class Prenda {

	public Tela tela;
	public TipoDePrenda tipo;
	public Color colorPrimario;
	public Color colorSecundario;
	
	
	public Prenda(TipoDePrenda tipo, Color primario, Tela tel) {
		
		if(RepositorioTipoDePrendaTela.getSinglentonInstance().telaValida(tipo, tel)){	
		this.tipo = tipo;
		this.colorPrimario = Objects.requireNonNull(primario, "El color primario es obligatorio");
		this.tela = tel;
		}else{
			throw new TelaInvalidaException("Tela no valida para el tipo de prenda");
		}
	}

	public Prenda(TipoDePrenda tipo, Color primario, Color secundario, Tela tel) {
		
		if(RepositorioTipoDePrendaTela.getSinglentonInstance().telaValida(tipo, tel)){
			if(!primario.equals(secundario)){
			this.tipo = tipo;
			this.colorPrimario = Objects.requireNonNull(primario, "El color primario es obligatorio");
			this.colorSecundario = secundario;
			this.tela = tel;
			}else{
				throw new RuntimeException("El color Secundario es igual al Color Primario ");
			}
		}else{
			throw new TelaInvalidaException("Tela no valida para el tipo de prenda");
		}
	
	}
	/*
	public Prenda(TipoDePrenda tipo, Tela tel, Color primario) {
		this.tipo = Objects.requireNonNull(tipo,"Debe especificar el tipo de prenda");
		this.colorPrimario = Objects.requireNonNull(primario, "El color primario es obligatorio");
		tipo.validarTela(tel,this);
	}
	*/
	
	public void setColorSecundario(Color colour) {
		if(!this.colorPrimario.equals(colour)){
		this.colorSecundario = colour;
		}else{
			throw new RuntimeException("El color Secundario es igual al Color Primario ");
		}
	}
	
	public Categoria getCategoria(){
		
		return this.tipo.getCategoria();
	}
	public String getTipoDePrenda(){
		
		return this.tipo.getNombre();
	}
	
	/*public void setTela(Tela telita) {
		this.tela = telita;
	}*/

		
	
}
