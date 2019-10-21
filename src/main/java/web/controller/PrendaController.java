package web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

public class PrendaController {
	
public String agregar(Request req,Response res){
		
		HashMap<String,Object> viewModel=new HashMap();
		
		Set<TipoDePrenda>tipos = RepositorioTiposDePrenda.instance()
				.traerListaDeTiposDePrendaDesdeBD()
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
	
	TipoDePrenda tipo=EntityManagerHelper
			.entityManager()
			.createQuery("FROM TipoDePrenda WHERE id=:idTipoPrenda",TipoDePrenda.class)
			.setParameter("idTipoPrenda", Integer.parseInt(idTipoDePrenda))
			.getResultList()
			.get(0);
	
	Prenda prendaNueva=new Prenda(tipo,Color.valueOf(colorP),Color.valueOf(colorS),Tela.valueOf(tela));
	
	EntityManagerHelper.entityManager().getTransaction().begin();
	getGuardaropaPorId(Integer.parseInt(idGuardaropa)).agregarPrenda(prendaNueva);
	EntityManagerHelper.entityManager().getTransaction().commit();
	
	
	ModelAndView modelAndView=new ModelAndView(viewModel, "/prendas/agregarPrendaResultado.hbs");
	return new HandlebarsTemplateEngine().render(modelAndView);
	}

	private List<Integer> listaDeGuardaropas(String idUser){
		List<Guardaropa> idsGuardaropas=EntityManagerHelper.getEntityManager()
				.createQuery("FROM Guardaropa WHERE id_usuario=:idUser",Guardaropa.class)
				.setParameter("idUser", Integer.parseInt(idUser))
				.getResultList();
				
		return idsGuardaropas.stream()
				.map(g->g.getId())
				.collect(Collectors.toList());
	}
	
	public Guardaropa getGuardaropaPorId(int id){
		Guardaropa guardaropa=EntityManagerHelper
				.entityManager()
				.createQuery("FROM Guardaropa WHERE id=:idGua",Guardaropa.class)
				.setParameter("idGua", id)
				.getResultList()
				.get(0);
		return guardaropa;
	}
	
}
