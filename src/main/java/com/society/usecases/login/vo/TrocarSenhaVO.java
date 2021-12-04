package com.society.usecases.login.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrocarSenhaVO {

	private String senhaAtual;
	private String novaSenha;
}
