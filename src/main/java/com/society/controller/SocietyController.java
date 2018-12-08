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
import com.society.model.Society;
import com.society.service.SocietyService;

@Controller
@RequestMapping("/society")
public class SocietyController {

	@Autowired
	private SocietyService societyService;

	@PostMapping
	public ResponseEntity<Society> salvar(@RequestBody Society society) {

		return new ResponseEntity<>(societyService.salvar(society), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Society> alterar(@PathVariable Long id, @RequestBody Society society) throws BusinessException {

		return new ResponseEntity<>(societyService.alterar(id, society), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Society> deletar(@PathVariable Long id) throws BusinessException {

		societyService.deletar(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Society>> buscarTodos() {

		return new ResponseEntity<>(societyService.buscarTodos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Society> buscarPorId(@PathVariable Long id) throws BusinessException {

		return new ResponseEntity<>(societyService.buscarPorId(id), HttpStatus.OK);
	}

	@GetMapping("/nome/{nomeUrl}")
	public ResponseEntity<Society> buscarPorNomeUrl(@PathVariable String nomeUrl) throws BusinessException {

		return new ResponseEntity<>(societyService.buscarPorNomeUrl(nomeUrl), HttpStatus.OK);
	}
}
