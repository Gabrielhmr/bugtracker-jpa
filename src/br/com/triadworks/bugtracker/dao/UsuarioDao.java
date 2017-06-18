package br.com.triadworks.bugtracker.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.triadworks.bugtracker.modelo.Usuario;
import br.com.triadworks.bugtracker.util.JPAUtil;
@Transactional
@Repository
public class UsuarioDao {
	@PersistenceContext
	private EntityManager manager;
	
	public UsuarioDao() {
		this.manager = new JPAUtil().getEntityManager();
	}
	
	public List<Usuario> lista() {
		return manager
				.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}

	public void salva(Usuario usuario) {
			manager.persist(usuario);
	}

	public void atualiza(Usuario usuario) {
			manager.merge(usuario);
	}

	public void remove(Usuario usuario) {
			manager.remove(manager.merge(usuario));
	}

	public Usuario carrega(Integer id) {
		return manager.find(Usuario.class, id);
	}
	
}
