package com.tc.suport;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean (name="bbGlobal")
@ApplicationScoped
public class BbGlobal {
	
	/**
	 * Endereço da página para visualização de avalições que o professor deve corrigir
	 */
	public static final String PAGINA_AVALIACOES_PROFESSOR_A_CORRIGIR = "/professor/avaliacoesProfessorCorrigir.jsf";
	
	/**
	 * Indica o status de avaliação corrigida
	 */
	private  final String STATUS_AVALIACAO_CORRIGIDA = "CORRIGIDA";

	/**
	 * Indica o status de avaliação PENDENTE DE CORREÇÃO
	 */
	private  final String STATUS_AVALIACAO_PENDETE_CORRECAO = "PENDENTE DE CORREÇÃO";
	
	/**
	 * Indica o status de avaliação AGUARDANDO INÍCIO(aguardando para ser respondida)
	 */
	private  final String STATUS_AVALIACAO_AGUARDANDO_INICIO = "AGUARDANDO INÍCIO";

	/**
	 * Endereço da página para para visualização de avalições
	 */
	private  final String PAGINA_PESQUISA_AVALIACAO = "/professor/pesquisaavaliacao.jsf";
	/**
	 * Indica a opção 'A' para as respostas
	 */
	private static  final String OPACAO_A = "A";
	
	/**
	 * Indica a opção 'B' para as respostas
	 */
	private static  final String OPACAO_B = "B";
	
	/**
	 * Indica a opção 'C' para as respostas
	 */
	private static  final String OPACAO_C = "C";
	
	/**
	 * Indica a opção 'D' para as respostas
	 */
	private static  final String OPACAO_D = "D";
	
	/**
	 * Indica a opção 'E' para as respostas
	 */
	private static  final String OPACAO_E = "E";

	/**
	 * Indica tipo de questão dissertativa.
	 */
	private  final String QUESTAO_DISSERTATIVA = "DISSERTATIVA";

	/**
	 * Indica tipo de questão verdadeiro ou falso.
	 */
	private  final String QUESTAO_VF = "VERDADEIRO OU FALSO";

	/**
	 * Indica tipo de questão objetiva.
	 */
	private  final String QUESTAO_OBJETIVA = "OBJETIVA";

	/**
	 * Indica tipo de questão relacionar.
	 */
	private  final String QUESTAO_RELACAO = "RELACAIONAR";

	/**
	 * Indica tipo de questão de relacionar
	 */
	private  final String QUESTAO_ORDENACAO = "ORDENAR";

	/**
	 * Indica grau de dificuldade fácil
	 */
	private  final String DIFICULDADE_FACIL = "FÁCIL";

	/**
	 * Indica grau de dificuldade moderado
	 */
	private  final String DIFICULDADE_MODERADA = "MODERADO";

	/**
	 * Indica grau de dificuldade exigente
	 */
	private  final String DIFICULDADE_EXIGENTE = "EXIGENTE";

	/**
	 * Indica grau de dificuldade muito exigente
	 */
	private  final String DIFICULDADE_MUITO_EGIGENTE = "MUITO EXIGENTE";

	/**
	 * Indica grau de dificuldade extremo
	 */
	private  final String DIFICULDADE_EXTREMA = "EXTREMA";

	/**
	 * Endereço da página home
	 */
	private  final String PAGINA_HOME = "/restrito/home.jsf";

	/**
	 * Endereço da página atualizar cadastro.
	 */
	private  final String PAGINA_ATUALIZACAO_CADASTRO = "/restrito/atualizacadastro.jsf";

	/**
	 * Endereço da página de acessi negado.
	 */
	private  final String PAGINA_ACESSO_NEGADO = "/privateo/AcessoNegado.jsf";

	/**
	 * Endereço para a página de cadastro de alunos
	 */
	private  final String PAGINA_CADASTRO_ALUNO = "/privateo/cadastroaluno.jsf";

	/**
	 * Endereço para a página de cadastro de professor
	 */
	private  final String PAGINA_CADASTRO_PROFESSOR = "/privateo/cadastroprofessor.jsf";

	/**
	 * Endereço para a página de login
	 */
	private  final String PAGINA_LOGIN = "/privateo/login.jsf";

	/**
	 * Endereço para a página de erro ao efetuar login
	 */
	private  final String PAGINA_LOGIN_ERROR = "/privateo/loginError.jsf";

	/**
	 * Endereço para a página de cadastro de cidades
	 */
	private  final String PAGINA_CADASTRO_CIDADE = "/admin/cadastrarcidade.jsf";

