package br.com.jpamappergen.domain.service;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClassGeneratorProperty {

	private String name;
	private Class<?> clazz;
	private List<ClassGeneratorPropertyAnnotation> annotations;

	@Data
	@AllArgsConstructor
	public static class ClassGeneratorPropertyAnnotation {
		private Class<?> clazz;
		private List<ClassGeneratorPropertyAnnotationAttribute> atributes = new ArrayList<>();
 	}
	
	@Data
	@AllArgsConstructor
	public static class ClassGeneratorPropertyAnnotationAttribute {
		private String name;
		private String value;
	}
}
