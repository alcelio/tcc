package com.tc.data;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.tc.model.Aluno;
import com.tc.model.AlunosTurma;
import com.tc.model.Avaliacao;
import com.tc.model.QuestoesAvaliacao;
import com.tc.model.Respostas;
import com.tc.model.PK.RespostaPK;
import com.tc.util.CriaCriteria;

/**
 * Session Bean implementation class UsuarioBeanDao
 */

@Stateless
@LocalBean
@Remote
public class RespostasBeanDao implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;
	
	@EJB
	AvaliacaoBeanDao daoAvaliacao;
	@EJB
	QuestaoBeanDao daoQuestao;
	@EJB
	UsuarioBeanDao daoUsuario;
	
    
	public void update(Respostas entidade){
    	em.merge(entidade);
    }
	

	public RespostasBeanDao() {
	}
	
	public void incluirRespostasParaTurma(List<AlunosTurma> alunosTurmas, Avaliacao entidade) throws Exception{
		Avaliacao avaliacao = daoAvaliacao.buscaPorId(entidade.getIdAvaliacao());
		Respostas resp;
		RespostaPK pk;
		for (AlunosTurma alunosTurma : alunosTurmas) {
			Aluno aluno = (Aluno) daoUsuario.buscaUsuarioPorId(alunosTurma.getAluno().getIdUsuario());
			for (QuestoesAvaliacao questaoAvaliacao : avaliacao.getQuestoesAvaliacao()) {
				resp = new Respostas();
				pk = new RespostaPK();
				pk.setIdAluno(aluno.getIdUsuario());
				pk.setIdAvaliacao(questaoAvaliacao.getId().getIdAvaliacao());
				pk.setIdQuestao(questaoAvaliacao.getId().getIdQuestao());
				resp.setId(pk);
				resp.setQuestao(questaoAvaliacao.getQuestao());
				resp.setAvaliacao(questaoAvaliacao.getAvaliacao());
				resp.setAluno(aluno);
				em.persist(resp);
			}
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<Respostas> buscaRespostasParaAvalaicaoAluno(Aluno aluno, Avaliacao avaliacao) throws Exception {
		final Session session = em.unwrap(Session.class);
		try {
			final Criteria crit = CriaCriteria.createCriteria(Respostas.class, session);
			crit.add(Restrictions.eq("aluno", aluno)).add(Restrictions.eq("avaliacao", avaliacao));
			return crit.list();
		} catch (final Exception e) {
			throw new Exception("Erro ao carregar dados da tabela.", e);
		}
	}

}
