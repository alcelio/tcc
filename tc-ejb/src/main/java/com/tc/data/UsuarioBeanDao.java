package com.tc.data;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.tc.model.Usuario;
import com.tc.util.CriaCriteria;
/**
 * Session Bean implementation class UsuarioBeanDao
 */
@Stateless
@LocalBean
@Remote
public class UsuarioBeanDao implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	public UsuarioBeanDao() {
	}

	public void create(Usuario entidade) throws Exception {
		if(entidade == null){
			throw new Exception("Usuario enviado é inválido.");
		}
		try{
			em.persist(entidade);
		}catch( Exception e) {
			e.printStackTrace();
		}
		
	}

	public void update(Usuario entidade) {
		em.merge(entidade);
	}

	public void remove(Usuario entidade) {
		em.remove(em.getReference(Usuario.class, entidade.getIdUsuario()));
	}

	public List<Usuario> listarUsuarios() {
		return em.createNamedQuery("Usuario.findAll", Usuario.class).getResultList();
	}
	
	public Usuario buscaUsuarioPorLogin(String login) throws Exception{
		final Session session = em.unwrap(Session.class);
		Usuario usuario = null;
		try {
			final Criteria crit = CriaCriteria.createCriteria(Usuario.class, session);
			crit.add(Restrictions.eq("login", login));
			usuario = (Usuario) crit.uniqueResult();
		} catch (final Exception e) {
			throw new Exception("Erro ao carregar dados da tabela.", e);
		}
		return usuario;
    }
	
	public Usuario buscaUsuarioPorId(Integer id) throws Exception{
		final Session session = em.unwrap(Session.class);
		Usuario usuario = null;
		
		try {
			final Criteria crit = CriaCriteria.createCriteria(Usuario.class, session);
			crit.add(Restrictions.eq("idUsuario", id));
			usuario = (Usuario) crit.uniqueResult();
		} catch (final Exception e) {
			throw new Exception("Erro ao carregar dados da tabela.", e);
		}
		return usuario;
    }
	
	public boolean isExisteLogin(String login) throws Exception{
		final Session session = em.unwrap(Session.class);
		Usuario usuario = null;
		try {
			final Criteria crit = CriaCriteria.createCriteria(Usuario.class, session);
			crit.add(Restrictions.eq("login", login));
			usuario = (Usuario) crit.uniqueResult();
			if(usuario != null){
				return true;
			}else{
				return false;
			}
		} catch (final Exception e) {
			throw new Exception("Erro ao carregar dados da tabela.", e);
		}
    } 
}
