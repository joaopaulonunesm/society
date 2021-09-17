package com.society.features.login.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
	private LocalDateTime dataExpiracaoToken;
	private Integer tipoLogin;

	public Login(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
}