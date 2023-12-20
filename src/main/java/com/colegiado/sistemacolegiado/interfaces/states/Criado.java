package com.colegiado.sistemacolegiado.interfaces.states;

import com.colegiado.sistemacolegiado.interfaces.EstadoProcesso;
import com.colegiado.sistemacolegiado.models.Processo;
import com.colegiado.sistemacolegiado.models.enums.StatusProcesso;

public class Criado implements EstadoProcesso {
    private Processo processo;

    public Criado(Processo processo) {
        this.processo = processo;
    }

    @Override
    public StatusProcesso getEstado() {
        return StatusProcesso.CRIADO;
    }

    @Override
    public void avancarEstado(Processo processo) {
        processo.setStatus(new Distribuido(processo));
    }
}
