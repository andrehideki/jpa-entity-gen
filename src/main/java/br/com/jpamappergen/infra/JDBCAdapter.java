package br.com.jpamappergen.infra;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.jpamappergen.domain.entity.DataColumn;
import br.com.jpamappergen.domain.entity.Table;

public class JDBCAdapter {
	
	private Connection conn;
	
	public Table getTable(String name) throws Exception {
		Statement stmt = conn.createStatement();
		ResultSet resultSet = stmt.executeQuery("SELECT * FROM " + name);
		ResultSetMetaData metaData = resultSet.getMetaData();
		int i = 1;
		List<DataColumn> columns = new ArrayList<>();
		while (i != metaData.getColumnCount() + 1) {
			String label = metaData.getColumnLabel(i);
			String type = metaData.getColumnTypeName(i);
			columns.add(new DataColumn(label, type));
			i++;
		}
		return new Table(name, columns);
	}
}
