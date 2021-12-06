package com.society.apis;

import com.society.apis.middleware.ResponseApiVO;
import com.society.infrastructure.repositories.agendamento.Agendamento;
import com.society.usecases.agendamento.AgendamentoUseCase;
import com.society.usecases.agendamento.StatusAgendamento;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoUseCase agendamentoUseCase;

    @PostMapping
    public ResponseEntity<ResponseApiVO<Agendamento>> agendar(@RequestBody Agendamento agendamento) {
        return ResponseEntity.ok(new ResponseApiVO(agendamentoUseCase.criar(agendamento)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApiVO<Agendamento>> alterar(@PathVariable Long id, @RequestBody Agendamento agendamento) {
        return ResponseEntity.ok(new ResponseApiVO(agendamentoUseCase.alterar(id, agendamento)));
    }

    @PutMapping("/{id}/confirmacao")
    public ResponseEntity<ResponseApiVO<Agendamento>> confirmar(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseApiVO(agendamentoUseCase.confirmarOuCancelar(id, StatusAgendamento.CONFIRMADO)));
    }

    @DeleteMapping("/{id}/cancelamento")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        agendamentoUseCase.confirmarOuCancelar(id, StatusAgendamento.CANCELADO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApiVO<Agendamento>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(new ResponseApiVO(agendamentoUseCase.buscarPorId(id)));
    }

    @GetMapping("/agendamentos/society/{idSociety}")
    public ResponseEntity<ResponseApiVO> buscarPorSociety(@PathVariable Long idSociety) {
        return ResponseEntity.ok(new ResponseApiVO(agendamentoUseCase.buscarPorSociety(idSociety)));
    }
}