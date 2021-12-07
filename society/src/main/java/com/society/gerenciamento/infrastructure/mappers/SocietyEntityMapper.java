package com.society.gerenciamento.infrastructure.mappers;

import com.society.gerenciamento.domain.entities.Society;
import com.society.gerenciamento.infrastructure.dataproviders.repository.SocietyEntity;

import java.util.List;
import java.util.stream.Collectors;

public class SocietyEntityMapper {

    public static SocietyEntity societyParaEntity(Society society){
        return SocietyEntity.builder()
                .id(society.getId())
                .status(society.getStatus())
                .quantidadeCampos(society.getQuantidadeCampos())
                .celular(society.getCelular())
                .cep(society.getCep())
                .descricao(society.getDescricao())
                .endereco(society.getEndereco())
                .nome(society.getNome())
                .nomeResponsavel(society.getNomeResponsavel())
                .nomeUrl(society.getNomeUrl())
                .observacao(society.getObservacao())
                .telefone(society.getTelefone())
                .urlFoto(society.getUrlFoto())
                .valorHora(society.getValorHora())
                .build();
    }

    public static List<Society> listEntityParaSociety(List<SocietyEntity> societiesEntity){
        return societiesEntity.stream().map((society) -> entityParaSociety(society)).collect(Collectors.toList());
    }

    public static Society entityParaSociety(SocietyEntity societyEntity){
        return Society.builder()
                .id(societyEntity.getId())
                .status(societyEntity.getStatus())
                .quantidadeCampos(societyEntity.getQuantidadeCampos())
                .celular(societyEntity.getCelular())
                .cep(societyEntity.getCep())
                .descricao(societyEntity.getDescricao())
                .endereco(societyEntity.getEndereco())
                .nome(societyEntity.getNome())
                .nomeResponsavel(societyEntity.getNomeResponsavel())
                .nomeUrl(societyEntity.getNomeUrl())
                .observacao(societyEntity.getObservacao())
                .telefone(societyEntity.getTelefone())
                .urlFoto(societyEntity.getUrlFoto())
                .valorHora(societyEntity.getValorHora())
                .build();
    }
}
