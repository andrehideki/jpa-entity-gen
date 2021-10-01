package br.com.jpamappergen.domain.usecase.generatemapperclass;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;

import br.com.jpamappergen.domain.service.ClassGenerator;
import br.com.jpamappergen.domain.service.ClassGeneratorProperty;
import br.com.jpamappergen.domain.service.ClassGeneratorProperty.ClassGeneratorPropertyAnnotation;
import br.com.jpamappergen.domain.service.ClassGeneratorProperty.ClassGeneratorPropertyAnnotationAttribute;

public class GenerateMapperClass {

	private ClassGenerator generator;
	
	public GenerateMapperClass(ClassGenerator generator) {
		this.generator = generator;
	}

	public void execute(GenerateMapperClassInput input) {
		List<ClassGeneratorProperty> properties = convertProperties(input);
		generator.generate(input.getClassName(), properties);
	}
	
	public List<ClassGeneratorProperty> convertProperties(GenerateMapperClassInput input) {
		return input.getProperties().stream()
			.map(prop -> {
				return ClassGeneratorProperty.builder()
								.clazz(prop.getClazz())
								.name(prop.getName())
								.annotations(asList(new ClassGeneratorPropertyAnnotation(Column.class, asList(new ClassGeneratorPropertyAnnotationAttribute("name", "MY_COLUMN")))))
								.build();
			})
			.collect(Collectors.toList());
	}
}
