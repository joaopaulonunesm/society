package com.society.agendamento.infrastructure.dataproviders;

import com.society.agendamento.domain.models.Society;
import com.society.agendamento.domain.gateways.SocietyGateway;
import com.society.agendamento.infrastructure.dataproviders.webservice.ResponseApiDTO;
import com.society.agendamento.infrastructure.dataproviders.webservice.SocietyFeignClient;
import com.society.agendamento.infrastructure.dataproviders.webservice.SocietyResponse;
import com.society.agendamento.infrastructure.exceptions.DataProviderException;
import com.society.agendamento.infrastructure.exceptions.DataProviderMensagem;
import com.society.agendamento.infrastructure.mappers.SocietyResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.society.agendamento.infrastructure.mappers.SocietyResponseMapper.societyResponseParaSociety;

@RequiredArgsConstructor
@Service
@Slf4j
public class SocietyDataProvider implements SocietyGateway {

    private final SocietyFeignClient societyFeignClient;

    @Override
    public Optional<Society> buscarPorId(Long idSociety) {
        log.info("M=buscarPorId, message=Buscando Society por idSociety, idSociety={}", idSociety);

        Optional<SocietyResponse> societyResponse = buscarSocietyIntegracao(idSociety);

        Optional<Society> society = societyResponseParaSociety(societyResponse);

        log.info("M=buscarPorId, message=Society por id encontrado, idSociety={}, society={}", idSociety, society);
        return society;
    }

    private Optional<SocietyResponse> buscarSocietyIntegracao(Long idSociety) {
        try {
            log.info("M=buscarSocietyIntegracao, message=Buscando Society no society-service, idSociety={}", idSociety);
            ResponseEntity<ResponseApiDTO<SocietyResponse>> responseBuscaSociety = societyFeignClient.buscaSocietyPorId(idSociety);

            if (HttpStatus.NO_CONTENT.equals(responseBuscaSociety.getStatusCode()) || responseBuscaSociety.getBody() == null) {
                log.info("M=buscarSocietyIntegracao, message=Society não encontrado no society-service, idSociety={}", idSociety);
                return Optional.empty();
            }

            SocietyResponse societyResponse = responseBuscaSociety.getBody().getData();

            log.info("M=buscarSocietyIntegracao, message=Society encontrado no society-service, idSociety={}, societyResponse={}", idSociety, societyResponse);
            return Optional.of(societyResponse);

        } catch (Exception exception) {
            log.info("M=buscarSocietyIntegracao, message=Erro na comunicação com society-service, idSociety={}, erro={}", idSociety, exception.getMessage());
            throw new DataProviderException(DataProviderMensagem.ERRO_COMUNICACAO_SOCIETY_SERVICE);
        }
    }
}
