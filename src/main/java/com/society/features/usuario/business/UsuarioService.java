package com.society.features.usuario.business;

import com.society.features.login.domain.Login;
import com.society.features.usuario.domain.Usuario;
import com.society.features.usuario.domain.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	private final UsuarioBusiness usuarioBusiness;

	public Usuario criar(UsuarioDTO usuarioDTO) {

		Usuario usuario = new Usuario();
		usuario.setNome(usuarioDTO.getNome());
		usuario.setCelular(usuarioDTO.getCelular());
		usuario.setTelefone(usuarioDTO.getTelefone());

		Login login = new Login(usuarioDTO.getEmail(), usuarioDTO.getSenha());
		usuario.setLogin(login);

		return usuarioBusiness.criar(usuario);
	}

	public Usuario alterar(String token, Usuario usuario) {
		return usuarioBusiness.alterar(token, usuario);
	}

	public Usuario buscarPorToken(String token) {
		return usuarioBusiness.buscarPorToken(token);
	}

	public Usuario buscarPorId(Long id) {
		return usuarioBusiness.buscarPorId(id);
	}
}
