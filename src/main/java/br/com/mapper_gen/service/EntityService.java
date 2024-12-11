package br.com.mapper_gen.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.lang.model.element.Modifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
                classBuilder.addField(String.class, toCamelCase(attr.name(), false), Modifier.PRIVATE);
            });
        }
			
        if (!Files.exists(Paths.get(outputDir))){
            Files.createDirectory(Paths.get(outputDir));
        }
        var javaFile = JavaFile.builder("", classBuilder.build())
            .build();
        javaFile.writeToFile(new File(outputDir));
        return true;
    }

    private String toCamelCase(String value, boolean firstLetterCaptalize) {
        var splited = value.split("_");
        value = Stream.of(splited).map(v -> v.substring(0, 1).toUpperCase() + v.substring(1).toLowerCase())
            .collect(Collectors.joining(""));
        if (firstLetterCaptalize)
            return value;
        return value.substring(0, 1).toLowerCase() + value.substring(1);
    }
}
