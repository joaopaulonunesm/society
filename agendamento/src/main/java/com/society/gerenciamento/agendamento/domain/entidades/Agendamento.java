package com.society.gerenciamento.agendamento.domain.entidades;

import com.society.gerenciamento.agendamento.domain.enums.StatusAgendamento;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Agendamento {

    private Society society;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private StatusAgendamento status;

    public Agendamento(){
        this.status = StatusAgendamento.AGUARDANDO_SOCIETY;
    }

    public void definirDataInicio(LocalDateTime dataInicio){
        this.dataInicio = dataInicio;
    }

    public void definirDataFim(Long quantidadeHoras){
        this.dataFim = getDataInicio().plusHours(quantidadeHoras);
    }

    public void confirmarAgendamento() {
        this.status = StatusAgendamento.CONFIRMADO;
    }

    public void cancelar() {
        this.status = StatusAgendamento.CANCELADO;
    }
}
