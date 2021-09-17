package com.society.features.avaliacao.domain;

import com.society.features.agendamento.domain.Agendamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AvaliacaoUsuario {

	@Id
	@SequenceGenerator(name = "AVALIACAOUSRSEQ", sequenceName = "AVALIACAOUSR_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "AVALIACAOUSRSEQ")
	private Long id;
	@OneToOne
	private Agendamento agendamento;
	@NotNull
	private Integer nota;
	private String descricao;
}