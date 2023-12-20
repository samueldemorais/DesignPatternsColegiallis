package com.colegiado.sistemacolegiado.interfaces.Proxy;

import com.colegiado.sistemacolegiado.interfaces.ConsultaProcessos;
import com.colegiado.sistemacolegiado.models.Processo;
import com.colegiado.sistemacolegiado.models.dto.FiltrarProcessoDTO;
import com.colegiado.sistemacolegiado.services.ProcessoService;

import java.util.List;

public class ProxyConsultaProcessosProfessor implements ConsultaProcessos {
    private ProcessoService processoService;
    private FiltrarProcessoDTO filtro;
    private Long idProfessor;

    public ProxyConsultaProcessosProfessor(ProcessoService processoService, FiltrarProcessoDTO filtro, Long idProfessor) {
        this.processoService = processoService;
        this.filtro = filtro;
        this.idProfessor = idProfessor;
    }

    @Override
    public List<Processo> consultarProcessos() {
        if (verificarAcesso()) {
            List<Processo> processos = processoService.listarProcessos(filtro);
            return processos;
        } else {
            throw new SecurityException("Acesso negado. Você não tem permissão para acessar estes processos.");
        }
    }


    private boolean verificarAcesso() {
        ProcessosConsulta processosConsulta = new ProcessosConsulta(processoService, filtro);
        return processosConsulta.verificarAcessoParaProfessor(idProfessor);
    }
}

