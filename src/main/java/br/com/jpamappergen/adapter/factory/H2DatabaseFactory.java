package br.com.jpamappergen.adapter.factory;

import static java.util.Arrays.asList;

import java.nio.file.Paths;

import br.com.jpamappergen.domain.entity.Database;
import br.com.jpamappergen.domain.entity.Table;
import br.com.jpamappergen.domain.errors.database.DatabaseFailedCreateException;
import br.com.jpamappergen.domain.factory.DatabaseFactory;
import br.com.jpamappergen.infra.JDBCAdapter;

public class H2DatabaseFactory implements DatabaseFactory {

	private JDBCAdapter jdbcAdapter;
	
	public H2DatabaseFactory(JDBCAdapter jdbcAdapter) {
		this.jdbcAdapter = jdbcAdapter;
	}

	@Override
	public Database createDatabase() {
		try {
			return create();
		} catch (Exception e) {
			throw new DatabaseFailedCreateException(e);
		}
	}

	private Database create() throws Exception {
		this.jdbcAdapter.runScript(Paths.get("h2_start.sql"));
		Table personTable = this.jdbcAdapter.getTable("person");
		return new Database(asList(personTable));
	}
}
