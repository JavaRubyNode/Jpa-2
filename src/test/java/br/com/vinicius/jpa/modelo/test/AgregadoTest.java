package br.com.vinicius.jpa.modelo.test;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.junit.Test;

import br.com.vinicius.jpa.base.test.BaseTest;
import br.com.vinicius.jpa.modelo.Agregado;

public class AgregadoTest extends BaseTest{

	public Agregado instanciar(){return new Agregado().setNome("Vilma Gomes").setParentesco("mãe");}
	
	@SuppressWarnings("unchecked")
	@Test
	public void deveConsultarIdENomeProduto() {
		salvar(3);
		ProjectionList projection = Projections.projectionList()
				.add(Projections.property("a.id").as("id"))
				.add(Projections.property("a.nome").as("nome"));
		Criteria criteria = createCriteria(Agregado.class, "a").setProjection(projection);
		List<Object[]> agregados = criteria.setResultTransformer(Criteria.PROJECTION).list();
		assertTrue("verifica se a quantidade de agregados é pelo menos 1", agregados.size() >= 1);
		agregados.forEach(agregado -> {
			assertTrue("primeiro item deve ser o ID", 	agregado[0] instanceof Long);
			assertTrue("primeiro item deve ser o nome", agregado[1] instanceof String);
		});
	}
	
	
	
	
	public void salvar(int qtde) {
		em.getTransaction().begin();
    for (int i = 0; i < qtde; i++) {em.persist(instanciar());}
		em.getTransaction().commit();
	}

}
