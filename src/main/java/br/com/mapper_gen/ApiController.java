package br.com.mapper_gen;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mapper_gen.service.DatabaseService;
import br.com.mapper_gen.service.EntityService;

@RestController
@RequestMapping("api")
public class ApiController {
    
    @Autowired
    private DatabaseService databaseService;

    @Autowired
    private EntityService entityService;

    @GetMapping("object/{name}")
    public ResponseEntity<?> getObject(
        @PathVariable String name
    ) {
        return ResponseEntity.ok(databaseService.getDatabaseObject(name));
    }

    @PostMapping("object/{name}")
    public ResponseEntity<?> postObject(
        @PathVariable String name
    ) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(entityService.createEntity(name));
    }

    @GetMapping("tables")
    public ResponseEntity<?> getTables(
        @RequestParam(defaultValue = "") String name
    ) {
        return ResponseEntity.ok(databaseService.getTables(name));
    }

    @GetMapping("views")
    public ResponseEntity<?> getViews(
        @RequestParam(defaultValue = "") String name
    ) {
        return ResponseEntity.ok(databaseService.getViews(name));
    }

    @GetMapping("procedures")
    public ResponseEntity<?> getProcedures(
        @RequestParam(defaultValue = "") String name
    ) {
        return ResponseEntity.ok(databaseService.getProcedures(name));
    }

   
}
