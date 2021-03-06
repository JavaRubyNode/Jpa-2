package br.com.vinicius.jpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum JPAUtil {

	INSTANCE;

	private EntityManagerFactory factory;

	private JPAUtil() {
		factory = Persistence.createEntityManagerFactory("Tiradentes");
	}

	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}
