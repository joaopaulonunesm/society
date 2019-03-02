package com.society.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.business.SocietyBusiness;
import com.society.exception.BusinessException;
import com.society.model.Society;

@Service
public class SocietyService {

	@Autowired
	private SocietyBusiness societyBusiness;

	public Society criar(Society society) throws BusinessException {
		return societyBusiness.criar(society);
	}

	public Society alterar(String token, Society society) throws BusinessException {
		return societyBusiness.alterar(token, society);
	}

	public Society ativarOuInativar(Long id, String confirmacao) throws BusinessException {
		return societyBusiness.ativarOuInativar(id, confirmacao);
	}

	public List<Society> buscarTodos() {
		return societyBusiness.buscarTodos();
	}

	public Society buscarPorNomeUrl(String nomeUrl) throws BusinessException {
		return societyBusiness.buscarPorNomeUrl(nomeUrl);
	}

	public Society buscarPorToken(String token) throws BusinessException {
		return societyBusiness.buscarPorToken(token);
	}

}
