package com.society.features.login.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.society.features.login.domain.Login;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long>{

	Optional<Login> findByEmail(String email);

	Optional<Login> findByToken(String token);
	
}
