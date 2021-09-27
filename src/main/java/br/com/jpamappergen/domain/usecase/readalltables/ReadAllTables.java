package br.com.jpamappergen.domain.usecase.readalltables;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jpamappergen.domain.entity.Database;
import br.com.jpamappergen.domain.entity.Table;
import br.com.jpamappergen.domain.factory.DatabaseFactory;

public class ReadAllTables {
	
	private Database database;
	
	public ReadAllTables(DatabaseFactory databaseFactory) {
		this.database = databaseFactory.createDatabase();
	}


	public ReadAllTablesOutput execute() {
		List<Table> tables = database.getTables();
		return ReadAllTablesOutput.builder()
					.tables(tables.stream()
								.map(table -> table.getName())
								.collect(Collectors.toList()))
					.build();
	}
}
