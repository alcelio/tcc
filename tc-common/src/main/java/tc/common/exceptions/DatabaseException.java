package tc.common.exceptions;

/**
 * Lançada durante a execução de tarefas do banco de dados
 * 
 */
public class DatabaseException extends ScreenException {

	private static final long serialVersionUID = 7251064962204604681L;

	/**
	 * Construtor da classe
	 * 
	 * @param cause
	 *            Exceção de que gerou a falha
	 */
	public DatabaseException(Throwable cause) {
		this("Falha grave ao tentar acessar o banco de dados", cause);
	}
	
	/**
	 * Construtor da classe
	 * 
	 * @param screenMessage
	 *            Mensagem de erro que poderão também ser exibida na tela
	 */
	public DatabaseException(String screenMessage) {
		super(screenMessage);
	}
	
	/**
	 * Construtor da classe
	 * 
	 * @param screenMessage
	 *            Mensagem de erro que poderão também ser exibida na tela
	 * @param cause
	 *            Exceção de que gerou a falha
	 */
	public DatabaseException(String screenMessage, Throwable cause) {
		super(screenMessage, cause);
	}
	
}
