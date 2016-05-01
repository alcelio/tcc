package com.tc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * Entity implementation class for Entity: Turma
 *
 */
@Entity
@NamedQuery(name = "Turma.findAll", query = "SELECT d FROM Turma d")
public class Turma implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer idTurma;
	private String dsTurma;

	@ManyToOne
	@JoinColumn(name = "idSerie")
	private Serie serie;

	@ManyToOne
	@JoinColumn(name = "idTurno")
	private Turno turno;
	
	@ManyToOne
	@JoinColumn(name = "idInstituicao")
	private Instituicao instituicao;
	
	private Integer anoLetivo;
	
	@ManyToOne
	@JoinColumn(name = "idProfessor")
	private Usuario professor;
	

	public Turma() {
		instituicao = new Instituicao();
		serie = new Serie();
		turno = new Turno();
	}

	public Integer getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Integer idTurma) {
		this.idTurma = idTurma;
	}

	public String getDsTurma() {
		return dsTurma;
	}

	public void setDsTurma(String dsTurma) {
		this.dsTurma = dsTurma;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Integer getAnoLetivo() {
		return anoLetivo;
	}

	public void setAnoLetivo(Integer anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

	public Usuario getProfessor() {
		return professor;
	}

	public void setProfessor(Usuario professor) {
		this.professor = professor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anoLetivo == null) ? 0 : anoLetivo.hashCode());
		result = prime * result + ((dsTurma == null) ? 0 : dsTurma.hashCode());
		result = prime * result + ((idTurma == null) ? 0 : idTurma.hashCode());
		result = prime * result + ((instituicao == null) ? 0 : instituicao.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
		result = prime * result + ((serie == null) ? 0 : serie.hashCode());
		result = prime * result + ((turno == null) ? 0 : turno.hashCode());
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
		Turma other = (Turma) obj;
		if (anoLetivo == null) {
			if (other.anoLetivo != null)
				return false;
		} else if (!anoLetivo.equals(other.anoLetivo))
			return false;
		if (dsTurma == null) {
			if (other.dsTurma != null)
				return false;
		} else if (!dsTurma.equals(other.dsTurma))
			return false;
		if (idTurma == null) {
			if (other.idTurma != null)
				return false;
		} else if (!idTurma.equals(other.idTurma))
			return false;
		if (instituicao == null) {
			if (other.instituicao != null)
				return false;
		} else if (!instituicao.equals(other.instituicao))
			return false;
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
			return false;
		if (serie == null) {
			if (other.serie != null)
				return false;
		} else if (!serie.equals(other.serie))
			return false;
		if (turno == null) {
			if (other.turno != null)
				return false;
		} else if (!turno.equals(other.turno))
			return false;
		return true;
	}

	
}
