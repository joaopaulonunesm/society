package com.society.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.society.enums.StatusSociety;

@Entity
public class Society {

	@Id
	@SequenceGenerator(name = "SOCIETYSEQ", sequenceName = "SOCIETY_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SOCIETYSEQ")
	private Long id;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_login")
	private Login login;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String nomeUrl;
	private String descricao;
	@Column(nullable = false)
	private Double valorHora;
	@Column(nullable = false)
	private String nomeResponsavel;
	@Column(nullable = false)
	private Long celular;
	private Long telefone;
	@Column(nullable = false)
	private String endereco;
	@Column(nullable = false)
	private Integer cep;
	private String observacao;
	@Column(nullable = false)
	private String urlFoto;
	private Long quantidadeJogos;
	@Column(nullable = false)
	private Integer quantidadeCampos;
	private Integer statusSociety;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeUrl() {
		return nomeUrl;
	}

	public void setNomeUrl(String nomeUrl) {
		this.nomeUrl = nomeUrl.replaceAll(" ", "-").replaceAll("[ãâàáä]", "a").replaceAll("[êèéë]", "e")
				.replaceAll("[îìíï]", "i").replaceAll("[õôòóö]", "o").replaceAll("[ûúùü]", "u")
				.replaceAll("[ÃÂÀÁÄ]", "A").replaceAll("[ÊÈÉË]", "E").replaceAll("[ÎÌÍÏ]", "I")
				.replaceAll("[ÕÔÒÓÖ]", "O").replaceAll("[ÛÙÚÜ]", "U").replace('ç', 'c').replace('Ç', 'C')
				.replace('ñ', 'n').replace('Ñ', 'N').toLowerCase();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValorHora() {
		return valorHora;
	}

	public void setValorHora(Double valorHora) {
		this.valorHora = valorHora;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public Long getCelular() {
		return celular;
	}

	public void setCelular(Long celular) {
		this.celular = celular;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public Long getQuantidadeJogos() {
		return quantidadeJogos;
	}

	public void setQuantidadeJogos(Long quantidadeJogos) {
		this.quantidadeJogos = quantidadeJogos;
	}

	public Integer getQuantidadeCampos() {
		return quantidadeCampos;
	}

	public void setQuantidadeCampos(Integer quantidadeCampos) {
		this.quantidadeCampos = quantidadeCampos;
	}

	public StatusSociety getStatusSociety() {
		return StatusSociety.buscarPorId(statusSociety);
	}

	public void setStatusSociety(StatusSociety statusSociety) {
		this.statusSociety = statusSociety.getId();
	}

}
