package br.com.jpamappergen.domain.usecase.generatemapperclass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenerateMapperClassInput {
	
	private String className;
	
	@Builder.Default
	private List<GenerateMapperClassPropertyInput> properties = new ArrayList<>();
	
	@Data
	public static class GenerateMapperClassPropertyInput {
		private String name;
		private Class<?> clazz;
		private Optional<Class<?>> annotation = Optional.empty();
		
		@Builder
		public GenerateMapperClassPropertyInput(String name, Class<?> clazz, Class<?> annotation) {
			this.name = name;
			this.clazz = clazz;
			this.annotation = annotation !=null? Optional.of(annotation): Optional.empty();
		}
	}
}
