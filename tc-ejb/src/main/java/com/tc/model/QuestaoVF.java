package com.tc.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Questao VF")
public class QuestaoVF extends Questao implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean respVFopcaoA = false;
	private boolean respVFopcaoB = false;
	private boolean respVFopcaoC = false;
	private boolean respVFopcaoD = false;
	private boolean respVFopcaoE = false;

	public QuestaoVF() {
	}

	public boolean isRespVFopcaoA() {
		return respVFopcaoA;
	}

	public boolean isRespVFopcaoB() {
		return respVFopcaoB;
	}

	public void setRespVFopcaoB(boolean respVFopcaoB) {
		this.respVFopcaoB = respVFopcaoB;
	}

	public boolean isRespVFopcaoC() {
		return respVFopcaoC;
	}

	public void setRespVFopcaoC(boolean respVFopcaoC) {
		this.respVFopcaoC = respVFopcaoC;
	}

	public boolean isRespVFopcaoD() {
		return respVFopcaoD;
	}

	public void setRespVFopcaoD(boolean respVFopcaoD) {
		this.respVFopcaoD = respVFopcaoD;
	}

	public boolean isRespVFopcaoE() {
		return respVFopcaoE;
	}

	public void setRespVFopcaoE(boolean respVFopcaoE) {
		this.respVFopcaoE = respVFopcaoE;
	}

	public void setRespVFopcaoA(boolean respVFopcaoA) {
		this.respVFopcaoA = respVFopcaoA;
	}

}
