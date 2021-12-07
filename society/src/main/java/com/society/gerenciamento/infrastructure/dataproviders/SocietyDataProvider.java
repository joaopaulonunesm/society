package com.society.gerenciamento.infrastructure.dataproviders;

import com.society.gerenciamento.domain.entities.Society;
import com.society.gerenciamento.domain.gateways.SocietyGateway;
import com.society.gerenciamento.infrastructure.dataproviders.repository.SocietyEntity;
import com.society.gerenciamento.infrastructure.dataproviders.repository.SocietyRepository;
import com.society.gerenciamento.infrastructure.exceptions.DataProviderException;
import com.society.gerenciamento.infrastructure.exceptions.DataProviderMensagem;
import com.society.gerenciamento.infrastructure.mappers.SocietyEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SocietyDataProvider implements SocietyGateway {

    private final SocietyRepository societyRepository;

    @Override
    public Society criar(Society society) {

        SocietyEntity societyEntity = SocietyEntityMapper.societyParaEntity(society);

        societyEntity = societyRepository.save(societyEntity);

        society.defineId(societyEntity.getId());

        return society;
    }

    @Override
    public Society alterar(Society society) {

        SocietyEntity societyEntity = SocietyEntityMapper.societyParaEntity(society);

        societyEntity = societyRepository.save(societyEntity);

        society.defineId(societyEntity.getId());

        return society;
    }

    @Override
    public List<Society> buscarTodos() {
        List<SocietyEntity> societiesEntity = societyRepository.findAll();

        return SocietyEntityMapper.listEntityParaSociety(societiesEntity);
    }

    @Override
    public Optional<Society> buscarPorId(Long id) {

        Optional<SocietyEntity> societyEntity = societyRepository.findById(id);

        if(societyEntity.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(SocietyEntityMapper.entityParaSociety(societyEntity.get()));
    }

    @Override
    public Optional<Society> buscaPorNomeUrl(String nomeUrl) {
        Optional<SocietyEntity> societyEntity = societyRepository.findByNomeUrl(nomeUrl);

        if(societyEntity.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(SocietyEntityMapper.entityParaSociety(societyEntity.get()));
    }
}
