package com.society.agendamento.infrastructure.dataproviders;

import com.society.agendamento.domain.entidades.Society;
import com.society.agendamento.domain.gateway.SocietyGateway;
import com.society.agendamento.infrastructure.dataproviders.webservice.ResponseApiDTO;
import com.society.agendamento.infrastructure.dataproviders.webservice.SocietyFeignClient;
import com.society.agendamento.infrastructure.dataproviders.webservice.SocietyResponse;
import com.society.agendamento.infrastructure.exceptions.DataProviderException;
import com.society.agendamento.infrastructure.exceptions.DataProviderMensagem;
import com.society.agendamento.infrastructure.mappers.SocietyResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SocietyDataProvider implements SocietyGateway {

    private final SocietyFeignClient societyFeignClient;

    @Override
    public Optional<Society> buscarPorId(Long idSociety) {

        Optional<SocietyResponse> societyResponse = buscarSocietyIntegracao(idSociety);

        return SocietyResponseMapper.societyResponseParaSociety(societyResponse);
    }

    private Optional<SocietyResponse> buscarSocietyIntegracao(Long idSociety){
        try {
            ResponseEntity<ResponseApiDTO<SocietyResponse>> responseBuscaSociety = societyFeignClient.buscaSocietyPorId(idSociety);

            if(responseBuscaSociety.getBody() == null){
                return Optional.empty();
            }

            return Optional.of(responseBuscaSociety.getBody().getData());

        } catch (Exception exception) {
            throw new DataProviderException(DataProviderMensagem.ERRO_COMUNICACAO_SOCIETY_SERVICE);
        }
    }
}
