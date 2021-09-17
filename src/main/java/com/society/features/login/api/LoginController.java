package com.society.features.login.api;

import com.society.features.login.domain.LoginDTO;
import com.society.features.login.business.LoginService;
import com.society.features.society.domain.ResponseSocietyDTO;
import com.society.features.login.domain.TrocarSenhaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ResponseSocietyDTO> logar(@RequestBody LoginDTO login) {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(loginService.logar(login)));
    }

    @PutMapping("/v1/login")
    public ResponseEntity<ResponseSocietyDTO> trocarSenha(@RequestBody TrocarSenhaDTO trocarSenhaDTO, @RequestHeader(value = "Authorization") String token) {
        loginService.trocarSenha(token, trocarSenhaDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/v1/login")
    public ResponseEntity<ResponseSocietyDTO> buscarPorToken(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(loginService.buscarPorTokenAPI(token)));
    }

}
