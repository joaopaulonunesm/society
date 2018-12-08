package com.society.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.society.model.Society;

@Repository
public interface SocietyRepository extends JpaRepository<Society, Long> {

	public Optional<Society> findByNomeUrl(String nomeUrl);

}
