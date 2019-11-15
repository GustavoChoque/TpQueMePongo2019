package web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import db.DatabaseHelper;
import db.EntityManagerHelper;
import modelo.Categoria;
import modelo.Color;
import modelo.Guardaropa;
import modelo.Prenda;
import modelo.Tela;
import modelo.TipoDePrenda;
import modelo.Usuario;
import repositorios.RepositorioTiposDePrenda;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class PrendaController implements WithGlobalEntityManager, TransactionalOps{
	
public String agregar(Request req,Response res){
		
		HashMap<String,Object> viewModel=new HashMap();
		
		Set<TipoDePrenda>tipos = traerListaDeTiposDePrendaDesdeBD()
				.stream()
				.filter(p->!p.getNombre().equals("SinSuperior") && !p.getNombre().equals("SinAccesorio"))
				.collect(Collectors.toSet());
		
		List<Color> colores = Arrays.asList(Color.values());
		List<Color> colores2=colores.stream()
				.filter(c->!c.equals(Color.NINGUNO))
				.collect(Collectors.toList());;
		
		
		viewModel.put("tiposDePrenda", tipos);
		viewModel.put("tiposDeTela", Tela.values());
		viewModel.put("coloresPrimarios", colores2);
		viewModel.put("coloresSecundarios", colores);
		viewModel.put("guardaropas", listaDeGuardaropas(req.cookie("uid")));
		//viewModel.put("guardaropas", usuario.getGuardaropas());
		
		ModelAndView modelAndView=new ModelAndView(viewModel, "/prendas/agregarPrenda.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
		
		
		
		
	}


	public String crear(Request req,Response res){
	
	HashMap<String,Object> viewModel=new HashMap();
	String idTipoDePrenda=req.queryParams("tipoDePrenda");
	String tela=req.queryParams("tela");
	String colorP=req.queryParams("colorP");
	String colorS= req.queryParams("colorS");
	String idGuardaropa=req.queryParams("guardaropa");
	
	EntityManager em=entityManager();
	
	TipoDePrenda tipo=em
			.createQuery("FROM TipoDePrenda WHERE id=:idTipoPrenda",TipoDePrenda.class)
			.setParameter("idTipoPrenda", Integer.parseInt(idTipoDePrenda))
			.getResultList()
			.get(0);
	
	Prenda prendaNueva=new Prenda(tipo,Color.valueOf(colorP),Color.valueOf(colorS),Tela.valueOf(tela));
	
	/*
	EntityManagerHelper.entityManager().getTransaction().begin();
	DatabaseHelper.getGuardaropaPorId(Integer.parseInt(idGuardaropa)).agregarPrenda(prendaNueva);
	EntityManagerHelper.entityManager().getTransaction().commit();
	*/
	
	withTransaction(()->{

		getGuardaropaPorId(Integer.parseInt(idGuardaropa)).agregarPrenda(prendaNueva);

	});
	
	ModelAndView modelAndView=new ModelAndView(viewModel, "/prendas/agregarPrendaResultado.hbs");
	return new HandlebarsTemplateEngine().render(modelAndView);
	}

	
	public List<TipoDePrenda> traerListaDeTiposDePrendaDesdeBD(){
		EntityManager em=entityManager();
		List<TipoDePrenda> tiposDePrendas=em
				.createQuery("FROM TipoDePrenda",TipoDePrenda.class)
				.getResultList();
		return tiposDePrendas;

	}

	public List<Integer> listaDeGuardaropas(String idUser){
		EntityManager em=entityManager();
		List<Guardaropa> idsGuardaropas=em
				.createQuery("FROM Guardaropa WHERE id_usuario=:idUser",Guardaropa.class)
				.setParameter("idUser", Integer.parseInt(idUser))
				.getResultList();

		return idsGuardaropas.stream()
				.map(g->g.getId())
				.collect(Collectors.toList());
	}

	public  Guardaropa getGuardaropaPorId(int id){
		EntityManager em=entityManager();
		Guardaropa guardaropa=em
				.createQuery("FROM Guardaropa WHERE id=:idGua",Guardaropa.class)
				.setParameter("idGua", id)
				.getResultList()
				.get(0);
		return guardaropa;
	}
	
	
}
