package modelo;

import java.util.Arrays;
import java.util.List;

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
