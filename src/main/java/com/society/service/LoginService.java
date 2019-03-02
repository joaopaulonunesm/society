package com.society.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.business.LoginBusiness;
import com.society.exception.BusinessException;
import com.society.model.Login;
import com.society.model.vo.LoginResponseVO;
import com.society.model.vo.LoginVO;
import com.society.model.vo.TrocarSenhaVO;

@Service
public class LoginService {

	@Autowired
	private LoginBusiness loginBusiness;
	
	public LoginResponseVO logar(LoginVO loginVO) throws BusinessException {
		
		Login login = loginBusiness.logar(loginVO);
		
		return new LoginResponseVO(login);
	}

	public void trocarSenha(String token, TrocarSenhaVO trocarSenhaVO) throws BusinessException {
		loginBusiness.trocarSenha(token, trocarSenhaVO);		
	}

	public LoginResponseVO buscarPorTokenAPI(String token) throws BusinessException {
		
		Login login = loginBusiness.buscarPorToken(token);
		
		return new LoginResponseVO(login);
	}

}
