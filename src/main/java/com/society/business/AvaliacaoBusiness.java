package com.society.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.society.enums.StatusAgendamento;
import com.society.exception.BusinessException;
import com.society.model.Agendamento;
import com.society.model.AvaliacaoSociety;
import com.society.model.AvaliacaoUsuario;
import com.society.model.Society;
import com.society.model.Usuario;

@Component
public class AvaliacaoBusiness {

	@Autowired
	private AvaliacaoSocietyRepository avaliacaoSocietyRepository;
	
	@Autowired
	private AvaliacaoUsuarioRepository avaliacaoUsuarioRepository;

	@Autowired
	private SocietyBusiness societyBusiness;
	
	@Autowired
	private UsuarioBusiness usuarioBusiness;
	
	@Autowired
	private AgendamentoBusiness agendamentoBusiness;
	
	public AvaliacaoSociety avaliarSociety(String token, AvaliacaoSociety avaliacao) throws BusinessException {
		
		if(avaliacaoSocietyRepository.findByAgendamentoId(avaliacao.getAgendamento().getId()) != null) {
			throw new BusinessException("Esse Jogo já foi avaliado.", HttpStatus.BAD_REQUEST);
		}
		
		Usuario usuario = usuarioBusiness.buscarPorToken(token);
		
		Agendamento agendamento = agendamentoBusiness.buscarPorId(avaliacao.getAgendamento().getId());
		
		if(agendamento.getUsuario() != usuario) {
			throw new BusinessException("Usuário avaliador não é o mesmo do Agendamento.", HttpStatus.BAD_REQUEST);
		}
		
		if(avaliacao.getNota() == null || avaliacao.getNota() < 1 || avaliacao.getNota() > 10) {
			throw new BusinessException("Nota é uma informação obrigatória. Informar valor entre 1 e 10.", HttpStatus.BAD_REQUEST);
		}
		
		return avaliacaoSocietyRepository.save(avaliacao);
	}
	
	public AvaliacaoUsuario avaliarUsuario(String token, AvaliacaoUsuario avaliacao) throws BusinessException {
		
		Society society = societyBusiness.buscarPorToken(token);
		
		Agendamento agendamento = agendamentoBusiness.buscarPorId(avaliacao.getAgendamento().getId());
		
		if(agendamento.getSociety() != society) {
			throw new BusinessException("Society avaliador não é o mesmo do Agendamento.", HttpStatus.BAD_REQUEST);
		}
		
		if(avaliacao.getNota() == null || avaliacao.getNota() < 1 || avaliacao.getNota() > 10) {
			throw new BusinessException("Nota é uma informação obrigatória. Informar valor entre 1 e 10.", HttpStatus.BAD_REQUEST);
		}
		
		return avaliacaoUsuarioRepository.save(avaliacao);
	}
	
	public List<AvaliacaoSociety> avaliacoesDoSociety(Long idSociety) throws BusinessException {
		
		societyBusiness.buscarPorId(idSociety);
		
		return avaliacaoSocietyRepository.findByAgendamentoSocietyId(idSociety);
	}
	
	public List<AvaliacaoSociety> avaliacoesDoSociety(String token) throws BusinessException {
		
		Society society = societyBusiness.buscarPorToken(token);
		
		return avaliacaoSocietyRepository.findByAgendamentoSocietyId(society.getId());
	}

	public List<AvaliacaoUsuario> avaliacoesDoUsuario(Long idUsuario) throws BusinessException {
		
		usuarioBusiness.buscarPorId(idUsuario);
		
		return avaliacaoUsuarioRepository.findByAgendamentoUsuarioId(idUsuario);
	}
	
	public List<AvaliacaoUsuario> avaliacoesDoUsuario(String token) throws BusinessException {
		
		Usuario usuario = usuarioBusiness.buscarPorToken(token);
		
		return avaliacaoUsuarioRepository.findByAgendamentoUsuarioId(usuario.getId());
	}

	public List<Agendamento> avaliacoesPendentesDoUsuario(String token) throws BusinessException {

		Date dataAtual = new Date();
		
		List<Agendamento> agendamentosDoUsuario = agendamentoBusiness.buscaComTokenPorUsuario(token);
		agendamentosDoUsuario.removeIf(agendamento -> agendamento.getStatusAgendamento() != StatusAgendamento.CONFIRMADO);
		agendamentosDoUsuario.removeIf(agendamento -> agendamento.getDataFim().after(dataAtual));
		
		List<Agendamento> agendamenosComAvaliacaoPendente = new ArrayList<>();
		
		for (Agendamento agendamento : agendamentosDoUsuario) {
			
			if(avaliacaoSocietyRepository.findByAgendamentoId(agendamento.getId())  == null) {
				agendamenosComAvaliacaoPendente.add(agendamento);
			}
			
		}
		
		return agendamenosComAvaliacaoPendente;
	}
	
	public List<Agendamento> avaliacoesPendentesDoSociety(String token) throws BusinessException {

		Date dataAtual = new Date();
		
		List<Agendamento> agendamentosDoSociety = agendamentoBusiness.buscaComTokenPorSociety(token);
		agendamentosDoSociety.removeIf(agendamento -> agendamento.getStatusAgendamento() != StatusAgendamento.CONFIRMADO);
		agendamentosDoSociety.removeIf(agendamento -> agendamento.getDataFim().after(dataAtual));
		
		List<Agendamento> agendamenosComAvaliacaoPendente = new ArrayList<>();
		
		for (Agendamento agendamento : agendamentosDoSociety) {
			
			if(avaliacaoUsuarioRepository.findByAgendamentoId(agendamento.getId())  == null) {
				agendamenosComAvaliacaoPendente.add(agendamento);
			}
			
		}
		
		return agendamenosComAvaliacaoPendente;
	}
	
}
