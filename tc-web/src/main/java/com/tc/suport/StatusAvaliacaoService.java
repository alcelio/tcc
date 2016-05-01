package com.tc.suport;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "statusAvaliacaoService")
@ApplicationScoped
public class StatusAvaliacaoService {
	
	 private final static String[] statusAvaliacao;
     
	 static {
		 statusAvaliacao = new String[5];
		 statusAvaliacao[0] = "CORRIGIDA";
		 statusAvaliacao[1] = "PENDENTE DE CORREÇÃO";
		 statusAvaliacao[2] = "AGUARDANDO INÍCIO";
	       
	    }
	 
	   public List<String> getDtatusAvaliacao() {
	        return Arrays.asList(statusAvaliacao);
	    }
}
