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

	private Tela tela;
	private TipoDePrenda tipo;
	private Color colorPrimario;
	private Color colorSecundario;
	private String imagen; 
	
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

	
	
	
	
	/*public void setTela(Tela telita) {
		this.tela = telita;
	}*/

		
	
}
