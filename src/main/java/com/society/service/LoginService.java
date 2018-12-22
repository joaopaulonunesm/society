package com.society.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.society.exception.BusinessException;
import com.society.model.Login;
import com.society.model.vo.LoginResponseVO;
import com.society.model.vo.LoginVO;
import com.society.model.vo.TrocarSenhaVO;
import com.society.repository.LoginRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;

	public Login buscarPorToken(String token) throws BusinessException {
		Login login = loginRepository.findByToken(token.substring(7));

		if (login == null) {
			throw new BusinessException("Login não encontrado por Token.", HttpStatus.UNAUTHORIZED);
		}

		return login;
	}

	public LoginResponseVO logar(LoginVO login) throws BusinessException {

		LoginResponseVO response = new LoginResponseVO();
		
		if (login.getEmail() == null || login.getSenha() == null) {
			throw new BusinessException("Email ou Senha inválido, favor tente novamente.", HttpStatus.UNAUTHORIZED);
		}

		Login loginExistente = loginRepository.findByEmail(login.getEmail());

		if (loginExistente == null) {

			throw new BusinessException("Email ainda não cadastrado, cadastre seu Society!", HttpStatus.UNAUTHORIZED);

		} else {
			if (!login.getSenha().equals(loginExistente.getSenha())) {
				throw new BusinessException("Combinação de Email e Senha está incorreto.", HttpStatus.UNAUTHORIZED);
			}
		}

		validaToken(loginExistente);
		
		response.setToken(loginExistente.getToken());

		return response;
	}

	public void trocarSenha(String token, TrocarSenhaVO trocarSenhaVO) throws BusinessException {

		Login login = buscarPorToken(token);

		if (!login.getSenha().equals(trocarSenhaVO.getSenhaAtual())) {
			throw new BusinessException("Senha atual incorreta.", HttpStatus.BAD_REQUEST);
		}

		if (login.getSenha().equals(trocarSenhaVO.getNovaSenha())) {
			throw new BusinessException("Senha informada esta em uso.", HttpStatus.BAD_REQUEST);
		}

		login.setSenha(trocarSenhaVO.getNovaSenha());

		loginRepository.save(login);

	}

	public Login completarLogin(Login login) {

		login.setSenha(String.valueOf(login.hashCode()));

		validaToken(login);

		return login;
	}

	public Login manterDados(Login loginExistente, Login login) {

		login.setId(loginExistente.getId());
		login.setToken(loginExistente.getToken());
		login.setSenha(loginExistente.getSenha());
		login.setDataExpiracaoToken(loginExistente.getDataExpiracaoToken());

		return login;
	}
	
	private Login validaToken(Login login) {

		if (login.getDataExpiracaoToken() == null || login.getDataExpiracaoToken().before(new Date())) {

			Date expirationDate = new Date(System.currentTimeMillis() + 240 * 60 * 1000);

			String token = Jwts.builder().setSubject(login.getEmail())
					.signWith(SignatureAlgorithm.HS512, "autenticando").setExpiration(expirationDate).compact();

			login.setToken(token);
			login.setDataExpiracaoToken(expirationDate);

		}

		return login;
	}

}
