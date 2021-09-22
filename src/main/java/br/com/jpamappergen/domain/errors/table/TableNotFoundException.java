package br.com.jpamappergen.domain.errors.table;

public class TableNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TableNotFoundException(String tableName) {
		super(String.format("Table not found: %s", tableName));
	}
}
