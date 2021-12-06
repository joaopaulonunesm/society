package com.society.apis.backend;

import com.society.apis.backend.middleware.ResponseApiVO;
import com.society.usecases.login.vo.LoginRequestVO;
import com.society.usecases.login.vo.LoginResponseVO;
import com.society.usecases.login.vo.TrocarSenhaVO;
import com.society.usecases.login.LoginUseCase;
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
    public ResponseEntity<ResponseApiVO<LoginResponseVO>> logar(@RequestBody LoginRequestVO login) {
        return ResponseEntity.ok(new ResponseApiVO(loginUseCase.logar(login)));
    }

    @PutMapping
    public ResponseEntity<Void> trocarSenha(@RequestBody TrocarSenhaVO trocarSenhaVO, @RequestHeader(value = "Authorization") String token) {
        loginUseCase.trocarSenha(token, trocarSenhaVO);
        return ResponseEntity.noContent().build();
    }

}
