package repositorios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import auxiliar.TipoxTela;
import modelo.Tela;
import modelo.TipoDePrenda;


public class RepositorioTipoDePrendaTela {
	
	private static RepositorioTipoDePrendaTela repo;
	
	public Set<TipoxTela> tipoxTelaValidos;
	
	private RepositorioTipoDePrendaTela(){
		tipoxTelaValidos=new HashSet<TipoxTela>();
		cargarTipoxTelaValidos("src/main/resources/tipoxtelas");
		
	}

	public static RepositorioTipoDePrendaTela getSinglentonInstance(){
		if(repo==null){
			repo=new RepositorioTipoDePrendaTela();
		}
		return repo;
	}
	public void cargarTipoxTelaValidos(String path){
		
		FileReader fr;
		try {
			fr = new FileReader(path);
		
		BufferedReader bf=new BufferedReader(fr);
		String linea;
		try {
			while((linea=bf.readLine())!=null){
				StringTokenizer subcadena=new StringTokenizer(linea, ",");
				
				String nombreTipoPrenda=subcadena.nextToken();
				String tela=subcadena.nextToken();
				TipoxTela tipoxTela=new TipoxTela();
				tipoxTela.setTipoPrenda(nombreTipoPrenda);
				tipoxTela.setTela(tela);
				tipoxTelaValidos.add(tipoxTela);
			
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public  Set<TipoxTela> getTipoxTelaValidos() {
	return tipoxTelaValidos;
}

public boolean telaValida(TipoDePrenda tipo,Tela tela){
	TipoxTela tipxtel=new TipoxTela();
	tipxtel.setTipoPrenda(tipo);
	tipxtel.setTela(tela);
	return this.tipoxTelaValidos.contains(tipxtel);
}

}
