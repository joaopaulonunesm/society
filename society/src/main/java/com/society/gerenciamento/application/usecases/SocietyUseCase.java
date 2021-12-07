package com.society.gerenciamento.application.usecases;

import com.society.gerenciamento.application.dto.SocietyRequest;
import com.society.gerenciamento.application.dto.SocietyResponse;
import com.society.gerenciamento.application.exceptions.UseCaseException;
import com.society.gerenciamento.application.exceptions.UseCaseMensagem;
import com.society.gerenciamento.application.mapper.SocietyMapper;
import com.society.gerenciamento.domain.entities.Society;
import com.society.gerenciamento.domain.enums.StatusSociety;
import com.society.gerenciamento.domain.gateways.SocietyGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocietyUseCase {

    private final SocietyGateway societyGateway;

    public SocietyResponse criar(SocietyRequest societyRequest) {

        Society society = SocietyMapper.societyRequestParaSociety(societyRequest);

        society.defineStatus(StatusSociety.ATIVO);

        society = societyGateway.criar(society);

        return SocietyMapper.societyParaResponse(society);
    }

    public SocietyResponse alterar(Long id, SocietyRequest societyRequest) {

        Society societyExistente = societyGateway.buscarPorId(id).orElseThrow(() -> new UseCaseException(UseCaseMensagem.SOCIETY_NAO_CADASTRADO));

        Society society = SocietyMapper.societyRequestParaSociety(societyRequest);

        societyExistente.atualizarDados(society);

        societyExistente = societyGateway.alterar(societyExistente);

        return SocietyMapper.societyParaResponse(societyExistente);
    }

    public List<SocietyResponse> buscarTodos() {

        List<Society> societies = societyGateway.buscarTodos();

        return SocietyMapper.listSocietyParaResponse(societies);
    }

    public SocietyResponse buscarPorId(Long id) {

        Optional<Society> society = societyGateway.buscarPorId(id);

        if(society.isEmpty()){
            return null;
        }

        return SocietyMapper.societyParaResponse(society.get());
    }

    public SocietyResponse buscarPorNomeUrl(String nomeUrl) {

        Society society = societyGateway.buscaPorNomeUrl(nomeUrl).orElseThrow(() -> new UseCaseException(UseCaseMensagem.SOCIETY_NAO_CADASTRADO));

        return SocietyMapper.societyParaResponse(society);
    }

    public SocietyResponse ativar(Long id) {

        Society society = societyGateway.buscarPorId(id).orElseThrow(() -> new UseCaseException(UseCaseMensagem.SOCIETY_NAO_CADASTRADO));

        society.defineStatus(StatusSociety.ATIVO);

        society = societyGateway.alterar(society);

        return SocietyMapper.societyParaResponse(society);
    }

    public SocietyResponse inativar(Long id) {

        Society society = societyGateway.buscarPorId(id).orElseThrow(() -> new UseCaseException(UseCaseMensagem.SOCIETY_NAO_CADASTRADO));

        society.defineStatus(StatusSociety.INATIVO);

        society = societyGateway.alterar(society);

        return SocietyMapper.societyParaResponse(society);
    }

}
