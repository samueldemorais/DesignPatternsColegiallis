package com.colegiado.sistemacolegiado.models.Voto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class VotoId implements Serializable {
    @Column(name = "id_professor")
    private int idProfessor;
    @Column(name ="id_processo")
    private int idProcesso;
}