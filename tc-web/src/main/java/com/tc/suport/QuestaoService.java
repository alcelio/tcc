package com.tc.suport;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "questaoService")
@ApplicationScoped
public class QuestaoService {
	
	 private final static String[] grauDificuldade;
     
	 private final static String[] tipoQuestao;
	 
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
	      
	    }
	 
	   public List<String> getGrauDificuldade() {
	        return Arrays.asList(grauDificuldade);
	    }
	     
	    public List<String> getTipoQuestao() {
	        return Arrays.asList(tipoQuestao);
	    }
}
