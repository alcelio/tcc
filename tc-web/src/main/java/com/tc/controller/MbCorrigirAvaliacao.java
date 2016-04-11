package com.tc.controller;

import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static org.apache.commons.lang3.StringUtils.isBlank;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.tc.data.AvaliacaoBeanDao;
import com.tc.model.Avaliacao;

@ManagedBean
@SessionScoped
public class MbCorrigirAvaliacao {

	@EJB
	AvaliacaoBeanDao daoAvaliacao;

	private Avaliacao avaliacao;

	private String caminhoOrigem;

	@PostConstruct
	public void init() {
		setAvaliacao(new Avaliacao());
	}


	public void setaCaminhoOrigem(String origem) {
		this.caminhoOrigem = origem;
	}

	/**
	 * Método que indica para que página deve seguir o programa
	 * 
	 * @return
	 */
	public String goBack() {
		if (isBlank(getCaminhoOrigem())) {
			return PAGINA_HOME;
		} else {
			return getCaminhoOrigem();
		}
	}

	public String getCaminhoOrigem() {
		return caminhoOrigem;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

}
