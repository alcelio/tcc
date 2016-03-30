package com.tc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: TopicoEstudo
 *
 */
@Entity
@NamedQuery(name = "TopicoEstudo.findAll", query = "SELECT d FROM TopicoEstudo d")
public class TopicoEstudo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer idTopicoEstudo;
	private String dsTopicoEstudo;

	@ManyToOne
	@JoinColumn(name = "idDisciplina")
	private Disciplina disciplina;

	@OneToMany(mappedBy = "topicoEstudo")
	private List<Questao> questoes;

	public TopicoEstudo() {
		disciplina = new Disciplina();
		questoes = new ArrayList<Questao>();
	}

	public Integer getIdTopicoEstudo() {
		return idTopicoEstudo;
	}

	public void setIdTopicoEstudo(Integer idTopicoEstudo) {
		this.idTopicoEstudo = idTopicoEstudo;
	}

	public String getDsTopicoEstudo() {
		return dsTopicoEstudo;
	}

	public void setDsTopicoEstudo(String dsTopicoEstudo) {
		this.dsTopicoEstudo = dsTopicoEstudo;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Questao> getQuestoes() {
		return questoes;
	}

	public void setQuestoes(List<Questao> questoes) {
		this.questoes = questoes;
	}

	@Override
	public String toString() {
		return dsTopicoEstudo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((disciplina == null) ? 0 : disciplina.hashCode());
		result = prime * result + ((dsTopicoEstudo == null) ? 0 : dsTopicoEstudo.hashCode());
		result = prime * result + ((idTopicoEstudo == null) ? 0 : idTopicoEstudo.hashCode());
		result = prime * result + ((questoes == null) ? 0 : questoes.hashCode());
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
		TopicoEstudo other = (TopicoEstudo) obj;
		if (disciplina == null) {
			if (other.disciplina != null)
				return false;
		} else if (!disciplina.equals(other.disciplina))
			return false;
		if (dsTopicoEstudo == null) {
			if (other.dsTopicoEstudo != null)
				return false;
		} else if (!dsTopicoEstudo.equals(other.dsTopicoEstudo))
			return false;
		if (idTopicoEstudo == null) {
			if (other.idTopicoEstudo != null)
				return false;
		} else if (!idTopicoEstudo.equals(other.idTopicoEstudo))
			return false;
		if (questoes == null) {
			if (other.questoes != null)
				return false;
		} else if (!questoes.equals(other.questoes))
			return false;
		return true;
	}

}