	/**
	 * Endereço para a página de cadastro de disciplinas
	 */
	private  final String PAGINA_CADASTRO_DISCIPLINA = "/admin/cadastrardisciplina.jsf";

	/**
	 * Endereço para a página de cadastro de Cidades
	 */
	private  final String PAGINA_CADASTRO_GRAU_DIFICULDADE = "/admin/cadastrograudificuldade.jsf";

	/**
	 * Endereço para a página de cadastro de Séries
	 */
	private  final String PAGINA_CADASTRO_SERIE = "/admin/cadastroserie.jsf";

	/**
	 * Endereço para a página de cadastro de Satus de Avaliações
	 */
	private  final String PAGINA_CADASTRO_STATUS_AVALIACAO = "/admin/cadastrostatusavaliacao.jsf";

	/**
	 * Endereço para a página de efetuar cadastro de Tópicos de Estudos
	 */
	private  final String PAGINA_CADASTRO_TOPICOS_ESTUDOS = "/admin/cadastrotopicoestudo.jsf";

	/**
	 * Endereço para a página de cadastro de Turnos
	 */
	private  final String PAGINA_CADASTRO_TURNOS = "/admin/cadastroturno.jsf";

	/**
	 * Endereço para a página de cadastro de Instituições
	 */
	private  final String PAGINA_CADASTRO_INSTITUICOES = "/admin/manutencaoinstituicao.jsf";

	/**
	 * Endereço para a página para controle de usuários cadastrados no sistema
	 */
	private  final String PAGINA_MANUTENCAO_USUARIO = "/admin/manutencaousuario.jsf";

	/**
	 * Endereço para a página para consulta as avaliaçõe do aluno logado.
	 */
	private  final String PAGINA_AVALIACOES_ALUNO = "/aluno/avaliacoesaluno.jsf";

	/**
	 * Endereço da página para aluno entart e participar de uma nova turma
	 */
	private  final String PAGINA_ENTRAR_TURMA = "/aluno/entrarturma.jsf";

	/**
	 * Endereço da página de visualização de estatisticas para aluno;
	 */
	private  final String PAGINA_ESTATISTICAS_ALUNO = "/aluno/estatisticasaluno.jsf";

	/**
	 * Endereço da página de visualização de estatisticas para o professor ;
	 */
	private static final String PAGINA_ESTATISTICAS_PROFESSOR = "/aluno/estatisticasProfessor.jsf";

	/**
	 * Endereço da página pare responder avaliaçao
	 */
	private  final String PAGINA_RESPONDER_AVALIACAO = "/aluno/responderavaliacao.jsf";

	/**
	 * Endereço da página inclusao de novas questoes
	 */
	private  final String PAGINA_CADASTRO_QUESTAO = "/professor/cadastroquestao.jsf";

	/**
	 * Endereço da página inclusao de novas turmas
	 */
	private  final String PAGINA_CADASTRO_TURMA = "/professor/cadastroturma.jsf";

	/**
	 * Endereço da página corrigir avaliações com pendencias
	 */
	private  final String PAGINA_CORRECAO_AVALIACAO = "/professor/correcaoavaliacao.jsf";

	/**
	 * Endereço da página para criar avaliações
	 */
	private  final String PAGINA_CRIAR_AVALIACAO = "/professor/criaravaliacao.jsf";
	
	
	/**
	 * Endereço da página que apresenta as turmas de um professor
	 */
	private final String PAGINA_TURMAS_PROFESSOR = "/professor/turmasprofessor.jsf";
	
	/**
	 * Endereço da página de estatÍsticas para para o professor
	 */
	private  final String PAGINA_ESTATISTICAS = "/professor/estatisticas.jsf";

	/**
	 * Endereço da página para incluir novas disciplinas
	 */
	private  final String PAGINA_INCLUIR_DISCIPLINA = "/professor/incluirdisciplina.jsf";

	/**
	 * Endereço da página para incluir intituiçoes de ensino
	 */
	private  final String PAGINA_INCLUIR_INSTITUICAO = "/professor/incluirinstituicao.jsf";

	/**
	 * Endereço da página para incluir questões dissertativas
	 */
	private  final String PAGINA_INCLUI_QUESTAO_DISSERTATIVA = "/professor/incluirquestaodissertativa.jsf";

	/**
	 * Endereço da página para incluir questões objetivas
	 */
	private  final String PAGINA_INCLUI_QUESTAO_OBJETIVA = "/professor/incluirquestaoobjetiva.jsf";

