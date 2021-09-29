package br.com.jpamappergen.infra.jsonschema2pojo;

import org.jsonschema2pojo.AnnotationStyle;
import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.SourceType;

public class JsonSchema2PojoConfig extends DefaultGenerationConfig {
	
	@Override
	public SourceType getSourceType() {
		return SourceType.YAMLSCHEMA;
	}
	
	@Override
	public boolean isIncludeAdditionalProperties() {
		return false;
	}
	
	@Override
	public AnnotationStyle getAnnotationStyle() {
		return AnnotationStyle.NONE;
	}
	
	@Override
	public boolean isIncludeToString() {
		return false;
	}
	
	@Override
	public boolean isIncludeHashcodeAndEquals() {
		return false;
	}
	
	@Override
	public boolean isIncludeGeneratedAnnotation() {
		return false;
	}
	
	@Override
	public boolean isGenerateBuilders() {
		return false;
	}
}
