package com.colegiado.sistemacolegiado.repositories;

import com.colegiado.sistemacolegiado.models.Colegiado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColegiadoRepositorio extends JpaRepository<Colegiado, Integer> {

}
