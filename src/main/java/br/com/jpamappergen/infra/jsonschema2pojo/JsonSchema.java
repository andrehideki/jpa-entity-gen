package br.com.jpamappergen.infra.jsonschema2pojo;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class JsonSchema {
	
	private String type = "object";
	private Map<String, JsonSchemaPropety> properties;
}
