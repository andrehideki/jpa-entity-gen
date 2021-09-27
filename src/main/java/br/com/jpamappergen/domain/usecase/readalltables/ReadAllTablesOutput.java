package br.com.jpamappergen.domain.usecase.readalltables;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReadAllTablesOutput {
	private List<String> tables;
}
