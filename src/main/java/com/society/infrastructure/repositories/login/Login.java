package com.society.infrastructure.repositories.login;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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
}