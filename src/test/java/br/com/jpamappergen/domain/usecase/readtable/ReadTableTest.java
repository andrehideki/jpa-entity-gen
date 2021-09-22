package br.com.jpamappergen.domain.usecase.readtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.jpamappergen.adapter.factory.H2DatabaseFactory;
import br.com.jpamappergen.domain.factory.DatabaseFactory;

public class ReadTableTest {

	private ReadTable readTable;
	
	@BeforeEach
	public void setup() {
		DatabaseFactory databaseFactory = new H2DatabaseFactory();
		readTable = new ReadTable(databaseFactory);
	}
	
	@Test
	public void shouldReadAndTable() {
		ReadTableOutput output = readTable.execute(ReadTableInput.builder()
				.name("person")
				.build());
		assertEquals("person", output.getName());
	}
}
