package web;


import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import spark.Spark;
import web.controller.EventoController;
import web.controller.GuardaropaController;
import web.controller.InicioController;
import web.controller.LoginController;
import web.controller.PrendaController;

public class Router {
	static Router _instance;
	private Router(){}
	public static Router instance(){
		if(_instance==null){
			_instance=new Router();
		}
		return _instance;
	}
	public void cofigurar(){
		
		//ruteo dinamico
		//Login
		Spark.get("/login", (req, res) -> new LoginController().mostrarLogin(req, res,""));
		Spark.post("/login",(req, res)-> new LoginController().crear(req, res));
		Spark.get("/logout",(req, res)-> new LoginController().cerrar(req, res));

		Spark.get("/inicio",(req, res) ->new InicioController().mostrar(req, res));
		
		//Prenda
		Spark.get("/prendas/nuevo", (req, res) -> new PrendaController().agregar(req, res));
		//Spark.post("guardaropa/:id/prendas",(req, res) ->new PrendaController().crear(req, res));		
		Spark.post("/guardaropa/prendas",(req, res)-> new PrendaController().crear(req, res));
		
		//Evento
		Spark.get("/eventos/nuevo", (req, res) -> new EventoController().agregar(req, res));
		Spark.post("/eventos", (req, res) -> new EventoController().crear(req, res));
		Spark.get("/eventos/:id", (req, res) -> new EventoController().getById(req, res));
		Spark.get("/eventos",(req, res) -> new EventoController().mostrarEventos(req, res));
		
		Spark.get("/eventos/:idEvento/sugerencias/:idAtuendo", (req, res) -> new EventoController().aceptarSugerencia(req, res));
		
		//Guardaropas
		Spark.get("/guardaropas", (req, res) -> new GuardaropaController().mostrarPrendas(req,res));
		
		
		Spark.after((req,res) -> { 
			   PerThreadEntityManagers.getEntityManager(); 
			   PerThreadEntityManagers.closeEntityManager();
			 });

	}
}
