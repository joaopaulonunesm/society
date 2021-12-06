package com.society.gerenciamento.agendamento.api.controllers;

import com.society.gerenciamento.agendamento.api.middlewares.ResponseApiVO;
import com.society.gerenciamento.agendamento.application.dto.AgendamentoResponse;
import com.society.gerenciamento.agendamento.application.dto.AlteraHorarioAgendamentoRequest;
import com.society.gerenciamento.agendamento.application.dto.CriaAgendamentoRequest;
import com.society.gerenciamento.agendamento.application.usecases.AgendamentoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/v1/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoUseCase agendamentoUseCase;

    @PostMapping
    public ResponseEntity<ResponseApiVO<AgendamentoResponse>> agendar(@RequestBody CriaAgendamentoRequest criaAgendamentoRequest) {
        return ResponseEntity.ok(new ResponseApiVO(agendamentoUseCase.agendar(criaAgendamentoRequest)));
    }

    @PutMapping("/{id}/horario")
    public ResponseEntity<ResponseApiVO<AgendamentoResponse>> alterarHorario(@PathVariable Long id, @RequestBody AlteraHorarioAgendamentoRequest alteraHorarioAgendamentoRequest) {
        return ResponseEntity.ok(new ResponseApiVO(agendamentoUseCase.alterarHorario(id, alteraHorarioAgendamentoRequest)));
    }

    @PutMapping("/{id}/confirmacao")
    public ResponseEntity<ResponseApiVO<AgendamentoResponse>> confirmar(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseApiVO(agendamentoUseCase.confirmar(id)));
    }

    @DeleteMapping("/{id}/cancelamento")
    public ResponseEntity<ResponseApiVO<AgendamentoResponse>> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseApiVO(agendamentoUseCase.cancelar(id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApiVO<AgendamentoResponse>> buscarPorId(@PathVariable Long id) {
        Optional<AgendamentoResponse> agendamentoResponse = agendamentoUseCase.buscarPorId(id);

        if(agendamentoResponse.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(new ResponseApiVO(agendamentoResponse));
    }
}