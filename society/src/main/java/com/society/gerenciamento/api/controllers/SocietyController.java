package com.society.gerenciamento.api.controllers;

import com.society.gerenciamento.api.middlewares.ResponseApi;
import com.society.gerenciamento.application.dto.SocietyRequest;
import com.society.gerenciamento.application.dto.SocietyResponse;
import com.society.gerenciamento.application.usecases.SocietyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/v1/societies")
@RequiredArgsConstructor
@Validated
public class SocietyController {

    private final SocietyUseCase societyUseCase;

    @PostMapping
    public ResponseEntity<ResponseApi<SocietyResponse>> criar(@RequestBody @Valid SocietyRequest societyRequest) {

        SocietyResponse societyResponse = societyUseCase.criar(societyRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseApi(societyResponse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi<SocietyResponse>> alterar(@PathVariable Long id, @RequestBody SocietyRequest societyRequest) {
        return ResponseEntity.ok(new ResponseApi(societyUseCase.alterar(id, societyRequest)));
    }

    @PutMapping("/{id}/ativar")
    public ResponseEntity<ResponseApi<SocietyResponse>> ativar(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseApi(societyUseCase.ativar(id)));
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<ResponseApi<SocietyResponse>> inativar(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseApi(societyUseCase.inativar(id)));
    }

    @GetMapping
    public ResponseEntity<ResponseApi<List<SocietyResponse>>> buscarTodos() {
        return ResponseEntity.ok(new ResponseApi(societyUseCase.buscarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<SocietyResponse>> buscarPorId(@PathVariable Long id) {
        SocietyResponse societyResponse = societyUseCase.buscarPorId(id);

        if (societyResponse == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(new ResponseApi(societyResponse));
    }

    @GetMapping("/nome/{nomeUrl}")
    public ResponseEntity<ResponseApi<SocietyResponse>> buscarPorNomeUrl(@PathVariable String nomeUrl) {
        return ResponseEntity.ok(new ResponseApi(societyUseCase.buscarPorNomeUrl(nomeUrl)));
    }
}
