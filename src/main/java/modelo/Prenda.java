package modelo;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;

import exceptions.TelaInvalidaException;
import repositorios.RepositorioTipoDePrendaTela;

public class Prenda {

	private TipoDePrenda tipo;
	private Color colorP;
	private Color colorS;
	private Tela tela;
	private String imagen; 
	private boolean habilitado;
	
	

	public Prenda(TipoDePrenda ti, Color c1, Tela te) {
		
		if(RepositorioTipoDePrendaTela.getSinglentonInstance().telaValida(ti, te)){	
		this.tipo = ti;
		this.colorP = Objects.requireNonNull(c1, "El color primario es obligatorio");
		this.tela = te;
		this.habilitado=true;
		}else{
			throw new TelaInvalidaException("Tela no valida para el tipo de prenda");
		}
	}

	public Prenda(TipoDePrenda ti, Color c1, Color c2, Tela te) {
		
		if(RepositorioTipoDePrendaTela.getSinglentonInstance().telaValida(ti, te)){
			if(!c1.equals(c2)){
			this.tipo = ti;
			this.colorP = Objects.requireNonNull(c1, "El color primario es obligatorio");
			this.colorS = c2;
			this.tela = te;
			}else{
				throw new RuntimeException("El color Secundario es igual al Color Primario ");
			}
		}else{
			throw new TelaInvalidaException("Tela no valida para el tipo de prenda");
		}
	}
	
	public void setColorSecundario(Color colour) {
		if(!this.colorP.equals(colour)){
		this.colorS = colour;
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
	
	public int getCapa() {
		return this.tipo.getCapa();
	}
	
	//Luego podria combinar con el tipode tela para el nivel final
		public int getNivelDeAbrigo(){
			return this.tipo.getNivelDeAbrigo();
		}
	
	
	public void cargarImagen(String path){
		//talvez tambien deberia agregar el nombre del usuario al nombre de la imagen
		String destinoPath="imagenes/"+this.getTipoDePrenda()+this.getCategoria()+".jpg";
		escalarYCopiar(path, destinoPath, 128, 128);
		this.imagen=destinoPath;
	}
	
	public void escalarYCopiar(String origenPath,String destinoPath,int escalaAncho,int escalaAlto){
		
		File pathOrigen=new File(origenPath);
		File pathDestino=new File(destinoPath);
		
		try {
			BufferedImage inputImage=ImageIO.read(pathOrigen);
			BufferedImage outputImage=new BufferedImage(escalaAncho, escalaAncho,inputImage.getType());
			
			Graphics2D g2d=outputImage.createGraphics();
			g2d.drawImage(inputImage, 0, 0,escalaAncho ,escalaAlto,null);
			g2d.dispose();
			
			ImageIO.write(outputImage,"jpg", pathDestino);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	

	public String getImagen() {
		return imagen;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		if(tipo.getNombre().equals("SinAccesorio"))
			this.habilitado = true;
		else
			this.habilitado = habilitado;
	}

	@Override
	public boolean equals(Object obj) {
		Prenda prenda=(Prenda)obj;
		if(prenda.getColorS()!=null && this.getColorS()!=null){
		return prenda.getTipo().equals(this.getTipo()) 
				&& prenda.getTela().equals(this.getTela())
				&& prenda.getColorP().equals(this.getColorP())
				&& prenda.getColorS().equals(this.getColorS());
		}else if(prenda.getColorS()==null && this.getColorS()==null){
			return prenda.getTipo().equals(this.getTipo()) 
					&& prenda.getTela().equals(this.getTela())
					&& prenda.getColorP().equals(this.getColorP());
				
		}else{
			return false;
		}
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		return "("+tipo + "," + colorP + "," + colorS + "," + tela + ")";
	}
	
	
	public TipoDePrenda getTipo() {
		return tipo;
	}

	public Color getColorP() {
		return colorP;
	}

	public Color getColorS() {
		return colorS;
	}

	public Tela getTela() {
		return tela;
	}

		
	
}
