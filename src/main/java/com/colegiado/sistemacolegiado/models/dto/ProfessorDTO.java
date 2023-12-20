package com.colegiado.sistemacolegiado.models.dto;

import com.colegiado.sistemacolegiado.models.Professor;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorDTO {
    private int id;
    private String nome;
    private String fone;
    private String matricula;
    private String login;
    private String senha;
    private boolean coordenador;
//    private Integer colegiadoId;

    public ProfessorDTO (Professor professor){
        this.id = professor.getId();
        this.nome = professor.getNome();
        this.fone = professor.getFone();
        this.matricula = professor.getMatricula();
        this.login = professor.getLogin();
        this.senha = professor.getSenha();
        this.coordenador = professor.isCoordenador();
//        this.colegiadoId = professor.getColegiado().getId();
    }
}
