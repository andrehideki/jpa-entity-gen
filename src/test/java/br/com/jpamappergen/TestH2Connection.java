package br.com.jpamappergen;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.h2.tools.RunScript;

public class TestH2Connection {

	public static void main(String[] args) throws Exception {
		TestH2Connection test = new TestH2Connection();
		test.readTable("your user name", "your password", "your MySQLserver name");
	}

	public void readTable(String user, String password, String server) throws Exception {
		Connection conn = DriverManager.getConnection("jdbc:h2:mem:./h2/db", "admin", "admin");  
		Statement stmt = conn.createStatement();  
		RunScript.execute(conn, new FileReader(new File("h2_start.sql")));
		ResultSet resultSet = stmt.executeQuery("SELECT * FROM person");
		ResultSetMetaData metaData = resultSet.getMetaData();
		int i = 1;
		while (i != metaData.getColumnCount() + 1) {
			String label = metaData.getColumnLabel(i);
			String type = metaData.getColumnTypeName(i);
			System.out.println((i++) + ": " + label + ": " + type);
		}
		conn.close();  
	}
	
}
