package br.com.jpamappergen.domain.entity;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import lombok.Data;

@Data
public class DataColumnTest {

	@Test
	public void shouldThrowErrorWhenUnsuportedType() {
		assertThrows(RuntimeException.class, () -> new DataColumn("Column", "UNSUPORTED"));
	}
}
