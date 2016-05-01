package com.tc.model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

import com.tc.model.Questao;

/**
 * Entity implementation class for Entity: QuestaoRelacionar
 *
 */
@Entity
@DiscriminatorValue("Relacionar")
public class QuestaoRelacionar extends Questao implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column(length= 1000)
	private String respRelOpcaoA;
	@Column(length= 1000)
	private String respRelOpcaoB;
	@Column(length= 1000)
	private String respRelOpcaoC;
	@Column(length= 1000)
	private String respRelOpcaoD;
	@Column(length= 1000)
	private String respRelOpcaoE;

	public QuestaoRelacionar() {
	}

	public String getRespRelOpcaoA() {
		return this.respRelOpcaoA;
	}

	public void setRespRelOpcaoA(String respRelOpcaoA) {
		this.respRelOpcaoA = respRelOpcaoA;
	}

	public String getRespRelOpcaoB() {
		return this.respRelOpcaoB;
	}

	public void setRespRelOpcaoB(String respRelOpcaoB) {
		this.respRelOpcaoB = respRelOpcaoB;
	}

	public String getRespRelOpcaoC() {
		return this.respRelOpcaoC;
	}

	public void setRespRelOpcaoC(String respRelOpcaoC) {
		this.respRelOpcaoC = respRelOpcaoC;
	}

	public String getRespRelOpcaoD() {
		return this.respRelOpcaoD;
	}

	public void setRespRelOpcaoD(String respRelOpcaoD) {
		this.respRelOpcaoD = respRelOpcaoD;
	}

	public String getRespRelOpcaoE() {
		return this.respRelOpcaoE;
	}

	public void setRespRelOpcaoE(String respRelOpcaoE) {
		this.respRelOpcaoE = respRelOpcaoE;
	}

}
