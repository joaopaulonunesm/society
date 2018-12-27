package com.society.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.society.exception.BusinessException;
import com.society.model.Society;
import com.society.model.vo.ResponseSocietyVO;
import com.society.service.SocietyService;

@Controller
public class SocietyController {

	@Autowired
	private SocietyService societyService;

	@PostMapping("/society")
	public ResponseEntity<ResponseSocietyVO> criar(@RequestBody Society society) {

		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(societyService.criar(society)), HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<>(new ResponseSocietyVO().withError(e.getMessage()), e.getHttpStatus());
		}
	}

	@PutMapping("/v1/society")
	public ResponseEntity<ResponseSocietyVO> alterar(@RequestHeader(value = "Authorization") String token, @RequestBody Society society) {

		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(societyService.alterar(token, society)), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(new ResponseSocietyVO().withError(e.getMessage()), e.getHttpStatus());
		}
	}

	@PutMapping("/society/{id}/confirmacao/{confirmacao}")
	public ResponseEntity<ResponseSocietyVO> confirmarOuRecusar(@PathVariable Long id, @PathVariable String confirmacao) {

		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(societyService.ativarOuInativar(id, confirmacao)), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(new ResponseSocietyVO().withError(e.getMessage()), e.getHttpStatus());
		}
	}
	
	@GetMapping("/society")
	public ResponseEntity<ResponseSocietyVO> buscarTodos() {
		
		return new ResponseEntity<>(new ResponseSocietyVO().withData(societyService.buscarTodos()), HttpStatus.OK);
	}

	@GetMapping("/society/nome/{nomeUrl}")
	public ResponseEntity<ResponseSocietyVO> buscarPorNomeUrl(@PathVariable String nomeUrl) {

		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(societyService.buscarPorNomeUrl(nomeUrl)), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(new ResponseSocietyVO().withError(e.getMessage()), e.getHttpStatus());
		}
	}
	
	@GetMapping("/v1/society/token")
	public ResponseEntity<ResponseSocietyVO> buscaPorToken(@RequestHeader(value = "Authorization") String token) {

		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(societyService.buscarPorToken(token)), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(new ResponseSocietyVO().withError(e.getMessage()), e.getHttpStatus());
		}
	}
	
}
