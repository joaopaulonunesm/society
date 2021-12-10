package com.society.agendamento.domain.models;

import com.society.agendamento.domain.enums.StatusSociety;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Society {

    private Long id;
    private Long quantidadeCampos;
    private StatusSociety status;

    public boolean estaAtivo(){
        return StatusSociety.ATIVO.equals(this.status);
    }
}
