package br.com.jpamappergen.domain.usecase.generateclass;

import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenerateClassInput {
	
	private String className;
	
	@Builder.Default
	private Map<String, Class<?>> properties = new HashMap<>();
}
