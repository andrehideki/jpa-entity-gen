package br.com.jpamappergen.infra.jsonschema2pojo;

import javax.persistence.Column;

import org.jsonschema2pojo.AbstractAnnotator;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;

public class JpaAnnotator extends AbstractAnnotator {
	
	@Override
	public void propertyField(JFieldVar field, JDefinedClass clazz, String propertyName, JsonNode propertyNode) {
		super.propertyField(field, clazz, propertyName, propertyNode);
		System.out.println(propertyNode);
		field.annotate(Column.class)
				.param("name", "TESTE");
	}
}
