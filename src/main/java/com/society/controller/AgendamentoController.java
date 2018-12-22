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
import com.society.model.Agendamento;
import com.society.model.vo.ResponseSocietyVO;
import com.society.service.AgendamentoService;

@Controller
public class AgendamentoController {
	@Autowired
	private AgendamentoService agendamentoService;

	@PostMapping("/agendamentos")
	public ResponseEntity<ResponseSocietyVO> criar(@RequestBody Agendamento agendamento) {

		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(agendamentoService.criar(agendamento)), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(new ResponseSocietyVO().withError(e.getMessage()), e.getHttpStatus());
		}
	}

	@PutMapping("/agendamentos/{id}")
	public ResponseEntity<ResponseSocietyVO> alterar(@PathVariable Long id, @RequestBody Agendamento agendamento) {

		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(agendamentoService.alterar(id, agendamento)), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(new ResponseSocietyVO().withError(e.getMessage()), e.getHttpStatus());
		}
	}
	
	@PutMapping("/agendamentos/{id}/confirmacao/{confirmacao}")
	public ResponseEntity<ResponseSocietyVO> confirmarOuCancelar(@PathVariable Long id, @PathVariable String confirmacao) {

		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(agendamentoService.confirmarOuCancelar(id, confirmacao)), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(new ResponseSocietyVO().withError(e.getMessage()), e.getHttpStatus());
		}
	}

	@GetMapping("/agendamentos/{id}")
	public ResponseEntity<ResponseSocietyVO> buscarPorId(@PathVariable Long id) {

		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(agendamentoService.buscarPorId(id)), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(new ResponseSocietyVO().withError(e.getMessage()), e.getHttpStatus());
		}
	}
	
	@GetMapping("/agendamentos/society/{idSociety}")
	public ResponseEntity<ResponseSocietyVO> buscarPorSociety(@PathVariable Long idSociety) {

		return new ResponseEntity<>(new ResponseSocietyVO().withData(agendamentoService.buscarPorSociety(idSociety)), HttpStatus.OK);
	}
	
	@GetMapping("/v1/agendamentos/society")
	public ResponseEntity<ResponseSocietyVO> buscaComTokenPorSociety(@RequestHeader(value = "Authorization") String token) {

		try {
			return new ResponseEntity<>(new ResponseSocietyVO().withData(agendamentoService.buscaComTokenPorSociety(token)), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(new ResponseSocietyVO().withError(e.getMessage()), e.getHttpStatus());
		}
	}
	
}