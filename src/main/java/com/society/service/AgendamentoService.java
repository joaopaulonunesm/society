package com.society.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.society.enums.StatusAgendamento;
import com.society.exception.BusinessException;
import com.society.model.Agendamento;
import com.society.model.Society;
import com.society.repository.AgendamentoRepository;
import com.society.utils.Utils;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Autowired
	private SocietyService societyService;
	
	@Autowired 
	private Utils utils;

	public Agendamento criar(Agendamento agendamento) throws BusinessException {

		Society society = societyService.buscarPorId(agendamento.getSociety().getId());
		
		agendamento.setSociety(society);
		agendamento.setDataFim(utils.addHora(agendamento.getDataInicio(), 1));
		agendamento.setStatus(StatusAgendamento.AGUARDANDO_SOCIETY);

		valida(agendamento);
		
		Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);
		
		societyService.addQntJogo(society);
		
		return agendamentoSalvo;
	}
	
	public Agendamento alterar(Long id, Agendamento agendamento) throws BusinessException {
		
		Agendamento agendamentoExistente = buscarPorId(id);
		
		// Respeitara o id passado no parametro da API
		agendamento.setId(agendamentoExistente.getId());
		// Não é permitido alterar society do agendamento
		agendamento.setSociety(agendamentoExistente.getSociety());
		agendamento.setDataFim(utils.addHora(agendamento.getDataInicio(), 1));
		
		valida(agendamento);
		
		return agendamentoRepository.save(agendamento);
	}

	public Agendamento buscarPorId(Long id) throws BusinessException {

		Optional<Agendamento> busca = agendamentoRepository.findById(id);

		if (!busca.isPresent()) {
			throw new BusinessException("Não foi encontrado Agendamento pelo ID", HttpStatus.NOT_FOUND);
		}

		return busca.get();
	}

	public List<Agendamento> buscarPorSociety(Long idSociety) {
		return agendamentoRepository.findBySocietyIdOrderByDataInicio(idSociety);
	}

	public List<Agendamento> buscaComTokenPorSociety(String token) throws BusinessException {

		Society society = societyService.buscarPorToken(token);
		
		return agendamentoRepository.findBySocietyIdOrderByDataInicio(society.getId());
	}
	
	public Agendamento confirmarOuCancelar(Long id, String confirmacao) throws BusinessException {

		if(!confirmacao.equalsIgnoreCase(StatusAgendamento.CONFIRMADO.toString()) && !confirmacao.equalsIgnoreCase(StatusAgendamento.CANCELADO.toString())) {
			throw new BusinessException("Parametro " + confirmacao + " é inválido para Confirmação ou Cancelamento!", HttpStatus.BAD_REQUEST);
		}

		Agendamento agendamento = buscarPorId(id);

		agendamento.setStatus(StatusAgendamento.getPorString(confirmacao));

		return agendamentoRepository.save(agendamento);
	}

	private void valida(Agendamento agendamento) throws BusinessException {

		if (agendamento.getDataInicio().before(new Date())) {
			throw new BusinessException("A data informada deve ser maior que a data atual.", HttpStatus.BAD_REQUEST);
		}

		List<Agendamento> agendamentoExistente = agendamentoRepository.findByDataInicioAndDataFimAndSociety(
				agendamento.getDataInicio(), agendamento.getDataFim(), agendamento.getSociety().getId());

		if (!agendamentoExistente.isEmpty()
				&& agendamentoExistente.size() >= agendamento.getSociety().getQuantidadeCampos()) {
			throw new BusinessException("Não existe campo disponível no " + agendamento.getSociety().getNome()
					+ " para o horário informado.", HttpStatus.BAD_REQUEST);
		}

	}

}
