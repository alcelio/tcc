package com.tc.agentes.comportamentos;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import com.tc.data.QuestaoBeanDao;
import com.tc.data.QuestoesAvaliacaoBeanDao;
import com.tc.data.RespostasBeanDao;
import com.tc.model.Questao;
import com.tc.model.QuestaoObjetiva;
import com.tc.model.QuestaoOrdenar;
import com.tc.model.QuestaoRelacionar;
import com.tc.model.QuestaoVF;
import com.tc.model.QuestoesAvaliacao;
import com.tc.model.Respostas;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;


public class ComportamentoCorrigirRespostas extends OneShotBehaviour {
	private static final long serialVersionUID = 1L;

	@EJB
	RespostasBeanDao daoRespostas;
	@EJB
	QuestaoBeanDao daoQuestao;
	@EJB
	QuestoesAvaliacaoBeanDao daoQuestoesAvaliacao;
	private Respostas respostas;

	public ComportamentoCorrigirRespostas(Agent a, Respostas respostas) {
		super(a);
		this.respostas = respostas;
	}

	@Override
	public void action() {
		System.out.println("SMA - Iniciando correção da avalição [" 
				+ respostas.getAvaliacao().getIdAvaliacao().toString()
				+ "] aluno:[" + respostas.getAluno().getIdUsuario().toString() + "]");
		
		List<QuestoesAvaliacao> listaQuestoes = respostas.getAvaliacao().getQuestoesAvaliacao();

		for (QuestoesAvaliacao questao : listaQuestoes) {
			try {
				corrigeQuestao(questao.getQuestao(), respostas);
				questao.setCorrigida(true);
				questao.setDtaCorrecao(new Date());
				daoQuestoesAvaliacao.update(questao);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private void corrigeQuestao(Questao questao, Respostas respostas) throws Exception {
		Respostas resposta = respostas;

		// Corrige questao objetiva
		if (questao instanceof QuestaoObjetiva) {
			QuestaoObjetiva q = (QuestaoObjetiva) daoQuestao.buscaPorId(questao.getIdQuestao());

			if (q.isRespObjOpcaoA() == resposta.isRespAlunoOpcaoA()
					&& q.isRespObjOpcaoB() == resposta.isRespAlunoOpcaoB()
					&& q.isRespObjOpcaoC() == resposta.isRespAlunoOpcaoB()
					&& q.isRespObjOpcaoD() == resposta.isRespAlunoOpcaoC()
					&& q.isRespObjOpcaoE() == resposta.isRespAlunoOpcaoD()) {

				resposta.setRespondeuCorretamente(true);
				daoRespostas.update(resposta);
			}
		}

		// Corrige questao V&F
		if (questao instanceof QuestaoVF) {
			QuestaoVF q = (QuestaoVF) daoQuestao.buscaPorId(questao.getIdQuestao());

			if (q.isRespVFopcaoA() == resposta.isRespAlunoOpcaoA() 
					&& q.isRespVFopcaoB() == resposta.isRespAlunoOpcaoB()
					&& q.isRespVFopcaoC() == resposta.isRespAlunoOpcaoC()
					&& q.isRespVFopcaoD() == resposta.isRespAlunoOpcaoD()
					&& q.isRespVFopcaoE() == resposta.isRespAlunoOpcaoE()) {

				resposta.setRespondeuCorretamente(true);
				daoRespostas.update(resposta);
			}
		}

		// Corrige questao ordenar
		if (questao instanceof QuestaoOrdenar) {
			QuestaoOrdenar q = (QuestaoOrdenar) daoQuestao.buscaPorId(questao.getIdQuestao());

			if (q.getRespOrPrimeira() == resposta.getRespOrdemAlunoA()
					&& q.getRespOrSegunda() == resposta.getRespOrdemAlunoB()
					&& q.getRespOrTerceira() == resposta.getRespOrdemAlunoC()
					&& q.getRespOrQuarta() == resposta.getRespOrdemAlunoD()
					&& q.getRespOrQuinta() == resposta.getRespOrdemAlunoE()) {

				resposta.setRespondeuCorretamente(true);
				daoRespostas.update(resposta);
			}
		}
		
		// Corrige questao ordenar
		if (questao instanceof QuestaoOrdenar) {
			QuestaoOrdenar q = (QuestaoOrdenar) daoQuestao.buscaPorId(questao.getIdQuestao());

			if (q.getRespOrPrimeira() == resposta.getRespOrdemAlunoA()
					&& q.getRespOrSegunda() == resposta.getRespOrdemAlunoB()
					&& q.getRespOrTerceira() == resposta.getRespOrdemAlunoC()
					&& q.getRespOrQuarta() == resposta.getRespOrdemAlunoD()
					&& q.getRespOrQuinta() == resposta.getRespOrdemAlunoE()) {

				resposta.setRespondeuCorretamente(true);
				daoRespostas.update(resposta);
			}
		}
		
		// Corrige questao relacionar
		if (questao instanceof QuestaoRelacionar) {
			QuestaoRelacionar q = (QuestaoRelacionar) daoQuestao.buscaPorId(questao.getIdQuestao());

			if (q.getRespRelOpcaoA().equals(resposta.getRespAlunoRelOpcaoA())
					&& q.getRespRelOpcaoB().equals(resposta.getRespAlunoRelOpcaoB())
					&& q.getRespRelOpcaoC().equals(resposta.getRespAlunoRelOpcaoC())
					&& q.getRespRelOpcaoD().equals(resposta.getRespAlunoRelOpcaoD())
					&& q.getRespRelOpcaoE().equals(resposta.getRespAlunoRelOpcaoE())){

				resposta.setRespondeuCorretamente(true);
				daoRespostas.update(resposta);
			}
		}
		
	}

}
