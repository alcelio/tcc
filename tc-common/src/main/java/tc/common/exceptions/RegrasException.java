package tc.common.exceptions;

/**
 * Lan�ada quando ocorrem erros no processamento das regras de neg�cios
 * 
 */
public class RegrasException extends ScreenException {
	private static final long serialVersionUID = 1L;

	/**
	 * @param screenMessage
	 *            String
	 * @param cause
	 *            Throwable
	 */
	public RegrasException(String screenMessage, Throwable cause) {
		super(screenMessage, cause);
	}
	
	/**
	 * @param screenMessage
	 *            String
	 */
	public RegrasException(String screenMessage) {
		super(screenMessage);
	}
	
}
