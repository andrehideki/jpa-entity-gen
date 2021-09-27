package br.com.jpamappergen.domain.entity;

import java.util.List;

import lombok.Data;

@Data
public class PrimaryKey {
	
	private List<DataColumn> columns;

	public PrimaryKey(List<DataColumn> columns) {
		this.columns = columns;
	}
}
