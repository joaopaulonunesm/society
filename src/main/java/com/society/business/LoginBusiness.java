package com.society.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.society.exception.BusinessException;
import com.society.model.Login;
import com.society.model.vo.LoginVO;
import com.society.model.vo.TrocarSenhaVO;
import com.society.repository.LoginRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class LoginBusiness {

	@Autowired
	private LoginRepository loginRepository;
	
	public Login buscarPorToken(String token) throws BusinessException {
		
		Login login = loginRepository.findByToken(token.contains("Bearer ") ? token.substring(7) : token);

		if (login == null) {
			throw new BusinessException("Login não encontrado por Token.", HttpStatus.UNAUTHORIZED);
		}

		return login;
	}
	
	public Login logar(LoginVO loginVO) throws BusinessException {

		if (loginVO.getEmail() == null || loginVO.getSenha() == null) {
			throw new BusinessException("Email ou Senha inválido, favor tente novamente.", HttpStatus.UNAUTHORIZED);
		}

		Login login = loginRepository.findByEmail(loginVO.getEmail());

		if (login == null) {

			throw new BusinessException("Email ainda não cadastrado, cadastre seu Society!", HttpStatus.UNAUTHORIZED);

		} else {
			if (!loginVO.getSenha().equals(login.getSenha())) {
				throw new BusinessException("Combinação de Email e Senha está incorreto.", HttpStatus.UNAUTHORIZED);
			}
		}

		return validaToken(login, true);
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

	public Login completarLogin(Login login) throws BusinessException {

		if(loginRepository.findByEmail(login.getEmail()) != null) {
			throw new BusinessException("Email já cadastrado, por favor, informe outro email.", HttpStatus.BAD_REQUEST);
		}
		
		login.setSenha(login.getSenha() == null ? String.valueOf(login.hashCode()) : login.getSenha());

		validaToken(login, false);
		
		return login;
	}

	public Login manterDados(Login loginExistente, Login login) {

		login.setId(loginExistente.getId());
		login.setToken(loginExistente.getToken());
		login.setSenha(loginExistente.getSenha());
		login.setDataExpiracaoToken(loginExistente.getDataExpiracaoToken());

		return login;
	}
	
	private Login validaToken(Login login, boolean comitar) {

		if (login.getDataExpiracaoToken() == null || login.getDataExpiracaoToken().before(new Date())) {

			Date expirationDate = new Date(System.currentTimeMillis() + 240 * 60 * 1000);

			String token = Jwts.builder().setSubject(login.getEmail())
					.signWith(SignatureAlgorithm.HS512, "autenticando").setExpiration(expirationDate).compact();

			login.setToken(token);
			login.setDataExpiracaoToken(expirationDate);
			
			if(comitar) {
				loginRepository.save(login);
			}
			
		}

		return login;
	}

}
