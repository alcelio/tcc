package com.tc.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: RespostaObjetiva
 *
 */
@Entity
@DiscriminatorValue("Objetiva")
public class QuestaoObjetiva extends Questao implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean respObjOpcaoA = false;
	private boolean respObjOpcaoB = false;
	private boolean respObjOpcaoC = false;
	private boolean respObjOpcaoD = false;
	private boolean respObjOpcaoE = false;

	public QuestaoObjetiva() {
	}

	public boolean isRespObjOpcaoA() {
		return respObjOpcaoA;
	}

	public void setRespObjOpcaoA(boolean respObjOpcaoA) {
		this.respObjOpcaoA = respObjOpcaoA;
	}

	public boolean isRespObjOpcaoB() {
		return respObjOpcaoB;
	}

	public void setRespObjOpcaoB(boolean respObjOpcaoB) {
		this.respObjOpcaoB = respObjOpcaoB;
	}

	public boolean isRespObjOpcaoC() {
		return respObjOpcaoC;
	}

	public void setRespObjOpcaoC(boolean respObjOpcaoC) {
		this.respObjOpcaoC = respObjOpcaoC;
	}

	public boolean isRespObjOpcaoD() {
		return respObjOpcaoD;
	}

	public void setRespObjOpcaoD(boolean respObjOpcaoD) {
		this.respObjOpcaoD = respObjOpcaoD;
	}

	public boolean isRespObjOpcaoE() {
		return respObjOpcaoE;
	}

	public void setRespObjOpcaoE(boolean respObjOpcaoE) {
		this.respObjOpcaoE = respObjOpcaoE;
	}

}
