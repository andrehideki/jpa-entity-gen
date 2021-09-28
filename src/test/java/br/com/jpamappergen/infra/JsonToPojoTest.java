package br.com.jpamappergen.infra;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.SchemaMapper;
import org.junit.jupiter.api.Test;

import com.sun.codemodel.JCodeModel;

public class JsonToPojoTest {

	@Test
	public void test() throws IOException {
		JCodeModel codeModel = new JCodeModel();
		URL source = getClass().getResource("/schema/required.json");
		GenerationConfig config = new DefaultGenerationConfig() {
			@Override
			public boolean isGenerateBuilders() {
				return false;
			}
		};

//		SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());
		SchemaMapper mapper = new SchemaMapper();
		mapper.generate(codeModel, "ClassName", "", source);
		Path destPath = Paths.get("generate");
		Files.createDirectory(destPath);
		codeModel.build(destPath.toFile());
	}
}
