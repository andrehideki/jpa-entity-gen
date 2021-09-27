package br.com.jpamappergen.domain.usecase.readtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.jpamappergen.adapter.factory.H2DatabaseFactory;
import br.com.jpamappergen.domain.factory.DatabaseFactory;
import br.com.jpamappergen.infra.JDBCAdapter;

public class ReadTableTest {

	private ReadTable readTable;
	private JDBCAdapter jdbcAdapter;
	
	@BeforeEach
	public void setup() {
		jdbcAdapter = new JDBCAdapter("jdbc:h2:mem:./h2/db", "admin", "admin");
		DatabaseFactory databaseFactory = new H2DatabaseFactory(jdbcAdapter);
		readTable = new ReadTable(databaseFactory);
	}
	
	@AfterEach
	public void tearDown() {
		jdbcAdapter.close();
	}
	
	@Test
	public void shouldReadTableName() {
		ReadTableOutput output = readTable.execute(ReadTableInput.builder()
				.name("person")
				.build());
		assertEquals("person", output.getName());
	}
	
	@Test
	public void shouldReadTableColumns() {
		ReadTableOutput output = readTable.execute(ReadTableInput.builder()
				.name("person")
				.build());
		assertTrue(output.getDataColumns().stream().anyMatch(column -> column.getName().equals("NAME")));
	}
}
