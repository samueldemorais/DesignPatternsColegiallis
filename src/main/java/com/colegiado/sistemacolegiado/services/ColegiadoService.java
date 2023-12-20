package com.colegiado.sistemacolegiado.services;

import com.colegiado.sistemacolegiado.models.Colegiado;
import com.colegiado.sistemacolegiado.models.Professor;
import com.colegiado.sistemacolegiado.models.dto.CriarColegiadoDTO;
import com.colegiado.sistemacolegiado.repositories.ColegiadoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ColegiadoService {
    final ColegiadoRepositorio colegiadoRepositorio;
    final ProfessorService professorService;

    public Colegiado criarColegiado(CriarColegiadoDTO colegiadoDTO){
        return this.colegiadoRepositorio.save(new Colegiado(colegiadoDTO));
    }

    public List<Colegiado> listarColegiado(){
        return this.colegiadoRepositorio.findAll();
    }

    public boolean temcolegiado(Integer id){
        Professor verificarprofessor = professorService.encontrarPorId(id);
        if(verificarprofessor.getColegiado() != null){
            return true;
        }
        return false;
    }

    public Colegiado encontrarPorId(int id){
        return this.colegiadoRepositorio.findById(id).orElseThrow(() -> new RuntimeException("Colegiado não encotrado"));
    }

    public void deletarColegiado(Integer id){
        var colegiado = encontrarPorId(id);
        colegiado.getProfessores().clear();
        colegiadoRepositorio.save(colegiado);
        this.colegiadoRepositorio.delete(colegiado);
    }

    public Colegiado adicionarProfessor(int idColegiado, int idProfessor){
        Professor professor = professorService.encontrarPorId(idProfessor);
        Colegiado colegiado = encontrarPorId(idColegiado);
        colegiado.getProfessores().add(professor);
        professor.setColegiado(colegiado);
        return this.colegiadoRepositorio.save(colegiado);
    }

    public Colegiado removerProfessor(int idColegiado, int idProfessor){
        Professor professor = professorService.encontrarPorId(idProfessor);
        Colegiado colegiado = encontrarPorId(idColegiado);
        if (colegiado.getProfessores().contains(professor)) {
            colegiado.getProfessores().remove(professor);
        } else {
            throw new RuntimeException("O professor não está associado a este colegiado");
        }
        return this.colegiadoRepositorio.save(colegiado);
    }

    public Colegiado atualizarColegiado(Integer id, CriarColegiadoDTO colegiadoDTO) {
        Colegiado colegiado = encontrarPorId(id);
        colegiado.setDescricao(colegiadoDTO.getDescricao());
        colegiado.setPortaria(colegiadoDTO.getPortaria());
        colegiado.setCurso(colegiadoDTO.getCurso());
        return colegiadoRepositorio.save(colegiado);
    }
}
