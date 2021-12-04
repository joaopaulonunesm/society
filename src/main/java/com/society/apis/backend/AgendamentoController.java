package com.society.apis.backend;

import com.society.usecases.agendamento.StatusAgendamento;
import com.society.infrastructure.repositories.agendamento.Agendamento;
import com.society.usecases.agendamento.AgendamentoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoUseCase agendamentoUseCase;

    @PostMapping
    public ResponseEntity<ResponseApiVO> agendar(@RequestBody Agendamento agendamento) {
        return new ResponseEntity<>(new ResponseApiVO().withData(agendamentoUseCase.criar(agendamento)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApiVO> alterar(@PathVariable Long id, @RequestBody Agendamento agendamento) {
        return new ResponseEntity<>(new ResponseApiVO().withData(agendamentoUseCase.alterar(id, agendamento)), HttpStatus.OK);
    }

    @PutMapping("/{id}/confirmacao")
    public ResponseEntity<ResponseApiVO> confirmar(@PathVariable Long id) {
        return new ResponseEntity<>(new ResponseApiVO().withData(agendamentoUseCase.confirmarOuCancelar(id, StatusAgendamento.CONFIRMADO)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/cancelamento")
    public ResponseEntity<Object> cancelar(@PathVariable Long id) {
        agendamentoUseCase.confirmarOuCancelar(id, StatusAgendamento.CANCELADO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApiVO> buscarPorId(@PathVariable Long id) {
        return new ResponseEntity<>(new ResponseApiVO().withData(agendamentoUseCase.buscarPorId(id)), HttpStatus.OK);
    }

    @GetMapping("/agendamentos/society/{idSociety}")
    public ResponseEntity<ResponseApiVO> buscarPorSociety(@PathVariable Long idSociety) {
        return new ResponseEntity<>(new ResponseApiVO().withData(agendamentoUseCase.buscarPorSociety(idSociety)), HttpStatus.OK);
    }

    @GetMapping("/v1/agendamentos/society")
    public ResponseEntity<ResponseApiVO> buscaComTokenPorSociety(@RequestHeader(value = "Authorization") String token) {
        return new ResponseEntity<>(new ResponseApiVO().withData(agendamentoUseCase.buscaComTokenPorSociety(token)), HttpStatus.OK);
    }

}