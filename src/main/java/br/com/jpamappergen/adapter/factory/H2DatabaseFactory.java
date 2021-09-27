package br.com.jpamappergen.adapter.factory;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.h2.tools.RunScript;

import br.com.jpamappergen.domain.entity.DataColumn;
import br.com.jpamappergen.domain.entity.Database;
import br.com.jpamappergen.domain.entity.Table;
import br.com.jpamappergen.domain.errors.database.DatabaseFailedCreateException;
import br.com.jpamappergen.domain.factory.DatabaseFactory;
import br.com.jpamappergen.infra.JDBCAdapter;

public class H2DatabaseFactory implements DatabaseFactory {

	private JDBCAdapter jdbcAdapter;
	
	
	
	@Override
	public Database createDatabase() {
		try {
			return create();
		} catch (Exception e) {
			throw new DatabaseFailedCreateException(e);
		}
	}

	private Database create() throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:h2:mem:./h2/db", "admin", "admin");  
		Statement stmt = conn.createStatement();
		RunScript.execute(conn, new FileReader(new File("h2_start.sql")));
		ResultSet resultSet = stmt.executeQuery("SELECT * FROM person");
		ResultSetMetaData metaData = resultSet.getMetaData();
		int i = 1;
		List<DataColumn> columns = new ArrayList<>();
		while (i != metaData.getColumnCount() + 1) {
			String label = metaData.getColumnLabel(i);
			String type = metaData.getColumnTypeName(i);
			columns.add(new DataColumn(label, type));
			i++;
		}
		Table personTable = new Table("person", columns);
		List<Table> tables = new ArrayList<>();
		tables.add(personTable);
		conn.close();
		return new Database(tables);
	}
}
