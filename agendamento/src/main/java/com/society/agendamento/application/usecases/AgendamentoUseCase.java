package com.society.agendamento.application.usecases;

import com.society.agendamento.application.dto.AgendamentoResponse;
import com.society.agendamento.application.dto.AlteraHorarioAgendamentoRequest;
import com.society.agendamento.application.dto.CriaAgendamentoRequest;
import com.society.agendamento.application.exceptions.UseCaseException;
import com.society.agendamento.application.exceptions.UseCaseMensagem;
import com.society.agendamento.domain.gateways.AgendamentoGateway;
import com.society.agendamento.domain.gateways.SocietyGateway;
import com.society.agendamento.domain.models.Agendamento;
import com.society.agendamento.domain.models.Society;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.society.agendamento.application.mapper.AgendamentoMapper.*;
import static com.society.agendamento.application.utils.DataUtil.isDataPassada;

@Service
@RequiredArgsConstructor
@Slf4j
public class AgendamentoUseCase {

    private final AgendamentoGateway agendamentoGateway;
    private final SocietyGateway societyGateway;

    public AgendamentoResponse agendar(CriaAgendamentoRequest criaAgendamentoRequest) {
        log.info("M=agendar, message=Criando agendamento de horario para society, criaAgendamentoRequest={}", criaAgendamentoRequest);

        if (isDataPassada(criaAgendamentoRequest.getDataInicio())) {
            throw new UseCaseException(UseCaseMensagem.DATA_INICIO_MENOR_QUE_DATA_ATUAL);
        }

        Society society = societyGateway.buscarPorId(criaAgendamentoRequest.getIdSociety())
                .orElseThrow(() -> new UseCaseException(UseCaseMensagem.SOCIETY_NAO_CADASTRADO));

        if(!society.estaAtivo()){
            throw new UseCaseException(UseCaseMensagem.SOCIETY_INATIVO);
        }

        Agendamento agendamento = criaAgendamento(criaAgendamentoRequest);

        List<Agendamento> agendamentos = agendamentoGateway.agendamentosDoSocietyPorHorario(agendamento.getDataInicio(), agendamento.getDataFim(), society.getId());

        if (!agendamentos.isEmpty() && agendamentos.size() >= society.getQuantidadeCampos()) {
            throw new UseCaseException(UseCaseMensagem.NAO_EXISTE_CAMPO_DISPONIVEL);
        }

        agendamento = agendamentoGateway.salvar(agendamento);

        AgendamentoResponse agendamentoResponse = retornarAgendamentoResponse(agendamento);

        log.info("M=agendar, message=Agendamento de horario criado para society, criaAgendamentoRequest={}, agendamentoResponse={}", criaAgendamentoRequest, agendamentoResponse);
        return agendamentoResponse;
    }

