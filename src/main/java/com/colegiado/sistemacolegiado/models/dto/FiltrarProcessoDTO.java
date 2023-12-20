package com.colegiado.sistemacolegiado.models.dto;

import com.colegiado.sistemacolegiado.models.enums.StatusProcesso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FiltrarProcessoDTO {
    private Integer idAluno;
    private Integer idProfessor;
    private StatusProcesso status;
    private Integer idAssunto;
}
