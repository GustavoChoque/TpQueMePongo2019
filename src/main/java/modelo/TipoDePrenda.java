package modelo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import exceptions.TelaInvalidaException;

public class TipoDePrenda{
	
	Categoria categoria;
	String nombre;
	List<Tela> telasPermitidas;
	

	
	public TipoDePrenda(Categoria cat,String name, List<Tela> telasPermitida) {
		this.categoria = cat;
		this.nombre=name;
		this.telasPermitidas=telasPermitida;
	}
	
	public void validarTela(Tela telita,Prenda prenda) {
		Objects.requireNonNull(telita,"Debe especificar el tipo de tela");
		if(this.verificarTela(telita)) {
			prenda.setTela(telita);
		}else {
			throw new TelaInvalidaException("La tela no es valida para esta prenda");
		}
	}
	
	public Boolean verificarTela(Tela telita) {
		return this.telasPermitidas.contains(telita);
		
	}

	
	// Prendas conocidas:
	
	public Categoria getCategoria() {
		return categoria;
	}

	public String getNombre() {
		return nombre;
	}
	
}
