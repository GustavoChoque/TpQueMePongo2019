package db;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import modelo.Gratuito;
import modelo.TipoDeUsuario;
import modelo.Usuario;



public class ClubTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

  //private RepositorioCancha repositorioCancha;

  /*
  @Before
  public void setUp() {
    repositorioCancha = new RepositorioCancha();
  }

  @Test
  public void alPedirleQueGuardeUnaCanchaPersiste() {
    Cancha cancha = new Cancha("Cancha uno");
    repositorioCancha.registrar(cancha);
    cancha.setNombre("lalalala");

    assertEquals(repositorioCancha.obtenerCancha(cancha.getId()), cancha);
  }

  @Test
  public void guardarUnaCanchaGuardaSuColor() {

    Cancha cancha = new Cancha("Cancha uno", new Color("rojo"));

    repositorioCancha.registrar(cancha);

    assertEquals(repositorioCancha.obtenerCancha(cancha.getId()), cancha);
   
    assertEquals(repositorioCancha.obtenerCancha(cancha.getId()), cancha);
  }

  @Test
  public void testName() throws Exception {
    Cancha cancha = new Cancha("Cancha uno", new Color("rojo"));

    
    Paleta paletaA = new Paleta();
    Paleta paletaB = new Paleta();
    
    Jugador jose = new JugadorAmateur();
    jose.setPaleta(paletaA);
    Jugador maria = new JugadorProfesional();
    jose.setPaleta(paletaB);

    entityManager().persist(jose);
    entityManager().persist(maria);

    cancha.reservar(LocalDateTime.now(),//
        Arrays.asList(jose, maria));
    
    repositorioCancha.registrar(cancha);
    
  }
  
  @Test
  public void testName2() throws Exception {
    List<Jugador> jugadores = entityManager().
      createQuery("from Jugador", Jugador.class).
      getResultList();
  }

  */  
  
  /*
  @Test
  public void testName3() throws Exception {
    List<Color> colores = entityManager().
      createQuery("from Color", Color.class).
      getResultList();
    
    assertEquals("colores en total",colores.size() ,3);
    
  }
*/
  
}
