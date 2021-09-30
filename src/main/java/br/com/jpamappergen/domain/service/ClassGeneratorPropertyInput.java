package br.com.jpamappergen.domain.service;

import java.util.Optional;

import lombok.Builder;
import lombok.Data;

@Data
public class ClassGeneratorPropertyInput {

	private String name;
	private Class<?> clazz;
	private Optional<Class<?>> annotation = Optional.empty();

	@Builder
	public ClassGeneratorPropertyInput(String name, Class<?> clazz, Class<?> annotation) {
		this.name = name;
		this.clazz = clazz;
		if (annotation != null) {
			this.annotation = Optional.of(annotation);
		}
	}
	
	@Builder
	public ClassGeneratorPropertyInput(String name, Class<?> clazz) {
		this.name = name;
		this.clazz = clazz;
		this.annotation = Optional.empty();
	}
}
