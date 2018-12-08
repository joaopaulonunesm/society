package com.society.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Agendamento {

	@Id
	@SequenceGenerator(name = "AGENDAMENTOSEQ", sequenceName = "AGENDAMENTO_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "AGENDAMENTOSEQ")
	private Long id;
	@OneToOne
	@JoinColumn(name = "id_society")
	private Society society;
	@Column(nullable = false)
	private String nomeResponsavel;
	private Long telefone;
	@Column(nullable = false)
	private Long celular;
	private String email;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dataInicio;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dataFim;
	private String observacao;

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

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public Long getCelular() {
		return celular;
	}

	public void setCelular(Long celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

}
