package com.colegiado.sistemacolegiado.models.dto;

import com.colegiado.sistemacolegiado.models.Assunto;
import com.colegiado.sistemacolegiado.models.Colegiado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CriarColegiadoDTO {
    private int id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String descricao;
    private String portaria;
    private String curso;

    public CriarColegiadoDTO(Colegiado colegiado) {
        this.id = colegiado.getId();
        this.dataInicio = colegiado.getDataInicio();
        this.dataFim = colegiado.getDataFim();
        this.descricao = colegiado.getDescricao();
        this.portaria = colegiado.getPortaria();
        this.curso = colegiado.getCurso();
    }
}
