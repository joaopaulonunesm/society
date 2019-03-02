package com.society.model.vo;

import com.society.enums.TipoLogin;
import com.society.model.Login;

public class LoginResponseVO {

	private String token;
	private TipoLogin tipoLogin;
	
	public LoginResponseVO(Login login) {
		this.token = login.getToken();
		this.tipoLogin = login.getTipoLogin();
	}

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
