package br.com.jpamappergen.domain.usecase.generateclass;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jpamappergen.domain.service.ClassGenerator;
import br.com.jpamappergen.domain.service.ClassGeneratorPropertyInput;

public class GenerateClass {

	private ClassGenerator generator;
	
	public GenerateClass(ClassGenerator generator) {
		this.generator = generator;
	}

	public void execute(GenerateClassInput input) {
		List<ClassGeneratorPropertyInput> properties = convertProperties(input);
		generator.generate(input.getClassName(), properties);
	}
	
	public List<ClassGeneratorPropertyInput> convertProperties(GenerateClassInput input) {
		return input.getProperties().stream()
			.map(prop -> {
				if (prop.getAnnotation().isPresent()) {
					return ClassGeneratorPropertyInput.builder()
									.clazz(prop.getClazz())
									.name(prop.getName())
									.annotation(prop.getAnnotation().get())
									.build();
				}
				return ClassGeneratorPropertyInput.builder()
						.clazz(prop.getClazz())
						.name(prop.getName())
						.build();
			})
			.collect(Collectors.toList());
	}
}
