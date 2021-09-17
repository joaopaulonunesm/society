package com.society.features.usuario.domain;

import com.society.features.login.domain.Login;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {

	@Id
	@SequenceGenerator(name = "USUARIOSEQ", sequenceName = "USUARIO_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USUARIOSEQ")
	private Long id;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_login")
	private Login login;
	@Column(nullable = false)
	private String nome;
	private Long telefone;
	@Column(nullable = false)
	private Long celular;
}
