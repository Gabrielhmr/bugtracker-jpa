package br.com.triadworks.bugtracker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.com.triadworks.bugtracker.dao.BugDao;
import br.com.triadworks.bugtracker.modelo.Bug;
import br.com.triadworks.bugtracker.modelo.BugPorMes;
import br.com.triadworks.bugtracker.modelo.Comentario;
import br.com.triadworks.bugtracker.modelo.Status;
import br.com.triadworks.bugtracker.modelo.Usuario;

@ManagedBean
public class BugsDoUsuarioBean {

	@ManagedProperty("#{bugDao}")
	private BugDao dao;
	
	private Usuario usuario = new Usuario();
	private List<Bug> bugs = new ArrayList<Bug>();
	private Status status;
	private Long totalDeBugs;
	private Bug bug = new Bug();
	private List<Comentario> comentarios = new ArrayList<Comentario>();
	private List<BugPorMes> bugsPorMes = new ArrayList<BugPorMes>();
	
	/**
	 * Busca bugs do usuário
	 */
	public void pesquisaPorUsuario() {
		this.bugs = dao.getBugsDoUsuario(usuario);
		
	}
	
	/**
	 * Busca bugs por usuário e status
	 */
	public void pesquisaPorUsuarioEStatus() {
		this.bugs = dao.buscaBugsPorUsuarioEStatus(usuario, status);
	}
	
	/**
	 * Busca bugs por nome do usuário
	 */
	public void pesquisaPorNomeDoUsuario() {
		this.bugs = dao.buscaBugsPorNomeDoUsuario(usuario.getNome());
	}
	
	/**
	 * Calcula total de bugs por status
	 */
	public void calculaTotalDeBugsPorStatus() {
		
		this.totalDeBugs = dao.getTotalDeBugsPorStatus(status);
	}
	
	/**
	 * Busca bugs com comentário
	 */
	public void pesquisaBugsComComentarios() {
		this.bugs = dao.getBugsComComentarios();
	}
	
	/**
	 * Busca comentários do bug
	 */
	public void pesquisaComentariosDoBug() {
		this.comentarios = dao.getComentariosDoBug(bug);
	}
	
	/**
	 * Calcula quantidade de bugs por mês
	 */
	public void calculaQuantidadeDeBugsPorMes() {
		this.bugsPorMes = dao.getTotalDeBugsPorMes();
	}

	public BugDao getDao() {
		return dao;
	}
	public void setDao(BugDao dao) {
		this.dao = dao;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
	public Long getTotalDeBugs() {
		return totalDeBugs;
	}
	public void setTotalDeBugs(Long totalDeBugs) {
		this.totalDeBugs = totalDeBugs;
	}
	public Bug getBug() {
		return bug;
	}
	public void setBug(Bug bug) {
		this.bug = bug;
	}
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<BugPorMes> getBugsPorMes() {
		return bugsPorMes;
	}

	public void setBugsPorMes(List<BugPorMes> bugsPorMes) {
		this.bugsPorMes = bugsPorMes;
	}
	
	
	
}
