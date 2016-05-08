package com.tc.suport;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "iavaliarService")
@ApplicationScoped
public class IavaliarService {
	
	 private final static String[] grauDificuldade;
     
	 private final static String[] tipoQuestao;

	 private final static String[] statusAvaliacao;
	 
	 static {
	        grauDificuldade = new String[5];
	        grauDificuldade[0] = "FÁCIL";
	        grauDificuldade[1] = "MODERADO";
	        grauDificuldade[2] = "EXIGENTE";
	        grauDificuldade[3] = "MUITO EXIGENTE";
	        grauDificuldade[4] = "EXTREMA";
	         
	        tipoQuestao = new String[5];
	        tipoQuestao[0] = "DISSERTATIVA";
	        tipoQuestao[1] = "VERDADEIRO OU FALSO";
	        tipoQuestao[2] = "OBJETIVA";
	        tipoQuestao[3] = "RELACAIONAR";
	        tipoQuestao[4] = "ORDENAR";
	        
			 statusAvaliacao = new String[3];
			 statusAvaliacao[0] = "CORRIGIDA";
			 statusAvaliacao[1] = "PENDENTE DE CORREÇÃO";
			 statusAvaliacao[2] = "AGUARDANDO INÍCIO";
	        
	    }
	 
	   public List<String> getStatusAvaliacao() {
	        return Arrays.asList(statusAvaliacao);
	    }
	   public List<String> getGrauDificuldade() {
	        return Arrays.asList(grauDificuldade);
	    }
	     
	    public List<String> getTipoQuestao() {
	        return Arrays.asList(tipoQuestao);
	    }
}
