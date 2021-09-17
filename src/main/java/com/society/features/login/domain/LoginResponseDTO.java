package com.society.features.login.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDTO {

	private String token;
	private TipoLogin tipoLogin;

	public LoginResponseDTO(Login login) {
		this.token = login.getToken();
		this.tipoLogin = TipoLogin.buscarPorId(login.getTipoLogin());
	}
}
