package br.com.vinicius.jpa.modelo.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Test;
import br.com.vinicius.jpa.base.test.BaseTest;
import br.com.vinicius.jpa.modelo.Patente;
import br.com.vinicius.jpa.util.JPAUtil;

public class PatenteTest extends BaseTest{

	private static final Logger LOGGER = Logger.getLogger(Patente.class);
	
	public Patente instanciar(){return new Patente().setNome("Coronel").setSigla("CR");}
	
	
	
	@Test
	public void salvarPatente() {
        assertTrue("não deve ter ID definido", instanciar().isTransient());
		em.getTransaction().begin();
		em.persist(instanciar());
		em.getTransaction().commit();
		assertNotNull("deve ter ID definido", instanciar().isTransient());
	}
	
	@Test
	public void pesquisarPatente(){
		for (int i = 0; i < 10; i++) {salvarPatente();}
		
		TypedQuery<Patente> query = em.createQuery("SELECT p FROM Patente p", Patente.class);
		List<Patente> patentes = query.getResultList();
		assertFalse("deve ter encontrado uma patente", patentes.isEmpty());
		assertTrue("deve ter encontrado várias patentes", patentes.size() >= 10);
	}
	
	@Test
	public void atualizarPatente(){
		salvarPatente();
		
		
        TypedQuery<Patente> query = em.createQuery("SELECT p FROM Patente p", Patente.class).setMaxResults(1);
		Patente patente = query.getSingleResult();
		assertNotNull("deve ter encontrado um patente", patente);
		Integer versao = patente.getVersion();
		em.getTransaction().begin();
		patente.setNome("Tenente");
		patente = em.merge(patente);
		em.getTransaction().commit();
		assertNotEquals("deve ter versao incrementada", versao.intValue(), patente.getVersion().intValue());
	}
	
	
	@Test
	public void excluirPatente(){
		salvarPatente();
		TypedQuery<Long> query = em.createQuery("SELECT MAX(p.id) FROM Patente p", Long.class);
		Long id =query.getSingleResult();
        em.getTransaction().begin();
		Patente patente = em.find(Patente.class, id);
		em.remove(patente);
		em.getTransaction().commit();
		Patente patenteExcluida = em.find(Patente.class, id);
		assertNull("não deve ter encontrado a patente", patenteExcluida);
	}
	
	
	
	@AfterClass
	public static void deveLimparBaseTeste() {
		EntityManager entityManager = JPAUtil.INSTANCE.getEntityManager();
		entityManager.getTransaction().begin();
		Query query = entityManager.createQuery("DELETE FROM Patente p");
		int qtdRegistrosExcluidos = query.executeUpdate();
		entityManager.getTransaction().commit();
		assertTrue("certifica que a base foi limpada", qtdRegistrosExcluidos > 0);
	}

}
