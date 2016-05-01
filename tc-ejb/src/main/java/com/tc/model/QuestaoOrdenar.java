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

	private int respOrPrimeira;
	private int respOrSegunda;
	private int respOrTerceira;
	private int respOrQuarta;
	private int respOrQuinta;

	public QuestaoOrdenar() {
	}

	public int getRespOrPrimeira() {
		return respOrPrimeira;
	}

	public void setRespOrPrimeira(int respOrPrimeira) {
		this.respOrPrimeira = respOrPrimeira;
	}

	public int getRespOrSegunda() {
		return respOrSegunda;
	}

	public void setRespOrSegunda(int respOrSegunda) {
		this.respOrSegunda = respOrSegunda;
	}

	public int getRespOrTerceira() {
		return respOrTerceira;
	}

	public void setRespOrTerceira(int respOrTerceira) {
		this.respOrTerceira = respOrTerceira;
	}

	public int getRespOrQuarta() {
		return respOrQuarta;
	}

	public void setRespOrQuarta(int respOrQuarta) {
		this.respOrQuarta = respOrQuarta;
	}

	public int getRespOrQuinta() {
		return respOrQuinta;
	}

	public void setRespOrQuinta(int respOrQuinta) {
		this.respOrQuinta = respOrQuinta;
	}

}
