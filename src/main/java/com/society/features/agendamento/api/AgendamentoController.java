package com.society.features.agendamento.api;

import com.society.features.agendamento.domain.Agendamento;
import com.society.features.society.domain.ResponseSocietyDTO;
import com.society.features.agendamento.business.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping("/agendamentos")
    public ResponseEntity<ResponseSocietyDTO> criar(@RequestBody Agendamento agendamento) {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(agendamentoService.criar(agendamento)));
    }

    @GetMapping("/agendamentos/{id}")
    public ResponseEntity<ResponseSocietyDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(agendamentoService.buscaPorIdDTO(id)));
    }

    @GetMapping("/v1/agendamentos/society")
    public ResponseEntity<ResponseSocietyDTO> buscaComTokenPorSociety(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(agendamentoService.buscaComTokenPorSociety(token)));
    }

    @GetMapping("/v1/agendamentos/usuario")
    public ResponseEntity<ResponseSocietyDTO> buscaComTokenPorUsuario(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(agendamentoService.buscaComTokenPorUsuarioDTO(token)));
    }

    @PutMapping("/agendamentos/{id}/confirmacao/{confirmacao}")
    public ResponseEntity<ResponseSocietyDTO> confirmarOuCancelar(@PathVariable Long id, @PathVariable String confirmacao) {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(agendamentoService.confirmarOuCancelar(id, confirmacao)));
    }
}