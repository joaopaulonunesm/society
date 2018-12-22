package com.society.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.society.model.Login;

public interface LoginRepository extends JpaRepository<Login, Long>{

	public Login findByEmail(String email);

	public Login findByToken(String substring);
	
}
