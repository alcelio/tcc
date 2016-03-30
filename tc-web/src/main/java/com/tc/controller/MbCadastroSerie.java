package com.tc.controller;

import static com.tc.util.IavaliarGlobal.PAGINA_CADASTRO_SERIE;
import static com.tc.util.IavaliarGlobal.PAGINA_HOME;
import static org.apache.commons.lang3.StringUtils.isBlank;

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
	/**
	 * Método que orienta qual pagina deverá ser retornada
	 * @return
	 */
	public String goBack(){
		if(isBlank(getCamingoOrigem())){
			return PAGINA_HOME;
		}
		return getCamingoOrigem();
	}
	
	/**
	 * Método que retorna uma nova página para cadastrar as séries
	 * @return
	 */
	public String novaSerie() {
		setSerie(new Serie());
		return PAGINA_CADASTRO_SERIE;
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
		novaSerie();
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

	public void deletar() {
		dao.remove(serie);
		novaSerie();
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

	public void setaCaminhoOrigem(String caminho){
		this.camingoOrigem = caminho;
	}

}
