package com.society.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.society.exception.BusinessException;
import com.society.model.Login;
import com.society.model.Usuario;
import com.society.model.vo.UsuarioVO;
import com.society.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private LoginService loginService;
	
	public Usuario criar(UsuarioVO usuarioVO) throws BusinessException {

		Usuario usuario = new Usuario();
		
		usuario.setLogin(loginService.completarLogin(new Login(usuarioVO.getEmail(), usuarioVO.getSenha())));
		usuario.setModerador(false);
		usuario.setNome(usuarioVO.getNome());
		usuario.setCelular(usuarioVO.getCelular());
		usuario.setTelefone(usuarioVO.getTelefone());
		
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
		return usuarioRepository.findByLoginId(loginService.buscarPorToken(token).getId());
	}

	public Usuario buscarPorId(Long id) throws BusinessException {
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if(!usuario.isPresent()) {
			throw new BusinessException("Usuário de id "+ id +"não encontrado.", HttpStatus.NOT_FOUND);
		}
		
		return usuario.get();
	}

}
