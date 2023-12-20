package com.colegiado.sistemacolegiado.interfaces.Mediator;

import com.colegiado.sistemacolegiado.interfaces.MediatorProcesso;
import com.colegiado.sistemacolegiado.models.Professor;
import com.colegiado.sistemacolegiado.services.ProcessoService;
import com.colegiado.sistemacolegiado.services.ProfessorService;

// ConcreteMediator (Coordenador)
public class Coordenador implements MediatorProcesso {
    private ProcessoService processoService;
    private ProfessorService professorService;

    public Coordenador(ProcessoService processoService, ProfessorService professorService) {
        this.processoService = processoService;
        this.professorService = professorService;
    }

    @Override
    public void atribuirProcesso(Integer idProcesso, Integer idProfessor) {
        Professor professor = professorService.encontrarPorId(idProfessor);

        if ((professor.isCoordenador())) {
            processoService.atribuirProcesso(idProcesso, idProfessor);
        } else {
            throw new RuntimeException("Professor não tem permissão para atribuir processos.");
        }
    }
}

