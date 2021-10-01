package br.com.jpamappergen.infra.jsonschema2pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JsonSchemaPropety {
	private String type;
	private List<JsonSchemaAnnotation> annotations;
	
	@Data
	@AllArgsConstructor
	public static class JsonSchemaAnnotation {
		private Class<?> clazz;
		private List<JsonSchemaAttribute> attributes;
	}
	
	@Data
	@AllArgsConstructor
	public static class JsonSchemaAttribute {
		private String name;
		private String value;
	}
}
