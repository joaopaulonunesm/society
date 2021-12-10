package com.society.gerenciamento.infrastructure.dataproviders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocietyRepository extends JpaRepository<SocietyEntity, Long> {

    Optional<SocietyEntity> findByNomeUrl(String nomeUrl);
}