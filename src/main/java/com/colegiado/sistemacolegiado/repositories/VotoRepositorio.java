package com.colegiado.sistemacolegiado.repositories;

import com.colegiado.sistemacolegiado.models.Voto.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepositorio extends JpaRepository<Voto, Integer> {
}