	/**
	 * Endereço da página para incluir questões de ordenar
	 */
	private  final String PAGINA_INCLUI_QUESTAO_ORDENAR = "/professor/incluirquestaoordenar.jsf";

	/**
	 * Endereço da página para incluir questões de ordenar
	 */
	private  final String PAGINA_INCLUI_QUESTAO_RELACIONAR = "/professor/incluirquestaorelacionar.jsf";

	/**
	 * Endereço da página para incluir questões Verdadeiro ou falso
	 */
	private  final String PAGINA_INCLUI_QUESTAO_VF = "/professor/incluirquestaoVF.jsf";

	/**
	 * Endereço da página para incluir séries
	 */
	private  final String PAGINA_INCLUI_SERIE = "/professor/incluirserie.jsf";
	
	/**
	 * Endereço da página de pesquisa de questões
	 */
	private  final String PAGINA_PESQUISA_QUESTAO = "/professor/pesquisaquestao.jsf";
	

	/**
	 * Endereço da página para incluir tópicos de estudo
	 */
	private  final String PAGINA_INCLUIR_TOPICOS_ESTUDOS = "/professor/incluirtopicoestudo.jsf";

	/**
	 * Endereço da página para incluir turnos de aulas
	 */
	private  final String PAGINA_INCLUIR_TURNOS = "/professor/incluirturno.jsf";
	
	/**
	 * Endereço da página para visualização de avalições
	 */
	private final String PAGINA_AVALIACOES_PROFESSOR = "/professor/avaliacoesprofessor.jsf";

	public String getQUESTAO_DISSERTATIVA() {
		return QUESTAO_DISSERTATIVA;
	}

	public String getQUESTAO_VF() {
		return QUESTAO_VF;
	}

	public String getQUESTAO_OBJETIVA() {
		return QUESTAO_OBJETIVA;
	}

	public String getQUESTAO_RELACAO() {
		return QUESTAO_RELACAO;
	}

	public String getQUESTAO_ORDENACAO() {
		return QUESTAO_ORDENACAO;
	}

	public String getDIFICULDADE_FACIL() {
		return DIFICULDADE_FACIL;
	}

	public String getDIFICULDADE_MODERADA() {
		return DIFICULDADE_MODERADA;
	}

	public String getDIFICULDADE_EXIGENTE() {
		return DIFICULDADE_EXIGENTE;
	}

	public String getDIFICULDADE_MUITO_EGIGENTE() {
		return DIFICULDADE_MUITO_EGIGENTE;
	}

	public String getDIFICULDADE_EXTREMA() {
		return DIFICULDADE_EXTREMA;
	}

	public String getPAGINA_HOME() {
		return PAGINA_HOME;
	}

	public String getPAGINA_ATUALIZACAO_CADASTRO() {
		return PAGINA_ATUALIZACAO_CADASTRO;
	}

	public String getPAGINA_ACESSO_NEGADO() {
		return PAGINA_ACESSO_NEGADO;
	}

	public String getPAGINA_CADASTRO_ALUNO() {
		return PAGINA_CADASTRO_ALUNO;
	}

	public String getPAGINA_CADASTRO_PROFESSOR() {
		return PAGINA_CADASTRO_PROFESSOR;
	}

	public String getPAGINA_LOGIN() {
		return PAGINA_LOGIN;
	}

	public String getPAGINA_LOGIN_ERROR() {
		return PAGINA_LOGIN_ERROR;
	}

	public String getPAGINA_CADASTRO_CIDADE() {
		return PAGINA_CADASTRO_CIDADE;
	}

	public String getPAGINA_CADASTRO_DISCIPLINA() {
		return PAGINA_CADASTRO_DISCIPLINA;
	}

	public String getPAGINA_CADASTRO_GRAU_DIFICULDADE() {
		return PAGINA_CADASTRO_GRAU_DIFICULDADE;
	}

	public String getPAGINA_CADASTRO_SERIE() {
		return PAGINA_CADASTRO_SERIE;
	}

	public String getPAGINA_CADASTRO_STATUS_AVALIACAO() {
		return PAGINA_CADASTRO_STATUS_AVALIACAO;
	}

	public String getPAGINA_CADASTRO_TOPICOS_ESTUDOS() {
		return PAGINA_CADASTRO_TOPICOS_ESTUDOS;
	}

	public String getPAGINA_CADASTRO_TURNOS() {
		return PAGINA_CADASTRO_TURNOS;
	}

