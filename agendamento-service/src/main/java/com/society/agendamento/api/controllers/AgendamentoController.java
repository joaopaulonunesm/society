package com.society.agendamento.api.controllers;

import com.society.agendamento.api.models.ApiResponse;
import com.society.agendamento.application.dto.AgendamentoResponse;
import com.society.agendamento.application.dto.AlteraHorarioAgendamentoRequest;
import com.society.agendamento.application.dto.CriaAgendamentoRequest;
import com.society.agendamento.application.usecases.AgendamentoUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/v1/agendamentos")
@RequiredArgsConstructor
@Validated
@Slf4j
public class AgendamentoController {

    private final AgendamentoUseCase agendamentoUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<AgendamentoResponse>> agendar(@RequestBody @Valid CriaAgendamentoRequest criaAgendamentoRequest) {
        log.info("M=agendar, message=Recebendo requisição de agendamento, criaAgendamentoRequest={}", criaAgendamentoRequest);

        AgendamentoResponse agendarResponse = agendamentoUseCase.agendar(criaAgendamentoRequest);

        UriComponents uriComponent = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(agendarResponse.getId());

        log.info("M=agendar, message=Requisição de agendamento feita com sucesso, criaAgendamentoRequest={}, agendarResponse={}", criaAgendamentoRequest, agendarResponse);
        return ResponseEntity.created(uriComponent.toUri()).body(new ApiResponse(agendarResponse));
    }

    @PutMapping("/{id}/horario")
    public ResponseEntity<ApiResponse<AgendamentoResponse>> alterarHorario(@PathVariable Long id, @RequestBody @Valid AlteraHorarioAgendamentoRequest alteraHorarioAgendamentoRequest) {
        log.info("M=alterarHorario, message=Recebendo requisição de alteração de horario do agendamento, id={}, alteraHorarioAgendamentoRequest={}", id, alteraHorarioAgendamentoRequest);

        AgendamentoResponse agendamentoResponse = agendamentoUseCase.alterarHorario(id, alteraHorarioAgendamentoRequest);

        log.info("M=alterarHorario, message=Recebendo requisição de alteração de horario do agendamento, id={}, alteraHorarioAgendamentoRequest={}, agendamentoResponse={}", id, alteraHorarioAgendamentoRequest, agendamentoResponse);
        return ResponseEntity.ok(new ApiResponse(agendamentoResponse));
    }

    @PutMapping("/{id}/confirmacao")
    public ResponseEntity<Void> confirmar(@PathVariable Long id) {
        log.info("M=confirmar, message=Recebendo requisição de confirmação do agendamento, id={}", id);

        agendamentoUseCase.confirmar(id);

        log.info("M=confirmar, message=Requisição de confirmação do agendamento feita com sucesso, id={}", id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/cancelamento")
    public ResponseEntity<ApiResponse<AgendamentoResponse>> cancelar(@PathVariable Long id) {
        log.info("M=cancelar, message=Recebendo requisição de cancelamento do agendamento, id={}", id);

        agendamentoUseCase.cancelar(id);

        log.info("M=cancelar, message=Requisição de agendamento do agendamento feita com sucesso, id={}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AgendamentoResponse>> buscarPorId(@PathVariable Long id) {
        log.info("M=buscarPorId, message=Recebendo requisição de busca por id do agendamento, id={}", id);

        Optional<AgendamentoResponse> agendamentoResponse = agendamentoUseCase.buscarPorId(id);

        if(agendamentoResponse.isEmpty()){
            log.info("M=buscarPorId, message=Requisição de busca por id do agendamento feita com sucesso porém agendamento não encontrado, id={}", id);
            return ResponseEntity.noContent().build();
        }

        log.info("M=buscarPorId, message=Requisição de busca por id do agendamento feita com sucesso, id={}, agendamentoResponse={}", id, agendamentoResponse);
        return ResponseEntity.ok(new ApiResponse(agendamentoResponse));
    }

    @GetMapping("/society/{idSociety}")
    public ResponseEntity<ApiResponse<List<AgendamentoResponse>>> buscaPorSociety(@PathVariable Long idSociety) {
        log.info("M=buscaPorSociety, message=Recebendo requisição de busca de agendamentos por id do society, idSociety={}", idSociety);

        List<AgendamentoResponse> agendamentoResponse = agendamentoUseCase.buscarPorSociety(idSociety);

        if(agendamentoResponse.isEmpty()){
            log.info("M=buscaPorSociety, message=Requisição de busca agendamento por society id feita com sucesso porém agendamento não encontrado, idSociety={}", idSociety);
            return ResponseEntity.noContent().build();
        }

        log.info("M=buscaPorSociety, message=Requisição de busca de agendamentos por id do society feita com sucesso, idSociety={}, agendamentoResponse.size={}", idSociety, agendamentoResponse.size());
        return ResponseEntity.ok(new ApiResponse(agendamentoResponse));
    }
}