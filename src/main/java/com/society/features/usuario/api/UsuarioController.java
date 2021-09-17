package com.society.features.usuario.api;

import com.society.features.society.domain.ResponseSocietyDTO;
import com.society.features.usuario.domain.Usuario;
import com.society.features.usuario.domain.UsuarioDTO;
import com.society.features.usuario.business.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/usuario")
    public ResponseEntity<ResponseSocietyDTO> criar(@RequestBody UsuarioDTO usuarioDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseSocietyDTO.withData(usuarioService.criar(usuarioDTO)));
    }

    @PutMapping("/v1/usuario")
    public ResponseEntity<ResponseSocietyDTO> alterar(@RequestHeader(value = "Authorization") String token,
                                                      @RequestBody Usuario usuario) {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(usuarioService.alterar(token, usuario)));
    }

    @GetMapping("/v1/usuario/token")
    public ResponseEntity<ResponseSocietyDTO> buscaPorToken(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok(ResponseSocietyDTO.withData(usuarioService.buscarPorToken(token)));
    }
}
