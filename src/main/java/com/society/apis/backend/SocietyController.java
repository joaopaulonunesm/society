package com.society.apis.backend;

import com.society.usecases.society.StatusSociety;
import com.society.infrastructure.repositories.society.Society;
import com.society.usecases.society.SocietyUseCase;
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
    public ResponseEntity<ResponseApiVO> criar(@RequestBody Society society) {
        return new ResponseEntity<>(new ResponseApiVO().withData(societyUseCase.criar(society)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseApiVO> alterar(@RequestHeader(value = "Authorization") String token, @RequestBody Society society) {
        return new ResponseEntity<>(new ResponseApiVO().withData(societyUseCase.alterar(token, society)), HttpStatus.OK);
    }

    @PutMapping("/{id}/ativar")
    public ResponseEntity<ResponseApiVO> ativar(@PathVariable Long id) {
        return new ResponseEntity<>(new ResponseApiVO().withData(societyUseCase.ativarOuInativar(id, StatusSociety.ATIVO)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<Object> inativar(@PathVariable Long id) {
        societyUseCase.ativarOuInativar(id, StatusSociety.INATIVO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<ResponseApiVO> buscarTodos() {
        return new ResponseEntity<>(new ResponseApiVO().withData(societyUseCase.buscarTodos()), HttpStatus.OK);
    }

    @GetMapping("/nome/{nomeUrl}")
    public ResponseEntity<ResponseApiVO> buscarPorNomeUrl(@PathVariable String nomeUrl) {
        return new ResponseEntity<>(new ResponseApiVO().withData(societyUseCase.buscarPorNomeUrl(nomeUrl)), HttpStatus.OK);
    }

    @GetMapping("/token")
    public ResponseEntity<ResponseApiVO> buscaPorToken(@RequestHeader(value = "Authorization") String token) {
        return new ResponseEntity<>(new ResponseApiVO().withData(societyUseCase.buscarPorToken(token)), HttpStatus.OK);
    }

}
