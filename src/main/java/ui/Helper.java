package ui;



import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import modelo.Usuario;

public class Helper implements WithGlobalEntityManager, TransactionalOps {

	
	public Usuario getUsuarioPorId(int idUsuario){
		EntityManager em=entityManager();
		Usuario usuario=em
				.createQuery("FROM Usuario WHERE id=:idUsuario",Usuario.class)
				.setParameter("idUsuario", idUsuario)
				.getResultList()
				.get(0);
		
		return usuario;
	}
}
