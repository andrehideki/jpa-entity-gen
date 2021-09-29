package br.com.jpamappergen.infra;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.NoopAnnotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.rules.RuleFactory;
import org.junit.jupiter.api.Test;

import com.sun.codemodel.JCodeModel;

import br.com.jpamappergen.infra.jsonschema2pojo.JsonSchema2PojoConfig;

public class JsonToPojoTest {

	@Test
	public void test() throws IOException {
		JCodeModel codeModel = new JCodeModel();
		URL source = getClass().getResource("/schema/teste.json");
		GenerationConfig config = new JsonSchema2PojoConfig();		
		SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new NoopAnnotator(), new SchemaStore()), new SchemaGenerator());
		mapper.generate(codeModel, "ClassName", "", source);
		Path destPath = Paths.get("generate");
		if (!Files.exists(destPath)) {
			Files.createDirectory(destPath);
		}
		codeModel.build(destPath.toFile());
	}
}
