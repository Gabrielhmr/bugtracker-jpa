package br.com.triadworks.bugtracker.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.triadworks.bugtracker.modelo.Bug;

@Controller
@Scope("request")
public class CacheBean {
	
	@PersistenceContext
	EntityManager entityManager;

	private Bug bug;
	private Integer id;
	
	/**
	 * Testando cache de primeiro nível
	 */
	public void pesquisa() {
		this.bug = this.entityManager.find(Bug.class, id);
		this.bug = this.entityManager.find(Bug.class, id);
	}

	public Bug getBug() {
		return bug;
	}
	public void setBug(Bug bug) {
		this.bug = bug;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