	public String getPAGINA_CADASTRO_INSTITUICOES() {
		return PAGINA_CADASTRO_INSTITUICOES;
	}

	public String getPAGINA_MANUTENCAO_USUARIO() {
		return PAGINA_MANUTENCAO_USUARIO;
	}

	public String getPAGINA_AVALIACOES_ALUNO() {
		return PAGINA_AVALIACOES_ALUNO;
	}

	public String getPAGINA_ENTRAR_TURMA() {
		return PAGINA_ENTRAR_TURMA;
	}

	public String getPAGINA_ESTATISTICAS_ALUNO() {
		return PAGINA_ESTATISTICAS_ALUNO;
	}
	

	public static String getPaginaEstatisticasProfessor() {
		return PAGINA_ESTATISTICAS_PROFESSOR;
	}

	public String getPAGINA_RESPONDER_AVALIACAO() {
		return PAGINA_RESPONDER_AVALIACAO;
	}

	public String getPAGINA_CADASTRO_QUESTAO() {
		return PAGINA_CADASTRO_QUESTAO;
	}

	public String getPAGINA_CADASTRO_TURMA() {
		return PAGINA_CADASTRO_TURMA;
	}

	public String getPAGINA_CORRECAO_AVALIACAO() {
		return PAGINA_CORRECAO_AVALIACAO;
	}

	public String getPAGINA_CRIAR_AVALIACAO() {
		return PAGINA_CRIAR_AVALIACAO;
	}

	public String getPAGINA_ESTATISTICAS() {
		return PAGINA_ESTATISTICAS;
	}

	public String getPAGINA_INCLUIR_DISCIPLINA() {
		return PAGINA_INCLUIR_DISCIPLINA;
	}

	public String getPAGINA_INCLUIR_INSTITUICAO() {
		return PAGINA_INCLUIR_INSTITUICAO;
	}

	public String getPAGINA_INCLUI_QUESTAO_DISSERTATIVA() {
		return PAGINA_INCLUI_QUESTAO_DISSERTATIVA;
	}

	public String getPAGINA_INCLUI_QUESTAO_OBJETIVA() {
		return PAGINA_INCLUI_QUESTAO_OBJETIVA;
	}

	public String getPAGINA_INCLUI_QUESTAO_ORDENAR() {
		return PAGINA_INCLUI_QUESTAO_ORDENAR;
	}

	public String getPAGINA_INCLUI_QUESTAO_RELACIONAR() {
		return PAGINA_INCLUI_QUESTAO_RELACIONAR;
	}

	public String getPAGINA_INCLUI_QUESTAO_VF() {
		return PAGINA_INCLUI_QUESTAO_VF;
	}

	public String getPAGINA_INCLUI_SERIE() {
		return PAGINA_INCLUI_SERIE;
	}

	public String getPAGINA_INCLUIR_TOPICOS_ESTUDOS() {
		return PAGINA_INCLUIR_TOPICOS_ESTUDOS;
	}

	public String getPAGINA_INCLUIR_TURNOS() {
		return PAGINA_INCLUIR_TURNOS;
	}

	public String getPAGINA_PESQUISA_QUESTAO() {
		return PAGINA_PESQUISA_QUESTAO;
	}

	public static String getOpacaoA() {
		return OPACAO_A;
	}

	public static String getOpacaoB() {
		return OPACAO_B;
	}

	public static String getOpacaoC() {
		return OPACAO_C;
	}

	public static String getOpacaoD() {
		return OPACAO_D;
	}

	public static String getOpacaoE() {
		return OPACAO_E;
	}

	public String getPaginaPesquisaAvaliacao() {
		return PAGINA_PESQUISA_AVALIACAO;
	}

	public String getPAGINA_AVALIACOES_PROFESSOR() {
		return PAGINA_AVALIACOES_PROFESSOR;
	}

	public String getSTATUS_AVALIACAO_CORRIGIDA() {
		return STATUS_AVALIACAO_CORRIGIDA;
	}

	public String getSTATUS_AVALIACAO_PENDETE_CORRECAO() {
		return STATUS_AVALIACAO_PENDETE_CORRECAO;
	}

	public String getSTATUS_AVALIACAO_AGUARDANDO_INICIO() {
		return STATUS_AVALIACAO_AGUARDANDO_INICIO;
	}

	public String getPAGINA_PESQUISA_AVALIACAO() {
		return PAGINA_PESQUISA_AVALIACAO;
	}

	public String getPAGINA_TURMAS_PROFESSOR() {
		return PAGINA_TURMAS_PROFESSOR;
	}

	
	
}
