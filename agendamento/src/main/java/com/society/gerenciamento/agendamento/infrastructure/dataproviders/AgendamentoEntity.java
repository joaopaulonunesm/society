package com.society.gerenciamento.agendamento.infrastructure.dataproviders;

import com.society.gerenciamento.agendamento.domain.enums.StatusAgendamento;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
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
	@OneToOne
	@JoinColumn(name = "id_society")
	private Long society;
	@Column(nullable = false)
	private String nomeResponsavel;
	private Long telefone;
	@Column(nullable = false)
	private Long celular;
	private String email;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private LocalDateTime dataInicio;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private LocalDateTime dataFim;
	private String observacao;
	private StatusAgendamento status;
}
