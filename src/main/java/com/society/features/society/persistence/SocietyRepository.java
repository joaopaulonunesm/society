package com.society.features.society.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.society.features.society.domain.Society;

@Repository
public interface SocietyRepository extends JpaRepository<Society, Long> {

	Optional<Society> findByNomeUrl(String nomeUrl);

	Optional<Society> findByLoginId(Long id);

}
