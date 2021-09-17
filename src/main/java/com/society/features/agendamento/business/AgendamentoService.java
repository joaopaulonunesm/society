package com.society.features.agendamento.business;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.society.features.agendamento.domain.Agendamento;
import com.society.features.agendamento.domain.ResponseAgendamentoDTO;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

	private final AgendamentoBusiness agendamentoBusiness;

	public ResponseAgendamentoDTO criar(Agendamento agendamento) {
		return new ResponseAgendamentoDTO(agendamentoBusiness.criar(agendamento));
	}

	public Agendamento buscarPorId(Long id) {
		return agendamentoBusiness.buscarPorId(id);
	}

	public ResponseAgendamentoDTO buscaPorIdDTO(Long id){
		return new ResponseAgendamentoDTO(buscarPorId(id));
	}

	public List<ResponseAgendamentoDTO> buscaComTokenPorSocietyDTO(String token) {

		List<ResponseAgendamentoDTO> agendamentos = new ArrayList<>();

		for (Agendamento agendamento : agendamentoBusiness.buscaComTokenPorSociety(token)) {
			agendamentos.add(new ResponseAgendamentoDTO(agendamento));
		}

		return agendamentos;
	}

	public List<Agendamento> buscaComTokenPorSociety(String token) {
		return agendamentoBusiness.buscaComTokenPorSociety(token);
	}

	public List<ResponseAgendamentoDTO> buscaComTokenPorUsuarioDTO(String token) {

		List<ResponseAgendamentoDTO> agendamentos = new ArrayList<>();

		for (Agendamento agendamento : agendamentoBusiness.buscaComTokenPorUsuario(token)) {
			agendamentos.add(new ResponseAgendamentoDTO(agendamento));
		}

		return agendamentos;
	}

	public List<Agendamento> buscaComTokenPorUsuario(String token) {
		return agendamentoBusiness.buscaComTokenPorUsuario(token);
	}

	public ResponseAgendamentoDTO confirmarOuCancelar(Long id, String confirmacao) {
		return new ResponseAgendamentoDTO(agendamentoBusiness.confirmarOuCancelar(id, confirmacao));
	}

}
