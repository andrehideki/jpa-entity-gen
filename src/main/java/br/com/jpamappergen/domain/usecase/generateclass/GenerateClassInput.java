package br.com.jpamappergen.domain.usecase.generateclass;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenerateClassInput {
	private String className;
	private String json;
}
