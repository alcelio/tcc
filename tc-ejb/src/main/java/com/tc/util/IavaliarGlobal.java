package com.tc.util;

public class IavaliarGlobal {
	/**
	 * Indica o status de avaliação corrigida
	 */
	public static final String STATUS_AVALIACAO_CORRIGIDA = "CORRIGIDA";

	/**
	 * Indica o status de avaliação PENDENTE DE CORREÇÃO
	 */
	public static final String STATUS_AVALIACAO_PENDETE = "PENDENTE DE CORREÇÃO";
	
	/**
	 * Indica o status de avaliação AGUARDANDO INÍCIO(aguardando para ser respondida)
	 */
	public static final String STATUS_AVALIACAO_AGUARDANDO_INICIO = "AGUARDANDO INÍCIO";
	
	/**
	 * Indica que a avaliação com este status já foi respondida pelo aluno
	 */
	public static final Integer STATUS_AVALIACAO_4_RESPONDIDA = 4;
	
	
	
	/**
	 * Indica a opção 'A' para as respostas
	 */
	public static  final String OPCAO_A = "A";
	
	/**
	 * Indica a opção 'B' para as respostas
	 */
	public static  final String OPCAO_B = "B";
	
	/**
	 * Indica a opção 'C' para as respostas
	 */
	public static  final String OPCAO_C = "C";
	
	/**
	 * Indica a opção 'D' para as respostas
	 */
	public static  final String OPCAO_D = "D";
	
	/**
	 * Indica a opção 'E' para as respostas
	 */
	public static  final String OPCAO_E = "E";
	
	/**
	 * Indica tipo de questão dissertativa.
	 */
	public static  final String QUESTAO_DISSERTATIVA = "DISSERTATIVA";

	/**
	 * Indica tipo de questão verdadeiro ou falso.
	 */
	public static  final String QUESTAO_VF = "VERDADEIRO OU FALSO";

	/**
	 * Indica tipo de questão objetiva.
	 */
	public static  final String QUESTAO_OBJETIVA = "OBJETIVA";

	/**
	 * Indica tipo de questão relacionar.
	 */
	public static  final String QUESTAO_RELACAO = "RELACIONAR";

	/**
	 * Indica tipo de questão de relacionar
	 */
	public static  final String QUESTAO_ORDENACAO = "ORDENAR";
	
	
	
	/**
	 * Endereço da página home
	 */
	public static final String PAGINA_HOME = "/restrito/home.jsf";
	
	/**
	 * Endereço da página atualizar cadastro.
	 */
	public static final String PAGINA_ATUALIZACAO_CADASTRO = "/restrito/atualizacadastro.jsf";
	
	/**
	 * Endereço da página de acessi negado.
	 */
	public static final String PAGINA_ACESSO_NEGADO = "/publico/AcessoNegado.jsf";

	/**
	 * Endereço para a página de cadastro de alunos
	 */
	public static final String PAGINA_CADASTRO_ALUNO = "/publico/cadastroaluno.jsf";
	
	/**
	 * Endereço para a página de cadastro de professor
	 */
	public static final String PAGINA_CADASTRO_PROFESSOR = "/publico/cadastroprofessor.jsf";
	
	/**
	 * Endereço para a página de login
	 */
	public static final String PAGINA_LOGIN = "/publico/login.jsf";
	
	/**
	 * Endereço para a página de erro ao efetuar login
	 */
	public static final String PAGINA_LOGIN_ERROR = "/publico/loginError.jsf";
	
	/**
	 * Endereço para a página de cadastro de cidades
	 */
	public static final String PAGINA_CADASTRO_CIDADE = "/admin/cadastrarcidade.jsf";
	
	/**
	 * Endereço para a página de cadastro de disciplinas
	 */
	public static final String PAGINA_CADASTRO_DISCIPLINA = "/admin/cadastrardisciplina.jsf";
	
	/**
	 * Endereço para a página de cadastro de Cidades
	 */
	public static final String PAGINA_CADASTRO_GRAU_DIFICULDADE = "/admin/cadastrograudificuldade.jsf";
	
	/**
	 * Endereço para a página de cadastro de Séries
	 */
	public static final String PAGINA_CADASTRO_SERIE = "/admin/cadastroserie.jsf";
	
	/**
	 * Endereço para a página de cadastro de Satus de Avaliações
	 */
	public static final String PAGINA_CADASTRO_STATUS_AVALIACAO = "/admin/cadastrostatusavaliacao.jsf";
	
	/**
	 * Endereço para a página de efetuar cadastro de Tópicos de Estudos
	 */
	public static final String PAGINA_CADASTRO_TOPICOS_ESTUDOS = "/admin/cadastrotopicoestudo.jsf";

	/**
	 * Endereço para a página de cadastro de Turnos
	 */
	public static final String PAGINA_CADASTRO_TURNOS = "/admin/cadastroturno.jsf";
	
