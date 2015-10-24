package com.tc.util;

import java.util.HashSet;
import java.util.Set;
import com.tc.model.Permissao;


public class GeraPermissoes {
	static private Set<Permissao> permissoes; 
	
	private static final Integer admin = 1;
	private static final Integer professor =2;
	private static final Integer aluno = 3;
	private static final Integer usuario =4;

	public static Set<Permissao> getPermissaoProfessor(){
		permissoes = new HashSet<Permissao>();
		Permissao  p = new Permissao();
		Permissao  u = new Permissao();
		p.setIdPermissao(professor);
		u.setIdPermissao(usuario);
		permissoes.add(p);
		permissoes.add(u);
		return permissoes;
	}
	
	public static Set<Permissao> getPermissaoAluno(){
		permissoes = new HashSet<Permissao>();
		Permissao  a = new Permissao();
		Permissao  u = new Permissao();
		a.setIdPermissao(aluno);
		u.setIdPermissao(usuario);
		permissoes.add(a);
		permissoes.add(u);
		return permissoes;
	}
	public static Set<Permissao> getPermissaoAdmin(){
		permissoes = new HashSet<Permissao>();
		Permissao  a = new Permissao();
		Permissao  u = new Permissao();
		
		a.setIdPermissao(admin);
		u.setIdPermissao(usuario);
		permissoes.add(a);
		permissoes.add(u);
		return permissoes;
	}
	
	
}
