package br.com.jpamappergen.domain.errors;

public class TypeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TypeException() {
		super();
	}
	
	public TypeException(String typeName) {
		super(String.format("Unsuported type: %s", typeName));
	}

	public TypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public TypeException(Throwable cause) {
		super(cause);
	}
}