	/**
	 * Endereço para a página de cadastro de Instituições
	 */
	public static final String PAGINA_CADASTRO_INSTITUICOES = "/admin/manutencaoinstituicao.jsf";
	
	/**
	 * Endereço para a página para controle de usuários cadastrados no sistema
	 */
	public static final String PAGINA_MANUTENCAO_USUARIO = "/admin/manutencaousuario.jsf";
	
	/**
	 * Endereço para a página para consulta as avaliaçõe do aluno logado.
	 */
	public static final String PAGINA_AVALIACOES_ALUNO = "/aluno/avaliacoesaluno.jsf";
	
	/**
	 * Endereço da página para aluno entart e participar de uma nova turma
	 */
	public static final String PAGINA_ENTRAR_TURMA = "/aluno/entrarturma.jsf";
	
	/**
	 * Endereço da página de visualização de estatisticas para aluno;
	 */
	public static final String PAGINA_ESTATISTICAS_ALUNO = "/aluno/estatisticasaluno.jsf";
	
	/**
	 * Endereço da página pare responder avaliaçao
	 */
	public static final String PAGINA_RESPONDER_AVALIACAO = "/aluno/responderavaliacao.jsf";
	
	/**
	 * Endereço da página inclusao de novas questoes
	 */
	public static final String PAGINA_CADASTRO_QUESTAO = "/professor/cadastroquestao.jsf";
	
	/**
	 * Endereço da página que apresenta as turmas de um professor
	 */
	public static final String PAGINA_TURMAS_PROFESSOR = "/professor/turmasprofessor.jsf";
	
	
	/**
	 * Endereço da página inclusao de novas turmas
	 */
	public static final String PAGINA_CADASTRO_TURMA = "/professor/cadastroturma.jsf";
	
	/**
	 * Endereço da página corrigir avaliações com pendencias
	 */
	public static final String PAGINA_CORRECAO_AVALIACAO = "/professor/correcaoavaliacao.jsf";
	
	/**
	 * Endereço da página para criar avaliações
	 */
	public static final String PAGINA_CRIAR_AVALIACAO = "/professor/criaravaliacao.jsf";
	
	/**
	 * Endereço da página de estatÍsticas para para o professor
	 */
	public static final String PAGINA_ESTATISTICAS = "/professor/estatisticas.jsf";
	
	/**
	 * Endereço da página para incluir novas disciplinas
	 */
	public static final String PAGINA_INCLUIR_DISCIPLINA = "/professor/incluirdisciplina.jsf";
	
	/**
	 * Endereço da página para incluir intituiçoes de ensino
	 */
	public static final String PAGINA_INCLUIR_INSTITUICAO = "/professor/incluirinstituicao.jsf";
	
	/**
	 * Endereço da página para incluir questões dissertativas
	 */
	public static final String PAGINA_INCLUI_QUESTAO_DISSERTATIVA = "/professor/incluirquestaodissertativa.jsf";
	
	/**
	 * Endereço da página para incluir questões objetivas
	 */
	public static final String PAGINA_INCLUI_QUESTAO_OBJETIVA = "/professor/incluirquestaoobjetiva.jsf";
	
	/**
	 * Endereço da página para incluir questões de ordenar
	 */
	public static final String PAGINA_INCLUI_QUESTAO_ORDENAR = "/professor/incluirquestaoordenar.jsf";
	
	/**
	 * Endereço da página para incluir questões de ordenar
	 */
	public static final String PAGINA_INCLUI_QUESTAO_RELACIONAR = "/professor/incluirquestaorelacionar.jsf";
	
	/**
	 * Endereço da página para incluir questões Verdadeiro ou falso
	 */
	public static final String PAGINA_INCLUI_QUESTAO_VF = "/professor/incluirquestaoVF.jsf";
	
	/**
	 * Endereço da página para incluir séries
	 */
	public static final String PAGINA_INCLUI_SERIE = "/professor/incluirserie.jsf";
	
	/**
	 * Endereço da página para incluir tópicos de estudo
	 */
	public static final String PAGINA_INCLUIR_TOPICOS_ESTUDOS = "/professor/incluirtopicoestudo.jsf";
	
	/**
	 * Endereço da página para incluir turnos de aulas
	 */
	public static final String PAGINA_INCLUIR_TURNOS = "/professor/incluirturno.jsf";
	
	/**
	 * Endereço da página para pesquisa de questões ja cadastradas
	 */
	public static final String PAGINA_PESQUISA_QUESTAO = "/professor/pesquisaquestao.jsf";
	
	/**
	 * Endereço da página para visualização de avalições
	 */
	public static final String PAGINA_AVALIACOES_PROFESSOR = "/professor/avaliacoesprofessor.jsf";
	
}
