package br.com.jpamappergen.domain.entity;

import java.util.List;

import br.com.jpamappergen.domain.errors.table.TableNotFoundException;

public class Database {

	private List<Table> tables;
	
	public Database(List<Table> tables) {
		this.tables = tables;
	}

	public Table getTable(String name) {
		return tables.stream()
					.filter(table -> table.getName().equals(name))
					.findFirst()
					.orElseThrow(() -> new TableNotFoundException(name));
	}

	public List<Table> getTables() {
		return this.tables;
	}
}
