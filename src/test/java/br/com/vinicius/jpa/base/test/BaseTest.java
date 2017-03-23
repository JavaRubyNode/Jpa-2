package br.com.vinicius.jpa.base.test;


import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;

import br.com.vinicius.jpa.util.JPAUtil;


public abstract class BaseTest {

	protected EntityManager em;
	
	public Session getSession() {
		return (Session) em.getDelegate();
	}
	
	public Criteria createCriteria(Class<?> clazz) {
		return getSession().createCriteria(clazz);
	}
	
	public Criteria createCriteria(Class<?> clazz, String alias) {
		return getSession().createCriteria(clazz, alias);
	}

	@Before
	public void instanciarEntityManager() {
		em = JPAUtil.INSTANCE.getEntityManager();
	}
	
	@After
	public void fecharEntityManager() {
		if (em.isOpen()) {
			em.close();
		}
	}
}
