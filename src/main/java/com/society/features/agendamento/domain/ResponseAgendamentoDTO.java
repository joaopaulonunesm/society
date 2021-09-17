package com.society.features.agendamento.domain;

import java.time.LocalDateTime;

import com.society.features.society.domain.Society;
import com.society.features.usuario.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAgendamentoDTO {

	private Long id;
	private Society society;
	private Usuario usuario;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	private String observacao;
	private StatusAgendamento statusAgendamento;

	public ResponseAgendamentoDTO(Agendamento agendamento) {
		this.id = agendamento.getId();
		this.society = agendamento.getSociety();
		this.usuario = agendamento.getUsuario();
		this.dataInicio = agendamento.getDataInicio();
		this.dataFim = agendamento.getDataFim();
		this.observacao = agendamento.getObservacao();
		this.statusAgendamento = StatusAgendamento.buscarPorId(agendamento.getStatusAgendamento());
	}
}
