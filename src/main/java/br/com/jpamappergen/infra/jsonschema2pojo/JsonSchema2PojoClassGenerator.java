package br.com.jpamappergen.infra.jsonschema2pojo;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.NoopAnnotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.rules.RuleFactory;

import com.sun.codemodel.JCodeModel;

import br.com.jpamappergen.domain.service.ClassGenerator;

public class JsonSchema2PojoClassGenerator implements ClassGenerator {

	@Override
	public void generate(String className) {
		try {
			generateClass(className);
		} catch (Exception e) {
			throw new RuntimeException("Error at Class Generating:", e);
		}
	}
	
	private void generateClass(String className) throws Exception {
		JCodeModel codeModel = new JCodeModel();
		GenerationConfig config = new JsonSchema2PojoConfig();		
		SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new NoopAnnotator(), new SchemaStore()), new SchemaGenerator());
		mapper.generate(codeModel, className, "", "{\"type\":\"object\"  }");
		Path destPath = Paths.get("./");
		if (!Files.exists(destPath)) {
			Files.createDirectory(destPath);
		}
		codeModel.build(destPath.toFile());
	}
	
}
