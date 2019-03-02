package com.society.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.society.enums.TipoLogin;
import com.society.exception.BusinessException;
import com.society.model.Usuario;
import com.society.repository.UsuarioRepository;

@Component
public class UsuarioBusiness {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private LoginBusiness loginBusiness;

	public Usuario criar(Usuario usuario) throws BusinessException {

		usuario.setLogin(loginBusiness.completarLogin(usuario.getLogin()));
		usuario.getLogin().setTipoLogin(TipoLogin.USUARIO);

		return usuarioRepository.save(usuario);
	}

	public Usuario alterar(String token, Usuario usuario) throws BusinessException {

		Usuario usuarioExistente = buscarPorToken(token);

		usuarioExistente.setNome(usuario.getNome());
		usuarioExistente.setCelular(usuario.getCelular());
		usuarioExistente.setTelefone(usuario.getTelefone());
		usuarioExistente.getLogin().setEmail(usuario.getLogin().getEmail());

		return usuarioRepository.save(usuarioExistente);
	}

	public Usuario buscarPorToken(String token) throws BusinessException {
		return usuarioRepository.findByLoginId(loginBusiness.buscarPorToken(token).getId());
	}

	public Usuario buscarPorId(Long id) throws BusinessException {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (!usuario.isPresent()) {
			throw new BusinessException("Usuário de id " + id + "não encontrado.", HttpStatus.NOT_FOUND);
		}

		return usuario.get();
	}

}
