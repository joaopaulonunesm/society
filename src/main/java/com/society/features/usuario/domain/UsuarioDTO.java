package com.society.features.usuario.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

	private String email;
	private String senha;
	private String nome;
	private Long telefone;
	private Long celular;
}
