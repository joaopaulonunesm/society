package com.society.features.avaliacao.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.society.features.avaliacao.domain.AvaliacaoSociety;
import com.society.features.avaliacao.domain.AvaliacaoUsuario;
import com.society.features.avaliacao.domain.ResponseAvaliacaoSocietyDTO;
import com.society.features.avaliacao.domain.ResponseAvaliacaoUsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.society.features.agendamento.domain.Agendamento;
import com.society.features.agendamento.domain.ResponseAgendamentoDTO;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

	private final AvaliacaoBusiness avaliacaoBusiness;

	public AvaliacaoSociety avaliarSociety(String token, AvaliacaoSociety avaliacao) {
		return avaliacaoBusiness.avaliarSociety(token, avaliacao);
	}
	
	public AvaliacaoUsuario avaliarUsuario(String token, AvaliacaoUsuario avaliacao) {
		return avaliacaoBusiness.avaliarUsuario(token, avaliacao);
	}

	public ResponseAvaliacaoSocietyDTO avaliacoesSociety(Long idSociety) {
		
		List<AvaliacaoSociety> avaliacoesDoSociety = avaliacaoBusiness.avaliacoesDoSociety(idSociety);
		
		return montarResponseSociety(avaliacoesDoSociety);
	}
	
	public ResponseAvaliacaoSocietyDTO avaliacoesSociety(String token) {
		
		List<AvaliacaoSociety> avaliacoesDoSociety = avaliacaoBusiness.avaliacoesDoSociety(token);
		
		return montarResponseSociety(avaliacoesDoSociety);
	}

	public ResponseAvaliacaoUsuarioDTO avaliacoesUsuario(Long idUsuario) {
		
		List<AvaliacaoUsuario> avaliacoesDoUsuario = avaliacaoBusiness.avaliacoesDoUsuario(idUsuario);
		
		return montarResponseUsuario(avaliacoesDoUsuario);
	}
	
	public ResponseAvaliacaoUsuarioDTO avaliacoesUsuario(String token) {
		
		List<AvaliacaoUsuario> avaliacoesDoUsuario = avaliacaoBusiness.avaliacoesDoUsuario(token);
		
		return montarResponseUsuario(avaliacoesDoUsuario);
	}

	public List<ResponseAgendamentoDTO> avaliacoesPendentesDoUsuario(String token) {
		
		List<ResponseAgendamentoDTO> agendamentos = new ArrayList<>();

		for (Agendamento agendamento : avaliacaoBusiness.avaliacoesPendentesDoUsuario(token)) {
			agendamentos.add(new ResponseAgendamentoDTO(agendamento));
		}
		
		return agendamentos;
	}

	public List<ResponseAgendamentoDTO> avaliacoesPendentesDoSociety(String token) {
		
		List<ResponseAgendamentoDTO> agendamentos = new ArrayList<>();

		for (Agendamento agendamento : avaliacaoBusiness.avaliacoesPendentesDoSociety(token)) {
			agendamentos.add(new ResponseAgendamentoDTO(agendamento));
		}
		
		return agendamentos;
	}
	
	private ResponseAvaliacaoSocietyDTO montarResponseSociety(List<AvaliacaoSociety> avaliacoesDoSociety) {
		
		BigDecimal quantidadeAvaliacoes = BigDecimal.valueOf(avaliacoesDoSociety.size());
		BigDecimal somaAvaliacoes = BigDecimal.ZERO;
		
		ResponseAvaliacaoSocietyDTO response = new ResponseAvaliacaoSocietyDTO();
		
		if(quantidadeAvaliacoes.compareTo(BigDecimal.ZERO) < 1) {
			response.setAvaliacoes(avaliacoesDoSociety);
			response.setQuantidadeAvaliacoes(quantidadeAvaliacoes.intValue());
			response.setMedia(BigDecimal.ZERO);
			return response;
		}
		
		for (AvaliacaoSociety avaliacaoSociety : avaliacoesDoSociety) {
			somaAvaliacoes = somaAvaliacoes.add(BigDecimal.valueOf(avaliacaoSociety.getNota()));
		}
		
		BigDecimal media = somaAvaliacoes.divide(quantidadeAvaliacoes, 2, RoundingMode.HALF_UP);

		response.setAvaliacoes(avaliacoesDoSociety);
		response.setQuantidadeAvaliacoes(quantidadeAvaliacoes.intValue());
		response.setMedia(media);
		return response;
	}
	
	private ResponseAvaliacaoUsuarioDTO montarResponseUsuario(List<AvaliacaoUsuario> avaliacoesDoUsuario) {
		BigDecimal quantidadeAvaliacoes = BigDecimal.valueOf(avaliacoesDoUsuario.size());
		BigDecimal somaAvaliacoes = BigDecimal.ZERO;
		
		ResponseAvaliacaoUsuarioDTO response = new ResponseAvaliacaoUsuarioDTO();

		if(quantidadeAvaliacoes.compareTo(BigDecimal.ZERO) < 1) {
			response.setAvaliacoes(avaliacoesDoUsuario);
			response.setQuantidadeAvaliacoes(quantidadeAvaliacoes.intValue());
			response.setMedia(BigDecimal.ZERO);
			return response;
		}
		
		for (AvaliacaoUsuario avaliacaoUsuario : avaliacoesDoUsuario) {
			somaAvaliacoes = somaAvaliacoes.add(BigDecimal.valueOf(avaliacaoUsuario.getNota()));
		}
		
		BigDecimal media = somaAvaliacoes.divide(quantidadeAvaliacoes, 2, RoundingMode.HALF_UP);

		response.setAvaliacoes(avaliacoesDoUsuario);
		response.setQuantidadeAvaliacoes(quantidadeAvaliacoes.intValue());
		response.setMedia(media);
		return response;
	}
	
}
