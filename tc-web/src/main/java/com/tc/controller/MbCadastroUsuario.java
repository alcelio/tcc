package com.tc.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.tc.data.UsuarioBeanDao;
import com.tc.model.Aluno;
import com.tc.model.Endereco;
import com.tc.model.Professor;
import com.tc.model.Usuario;
import com.tc.util.BuscaCEP;
import com.tc.util.GeraPermissoes;

@ManagedBean
@SessionScoped
public class MbCadastroUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	UsuarioBeanDao usuarioDao;

	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private String confirmaSenha;
	private Endereco endereco;
	private String cep;
	private boolean rendererOpcaoUsuario = true;
	private boolean usuarioCriado = false;
	BuscaCEP buscaCep = new BuscaCEP();

	public MbCadastroUsuario() {

	}

	public String novoUsuario() {
		usuario = new Usuario();
		setUsuarioCriado(true);
		setRendererOpcaoUsuario(false);

		return "/publico/cadastrousuario.jsf";
	}

	public void criaProfessor() {
		usuario = new Professor();
		setUsuarioCriado(true);
		setRendererOpcaoUsuario(false);
	}

	public void criaAluno() {
		usuario = new Aluno();
		setUsuarioCriado(true);
		setRendererOpcaoUsuario(false);
	}

	public String encerraCadastramento() {
		System.out.println("Passou pelo ecerramento de cadastro");
		return "/publico/login.jsf";
	}

	/**
	 * Metodo para chamar a inclusao no banco de dados.
	 * 
	 * @return void
	 */
	public void addUsuario() {
		if (usuario.getIdUsuario() == null || usuario.getIdUsuario() == 0) {
			salvar();
		} else {
			atualizar();
		}

	}

	/**
	 * Método para incluir instancia de usuario corrente na base de dados.
	 */
	private void salvar() {
		
		//usuario.setSenha(ConverterSHA1.cipher(usuario.getSenha()));
		usuario.setDataCadastro(new Date());
		usuario.setPermissoes(GeraPermissoes.getPermissaoAdmin());
		
		if (usuario.getSenha() == null ? confirmaSenha == null
				: usuario.getSenha().equals(confirmaSenha)) {

			
			try {

				if (!buscaCep.getBairro(cep).equals(cep)) {
					carregaCep();
					usuarioDao.create(usuario);
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação efetuada com sucesso", ""));
					
					novoUsuario();
					encerraCadastramento();
				} else {
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "CEP inexistente, informe corretamente.", ""));

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "As senhas não conferem.", ""));
		}
	}

	private void atualizar() {
		usuarioDao.update(usuario);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
	}

	public void deletar() {
		usuarioDao.remove(usuario);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Operação realizada com sucesso", ""));
	}

	public void carregaCep() {

		try {

			if (buscaCep.getBairro(cep).equals("-")) {
				usuario.getEndereco().setDsCidade(buscaCep.getCidade(cep));
				usuario.getEndereco().setDsEstado(buscaCep.getUF(cep));
				usuario.getEndereco().setDsCEP(cep);
			} else {
				usuario.getEndereco().setDsBairro(buscaCep.getBairro(cep));
				usuario.getEndereco().setDsCidade(buscaCep.getCidade(cep));
				usuario.getEndereco().setDsEndereco(buscaCep.getEndereco(cep));
				usuario.getEndereco().setDsCidade(buscaCep.getCidade(cep));
				usuario.getEndereco().setDsEstado(buscaCep.getUF(cep));
				usuario.getEndereco().setDsCEP(cep);
			}

			try {
				String[] values = buscaCep.getLatLong(cep).split(",");
				usuario.getEndereco().setLatitude(values[0]);
				usuario.getEndereco().setLongitude(values[1]);
			} catch (ParseException e) {

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public boolean isUsuarioCriado() {
		return usuarioCriado;
	}

	public void setUsuarioCriado(boolean usuarioCriado) {
		this.usuarioCriado = usuarioCriado;
	}

	public boolean isRendererOpcaoUsuario() {
		return rendererOpcaoUsuario;
	}

	public void setRendererOpcaoUsuario(boolean rendererOpcaoUsuario) {
		this.rendererOpcaoUsuario = rendererOpcaoUsuario;
	}

	public List<Usuario> getUsuarios() {
		usuarios = usuarioDao.listarUsuarios();
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
