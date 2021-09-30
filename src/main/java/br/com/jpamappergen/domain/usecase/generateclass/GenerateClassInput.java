package br.com.jpamappergen.domain.usecase.generateclass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenerateClassInput {
	
	private String className;
	
	@Builder.Default
	private List<GenerateClassPropertyInput> properties = new ArrayList<>();
	
	@Data
	@Builder
	public static class GenerateClassPropertyInput {
		private String name;
		private Class<?> clazz;
		@Builder.Default
		private Optional<Class<?>> annotation = Optional.empty();
	}
}
