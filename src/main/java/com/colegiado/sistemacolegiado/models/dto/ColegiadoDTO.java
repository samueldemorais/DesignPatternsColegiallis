package com.colegiado.sistemacolegiado.models.dto;

import com.colegiado.sistemacolegiado.models.Colegiado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColegiadoDTO {

    private Integer id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String descricao;
    private String portaria;
    private String curso;
    private List<ProfessorDTO> professores = new ArrayList<>();

    public ColegiadoDTO (Colegiado colegiado){
        this.id = colegiado.getId();
        this.dataInicio = colegiado.getDataInicio();
        this.dataFim = colegiado.getDataFim();
        this.descricao = colegiado.getDescricao();
        this.portaria = colegiado.getPortaria();
        this.curso = colegiado.getCurso();
        this.professores = colegiado.getProfessores().stream().map(ProfessorDTO::new).collect(Collectors.toList());
    }
}
