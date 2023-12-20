package com.colegiado.sistemacolegiado.interfaces;

import com.colegiado.sistemacolegiado.models.Processo;
import com.colegiado.sistemacolegiado.models.enums.StatusProcesso;

public interface EstadoProcesso {
    StatusProcesso getEstado();
    void avancarEstado(Processo processo);

}
