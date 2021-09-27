package br.com.jpamappergen.domain.usecase.readalltables;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.jpamappergen.adapter.factory.H2DatabaseFactory;
import br.com.jpamappergen.domain.factory.DatabaseFactory;
import br.com.jpamappergen.infra.JDBCAdapter;

public class ReadAllTablesTest {
	
	private ReadAllTables readAllTables;
	private JDBCAdapter jdbcAdapter;
	
	@BeforeEach
	public void setup() {
		jdbcAdapter = new JDBCAdapter("jdbc:h2:mem:./h2/db", "admin", "admin");
		DatabaseFactory databaseFactory = new H2DatabaseFactory(jdbcAdapter);
		readAllTables = new ReadAllTables(databaseFactory);
	}
	
	@AfterEach
	public void tearDown() {
		jdbcAdapter.close();
	}
	
	@Test
	public void shouldReadAllTableNames() {
		ReadAllTablesOutput output = readAllTables.execute();
		assertEquals(asList("person"), output.getTables());
	}
	
}
