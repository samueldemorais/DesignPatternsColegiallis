package com.colegiado.sistemacolegiado.interfaces.Proxy;

import com.colegiado.sistemacolegiado.interfaces.ConsultaProcessos;
import com.colegiado.sistemacolegiado.models.Processo;
import com.colegiado.sistemacolegiado.models.dto.FiltrarProcessoDTO;
import com.colegiado.sistemacolegiado.services.ProcessoService;

import java.util.List;

public class ProcessosConsulta implements ConsultaProcessos {
    private ProcessoService processoService;
    private FiltrarProcessoDTO filtro;

    public ProcessosConsulta(ProcessoService processoService, FiltrarProcessoDTO filtro) {
        this.processoService = processoService;
        this.filtro = filtro;
    }

    @Override
    public List<Processo> consultarProcessos() {
        List<Processo> processos = processoService.listarProcessos(filtro);
        return processos;
    }

    public boolean verificarAcessoParaProfessor(Long idProfessor) {
        return filtro.getIdProfessor() != null && filtro.getIdProfessor().equals(idProfessor);
    }
}

