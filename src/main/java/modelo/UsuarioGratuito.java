package modelo;

public class UsuarioGratuito extends Usuario  {
	int guardaropasMax;
	
	public UsuarioGratuito(int guardaropasMax) {
		super();
		this.guardaropasMax = guardaropasMax;
	}
		
	@Override
	public void agregarGuardaropa(Guardaropa guardaropa) {
		if(this.puedeAgregarGuardaropa()) {
		guardaropas.add(guardaropa);
		}
		else {
			System.out.println("Se ha llegado a la maxima cantidad de guardaropas para usuario gratuito");
		}
	}
	public boolean puedeAgregarGuardaropa() {
		return this.guardaropas.size()>guardaropasMax;
	}
}
