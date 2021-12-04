package com.society.usecases.services;

import com.society.infrastructure.repositories.agendamento.Agendamento;
import com.society.infrastructure.repositories.agendamento.AgendamentoRepository;
import com.society.infrastructure.repositories.society.Society;
import com.society.usecases.enums.StatusAgendamento;
import com.society.usecases.exceptions.UseCaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoUseCase {

	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Autowired
	private SocietyUseCase societyUseCase;
	
	public Agendamento criar(Agendamento agendamento) throws UseCaseException {

		Society society = societyUseCase.buscarPorId(agendamento.getSociety().getId());
		
		agendamento.setSociety(society);
		agendamento.setDataFim(agendamento.getDataInicio().plusHours(1));
		agendamento.setStatus(StatusAgendamento.AGUARDANDO_SOCIETY);

		valida(agendamento);
		
		Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);
		
		societyUseCase.addQntJogo(society);
		
		return agendamentoSalvo;
	}
	
	public Agendamento alterar(Long id, Agendamento agendamento) throws UseCaseException {
		
		Agendamento agendamentoExistente = buscarPorId(id);
		
		// Respeitara o id passado no parametro da API
		agendamento.setId(agendamentoExistente.getId());
		// Não é permitido alterar society do agendamento
		agendamento.setSociety(agendamentoExistente.getSociety());
		agendamento.setDataFim(agendamento.getDataInicio().plusHours(1));
		
		valida(agendamento);
		
		return agendamentoRepository.save(agendamento);
	}

	public Agendamento buscarPorId(Long id) throws UseCaseException {

		Optional<Agendamento> busca = agendamentoRepository.findById(id);

		if (!busca.isPresent()) {
			throw new UseCaseException("Não foi encontrado Agendamento pelo ID", HttpStatus.NOT_FOUND);
		}

		return busca.get();
	}

	public List<Agendamento> buscarPorSociety(Long idSociety) {
		return agendamentoRepository.findBySocietyIdOrderByDataInicio(idSociety);
	}

	public List<Agendamento> buscaComTokenPorSociety(String token) throws UseCaseException {

		Society society = societyUseCase.buscarPorToken(token);
		
		return agendamentoRepository.findBySocietyIdOrderByDataInicio(society.getId());
	}
	
	public Agendamento confirmarOuCancelar(Long id, StatusAgendamento confirmacao) throws UseCaseException {

		Agendamento agendamento = buscarPorId(id);

		agendamento.setStatus(confirmacao);

		return agendamentoRepository.save(agendamento);
	}

	private void valida(Agendamento agendamento) throws UseCaseException {

		if (agendamento.getDataInicio().isBefore(LocalDateTime.now())) {
			throw new UseCaseException("A data informada deve ser maior que a data atual.", HttpStatus.BAD_REQUEST);
		}

		List<Agendamento> agendamentoExistente = agendamentoRepository.findByDataInicioAndDataFimAndSociety(
				agendamento.getDataInicio(), agendamento.getDataFim(), agendamento.getSociety().getId());

		if (!agendamentoExistente.isEmpty()
				&& agendamentoExistente.size() >= agendamento.getSociety().getQuantidadeCampos()) {
			throw new UseCaseException("Não existe campo disponível no " + agendamento.getSociety().getNome()
					+ " para o horário informado.", HttpStatus.BAD_REQUEST);
		}

	}

}
