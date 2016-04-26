package com.tc.model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

import com.tc.model.Questao;

/**
 * Entity implementation class for Entity: QuestaoDissertativa
 *
 */
@Entity
@DiscriminatorValue("Dissertativa")
public class QuestaoDissertativa extends Questao implements Serializable {
	private static final long serialVersionUID = 1L;

	private String dsRespDissertativa;
	private String dsRespostaAluno;

	public QuestaoDissertativa() {
	}

	public String getDsRespDissertativa() {
		return dsRespDissertativa;
	}

	public void setDsRespDissertativa(String dsRespDissertativa) {
		this.dsRespDissertativa = dsRespDissertativa;
	}

	public String getDsRespostaAluno() {
		return dsRespostaAluno;
	}

	public void setDsRespostaAluno(String dsRespostaAluno) {
		this.dsRespostaAluno = dsRespostaAluno;
	}
	

}
