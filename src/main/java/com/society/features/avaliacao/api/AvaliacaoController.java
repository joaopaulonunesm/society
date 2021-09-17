package com.society.features.avaliacao.api;

import com.society.features.avaliacao.business.AvaliacaoService;
import com.society.features.avaliacao.domain.AvaliacaoSociety;
import com.society.features.avaliacao.domain.AvaliacaoUsuario;
import com.society.features.society.domain.ResponseSocietyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @PostMapping("/v1/avaliacao/society")
    public ResponseEntity<ResponseSocietyDTO> avaliarSociety(@RequestHeader(value = "Authorization") String token, @RequestBody AvaliacaoSociety avaliacao) {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(avaliacaoService.avaliarSociety(token, avaliacao)));
    }

    @PostMapping("/v1/avaliacao/usuario")
    public ResponseEntity<ResponseSocietyDTO> avaliarUsuario(@RequestHeader(value = "Authorization") String token, @RequestBody AvaliacaoUsuario avaliacao) {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(avaliacaoService.avaliarUsuario(token, avaliacao)));
    }

    @GetMapping("/avaliacao/society/{idSociety}")
    public ResponseEntity<ResponseSocietyDTO> avaliacoesSociety(@PathVariable Long idSociety) {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(avaliacaoService.avaliacoesSociety(idSociety)));
    }

    @GetMapping("/avaliacao/usuario/{idUsuario}")
    public ResponseEntity<ResponseSocietyDTO> avaliacoesUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(avaliacaoService.avaliacoesUsuario(idUsuario)));
    }

    @GetMapping("/avaliacao/society")
    public ResponseEntity<ResponseSocietyDTO> avaliacoesSocietyPorToken(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(avaliacaoService.avaliacoesSociety(token)));
    }

    @GetMapping("/avaliacao/usuario")
    public ResponseEntity<ResponseSocietyDTO> avaliacoesUsuarioPorToken(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(avaliacaoService.avaliacoesUsuario(token)));
    }

    @GetMapping("/v1/avaliacao/usuario/pendente")
    public ResponseEntity<ResponseSocietyDTO> avaliacoesPendentesDoUsuario(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(avaliacaoService.avaliacoesPendentesDoUsuario(token)));
    }

    @GetMapping("/v1/avaliacao/society/pendente")
    public ResponseEntity<ResponseSocietyDTO> avaliacoesPendentesDoSociety(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(avaliacaoService.avaliacoesPendentesDoSociety(token)));
    }
}
