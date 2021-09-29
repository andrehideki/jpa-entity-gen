package br.com.jpamappergen.domain.usecase.generateclass;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.jpamappergen.infra.jsonschema2pojo.JsonSchema2PojoClassGenerator;

public class GenerateClassTest {
	
	private GenerateClass generateClass;
	
	@BeforeEach
	public void setup() {
		generateClass = new GenerateClass(new JsonSchema2PojoClassGenerator());
	}
	
	@Test
	public void should_generate_classfile() {
		generateClass.execute(GenerateClassInput.builder()
									.className("MyClass")
									.build());
		assertTrue(Files.exists(Paths.get("MyClass.java")));
	}
	
	@Test
	public void should_generate_classfile_with_one_property() {
		Map<String, Class<?>> properties = new HashMap<>();
		properties.put("age", Integer.class);
		generateClass.execute(GenerateClassInput.builder()
				.className("MyClass2")
				.properties(properties)
				.build());
		assertTrue(Files.exists(Paths.get("MyClass2.java")));
	}
}
