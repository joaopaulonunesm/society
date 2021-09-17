package com.society.features.login.business;

import java.util.Date;
import java.util.Optional;

import com.society.features.login.domain.Login;
import com.society.features.login.domain.LoginDTO;
import com.society.features.login.persistence.LoginRepository;
import com.society.features.login.domain.TrocarSenhaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.society.exceptions.BusinessException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
@RequiredArgsConstructor
public class LoginBusiness {

    private final LoginRepository loginRepository;

    public Login buscarPorToken(String token) {
        Optional<Login> login = loginRepository.findByToken(token.contains("Bearer ") ? token.substring(7) : token);
        return login.orElseThrow(() -> new BusinessException("Login não encontrado por Token.", HttpStatus.UNAUTHORIZED));
    }

    public Login logar(LoginDTO loginDTO) {

        if (loginDTO.getEmail().isBlank() || loginDTO.getSenha().isBlank()) {
            throw new BusinessException("Email ou Senha inválido, favor tente novamente.", HttpStatus.UNAUTHORIZED);
        }

        Login login = loginRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new BusinessException("Email ainda não cadastrado, cadastre seu Society!", HttpStatus.UNAUTHORIZED));

        if (!loginDTO.getSenha().equals(login.getSenha())) {
            throw new BusinessException("Combinação de Email e Senha está incorreto.", HttpStatus.UNAUTHORIZED);
        }

        return validaToken(login, true);
    }

    public void trocarSenha(String token, TrocarSenhaDTO trocarSenhaDTO) {

        Login login = buscarPorToken(token);

        if (!login.getSenha().equals(trocarSenhaDTO.getSenhaAtual())) {
            throw new BusinessException("Senha atual incorreta.", HttpStatus.BAD_REQUEST);
        }

        if (login.getSenha().equals(trocarSenhaDTO.getNovaSenha())) {
            throw new BusinessException("Senha informada esta em uso.", HttpStatus.BAD_REQUEST);
        }

        login.setSenha(trocarSenhaDTO.getNovaSenha());

        loginRepository.save(login);
    }

    public Login completarLogin(Login login) {

        if (loginRepository.findByEmail(login.getEmail()).isPresent()) {
            throw new BusinessException("Email já cadastrado, por favor, informe outro email.", HttpStatus.BAD_REQUEST);
        }

        login.setSenha(login.getSenha() == null ? String.valueOf(login.hashCode()) : login.getSenha());

        validaToken(login, false);

        return login;
    }

    public Login manterDados(Login loginExistente, Login login) {

        login.setId(loginExistente.getId());
        login.setToken(loginExistente.getToken());
        login.setSenha(loginExistente.getSenha());
        login.setDataExpiracaoToken(loginExistente.getDataExpiracaoToken());

        return login;
    }

    private Login validaToken(Login login, boolean comitar) {

        if (login.getDataExpiracaoToken() == null || login.getDataExpiracaoToken().before(new Date())) {

            Date expirationDate = new Date(System.currentTimeMillis() + 240 * 60 * 1000);

            String token = Jwts.builder().setSubject(login.getEmail())
                    .signWith(SignatureAlgorithm.HS512, "autenticando").setExpiration(expirationDate).compact();

            login.setToken(token);
            login.setDataExpiracaoToken(expirationDate);

            if (comitar) {
                loginRepository.save(login);
            }
        }
        return login;
    }
}
