package br.com.jpamappergen.domain.usecase.generateclass;

import br.com.jpamappergen.domain.service.ClassGenerator;

public class GenerateClass {

	private ClassGenerator generator;
	
	public GenerateClass(ClassGenerator generator) {
		this.generator = generator;
	}

	public void execute(GenerateClassInput input) {
		generator.generate(input.getClassName());
	}
}
