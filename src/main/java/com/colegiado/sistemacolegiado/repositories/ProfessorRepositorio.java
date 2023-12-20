package com.colegiado.sistemacolegiado.repositories;

import com.colegiado.sistemacolegiado.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepositorio extends JpaRepository<Professor, Integer> {
    boolean existsByfone (String fone);

    boolean existsBymatricula (String matricula);

    boolean existsBylogin (String login);
}
