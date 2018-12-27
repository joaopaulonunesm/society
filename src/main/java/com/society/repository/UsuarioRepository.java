package com.society.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.society.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Usuario findByLoginId(Long id);

}
