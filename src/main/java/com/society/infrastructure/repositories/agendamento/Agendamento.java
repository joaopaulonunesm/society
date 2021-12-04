package com.society.infrastructure.repositories.agendamento;

import com.society.infrastructure.repositories.society.Society;
import com.society.usecases.enums.StatusAgendamento;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Agendamento {

	@Id
	@SequenceGenerator(name = "AGENDAMENTOSEQ", sequenceName = "AGENDAMENTO_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "AGENDAMENTOSEQ")
	private Long id;
	@OneToOne
	@JoinColumn(name = "id_society")
	private Society society;
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
