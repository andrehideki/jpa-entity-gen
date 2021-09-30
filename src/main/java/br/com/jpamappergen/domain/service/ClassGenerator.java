package br.com.jpamappergen.domain.service;

import java.util.List;

public interface ClassGenerator {
	public void generate(String className, List<ClassGeneratorPropertyInput> properties);
}
