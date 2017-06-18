package br.com.triadworks.bugtracker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.com.triadworks.bugtracker.dao.BugDao;
import br.com.triadworks.bugtracker.modelo.Bug;
import br.com.triadworks.bugtracker.modelo.Status;
import br.com.triadworks.bugtracker.modelo.Usuario;

@ManagedBean
public class BugsDoUsuarioComCriteriaBean {

	@ManagedProperty("#{bugDao}")
	private BugDao dao;
	
	private List<Bug> bugs = new ArrayList<Bug>();
	private Status status;
	private String sumario;
	private Usuario usuario = new Usuario();
	
	/**
	 * Lista todos os bugs do sistema
	 */
	public void listaTudo() {
		
		this.bugs = dao.listaComCriteria();
		
	}
	
	/**
	 * Busca bugs por status
	 */
	public void pesquisaPorStatus() {
		this.bugs = dao.buscaBugsPorStatusComCriteria(status);
	}
	
	/**
	 * Busca bugs por sumário e status
	 */
	public void pesquisaPorSumarioEStatus() {
		this.bugs = dao.buscaBugsPorSumarioEStatusComCriteria(sumario, status);
	}
	
	/**
	 * Busca bugs com comentário
	 */
	public void pesquisaBugsComComentarios() {
		this.bugs = dao.getBugsComComentariosComCriteria();
	}
	
	/**
	 * Busca bugs por nome do usuário
	 */
	public void pesquisaPorNomeDoUsuario() {
		this.bugs = dao.buscaBugsPorNomeDoUsuarioComCriteria(usuario.getNome());
	}
	
	/**
	 * Busca bugs por nome ou login do usuário
	 */
	public void pesquisaPorNomeOuLoginDoUsuario() {
		
	}
	
	/**
	 * Calcula quantidade de bugs por usuários
	 */
	public void calculaQuantidadeDeBugsPorUsuario() {
		
	}
	
	public BugDao getDao() {
		return dao;
	}
	public void setDao(BugDao dao) {
		this.dao = dao;
	}
	public List<Bug> getBugs() {
		return bugs;
	}
	public void setBugs(List<Bug> bugs) {
		this.bugs = bugs;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getSumario() {
		return sumario;
	}
	public void setSumario(String sumario) {
		this.sumario = sumario;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
