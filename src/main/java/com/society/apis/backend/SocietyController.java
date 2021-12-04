package com.society.apis.backend;

import com.society.usecases.enums.StatusSociety;
import com.society.infrastructure.repositories.society.Society;
import com.society.usecases.models.ResponseSocietyVO;
import com.society.usecases.services.SocietyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1/socities")
@RequiredArgsConstructor
public class SocietyController {

    private final SocietyUseCase societyUseCase;

    @PostMapping
    public ResponseEntity<ResponseSocietyVO> criar(@RequestBody Society society) {
        return new ResponseEntity<>(new ResponseSocietyVO().withData(societyUseCase.criar(society)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseSocietyVO> alterar(@RequestHeader(value = "Authorization") String token, @RequestBody Society society) {
        return new ResponseEntity<>(new ResponseSocietyVO().withData(societyUseCase.alterar(token, society)), HttpStatus.OK);
    }

    @PutMapping("/{id}/ativar")
    public ResponseEntity<ResponseSocietyVO> ativar(@PathVariable Long id) {
        return new ResponseEntity<>(new ResponseSocietyVO().withData(societyUseCase.ativarOuInativar(id, StatusSociety.ATIVO)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<Object> inativar(@PathVariable Long id) {
        societyUseCase.ativarOuInativar(id, StatusSociety.INATIVO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<ResponseSocietyVO> buscarTodos() {
        return new ResponseEntity<>(new ResponseSocietyVO().withData(societyUseCase.buscarTodos()), HttpStatus.OK);
    }

    @GetMapping("/nome/{nomeUrl}")
    public ResponseEntity<ResponseSocietyVO> buscarPorNomeUrl(@PathVariable String nomeUrl) {
        return new ResponseEntity<>(new ResponseSocietyVO().withData(societyUseCase.buscarPorNomeUrl(nomeUrl)), HttpStatus.OK);
    }

    @GetMapping("/token")
    public ResponseEntity<ResponseSocietyVO> buscaPorToken(@RequestHeader(value = "Authorization") String token) {
        return new ResponseEntity<>(new ResponseSocietyVO().withData(societyUseCase.buscarPorToken(token)), HttpStatus.OK);
    }

}
