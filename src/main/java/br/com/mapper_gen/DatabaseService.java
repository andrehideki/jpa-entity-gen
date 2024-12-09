package br.com.mapper_gen;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import br.com.mapper_gen.dto.Table;
import br.com.mapper_gen.dto.View;


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

    public List<Table> getDatabaseTables(String table) {
        table = table.toUpperCase();
        return jdbc.query("""
            SELECT table_name, owner
            FROM all_tables  
            WHERE owner = '{owner}' AND 
            table_name like '%{table_name}%'
            ORDER BY table_name
        """
        .replace("{owner}", schema)
        .replace("{table_name}", table)
        , (rs, row) -> {
            return new Table(
                rs.getString("table_name"),
                rs.getString("owner")
            );
        });
    }

    public List<View> getDatabaseViews(String name) {
        name = name.toUpperCase();
        return jdbc.query("""
            SELECT view_name, owner
            FROM all_views  
            WHERE owner = '{owner}' AND 
            view_name like '%{view_name}%'
            ORDER BY view_name
        """
        .replace("{owner}", schema)
        .replace("{view_name}", name)
        , (rs, row) -> {
            return new View(
                rs.getString("view_name"),
                rs.getString("owner")
            );
        });
    }

    public List<View> getProcedures(String name) {
        name = name.toUpperCase();
        return jdbc.query("""
            SELECT OBJECT_NAME, owner
            FROM all_objects   
            WHERE owner = '{owner}' AND 
            object_name like '%{object_name}%'
            AND object_type IN ('PROCEDURE', 'FUNCTION')
            ORDER BY OBJECT_NAME
        """
        .replace("{owner}", schema)
        .replace("{object_name}", name)
        , (rs, row) -> {
            return new View(
                rs.getString("object_name"),
                rs.getString("owner")
            );
        });
    }
    
}
