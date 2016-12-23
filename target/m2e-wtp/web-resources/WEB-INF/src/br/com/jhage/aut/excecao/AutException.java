package br.com.jhage.aut.excecao;


public class AutException extends Exception {

	private static final long serialVersionUID = 1L;

	private AutExceptionCode code;

	

	public AutException(AutExceptionCode code) {
		super(code.toString());
		this.code = code;
	}

	public AutException(final Exception cause, AutExceptionCode code) {

		super(code.toString(), cause);
		this.code = code;
	}

	public AutException(final Throwable cause, AutExceptionCode code) {

		super(code.toString(), cause);
		this.code = code;
	}

	public AutExceptionCode getCode() {
		return code;
	}

	@Override
	public String getMessage() {

		String message = super.getMessage();
		if (message == null || message.isEmpty()) {
			message = AutExceptionCode.ERRRO_ACAO_ABRUPTA.toString();
		}
		return message;
	}

}
