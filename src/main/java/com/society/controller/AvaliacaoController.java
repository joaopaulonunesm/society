package com.society.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.society.exception.BusinessException;
import com.society.model.AvaliacaoSociety;
import com.society.model.AvaliacaoUsuario;
import com.society.model.vo.ResponseSocietyVO;
import com.society.service.AvaliacaoService;

@Controller
public class AvaliacaoController {
	
	@Autowired
	private AvaliacaoService avaliacaoService;

	@PostMapping("/v1/avaliacao/society")
	public ResponseEntity<ResponseSocietyVO> avaliarSociety(@RequestHeader(value = "Authorization") String token, @RequestBody AvaliacaoSociety avaliacao) {
		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(avaliacaoService.avaliarSociety(token, avaliacao)), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>((new ResponseSocietyVO().withError(e.getMessage())), e.getHttpStatus());
		}
	}
	
	@PostMapping("/v1/avaliacao/usuario")
	public ResponseEntity<ResponseSocietyVO> avaliarUsuario(@RequestHeader(value = "Authorization") String token, @RequestBody AvaliacaoUsuario avaliacao) {
		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(avaliacaoService.avaliarUsuario(token, avaliacao)), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>((new ResponseSocietyVO().withError(e.getMessage())), e.getHttpStatus());
		}
	}
	
	@GetMapping("/avaliacao/society/{idSociety}")
	public ResponseEntity<ResponseSocietyVO> avaliacoesSociety(@PathVariable Long idSociety){
		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(avaliacaoService.avaliacoesSociety(idSociety)), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>((new ResponseSocietyVO().withError(e.getMessage())), e.getHttpStatus());
		}
	}
	
	@GetMapping("/avaliacao/usuario/{idUsuario}")
	public ResponseEntity<ResponseSocietyVO> avaliacoesUsuario(@PathVariable Long idUsuario){
		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(avaliacaoService.avaliacoesUsuario(idUsuario)), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>((new ResponseSocietyVO().withError(e.getMessage())), e.getHttpStatus());
		}
	}
	
	@GetMapping("/avaliacao/society")
	public ResponseEntity<ResponseSocietyVO> avaliacoesSocietyPorToken(@RequestHeader(value = "Authorization") String token){
		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(avaliacaoService.avaliacoesSociety(token)), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>((new ResponseSocietyVO().withError(e.getMessage())), e.getHttpStatus());
		}
	}
	
	@GetMapping("/avaliacao/usuario")
	public ResponseEntity<ResponseSocietyVO> avaliacoesUsuarioPorToken(@RequestHeader(value = "Authorization") String token){
		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(avaliacaoService.avaliacoesUsuario(token)), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>((new ResponseSocietyVO().withError(e.getMessage())), e.getHttpStatus());
		}
	}
	
	@GetMapping("/v1/avaliacao/usuario/pendente")
	public ResponseEntity<ResponseSocietyVO> avaliacoesPendentesDoUsuario(@RequestHeader(value = "Authorization") String token){
		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(avaliacaoService.avaliacoesPendentesDoUsuario(token)), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>((new ResponseSocietyVO().withError(e.getMessage())), e.getHttpStatus());
		}
	}
	
	@GetMapping("/v1/avaliacao/society/pendente")
	public ResponseEntity<ResponseSocietyVO> avaliacoesPendentesDoSociety(@RequestHeader(value = "Authorization") String token){
		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(avaliacaoService.avaliacoesPendentesDoSociety(token)), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>((new ResponseSocietyVO().withError(e.getMessage())), e.getHttpStatus());
		}
	}
	
}
