package br.com.jpamappergen.domain.service;

import java.util.Map;

public interface ClassGenerator {
	public void generate(String className, Map<String, Class<?>> properties);
}
