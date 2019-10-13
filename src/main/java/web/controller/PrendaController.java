package web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



import modelo.Categoria;
import modelo.Color;
import modelo.Tela;
import modelo.TipoDePrenda;
import repositorios.RepositorioTiposDePrenda;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class PrendaController {
	
public String agregar(Request req,Response res){
		
		HashMap<String,Object> viewModel=new HashMap();
		
		Set<TipoDePrenda>tipos = RepositorioTiposDePrenda.instance()
				.getTiposPrenda()
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
		//viewModel.put("guardaropas", usuario.getGuardaropas());
		
		ModelAndView modelAndView=new ModelAndView(viewModel, "/prendas/agregarPrenda.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
			
	}
//ejemplo en clase	
/*
public String crear(Request req,Response res){
	
	HashMap<String,Object> viewModel=new HashMap();
	
	Categoria cat=Categoria.valueOf(req.queryParams("Categoria"));
	
	//req.queryParams("id");//asi obtengo el valo del :id en los endpoints
	//hasta que no se ejecuto toda la lambda, hace un rollback
	*/
	
	/*withTransaction(()->{
		
		Prenda p= new Prenda();
		//no conviene usarlo que es mejor delegar a un repo  y que el se encargue
		//em.persist(p);
		repoPrenda.agregar(p);
		
	});*/
	/*
	ModelAndView modelAndView=new ModelAndView(viewModel, "/prendas/agregarPrenda.hbs");
	return new HandlebarsTemplateEngine().render(modelAndView);
	
		
}*/


}
