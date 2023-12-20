package com.colegiado.sistemacolegiado.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CriarProcessoDTO {
    @NotEmpty(message = "Requerimento é obrigatório")
    private String requerimento;
    @NotNull(message = "assunto é obrigatório")
    private Integer idAssunto;
    @NotNull(message = "Aluno é obrigatório")
    private Integer idAluno;

    public String toString(){
        return "Requerimento: " + requerimento + "\n" +
                "id assunto: " + idAssunto + "\n" +
                "id aluno: " + idAluno + "\n";
    }
}
