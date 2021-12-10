package com.society.gerenciamento.infrastructure.dataproviders;

import com.society.gerenciamento.domain.models.Society;
import com.society.gerenciamento.domain.gateways.SocietyGateway;
import com.society.gerenciamento.infrastructure.dataproviders.repository.SocietyEntity;
import com.society.gerenciamento.infrastructure.dataproviders.repository.SocietyRepository;
import com.society.gerenciamento.infrastructure.mappers.SocietyEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class SocietyDataProvider implements SocietyGateway {

    private final SocietyRepository societyRepository;

    @Override
    public Society salvar(Society society) {
        log.info("M=salvar, message=Salvando society no banco de dados, society={}", society);
        SocietyEntity societyEntity = SocietyEntityMapper.societyParaEntity(society);

        societyEntity = societyRepository.save(societyEntity);

        society.defineId(societyEntity.getId());

        log.info("M=salvar, message=Society salvo no banco de dados, society={}", society);
        return society;
    }

    @Override
    public List<Society> buscarTodos() {
        log.info("M=buscarTodos, message=Buscando todos os societies no banco de dados");

        List<SocietyEntity> societiesEntity = societyRepository.findAll();

        List<Society> societies = SocietyEntityMapper.listEntityParaSociety(societiesEntity);

        log.info("M=buscarTodos, message=Buscando todos os societies no banco de dados, societies.size={}", societies.size());
        return societies;
    }

    @Override
    public Optional<Society> buscarPorId(Long id) {
        log.info("M=buscarPorId, message=Buscando society por id no banco de dados, id={}", id);

        Optional<SocietyEntity> societyEntity = societyRepository.findById(id);

        if(societyEntity.isEmpty()){
            return Optional.empty();
        }

        Society society = SocietyEntityMapper.entityParaSociety(societyEntity.get());

        log.info("M=buscarPorId, message=Society por id encontrado no banco de dados, id={}, society={}", id, society);
        return Optional.of(society);
    }

    @Override
    public Optional<Society> buscaPorNomeUrl(String nomeUrl) {
        log.info("M=buscaPorNomeUrl, message=Buscando society por nome url no banco de dados, nomeUrl={}", nomeUrl);
        Optional<SocietyEntity> societyEntity = societyRepository.findByNomeUrl(nomeUrl);

        if(societyEntity.isEmpty()){
            return Optional.empty();
        }

        Society society = SocietyEntityMapper.entityParaSociety(societyEntity.get());

        log.info("M=buscaPorNomeUrl, message=Society por nome url encontrado no banco de dados, nomeUrl={}, society={}", nomeUrl, society);
        return Optional.of(society);
    }
}
