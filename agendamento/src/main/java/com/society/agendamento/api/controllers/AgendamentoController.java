package com.society.agendamento.api.controllers;

import com.society.agendamento.api.middlewares.ResponseApi;
import com.society.agendamento.application.dto.AgendamentoResponse;
import com.society.agendamento.application.dto.AlteraHorarioAgendamentoRequest;
import com.society.agendamento.application.dto.CriaAgendamentoRequest;
import com.society.agendamento.application.usecases.AgendamentoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/v1/agendamentos")
@RequiredArgsConstructor
@Validated
public class AgendamentoController {

    private final AgendamentoUseCase agendamentoUseCase;

    @PostMapping
    public ResponseEntity<ResponseApi<AgendamentoResponse>> agendar(@RequestBody @Valid CriaAgendamentoRequest criaAgendamentoRequest) {
        return ResponseEntity.ok(new ResponseApi(agendamentoUseCase.agendar(criaAgendamentoRequest)));
    }

    @PutMapping("/{id}/horario")
    public ResponseEntity<ResponseApi<AgendamentoResponse>> alterarHorario(@PathVariable Long id, @RequestBody AlteraHorarioAgendamentoRequest alteraHorarioAgendamentoRequest) {
        return ResponseEntity.ok(new ResponseApi(agendamentoUseCase.alterarHorario(id, alteraHorarioAgendamentoRequest)));
    }

    @PutMapping("/{id}/confirmacao")
    public ResponseEntity<ResponseApi<AgendamentoResponse>> confirmar(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseApi(agendamentoUseCase.confirmar(id)));
    }

    @DeleteMapping("/{id}/cancelamento")
    public ResponseEntity<ResponseApi<AgendamentoResponse>> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseApi(agendamentoUseCase.cancelar(id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<AgendamentoResponse>> buscarPorId(@PathVariable Long id) {
        Optional<AgendamentoResponse> agendamentoResponse = agendamentoUseCase.buscarPorId(id);

        if(agendamentoResponse.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(new ResponseApi(agendamentoResponse));
    }

    @GetMapping("/society/{idSociety}")
    public ResponseEntity<ResponseApi<List<AgendamentoResponse>>> buscaPorSociety(@PathVariable Long idSociety) {
        List<AgendamentoResponse> agendamentoResponse = agendamentoUseCase.buscarPorSociety(idSociety);

        if(agendamentoResponse.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(new ResponseApi(agendamentoResponse));
    }
}