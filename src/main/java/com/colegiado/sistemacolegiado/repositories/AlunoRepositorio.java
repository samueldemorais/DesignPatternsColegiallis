package com.colegiado.sistemacolegiado.repositories;

import com.colegiado.sistemacolegiado.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepositorio extends JpaRepository<Aluno, Integer> {

    boolean existsByfone (String fone);

    boolean existsBymatricula (String matricula);

    boolean existsBylogin (String login);

}
