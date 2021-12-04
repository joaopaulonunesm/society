package com.society.apis.backend;

import com.society.usecases.models.LoginRequestVO;
import com.society.usecases.models.ResponseSocietyVO;
import com.society.usecases.models.TrocarSenhaVO;
import com.society.usecases.services.LoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1/logins")
@RequiredArgsConstructor
public class LoginController {

    private final LoginUseCase loginUseCase;

    @PostMapping
    public ResponseEntity<ResponseSocietyVO> logar(@RequestBody LoginRequestVO login) {
        return new ResponseEntity<>(new ResponseSocietyVO().withData(loginUseCase.logar(login)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseSocietyVO> trocarSenha(@RequestBody TrocarSenhaVO trocarSenhaVO, @RequestHeader(value = "Authorization") String token) {
        loginUseCase.trocarSenha(token, trocarSenhaVO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
