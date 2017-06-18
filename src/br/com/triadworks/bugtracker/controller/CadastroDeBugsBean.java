package br.com.triadworks.bugtracker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.com.triadworks.bugtracker.dao.BugDao;
import br.com.triadworks.bugtracker.modelo.Bug;
import br.com.triadworks.bugtracker.modelo.Usuario;

@ManagedBean
public class CadastroDeBugsBean {
	@ManagedProperty("#{bugDao}")
	private BugDao dao;
	
	private Bug bug = new Bug();
	private List<Bug> bugs = new ArrayList<Bug>();

	
	@PostConstruct
	public void init() {
		this.bugs = dao.lista();
		this.bug.setResponsavel(new Usuario());
	}
	
	/**
	 * Salva um bug no banco de dados
	 */
	public String salva() {
		dao.salva(bug);
		this.bug = new Bug();
		return "bugs?faces-redirect=true";
	}
	
	public String remove(Bug bug) {
		dao.remove(bug);
		return "bugs?faces-redirect=true";
	}

	public Bug getBug() {
		return bug;
	}
	public List<Bug> getBugs() {
		return bugs;
	}
	public void setDao(BugDao dao) {
		this.dao = dao;
	}
	
}
