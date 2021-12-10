package com.society.agendamento.infrastructure.dataproviders.repository;

import com.society.agendamento.domain.enums.StatusAgendamento;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "agendamento")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoEntity {

	@Id
	@SequenceGenerator(name = "AGENDAMENTOSEQ", sequenceName = "AGENDAMENTO_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "AGENDAMENTOSEQ")
	private Long id;

	private Long idSociety;
	private String nomeResponsavel;
	private String celular;
	private String celularSecundario;
	private String email;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	private String observacao;
	private StatusAgendamento status;
}
