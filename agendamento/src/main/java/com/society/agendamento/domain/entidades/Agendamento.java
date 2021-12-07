package com.society.agendamento.domain.entidades;

import com.society.agendamento.domain.enums.StatusAgendamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Agendamento {

    private Long id;
    private Society society;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private StatusAgendamento status;
    private String nomeResponsavel;
    private String celular;
    private String celularSecundario;
    private String email;
    private String observacao;

    public void definirId(Long id) {
        this.id = id;
    }

    public void definirDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void definirDataFim(Long quantidadeHoras) {
        this.dataFim = getDataInicio().plusHours(quantidadeHoras);
    }

    public void definirStatusInicial() {
        this.status = StatusAgendamento.AGUARDANDO_SOCIETY;
    }

    public void confirmarAgendamento() {
        this.status = StatusAgendamento.CONFIRMADO;
    }

    public void cancelar() {
        this.status = StatusAgendamento.CANCELADO;
    }
}
