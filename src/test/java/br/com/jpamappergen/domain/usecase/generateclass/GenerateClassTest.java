package br.com.jpamappergen.domain.usecase.generateclass;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;

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
									.json("{ type:object }")
									.build());
		assertTrue(Files.exists(Paths.get("MyClass.java")));
	}
}
