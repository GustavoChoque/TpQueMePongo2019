package web;


import spark.Spark;
import web.controller.EventoController;
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
		//Login(solo va al inicio)
		Spark.get("/login", (req, res) -> new LoginController().paginaInicio(req, res));
		
		//Prenda
		Spark.get("/prendas/agregarPrenda", (req, res) -> new PrendaController().agregar(req, res));
		//Spark.post("guardaropa/:id/prendas",(req, res) ->new PrendaController().crear(req, res));		
		
		//Evento
		Spark.get("/eventos/agregarEvento", (req, res) -> new EventoController().agregar(req, res));
		
	}
}
