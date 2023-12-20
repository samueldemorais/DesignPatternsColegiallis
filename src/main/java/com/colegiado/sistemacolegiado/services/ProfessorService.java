package com.colegiado.sistemacolegiado.services;

import com.colegiado.sistemacolegiado.models.Aluno;
import com.colegiado.sistemacolegiado.models.Colegiado;
import com.colegiado.sistemacolegiado.models.Professor;
import com.colegiado.sistemacolegiado.models.dto.ProfessorDTO;
import com.colegiado.sistemacolegiado.models.dto.UsuarioDTO;
import com.colegiado.sistemacolegiado.repositories.ProfessorRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfessorService {
    final ProfessorRepositorio professorRepositorio;
    //final  ColegiadoService colegiadoService;

    public Professor criarProfessor(UsuarioDTO professorDTO, Colegiado colegiado){
        //Colegiado colegiado = colegiadoService.encontrarPorId(idcolegiado);
        Professor professor = new Professor(professorDTO);
        professor.setColegiado(colegiado);
        return professorRepositorio.save(professor);
    }

    public Professor encontrarPorId(int id){
        return professorRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    }

    public List<Professor> listarProfessores(){
        return professorRepositorio.findAll();
    }

    public void deletarProfessores(Professor professor){
        professorRepositorio.delete(professor);
    }

    public boolean verificarTelefone(String fone){
        return professorRepositorio.existsByfone(fone);
    }
    public boolean verificarMatricula(String matricula) {
        return  professorRepositorio.existsBymatricula((matricula));
    }

    public  boolean verificarLogin(String login){
        return professorRepositorio.existsBylogin(login);
    }

    public Professor atualizarProfessor(Integer id, UsuarioDTO professorDTO) {
        Professor professor = encontrarPorId(id);
        professor.setNome(professorDTO.getNome());
        professor.setFone(professorDTO.getFone());
        professor.setMatricula(professorDTO.getMatricula());
        professor.setLogin(professorDTO.getLogin());
        professor.setSenha(professorDTO.getSenha());
        professor.setCoordenador(professorDTO.getCoordenador());
        return professorRepositorio.save(professor);
    }
}
