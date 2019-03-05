package com.society.business;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.society.enums.StatusAgendamento;
import com.society.exception.BusinessException;
import com.society.model.Agendamento;
import com.society.model.Society;
import com.society.model.Usuario;
import com.society.repository.AgendamentoRepository;
import com.society.utils.Utils;

@Component
public class AgendamentoBusiness {

	@Autowired
	private AgendamentoRepository agendamentoRepository;

	@Autowired
	private SocietyBusiness societyBusiness;

	@Autowired
	private UsuarioBusiness usuarioBusiness;

	@Autowired
	private Utils utils;

	public Agendamento criar(Agendamento agendamento) throws BusinessException {

		Society society = societyBusiness.buscarPorId(agendamento.getSociety().getId());

		agendamento.setSociety(society);
		agendamento.setDataFim(utils.addHora(agendamento.getDataInicio(), 1));
		agendamento.setStatusAgendamento(StatusAgendamento.AGUARDANDO_SOCIETY);

		Usuario usuario = usuarioBusiness.buscarPorId(agendamento.getUsuario().getId());

		agendamento.setUsuario(usuario);

		if (agendamento.getDataInicio().before(new Date())) {
			throw new BusinessException("A data do jogo deve ser maior que a data atual.", HttpStatus.BAD_REQUEST);
		}
		
		if(!horarioDisponivel(agendamento)) {
			throw new BusinessException("Os campos já estão reservados no " + agendamento.getSociety().getNome()
					+ " para o horário informado.", HttpStatus.BAD_REQUEST);
		}
		
		if(existeReservaParaUsuario(usuario, society, agendamento.getDataInicio())) {
			throw new BusinessException("Você já tem uma reserva nesse horário para esse Society.", HttpStatus.BAD_REQUEST);
		}

		Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);

		societyBusiness.addQntJogo(society);

		return agendamentoSalvo;
	}

	public Agendamento buscarPorId(Long id) throws BusinessException {

		Optional<Agendamento> agendamento = agendamentoRepository.findById(id);

		if (!agendamento.isPresent()) {
			throw new BusinessException("Não foi encontrado Agendamento pelo ID", HttpStatus.NOT_FOUND);
		}

		return agendamento.get();
	}

	public Agendamento confirmarOuCancelar(Long id, String confirmacao) throws BusinessException {

		if (!confirmacao.equalsIgnoreCase(StatusAgendamento.CONFIRMADO.toString())
				&& !confirmacao.equalsIgnoreCase(StatusAgendamento.CANCELADO.toString())) {
			throw new BusinessException("Parametro " + confirmacao + " é inválido para Confirmação ou Cancelamento!",
					HttpStatus.BAD_REQUEST);
		}

		Agendamento agendamento = buscarPorId(id);

		if (confirmacao.equalsIgnoreCase(StatusAgendamento.CONFIRMADO.toString()) && !horarioDisponivel(agendamento) && agendamento.getStatusAgendamento() != StatusAgendamento.CONFIRMADO) {
			agendamento.setStatusAgendamento(StatusAgendamento.CANCELADO);
			agendamentoRepository.save(agendamento);
			throw new BusinessException("Não existe mais campos disponíveis para esse horário.", HttpStatus.BAD_REQUEST);
		}
		
		Date dataReferencia = new Date();
		
		if (dataReferencia.after(agendamento.getDataInicio())) {
			agendamento.setStatusAgendamento(agendamento.getStatusAgendamento() == StatusAgendamento.CONFIRMADO ? StatusAgendamento.CONFIRMADO : StatusAgendamento.CANCELADO);
			agendamentoRepository.save(agendamento);
			throw new BusinessException("O jogo já iniciou! Caso estava em analise, o jogo será cancelado automaticamente.", HttpStatus.BAD_REQUEST);
		}

		agendamento.setStatusAgendamento(StatusAgendamento.buscarPorString(confirmacao));
		return agendamentoRepository.save(agendamento);
	}

	public List<Agendamento> buscaComTokenPorSociety(String token) throws BusinessException {

		Society society = societyBusiness.buscarPorToken(token);

		return agendamentoRepository.findBySocietyIdOrderByDataInicio(society.getId());
	}

	public List<Agendamento> buscaComTokenPorUsuario(String token) throws BusinessException {

		Usuario usuario = usuarioBusiness.buscarPorToken(token);

		return agendamentoRepository.findByUsuarioIdOrderByDataInicio(usuario.getId());
	}

	private boolean horarioDisponivel(Agendamento agendamento) {

		List<Agendamento> agendamentoExistente = agendamentoRepository.findByDataInicioAndDataFimAndSocietyAndStatusAgendamento(agendamento.getDataInicio(), agendamento.getDataFim(), agendamento.getSociety().getId(), StatusAgendamento.CONFIRMADO.getId());

		return agendamentoExistente.size() < agendamento.getSociety().getQuantidadeCampos();
	}
	
	private boolean existeReservaParaUsuario(Usuario usuario, Society society, Date dataAgendamento) {
		
		Agendamento agendamento = agendamentoRepository.findByUsuarioIdAndDataInicioAndSocietyId(usuario.getId(), dataAgendamento, society.getId());
		
		return agendamento != null;
	}

}
