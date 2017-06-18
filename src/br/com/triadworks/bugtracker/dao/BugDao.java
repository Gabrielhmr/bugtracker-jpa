package br.com.triadworks.bugtracker.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.triadworks.bugtracker.modelo.Bug;
import br.com.triadworks.bugtracker.modelo.BugPorMes;
import br.com.triadworks.bugtracker.modelo.Comentario;
import br.com.triadworks.bugtracker.modelo.Status;
import br.com.triadworks.bugtracker.modelo.Usuario;

@Transactional
@Repository
public class BugDao {
	@PersistenceContext
	private EntityManager manager;
	
	
	public List<Bug> lista() {
		return manager
				.createQuery("from Bug", Bug.class)
				.getResultList();
	}

	public void salva(Bug bug) {
		manager.persist(bug);
	}

	public void atualiza(Bug bug) {
			manager.merge(bug);
	}

	public void remove(Bug bug) {
			manager.remove(manager.merge(bug));
	}

	public Bug busca(Integer id) {
		return manager.find(Bug.class, id);
	}
	
	public List<Bug> getBugsDoUsuario(Usuario responsavel){
		String jpql = "select b from Bug b where b.responsavel = :usuario"
				+ " order by b.criadoEm desc";
		TypedQuery<Bug> query = this.manager.createQuery(jpql,Bug.class);
		query.setParameter("usuario", responsavel);
		
		return query.getResultList();
	}
	
	public List<Bug> buscaBugsPorUsuarioEStatus(Usuario responsavel,Status status){
		String jpql = "select b from Bug b where b.responsavel = :usuario "
				+ "and b.status = :status ";
		TypedQuery<Bug> query = this.manager.createQuery(jpql,Bug.class);
		query.setParameter("usuario", responsavel);
		query.setParameter("status", status);
		
		return query.getResultList();
	}
	
	public List<Bug> buscaBugsPorNomeDoUsuario(String nome){
		String jpql = "select b from Bug b  "
				+ " where b.responsavel.nome like :nome "
				+ " order by b.responsavel.nome asc ";
		TypedQuery<Bug> query = this.manager.createQuery(jpql,Bug.class);
		query.setParameter("nome", "%" + nome + "%");
		
		return query.getResultList();
	}
	
	public Long getTotalDeBugsPorStatus(Status status){
		String jpql = "select count(b) from Bug b  "
				+ " where b.status = :status ";
		TypedQuery<Long> query = this.manager.createQuery(jpql,Long.class);
		query.setParameter("status", status);
		
		return query.getSingleResult();
	}
	
	public List<Bug> getBugsComComentarios(){
		String jpql = "select b from Bug b  "
				+ " where b.comentarios is not empty ";
		TypedQuery<Bug> query = this.manager.createQuery(jpql,Bug.class);
		
		return query.getResultList();
	}
	
	public List<Comentario> getComentariosDoBug(Bug b){
		String jpql = "select comentario from Bug b  "
				+ " join b.comentarios as comentario "
				+ " where b = :bug";
		TypedQuery<Comentario> query = this.manager.createQuery(jpql,Comentario.class);
		query.setParameter("bug", b);
		return query.getResultList();
	}
	
	public List<BugPorMes> getTotalDeBugsPorMes(){
		String jpql = "select new br.com.triadworks.bugtracker.modelo.BugPorMes("
				+" month(b.criadoEm), year(b.criadoEm), count(*))  "
				+ " from Bug b "
				+ " group by year(b.criadoEm), month(b.criadoEm) "
				+ " order by count(*) desc";
		TypedQuery<BugPorMes> query = this.manager.createQuery(jpql,BugPorMes.class);
		
		return query.getResultList();
	}
	
	public List<Bug> listaComCriteria(){
		Session session = this.manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Bug.class);
		
		List<Bug> bugs = criteria.list();
		return bugs;
	}
	
	public List<Bug> buscaBugsPorStatusComCriteria(Status status){
		Session session = this.manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Bug.class);
		
		criteria.add(Restrictions.eq("status", status));
		criteria.addOrder(Order.asc("criadoEm"));
		
		List<Bug> bugs = criteria.list();
		return bugs;
	}
	
	public List<Bug> buscaBugsPorSumarioEStatusComCriteria(String sumario, Status status){
		Session session = this.manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Bug.class);
		
		if (sumario != null) {
			criteria.add(Restrictions.ilike("sumario", sumario, MatchMode.ANYWHERE));
		}
		
		if (status != null) {
			criteria.add(Restrictions.eq("status", status));
		}
		
		criteria.addOrder(Order.desc("criadoEm"));
		
		List<Bug> bugs = criteria.list();
		return bugs;
	}
	
	public List<Bug> getBugsComComentariosComCriteria(){
		Session session = this.manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Bug.class);
		
		
		criteria.add(Restrictions.isNotEmpty("comentarios"));
		
		List<Bug> bugs = criteria.list();
		return bugs;
	}
	
	public List<Bug> buscaBugsPorNomeDoUsuarioComCriteria(String nome){
		Session session = this.manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Bug.class);
		
		
		criteria.createAlias("responsavel", "r");
		criteria.add(Restrictions.like("r.nome", nome, MatchMode.ANYWHERE));
		criteria.addOrder(Order.asc("r.nome"));
		List<Bug> bugs = criteria.list();
		return bugs;
	}
	
}
