package auxiliar;

public enum Frecuencia {
	NINGUNA{public int valor(){return 0;}},
	DIARIA {public int valor(){return 1;}},
	SEMANAL{public int valor(){return 7;}},
	MENSUAL{public int valor(){return 30;}},
	ANUAL{public int valor(){return 365;}};
	
	abstract public int valor();
}
