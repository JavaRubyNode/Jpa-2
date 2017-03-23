package br.com.vinicius.jpa.util.test;

import static org.junit.Assert.*;
import org.junit.Test;

import br.com.vinicius.jpa.base.test.BaseTest;

public class JPAUtilTest extends BaseTest {
	
	@Test
	public void deveTerInstanciaDoEntityManagerDefinida() {
		assertNotNull("instância do EntityManager não deve estar nula", em);
	}
	
	@Test
	public void deveFecharEntityManager() {
		em.close();
		
		assertFalse("instância do EntityManager deve estar fechada", em.isOpen());
	}
	
	@Test
	public void deveAbrirUmaTransacao() {
		assertFalse("transação deve estar fechada", em.getTransaction().isActive());
		
		em.getTransaction().begin();
		
		assertTrue("transação deve estar aberta", em.getTransaction().isActive());
	}
}
