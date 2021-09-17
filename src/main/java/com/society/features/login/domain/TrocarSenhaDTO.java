package com.society.features.login.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrocarSenhaDTO {

	private String senhaAtual;
	private String novaSenha;
}
