package br.com.jpamappergen.domain.usecase.generatemapperclass;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.jpamappergen.domain.usecase.generatemapperclass.GenerateMapperClassInput.GenerateMapperClassPropertyInput;
import br.com.jpamappergen.infra.jsonschema2pojo.JsonSchema2PojoClassGenerator;

public class GenerateMapperClassTest {
	
	private GenerateMapperClass generateMapperClass;
	
	@BeforeEach
	public void setup() {
		generateMapperClass = new GenerateMapperClass(new JsonSchema2PojoClassGenerator());
	}
	
	@Test
	public void should_generate_classfile() {
		generateMapperClass.execute(GenerateMapperClassInput.builder()
									.className("MyClass")
									.build());
		assertTrue(Files.exists(Paths.get("MyClass.java")));
	}
	
	@Test
	public void should_generate_classfile_with_one_property() {
		List<GenerateMapperClassPropertyInput> properties = asList(
				GenerateMapperClassPropertyInput.builder().name("age").clazz(Integer.class).build()
		);
		generateMapperClass.execute(GenerateMapperClassInput.builder()
				.className("MyClass2")
				.properties(properties)
				.build());
		assertTrue(Files.exists(Paths.get("MyClass2.java")));
	}
	
	@Test
	public void should_generate_classfile_with_annotation() {
		List<GenerateMapperClassPropertyInput> properties = asList(
				GenerateMapperClassPropertyInput.builder()
					.name("name")
					.clazz(String.class)
					.columnName("C_NAME")
					.build()
				);
		generateMapperClass.execute(GenerateMapperClassInput.builder()
				.className("MyClass3")
				.properties(properties)
				.build());
		assertTrue(Files.exists(Paths.get("MyClass3.java")));
	}
}
