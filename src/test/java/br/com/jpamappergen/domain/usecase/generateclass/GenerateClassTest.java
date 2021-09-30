package br.com.jpamappergen.domain.usecase.generateclass;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.persistence.Column;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.jpamappergen.domain.usecase.generateclass.GenerateClassInput.GenerateClassPropertyInput;
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
		List<GenerateClassPropertyInput> properties = asList(
				GenerateClassPropertyInput.builder().name("age").clazz(Integer.class).build()
		);
		generateClass.execute(GenerateClassInput.builder()
				.className("MyClass2")
				.properties(properties)
				.build());
		assertTrue(Files.exists(Paths.get("MyClass2.java")));
	}
	
	@Test
	public void should_generate_classfile_with_annotation() {
		List<GenerateClassPropertyInput> properties = asList(
				GenerateClassPropertyInput.builder()
					.name("name")
					.clazz(String.class)
					.annotation(Column.class)
					.build()
				);
		generateClass.execute(GenerateClassInput.builder()
				.className("MyClass3")
				.properties(properties)
				.build());
		assertTrue(Files.exists(Paths.get("MyClass3.java")));
	}
}
