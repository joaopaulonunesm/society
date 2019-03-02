package com.society.model.vo;

import java.util.Date;

import com.society.enums.StatusAgendamento;
import com.society.model.Agendamento;
import com.society.model.Society;
import com.society.model.Usuario;

public class ResponseAgendamentoVO {

	private Long id;
	private Society society;
	private Usuario usuario;
	private Date dataInicio;
	private Date dataFim;
	private String observacao;
	private StatusAgendamento statusAgendamento;

	public ResponseAgendamentoVO(Agendamento agendamento) {
		this.id = agendamento.getId();
		this.society = agendamento.getSociety();
		this.usuario = agendamento.getUsuario();
		this.dataInicio = agendamento.getDataInicio();
		this.dataFim = agendamento.getDataFim();
		this.observacao = agendamento.getObservacao();
		this.statusAgendamento = agendamento.getStatusAgendamento();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Society getSociety() {
		return society;
	}

	public void setSociety(Society society) {
		this.society = society;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public StatusAgendamento getStatusAgendamento() {
		return statusAgendamento;
	}

	public void setStatusAgendamento(StatusAgendamento statusAgendamento) {
		this.statusAgendamento = statusAgendamento;
	}

}
