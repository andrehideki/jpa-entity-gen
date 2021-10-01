package br.com.jpamappergen.domain.usecase.readtable;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Collectors;

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
				.name("PERSON")
				.build());
		assertEquals("PERSON", output.getName());
	}
	
	@Test
	public void shouldReadTableColumns() {
		ReadTableOutput output = readTable.execute(ReadTableInput.builder()
				.name("PERSON")
				.build());
		assertTrue(output.getDataColumns().stream().anyMatch(column -> column.getName().equals("NAME")));
	}
	
	@Test
	public void shouldReadPrimaryKey() {
		ReadTableOutput output = readTable.execute(ReadTableInput.builder()
				.name("PERSON")
				.build());
		assertEquals("NAME", output.getPrimaryKey().get(0).getName());
	}
	
	@Test
	public void shouldReadComposedPrimaryKey() {
		ReadTableOutput output = readTable.execute(ReadTableInput.builder()
				.name("PERSON_JOB")
				.build());
		assertEquals(asList("JOB_NAME", "PERSON_NAME"), output.getPrimaryKey().stream().map(column -> column.getName()).sorted().collect(Collectors.toList()));
	}
}
