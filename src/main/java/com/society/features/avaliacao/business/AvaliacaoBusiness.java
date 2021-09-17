package com.society.features.avaliacao.business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.society.features.agendamento.business.AgendamentoService;
import com.society.features.agendamento.domain.StatusAgendamento;
import com.society.features.avaliacao.domain.AvaliacaoSociety;
import com.society.features.avaliacao.domain.AvaliacaoUsuario;
import com.society.features.avaliacao.persistence.AvaliacaoSocietyRepository;
import com.society.features.avaliacao.persistence.AvaliacaoUsuarioRepository;
import com.society.features.society.business.SocietyService;
import com.society.features.society.domain.Society;
import com.society.features.usuario.business.UsuarioService;
import com.society.features.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.society.exceptions.BusinessException;
import com.society.features.agendamento.domain.Agendamento;

@Component
@RequiredArgsConstructor
public class AvaliacaoBusiness {

	private final AvaliacaoSocietyRepository avaliacaoSocietyRepository;
	private final AvaliacaoUsuarioRepository avaliacaoUsuarioRepository;
	private final SocietyService societyService;
	private final UsuarioService usuarioService;
	private final AgendamentoService agendamentoService;
	
	public AvaliacaoSociety avaliarSociety(String token, AvaliacaoSociety avaliacao) {
		
		if(avaliacaoSocietyRepository.findByAgendamentoId(avaliacao.getAgendamento().getId()) != null) {
			throw new BusinessException("Esse Jogo já foi avaliado.", HttpStatus.BAD_REQUEST);
		}
		
		Usuario usuario = usuarioService.buscarPorToken(token);
		
		Agendamento agendamento = agendamentoService.buscarPorId(avaliacao.getAgendamento().getId());
		
		if(agendamento.getUsuario() != usuario) {
			throw new BusinessException("Usuário avaliador não é o mesmo do Agendamento.", HttpStatus.BAD_REQUEST);
		}
		
		if(avaliacao.getNota() == null || avaliacao.getNota() < 1 || avaliacao.getNota() > 10) {
			throw new BusinessException("Nota é uma informação obrigatória. Informar valor entre 1 e 10.", HttpStatus.BAD_REQUEST);
		}
		
		return avaliacaoSocietyRepository.save(avaliacao);
	}
	
	public AvaliacaoUsuario avaliarUsuario(String token, AvaliacaoUsuario avaliacao) {
		
		Society society = societyService.buscarPorToken(token);
		
		Agendamento agendamento = agendamentoService.buscarPorId(avaliacao.getAgendamento().getId());
		
		if(agendamento.getSociety() != society) {
			throw new BusinessException("Society avaliador não é o mesmo do Agendamento.", HttpStatus.BAD_REQUEST);
		}
		
		if(avaliacao.getNota() == null || avaliacao.getNota() < 1 || avaliacao.getNota() > 10) {
			throw new BusinessException("Nota é uma informação obrigatória. Informar valor entre 1 e 10.", HttpStatus.BAD_REQUEST);
		}
		
		return avaliacaoUsuarioRepository.save(avaliacao);
	}
	
	public List<AvaliacaoSociety> avaliacoesDoSociety(Long idSociety) {
		
		societyService.buscarPorId(idSociety);
		
		return avaliacaoSocietyRepository.findByAgendamentoSocietyId(idSociety);
	}
	
	public List<AvaliacaoSociety> avaliacoesDoSociety(String token) {
		
		Society society = societyService.buscarPorToken(token);
		
		return avaliacaoSocietyRepository.findByAgendamentoSocietyId(society.getId());
	}

	public List<AvaliacaoUsuario> avaliacoesDoUsuario(Long idUsuario) {
		
		usuarioService.buscarPorId(idUsuario);
		
		return avaliacaoUsuarioRepository.findByAgendamentoUsuarioId(idUsuario);
	}
	
	public List<AvaliacaoUsuario> avaliacoesDoUsuario(String token) {
		
		Usuario usuario = usuarioService.buscarPorToken(token);
		
		return avaliacaoUsuarioRepository.findByAgendamentoUsuarioId(usuario.getId());
	}

	public List<Agendamento> avaliacoesPendentesDoUsuario(String token) {

		List<Agendamento> agendamentosDoUsuario = agendamentoService.buscaComTokenPorUsuario(token);
		agendamentosDoUsuario.removeIf(agendamento -> !StatusAgendamento.CONFIRMADO.equals(StatusAgendamento.buscarPorId(agendamento.getStatusAgendamento())));
		agendamentosDoUsuario.removeIf(agendamento -> agendamento.getDataFim().isAfter(LocalDateTime.now()));
		
		List<Agendamento> agendamenosComAvaliacaoPendente = new ArrayList<>();
		
		for (Agendamento agendamento : agendamentosDoUsuario) {
			
			if(avaliacaoSocietyRepository.findByAgendamentoId(agendamento.getId())  == null) {
				agendamenosComAvaliacaoPendente.add(agendamento);
			}
			
		}
		
		return agendamenosComAvaliacaoPendente;
	}
	
	public List<Agendamento> avaliacoesPendentesDoSociety(String token) {

		List<Agendamento> agendamentosDoSociety = agendamentoService.buscaComTokenPorSociety(token);
		agendamentosDoSociety.removeIf(agendamento -> !StatusAgendamento.CONFIRMADO.equals(StatusAgendamento.buscarPorId(agendamento.getStatusAgendamento())));
		agendamentosDoSociety.removeIf(agendamento -> agendamento.getDataFim().isAfter(LocalDateTime.now()));
		
		List<Agendamento> agendamenosComAvaliacaoPendente = new ArrayList<>();
		
		for (Agendamento agendamento : agendamentosDoSociety) {
			
			if(avaliacaoUsuarioRepository.findByAgendamentoId(agendamento.getId())  == null) {
				agendamenosComAvaliacaoPendente.add(agendamento);
			}
			
		}
		
		return agendamenosComAvaliacaoPendente;
	}
	
}
