package com.society.gerenciamento.application.usecases;

import com.society.gerenciamento.application.dto.SocietyRequest;
import com.society.gerenciamento.application.dto.SocietyResponse;
import com.society.gerenciamento.application.exceptions.UseCaseException;
import com.society.gerenciamento.application.exceptions.UseCaseMensagem;
import com.society.gerenciamento.domain.gateways.SocietyGateway;
import com.society.gerenciamento.domain.models.Society;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.society.gerenciamento.application.mapper.SocietyMapper.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class SocietyUseCase {

    private final SocietyGateway societyGateway;

    public SocietyResponse criar(SocietyRequest societyRequest) {
        log.info("M=criar, message=Criando society usecase, societyRequest={}", societyRequest);

        Society society = societyRequestParaSociety(societyRequest);

        society.criar();

        Optional<Society> societyExistente = societyGateway.buscaPorNomeUrl(society.getNomeUrl());
        if(societyExistente.isPresent()){
            throw new UseCaseException(UseCaseMensagem.SOCIETY_JA_EXISTENTE);
        }

        society = societyGateway.salvar(society);

        SocietyResponse societyResponse = societyParaResponse(society);

        log.info("M=criar, message=Society criado com sucesso usecase, societyRequest={}, societyResponse={}", societyRequest, societyResponse);
        return societyResponse;
    }

    public SocietyResponse alterar(Long id, SocietyRequest societyRequest) {
        log.info("M=alterar, message=Alterando society usecase, societyRequest={}", societyRequest);

        Society societyCadastrado = societyGateway.buscarPorId(id)
                .orElseThrow(() -> new UseCaseException(UseCaseMensagem.SOCIETY_NAO_CADASTRADO));

        Society society = societyRequestParaSociety(societyRequest);

        societyCadastrado.atualizar(society);

        Optional<Society> societyExistente = societyGateway.buscaPorNomeUrl(societyCadastrado.getNomeUrl());
        if(societyExistente.isPresent() && societyExistente.get().getId() != societyCadastrado.getId()){
            throw new UseCaseException(UseCaseMensagem.SOCIETY_JA_EXISTENTE);
        }

        societyCadastrado = societyGateway.salvar(societyCadastrado);

        SocietyResponse societyResponse = societyParaResponse(societyCadastrado);

        log.info("M=alterar, message=Society alterado com sucesso usecase, societyRequest={}, societyResponse={}", societyRequest, societyResponse);
        return societyResponse;
    }

    public List<SocietyResponse> buscarTodos() {
        log.info("M=buscarTodos, message=Buscando todos os societies usecase");

        List<Society> societies = societyGateway.buscarTodos();

        List<SocietyResponse> societiesResponse = listSocietyParaResponse(societies);

        log.info("M=buscarTodos, message=Societies recuperados com sucesso usecase, societiesResponse.size={}", societiesResponse.size());
        return societiesResponse;
    }

    public SocietyResponse buscarPorId(Long id) {
        log.info("M=buscarPorId, message=Buscando Society por id usecase, id={}", id);

        Optional<Society> society = societyGateway.buscarPorId(id);

        if (society.isEmpty()) {
            return null;
        }

        SocietyResponse societyResponse = societyParaResponse(society.get());

        log.info("M=buscarPorId, message=Society por id encontrado com sucesso usecase, id={}, societyResponse={}", id, societyResponse);
        return societyResponse;
    }

    public SocietyResponse buscarPorNomeUrl(String nomeUrl) {
        log.info("M=buscarPorNomeUrl, message=Buscando Society por nome url usecase, nomeUrl={}", nomeUrl);

        Society society = societyGateway.buscaPorNomeUrl(nomeUrl)
                .orElseThrow(() -> new UseCaseException(UseCaseMensagem.SOCIETY_NAO_CADASTRADO));

        SocietyResponse societyResponse = societyParaResponse(society);

        log.info("M=buscarPorNomeUrl, message=Society por nome url encontrado com sucesso usecase, nomeUrl={}, societyResponse={}", nomeUrl, societyResponse);
        return societyResponse;
    }

    public void ativar(Long id) {
        log.info("M=ativar, message=Ativando Society por id usecase, id={}", id);

        Society society = societyGateway.buscarPorId(id)
                .orElseThrow(() -> new UseCaseException(UseCaseMensagem.SOCIETY_NAO_CADASTRADO));

        society.ativar();

        societyGateway.salvar(society);

        log.info("M=ativar, message=Society ativado por id com sucesso usecase, id={}", id);
    }

    public void inativar(Long id) {
        log.info("M=inativar, message=Inativando Society por id usecase, id={}", id);

        Society society = societyGateway.buscarPorId(id)
                .orElseThrow(() -> new UseCaseException(UseCaseMensagem.SOCIETY_NAO_CADASTRADO));

        society.inativar();

        societyGateway.salvar(society);

        log.info("M=inativar, message=Society inativado por id com sucesso usecase, id={}", id);
    }
}
