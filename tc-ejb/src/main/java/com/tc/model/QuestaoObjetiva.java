package com.tc.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: Questão Objetiva
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (respObjOpcaoA ? 1231 : 1237);
		result = prime * result + (respObjOpcaoB ? 1231 : 1237);
		result = prime * result + (respObjOpcaoC ? 1231 : 1237);
		result = prime * result + (respObjOpcaoD ? 1231 : 1237);
		result = prime * result + (respObjOpcaoE ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestaoObjetiva other = (QuestaoObjetiva) obj;
		if (respObjOpcaoA != other.respObjOpcaoA)
			return false;
		if (respObjOpcaoB != other.respObjOpcaoB)
			return false;
		if (respObjOpcaoC != other.respObjOpcaoC)
			return false;
		if (respObjOpcaoD != other.respObjOpcaoD)
			return false;
		if (respObjOpcaoE != other.respObjOpcaoE)
			return false;
		return true;
	}

	
}
