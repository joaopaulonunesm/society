package com.society.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.society.exception.BusinessException;
import com.society.model.Agendamento;
import com.society.service.AgendamentoService;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoController {

	@Autowired
	private AgendamentoService agendamentoService;

	@PostMapping
	public ResponseEntity<Agendamento> salvar(@RequestBody Agendamento agendamento) throws BusinessException {

		return new ResponseEntity<>(agendamentoService.salvar(agendamento), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Agendamento> alterar(@PathVariable Long id, @RequestBody Agendamento agendamento)
			throws BusinessException {

		return new ResponseEntity<>(agendamentoService.alterar(id, agendamento), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Agendamento> deletar(@PathVariable Long id) throws BusinessException {

		agendamentoService.deletar(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Agendamento>> buscarTodos() {

		return new ResponseEntity<>(agendamentoService.buscarTodos(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Agendamento> buscarPorId(@PathVariable Long id) throws BusinessException {

		return new ResponseEntity<>(agendamentoService.buscarPorId(id), HttpStatus.OK);
	}
	
	@GetMapping("/society/{idSociety}")
	public ResponseEntity<List<Agendamento>> buscarPorSociety(@PathVariable Long idSociety) {

		return new ResponseEntity<>(agendamentoService.buscarPorSociety(idSociety), HttpStatus.OK);
	}
}