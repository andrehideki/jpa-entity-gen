package br.com.jpamappergen.infra.jsonschema2pojo;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.rules.RuleFactory;

import com.google.gson.Gson;
import com.sun.codemodel.JCodeModel;

import br.com.jpamappergen.domain.service.ClassGenerator;
import br.com.jpamappergen.domain.service.ClassGeneratorProperty;
import br.com.jpamappergen.domain.service.ClassGeneratorProperty.ClassGeneratorPropertyAnnotation;
import br.com.jpamappergen.infra.jsonschema2pojo.JsonSchemaPropety.JsonSchemaAnnotation;
import br.com.jpamappergen.infra.jsonschema2pojo.JsonSchemaPropety.JsonSchemaAttribute;

public class JsonSchema2PojoClassGenerator implements ClassGenerator {

	@Override
	public void generate(String className, List<ClassGeneratorProperty> properties) {
		try {
			generateClass(className, properties);
		} catch (Exception e) {
			throw new RuntimeException("Error at Class Generating:", e);
		}
	}
	
	private void generateClass(String className, List<ClassGeneratorProperty> properties) throws Exception {
		JCodeModel codeModel = new JCodeModel();
		GenerationConfig config = new JsonSchema2PojoConfig();
		SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new JpaAnnotator(), new SchemaStore()), new SchemaGenerator());
		mapper.generate(codeModel, className, "", convertToJsonProperties(properties));
		Path destPath = Paths.get("./");
		codeModel.build(destPath.toFile());
	}
	
	private String convertToJsonProperties(List<ClassGeneratorProperty> properties) {
		Map<String, JsonSchemaPropety> adaptedProperties = new HashMap<>();
		List<JsonSchemaAnnotation> annotations = new ArrayList<>();
		for (ClassGeneratorProperty prop: properties) {
			String type = prop.getClazz().getSimpleName().toLowerCase();
			for (ClassGeneratorPropertyAnnotation annotation: prop.getAnnotations()) {
				List<JsonSchemaAttribute> attributes = annotation.getAtributes().stream()
						.map(attr -> new JsonSchemaAttribute(attr.getName(), attr.getValue()))
						.collect(Collectors.toList());
				annotations.add(new JsonSchemaAnnotation(annotation.getClass(), attributes));
			}
			JsonSchemaPropety property = new JsonSchemaPropety(type, annotations);
			adaptedProperties.put(prop.getName(), property);
		}
		JsonSchema schema = new JsonSchema("object", adaptedProperties);
		String json = new Gson().toJson(schema);
		return json;
	}
}
