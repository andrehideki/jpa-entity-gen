package br.com.jpamappergen.domain.errors.type;

public class TypeUnsuportedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TypeUnsuportedException(String typeName) {
		super(String.format("Unsuported type: %s", typeName));
	}
}
