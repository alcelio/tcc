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
	@Column(length= 2000)
	private String dsRespDissertativa;
	
	public QuestaoDissertativa() {
	}

	public String getDsRespDissertativa() {
		return dsRespDissertativa;
	}

	public void setDsRespDissertativa(String dsRespDissertativa) {
		this.dsRespDissertativa = dsRespDissertativa;
	}
}
