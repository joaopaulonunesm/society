package com.society.gerenciamento.api.controllers;

import com.society.gerenciamento.api.models.ApiResponse;
import com.society.gerenciamento.application.dto.SocietyRequest;
import com.society.gerenciamento.application.dto.SocietyResponse;
import com.society.gerenciamento.application.usecases.SocietyUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/v1/societies")
@RequiredArgsConstructor
@Validated
@Slf4j
public class SocietyController {

    private final SocietyUseCase societyUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<SocietyResponse>> criar(@RequestBody @Valid SocietyRequest societyRequest) {
        log.info("M=criar, message=Recebendo requisição de criação de Society, societyRequest={}", societyRequest);

        SocietyResponse societyResponse = societyUseCase.criar(societyRequest);

        UriComponents uriComponent = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(societyResponse.getId());

        log.info("M=criar, message=Requisição de criação de Society finalizada com sucesso, societyRequest={}, societyResponse={}", societyRequest, societyResponse);
        return ResponseEntity.created(uriComponent.toUri()).body(new ApiResponse(societyResponse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SocietyResponse>> alterar(@PathVariable Long id, @RequestBody @Valid SocietyRequest societyRequest) {
        log.info("M=alterar, message=Recebendo requisição de alteração de Society, societyRequest={}", societyRequest);

        SocietyResponse societyResponse = societyUseCase.alterar(id, societyRequest);

        log.info("M=alterar, message=Rrequisição de alteração de Society finalizada com sucesso, societyRequest={}, societyResponse={}", societyRequest, societyResponse);
        return ResponseEntity.ok(new ApiResponse(societyResponse));
    }

    @PutMapping("/{id}/ativar")
    public ResponseEntity<Void> ativar(@PathVariable Long id) {
        log.info("M=ativar, message=Recebendo requisição de ativação de Society, idSociety={}", id);

        societyUseCase.ativar(id);

        log.info("M=ativar, message=Requisição de ativação de Society finalizada com sucesso, idSociety={}", id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        log.info("M=inativar, message=Recebendo requisição de inativação de Society, idSociety={}", id);

        societyUseCase.inativar(id);

        log.info("M=inativar, message=Requisição de inativação de Society finalizada com sucesso, idSociety={}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SocietyResponse>>> buscarTodos() {
        log.info("M=buscarTodos, message=Recebendo requisição de busca de todos os societies");

        List<SocietyResponse> societiesResponse = societyUseCase.buscarTodos();

        log.info("M=buscarTodos, message=Requisição de busca de todos os societies finalizada com sucesso, societiesResponse.size={}", societiesResponse.size());
        return ResponseEntity.ok(new ApiResponse(societiesResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SocietyResponse>> buscarPorId(@PathVariable Long id) {
        log.info("M=buscarPorId, message=Recebendo requisição de busca de Society por id, idSociety={}", id);

        SocietyResponse societyResponse = societyUseCase.buscarPorId(id);

        if (societyResponse == null) {
            log.info("M=buscarPorId, message=Requisição de busca de Society por id finalizada com sucesso porém sem society encontrado, idSociety={}", id);
            return ResponseEntity.noContent().build();
        }

        log.info("M=buscarPorId, message=Requisição de busca de Society por id finalizada com sucesso, idSociety={}, societyResponse={}", id, societyResponse);
        return ResponseEntity.ok(new ApiResponse(societyResponse));
    }

    @GetMapping("/nome/{nomeUrl}")
    public ResponseEntity<ApiResponse<SocietyResponse>> buscarPorNomeUrl(@PathVariable String nomeUrl) {
        log.info("M=buscarPorNomeUrl, message=Recebendo requisição de busca de Society por nome url, nomeUrl={}", nomeUrl);

        SocietyResponse societyResponse = societyUseCase.buscarPorNomeUrl(nomeUrl);

        log.info("M=buscarPorNomeUrl, message=Requisição de busca de Society por nome url finalizada com sucesso, nomeUrl={}, societyResponse={}", nomeUrl, societyResponse);
        return ResponseEntity.ok(new ApiResponse(societyResponse));
    }
}
