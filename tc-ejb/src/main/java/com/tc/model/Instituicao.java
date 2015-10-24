package com.tc.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the Instituicao database table.
 * 
 */
@Entity
@NamedQuery(name = "Instituicao.findAll", query = "SELECT i FROM Instituicao i")
public class Instituicao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer idInstituicao;

	private String dsEmail;

	private String dsFone1;

	private String dsFone2;

	private String dsFone3;

	private String dsNomeDiretor;

	private String dsNomeInstituicao;

	private BigInteger idEndereco;

	// bi-directional many-to-one association to Turma
	// @OneToMany(mappedBy="instituicao")
	// private List<Turma> turmas;

	public Instituicao() {
	}

	public Integer getIdInstituicao() {
		return this.idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public String getDsEmail() {
		return this.dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}

	public String getDsFone1() {
		return this.dsFone1;
	}

	public void setDsFone1(String dsFone1) {
		this.dsFone1 = dsFone1;
	}

	public String getDsFone2() {
		return this.dsFone2;
	}

	public void setDsFone2(String dsFone2) {
		this.dsFone2 = dsFone2;
	}

	public String getDsFone3() {
		return this.dsFone3;
	}

	public void setDsFone3(String dsFone3) {
		this.dsFone3 = dsFone3;
	}

	public String getDsNomeDiretor() {
		return this.dsNomeDiretor;
	}

	public void setDsNomeDiretor(String dsNomeDiretor) {
		this.dsNomeDiretor = dsNomeDiretor;
	}

	public String getDsNomeInstituicao() {
		return this.dsNomeInstituicao;
	}

	public void setDsNomeInstituicao(String dsNomeInstituicao) {
		this.dsNomeInstituicao = dsNomeInstituicao;
	}

	public BigInteger getIdEndereco() {
		return this.idEndereco;
	}

	public void setIdEndereco(BigInteger idEndereco) {
		this.idEndereco = idEndereco;
	}

	// public List<Turma> getTurmas() {
	// return this.turmas;
	// }
	//
	// public void setTurmas(List<Turma> turmas) {
	// this.turmas = turmas;
	// }
	//
	// public Turma addTurma(Turma turma) {
	// getTurmas().add(turma);
	// turma.setInstituicao(this);
	//
	// return turma;
	// }
	//
	// public Turma removeTurma(Turma turma) {
	// getTurmas().remove(turma);
	// turma.setInstituicao(null);
	//
	// return turma;
	// }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idInstituicao;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Instituicao other = (Instituicao) obj;
		if (idInstituicao != other.idInstituicao)
			return false;
		return true;
	}

}