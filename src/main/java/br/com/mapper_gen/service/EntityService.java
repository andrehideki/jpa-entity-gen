package br.com.mapper_gen.service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.lang.model.element.Modifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mapper_gen.lib.javapoet.JavaFile;
import br.com.mapper_gen.lib.javapoet.TypeSpec;

@Service
public class EntityService {
    
    @Autowired
    private DatabaseService databaseService;

    public Object createEntity(String name) throws IOException {
        var databaseObject = databaseService.getDatabaseObject(name);
        var classBuilder = TypeSpec.classBuilder("MyGeneratedClass")
            .addModifiers(Modifier.PUBLIC);
		
        if (Arrays.asList("VIEW", "TABLE").contains(databaseObject.type())) {
            databaseObject.attributes().forEach(attr -> {
                classBuilder.addField(String.class, attr.name(), Modifier.PRIVATE);
            });
        }
			
        JavaFile javaFile = JavaFile.builder("", classBuilder.build())
            .build();
        javaFile.writeToFile(new File(""));
        return true;
    }
}
