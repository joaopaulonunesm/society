package com.society.agendamento.infrastructure.mappers;

import com.society.agendamento.domain.models.Society;
import com.society.agendamento.infrastructure.dataproviders.webservice.SocietyResponse;

import java.util.Optional;

public class SocietyResponseMapper {

    public static Optional<Society> societyResponseParaSociety(Optional<SocietyResponse> societyResponse){
        if(societyResponse.isEmpty()){
            return Optional.empty();
        }

        Society society = Society.builder()
                .id(societyResponse.get().getId())
                .quantidadeCampos(societyResponse.get().getQuantidadeCampos())
                .status(societyResponse.get().getStatus())
                .build();

        return Optional.of(society);
    }
}
