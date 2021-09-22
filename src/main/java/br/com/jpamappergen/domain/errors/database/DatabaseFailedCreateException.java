package br.com.jpamappergen.domain.errors.database;

public class DatabaseFailedCreateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DatabaseFailedCreateException() {
		super(String.format("Failed to create Database"));
	}

}
