package br.com.jpamappergen.domain.usecase.generatemapperclass;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jpamappergen.domain.service.ClassGenerator;
import br.com.jpamappergen.domain.service.ClassGeneratorPropertyInput;

public class GenerateMapperClass {

	private ClassGenerator generator;
	
	public GenerateMapperClass(ClassGenerator generator) {
		this.generator = generator;
	}

	public void execute(GenerateMapperClassInput input) {
		List<ClassGeneratorPropertyInput> properties = convertProperties(input);
		generator.generate(input.getClassName(), properties);
	}
	
	public List<ClassGeneratorPropertyInput> convertProperties(GenerateMapperClassInput input) {
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
