package br.com.jpamappergen.domain.usecase.readtable;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReadTableOutput {
	private String name;
	private List<DataColumnOutput> dataColumns;
	
	@Data
	@AllArgsConstructor
	public static class DataColumnOutput {
		private String name;
		private String type;
	}
}
