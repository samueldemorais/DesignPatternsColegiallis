package com.colegiado.sistemacolegiado.services;

import com.colegiado.sistemacolegiado.models.Aluno;
import com.colegiado.sistemacolegiado.models.Colegiado;
import com.colegiado.sistemacolegiado.models.dto.UsuarioDTO;
import com.colegiado.sistemacolegiado.repositories.AlunoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    final AlunoRepositorio alunoRepository;


    public AlunoService (AlunoRepositorio alunoRepository){
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public Aluno criarAluno(UsuarioDTO alunoDTO){
        return  this.alunoRepository.save(new Aluno(alunoDTO));
    }

    public Aluno encontrarPorId(int id){
        return alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public List<Aluno> listarAlunos(){
        return  alunoRepository.findAll();
    }

    public void deletarAluno(Aluno aluno){
        alunoRepository.delete(aluno);
    }

    public boolean verificarTelefone(String fone){
        return alunoRepository.existsByfone(fone);
    }

    public boolean verificarMatricula(String matricula) {
        return  alunoRepository.existsBymatricula((matricula));
    }

    public  boolean verificarLogin(String login){
        return alunoRepository.existsBylogin(login);
    }


    public Aluno atualizarAluno(Integer id, UsuarioDTO alunoDTO) {
        Aluno aluno = encontrarPorId(id);
        aluno.setNome(alunoDTO.getNome());
        aluno.setFone(alunoDTO.getFone());
        aluno.setMatricula(alunoDTO.getMatricula());
        aluno.setLogin(alunoDTO.getLogin());
        aluno.setSenha(alunoDTO.getSenha());
        return alunoRepository.save(aluno);
    }


}
