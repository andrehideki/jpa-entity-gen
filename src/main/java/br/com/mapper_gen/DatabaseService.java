package br.com.mapper_gen;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.com.mapper_gen.dto.Table;


@Service
public class DatabaseService {
    
    private JdbcTemplate jdbc;

    @Value("${database_schema}")
    private String schema;
    
    public DatabaseService(
        JdbcTemplate jdbc
    ) {
        this.jdbc = jdbc;
    }

    public List<Table> getDatabaseTables() {
        return jdbc.query("""
            SELECT table_name, owner
            FROM all_tables  
            WHERE owner = '{owner}' 
            ORDER BY table_name
        """
        .replace("{owner}", schema)    
        , (rs, row) -> {
            return new Table(
                rs.getString("table_name"),
                rs.getString("owner")
            );
        });
    }
}
