package com.society.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.society.exception.BusinessException;
import com.society.model.vo.LoginVO;
import com.society.model.vo.ResponseSocietyVO;
import com.society.model.vo.TrocarSenhaVO;
import com.society.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<ResponseSocietyVO> logar(@RequestBody LoginVO login) {

		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(loginService.logar(login)), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>((new ResponseSocietyVO().withError(e.getMessage())), e.getHttpStatus());
		}
	}
	
	@PutMapping("/v1/login")
	public ResponseEntity<ResponseSocietyVO> trocarSenha(@RequestBody TrocarSenhaVO trocarSenhaVO, @RequestHeader(value = "Authorization") String token){
		
		try {
			loginService.trocarSenha(token, trocarSenhaVO);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(new ResponseSocietyVO().withError(e.getMessage()), e.getHttpStatus());
		}
	}
	
	@GetMapping("/v1/login")
	public ResponseEntity<ResponseSocietyVO> buscarPorToken(@RequestHeader(value = "Authorization") String token){
		
		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(loginService.buscarPorTokenAPI(token)), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(new ResponseSocietyVO().withError(e.getMessage()), e.getHttpStatus());
		}
	}

}
