package com.society.agendamento.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CriaAgendamentoRequest {

    @NotNull
    private Long idSociety;
    @NotNull
    private LocalDateTime dataInicio;
    @NotNull
    @Positive
    private Long quantidadeHoras;
    @NotNull
        private String nomeResponsavel;
    @NotNull
    @Positive
    private String celular;
    private String celularSecundario;
    @Email
    private String email;
    private String observacao;
}
