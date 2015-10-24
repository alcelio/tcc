package com.tc.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.tc.model.Avaliacao;
import com.tc.model.Usuario;
import com.tc.util.CriaCriteria;

/**
 * Session Bean implementation class EnderecoBeanDao
 */

@Stateless
@LocalBean
@Remote
public class AvaliacaoBeanDao implements Serializable{

	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager em;

	public AvaliacaoBeanDao() {
    }
	
    public void create(Avaliacao entidade){
    	em.persist(entidade);
    }
    
    public void update(Avaliacao entidade){
    	em.merge(entidade);
    }
    public void remove(Avaliacao entidade){
    	em.remove(em.getReference(Avaliacao.class, entidade.getIdAvaliacao()));  
    }
    
    public List<Avaliacao> listarAvaliacoes(){
    	return em.createNamedQuery("Avaliacao.findAll", Avaliacao.class).getResultList();
    }

	public Avaliacao buscaPorId(Integer idAvaliacao) throws Exception {
		final Session session = em.unwrap(Session.class);
		Avaliacao disc = null;
		
		try {
			final Criteria crit = CriaCriteria.createCriteria(Avaliacao.class, session);
			crit.add(Restrictions.eq("idAvaliacao", idAvaliacao));
			
			disc = (Avaliacao) crit.uniqueResult();
			
		} catch (final Exception e) {
			throw new Exception("Erro ao carregar Avaliacao no banco de dados.", e);
		}
		return disc;
	}
	/**
	 *Busca todas a avalição das turms onde o aluno passado como parametro está presente; 
	 */
	public List<Avaliacao> buscaAvaliacoesTurmaAluno(Usuario aluno){
		List<Avaliacao> listaAvaliacoes= new ArrayList<Avaliacao>();
//		Query query = em.createQuery("SELECT e FROM Professor e");
//	    return (Collection<Professor>) query.getResultList();
		StringBuilder sql = new StringBuilder();
		
//		select * from Avaliacao, AlunosTurma where Avaliacao.idTurma = AlunosTurma.idTurma and AlunosTurma.idUsuario=1 ; 
		
//		sql.append("SELECT * FROM AVALIACAO, ALUNOSTURMA ");
//		sql.append("WHERE ");
//		sql.append("AVALIACAO.IDTURMA = ALUNOSTURMA.IDTURMA ");
//		sql.append("AND ");
//		sql.append("ALUNOSTURMA.IDUSUARIO = "+ aluno.getIdUsuario().toString()+";");
//		
		sql.append("from Avaliacao, AlunosTurma ");
		sql.append("where ");
		sql.append("Avaliacao.idTurma = AlunosTurma.idTurma ");
		sql.append("and ");
		sql.append("AlunosTurma.idUsuario = "+ aluno.getIdUsuario().toString());
		//TOSO Fazer este metodo funcionar
//			Query query = (Query) em.createQuery(sql.toString());
		System.out.println();
		return listaAvaliacoes;
	}
    
}
