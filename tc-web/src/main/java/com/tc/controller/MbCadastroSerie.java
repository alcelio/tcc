package com.tc.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import com.tc.data.SerieBeanDao;
import com.tc.model.Serie;

@ManagedBean
@SessionScoped
public class MbCadastroSerie implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private SerieBeanDao dao;
	private Serie serie = new Serie();
	private List<Serie> series;
	private String camingoOrigem;

	public MbCadastroSerie() {

	}
	public String goBack(){
		return camingoOrigem;
	}
	public void setaCaminhoOrigem(String caminho){
		this.camingoOrigem = caminho;
	}
	
	
	public String limpCidade() {
		serie = new Serie();
		return editSerie();
	}

	public String editSerie() {
		return "admin/cadastroserie.jsf";
	}

	public String limpSerie() {
		serie = new Serie();
		return editSerie();
	}

	public String novaSerie() {
		serie = new Serie();
		return "incluirserie";
	}

	public void addSerie() {
		if (serie.getIdSerie() == null || serie.getIdSerie() == 0) {

			insertSerie();
			if (!StringUtils.isBlank(getCamingoOrigem())) {
				novaSerie();
			}

		} else {
			updateSerie();
		}
		limpSerie();
	}

	private void insertSerie() {
		dao.create(serie);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
	}

	private void updateSerie() {
		dao.update(serie);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Atualização efetuada com sucesso", ""));
	}

	public void deleteSerie() {
		dao.remove(serie);
		editSerie();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro excluido com sucesso", ""));
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public List<Serie> getSeries() {
		series = dao.listarSeries();
		return series;
	}

	public void setSeries(List<Serie> series) {
		this.series = series;
	}

	public String getCamingoOrigem() {
		return camingoOrigem;
	}

	public void setCamingoOrigem(String camingoOrigem) {
		this.camingoOrigem = camingoOrigem;
	}

}
