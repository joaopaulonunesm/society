package com.society.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.exception.BusinessException;
import com.society.model.Society;
import com.society.repository.SocietyRepository;

@Service
public class SocietyService {

	@Autowired
	private SocietyRepository societyRepository;

	public Society salvar(Society society) {

		society.setNomeUrl(society.getNome());
		society.setQntJogos(0l);

		return societyRepository.save(society);
	}

	public Society alterar(Long id, Society society) throws BusinessException {

		society.setId(buscarPorId(id).getId());
		society.setNomeUrl(society.getNome());

		return salvar(society);
	}

	public void deletar(Long id) throws BusinessException {
		societyRepository.delete(buscarPorId(id));
	}

	public List<Society> buscarTodos() {
		return societyRepository.findAll();
	}

	public Society buscarPorId(Long id) throws BusinessException {

		Optional<Society> busca = societyRepository.findById(id);

		if (!busca.isPresent()) {
			throw new BusinessException("Não foi encontrado Society pelo ID");
		}

		return busca.get();
	}

	public Society buscarPorNomeUrl(String nomeUrl) throws BusinessException {

		Optional<Society> busca = societyRepository.findByNomeUrl(nomeUrl);

		if (!busca.isPresent()) {
			throw new BusinessException("Não foi encontrado Society pelo Nome Url");
		}

		return busca.get();
	}

	public void addQntJogo(Society society) {
		society.setQntJogos(society.getQntJogos() + 1);
		salvar(society);
	}

}
