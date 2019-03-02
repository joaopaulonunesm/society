package com.society.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.business.UsuarioBusiness;
import com.society.exception.BusinessException;
import com.society.model.Login;
import com.society.model.Usuario;
import com.society.model.vo.UsuarioVO;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioBusiness usuarioBusiness;

	public Usuario criar(UsuarioVO usuarioVO) throws BusinessException {

		Usuario usuario = new Usuario();
		usuario.setNome(usuarioVO.getNome());
		usuario.setCelular(usuarioVO.getCelular());
		usuario.setTelefone(usuarioVO.getTelefone());

		Login login = new Login(usuarioVO.getEmail(), usuarioVO.getSenha());
		usuario.setLogin(login);

		return usuarioBusiness.criar(usuario);
	}

	public Usuario alterar(String token, Usuario usuario) throws BusinessException {
		return usuarioBusiness.alterar(token, usuario);
	}

	public Usuario buscarPorToken(String token) throws BusinessException {
		return usuarioBusiness.buscarPorToken(token);
	}

}
