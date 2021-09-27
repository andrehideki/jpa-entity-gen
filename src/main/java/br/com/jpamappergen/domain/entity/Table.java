package br.com.jpamappergen.domain.entity;

import java.util.List;

import lombok.Data;

@Data
public class Table {
	
	private String name;
	private PrimaryKey primaryKey;
	private List<DataColumn> columns;
	
	public Table(String name, List<DataColumn> columns, PrimaryKey primaryKey) {
		this.name = name;
		this.columns = columns;
		this.primaryKey = primaryKey;
	}
}
