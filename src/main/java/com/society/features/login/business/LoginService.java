package com.society.features.login.business;

import com.society.features.login.domain.Login;
import com.society.features.login.domain.LoginDTO;
import com.society.features.login.domain.LoginResponseDTO;
import com.society.features.login.domain.TrocarSenhaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLSession;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final LoginBusiness loginBusiness;
	
	public LoginResponseDTO logar(LoginDTO loginDTO) {
		
		Login login = loginBusiness.logar(loginDTO);
		
		return new LoginResponseDTO(login);
	}

	public void trocarSenha(String token, TrocarSenhaDTO trocarSenhaDTO) {
		loginBusiness.trocarSenha(token, trocarSenhaDTO);
	}

	public LoginResponseDTO buscarPorTokenAPI(String token) {
		
		Login login = loginBusiness.buscarPorToken(token);
		
		return new LoginResponseDTO(login);
	}

	public Login completarLogin(Login login) {
		return loginBusiness.completarLogin(login);
	}

	public Login manterDados(Login loginExistente, Login login) {
		return loginBusiness.manterDados(loginExistente, login);
	}

	public Login buscarPorToken(String token) {
		return loginBusiness.buscarPorToken(token);
	}
}
