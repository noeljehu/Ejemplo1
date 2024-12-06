package com.example.EJEMPLO_1.repository;


import com.example.EJEMPLO_1.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    // No es necesario agregar métodos adicionales si solo necesitas operaciones CRUD básicas
}