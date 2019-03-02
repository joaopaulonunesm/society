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
import com.society.model.Usuario;
import com.society.model.vo.ResponseSocietyVO;
import com.society.model.vo.UsuarioVO;
import com.society.service.UsuarioService;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/usuario")
	public ResponseEntity<ResponseSocietyVO> criar(@RequestBody UsuarioVO usuarioVO) {

		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(usuarioService.criar(usuarioVO)),
					HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<>(new ResponseSocietyVO().withError(e.getMessage()), e.getHttpStatus());
		}
	}

	@PutMapping("/v1/usuario")
	public ResponseEntity<ResponseSocietyVO> alterar(@RequestHeader(value = "Authorization") String token,
			@RequestBody Usuario usuario) {

		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(usuarioService.alterar(token, usuario)),
					HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(new ResponseSocietyVO().withError(e.getMessage()), e.getHttpStatus());
		}
	}

	@GetMapping("/v1/usuario/token")
	public ResponseEntity<ResponseSocietyVO> buscaPorToken(@RequestHeader(value = "Authorization") String token) {

		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(usuarioService.buscarPorToken(token)),
					HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(new ResponseSocietyVO().withError(e.getMessage()), e.getHttpStatus());
		}
	}

}
