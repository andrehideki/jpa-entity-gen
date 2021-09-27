package br.com.jpamappergen.domain.usecase.readtable;

import java.util.stream.Collectors;

import br.com.jpamappergen.domain.entity.Database;
import br.com.jpamappergen.domain.entity.Table;
import br.com.jpamappergen.domain.factory.DatabaseFactory;
import br.com.jpamappergen.domain.usecase.readtable.ReadTableOutput.DataColumnOutput;

public class ReadTable {

	private Database database;
	
	public ReadTable(DatabaseFactory databaseFactory) {
		this.database = databaseFactory.createDatabase();
	}


	public ReadTableOutput execute(ReadTableInput input) {
		Table table = database.getTable(input.getName());
		return ReadTableOutput.builder()
				.name(table.getName())
				.dataColumns(table.getColumns().stream()
						.map(column -> new DataColumnOutput(column.getName(), column.getType().toString()))
						.collect(Collectors.toList()))
				.primaryKey(table.getPrimaryKey().getColumns().stream()
										.map(column -> new DataColumnOutput(column.getName(), column.getType().toString()))
										.collect(Collectors.toList()))
				.build();
	}
}
