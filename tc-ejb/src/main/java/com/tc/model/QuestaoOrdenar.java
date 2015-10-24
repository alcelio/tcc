package com.tc.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: RespostaOrdenar
 *
 */
/**
 * @author Alcélio
 *
 */
@Entity
@DiscriminatorValue("Ordenar")
public class QuestaoOrdenar extends Questao implements Serializable {
	private static final long serialVersionUID = 1L;

	private String respOrPrimeira;
	private String respOrSegunda;
	private String respOrTerceira;
	private String respOrQuarta;
	private String respOrQuinta;

	public QuestaoOrdenar() {
	}

	public String getRespOrPrimeira() {
		return respOrPrimeira;
	}

	public void setRespOrPrimeira(String respOrPrimeira) {
		this.respOrPrimeira = respOrPrimeira;
	}

	public String getRespOrSegunda() {
		return respOrSegunda;
	}

	public void setRespOrSegunda(String respOrSsegundo) {
		this.respOrSegunda = respOrSsegundo;
	}

	public String getRespOrTerceira() {
		return respOrTerceira;
	}

	public void setRespOrTerceira(String respOrTerceira) {
		this.respOrTerceira = respOrTerceira;
	}

	public String getRespOrQuarta() {
		return respOrQuarta;
	}

	public void setRespOrQuarta(String respOrQuarta) {
		this.respOrQuarta = respOrQuarta;
	}

	public String getRespOrQuinta() {
		return respOrQuinta;
	}

	public void setRespOrQuinta(String respOrQuinta) {
		this.respOrQuinta = respOrQuinta;
	}

}
