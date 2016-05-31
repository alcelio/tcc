package com.tc.agentes.comportamentos;

import com.tc.data.QuestaoBeanDao;
import com.tc.model.Questao;
import com.tc.model.QuestaoObjetiva;
import com.tc.model.QuestaoOrdenar;
import com.tc.model.QuestaoRelacionar;
import com.tc.model.QuestaoVF;
import com.tc.model.Respostas;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class ComportamentoCorrigirQuestao extends OneShotBehaviour {
	private static final long serialVersionUID = 1L;

	Respostas resposta;
	
	QuestaoBeanDao daoQuestao;
	

	
	public ComportamentoCorrigirQuestao(Agent a, Respostas respostas) {
		super(a);
		this.resposta = respostas;
	}

	@Override
	public void action() {
		Questao questao = resposta.getQuestao();
		// Corrige questao objetiva
		if (questao instanceof QuestaoObjetiva) {
			QuestaoObjetiva q;
			try {
				q = (QuestaoObjetiva) daoQuestao.buscaPorId(questao.getIdQuestao());
				if (q.isRespObjOpcaoA() == resposta.isRespAlunoOpcaoA()
						&& q.isRespObjOpcaoB() == resposta.isRespAlunoOpcaoB()
						&& q.isRespObjOpcaoC() == resposta.isRespAlunoOpcaoB()
						&& q.isRespObjOpcaoD() == resposta.isRespAlunoOpcaoC()
						&& q.isRespObjOpcaoE() == resposta.isRespAlunoOpcaoD()) {

					resposta.setRespondeuCorretamente(true);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		// Corrige questao V&F
		if (questao instanceof QuestaoVF) {
			try {
				QuestaoVF q = (QuestaoVF) daoQuestao.buscaPorId(questao.getIdQuestao());
				if (q.isRespVFopcaoA() == resposta.isRespAlunoOpcaoA()
						&& q.isRespVFopcaoB() == resposta.isRespAlunoOpcaoB()
						&& q.isRespVFopcaoC() == resposta.isRespAlunoOpcaoC()
						&& q.isRespVFopcaoD() == resposta.isRespAlunoOpcaoD()
						&& q.isRespVFopcaoE() == resposta.isRespAlunoOpcaoE()) {

					resposta.setRespondeuCorretamente(true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		// Corrige questao ordenar
		if (questao instanceof QuestaoOrdenar) {
			try {
				QuestaoOrdenar q = (QuestaoOrdenar) daoQuestao.buscaPorId(questao.getIdQuestao());
				if (q.getRespOrPrimeira() == resposta.getRespOrdemAlunoA()
						&& q.getRespOrSegunda() == resposta.getRespOrdemAlunoB()
						&& q.getRespOrTerceira() == resposta.getRespOrdemAlunoC()
						&& q.getRespOrQuarta() == resposta.getRespOrdemAlunoD()
						&& q.getRespOrQuinta() == resposta.getRespOrdemAlunoE()) {

					resposta.setRespondeuCorretamente(true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		// Corrige questao ordenar
		if (questao instanceof QuestaoOrdenar) {
			try {
				QuestaoOrdenar q = (QuestaoOrdenar) daoQuestao.buscaPorId(questao.getIdQuestao());
				if (q.getRespOrPrimeira() == resposta.getRespOrdemAlunoA()
						&& q.getRespOrSegunda() == resposta.getRespOrdemAlunoB()
						&& q.getRespOrTerceira() == resposta.getRespOrdemAlunoC()
						&& q.getRespOrQuarta() == resposta.getRespOrdemAlunoD()
						&& q.getRespOrQuinta() == resposta.getRespOrdemAlunoE()) {

					resposta.setRespondeuCorretamente(true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		// Corrige questao relacionar
		if (questao instanceof QuestaoRelacionar) {
			try {
				QuestaoRelacionar q = (QuestaoRelacionar) daoQuestao.buscaPorId(questao.getIdQuestao());
				if (q.getRespRelOpcaoA().equals(resposta.getRespAlunoRelOpcaoA())
						&& q.getRespRelOpcaoB().equals(resposta.getRespAlunoRelOpcaoB())
						&& q.getRespRelOpcaoC().equals(resposta.getRespAlunoRelOpcaoC())
						&& q.getRespRelOpcaoD().equals(resposta.getRespAlunoRelOpcaoD())
						&& q.getRespRelOpcaoE().equals(resposta.getRespAlunoRelOpcaoE())) {

					resposta.setRespondeuCorretamente(true);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		

		
	}
	
	
	
	
	

}
