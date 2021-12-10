package com.society.gerenciamento.application.mapper;

import com.society.gerenciamento.application.dto.SocietyRequest;
import com.society.gerenciamento.application.dto.SocietyResponse;
import com.society.gerenciamento.domain.models.Society;

import java.util.List;
import java.util.stream.Collectors;

public class SocietyMapper {

    public static Society societyRequestParaSociety(SocietyRequest societyRequest){
        Society society = Society.builder()
                .celular(societyRequest.getCelular())
                .descricao(societyRequest.getDescricao())
                .endereco(societyRequest.getEndereco())
                .nome(societyRequest.getNome())
                .nomeResponsavel(societyRequest.getNomeResponsavel())
                .observacao(societyRequest.getObservacao())
                .telefone(societyRequest.getTelefone())
                .valorHora(societyRequest.getValorHora())
                .quantidadeCampos(societyRequest.getQuantidadeCampos())
                .cep(societyRequest.getCep())
                .urlFoto(societyRequest.getUrlFoto())
                .email(societyRequest.getEmail())
                .build();
        return society;
    }

    public static SocietyResponse societyParaResponse(Society society){
        return SocietyResponse.builder()
                .id(society.getId())
                .status(society.getStatus())
                .celular(society.getCelular())
                .descricao(society.getDescricao())
                .endereco(society.getEndereco())
                .nome(society.getNome())
                .nomeResponsavel(society.getNomeResponsavel())
                .observacao(society.getObservacao())
                .telefone(society.getTelefone())
                .valorHora(society.getValorHora())
                .quantidadeCampos(society.getQuantidadeCampos())
                .cep(society.getCep())
                .urlFoto(society.getUrlFoto())
                .nomeUrl(society.getNomeUrl())
                .email(society.getEmail())
                .build();
    }

    public static List<SocietyResponse> listSocietyParaResponse(List<Society> societies) {
        return societies.stream().map((society) -> societyParaResponse(society)).collect(Collectors.toList());
    }
}
