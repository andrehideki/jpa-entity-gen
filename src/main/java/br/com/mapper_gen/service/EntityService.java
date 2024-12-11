package br.com.mapper_gen.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.lang.model.element.Modifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.mapper_gen.dto.DatabaseObject;
import br.com.mapper_gen.lib.javapoet.JavaFile;
import br.com.mapper_gen.lib.javapoet.TypeSpec;

@Service
public class EntityService {
    
    @Value("${output_dir}")
    private String outputDir;

    @Autowired
    private DatabaseService databaseService;

    public Object createEntity(String name) throws IOException {
        var databaseObject = databaseService.getDatabaseObject(name);
        var classBuilder = TypeSpec.classBuilder(toCamelCase(name, true))
            .addModifiers(Modifier.PUBLIC);
		
        if (Arrays.asList("VIEW", "TABLE").contains(databaseObject.type())) {
            databaseObject.attributes().forEach(attr -> {
                String attrName = toCamelCase(attr.name(), false);
                Class<?> attrClass = getAttributeClass(attr);
                classBuilder.addField(attrClass, attrName, Modifier.PRIVATE);
            });
        }
			
        if (!Files.exists(Paths.get(outputDir))){
            Files.createDirectory(Paths.get(outputDir));
        }
        var javaFile = JavaFile.builder("", classBuilder.build())
            .build();
        javaFile.writeToFile(new File(outputDir));
        return databaseObject;
    }

    private String toCamelCase(String value, boolean firstLetterCaptalize) {
        var splited = value.split("_");
        value = Stream.of(splited).map(v -> v.substring(0, 1).toUpperCase() + v.substring(1).toLowerCase())
            .collect(Collectors.joining(""));
        if (firstLetterCaptalize)
            return value;
        return value.substring(0, 1).toLowerCase() + value.substring(1);
    }

    private Class<?> getAttributeClass(DatabaseObject.Attr attr) {
        if (Arrays.asList("CHAR", "VARCHAR", "VARCHAR2").contains(attr.type())) {
            return String.class;
        }

        if (Arrays.asList("TIMESTAMP(6)").contains(attr.type())) {
            return LocalDateTime.class;
        }

        if (Arrays.asList("DATE").contains(attr.type())) {
            return LocalDate.class;
        }

        if (Arrays.asList("NUMBER").contains(attr.type())) {
            return Long.class;
        }

        return String.class;
    }
}
