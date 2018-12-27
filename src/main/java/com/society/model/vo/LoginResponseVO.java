package com.society.model.vo;

import com.society.enums.TipoLogin;

public class LoginResponseVO {

	private String token;
	private TipoLogin tipoLogin;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TipoLogin getTipoLogin() {
		return tipoLogin;
	}

	public void setTipoLogin(TipoLogin tipoLogin) {
		this.tipoLogin = tipoLogin;
	}

}
