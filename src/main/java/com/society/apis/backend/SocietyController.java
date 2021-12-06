package com.society.apis.backend;

import com.society.apis.backend.middleware.ResponseApiVO;
import com.society.usecases.society.StatusSociety;
import com.society.infrastructure.repositories.society.Society;
import com.society.usecases.society.SocietyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/v1/socities")
@RequiredArgsConstructor
public class SocietyController {

    private final SocietyUseCase societyUseCase;

    @PostMapping
    public ResponseEntity<ResponseApiVO<Society>> criar(@RequestBody Society society) {

        Society societyResponse = societyUseCase.criar(society);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseApiVO(societyResponse));
    }

    @PutMapping
    public ResponseEntity<ResponseApiVO<Society>> alterar(@RequestHeader(value = "Authorization") String token, @RequestBody Society society) {
        return ResponseEntity.ok(new ResponseApiVO(societyUseCase.alterar(token, society)));
    }

    @PutMapping("/{id}/ativar")
    public ResponseEntity<ResponseApiVO<Society>> ativar(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseApiVO(societyUseCase.ativarOuInativar(id, StatusSociety.ATIVO)));
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        societyUseCase.ativarOuInativar(id, StatusSociety.INATIVO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<ResponseApiVO<List<Society>>> buscarTodos() {
        return ResponseEntity.ok(new ResponseApiVO(societyUseCase.buscarTodos()));
    }

    @GetMapping("/nome/{nomeUrl}")
    public ResponseEntity<ResponseApiVO<Society>> buscarPorNomeUrl(@PathVariable String nomeUrl) {
        return ResponseEntity.ok(new ResponseApiVO(societyUseCase.buscarPorNomeUrl(nomeUrl)));
    }
}
