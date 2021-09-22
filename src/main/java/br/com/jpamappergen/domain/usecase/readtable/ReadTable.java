package br.com.jpamappergen.domain.usecase.readtable;

import br.com.jpamappergen.domain.entity.Database;
import br.com.jpamappergen.domain.entity.Table;
import br.com.jpamappergen.domain.factory.DatabaseFactory;

public class ReadTable {

	private Database database;
	
	public ReadTable(DatabaseFactory databaseFactory) {
		this.database = databaseFactory.createDatabase();
	}


	public ReadTableOutput execute(ReadTableInput input) {
		Table table = database.getTable(input.getName());
		return ReadTableOutput.builder()
				.name(table.getName())
				.build();
	}
}
