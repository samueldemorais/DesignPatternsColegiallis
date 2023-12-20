package com.colegiado.sistemacolegiado.interfaces.states;

import com.colegiado.sistemacolegiado.interfaces.EstadoProcesso;
import com.colegiado.sistemacolegiado.models.Processo;
import com.colegiado.sistemacolegiado.models.enums.StatusProcesso;

public class Julgado implements EstadoProcesso {
    private Processo processo;

    public Julgado(Processo processo) {
        this.processo = processo;
    }

    @Override
    public StatusProcesso getEstado() {
        return StatusProcesso.JULGADO;
    }

    @Override
    public void avancarEstado(Processo processo) {
        throw new IllegalStateException("O processo já está no estado final (Julgado).");
        // Ou outra exceção apropriada, conforme a necessidade do contexto
    }
}

