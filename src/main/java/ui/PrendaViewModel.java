package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.uqbar.arena.windows.Dialog;
import org.uqbar.arena.windows.Window;
import org.uqbar.commons.model.annotations.Observable;

import db.EntityManagerHelper;
import modelo.Color;
import modelo.Guardaropa;
import modelo.Prenda;
import modelo.Tela;
import modelo.TipoDePrenda;
import modelo.Usuario;
import repositorios.RepositorioTiposDePrenda;


@Observable
public class PrendaViewModel {
	private Usuario usuario;
	private Window<QueMePongoViewModel> ventanaPrincipal;
	
	private Set<TipoDePrenda> tipos;
	private TipoDePrenda tipoDePrenda;
	
	private List<Color> colores;
	private List<Color> colores2;
	private Color colorPrimario;
	private Color colorSecundario;
	
	private List<Tela> telas; 
	private Tela tela;
	
	private List<Guardaropa> guardaropasPersonales;
	private Guardaropa guardaropa;
	
	
	public PrendaViewModel(Usuario usuario,Window<QueMePongoViewModel> ventanaPrincipal) {
		this.usuario=usuario;
		this.ventanaPrincipal=ventanaPrincipal;
		this.tipos = RepositorioTiposDePrenda.instance()
				.getTiposPrenda()
				.stream()
				.filter(p->!p.getNombre().equals("SinSuperior") && !p.getNombre().equals("SinAccesorio"))
				.collect(Collectors.toSet());
		this.colores = Arrays.asList(Color.values());
		this.colores2=this.colores.stream()
				.filter(c->!c.equals(Color.NINGUNO))
				.collect(Collectors.toList());;
		this.telas = Arrays.asList(Tela.values());
		this.guardaropasPersonales=this.usuario.getGuardaropas();
	}

	
	public void crearPrenda() {
		EntityManagerHelper.beginTransaction();
		this.guardaropa.agregarPrenda(new Prenda(tipoDePrenda, colorPrimario,colorSecundario, tela));
		EntityManagerHelper.commit();
		this.ventanaPrincipal.getModelObject().setCantPrendasTotales(this.ventanaPrincipal.getModelObject().cantidadDePrendasTotales());

	}
	
	public List<Color> getColores() {
		return colores;
	}


	public void setColores(List<Color> colores) {
		this.colores = colores;
	}


	public Color getColorPrimario() {
		return colorPrimario;
	}


	public void setColorPrimario(Color colorPrimario) {
		this.colorPrimario = colorPrimario;
	}


	public Color getColorSecundario() {
		return colorSecundario;
	}


	public void setColorSecundario(Color colorSecundario) {
		this.colorSecundario = colorSecundario;
	}


	public List<Tela> getTelas() {
		return telas;
	}


	public void setTelas(List<Tela> telas) {
		this.telas = telas;
	}


	public Tela getTela() {
		return tela;
	}


	public void setTela(Tela tela) {
		this.tela = tela;
	}


	public Set<TipoDePrenda> getTipos() {
		return tipos;
	}


	public void setTipos(Set<TipoDePrenda> tipos) {
		this.tipos = tipos;
	}


	public TipoDePrenda getTipoDePrenda() {
		return tipoDePrenda;
	}


	public void setTipoDePrenda(TipoDePrenda tipoDePrenda) {
		this.tipoDePrenda = tipoDePrenda;
	}


	public List<Guardaropa> getGuardaropasPersonales() {
		return guardaropasPersonales;
	}


	public void setGuardaropasPersonales(List<Guardaropa> guardaropasPersonales) {
		this.guardaropasPersonales = guardaropasPersonales;
	}


	public Guardaropa getGuardaropa() {
		return guardaropa;
	}


	public void setGuardaropa(Guardaropa guardaropa) {
		this.guardaropa = guardaropa;
	}


	public List<Color> getColores2() {
		return colores2;
	}


	public void setColores2(List<Color> colores2) {
		this.colores2 = colores2;
	}

	
	
	
}
