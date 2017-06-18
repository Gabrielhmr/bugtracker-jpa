package br.com.triadworks.bugtracker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import br.com.triadworks.bugtracker.dao.UsuarioDao;
import br.com.triadworks.bugtracker.modelo.Usuario;

@ManagedBean
public class CadastroDeUsuariosBean {
    @ManagedProperty("#{usuarioDao}")
	private UsuarioDao dao;
	
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	
	@PostConstruct
	public void init() {
		this.usuarios = dao.lista();
	}
	
	/**
	 * Salva um bug no banco de dados
	 */
	public String salva() {
		dao.salva(usuario);
		this.usuario = new Usuario();
		return "usuarios?faces-redirect=true";
	}
	
	public String remove(Usuario usuario) {
		dao.remove(usuario);
		return "usuarios?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setDao(UsuarioDao dao) {
		this.dao = dao;
	}
}
