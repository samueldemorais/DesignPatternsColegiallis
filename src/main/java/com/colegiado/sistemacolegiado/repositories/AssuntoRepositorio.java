package com.colegiado.sistemacolegiado.repositories;

import com.colegiado.sistemacolegiado.models.Assunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuntoRepositorio extends JpaRepository<Assunto, Integer> {
}
