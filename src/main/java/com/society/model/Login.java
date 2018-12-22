package com.society.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Login {

	@Id
	@SequenceGenerator(name = "LOGINSEQ", sequenceName = "LOGIN_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "LOGINSEQ")
	@JsonIgnore
	private Long id;
	@Column(nullable = false)
	private String email;
	@JsonIgnore
	@Column(nullable = false)
	private String senha;
	@JsonIgnore
	@Column(nullable = false)
	private String token;
	@JsonIgnore
	@Column(nullable = false)
	private Date dataExpiracaoToken;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getDataExpiracaoToken() {
		return dataExpiracaoToken;
	}

	public void setDataExpiracaoToken(Date dataExpiracaoToken) {
		this.dataExpiracaoToken = dataExpiracaoToken;
	}

}