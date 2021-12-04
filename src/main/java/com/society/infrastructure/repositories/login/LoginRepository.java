package com.society.infrastructure.repositories.login;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<Login, Long>{

	public Login findByEmail(String email);

	public Login findByToken(String substring);
	
}