    public AgendamentoResponse alterarHorario(Long id, AlteraHorarioAgendamentoRequest alteraHorarioAgendamentoRequest) {
        log.info("M=alterarHorario, message=Alterando agendamento de horario para society, id={}, alteraHorarioAgendamentoRequest={}", id, alteraHorarioAgendamentoRequest);

        if (isDataPassada(alteraHorarioAgendamentoRequest.getDataInicio())) {
            throw new UseCaseException(UseCaseMensagem.DATA_INICIO_MENOR_QUE_DATA_ATUAL);
        }

        Agendamento agendamento = agendamentoGateway.buscarPorId(id)
                .orElseThrow(() -> new UseCaseException(UseCaseMensagem.AGENDAMENTO_NAO_CADASTRADO));

        if(agendamento.getDataInicio().equals(alteraHorarioAgendamentoRequest.getDataInicio())){
            throw new UseCaseException(UseCaseMensagem.AGENDAMENTO_JA_POSSUI_ESSE_HORARIO);
        }

        Society society = societyGateway.buscarPorId(agendamento.getIdSociety())
                .orElseThrow(() -> new UseCaseException(UseCaseMensagem.SOCIETY_NAO_CADASTRADO));

        agendamento.definirDataInicio(alteraHorarioAgendamentoRequest.getDataInicio());
        agendamento.definirDataFim(alteraHorarioAgendamentoRequest.getQuantidadeHoras());

        List<Agendamento> agendamentos = agendamentoGateway.agendamentosDoSocietyPorHorario(agendamento.getDataInicio(), agendamento.getDataFim(), society.getId());
        agendamentos.removeIf(agendamentoExistente -> agendamento.getId().equals(agendamentoExistente.getId()));

        if (!agendamentos.isEmpty() && agendamentos.size() >= society.getQuantidadeCampos()) {
            throw new UseCaseException(UseCaseMensagem.NAO_EXISTE_CAMPO_DISPONIVEL);
        }

        agendamentoGateway.salvar(agendamento);

        AgendamentoResponse agendamentoResponse = retornarAgendamentoResponse(agendamento);

        log.info("M=alterarHorario, message=Agendamento de horario alterado para society, id={}, alteraHorarioAgendamentoRequest={}, agendamentoResponse={}", id, alteraHorarioAgendamentoRequest, agendamentoResponse);
        return agendamentoResponse;
    }

    public Optional<AgendamentoResponse> buscarPorId(Long id) {
        log.info("M=buscarPorId, message=Buscando agendamento por ID, id={}", id);

        Optional<Agendamento> agendamento = agendamentoGateway.buscarPorId(id);

        if (agendamento.isEmpty()) {
            return Optional.empty();
        }

        AgendamentoResponse agendamentoResponse = retornarAgendamentoResponse(agendamento.get());

        log.info("M=buscarPorId, message=Agendamento encontrado por ID, id={}, agendamentoResponse={}", id, agendamentoResponse);
        return Optional.of(agendamentoResponse);
    }

    public void confirmar(Long id) {
        log.info("M=confirmar, message=Confirmando agendamento por ID, id={}", id);

        Agendamento agendamento = agendamentoGateway.buscarPorId(id)
                .orElseThrow(() -> new UseCaseException(UseCaseMensagem.AGENDAMENTO_NAO_CADASTRADO));

        if(isDataPassada(agendamento.getDataInicio())) {
            throw new UseCaseException(UseCaseMensagem.AGENDAMENTO_PASSADO);
        }

        agendamento.confirmarAgendamento();

        agendamentoGateway.salvar(agendamento);

        log.info("M=confirmar, message=Agendamento confirmando por ID, id={}", id);
    }

    public void cancelar(Long id) {
        log.info("M=cancelar, message=Cancelando agendamento por ID, id={}", id);

        Agendamento agendamento = agendamentoGateway.buscarPorId(id)
                .orElseThrow(() -> new UseCaseException(UseCaseMensagem.AGENDAMENTO_NAO_CADASTRADO));

        if(isDataPassada(agendamento.getDataInicio())) {
            throw new UseCaseException(UseCaseMensagem.AGENDAMENTO_PASSADO);
        }

        agendamento.cancelar();

        agendamentoGateway.salvar(agendamento);

        log.info("M=cancelar, message=Agendamento cancelado por ID, id={}", id);
    }

    public List<AgendamentoResponse> buscarPorSociety(Long idSociety) {
        log.info("M=buscarPorSociety, message=Buscando agendamento por ID Society, idSociety={}", idSociety);

        List<Agendamento> agendamentos = agendamentoGateway.agendamentosPorSociety(idSociety);

        List<AgendamentoResponse> agendamentosResponse = retornarListaDeAgendamentoResponse(agendamentos);

        log.info("M=buscarPorSociety, message=Agendamentos por ID Society recuperados, idSociety={}, agendamentosResponse.size={}", idSociety, agendamentosResponse.size());
        return agendamentosResponse;
    }
}