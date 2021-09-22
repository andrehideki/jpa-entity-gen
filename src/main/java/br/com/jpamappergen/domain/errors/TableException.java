package br.com.jpamappergen.domain.errors;

public class TableException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TableException() {
		super();
	}
	
	public TableException(String tableName) {
		super(String.format("Table not found: %s", tableName));
	}

	public TableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TableException(String message, Throwable cause) {
		super(message, cause);
	}

	public TableException(Throwable cause) {
		super(cause);
	}
}
