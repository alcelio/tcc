package com.tc.data;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.tc.util.CriaCriteria;
import com.tc.model.Usuario;

/**
 * Session Bean implementation class LoginBeanDao
 */
@Stateless
@LocalBean
@Remote
public class LoginBeanDao {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public LoginBeanDao() {
    }
    
    
    
    
	
	public boolean isUsaurioAceito(String usuario, String senha) throws Exception{
		final Session session = em.unwrap(Session.class);
		Object uniqueResult = null;
		
		try {
			final Criteria crit = CriaCriteria.createCriteria(Usuario.class, session);
			crit.add(Restrictions.eq("senha", senha));
			crit.add(Restrictions.eq("login", usuario));
			crit.add(Restrictions.eq("ativo", true));
			
			uniqueResult = crit.uniqueResult();
			
		} catch (final Exception e) {
			throw new Exception("Erro ao efetuar login", e);
		}
		
		return uniqueResult != null;
	}
	
	
	
	
	
	
	
	
	
	
	
	public String getRoleUsuario(String usuario, String senha) throws Exception{
		final Session session = em.unwrap(Session.class);
		Usuario user = null;
		
		try {
			final Criteria crit = CriaCriteria.createCriteria(Usuario.class, session);
			crit.add(Restrictions.eq("senha", senha));
			crit.add(Restrictions.eq("login", usuario));
			crit.add(Restrictions.eq("ativo", true));
			
			user = (Usuario) crit.uniqueResult();
			
		} catch (final Exception e) {
			throw new Exception("Erro ao carregar permissões do usuário.", e);
		}
		//TODO Ajustar o retorno deste metodo conforme necessidade;
		System.out.println(user.getDataNascimento());
		return null;
	}

}
