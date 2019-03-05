package com.society.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.business.AvaliacaoBusiness;
import com.society.exception.BusinessException;
import com.society.model.Agendamento;
import com.society.model.AvaliacaoSociety;
import com.society.model.AvaliacaoUsuario;
import com.society.model.vo.ResponseAgendamentoVO;
import com.society.model.vo.ResponseAvaliacaoSocietyVO;
import com.society.model.vo.ResponseAvaliacaoUsuarioVO;

@Service
public class AvaliacaoService {

	@Autowired 
	private AvaliacaoBusiness avaliacaoBusiness;

	public AvaliacaoSociety avaliarSociety(String token, AvaliacaoSociety avaliacao) throws BusinessException {
		return avaliacaoBusiness.avaliarSociety(token, avaliacao);
	}
	
	public AvaliacaoUsuario avaliarUsuario(String token, AvaliacaoUsuario avaliacao) throws BusinessException {
		return avaliacaoBusiness.avaliarUsuario(token, avaliacao);
	}

	public ResponseAvaliacaoSocietyVO avaliacoesSociety(Long idSociety) throws BusinessException {
		
		List<AvaliacaoSociety> avaliacoesDoSociety = avaliacaoBusiness.avaliacoesDoSociety(idSociety);
		
		return montarResponseSociety(avaliacoesDoSociety);
	}
	
	public ResponseAvaliacaoSocietyVO avaliacoesSociety(String token) throws BusinessException {
		
		List<AvaliacaoSociety> avaliacoesDoSociety = avaliacaoBusiness.avaliacoesDoSociety(token);
		
		return montarResponseSociety(avaliacoesDoSociety);
	}

	public ResponseAvaliacaoUsuarioVO avaliacoesUsuario(Long idUsuario) throws BusinessException {
		
		List<AvaliacaoUsuario> avaliacoesDoUsuario = avaliacaoBusiness.avaliacoesDoUsuario(idUsuario);
		
		return montarResponseUsuario(avaliacoesDoUsuario);
	}
	
	public ResponseAvaliacaoUsuarioVO avaliacoesUsuario(String token) throws BusinessException {
		
		List<AvaliacaoUsuario> avaliacoesDoUsuario = avaliacaoBusiness.avaliacoesDoUsuario(token);
		
		return montarResponseUsuario(avaliacoesDoUsuario);
	}

	public List<ResponseAgendamentoVO> avaliacoesPendentesDoUsuario(String token) throws BusinessException {
		
		List<ResponseAgendamentoVO> agendamentos = new ArrayList<>();

		for (Agendamento agendamento : avaliacaoBusiness.avaliacoesPendentesDoUsuario(token)) {
			agendamentos.add(new ResponseAgendamentoVO(agendamento));
		}
		
		return agendamentos;
	}

	public List<ResponseAgendamentoVO> avaliacoesPendentesDoSociety(String token) throws BusinessException {
		
		List<ResponseAgendamentoVO> agendamentos = new ArrayList<>();

		for (Agendamento agendamento : avaliacaoBusiness.avaliacoesPendentesDoSociety(token)) {
			agendamentos.add(new ResponseAgendamentoVO(agendamento));
		}
		
		return agendamentos;
	}
	
	private ResponseAvaliacaoSocietyVO montarResponseSociety(List<AvaliacaoSociety> avaliacoesDoSociety) {
		
		BigDecimal quantidadeAvaliacoes = BigDecimal.valueOf(avaliacoesDoSociety.size());
		BigDecimal somaAvaliacoes = BigDecimal.ZERO;
		
		ResponseAvaliacaoSocietyVO response = new ResponseAvaliacaoSocietyVO();
		
		if(quantidadeAvaliacoes.compareTo(BigDecimal.ZERO) < 1) {
			response.setAvaliacoes(avaliacoesDoSociety);
			response.setQuantidadeAvaliacoes(quantidadeAvaliacoes.intValue());
			response.setMedia(BigDecimal.ZERO);
			return response;
		}
		
		for (AvaliacaoSociety avaliacaoSociety : avaliacoesDoSociety) {
			somaAvaliacoes = somaAvaliacoes.add(BigDecimal.valueOf(avaliacaoSociety.getNota()));
		}
		
		BigDecimal media = somaAvaliacoes.divide(quantidadeAvaliacoes, 2, BigDecimal.ROUND_HALF_UP);

		response.setAvaliacoes(avaliacoesDoSociety);
		response.setQuantidadeAvaliacoes(quantidadeAvaliacoes.intValue());
		response.setMedia(media);
		return response;
	}
	
	private ResponseAvaliacaoUsuarioVO montarResponseUsuario(List<AvaliacaoUsuario> avaliacoesDoUsuario) {
		BigDecimal quantidadeAvaliacoes = BigDecimal.valueOf(avaliacoesDoUsuario.size());
		BigDecimal somaAvaliacoes = BigDecimal.ZERO;
		
		ResponseAvaliacaoUsuarioVO response = new ResponseAvaliacaoUsuarioVO();

		if(quantidadeAvaliacoes.compareTo(BigDecimal.ZERO) < 1) {
			response.setAvaliacoes(avaliacoesDoUsuario);
			response.setQuantidadeAvaliacoes(quantidadeAvaliacoes.intValue());
			response.setMedia(BigDecimal.ZERO);
			return response;
		}
		
		for (AvaliacaoUsuario avaliacaoUsuario : avaliacoesDoUsuario) {
			somaAvaliacoes = somaAvaliacoes.add(BigDecimal.valueOf(avaliacaoUsuario.getNota()));
		}
		
		BigDecimal media = somaAvaliacoes.divide(quantidadeAvaliacoes, 2, BigDecimal.ROUND_HALF_UP);

		response.setAvaliacoes(avaliacoesDoUsuario);
		response.setQuantidadeAvaliacoes(quantidadeAvaliacoes.intValue());
		response.setMedia(media);
		return response;
	}
	
}
