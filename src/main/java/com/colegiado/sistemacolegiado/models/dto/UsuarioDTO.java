package com.colegiado.sistemacolegiado.models.dto;

import com.colegiado.sistemacolegiado.models.Professor;
import com.colegiado.sistemacolegiado.models.Usuario;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioDTO {
    @NotEmpty(message = "nome é obrigatório")
    private String nome;
    @NotEmpty(message = "fone é obrigatório")
    private String fone;
    @NotEmpty(message = "matricula é obrigatório")
    private String matricula;
    @NotEmpty(message = "login é obrigatório")
    private String login;
    @NotEmpty(message = "senha é obrigatório")
    private String senha;
    private Boolean coordenador;

    @Override
    public String toString() {
        return super.toString();
    }
}
