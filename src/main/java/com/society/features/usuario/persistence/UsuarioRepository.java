package com.society.features.usuario.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.society.features.usuario.domain.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByLoginId(Long id);

}
