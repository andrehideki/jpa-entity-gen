package br.com.jpamappergen.domain.usecase.generatemapperclass;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenerateMapperClassInput {
	
	private String className;
	
	@Builder.Default
	private List<GenerateMapperClassPropertyInput> properties = new ArrayList<>();
	
	@Data
	@Builder
	public static class GenerateMapperClassPropertyInput {
		private String name;
		private Class<?> clazz;
		private String columnName;
	}
}
