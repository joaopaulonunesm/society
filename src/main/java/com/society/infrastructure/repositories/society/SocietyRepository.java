package com.society.infrastructure.repositories.society;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocietyRepository extends JpaRepository<Society, Long> {

	public Optional<Society> findByNomeUrl(String nomeUrl);

	public Society findByLoginId(Long id);

}
