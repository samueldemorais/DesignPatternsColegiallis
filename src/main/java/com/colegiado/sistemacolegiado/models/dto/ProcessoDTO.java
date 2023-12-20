package com.colegiado.sistemacolegiado.models.dto;

import com.colegiado.sistemacolegiado.models.Aluno;
import com.colegiado.sistemacolegiado.models.Assunto;
import com.colegiado.sistemacolegiado.models.Processo;
import com.colegiado.sistemacolegiado.models.enums.StatusProcesso;
import com.colegiado.sistemacolegiado.models.enums.TipoDecisao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoDTO {

    private Integer id;
    private LocalDate dataRecepcao;
    private LocalDate dataDistribuicao;
    private LocalDate dataParecer;
    private TipoDecisao parecer;
    private ProfessorDTO professor;
    private AlunoDTO aluno;
    private Assunto assunto;
    private String requerimento;
    private StatusProcesso status;

    public ProcessoDTO (Processo processo){
        this.id = processo.getId();
        this.dataRecepcao = processo.getDataRecepcao();
        this.dataDistribuicao = processo.getDataDistribuicao();
        this.dataParecer = processo.getDataParecer();
        if (processo.getProfessor() != null){
            this.professor = new ProfessorDTO(processo.getProfessor());
        }
        this.aluno = new AlunoDTO(processo.getAluno());
        this.assunto = processo.getAssunto();
        this.requerimento = processo.getRequerimento();
        this.status = StatusProcesso.valueOf(processo.getStatus());
    }

    public void setAluno (AlunoDTO newaluno){
        this.aluno = newaluno;

    }
}
