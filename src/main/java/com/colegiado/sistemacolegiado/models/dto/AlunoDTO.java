package com.colegiado.sistemacolegiado.models.dto;

import com.colegiado.sistemacolegiado.models.Aluno;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO {
    private int id;
    private String nome;
    private String fone;
    private String matricula;
    private String login;
    @NotEmpty(message = "senha é obrigatório")
    private String senha;

    public AlunoDTO (Aluno aluno){
        this.id = aluno.getId();
        this.nome = aluno.getNome();
        this.fone = aluno.getFone();
        this.matricula = aluno.getMatricula();
        this.login = aluno.getLogin();
        this.senha = aluno.getSenha();
    }

    public String toString(){
        return "id: " + id + "\n" +
                "Nome: " + nome + "\n" +
                "fone: " + fone + "\n" +
                "matricula: " + matricula + "\n" +
                "login: " + login + "\n" +
                "senha: " + senha + "\n";
    }
}
