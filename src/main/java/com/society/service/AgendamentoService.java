package com.society.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.business.AgendamentoBusiness;
import com.society.exception.BusinessException;
import com.society.model.Agendamento;
import com.society.model.vo.ResponseAgendamentoVO;

@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoBusiness agendamentoBusiness;

	public ResponseAgendamentoVO criar(Agendamento agendamento) throws BusinessException {
		return new ResponseAgendamentoVO(agendamentoBusiness.criar(agendamento));
	}

	public ResponseAgendamentoVO buscarPorId(Long id) throws BusinessException {

		return new ResponseAgendamentoVO(agendamentoBusiness.buscarPorId(id));
	}

	public List<ResponseAgendamentoVO> buscaComTokenPorSociety(String token) throws BusinessException {

		List<ResponseAgendamentoVO> agendamentos = new ArrayList<>();

		for (Agendamento agendamento : agendamentoBusiness.buscaComTokenPorSociety(token)) {
			agendamentos.add(new ResponseAgendamentoVO(agendamento));
		}

		return agendamentos;
	}

	public List<ResponseAgendamentoVO> buscaComTokenPorUsuario(String token) throws BusinessException {

		List<ResponseAgendamentoVO> agendamentos = new ArrayList<>();

		for (Agendamento agendamento : agendamentoBusiness.buscaComTokenPorUsuario(token)) {
			agendamentos.add(new ResponseAgendamentoVO(agendamento));
		}

		return agendamentos;
	}

	public ResponseAgendamentoVO confirmarOuCancelar(Long id, String confirmacao) throws BusinessException {
		return new ResponseAgendamentoVO(agendamentoBusiness.confirmarOuCancelar(id, confirmacao));
	}

}
