package com.example.EJEMPLO_1.controller;

import com.example.EJEMPLO_1.entity.Persona;
import com.example.EJEMPLO_1.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    // Obtener todas las personas
    @GetMapping
    public List<Persona> getAllPersonas() {
        return personaService.getAllPersonas();
    }

    // Obtener una persona por ID
    @GetMapping("/{id}")
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
        Optional<Persona> persona = personaService.getPersonaById(id);
        return persona.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva persona
    @PostMapping
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
        Persona nuevaPersona = personaService.savePersona(persona);
        return new ResponseEntity<>(nuevaPersona, HttpStatus.CREATED);
    }

    // Actualizar una persona
    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona persona) {
        Optional<Persona> personaExistente = personaService.getPersonaById(id);
        if (personaExistente.isPresent()) {
            persona.setId(id);
            Persona personaActualizada = personaService.savePersona(persona);
            return ResponseEntity.ok(personaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una persona
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        Optional<Persona> personaExistente = personaService.getPersonaById(id);
        if (personaExistente.isPresent()) {
            personaService.deletePersona(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
