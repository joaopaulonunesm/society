package com.society.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.exception.BusinessException;
import com.society.model.Agendamento;
import com.society.model.DataVO;
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

	public Agendamento salvar(Agendamento agendamento) throws BusinessException {

		Society society = societyService.buscarPorId(agendamento.getSociety().getId());

		agendamento.setSociety(society);
		agendamento.setDataFim(utils.addHora(agendamento.getDataInicio(), 1));

		List<Agendamento> agendamentoExistente = buscarHorarioExistentePorDataESociety(agendamento, society.getId());

		if (!agendamentoExistente.isEmpty()) {
			throw new BusinessException("Já existe um jogo agendado no society " + society.getNome() + " para o horário informado.");
		}
		
		societyService.addQntJogo(society);

		return agendamentoRepository.save(agendamento);
	}

	private List<Agendamento> buscarHorarioExistentePorDataESociety(Agendamento agendamento, Long idSociety) {
		
		return agendamentoRepository.findByDataInicioAndDataFimAndSociety(agendamento.getDataInicio(), agendamento.getDataFim(), idSociety);
	}

	public Agendamento alterar(Long id, Agendamento agendamento) throws BusinessException {
		agendamento.setId(buscarPorId(id).getId());
		return salvar(agendamento);
	}

	public void deletar(Long id) throws BusinessException {
		agendamentoRepository.delete(buscarPorId(id));
	}

	public List<Agendamento> buscarTodos() {
		return agendamentoRepository.findAll();
	}

	public Agendamento buscarPorId(Long id) throws BusinessException {

		Optional<Agendamento> busca = agendamentoRepository.findById(id);

		if (!busca.isPresent()) {
			throw new BusinessException("Não foi encontrado Agendamento pelo ID");
		}

		return busca.get();
	}

	public List<Agendamento> buscaHorariosOcupadosPorSociety(Long id, DataVO dataVO) {

		return agendamentoRepository.findByDataAndSociety(id, dataVO.getData());
	}

}
