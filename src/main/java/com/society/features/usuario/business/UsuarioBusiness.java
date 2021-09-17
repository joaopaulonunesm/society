package com.society.features.usuario.business;

import com.society.exceptions.BusinessException;
import com.society.features.login.business.LoginService;
import com.society.features.login.domain.TipoLogin;
import com.society.features.usuario.domain.Usuario;
import com.society.features.usuario.persistence.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioBusiness {

    private final UsuarioRepository usuarioRepository;
    private final LoginService loginService;

    public Usuario criar(Usuario usuario) {
        usuario.setLogin(loginService.completarLogin(usuario.getLogin()));
        usuario.getLogin().setTipoLogin(TipoLogin.USUARIO.getId());

        return usuarioRepository.save(usuario);
    }

    public Usuario alterar(String token, Usuario usuario) {

        Usuario usuarioExistente = buscarPorToken(token);

        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setCelular(usuario.getCelular());
        usuarioExistente.setTelefone(usuario.getTelefone());
        usuarioExistente.getLogin().setEmail(usuario.getLogin().getEmail());

        return usuarioRepository.save(usuarioExistente);
    }

    public Usuario buscarPorToken(String token) {
        Long idUsuario = loginService.buscarPorToken(token).getId();
        return usuarioRepository.findByLoginId(idUsuario)
                .orElseThrow(() -> new BusinessException("Erro ao buscar usuário por token.", HttpStatus.UNAUTHORIZED));
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário de id " + id + "não encontrado.", HttpStatus.NOT_FOUND));
    }

}
