package br.com.vinicius.jpa.modelo.test;

import static org.junit.Assert.*;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.junit.Test;
import br.com.vinicius.jpa.base.test.BaseTest;
import br.com.vinicius.jpa.modelo.Agregado;

public class AgregadoTest extends BaseTest{

	public Agregado instanciar(){return new Agregado().setNome("Vilma Gomes").setParentesco("mãe");}
	private static 	final Logger LOGGER = Logger.getLogger(Agregado.class);
	
	@Test
	@SuppressWarnings("unchecked")
	public void consultarPorIdNome(){
		salvar(3);
		String filter = "mãe";
		Query query = em.createQuery("select new Agregado(a.id,a.nome) from Agregado a where a.parentesco = :nome");
		query.setParameter("nome", filter);
		List<Agregado> lista = query.getResultList();
		assertFalse("verifica se há registros na lista", lista.isEmpty());
		lista.forEach(agreagado->{
            assertNull("verifica que o cpf deve estar null", agreagado.getParentesco());
			agreagado.setParentesco(filter);
		});
		
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void deveConsultarIdENomeAgregado() {
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
	
	@Test
	@SuppressWarnings("unchecked")
	public void consultarNome(){
		salvar(3);
		String filtro = "Gomes";
		Query query = em.createQuery("select a.nome from Agregado a where a.nome like :nome");
		query.setParameter("nome", "%".concat(filtro).concat("%"));
		List<String> listaNome= query.getResultList();
		assertFalse("checar se tem nome na lista",listaNome.isEmpty());
		listaNome.forEach((nome->LOGGER.info("\n\n============== nome: "+nome+"\n\n")));
		
	}
	
	@Test
	public void atualizar(){
		salvar();
		
		TypedQuery<Agregado> query = em.createQuery("SELECT a FROM Agregado a", Agregado.class).setMaxResults(1);
		Agregado agregado = query.getSingleResult();
		assertNotNull("deve ter encontrado um patente", agregado);
		Integer versao = agregado.getVersion();
		em.getTransaction().begin();
		agregado.setNome("Vinicius Gomes");
		agregado = em.merge(agregado);
		em.getTransaction().commit();
		assertNotEquals("deve ter versao incrementada", versao.intValue(), agregado.getVersion().intValue());
	}
	
	@Test
	public void excluir(){
		Criteria criteria = createCriteria(Agregado.class, "a").setProjection(Projections.max("a.id"));
		Long id = (Long) criteria.setResultTransformer(Criteria.PROJECTION).uniqueResult();
		abrirSessao();
		Agregado agregado = em.find(Agregado.class, id);
		em.remove(agregado);
		commitSessao();
		Agregado agregadoExcluido = em.find(Agregado.class, id);
		assertNull("não deve ter encontrado a patente", agregadoExcluido);
		
	}
	
	@Test
	public void alterar(){
		
		Projection projection = Projections.alias(Projections.property("a.id"), "id");
		Criteria criteria = createCriteria(Agregado.class,"a").setProjection(projection).setMaxResults(1);
		Long agregadoIdLong = (Long)criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).uniqueResult();
		assertNotNull("deve ter encontrado um agregado", agregadoIdLong);
		abrirSessao();
		Agregado agregado = new Agregado().setNome("Lucas Oliveira").setParentesco("primo").setId(agregadoIdLong);
		agregado = em.merge(agregado);
		commitSessao();
		
	}
	
	public void salvar(int qtde) {abrirSessao();for (int i = 0; i < qtde; i++) {em.persist(instanciar());}commitSessao();}
	
	public void salvar(){
	assertTrue("não deve ter ID definido", instanciar().isTransient());
	abrirSessao();
	em.persist(instanciar());
	commitSessao();
	assertNotNull("deve ter ID definido", instanciar().isTransient());
	}
	
	public void abrirSessao(){em.getTransaction().begin();}
	public void commitSessao(){em.getTransaction().commit();}

}
