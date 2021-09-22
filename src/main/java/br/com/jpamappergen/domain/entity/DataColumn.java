package br.com.jpamappergen.domain.entity;

import br.com.jpamappergen.domain.vo.Type;
import lombok.Data;

@Data
public class DataColumn {
	private String name;
	private Type type;

	public DataColumn(String name, String type) {
		this.name = name;
		this.type = Type.getType(type);
	}
}
