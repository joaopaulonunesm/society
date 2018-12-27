package com.society.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.society.enums.StatusSociety;
import com.society.exception.BusinessException;
import com.society.model.Society;
import com.society.repository.SocietyRepository;

@Service
public class SocietyService {

	@Autowired
	private SocietyRepository societyRepository;
	
	@Autowired
	private LoginService loginService;

	public Society criar(Society society) throws BusinessException {
		
		society.setNomeUrl(society.getNome());
		society.setQuantidadeJogos(0l);
		society.setStatusSociety(StatusSociety.EM_ANALISE);
		
		society.setLogin(loginService.completarLogin(society.getLogin()));

		return societyRepository.save(society);
	}

	public Society alterar(String token, Society society) throws BusinessException {

		Society societyExistente = buscarPorToken(token);
		
		society.setId(societyExistente.getId());
		society.setStatusSociety(societyExistente.getStatusSociety());
		society.setNomeUrl(society.getNome());
		
		society.setLogin(loginService.manterDados(societyExistente.getLogin(), society.getLogin()));
		
		return societyRepository.save(society);
	}

	public List<Society> buscarTodos() {
		return societyRepository.findAll();
	}

	public Society buscarPorId(Long id) throws BusinessException {

		Optional<Society> busca = societyRepository.findById(id);

		if (!busca.isPresent()) {
			throw new BusinessException("Não foi encontrado Society pelo ID", HttpStatus.NOT_FOUND);
		}

		return busca.get();
	}

	public Society buscarPorNomeUrl(String nomeUrl) throws BusinessException {

		Optional<Society> busca = societyRepository.findByNomeUrl(nomeUrl);

		if (!busca.isPresent()) {
			throw new BusinessException("Não foi encontrado Society pelo Nome Url", HttpStatus.NOT_FOUND);
		}

		return busca.get();
	}
	
	public Society buscarPorToken(String token) throws BusinessException {
		
		return societyRepository.findByLoginId(loginService.buscarPorToken(token).getId());
	}

	public void addQntJogo(Society society) throws BusinessException {
		society = buscarPorId(society.getId());
		society.setQuantidadeJogos(society.getQuantidadeJogos() + 1);
		societyRepository.save(society);
	}

	public Society ativarOuInativar(Long id, String confirmacao) throws BusinessException {

		if(!confirmacao.equalsIgnoreCase(StatusSociety.ATIVO.toString()) || !confirmacao.equalsIgnoreCase(StatusSociety.INATIVO.toString())) {
			throw new BusinessException("Parametro " + confirmacao + " é inválido para Ativação ou Inativação!", HttpStatus.BAD_REQUEST);
		}
		
		Society society = buscarPorId(id);
		
		society.setStatusSociety(StatusSociety.getPorString(confirmacao));
		
		return societyRepository.save(society);
	}

}
