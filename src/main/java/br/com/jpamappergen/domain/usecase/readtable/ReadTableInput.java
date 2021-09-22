package br.com.jpamappergen.domain.usecase.readtable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReadTableInput {
	private String name;
}
