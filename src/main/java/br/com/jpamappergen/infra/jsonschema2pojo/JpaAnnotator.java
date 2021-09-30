package br.com.jpamappergen.infra.jsonschema2pojo;

import javax.persistence.Column;

import org.jsonschema2pojo.AbstractAnnotator;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;

public class JpaAnnotator extends AbstractAnnotator {
	
	@Column(name = "asdiofj")
	private Integer i;
	
//	@Override
//	public void propertyOrder(JDefinedClass clazz, JsonNode propertiesNode) {
//		super.propertyOrder(clazz, propertiesNode);
//		System.out.println("here");
//		System.out.println(clazz.toString());
//		System.out.println(propertiesNode);
//	}
//	
//	@Override
//	public void propertyInclusion(JDefinedClass clazz, JsonNode schema) {
//		super.propertyInclusion(clazz, schema);
//		System.out.println("PROP:" + schema);
//	}
	
	@Override
	public void propertyField(JFieldVar field, JDefinedClass clazz, String propertyName, JsonNode propertyNode) {
		super.propertyField(field, clazz, propertyName, propertyNode);
		field.annotate(Column.class)
				.param("name", "TESTE");
	}
}
