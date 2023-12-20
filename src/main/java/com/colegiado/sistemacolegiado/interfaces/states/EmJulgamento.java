package com.colegiado.sistemacolegiado.interfaces.states;


import com.colegiado.sistemacolegiado.interfaces.EstadoProcesso;
import com.colegiado.sistemacolegiado.models.Processo;
import com.colegiado.sistemacolegiado.models.enums.StatusProcesso;

public class EmJulgamento implements EstadoProcesso {
    private Processo processo;

    public EmJulgamento(Processo processo) {
        this.processo = processo;
    }

    @Override
    public StatusProcesso getEstado() {
        return StatusProcesso.DISTRIBUIDO;
    }

    @Override
    public void avancarEstado(Processo processo) {
        processo.setStatus(new Julgado(processo));
    }
}