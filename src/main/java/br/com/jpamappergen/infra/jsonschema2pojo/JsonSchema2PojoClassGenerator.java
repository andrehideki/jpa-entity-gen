package br.com.jpamappergen.infra.jsonschema2pojo;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.NoopAnnotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.rules.RuleFactory;

import com.google.gson.Gson;
import com.sun.codemodel.JCodeModel;

import br.com.jpamappergen.domain.service.ClassGenerator;
import br.com.jpamappergen.domain.service.ClassGeneratorPropertyInput;

public class JsonSchema2PojoClassGenerator implements ClassGenerator {

	@Override
	public void generate(String className, List<ClassGeneratorPropertyInput> properties) {
		try {
			generateClass(className, properties);
		} catch (Exception e) {
			throw new RuntimeException("Error at Class Generating:", e);
		}
	}
	
	private void generateClass(String className, List<ClassGeneratorPropertyInput> properties) throws Exception {
		JCodeModel codeModel = new JCodeModel();
		GenerationConfig config = new JsonSchema2PojoConfig();
		SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new NoopAnnotator(), new SchemaStore()), new SchemaGenerator());
		mapper.generate(codeModel, className, "", convertToJsonProperties(properties));
		Path destPath = Paths.get("./");
		codeModel.build(destPath.toFile());
	}
	
	private String convertToJsonProperties(List<ClassGeneratorPropertyInput> properties) {
		Map<String, JsonSchemaPropety> adaptedProperties = new HashMap<>();
		for (ClassGeneratorPropertyInput prop: properties) {
			String type = prop.getClazz().getSimpleName().toLowerCase();
			adaptedProperties.put(prop.getName(), new JsonSchemaPropety(type));
		}
		JsonSchema schema = new JsonSchema("object", adaptedProperties);
		String json = new Gson().toJson(schema);
		return json;
	}
}
