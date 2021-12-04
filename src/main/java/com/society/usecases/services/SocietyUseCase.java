package com.society.usecases.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.society.usecases.enums.StatusSociety;
import com.society.usecases.exceptions.UseCaseException;
import com.society.infrastructure.repositories.society.Society;
import com.society.infrastructure.repositories.society.SocietyRepository;

@Service
public class SocietyUseCase {

	@Autowired
	private SocietyRepository societyRepository;
	
	@Autowired
	private LoginUseCase loginUseCase;

	public Society criar(Society society) {
		
		society.setNomeUrl(society.getNome());
		society.setQuantidadeJogos(0l);
		society.setStatusSociety(StatusSociety.EM_ANALISE);
		
		society.setLogin(loginUseCase.completarLogin(society.getLogin()));

		return societyRepository.save(society);
	}

	public Society alterar(String token, Society society) throws UseCaseException {

		Society societyExistente = buscarPorToken(token);
		
		society.setId(societyExistente.getId());
		society.setStatusSociety(societyExistente.getStatusSociety());
		society.setNomeUrl(society.getNome());
		
		society.setLogin(loginUseCase.manterDados(societyExistente.getLogin(), society.getLogin()));
		
		return societyRepository.save(society);
	}

	public List<Society> buscarTodos() {
		return societyRepository.findAll();
	}

	public Society buscarPorId(Long id) throws UseCaseException {

		Optional<Society> busca = societyRepository.findById(id);

		if (!busca.isPresent()) {
			throw new UseCaseException("Não foi encontrado Society pelo ID", HttpStatus.NOT_FOUND);
		}

		return busca.get();
	}

	public Society buscarPorNomeUrl(String nomeUrl) throws UseCaseException {

		Optional<Society> busca = societyRepository.findByNomeUrl(nomeUrl);

		if (!busca.isPresent()) {
			throw new UseCaseException("Não foi encontrado Society pelo Nome Url", HttpStatus.NOT_FOUND);
		}

		return busca.get();
	}
	
	public Society buscarPorToken(String token) throws UseCaseException {
		
		return societyRepository.findByLoginId(loginUseCase.buscarPorToken(token).getId());
	}

	public void addQntJogo(Society society) throws UseCaseException {
		society = buscarPorId(society.getId());
		society.setQuantidadeJogos(society.getQuantidadeJogos() + 1);
		societyRepository.save(society);
	}

	public Society ativarOuInativar(Long id, StatusSociety confirmacao) throws UseCaseException {

		Society society = buscarPorId(id);
		
		society.setStatusSociety(confirmacao);
		
		return societyRepository.save(society);
	}

}
