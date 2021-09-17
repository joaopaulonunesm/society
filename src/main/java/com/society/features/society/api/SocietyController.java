package com.society.features.society.api;

import com.society.features.society.domain.ResponseSocietyDTO;
import com.society.features.society.domain.Society;
import com.society.features.society.business.SocietyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class SocietyController {

    private final SocietyService societyService;

    @PostMapping("/society")
    public ResponseEntity<ResponseSocietyDTO> criar(@RequestBody Society society) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseSocietyDTO.withData(societyService.criar(society)));
    }

    @PutMapping("/v1/society")
    public ResponseEntity<ResponseSocietyDTO> alterar(@RequestHeader(value = "Authorization") String token, @RequestBody Society society) {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(societyService.alterar(token, society)));
    }

    @PutMapping("/society/{id}/confirmacao/{confirmacao}")
    public ResponseEntity<ResponseSocietyDTO> confirmarOuRecusar(@PathVariable Long id, @PathVariable String confirmacao) {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(societyService.ativarOuInativar(id, confirmacao)));
    }

    @GetMapping("/society")
    public ResponseEntity<ResponseSocietyDTO> buscarTodos() {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(societyService.buscarTodos()));
    }

    @GetMapping("/society/nome/{nomeUrl}")
    public ResponseEntity<ResponseSocietyDTO> buscarPorNomeUrl(@PathVariable String nomeUrl) {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(societyService.buscarPorNomeUrl(nomeUrl)));
    }

    @GetMapping("/v1/society/token")
    public ResponseEntity<ResponseSocietyDTO> buscaPorToken(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(societyService.buscarPorToken(token)));
    }

}
